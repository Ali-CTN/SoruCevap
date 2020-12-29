package com.example.SinavSavaslari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.SinavSavaslari.Common.Common;
import com.example.SinavSavaslari.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collection;
import java.util.Collections;

public class Start extends AppCompatActivity {

    Button btnPlay;

    FirebaseDatabase database;
    DatabaseReference questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        database =FirebaseDatabase.getInstance();
        questions = database.getReference("Sorular");

        loadQuestion(Common.categoryId);

        btnPlay = findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Start.this,Playing.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void loadQuestion(String categoryId) {

        // İlk önce listede eski soru kalmışsa onu temizle

        if (Common.questionList.size()>0) Common.questionList.clear();

        questions.orderByChild("KategoriId").equalTo(categoryId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot:dataSnapshot.getChildren()){

                    Question ques=postSnapshot.getValue(Question.class);
                    Common.questionList.add(ques);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Rasgele liste

        Collections.shuffle(Common.questionList);

    }
}
