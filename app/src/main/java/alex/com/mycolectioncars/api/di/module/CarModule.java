
package alex.com.mycolectioncars.api.di.module;

import alex.com.mycolectioncars.api.api.CarsApiService;
import alex.com.mycolectioncars.api.di.scope.PerActivity;
import alex.com.mycolectioncars.api.mvp.view.MainView;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class CarModule {

    private MainView mView;

    public CarModule(MainView view) {
        mView = view;
    }

    @PerActivity
    @Provides
    CarsApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(CarsApiService.class);
    }

    @PerActivity
    @Provides
    MainView provideView() {
        return mView;
    }
}
