package com.zainco.library.dagger.daggerscopescasterio;

import android.app.Application;

import com.zainco.library.dagger.daggerscopescasterio.screens.profile.ProfileComponent;

import com.zainco.library.dagger.daggerscopescasterio.screens.profile.ProfileModule;
import com.zainco.library.dagger.daggerscopescasterio.screens.settings.SettingsComponent;
import com.zainco.library.dagger.daggerscopescasterio.screens.settings.SettingsModule;

public class DemoApplication extends Application {

    private AppComponent appComponent;
    private ProfileComponent profileComponent;
    private SettingsComponent settingsComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Set up the data store
        /*RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
*/
//        appComponent = createAppComponent();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public ProfileComponent createProfileComponent() {
        profileComponent = appComponent.plus(new ProfileModule());
        return profileComponent;
    }

    public void releaseProfileComponent() {
        profileComponent = null;
    }

    public SettingsComponent createSettingsComponent() {
        settingsComponent = appComponent.plus(new SettingsModule());
        return settingsComponent;
    }

    public void releaseSettingsComponent() {
        settingsComponent = null;
    }

   /* private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .androidModule(new AndroidModule(this))
                .appModule(new AppModule())
                .build();
    }*/


}
