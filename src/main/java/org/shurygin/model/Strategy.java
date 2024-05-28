package org.shurygin.model;

import org.shurygin.vo.Vacancy;
import java.util.List;

public interface Strategy {
    List<Vacancy> getVacancies(String searchString, int max);
}
