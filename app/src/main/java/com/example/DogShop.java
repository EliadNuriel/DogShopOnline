package com.example;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

public class DogShop extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext());
        FirebaseCrashlytics.getInstance().checkForUnsentReports();
        FirebaseAnalytics.getInstance(this)
                .setAnalyticsCollectionEnabled(true);
    }
}
