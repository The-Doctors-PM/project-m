package com.example.feelingfinder.Goals;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Database.Goal;
import com.example.feelingfinder.Database.GoalsDAO;

import java.util.List;

public class GoalsActivityViewModel extends ViewModel {
    private MutableLiveData<List<Goal>> goalList;

    public MutableLiveData<List<Goal>> getGoalList(){
        if (goalList==null){
            goalList = new MutableLiveData<>();
            loadGoalList();
        }
        return goalList;
    }

    private void loadGoalList(){
        // Retrieve the Database instance
        AppDatabase db = Database.getAppDatabase();
        // Get access to the goals query
        GoalsDAO gDao = db.goalsDAO();
        // Queries all goals
        List<Goal> lg = gDao.getAll();
        goalList.postValue(lg);
    }
}

