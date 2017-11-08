package com.example.mathijs.mathijsparmentier_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int[] stories = {R.raw.madlib0_simple, R.raw.madlib1_tarzan,
                R.raw.madlib2_university, R.raw.madlib3_clothes, R.raw.madlib4_dance};
        Random random = new Random();
        int storyName = stories[random.nextInt(stories.length)];

        InputStream is = getResources().openRawResource(storyName);
        story = new Story(is);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, FillingIn.class);
        intent.putExtra("story", story);
        startActivity(intent);
    }
}
