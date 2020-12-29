package com.example.SinavSavaslari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.SinavSavaslari.Model.Category;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment= CategoryFragment.newInstence();
                switch (item.getItemId()){
                    case R.id.action_category:
                        selectedFragment = CategoryFragment.newInstence();
                        break;
                    case R.id.action_ranking:
                        selectedFragment = RankingFragment.newInstence();
                        break;
                    //case R.id.action_profile:
                      //  selectedFragment = .newInstence();
                      //  break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,selectedFragment);
                transaction.commit();

                return true;
            }




        });

        setDefaultFragment();
    }

    private void setDefaultFragment() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,new CategoryFragment());
        transaction.commit();

    }
}
