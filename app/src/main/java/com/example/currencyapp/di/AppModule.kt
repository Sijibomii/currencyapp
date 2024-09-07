package com.example.currencyapp.di

import com.example.currencyapp.UserSettings
import com.example.currencyapp.UserSettingsImpl
import com.example.currencyapp.model.RatesRepository
import com.example.currencyapp.model.RatesRepositoryImpl
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
    abstract fun bindsUserSettings(settingsImpl: UserSettingsImpl): UserSettings
}