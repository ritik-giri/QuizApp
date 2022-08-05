package com.example.quizx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<ModelClass> ListOfQuestions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListOfQuestions=new ArrayList<>();
        ListOfQuestions.add(new ModelClass("How many colors are there in Indian Flag?","2","3","4","5","4"));
        ListOfQuestions.add(new ModelClass("How many states in India?","29","28","27","30","29"));
        ListOfQuestions.add(new ModelClass("How many continents in the World?","5","6","7","8","7"));
        ListOfQuestions.add(new ModelClass("How many oceans in the World?","2","3","4","5","5"));
        ListOfQuestions.add(new ModelClass("How many planets in the Solar System?","8","7","6","5","8"));

    }

    public void Start(View view) {
        Intent intent=new Intent(MainActivity.this,DashboardActivity.class);
        startActivity(intent);
    }
}