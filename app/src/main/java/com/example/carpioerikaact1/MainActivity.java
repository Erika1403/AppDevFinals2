package com.example.carpioerikaact1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int num1 = 5;
    int num2 = 10;
    private FrameLayout frameLayout;

    public void LoadFragment(Fragment fragment){
        frameLayout.removeAllViews();

        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.framelayout, fragment)
                .commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.framelayout);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout, new LogFragment())
                    .commit();
        }
        Log.d("Main Activity", "onCreatecalled");
    }
    @Override
    protected void onStart() {
        super.onStart();
        num1 += 2;
        num2 += 2;
        Log.d("OnStart", num1 + " : " + num2);
        Toast.makeText(MainActivity.this, "Erika B. Carpio | " + num1 + ":" + num2, Toast.LENGTH_LONG).show();

    }
    @Override
    protected void onPause(){
        super.onPause();
        num1 += 2;
        num2 += 2;
        Log.d("OnPause", num1 + " : " + num2);
    }
}