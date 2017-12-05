package com.londoncentralmosque.Model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Android Dev on 7/4/2017.
 */

public class Preference {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private static final String PREFERENCE_NAME = "alarm_sound";
    private static final String SOUND_KEY = "NAME";


    public Preference(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void saveUserData(int data) {
        editor.putInt(SOUND_KEY, data);
        editor.commit();

    }

    public int getUserData() {
        int dataFromSPreference = sharedPreferences.getInt(SOUND_KEY, 0);
        return dataFromSPreference;
    }
}

