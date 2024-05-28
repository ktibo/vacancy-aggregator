package org.shurygin.model;

import org.shurygin.vo.Vacancy;

import java.util.List;

public class Provider {
    private Strategy strategy;

    public List<Vacancy> getJavaVacancies(String searchString, int max) {
        return strategy.getVacancies(searchString, max);
    }

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
