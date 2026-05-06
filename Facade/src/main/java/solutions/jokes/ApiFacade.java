package solutions.jokes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ApiFacade {
    private static final int CONNECTION_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 5000;

    /**
     * Retrieves a specific attribute value from a JSON response of an API endpoint.
     *
     * @param urlString the URL of the API endpoint
     * @param attributeName the name of the JSON attribute to extract
     * @return the value of the specified attribute as a String
     * @throws IOException if the URL is invalid or the HTTP request fails
     * @throws IllegalArgumentException if the attribute is not found in the JSON response
     */
    public String getAttributeValueFromJson(String urlString, String attributeName)
            throws IOException, IllegalArgumentException {

        // Step 1: Validate URL and make HTTP request
        String jsonResponse = fetchJsonFromApi(urlString);

        // Step 2: Parse JSON and extract attribute
        return extractAttributeFromJson(jsonResponse, attributeName);
    }

    /**
     * Makes an HTTP GET request to the specified URL and retrieves the JSON response.
     *
     * @param urlString the URL endpoint
     * @return the raw JSON response as a String
     * @throws IOException if the URL is invalid or the HTTP request fails
     */
    private String fetchJsonFromApi(String urlString) throws IOException {
        URL url;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new IOException("Invalid URL: " + urlString, e);
        }

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException(
                        "HTTP request failed with status code: " + responseCode);
            }

            return readResponse(connection);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * Reads the response from the HTTP connection.
     *
     * @param connection the HTTP connection
     * @return the response body as a String
     * @throws IOException if reading fails
     */
    private String readResponse(HttpURLConnection connection) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        }
    }

    /**
     * Parses the JSON response and extracts the specified attribute.
     *
     * @param json the JSON string
     * @param attributeName the attribute to extract
     * @return the value of the attribute
     * @throws IllegalArgumentException if the attribute is not found
     * @throws IOException if JSON parsing fails
     */
    private String extractAttributeFromJson(String json, String attributeName)
            throws IllegalArgumentException, IOException {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(json);

            if (!jsonObject.containsKey(attributeName)) {
                throw new IllegalArgumentException(
                        "Attribute '" + attributeName + "' not found in JSON response");
            }

            Object value = jsonObject.get(attributeName);
            return value != null ? value.toString() : "";
        } catch (ParseException e) {
            throw new IOException("Failed to parse JSON response", e);
        }
    }
}