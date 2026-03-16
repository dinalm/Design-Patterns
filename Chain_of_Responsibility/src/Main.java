/**
 * Main - Builds the handler chain and sends various customer feedback messages through it.
 */
public class Main {

    public static void main(String[] args) {

        // Build the chain: Compensation -> Contact -> Suggestion -> General
        FeedbackHandler compensationHandler = new CompensationHandler();
        FeedbackHandler contactHandler      = new ContactRequestHandler();
        FeedbackHandler suggestionHandler   = new DevelopmentSuggestionHandler();
        FeedbackHandler generalHandler      = new GeneralFeedbackHandler();

        compensationHandler
                .setNext(contactHandler)
                .setNext(suggestionHandler)
                .setNext(generalHandler);

        // Create various feedback messages
        Message[] messages = {
                new Message(MessageType.COMPENSATION_CLAIM,
                        "I was overcharged last month. I have the invoice to prove it.",
                        "alice@example.com"),

                new Message(MessageType.COMPENSATION_CLAIM,
                        "I want a refund for my order.",
                        "bob@example.com"),

                new Message(MessageType.CONTACT_REQUEST,
                        "I need help with a billing issue on my account.",
                        "carol@example.com"),

                new Message(MessageType.CONTACT_REQUEST,
                        "There is a technical bug when I try to log in.",
                        "dave@example.com"),

                new Message(MessageType.DEVELOPMENT_SUGGESTION,
                        "The app keeps crashing on startup, this is urgent!",
                        "eve@example.com"),

                new Message(MessageType.DEVELOPMENT_SUGGESTION,
                        "It would be great to improve the search feature.",
                        "frank@example.com"),

                new Message(MessageType.GENERAL_FEEDBACK,
                        "Your service is excellent, I love using this app!",
                        "grace@example.com"),

                new Message(MessageType.GENERAL_FEEDBACK,
                        "I was disappointed with the delivery time.",
                        "henry@example.com")
        };

        System.out.println("=".repeat(50));
        System.out.println("   Customer Feedback Handler");
        System.out.println("=".repeat(50));
        System.out.println();

        // Send each message through the chain starting at the first handler
        for (Message message : messages) {
            compensationHandler.handle(message);
        }
    }
}
