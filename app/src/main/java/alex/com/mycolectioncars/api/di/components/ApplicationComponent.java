package alex.com.mycolectioncars.api.di.components;

import android.content.Context;

import javax.inject.Singleton;

import alex.com.mycolectioncars.api.di.module.ApplicationModule;
import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit exposeRetrofit();

    Context exposeContext();
}
