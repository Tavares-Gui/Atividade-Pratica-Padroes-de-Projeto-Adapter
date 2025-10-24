package strategy;

import model.Publicacao;

import java.time.LocalDateTime;

public class ScheduledStrategy implements SchedulingStrategy {
    @Override
    public boolean execute(Publicacao publicacao) throws Exception {
        LocalDateTime quando = publicacao.getAgendadaPara();
        if (quando == null || quando.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data invalida para ScheduledStrategy");
        }
        return true;
    }
}
