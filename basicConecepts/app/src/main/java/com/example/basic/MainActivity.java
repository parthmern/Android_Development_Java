package com.example.basic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.d("test", "this is testing msg");
        button = findViewById(R.id.submit_button);
        // u can also change color/text of this btn
        button.setText("Submit Btn");

        // attach event listener

        View.OnClickListener viewListener = (view) ->{
            // here code goes
            Toast.makeText(this, "btn clicked", Toast.LENGTH_SHORT).show();
        };

        button.setOnClickListener((view) ->{
            // here code goes
            Toast.makeText(this, "btn clicked", Toast.LENGTH_SHORT).show();
        });
    }





}