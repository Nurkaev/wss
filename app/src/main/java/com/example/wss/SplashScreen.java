package com.example.wss;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {
    //Приветственный экран
    //09.03.2024
    //Нуркаев Альфир Х.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = null;
                if (isFirst())
                    i = new Intent(SplashScreen.this, Onboarding.class);
                else
                    i = new Intent(SplashScreen.this, Holder.class);
                startActivity(i);
                finish();
            }
        }, 400);
    }

    public boolean isFirst() {
        //Проверка на то, была ли нажата кнопка "skip" когда-либо
        SharedPreferences sp = getSharedPreferences("wsr", MODE_PRIVATE);
        return !sp.contains("is");
    }
}