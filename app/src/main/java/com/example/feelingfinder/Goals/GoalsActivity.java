package com.example.feelingfinder.Goals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Database.Goal;
import com.example.feelingfinder.Database.GoalsDAO;
import com.example.feelingfinder.Dialogs.CreateGoalDialog;
import com.example.feelingfinder.R;

import java.util.List;

public class GoalsActivity extends AppCompatActivity implements CreateGoalDialog.NoticeDialogListener {

    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        // Retrieve the Database instance
        AppDatabase db = Database.getAppDatabase();
        // Get access to the goals query
        GoalsDAO gDao = db.goalsDAO();
        // Queries all goals
        List<Goal> lg = gDao.getAll();

        // Get the Text containing all goals
        TextView goalsList = findViewById(R.id.goalsList);

        // If there are no goals, then change text to inform user, otherwise shows all
        // goals to them
        if(lg.size() == 0){
            goalsList.setText("There are no goals here!\n" +
                    "Add a new one with the button on the top right");
        }
        else {
            text = "";
            for (Goal g: lg) {
                text = text + "ID: " + g.getId() + ". " + g.getDescription() + ". STATUS: " +
                        g.getStatus() + "\n";
            }
            goalsList.setText(text);
        }

        // Add a new auto-generated goal to the database
        Button addGoalButton = findViewById(R.id.addGoalButton);
        addGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Goal newGoal = new Goal();
                System.out.println(newGoal.id + ": " + newGoal.description);
                gDao.addGoal(newGoal);
                text = text + "ID: " + newGoal.getId() + ". " + newGoal.getDescription() +
                        ". STATUS: " + newGoal.getStatus() + "\n";
                goalsList.setText(text);*/
                DialogFragment popup = new CreateGoalDialog();
                popup.show(getSupportFragmentManager(), "createGoalPopUp");
            }

            //TODO: study MutableLiveData for better performance?
        });

        // Wipes the database entire data. Use with care!
        Button wipeDbButton = findViewById(R.id.wipeDbButton);
        wipeDbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database.wipeGoals();
                finish();
            }
        });
    }

    @Override
    public void onDialogPositiveClick(String content) {

    }
    private void createGoal(@NonNull String content){
        // Retrieve the Database instance
        AppDatabase db = Database.getAppDatabase();
        // Get access to the goals query
        GoalsDAO gDao = db.goalsDAO();
        Goal newGoal = new Goal(content);
        System.out.println(newGoal.id + ": " + newGoal.description);
        gDao.addGoal(newGoal);
        text = text + "ID: " + newGoal.getId() + ". " + newGoal.getDescription() +
                ". STATUS: " + newGoal.getStatus() + "\n";
        // Get the Text containing all goals
        TextView goalsList = findViewById(R.id.goalsList);
        goalsList.setText(text);
    }
}