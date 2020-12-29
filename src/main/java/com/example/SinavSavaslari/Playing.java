package com.example.SinavSavaslari;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.SinavSavaslari.Common.Common;

import com.squareup.picasso.Picasso;

import java.util.Collections;


public class Playing extends AppCompatActivity implements View.OnClickListener {

    final static long INTERVAL = 1000; //1 saniye
    final static long TIMEOUT = 25000; //25 saniye
    int progressValue=0;

    CountDownTimer mCountDown;

    int index=0,score=0,thisQuestion=0,totalQuestion,correctAnswer=0;



    ProgressBar progressBar;
    ImageView question_image;
    Button btnA,btnB,btnC,btnD;
    TextView txtScore,txtQuestionNum,question_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        Common.shuffleList();



        //Görüntüler

        txtScore = (TextView)findViewById(R.id.txtScore);
        txtQuestionNum = (TextView)findViewById(R.id.txtTotalQuestion);
        question_text = (TextView)findViewById(R.id.question_text);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        question_image = (ImageView)findViewById(R.id.question_image);

        btnA = findViewById(R.id.btnAnswerA);
        btnB = findViewById(R.id.btnAnswerB);
        btnC = findViewById(R.id.btnAnswerC);
        btnD = findViewById(R.id.btnAnswerD);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);



    }


    @Override
    public void onClick(View view){

        mCountDown.cancel();

        if (index<totalQuestion){                     //Hala listede sorulara sahipse

            Button clickedButton = (Button)view;
            if (clickedButton.getText().equals(Common.questionList.get(index).getDogruCevap())){
                //Doğru cevap için
                score +=10;
                correctAnswer ++;
                showQuestion(++index); //Bir sonraki soru
            }

            else {
                //Yanlış cevap için
                showQuestion(++index);
            }

            txtScore.setText(String.format("Puan:%d",score));

        }

    }


    private void showQuestion(int index) {


        if (index<totalQuestion){

            thisQuestion++;
            txtQuestionNum.setText(String.format("Soru:%d / %d",thisQuestion,totalQuestion));
            progressBar.setProgress(0);
            progressValue=0;



                Picasso.with(getBaseContext()).load(Common.questionList.get(index).getResim()).into(question_image);
                question_text.setText(Common.questionList.get(index).getSoru());


            btnA.setText(Common.questionList.get(index).getCevapA());
            btnB.setText(Common.questionList.get(index).getCevapB());
            btnC.setText(Common.questionList.get(index).getCevapC());
            btnD.setText(Common.questionList.get(index).getCevapD());

            mCountDown.start(); //Sayacı başlat
        }
        else {

            //Eğer son soru ise

            Intent intent= new Intent(this,done.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("PUAN",score);
            dataSend.putInt("TOPLAM",totalQuestion);
            dataSend.putInt("DOĞRU",correctAnswer);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Soru sayısı buradan ayarlanıyor
        //totalQuestion=Common.questionList.size();
        totalQuestion = 10;

        mCountDown = new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long minisec) {

                progressBar.setProgress(progressValue);
                progressValue++;

            }

            @Override
            public void onFinish() {

                mCountDown.cancel();
                showQuestion(++index);

            }
        };
       showQuestion(index);
    }
}
