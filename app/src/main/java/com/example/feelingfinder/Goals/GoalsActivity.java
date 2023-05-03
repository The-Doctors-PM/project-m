package com.example.feelingfinder.Goals;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Database.Goal;
import com.example.feelingfinder.Database.GoalsDAO;
import com.example.feelingfinder.Dialogs.CreateGoalDialog;
import com.example.feelingfinder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class GoalsActivity extends AppCompatActivity implements CreateGoalDialog.NoticeDialogListener{

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GoalsAdapter goalsAdapter;
    private List<Goal> lg;

    private GoalsActivityViewModel viewModel;

    private MutableLiveData<List<Goal>> lgMLD;

    private TextView infoNoGoals;


    // ------------------- Recycler View -------------------

    private void initRV(){
        if (recyclerView == null){
            recyclerView = findViewById(R.id.goalsRV);
            linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        }
        recyclerView.setLayoutManager(linearLayoutManager);
        goalsAdapter = new GoalsAdapter(lg);
        recyclerView.setAdapter(goalsAdapter);
        goalsAdapter.notifyDataSetChanged();
    }

    // ----------------- End Recycler View ------------------



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        viewModel = new ViewModelProvider(this).get(GoalsActivityViewModel.class);

        viewModel.getGoalList().observe(this, goalList -> {
            initRV();
        });

        lgMLD = viewModel.getGoalList();

        // Retrieve the Database instance
        AppDatabase db = Database.getAppDatabase();
        // Get access to the goals query
        GoalsDAO gDao = db.goalsDAO();
        // Queries all goals
        lg = gDao.getAll();
        // Triggers the livedata update
        lgMLD.setValue(lg);


        // If there are no goals, then change text to inform user, otherwise shows all
        // goals to them
        if(lg.size() == 0){
            infoNoGoals = findViewById(R.id.noGoalsInfo);
            infoNoGoals.setVisibility(View.VISIBLE);
        }

        // Initialize the RecyclerView, showing the goals
        initRV();


        // Add a new auto-generated goal to the database
        FloatingActionButton addGoalButton = findViewById(R.id.addGoalButton);
        addGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creates and show a popup where the user inputs the goal description
                DialogFragment popup = new CreateGoalDialog();
                popup.show(getSupportFragmentManager(), "createGoalPopUp");
            }
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

    // Implementation of the method inside the Interface "CreateGoalDialog.NoticeDialogListener"
    // When the "Add" button has been clicked, it retrieves the data from the dialog and uses it
    // to create a new goal
    @Override
    public void onDialogPositiveClick(String content) {
        LinearLayout constraintLayout = findViewById(R.id.goalsLL);
        if (content.isEmpty()){
            // Creates a "Snack-bar" that informs the user that the data must not be empty
            Snackbar snackbar = Snackbar.make(constraintLayout, "ERROR, " +
                    "Goal must not be empty", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else{
            System.out.println("Arrived content here: " + content);
            // Creates a "Snack-bar" that informs the user that the data must not be empty
            Snackbar snackbar = Snackbar.make(constraintLayout,
                    "Goal added successfully", Snackbar.LENGTH_LONG);
            snackbar.show();
            createGoal(content);
        }
    }
    // Creates a new goal inside the database and updates the user.
    private void createGoal(@NonNull String content){
        // Retrieve the Database instance
        AppDatabase db = Database.getAppDatabase();
        // Get access to the goals query
        GoalsDAO gDao = db.goalsDAO();
        // Creates the new Goal in the database
        Goal newGoal = new Goal(content);
        System.out.println(newGoal.id + ": " + newGoal.description);
        gDao.addGoal(newGoal);
        System.out.println("Added new Goal:\n" +
                "ID: " + newGoal.id + "\nContent: " + newGoal.description + "\n\n");
        lg = gDao.getAll();
        lgMLD.setValue(lg);
        if(lg.size() >= 1){
            infoNoGoals = findViewById(R.id.noGoalsInfo);
            infoNoGoals.setVisibility(View.GONE);
        }
    }

}