/**
 * GeneralFeedbackHandler - Handles general feedback messages.
 * Analyzes the sentiment and sends an appropriate response.
 */
public class GeneralFeedbackHandler extends FeedbackHandler {

    @Override
    public void handle(Message message) {
        if (message.getType() != MessageType.GENERAL_FEEDBACK) {
            passToNext(message);
            return;
        }

        System.out.println("--- General Feedback Handler ---");
        System.out.println("From   : " + message.getSenderEmail());
        System.out.println("Message: " + message.getContent());

        String content = message.getContent().toLowerCase();
        String response;

        if (content.contains("great") || content.contains("love") || content.contains("excellent")) {
            response = "Thank you for your kind words! We are glad you enjoyed our service.";
        } else if (content.contains("bad") || content.contains("terrible") || content.contains("disappointed")) {
            response = "We are sorry to hear about your experience. We will work to improve.";
        } else {
            response = "Thank you for your feedback. We have noted your comments.";
        }

        System.out.println("Response sent to " + message.getSenderEmail() + ": \"" + response + "\"");
        System.out.println();
    }
}
