/**
 * FeedbackHandler - Abstract base class for the Chain of Responsibility pattern.
 * Each concrete handler either handles the message or passes it to the next handler.
 */
public abstract class FeedbackHandler {

    private FeedbackHandler next;

    /** Links the next handler in the chain and returns it for easy chaining. */
    public FeedbackHandler setNext(FeedbackHandler next) {
        this.next = next;
        return next;
    }

    /** Passes the message to the next handler, or reports unhandled if chain ends. */
    protected void passToNext(Message message) {
        if (next != null) {
            next.handle(message);
        } else {
            System.out.println("No handler found for message type: " + message.getType());
        }
    }

    /** Each subclass implements this to decide whether to handle or pass on. */
    public abstract void handle(Message message);
}
