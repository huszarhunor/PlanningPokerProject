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

    String items[] = new String[]{"alma","banan","kivi"};

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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),items[position],Toast.LENGTH_SHORT).show();
            }
        });

        myRef.child(question);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String stringQuestion = dataSnapshot.getValue().toString();
                Log.d("kerdes",stringQuestion);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
