/**
 * DevelopmentSuggestionHandler - Handles development suggestion messages.
 * Logs the suggestion and assigns a priority based on keywords.
 */
public class DevelopmentSuggestionHandler extends FeedbackHandler {

    private int suggestionCount = 0;

    @Override
    public void handle(Message message) {
        if (message.getType() != MessageType.DEVELOPMENT_SUGGESTION) {
            passToNext(message);
            return;
        }

        suggestionCount++;
        System.out.println("--- Development Suggestion Handler ---");
        System.out.println("From   : " + message.getSenderEmail());
        System.out.println("Message: " + message.getContent());

        String content = message.getContent().toLowerCase();
        String priority;

        if (content.contains("crash") || content.contains("broken") || content.contains("urgent")) {
            priority = "HIGH";
        } else if (content.contains("improve") || content.contains("feature")) {
            priority = "MEDIUM";
        } else {
            priority = "LOW";
        }

        System.out.println("Action: Logged as suggestion #" + suggestionCount + " with priority " + priority + ".");
        System.out.println();
    }
}
