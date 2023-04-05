package com.example.feelingfinder.Diary;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.feelingfinder.FeelingFinder;

public class Data {
    private static Context context = FeelingFinder.getAppContext();
    private static String KEY = "todaysNote";
    private static SharedPreferences instance = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);

    private Data(){}

    public static SharedPreferences.Editor getEditor(){
        return instance.edit();
    }

    public static SharedPreferences getInstance(){
        return instance;
    }



}
