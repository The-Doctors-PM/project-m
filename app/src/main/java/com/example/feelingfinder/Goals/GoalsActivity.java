package com.example.feelingfinder.Goals;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Database.Goal;
import com.example.feelingfinder.Database.GoalsDAO;
import com.example.feelingfinder.Dialogs.AskConfirmDialog;
import com.example.feelingfinder.Dialogs.CreateGoalDialog;
import com.example.feelingfinder.Dialogs.EditGoalDialog;
import com.example.feelingfinder.Diary.PastNotesActivity;
import com.example.feelingfinder.MainActivity;
import com.example.feelingfinder.R;
import com.example.feelingfinder.Utility.FeelingFinder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class GoalsActivity extends AppCompatActivity implements CreateGoalDialog.NoticeDialogListener,
        AskConfirmDialog.NoticeConfirmListener, EditGoalDialog.NoticeEditListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GoalsAdapter goalsAdapter;
    private List<Goal> lg;

    private GoalsActivityViewModel viewModel;

    private MutableLiveData<List<Goal>> lgMLD;

    private TextView infoNoGoals;

    private Button wipeDbButton;

    private AdapterCallback adapterCallback = new AdapterCallback() {
        @Override
        public void deleteGoalCallback(int id) {
            DialogFragment popup = new AskConfirmDialog(id);
            popup.show(getSupportFragmentManager(), "AskConfirm");
        }

        @Override
        public void editGoalCallback(int id) {
            DialogFragment popup = new EditGoalDialog(id);
            popup.show(getSupportFragmentManager(), "Edit");
        }
    };


    // ------------------- Recycler View -------------------

    private void initRV(){
        if (recyclerView == null){
            recyclerView = findViewById(R.id.goalsRV);
            linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        }
        recyclerView.setLayoutManager(linearLayoutManager);
        goalsAdapter = new GoalsAdapter(lg, adapterCallback);
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

        wipeDbButton = findViewById(R.id.wipeDbButton);


        // If there are no goals, then change text to inform user, otherwise shows all
        // goals to them
        if(lg.size() == 0){
            infoNoGoals = findViewById(R.id.noGoalsInfo);
            infoNoGoals.setVisibility(View.VISIBLE);
            wipeDbButton.setVisibility(View.GONE);
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
        wipeDbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ask for confirmation
                new AlertDialog.Builder(GoalsActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete all Goals?")
                        .setMessage("Are you sure you want to delete ALL the goals?\n" +
                                "You won't be able to retrieve them in any way!!")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Wipes Db and goes back to Main Activity to enable the refresh
                                Database.wipeGoals();
                                startActivity(new Intent(FeelingFinder.getAppContext(), MainActivity.class));
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        FloatingActionButton backButton = findViewById(R.id.backButtonToHome);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // Implementation of the method inside the Interface "CreateGoalDialog.NoticeDialogListener"
    // When the "Add" button has been clicked, it retrieves the data from the dialog and uses it
    // to create a new goal
    @Override
    public void onDialogPositiveClick(String content) {
        LinearLayout linearLayout = findViewById(R.id.goalsLL);
        if (content.isEmpty()){
            // Creates a "Snack-bar" that informs the user that the data must not be empty
            Snackbar snackbar = Snackbar.make(linearLayout, "ERROR, " +
                    "Goal must not be empty", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else{
            System.out.println("Arrived content here: " + content);
            // Creates a "Snack-bar" that informs the user that the data must not be empty
            Snackbar snackbar = Snackbar.make(linearLayout,
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
            wipeDbButton.setVisibility(View.VISIBLE);
        }
    }

    // Deletes a Goal
    void deleteGoal(@NonNull int id){
        // Retrieve the Database instance
        AppDatabase db = Database.getAppDatabase();
        // Get access to the goals query
        GoalsDAO gDao = db.goalsDAO();
        // Get the goal
        Goal g = gDao.getGoal(id);
        System.out.println("Trying to delete Goal:\n" +
                "ID: " + g.id + "\nContent: " + g.description + "\n\n");
        // Delete goal
        gDao.deleteGoal(g);
        System.out.println("Goal deleted");

        lg = gDao.getAll();
        lgMLD.setValue(lg);

        if(lg.size() == 0){
            infoNoGoals = findViewById(R.id.noGoalsInfo);
            infoNoGoals.setVisibility(View.VISIBLE);
            wipeDbButton.setVisibility(View.GONE);
        }
    }

    void editGoal(@NonNull int id, @NonNull String newContent){
        // Retrieve the Database instance
        AppDatabase db = Database.getAppDatabase();
        // Get access to the goals query
        GoalsDAO gDao = db.goalsDAO();
        // Get the goal
        Goal g = gDao.getGoal(id);
        System.out.println("Trying to edit Goal:\n" +
                "ID: " + g.id + "\nContent: " + g.description + "\n" +
                "With new content: " + newContent + "\n");
        // Delete goal
        g.description = newContent;
        gDao.updateGoal(g);
        System.out.println("Goal edited");

        lg = gDao.getAll();
        lgMLD.setValue(lg);

    }

    @Override
    public void onDialogPositiveClick1(int id) {
        LinearLayout linearLayout = findViewById(R.id.goalsLL);
        Snackbar snackbar = Snackbar.make(linearLayout, "Goal Deleted", Snackbar.LENGTH_LONG);
        snackbar.show();
        this.deleteGoal(id);
    }

    @Override
    public void onDialogPositiveClick2(int id, String newContent) {
        LinearLayout linearLayout = findViewById(R.id.goalsLL);
        if (newContent.isEmpty()){
            // Creates a "Snack-bar" that informs the user that the data must not be empty
            Snackbar snackbar = Snackbar.make(linearLayout, "ERROR, " +
                    "Goal must not be empty", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else{
            this.editGoal(id, newContent);
        }
    }
}