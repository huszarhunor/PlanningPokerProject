package com.example.planningpokeradmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuestionActivity extends AppCompatActivity {

    EditText editTextQuestion;
    Button buttonSend;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String question;
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        initialize();

        code = getIntent().getStringExtra("codeString");
        Log.d("semmi",code);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question = editTextQuestion.getText().toString();
                myRef.child(code).child(question).child("1").setValue("");
                myRef.child(code).child(question).child("2").setValue("");
                myRef.child(code).child(question).child("3").setValue("");
                myRef.child(code).child(question).child("4").setValue("");
                myRef.child(code).child(question).child("5").setValue("");

            }
        });
    }

    private void initialize() {
        editTextQuestion= findViewById(R.id.editTextQuestion);
        buttonSend = findViewById(R.id.buttonSend);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Admin");

    }

}
