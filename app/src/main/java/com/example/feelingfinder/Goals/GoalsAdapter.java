package com.example.feelingfinder.Goals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Database.Goal;
import com.example.feelingfinder.Database.GoalsDAO;
import com.example.feelingfinder.R;

import java.util.List;

public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.ViewHolder>{

    private List<Goal> goalList;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            checkBox = (CheckBox) view.findViewById(R.id.checkBoxGoal);
        }

        public CheckBox getCheckBoxView() {
            return checkBox;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public GoalsAdapter(List<Goal> dataSet) {
        goalList = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.goal_row, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        String content = goalList.get(position).description;
        Boolean checked = goalList.get(position).status;
        viewHolder.getCheckBoxView().setText(content);
        viewHolder.getCheckBoxView().setChecked(checked);

        // Set the listener for the checkbox
        viewHolder.getCheckBoxView().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // Retrieve the Database instance
                AppDatabase db = Database.getAppDatabase();
                // Get access to the goals query
                GoalsDAO gDao = db.goalsDAO();
                // Queries the goal
                Goal goal = gDao.getGoal(goalList.get(viewHolder.getAdapterPosition()).id);
                if(isChecked){
                    System.out.println("Checked goal #" + goalList.get(viewHolder.getAdapterPosition()).id + ": " + goal.description);
                    // Updates the goal
                    goal.status = true;
                    gDao.updateGoal(goal);
                }
                else {
                    System.out.println("Unchecked goal #" + + goalList.get(viewHolder.getAdapterPosition()).id + ": " + goal.description);
                    // Updates the goal
                    goal.status = false;
                    gDao.updateGoal(goal);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return goalList.size();
    }
}
