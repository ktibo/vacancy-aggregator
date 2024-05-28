package org.shurygin.view;

import org.shurygin.Controller;
import org.shurygin.vo.Vacancy;

import java.util.List;

public interface View {
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
