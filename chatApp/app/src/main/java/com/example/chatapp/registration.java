package com.example.chatapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class registration extends AppCompatActivity {

    TextView LOGINBTN;

    EditText USERNAME, EMAIL, PASSWORD, REPASSSWORD;

    Button SIGNUP;

    CircleImageView PROFILEIMG;

    FirebaseAuth auth;
    FirebaseDatabase database;


    Uri imageURI;
    String imageuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        USERNAME = findViewById(R.id.rgusername);
        LOGINBTN = findViewById(R.id.rgloginBtn);
        EMAIL = findViewById(R.id.rgemaill);
        PASSWORD = findViewById(R.id.rgpassword);
        REPASSSWORD = findViewById(R.id.rgrepassword);
        SIGNUP = findViewById(R.id.rgsignupbutton);
        PROFILEIMG = findViewById(R.id.rg_profile_image);

        // imp to get instance
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        LOGINBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registration.this, login.class);
                startActivity(intent);
                finish();
            }
        });

        SIGNUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namee = USERNAME.getText().toString();
                String emaill = EMAIL.getText().toString();
                String passwordd = PASSWORD.getText().toString();
                String repasswordd = REPASSSWORD.getText().toString();
                String statuss = "Hey i am using this app";

                if (TextUtils.isEmpty(namee) || TextUtils.isEmpty(emaill) || TextUtils.isEmpty(passwordd) || TextUtils.isEmpty(passwordd) || TextUtils.isEmpty(repasswordd) || TextUtils.isEmpty(statuss)) {
                    Toast.makeText(registration.this, "Please enter valid info", Toast.LENGTH_SHORT).show();
                } else if (!passwordd.equals(repasswordd)) {
                    REPASSSWORD.setError("pass not matching");
                } else {
                    auth.createUserWithEmailAndPassword(emaill, passwordd)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String id = task.getResult().getUser().getUid();
                                        DatabaseReference reference = database.getReference().child("user").child(id);

                                        // Create user object
                                        Users user = new Users();
                                        user.setUserId(id);
                                        user.setUsername(namee); // make sure you have a username variable
                                        user.setMail(emaill);
                                        user.setPassword(passwordd);
                                        user.setStatus("Hey there! I’m using ChatApp");
                                        user.setProfilepic(""); // empty by default
                                        user.setLastMessage(""); // optional

                                        // Save user in Firebase Realtime DB
                                        reference.setValue(user)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(registration.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(registration.this, MainActivity.class);
                                                            startActivity(intent);
                                                            finish();
                                                        } else {
                                                            Toast.makeText(registration.this, "Failed to save user: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });


                                    } else {
                                        Toast.makeText(registration.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        PROFILEIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 10);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (data != null) {
                imageURI = data.getData();
                PROFILEIMG.setImageURI(imageURI);

            }
        }
    }
}