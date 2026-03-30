public abstract class NotificationSender {

    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    public final void send(Notification n) {

        if (n == null) {
            throw new IllegalArgumentException("notification cannot be null");
        }

        validate(n);
        doSend(n);
    }

    protected void validate(Notification n) {
        // default: no extra validation
    }

    protected abstract void doSend(Notification n);
}