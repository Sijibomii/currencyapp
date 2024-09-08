package com.example.currencyapp.di

import com.example.rates_repository.RatesRepository
import com.example.rates_repository_impl.RatesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    // this tells hilt how to find an impl for the RatesRepository.
    @Binds
    abstract fun bindsRatesRepository(repoImpl: RatesRepositoryImpl): RatesRepository

    @Binds
    abstract fun bindsUserSettings(settingsImpl: com.example.user_settings_impl.UserSettingsImpl): com.example.user_settings.UserSettings
}