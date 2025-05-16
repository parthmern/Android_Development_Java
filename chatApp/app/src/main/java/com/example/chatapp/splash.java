package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class splash extends AppCompatActivity {

    TextView LOGONAME, FOOTERTXT;
    Animation topAni, bottomAni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        LOGONAME = findViewById(R.id.splash_logoNameImg);
        FOOTERTXT = findViewById(R.id.splash_footerText);

        topAni = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAni = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        LOGONAME.setAnimation(topAni);
        FOOTERTXT.setAnimation(bottomAni);

        // Add a delay before switching
        new Handler().postDelayed(() -> {
            startActivity(new Intent(splash.this, registration.class));
            finish();
        }, 2000); // 2 seconds






    }
}
