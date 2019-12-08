package com.example.planningpokeradmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuestionActivity extends AppCompatActivity {

    EditText editTextQuestion;
    Button buttonSend;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference myRef;

    String question;
    String code;


    static String items[];

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
                myRef.child(code).child(question).setValue("");
            }
        });

        myRef.child("123").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String stringQuestion = dataSnapshot.getValue().toString();
                String stringQu = stringQuestion.replace("{"," ");
                String stringQu1 = stringQu.replace("}","");
                String stringQu2 = stringQu1.replace("=","");
                items = stringQu2.split(",");
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,items);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("kerdes",databaseError.toString());
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),items[position],Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initialize() {
        editTextQuestion= findViewById(R.id.editTextQuestion);
        buttonSend = findViewById(R.id.buttonSend);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Admin");

        listView = findViewById(R.id.listQuestions);

    }

}
