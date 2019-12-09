package com.example.planningpokeradmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTextCode;
    Button buttonCreate;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_main);

        initialize();
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = editTextCode.getText().toString();

                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                intent.putExtra("codeString",code);
                startActivity(intent);
            }
        });


    }

    private void initialize() {
        editTextCode = findViewById(R.id.editTextCode);
        buttonCreate = findViewById(R.id.buttonCreate);

    }
}
