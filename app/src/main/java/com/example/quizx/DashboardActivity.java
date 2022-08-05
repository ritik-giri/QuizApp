package com.example.quizx;

import static com.example.quizx.MainActivity.ListOfQuestions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue=10;
    ProgressBar progressBar;
    List<ModelClass> allQuestionsList;
    ModelClass modelClass;
    int index=0;
    TextView card_question,option_a,option_b,option_c,option_d;
    CardView cardOA,cardOB,cardOC,cardOD;
    int correctCount=0;
    int wrongCount=0;
    LinearLayout nextBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Hooks();

        allQuestionsList=ListOfQuestions;
        Collections.shuffle(allQuestionsList);
        modelClass=ListOfQuestions.get(index);

        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));

        nextBTN.setClickable(true);

        countDownTimer=new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                timerValue=timerValue-1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                Dialog dialog=new Dialog(DashboardActivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog);
                dialog.findViewById(R.id.btn_tryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(DashboardActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        }.start();

        setAllData();

    }

    public void setAllData() {
        card_question.setText(modelClass.getQuestion());
        option_a.setText(modelClass.getoA());
        option_b.setText(modelClass.getoB());
        option_c.setText(modelClass.getoC());
        option_d.setText(modelClass.getoD());

        timerValue=10;
        countDownTimer.cancel();
        countDownTimer.start();
    }

    public void Hooks() {
        progressBar=findViewById(R.id.quiz_timer);
        card_question=findViewById(R.id.qa_question);
        option_a=findViewById(R.id.qa_option1);
        option_b=findViewById(R.id.qa_option2);
        option_c=findViewById(R.id.qa_option3);
        option_d=findViewById(R.id.qa_option4);

        cardOA=findViewById(R.id.cardA);
        cardOB=findViewById(R.id.cardB);
        cardOC=findViewById(R.id.cardC);
        cardOD=findViewById(R.id.cardD);

        nextBTN=findViewById(R.id.nextBTN);

    }
    public void Correct(CardView cardView){

        cardView.setBackgroundColor(getResources().getColor(R.color.green));

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctCount++;

                if(index<ListOfQuestions.size()-1)
                {
                    index++;
                    modelClass=ListOfQuestions.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                }
                else
                {
                    QuizOver();
                }
            }
        });

    }

    public void Wrong(CardView cardView){

        cardView.setBackgroundColor(getResources().getColor(R.color.red));

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrongCount++;

                if(index<ListOfQuestions.size()-1)
                {
                    index++;
                    modelClass=ListOfQuestions.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                }
                else
                {
                    QuizOver();
                }

            }
        });

    }

    public void QuizOver() {
        Intent intent=new Intent(DashboardActivity.this,QuizOverActivity.class);
        intent.putExtra("Correct", correctCount);
        intent.putExtra("Wrong", wrongCount);
        startActivity(intent);
    }

    public void enableButton(){
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);
    }

    public void disableButton(){
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);
    }

    public void resetColor(){
        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void optionAClick(View view){
        disableButton();
        nextBTN.setClickable(true);
        if(modelClass.getoA().equals(modelClass.getAnswer())){
            cardOA.setBackgroundColor(getResources().getColor(R.color.green));
            if(index<ListOfQuestions.size())
            {
                Correct(cardOA);
            }
            else
            {
                QuizOver();
            }
        }

        else
        {
            Wrong(cardOA);
        }
    }

    public void optionBClick(View view) {
        disableButton();
        nextBTN.setClickable(true);
        if(modelClass.getoB().equals(modelClass.getAnswer())){
            cardOB.setBackgroundColor(getResources().getColor(R.color.green));
            if(index<ListOfQuestions.size())
            {
                Correct(cardOB);
            }
            else
            {
                QuizOver();
            }
        }

        else
        {
            Wrong(cardOB);
        }
    }

    public void optionCClick(View view) {
        disableButton();
        nextBTN.setClickable(true);
        if(modelClass.getoC().equals(modelClass.getAnswer())){
            cardOC.setBackgroundColor(getResources().getColor(R.color.green));
            if(index<ListOfQuestions.size())
            {
                Correct(cardOC);
            }
            else
            {
                QuizOver();
            }
        }

        else
        {
            Wrong(cardOC);
        }
    }

    public void optionDClick(View view) {
        disableButton();
        nextBTN.setClickable(true);
        if(modelClass.getoD().equals(modelClass.getAnswer())){
            cardOD.setBackgroundColor(getResources().getColor(R.color.green));
            if(index<ListOfQuestions.size())
            {
                Correct(cardOD);
            }
            else
            {
                QuizOver();
            }
        }

        else
        {
            Wrong(cardOD);
        }
    }

    public void clickBack(View view) {
        Intent intent=new Intent(DashboardActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void clickExit(View view) {
        System.exit(0);
    }
}