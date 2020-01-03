package com.zainco.daggerpractice.inflow

import dagger.Module
import dagger.Provides


@Module
class DieselEngineModule constructor(val horsePower: Int) {
    @Provides
    fun provideHorsePower(): Int {
        return horsePower
    }

    @Provides
    fun provideEngine(dieselEngine: DieselEngine): Engine {
        return dieselEngine
    }
}