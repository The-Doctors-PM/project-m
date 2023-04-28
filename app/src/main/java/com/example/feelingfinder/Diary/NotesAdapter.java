package com.example.feelingfinder.Diary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feelingfinder.Database.Note;
import com.example.feelingfinder.R;

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

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            noteContent = (TextView) view.findViewById(R.id.noteContent);
            noteDate = (TextView) view.findViewById(R.id.noteDate);
        }

        public TextView getNoteContentView() {
            return noteContent;
        }
        public TextView getNoteDateView(){return noteDate;}
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

        // Create a shortened version of the Note's content that is about 15 chars and
        // adds two dots at the end of it
        String content = notesList.get(position).content;
        content = content.substring(0, Math.min(content.length(), 15));
        content = content + "..";

        // Date
        String noteDate = notesList.get(position).fullDate;

        // Sets them in place
        viewHolder.getNoteContentView().setText(content);
        viewHolder.getNoteDateView().setText(noteDate);

    }


    @Override
    public int getItemCount() {
        return notesList.size();
    }
}
