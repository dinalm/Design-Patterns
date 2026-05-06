package solutions.jokes;

public class JokeClient {
    private final ApiFacade apiFacade;

    public JokeClient() {
        this.apiFacade = new ApiFacade();
    }

    /**
     * Retrieves a random Chuck Norris joke using the API facade.
     *
     * @return the joke text
     * @throws Exception if the API request or JSON parsing fails
     */
    public String getRandomJoke() throws Exception {
        return apiFacade.getAttributeValueFromJson(
                "https://api.chucknorris.io/jokes/random",
                "value"
        );
    }

    public static void main(String[] args) {
        JokeClient client = new JokeClient();
        try {
            String jokeText = client.getRandomJoke();
            System.out.println(jokeText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}