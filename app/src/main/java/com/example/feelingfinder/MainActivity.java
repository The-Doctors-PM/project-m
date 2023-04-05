package com.example.feelingfinder;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.feelingfinder.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        TextView welcomeString, timerString;
        welcomeString = binding.textView;
        welcomeString.setText("Welcome to \"Feeling Finder\"");
        welcomeString.setTextSize(24);

        timerString = binding.timerTextView;
        CountDownTimer cdt = new CountDownTimer(3000, 1){
            @Override
            public void onTick(long l) {
                timerString.setText(" " + (3000-l) + " ");
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(FeelingFinder.getAppContext(), NotificationActivity.class));
            }
        };

        cdt.start();
    }

}