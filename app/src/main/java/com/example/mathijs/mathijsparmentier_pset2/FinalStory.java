package com.example.mathijs.mathijsparmentier_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinalStory extends AppCompatActivity {

    TextView storyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_story);

        Intent intent = getIntent();
        Story story = (Story) intent.getSerializableExtra("story");

        storyTextView = (TextView) findViewById(R.id.storyText);
        storyTextView.setText(story.toString());
    }

    @Override
    public void onBackPressed() { // https://stackoverflow.com/a/27129558
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String inputTextValue = storyTextView.getText().toString();
        outState.putString("storyText", inputTextValue);
    }

    @Override
    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);

        storyTextView.setText(inState.getString("storyText"));
    }

}
