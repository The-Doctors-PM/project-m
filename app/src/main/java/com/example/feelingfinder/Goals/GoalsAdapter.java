package com.example.feelingfinder.Goals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    private AdapterCallback adapterCallback;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBox;
        private final Button deleteButton;
        private final Button editButton;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            checkBox = (CheckBox) view.findViewById(R.id.checkBoxGoal);

            deleteButton = (Button) view.findViewById(R.id.deleteGoalButton);
            editButton = (Button) view.findViewById(R.id.editGoalButton);
        }

        public CheckBox getCheckBoxView() {
            return checkBox;
        }
        public Button getDeleteButton(){ return deleteButton;}
        public Button getEditButton(){ return editButton;}
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public GoalsAdapter(List<Goal> dataSet, AdapterCallback adapterCallback) {
        goalList = dataSet;
        this.adapterCallback = adapterCallback;
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

        // Reduce the length of the content, in case of very long goals' descriptions
        if (content.length() >= 25){
            content = content.substring(0, 23);
            content = content + "..";
        }

        // Set the text and checked box
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

        // Set the listener for the delete button
        viewHolder.getDeleteButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = goalList.get(viewHolder.getAdapterPosition()).id;
                System.out.println("Trying to delete goal #" +
                        id + ": " +
                        goalList.get(viewHolder.getAdapterPosition()).description
                );

                // Open dialog
                adapterCallback.deleteGoalCallback(id);
            }
        });

        viewHolder.getEditButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = goalList.get(viewHolder.getAdapterPosition()).id;
                System.out.println("Trying to edit goal #" +
                        id + ": " +
                        goalList.get(viewHolder.getAdapterPosition()).description
                );

                // Open dialog
                adapterCallback.editGoalCallback(id);
            }
        });

    }


    @Override
    public int getItemCount() {
        return goalList.size();
    }
}
