package com.android.teaching.chatapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ChatActivity extends AppCompatActivity {

    private ChatAdapter chatAdapter;
    private ListView chat;
    private ChatFirebaseInteractor chatFirebaseInteractor = new ChatFirebaseInteractor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.chat_menu_main);
        chat = findViewById(R.id.chatList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        chatFirebaseInteractor.getMessages(new ChatInteractorCallback() {
            @Override
            public void onMessagesAvailable() {
                chatAdapter = new ChatAdapter();
                chat.setAdapter(chatAdapter);
                chat.setSelection(chatFirebaseInteractor.getMessages().size());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chat_menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int identity = item.getItemId();
        switch (identity){
            case R.id.add_message:
                Toast.makeText(this,getString(R.string.adding_message),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, NewMesssageActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public class ChatAdapter extends BaseAdapter {

        private ChatMessage chatMessage;
        private String username;
        private String text;

        @Override
        public int getCount() {
            return chatFirebaseInteractor.getMessages().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.view_holder_item,parent,false);

            ((TextView)rowView.findViewById(R.id.userTextView)).setText(chatFirebaseInteractor.getMessages().get(position).getUsername());
            ((TextView)rowView.findViewById(R.id.chatUserTextView)).setText(chatFirebaseInteractor.getMessages().get(position).getText());

            return rowView;
        }
    }
}
