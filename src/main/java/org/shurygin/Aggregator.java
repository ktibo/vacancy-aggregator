package org.shurygin;

import org.shurygin.model.HHStrategy;
import org.shurygin.model.Model;
import org.shurygin.model.Provider;
import org.shurygin.view.HtmlView;

public class Aggregator
{
    public static void main( String[] args )
    {
        HtmlView view = new HtmlView();
        Model model = new Model(view, new Provider(new HHStrategy()));
        Controller controller = new Controller(model);

        view.setController(controller);

        view.userCitySelectEmulationMethod();
    }
}
