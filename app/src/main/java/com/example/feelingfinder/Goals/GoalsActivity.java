package com.example.feelingfinder.Goals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Data;
import com.example.feelingfinder.Database.Goal;
import com.example.feelingfinder.Database.GoalsDAO;
import com.example.feelingfinder.R;

import java.util.List;

public class GoalsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        // Retrieve the Database instance
        AppDatabase db = Data.getAppDatabase();
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
            String text = "";
            for (Goal g: lg) {
                text = text + "ID: " + g.getId() + ". " + g.getDescription() + ". STATUS: " +
                        g.getStatus() + "\n";
            }
            goalsList.setText(text);
        }


        Button addGoalButton = findViewById(R.id.addGoalButton);
        addGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gDao.addGoal(new Goal());
            }
        });
    }
}