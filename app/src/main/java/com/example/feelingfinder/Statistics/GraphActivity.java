package com.example.feelingfinder.Statistics;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.feelingfinder.Database.AppDatabase;
import com.example.feelingfinder.Database.Database;
import com.example.feelingfinder.Database.QuestionsDAO;
import com.example.feelingfinder.Database.Quiz;
import com.example.feelingfinder.Database.QuizDAO;
import com.example.feelingfinder.R;
import com.example.feelingfinder.Statistics.Fragments.DailyRatingFragment;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

public class GraphActivity extends FragmentActivity {
    private int NUM_PAGES = 2;
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new GraphActivityAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        System.out.println((DailyRatingFragment) getSupportFragmentManager().findFragmentByTag("f" + viewPager.getCurrentItem()));
        switch (viewPager.getCurrentItem()){
            case 1:
                DailyRatingFragment fragment = (DailyRatingFragment) getSupportFragmentManager().findFragmentByTag("f" + viewPager.getCurrentItem());
                fragment.setData();
            case 2:
                DailyRatingFragment fragment2 = (DailyRatingFragment) getSupportFragmentManager().findFragmentByTag("f" + viewPager.getCurrentItem());
                fragment2.setData();
            default:
                System.out.println("Nope: " + viewPager.getCurrentItem());
        }

    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private class GraphActivityAdapter extends FragmentStateAdapter {
        private Fragment currentFragment;
        public Fragment getCurrentFragment() {
            return currentFragment;
        }

        public GraphActivityAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            return new DailyRatingFragment();
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }


}