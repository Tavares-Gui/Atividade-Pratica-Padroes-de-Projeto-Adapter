package strategy;

import models.Publicacao;
import java.time.Instant;

public class ScheduledStrategy implements SchedulingStrategy {
    @Override
    public boolean shouldPublishNow(Publicacao p) {
        return p.getAgendamento() != null && !p.getAgendamento().isAfter(Instant.now());
    }
}
