package com.example.feelingfinder.Database;

import android.content.Context;

import androidx.room.Room;

import com.example.feelingfinder.Utility.FeelingFinder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Database {
    private static Context context = FeelingFinder.getAppContext();

    private static AppDatabase db;
    private Database(){}

    private static int fakeDate = 20230503;

    public static void buildAppDatabase(){
        //TODO: Allow main thread queries should be a temporary solution!!
        Database.db = Room.databaseBuilder(FeelingFinder.getAppContext(),
                AppDatabase.class, "FeelingFinderDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public static AppDatabase getAppDatabase() {
        return Database.db;
    }

    public static void wipeGoals(){
        List<Goal> lg = db.goalsDAO().getAll();
        for (Goal g: lg) {
            db.goalsDAO().deleteGoal(g);
        }
        System.out.println("Goals in the database deleted");
    }

    public static void wipeNotes(){
        List<Note> ln = db.notesDAO().getAll();
        for (Note n: ln) {
            db.notesDAO().deleteNote(n);
        }
        System.out.println("Notes in the database deleted");
    }

    public static void wipeQuizAndQuestions(){
        List<Question> lq = db.questionsDAO().getAll();
        List<Quiz> lqz = db.quizDAO().getAll();
        for (Question q: lq) {
            db.questionsDAO().deleteQuestion(q);
        }
        System.out.println("Questions in the database deleted");
        for (Quiz qz: lqz) {
            db.quizDAO().deleteQuiz(qz);
        }
        System.out.println("Quizzes in the database deleted");
    }

    public static void importMockData(){
        boolean nextStatus = false;
        for (int i = 0; i < 14; i++){
            int date = fakeDate + i;
            Note n = new Note(date,"Mock Note #" + i);
            db.notesDAO().addNote(n);
            Random rand = new Random();
            Goal g = new Goal("Do " + rand.nextInt(100) + " things");
            g.status = nextStatus;
            nextStatus = !nextStatus;
            db.goalsDAO().addGoal(g);

            int fakedate2 = fakeDate + i;
            Quiz quiz = new Quiz(fakedate2);
            quiz.hadAnxiety = nextStatus;
            quiz.betterTomorrow = !nextStatus;
            quiz.wasSatisfied = nextStatus;
            quiz.dayRating = rand.nextInt(10);
            db.quizDAO().addQuiz(quiz);

            Question q = new Question("Question here", rand.nextInt(9));
            q.quizId = quiz.id;
            db.questionsDAO().addQuestion(q);
            Question q2 = new Question("Question #2 here", rand.nextInt(7));
            q2.quizId = quiz.id;
            db.questionsDAO().addQuestion(q2);
            Question q3 = new Question("Question #3 here", rand.nextInt(5));
            q3.quizId = quiz.id;
            db.questionsDAO().addQuestion(q3);
        }
        System.out.println("Mock Data Added successfully");
    }

    public static void wipeAllData(){
        Database.wipeGoals();
        Database.wipeNotes();
        Database.wipeQuizAndQuestions();
    }

}
