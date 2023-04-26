package com.example.feelingfinder;

<<<<<<< HEAD:app/src/main/java/com/example/feelingfinder/NotificationTappedActivity.java
import androidx.appcompat.app.AppCompatActivity;
=======
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.feelingfinder.databinding.ActivityMainBinding;
import com.ramotion.circlemenu.CircleMenuView;
>>>>>>> FirstDemoUI:app/src/main/java/com/example/feelingfinder/MainActivity.java

import android.os.Bundle;

// Activity that appears when you click the notification
public class NotificationTappedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD:app/src/main/java/com/example/feelingfinder/NotificationTappedActivity.java
        setContentView(R.layout.activity_notification_tapped);
    }
}
=======

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        final CircleMenuView menu = findViewById(R.id.circle_menu);
        menu.setEventListener(new CircleMenuView.EventListener(){
            @Override
            public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {
                Log.d("D","onMenuOpenAnimationStart");
            }

            @Override
            public void onMenuOpenAnimationEnd(@NonNull CircleMenuView view) {
                Log.d("D","onMenuOpeneAnimationEnd");
            }

            @Override
            public void onMenuCloseAnimationStart(@NonNull CircleMenuView view) {
                Log.d("D","onMenuCloseAnimationStart");
            }

            @Override
            public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view) {
                Log.d("D","onMenuCloseAnimationEnd");
            }

            @Override
            public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int buttonIndex) {
                Log.d("D","onButtonClickAnimationStart|index: "+buttonIndex);
            }

            @Override
            public void onButtonClickAnimationEnd(@NonNull CircleMenuView view, int buttonIndex) {
                Log.d("D","onButtonClickAnimationEnd|index: "+buttonIndex);

                // Start a new activity when a button is clicked
                Intent intent;
                switch (buttonIndex) {
                    case 0:
                        intent = new Intent(MainActivity.this, QuizActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;

                    case 1:
                        intent = new Intent(MainActivity.this, QuizActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, QuizActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, QuizActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, QuizActivity.class);
                        startActivity(intent);
                        break;
                }
            }

            @Override
            public boolean onButtonLongClick(@NonNull CircleMenuView view, int buttonIndex) {
                Log.d("D","onButtonLongClick|index: "+buttonIndex);
                return true;
            }


        });
    }
}
>>>>>>> FirstDemoUI:app/src/main/java/com/example/feelingfinder/MainActivity.java
