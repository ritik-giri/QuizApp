package com.example.quizx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class QuizOverActivity extends AppCompatActivity {

    ProgressBar circularProgressBar;
    TextView resultText;
    
    int correct,wrong;
    LinearLayout shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_over);

        correct=getIntent().getIntExtra("Correct", 0);
        wrong=getIntent().getIntExtra("Wrong", 0);

        circularProgressBar=findViewById(R.id.circularProgressBar);
        resultText=findViewById(R.id.result_text);
        shareBtn=findViewById(R.id.shareBtn);

        circularProgressBar.setMax(correct+wrong);
        circularProgressBar.setProgress(correct);

        resultText.setText(correct+"/5");

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage= "\nI got "+correct+" questions correct out of 5. You can also try\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });
    }
}
