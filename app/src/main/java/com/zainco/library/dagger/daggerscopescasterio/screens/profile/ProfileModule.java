package com.zainco.library.dagger.daggerscopescasterio.screens.profile;

import com.zainco.library.dagger.daggerscopescasterio.models.SomeBigObject;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileModule {

    // Profile Screen based dependencies go here

    @Provides
    public SomeBigObject providesSomeBigObject() {
        return new SomeBigObject();
    }
}
