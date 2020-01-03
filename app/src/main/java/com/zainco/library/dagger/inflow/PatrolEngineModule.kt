package com.zainco.daggerpractice.inflow

import dagger.Binds
import dagger.Module

@Module
abstract class PatrolEngineModule {
    @Binds
     abstract fun bindEngine(engine: PatrolEngine): Engine
}