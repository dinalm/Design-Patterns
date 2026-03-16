/**
 * ContactRequestHandler - Handles contact request messages.
 * Forwards the request to the appropriate department based on keywords.
 */
public class ContactRequestHandler extends FeedbackHandler {

    @Override
    public void handle(Message message) {
        if (message.getType() != MessageType.CONTACT_REQUEST) {
            passToNext(message);
            return;
        }

        System.out.println("--- Contact Request Handler ---");
        System.out.println("From   : " + message.getSenderEmail());
        System.out.println("Message: " + message.getContent());

        String content = message.getContent().toLowerCase();
        String department;

        if (content.contains("billing") || content.contains("payment")) {
            department = "Billing Department";
        } else if (content.contains("technical") || content.contains("bug")) {
            department = "Technical Support";
        } else {
            department = "Customer Service";
        }

        System.out.println("Action: Forwarded to " + department + ".");
        System.out.println();
    }
}
