package com.example.SinavSavaslari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.SinavSavaslari.Common.Common;
import com.example.SinavSavaslari.Model.QuestionScore;
import com.example.SinavSavaslari.Model.Ranking;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class done extends AppCompatActivity {

    Button btnTryAgain;
    TextView getTxtResultQuestion,txtResultScore;
    ProgressBar progressBar;


    FirebaseDatabase database;
    DatabaseReference question_score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        database = FirebaseDatabase.getInstance();
        question_score = database.getReference("Soru_Puan");

        txtResultScore = findViewById(R.id.txtTotalScore);
        getTxtResultQuestion = findViewById(R.id.txtTotalQuestion);
        progressBar=findViewById(R.id.doneProgressBar);
        btnTryAgain = findViewById(R.id.btnTryAgain);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(done.this,Home.class);
                startActivity(intent);
                finish();
            }
        });

        //Bundle'dan veriyi al ve View'e yerleştir

        Bundle extra = getIntent().getExtras();
        if (extra != null){

            int score = extra.getInt("PUAN");
            int totalQuestion = extra.getInt("TOPLAM");
            int correctAnswer = extra.getInt("DOĞRU");

            txtResultScore.setText(String.format("PUAN : %d",score));
            getTxtResultQuestion.setText(String.format("DOĞRU : %d/%d",correctAnswer,totalQuestion));

            progressBar.setMax(totalQuestion);
            progressBar.setProgress(correctAnswer);

            //Puanları DB'ye yükleme
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference().child("");
            question_score.child(String.format("%s_%s", Common.currentUser.getUserName(),Common.categoryId)).setValue(new QuestionScore(String.format("%s_%s",
                                                                                  Common.currentUser.getUserName(), Common.categoryId),
                                                                                    Common.currentUser.getUserName(),String.valueOf(score+score),Common.categoryId,
                                                                                      Common.categoryName));

           /* question_score.child(String.format("%s_%s", Common.currentUser.getUserName(),Common.categoryId)).setValue(new QuestionScore(String.format("%s_%s",
                    Common.currentUser.getUserName(), Common.categoryId),
                    Common.currentUser.getUserName(),String.valueOf(score),Common.categoryId,
                    Common.categoryName));  */

        }


    }
}
