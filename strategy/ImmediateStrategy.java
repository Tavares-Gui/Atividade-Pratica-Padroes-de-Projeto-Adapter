package strategy;

import model.Publicacao;

public class ImmediateStrategy implements SchedulingStrategy {
    @Override
    public boolean execute(Publicacao publicacao) {
        return true;
    }
}
