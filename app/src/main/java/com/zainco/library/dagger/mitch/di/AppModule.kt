package com.zainco.library.dagger.mitch.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.zainco.library.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * contains all the application level dependencies for the project like retrofit instance and glide
 */
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions.placeholderOf(R.drawable.white_background)
            .error(R.drawable.white_background)
    }

    /*
    application injected from AppComponent
     */
    @Singleton
    @Provides
    fun providesGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    fun provideAppDrawable(application: Application): Drawable {
        return ContextCompat.getDrawable(application, R.drawable.logo_mitch)!!
    }

}

/*

@Module
object ObjectModule{
    @JvmStatic
    @Provides
    fun someString(): String {
        return "------------Ahmed Zain-------------"
    }
}*/
