package com.eos.eosfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


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

        //Initialize database reference
        mDatabase = FirebaseDatabase.getInstance().getReference();

        buttonAdd = (Button)findViewById(R.id.buttonAdd);
        editTextContact = (EditText)findViewById(R.id.editTextContact);
        editTextName = (EditText)findViewById(R.id.editTextName);

        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });
    }

    private void submitData(){
        final String name = editTextName.getText().toString();
        final String contact = editTextContact.getText().toString();

        //Name is required
        if(TextUtils.isEmpty(name)){
            editTextName.setError(REQUIRED);
            return;
        }

        //Contact is required
        if(TextUtils.isEmpty(contact)){
            editTextContact.setError(REQUIRED);
            return;
        }

        //TODO 이거해야됨 ㅠㅠ
    }
}
