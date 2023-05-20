package com.example.feelingfinder.Statistics.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Database.Question;
import com.example.feelingfinder.Database.QuestionsDAO;
import com.example.feelingfinder.Database.Quiz;
import com.example.feelingfinder.Database.QuizDAO;
import com.example.feelingfinder.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

public class DailyRatingFragment extends Fragment {
    private List<Quiz> quizList;
    private List<Question> questionList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(
                R.layout.fragment_daily_rating, container, false);
    }

    public void setData(){
        // ------------------ Custom data try #1 ------------------
        // Retrieve the Database instance
        AppDatabase db = Database.getAppDatabase();
        // Get access to the quiz query
        QuizDAO qzDao = db.quizDAO();
        // Get access to the questions query
        QuestionsDAO qDao = db.questionsDAO();
        // Gets all the quiz
        quizList = qzDao.getAll();

        // Datapoint array for the graph
        DataPoint[] dataPoints;
        dataPoints = new DataPoint[quizList.size()];

        int counter = 0;

        // For every quiz displays the day rating in a graph
        for (Quiz q: quizList) {
            dataPoints[counter] = new DataPoint(counter, q.dayRating);
            counter++;
        }

        GraphView graph = getView().findViewById(R.id.graphDaily);
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(dataPoints);
        series2.setColor(Color.BLUE);
        graph.addSeries(series2);
        graph.setTitle("Daily Rating");
        GridLabelRenderer glr = graph.getGridLabelRenderer();
        glr.setHorizontalAxisTitle("Days");
        glr.setVerticalAxisTitle("Rating");
        glr.setGridColor(Color.GRAY);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScrollableY(true);

        // -------------- END Custom data try #1 END ---------------
    }
}