package com.zainco.library.databinding.mvvmoldviewpagerfragments.di.modules

import com.zainco.library.databinding.mvvmoldviewpagerfragments.data.remote.Api
import com.zainco.library.databinding.mvvmoldviewpagerfragments.domain.repository.ColorRepository
import com.zainco.library.databinding.mvvmoldviewpagerfragments.domain.repository.UserRepository
import com.zainco.library.databinding.mvvmoldviewpagerfragments.util.schedulers.BaseScheduler
import com.zainco.library.databinding.mvvmoldviewpagerfragments.util.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author Wellington Costa on 31/12/2017.
 */
@Module(includes = [(ViewModelModule::class)])
class AppModule {

    @Provides
    @Singleton
    fun provideApi(retorfit: Retrofit): Api {
        return retorfit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: Api): UserRepository {
        return UserRepository(api)
    }

    @Provides
    @Singleton
    fun provideColorRepository(api: Api): ColorRepository {
        return ColorRepository(api)
    }

    @Provides
    @Singleton
    fun provideScheduler(): BaseScheduler {
        return SchedulerProvider()
    }

}