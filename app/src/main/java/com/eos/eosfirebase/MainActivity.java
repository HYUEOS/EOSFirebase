package com.eos.eosfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.eos.eosfirebase.models.Data;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private static final String REQUIRED = "Required";

    private EditText editTextName;
    private EditText editTextContact;
    private Button buttonAdd;

    //Declare Database Reference
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize database referenceddd
        mDatabase = FirebaseDatabase.getInstance().getReference();

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        editTextContact = (EditText) findViewById(R.id.editTextContact);
        editTextName = (EditText) findViewById(R.id.editTextName);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });
    }

    private void submitData() {
        final String name = editTextName.getText().toString();
        final String contact = editTextContact.getText().toString();

        //Name is required
        if (TextUtils.isEmpty(name)) {
            editTextName.setError(REQUIRED);
            return;
        }

        //Contact is required
        if (TextUtils.isEmpty(contact)) {
            editTextContact.setError(REQUIRED);
            return;
        }

        //Single Value Read
        mDatabase.child("data").child(name).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        writeNewData(name, contact);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("zzoroak", "Data Write Cancelled", databaseError.toException());
                    }
                }
        );
    }

    //Create New Data
    private void writeNewData(String name, String contact) {
        String key = mDatabase.child("users").push().getKey();
        Data data = new Data(name, contact);
        Map<String, Object> values = data.toMap();

        Map<String, Object> dataUpdates = new HashMap<>();
        dataUpdates.put("/data/" + key, values);

        mDatabase.updateChildren(dataUpdates);
    }
}
