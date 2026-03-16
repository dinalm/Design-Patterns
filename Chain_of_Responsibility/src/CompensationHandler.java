/**
 * CompensationHandler - Handles compensation claim messages.
 * Reviews the claim and approves or rejects it based on a keyword check.
 */
public class CompensationHandler extends FeedbackHandler {

    @Override
    public void handle(Message message) {
        if (message.getType() != MessageType.COMPENSATION_CLAIM) {
            passToNext(message);
            return;
        }

        System.out.println("--- Compensation Claim Handler ---");
        System.out.println("From   : " + message.getSenderEmail());
        System.out.println("Message: " + message.getContent());

        // Simple approval rule: approve if content mentions "receipt" or "invoice"
        String content = message.getContent().toLowerCase();
        if (content.contains("receipt") || content.contains("invoice")) {
            System.out.println("Decision: APPROVED - valid proof of purchase found.");
        } else {
            System.out.println("Decision: REJECTED - no proof of purchase provided.");
        }
        System.out.println();
    }
}
