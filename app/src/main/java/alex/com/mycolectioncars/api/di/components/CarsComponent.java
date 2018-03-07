
package alex.com.mycolectioncars.api.di.components;

import alex.com.mycolectioncars.api.di.module.CarModule;
import alex.com.mycolectioncars.api.di.scope.PerActivity;
import alex.com.mycolectioncars.api.modules.home.MainActivity;
import dagger.Component;

@PerActivity
@Component(modules = CarModule.class, dependencies = ApplicationComponent.class)
public interface CarsComponent {

    void inject(MainActivity activity);
}
