package io.loperilla.jokeapp

import android.app.Application
import io.loperilla.jokeapp.data.network.di.networkModule
import io.loperilla.jokeapp.domain.usecase.di.useCaseModule
import io.loperilla.jokeapp.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp
 * Created By Manuel Lopera on 3/10/24 at 20:50
 * All rights reserved 2024
 */
class JokeApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@JokeApp)
            modules(
                networkModule,
                useCaseModule
            )
            modules(
                presentationModule
            )
            // https://sv443.net/jokeapi/v2/
        }
    }
}
