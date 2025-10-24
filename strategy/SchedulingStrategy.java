package strategy;

import models.Publicacao;

public interface SchedulingStrategy {
    boolean shouldPublishNow(Publicacao p);
}
