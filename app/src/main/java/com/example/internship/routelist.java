package com.example.internship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class routelist extends AppCompatActivity {
    ImageView i;
    ListView l;
    static ArrayList<String> listItems;
    ArrayAdapter<String> adapter;

    int noteID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routelist);
        l=findViewById(R.id.listview1);
        i=findViewById(R.id.back);
        Intent intent = getIntent();
        listItems = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listItems);
        l.setAdapter(adapter);
        noteID = intent.getIntExtra("noteID",-1);
        Toast.makeText(this, "Showing available stops at route "+noteID, Toast.LENGTH_SHORT).show();
        if (noteID != -1) {
            String s= (String.valueOf(noteID));
            DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference().child("routes").child(s);
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String name = ds.getKey();
                        listItems.add(name);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("TAG", databaseError.getMessage()); //Don't ignore potential errors!

                }
            };
            itemsRef.addListenerForSingleValueEvent(eventListener);

        }

i.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});
    }
}