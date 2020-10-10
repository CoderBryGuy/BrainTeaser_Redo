package com.example.brainteaser_redo;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int a, b;
    Random rand = new Random();
    TextView sumTextView, resultTextView, scoreTextView, timerTextView;
    List<Integer> answers = new ArrayList<>();
    Button goBtn, btn0, btn1, btn2, btn3, playAgainBtn;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    ConstraintLayout gameGridView;

    public void start(View view){
        goBtn.setVisibility(View.INVISIBLE);
        gameGridView.setVisibility(View.VISIBLE);
        playAgain(timerTextView);

    }

    public void playAgain(View view){
        playAgainBtn.setVisibility(View.INVISIBLE);
        score = 0;
        numberOfQuestions = 0;
        resultTextView.setText("");
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        newQuestion();

        new CountDownTimer(30 * 1000 + 100, 1000){
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!!");
                playAgainBtn.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void newQuestion(){
        a = rand.nextInt(21);
        b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for (int i = 0; i < 4; i++) {

            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {

                int wrongAnswer;

                do {
                    wrongAnswer = rand.nextInt(41);
                } while (wrongAnswer == (a + b));

                answers.add(wrongAnswer);
            }
        }

        btn0.setText(Integer.toString(answers.get(0)));
        btn1.setText(Integer.toString(answers.get(1)));
        btn2.setText(Integer.toString(answers.get(2)));
        btn3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view) {
        Log.i("TAG: ", view.getTag().toString());

        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){


            Log.i("Correct!: ", "You got it");
            resultTextView.setText("Correct!!");
            score++;
        }else {
            Log.i("Wrong :( !: ", "You didn't get it");
            resultTextView.setText("Wrong :( !!");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        newQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAgainBtn = (Button)findViewById(R.id.playAgainBtn);
        goBtn = (Button)findViewById(R.id.goBtn);
        goBtn.setVisibility(View.VISIBLE);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        btn0 = (Button)findViewById(R.id.button0);
        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);

        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        gameGridView = findViewById(R.id.gameLayout);

//        newQuestion();
   }
}