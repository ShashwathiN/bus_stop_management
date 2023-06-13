package com.example.internship;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class route extends AppCompatActivity {
    ImageView i;
    SearchView s;
    TextView t;
    ListView listView;
    static ArrayList<String> listItems,listItems1;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        i=findViewById(R.id.back);
        s=findViewById(R.id.search);
        t=findViewById(R.id.t1);
        listView = (ListView) findViewById(R.id.listview);
        listItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        s.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                listItems.clear();
                adapter.notifyDataSetChanged();
                DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference().child("routes").child(s);
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String name = ds.getKey();
                            listItems.add(name);
                            adapter.notifyDataSetChanged();
                        }
                        Toast.makeText(route.this, "Results Available", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("TAG", databaseError.getMessage()); //Don't ignore potential errors!

                    }
                };
                itemsRef.addListenerForSingleValueEvent(eventListener);
            return true ;}

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }
}