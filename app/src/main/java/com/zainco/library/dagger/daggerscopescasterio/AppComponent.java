package com.zainco.library.dagger.daggerscopescasterio;

import com.zainco.library.dagger.daggerscopescasterio.screens.profile.ProfileComponent;
import com.zainco.library.dagger.daggerscopescasterio.screens.profile.ProfileModule;
import com.zainco.library.dagger.daggerscopescasterio.screens.settings.SettingsComponent;
import com.zainco.library.dagger.daggerscopescasterio.screens.settings.SettingsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, AndroidModule.class})
public interface AppComponent {
    void inject(DemoApplication target);

    ProfileComponent plus(ProfileModule ProfileModule);

    SettingsComponent plus(SettingsModule settingsModule);
}
