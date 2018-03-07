
package alex.com.mycolectioncars.api.mvp.view;

import java.util.List;

import alex.com.mycolectioncars.api.mvp.model.Car;

public interface MainView extends BaseView {

    void onCarsLoaded(List<Car> cakes);

    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);

    void onClearItems();
}
