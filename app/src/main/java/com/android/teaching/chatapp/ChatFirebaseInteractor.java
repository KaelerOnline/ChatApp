package com.android.teaching.chatapp;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatFirebaseInteractor {
    private ArrayList<ChatMessage> messages = new ArrayList<>();

    public ChatFirebaseInteractor(){
    }

    public void getMessages(final ChatInteractorCallback callback){
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference= firebaseDatabase.getReference("messages");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot messagesNode : dataSnapshot.getChildren()){
                    ChatMessage model = messagesNode.getValue(ChatMessage.class);
                    messages.add(model);
                }
                callback.onMessagesAvailable();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public ArrayList<ChatMessage> getMessages(){
        return messages;
    }

}
