package com.example.feelingfinder.Diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feelingfinder.Database.Note;
import com.example.feelingfinder.R;
import com.example.feelingfinder.Utility.FeelingFinder;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private List<Note> notesList;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView noteContent;
        private final TextView noteDate;
        private final View bgView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            noteContent = (TextView) view.findViewById(R.id.noteContent);
            noteDate = (TextView) view.findViewById(R.id.noteDate);
            bgView = view.findViewById(R.id.backgroundNote);
        }

        public TextView getNoteContentView() {
            return noteContent;
        }
        public TextView getNoteDateView(){return noteDate;}
        public View getBgView(){return bgView;}
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public NotesAdapter(List<Note> dataSet) {
        notesList = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_row, viewGroup, false);

        return new NotesAdapter.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(NotesAdapter.ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        // Create a shortened version of the Note's content that is 25 chars and adds
        // two dots at the end of it. If note's already shorter than 25 chars, then does nothing
        String content = notesList.get(position).content;
        int size = Math.min(content.length(), 25);
        if (size == 25){
            content = content.substring(0, size);
            content = content + "..";
        }

        // Date
        String noteDate = notesList.get(position).fullDate;

        // ID (used to open the single activity with the whole note
        int id = notesList.get(position).id;

        // Sets them in place
        viewHolder.getNoteContentView().setText(content);
        viewHolder.getNoteDateView().setText(noteDate);

        // Click listener for the note
        viewHolder.getBgView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeelingFinder.getAppContext(), SinglePastNoteActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", id);
                FeelingFinder.getAppContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return notesList.size();
    }
}
