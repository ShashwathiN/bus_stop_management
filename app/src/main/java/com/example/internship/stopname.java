package com.example.internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class stopname extends AppCompatActivity {
ImageView i;
Button b;
TextView t;
int y;

TextInputEditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopname);
        i=findViewById(R.id.back);
        b=findViewById(R.id.search);
        e1=findViewById(R.id.et1);
        e2=findViewById(R.id.et2);
        t=findViewById(R.id.text);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText("");
                DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference().child("routes");
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            if (ds.hasChild(e1.getText().toString()) && ds.hasChild(e2.getText().toString())) {
                                t.setText(ds.getKey());
                                Toast.makeText(stopname.this, "Results available", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("TAG", databaseError.getMessage()); //Don't ignore potential errors!
                    }
                };
                itemsRef.addListenerForSingleValueEvent(eventListener);

            }
        });
    }
}