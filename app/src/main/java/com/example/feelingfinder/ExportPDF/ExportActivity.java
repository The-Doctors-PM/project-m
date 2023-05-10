package com.example.feelingfinder.ExportPDF;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Database.DateToStringConverter;
import com.example.feelingfinder.Database.Goal;
import com.example.feelingfinder.Database.GoalsDAO;
import com.example.feelingfinder.Database.Note;
import com.example.feelingfinder.Database.NotesDAO;
import com.example.feelingfinder.R;
import com.example.feelingfinder.Utility.FeelingFinder;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExportActivity extends AppCompatActivity {
    private final int charPerRow = 25;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);

        Button exportGoals, exportNotes, exportWeeklyNotes;
        exportGoals = findViewById(R.id.exportGoals);
        exportNotes = findViewById(R.id.exportNotes);
        exportWeeklyNotes = findViewById(R.id.exportWeeklyNotes);

        exportGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    createGoalsPdf();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        exportNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    createNotesPdf();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        exportWeeklyNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    createNotesPdfWeek();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void createGoalsPdf() throws IOException {
        // Create a new document
        PdfDocument document = new PdfDocument();

        // Creates default black paint used to write
        Paint myPaint = new Paint();

        // PageInfo = Data about page dimensions
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(400,600,1).create();
        // Create a page in the pdf with the pageInfo as a "template"
        PdfDocument.Page page = document.startPage(pageInfo);

        // Get the canvas to draw in it. Will be used to put data inside the page
        Canvas canvas = page.getCanvas();

        // Retrieve the Database instance
        AppDatabase db = Database.getAppDatabase();
        // Get access to the goals query and gets all goals
        GoalsDAO gDao = db.goalsDAO();
        List<Goal> lg = gDao.getAll();

        // Variable to get "New Line" effect in Canvas
        int y = 20;
        // Draws in the canvas a text for every goal, with its description and status
        for (Goal g: lg){
            canvas.drawText(getGoalInfoString(g), 10, y, myPaint);
            y = y + 20;
        }

        // Informs the system that the app has finished working on the page
        document.finishPage(page);

        // Decide file name. In this case is Goals_TODAYDATE(YYYYMMDD format).pdf
        String fileName = "Goals_"+ DateToStringConverter.dateToInt(LocalDate.now()) + ".pdf";

        // Create the new file in the "Download" folder of the device.
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

        try{
            // Tries to put the document object inside the newly created file
            document.writeTo(new FileOutputStream(file));
            // close the document object. This means that everything is done.
            document.close();
            // Informs the user
            Toast toast = Toast.makeText(FeelingFinder.getAppContext(), "Check your download folder", Toast.LENGTH_LONG);
            toast.show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private String getGoalInfoString(Goal g){
        String status = "Uncompleted";
        String desc = g.description;
        if (g.status){
            status = "Completed";
        }
        if (g.description.length() > 25){
            desc = g.description.substring(0, 24);
            desc = desc + "..";
        }
        return "#" + g.id + ": " + desc +
                " Status: " + status;
    }

    private void createNotesPdf() throws IOException{
        int pageNumber = 1;
        PdfDocument document = new PdfDocument();
        Paint myPaint = new Paint();
        AppDatabase db = Database.getAppDatabase();
        NotesDAO nDao = db.notesDAO();
        List<Note> ln = nDao.getAll();

        for (Note n: ln) {
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(400,600, pageNumber).create();
            PdfDocument.Page page = document.startPage(pageInfo);
            Canvas canvas = page.getCanvas();
            List<String> fullText = getTextForCanvas(n.content);
            int y = 20;
            for (String text: fullText) {
                canvas.drawText(text, 10, y, myPaint);
                y = y + 20;
            }
            document.finishPage(page);
            pageNumber++;
        }

        // Decide file name. In this case is Goals_TODAYDATE(YYYYMMDD format).pdf
        String fileName = "AllNotes_"+ DateToStringConverter.dateToInt(LocalDate.now()) + ".pdf";

        // Create the new file in the "Download" folder of the device.
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

        try{
            document.writeTo(new FileOutputStream(file));
            document.close();
            // Informs the user
            Toast toast = Toast.makeText(FeelingFinder.getAppContext(), "Check your download folder", Toast.LENGTH_LONG);
            toast.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private List<String> getTextForCanvas(String content){
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i + charPerRow + 1 < content.length()){
            String subString = content.substring(i,i+charPerRow);
            res.add(subString);
            i = i + charPerRow + 1;
        }
        res.add(content.substring(i));
        return res;
    }

    private void createNotesPdfWeek() throws IOException{
        int pageNumber = 1;
        PdfDocument document = new PdfDocument();
        Paint myPaint = new Paint();
        AppDatabase db = Database.getAppDatabase();
        NotesDAO nDao = db.notesDAO();
        int today = DateToStringConverter.dateToInt(LocalDate.now());
        List<Note> ln = nDao.getAllPastWeek(today, today-8);
        System.out.println("Size: " + ln.size());
        for (Note n: ln) {
            System.out.println("Note: " + n.content);
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(400,600, pageNumber).create();
            PdfDocument.Page page = document.startPage(pageInfo);
            Canvas canvas = page.getCanvas();
            List<String> fullText = getTextForCanvas(n.content);
            int y = 20;
            for (String text: fullText) {
                canvas.drawText(text, 10, y, myPaint);
                y = y + 20;
            }
            document.finishPage(page);
            pageNumber++;
        }

        // Decide file name. In this case is Goals_TODAYDATE(YYYYMMDD format).pdf
        String fileName = "WeeklyNotes_"+ DateToStringConverter.dateToInt(LocalDate.now()) + ".pdf";

        // Create the new file in the "Download" folder of the device.
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

        try{
            document.writeTo(new FileOutputStream(file));
            document.close();
            // Informs the user
            Toast toast = Toast.makeText(FeelingFinder.getAppContext(), "Check your download folder", Toast.LENGTH_LONG);
            toast.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}