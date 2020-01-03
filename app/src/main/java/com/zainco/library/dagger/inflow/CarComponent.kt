package com.zainco.daggerpractice.inflow

import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(modules = [WheelsModule::class, PatrolEngineModule::class])
interface CarComponent {
    fun getCar(): Car

    fun inject(mainActivity: MainActivity)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun horsePower(@Named("horse Power") horsePower: Int)

        @BindsInstance
        fun engineCapacity(@Named("engine Capacity") engineCapacity: Int)

        fun build(): CarComponent
    }
}