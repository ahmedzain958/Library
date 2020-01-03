package com.zainco.library.dagger.daggerscopescasterio.screens.settings;

import dagger.Subcomponent;

@SettingsScope
@Subcomponent(modules = { SettingsModule.class })
public interface SettingsComponent {
    void inject(SettingsFragment target);
}
