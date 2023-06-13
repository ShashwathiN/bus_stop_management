package com.example.internship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class availableroute extends AppCompatActivity {
    ListView listView;
    static ArrayList<String> listItems,listItems1;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availableroute);
        listView = (ListView) findViewById(R.id.listview);
        listItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference().child("routes");
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), routelist.class);
                Integer y=new Integer(listItems.get(i));
                intent.putExtra("noteID",y);            //to tell us which row of listView was tapped
                startActivity(intent);
            }
        });
    }
}