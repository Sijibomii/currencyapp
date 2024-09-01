package com.example.currencyapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// this class would be created each time our app is created and destroyed when process is finished
//Application Class: Scoped to the entire lifecycle of the app.
// It initializes once and remains active as long as the app is running.
// Has a long-lived lifecycle tied to the app process.
// It starts when the app is launched and remains until the app process is terminated.
// Application Class: Responsible for application-wide initialization, setting up global state, and maintaining resources that need to exist throughout the app's life.
@HiltAndroidApp
class CurrencyApp: Application()