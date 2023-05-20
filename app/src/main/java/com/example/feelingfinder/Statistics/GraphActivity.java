package com.example.feelingfinder.Statistics;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Database.GoalsDAO;
import com.example.feelingfinder.Database.Question;
import com.example.feelingfinder.Database.QuestionsDAO;
import com.example.feelingfinder.Database.Quiz;
import com.example.feelingfinder.Database.QuizDAO;
import com.example.feelingfinder.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

public class GraphActivity extends AppCompatActivity {

    private List<Quiz> quizList;
    private List<Question> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        GraphView graph = (GraphView) findViewById(R.id.graph1);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        series.setColor(Color.RED);
        graph.addSeries(series);
        graph.setTitle("Mock 1");



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

        GraphView graph2 = (GraphView) findViewById(R.id.graph2);
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(dataPoints);
        series2.setColor(Color.BLUE);
        graph2.addSeries(series2);
        graph2.setTitle("Daily Rating");
        GridLabelRenderer glr = graph2.getGridLabelRenderer();
        glr.setHorizontalAxisTitle("Days");
        glr.setVerticalAxisTitle("Rating");
        glr.setGridColor(Color.GRAY);
        graph2.getViewport().setScalable(true);
        graph2.getViewport().setScalableY(true);
        graph2.getViewport().setScrollable(true);
        graph2.getViewport().setScrollableY(true);

        // -------------- END Custom data try #1 END ---------------



        GraphView graph3 = (GraphView) findViewById(R.id.graph3);
        BarGraphSeries<DataPoint> series3 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, -1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph3.addSeries(series3);
        graph3.setTitle("Mock 2");
    }
}