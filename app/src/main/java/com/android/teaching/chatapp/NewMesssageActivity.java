package com.android.teaching.chatapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class NewMesssageActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText textEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_messsage);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        usernameEditText = findViewById(R.id.username);
        textEditText = findViewById(R.id.chat_message);
    }

    public void onClick(View view){
        ChatMessage model = new ChatMessage();
        model.setText(textEditText.getText().toString());
        model.setUsername(usernameEditText.getText().toString());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("messages");
        String id = databaseReference.push().getKey();
        databaseReference.child(id).setValue(model);

        finish();
    }

}
