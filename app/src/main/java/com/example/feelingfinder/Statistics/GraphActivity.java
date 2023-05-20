package com.example.feelingfinder.Statistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Database;
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

import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {

    private List<Quiz> quizList;
    private List<Question> questionList;
    private GraphView graph;
    private AppDatabase db = Database.getAppDatabase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        // Retrieve the Database instance
        AppDatabase db = Database.getAppDatabase();
        // Get access to the quiz query
        QuizDAO qzDao = db.quizDAO();
        // Get access to the questions query
        QuestionsDAO qDao = db.questionsDAO();
        // Gets all the quiz
        quizList = qzDao.getAll();
        questionList = qDao.getAll();

        graph = (GraphView) findViewById(R.id.graph);
        graph.getGridLabelRenderer().setGridColor(Color.GRAY);
        //graph.getViewport().setScalable(true);
        //graph.getViewport().setScalableY(true);
        //graph.getViewport().setScrollable(true);
        //graph.getViewport().setScrollableY(true);

        loadDailyRatingGraph();


        // Items
        CardView dailyRating = findViewById(R.id.dailyRatingCV);
        CardView anxiety = findViewById(R.id.anxietyCV);
        CardView anxietyRating = findViewById(R.id.anxietyRating);
        dailyRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadDailyRatingGraph();
            }
        });
        anxiety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAnxietyGraph();
            }
        });
        anxietyRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAnxietyRatingGraph();
            }
        });


    }

    private void resetGraph(){
        graph.removeAllSeries();
    }

    private void loadDailyRatingGraph(){
        resetGraph();
        // Datapoint array for the graph
        DataPoint[] dataPoints;
        dataPoints = new DataPoint[quizList.size()];

        int counter = 0;

        // For every quiz displays the day rating in a graph
        for (Quiz q: quizList) {
            dataPoints[counter] = new DataPoint(counter, q.dayRating);
            counter++;
        }

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
        series.setColor(Color.BLUE);
        graph.addSeries(series);
        graph.setTitle("Daily Rating");
        GridLabelRenderer glr = graph.getGridLabelRenderer();
        glr.setHorizontalAxisTitle("Days");
        glr.setVerticalAxisTitle("Rating");

    }

    private void loadAnxietyGraph(){
        resetGraph();
        DataPoint[] dataPoints;
        dataPoints = new DataPoint[quizList.size()];

        int counter = 0;

        for (Quiz q: quizList) {
            int val = 0;
            if (q.hadAnxiety){
                val = 1;
            }
            dataPoints[counter] = new DataPoint(counter, val);
            counter++;
        }

        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(dataPoints);
        series.setColor(Color.BLUE);
        graph.addSeries(series);
        graph.setTitle("Anxiety");
        GridLabelRenderer glr = graph.getGridLabelRenderer();
        glr.setHorizontalAxisTitle("Days");
        glr.setVerticalAxisTitle("Yes/No");
    }
    private void loadAnxietyRatingGraph(){
        resetGraph();
        DataPoint[] dataPoints;
        dataPoints = new DataPoint[quizList.size()];
        List<Quiz> quizAnx = new ArrayList<>();
        for (Quiz q: quizList) {
            if (q.hadAnxiety) {
                quizAnx.add(q);
            }
        }

        int counter = 0;

        for (Quiz q: quizList) {
            if (quizAnx.contains(q)){
                List<Question> questions = db.questionsDAO().getQuestionFromQuizId(q.id);
                for (Question qq : questions){
                    if (qq.question.equals("Anxiety")){
                        DataPoint d = new DataPoint(counter, qq.answer);
                        dataPoints[counter] = d;
                    }
                }
            }
            else{
                DataPoint d = new DataPoint(counter, 0);
                dataPoints[counter] = d;
            }

            counter++;
        }

        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(dataPoints);
        series.setColor(Color.BLUE);
        graph.addSeries(series);
        graph.setTitle("Anxiety Detailed");
        GridLabelRenderer glr = graph.getGridLabelRenderer();
        glr.setHorizontalAxisTitle("Days");
        glr.setVerticalAxisTitle("Amount");
    }
}