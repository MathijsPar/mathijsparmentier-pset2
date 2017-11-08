package com.example.mathijs.mathijsparmentier_pset2;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Random;

public class FillingIn extends AppCompatActivity {

    Story story;

    EditText inputTextView;
    TextView textHintView;
    TextView wordCountView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filling_in);

        inputTextView = (EditText) findViewById(R.id.inputText);
        textHintView = (TextView) findViewById(R.id.textHint);
        wordCountView = (TextView) findViewById(R.id.wordCount);

        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("story");

        textHintView.setText(story.getNextPlaceholder());
        setTextHint();
    }

    public void enterWord(View view) {
        String inputWord = inputTextView.getText().toString();
        story.fillInPlaceholder(inputWord);

        // Set the text view to empty and get a new hint
        inputTextView.setText("");
        setTextHint();
        NewHint();

    }

    private void setTextHint() {
        if (story.getPlaceholderRemainingCount() > 1) {
            wordCountView.setText(story.getPlaceholderRemainingCount() + " words left");
        } else {
            wordCountView.setText("1 word left");
        }
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
        intent.putExtra("story", story);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String inputTextValue = inputTextView.getText().toString();
        outState.putString("inputText", inputTextValue);
    }

    @Override
    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);

        inputTextView.setText(inState.getString("inputText"));
    }
}
