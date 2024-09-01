package com.example.currencyapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// this class would be created each time our app is created and destroyed when process is finished
@HiltAndroidApp
class CurrencyApp: Application()