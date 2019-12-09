package com.example.planningpokeradmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VoteStatusActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    ListView listStatus;
    String code;

    String items[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_status);

        code = getIntent().getStringExtra("codeString2");

        initialize();

        myRef.child("Aktiv").child(code).child("Szavazatok").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String szavazatok = dataSnapshot.getValue().toString();
                String szavazatok2 = szavazatok.replace("{","");
                String szavazatok3 = szavazatok2.replace("}","");
                String szavazatok4 = szavazatok3.replace("[","");
                String szavazatok5 = szavazatok4.replace("]","");
                String szavazatok6 = szavazatok5.replace("null","");
                Log.d("szavazat",szavazatok6);
                items = szavazatok6.split(",");
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,items);
                listStatus.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

     private void initialize(){
         listStatus = findViewById(R.id.listStatus);

         database = FirebaseDatabase.getInstance();
         myRef = database.getReference();
    }
}
