package com.fdc.seb.BattleKnights;

import android.app.Application;
import android.content.Context;

/**
 * Created by Shide on 06/11/2015.
 */
public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        App.context = getApplicationContext();
    }
}
