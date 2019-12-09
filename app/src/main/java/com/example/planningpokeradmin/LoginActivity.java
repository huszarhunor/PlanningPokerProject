package com.example.planningpokeradmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText editTextCode;
    Button buttonEnter;
    Button buttonRegistration;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = editTextCode.getText().toString();
                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                intent.putExtra("codeString",code);
                startActivity(intent);
            }
        });

        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initialize() {
        editTextCode = findViewById(R.id.editTextCodeEnter);
        buttonEnter = findViewById(R.id.buttonRoomEnter);
        buttonRegistration = findViewById(R.id.buttonGoToRegistration);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

    }
}
