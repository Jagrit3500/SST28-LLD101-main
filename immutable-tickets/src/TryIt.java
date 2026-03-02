import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

public class TryIt {

    public static void main(String[] args) {

        TicketService service = new TicketService();

        IncidentTicket t1 = service.createTicket(
                "TCK-1001",
                "reporter@example.com",
                "Payment failing on checkout"
        );

        System.out.println("Created: " + t1);

        IncidentTicket t2 = service.assign(t1, "agent@example.com");
        IncidentTicket t3 = service.escalateToCritical(t2);

        System.out.println("\nAfter updates (new objects created):");
        System.out.println("Original: " + t1);
        System.out.println("Assigned: " + t2);
        System.out.println("Escalated: " + t3);

        // Try external mutation
        try {
            List<String> tags = t3.getTags();
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (Exception e) {
            System.out.println("\nTags list is immutable!");
        }
    }
}