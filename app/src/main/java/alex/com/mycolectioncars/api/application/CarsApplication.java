
package alex.com.mycolectioncars.api.application;

import android.app.Application;

import alex.com.mycolectioncars.api.di.components.ApplicationComponent;
import alex.com.mycolectioncars.api.di.components.DaggerApplicationComponent;
import alex.com.mycolectioncars.api.di.module.ApplicationModule;

public class CarsApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this, "http://ovz1.mr-katsuba.1l0r1.vps.myjino.ru/tester/piska/"))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
