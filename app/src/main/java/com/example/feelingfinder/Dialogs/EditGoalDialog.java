package com.example.feelingfinder.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Database.Goal;
import com.example.feelingfinder.Database.GoalsDAO;
import com.example.feelingfinder.R;

public class EditGoalDialog extends DialogFragment {
    private int id;
    private String newContent;

    public EditGoalDialog(int id){
        this.id = id;
    }

    public interface NoticeEditListener{
        void onDialogPositiveClick2(int id, String newContent);
    }

    NoticeEditListener noticeEditListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            noticeEditListener = (EditGoalDialog.NoticeEditListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException("Activity must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstance){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View myView = inflater.inflate(R.layout.dialog_edit_goal, null);
        // Get the TV
        TextView goalDescDialog = (TextView)myView.findViewById(R.id.editGoalDescription);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(myView)
        .setTitle("Editing goal")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        newContent = goalDescDialog.getText().toString();
                        noticeEditListener.onDialogPositiveClick2(id, newContent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditGoalDialog.this.getDialog().cancel();
                    }
                });
        // Retrieve the Database instance
        AppDatabase db = Database.getAppDatabase();
        // Get access to the goals query
        GoalsDAO gDao = db.goalsDAO();
        // Get the goal
        Goal g = gDao.getGoal(id);
        goalDescDialog.setText(g.description);
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        dismiss();
    }
}
