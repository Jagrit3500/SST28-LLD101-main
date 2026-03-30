package com.example.tickets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {

        return IncidentTicket.builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .tags(new ArrayList<>(Arrays.asList("NEW")))
                .build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {

        return t.toBuilder()
                .priority("CRITICAL")
                .tags(appendTag(t.getTags(), "ESCALATED"))
                .build();
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {

        return t.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }

    private List<String> appendTag(List<String> existing, String newTag) {
        List<String> copy = new ArrayList<>(existing);
        copy.add(newTag);
        return copy;
    }
}