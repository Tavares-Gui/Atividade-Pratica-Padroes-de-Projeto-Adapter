package strategy;

import model.Publicacao;

public interface SchedulingStrategy {
    boolean execute(Publicacao publicacao) throws Exception;
}
