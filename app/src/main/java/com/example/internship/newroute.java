package com.example.internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class newroute extends AppCompatActivity {
ImageView i;
Button b1,b2;
TextInputEditText e1,e2;
FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newroute);
        i=findViewById(R.id.back);
        b1=findViewById(R.id.save);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        db=FirebaseDatabase.getInstance("https://internship-3b21f-default-rtdb.asia-southeast1.firebasedatabase.app/");
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.getText().toString().isEmpty()&&e2.getText().toString().isEmpty()) {
                    Toast.makeText(newroute.this, "Please enter something", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Integer i= new Integer(e1.getText().toString());
                    user u=new user(e2.getText().toString());
                    db.getReference().child("routes").child(""+i).child(e2.getText().toString()).setValue(u);
                    Toast.makeText(newroute.this, "data added successfully", Toast.LENGTH_SHORT).show();

                }

            }
        });
        b2=findViewById(R.id.clear);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setText("");
                e2.setText("");
            }
        });
    }
}