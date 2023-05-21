package com.example.feelingfinder.Utility;

import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Database.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizGlobalVariables {

    public static List<Question> todaysQuestions = new ArrayList<>();

    public static int dayRating = -1;

    public static boolean hadAnxiety = false;
    public static boolean wasSatisfied = false;
    public static boolean betterTomorrow = false;

    public static void initDailyQuestions(){
        QuizGlobalVariables.todaysQuestions = new ArrayList<>();
        System.out.println("TQ size: " + todaysQuestions.size());
    }
}
