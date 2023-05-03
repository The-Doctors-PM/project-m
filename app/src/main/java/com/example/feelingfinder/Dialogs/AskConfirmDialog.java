package com.example.feelingfinder.Dialogs;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;


public class AskConfirmDialog extends DialogFragment {

    private int id;

    public AskConfirmDialog(int id) {
        this.id = id;
    }

    public interface NoticeConfirmListener{
        public void onDialogPositiveClick(int id);
    }

    NoticeConfirmListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (AskConfirmDialog.NoticeConfirmListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException("Activity must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstance){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("The goal will be deleted forever!").setTitle("Are your sure?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        listener.onDialogPositiveClick(id);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AskConfirmDialog.this.getDialog().cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        dismiss();
    }
}
