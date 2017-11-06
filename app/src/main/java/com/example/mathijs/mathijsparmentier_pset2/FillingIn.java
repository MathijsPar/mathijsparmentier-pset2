package com.example.mathijs.mathijsparmentier_pset2;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class FillingIn extends AppCompatActivity {

    private InputStream storytext;
    private Story story;

    EditText inputTextView;
    TextView textHintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filling_in);

        EditText inputTextView = (EditText) findViewById(R.id.inputText);
        TextView textHintView = (TextView) findViewById(R.id.textHint);

        //TODO Add randomization
        //final R.raw[] stories = {R.raw.madlib0_simple, R.raw.madlib1_tarzan,
        //R.raw.madlib2_university, R.raw.madlib3_clothes, R.raw.madlib4_dance};
        //Random random = new Random();
        //String storyName = stories[random.nextInt(stories.length)]);

        InputStream is = getResources().openRawResource(R.raw.madlib3_clothes);
        Story story = new Story(is);

        textHintView.setText(story.getNextPlaceholder());
    }

    public void enterWord(View view) {
        String inputWord = inputTextView.getText().toString();
        story.fillInPlaceholder(inputWord);
        NewHint();
    }

    private void NewHint() {
        if(!story.isFilledIn()) {
            textHintView.setText(story.getNextPlaceholder());
        } else {
            finishStory();
        }
    }

    private void finishStory() {
        Intent intent = new Intent(this, FinalStory.class);
        startActivity(intent);

    }
}
