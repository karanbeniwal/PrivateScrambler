package com.littleindia18.privatescrambler;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class MyActivity extends ActionBarActivity {

    public static final String EXTRA_MESSAGE = "com.littleindia18.privatescrambler.MESSAGE";
    public static final String EXTRA_TOENC = "com.littleindia18.privatescrambler.TOENC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            } else if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendMultipleImages(intent); // Handle multiple images being sent
            }
        } /*else {
            // Handle other intents, such as being started from the home screen
        }*/

        final EditText editTextDec = (EditText) findViewById(R.id.edit_message);
        final EditText editTextEnc = (EditText) findViewById(R.id.editMsgMain);
        TextWatcher watcher= new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (editTextEnc.getText().toString().equals("")) {
                    editTextEnc.setBackgroundColor(getResources().getColor(android.R.color.white));
                    editTextEnc.setTextColor(getResources().getColor(android.R.color.black));
                }

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Do something or nothing.
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Do something or nothing
                editTextDec.setText(decAll(s.toString()));
            }
        };

        editTextEnc.addTextChangedListener(watcher);
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Update UI to reflect text being shared
            TextView textView = (TextView) findViewById(R.id.edit_message);
            textView.setText(decAll(sharedText));
        }
    }

    void handleSendImage(Intent intent) {
        Uri imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        /*if (imageUri != null) {
            // Update UI to reflect image being shared
        }*/
    }

    void handleSendMultipleImages(Intent intent) {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        /*if (imageUris != null) {
            // Update UI to reflect multiple images being shared
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;*/

        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

        /*/ Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                //openSearch();
                return true;
            case R.id.action_settings:
                //openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }*/
    }

    public void shareMessageEnc(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, this.encAll(message));
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
        /*
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, this.encAll(message));
        intent.putExtra(EXTRA_TOENC, true);
        startActivity(intent);*/
    }
/*
    public void rcvMessageDec(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_TOENC, false);
        startActivity(intent);
    }*/

    /**
     * Called when the user clicks the Send button
     */
    public void sendMessageEnc(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_TOENC, true);
        startActivity(intent);
    }

    public void sendMessageDec(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_TOENC, false);
        startActivity(intent);
    }

    public String encAll(String inp){
        int i;
        String out;
        out = "";
        for(i=0;i<inp.length();i++){
            out = out + this.encK(inp.substring(i, i + 1));
        }
        return out;
    }

    public String decAll(String inp){
        int i;
        String out;
        out = "";
        for(i=0;i<inp.length();i++){
            out = out + this.decK(inp.substring(i,i+1));
        }
        return out;
    }

    public String encK(String inp){

        if(inp.equals("z")){
            return "x";
        }

        if(inp.equals("a")){
            return "s";
        }

        if(inp.equals("q")){
            return "w";
        }

        inp = inp.replace("m","z");
        inp = inp.replace("n","m");
        inp = inp.replace("b","n");
        inp = inp.replace("v","b");
        inp = inp.replace("c","v");
        inp = inp.replace("x","c");

        inp = inp.replace("l","a");
        inp = inp.replace("k","l");
        inp = inp.replace("j","k");
        inp = inp.replace("h","j");
        inp = inp.replace("g","h");
        inp = inp.replace("f","g");
        inp = inp.replace("d","f");
        inp = inp.replace("s","d");

        inp = inp.replace("p","q");
        inp = inp.replace("o","p");
        inp = inp.replace("i","o");
        inp = inp.replace("u","i");
        inp = inp.replace("y","u");
        inp = inp.replace("t","y");
        inp = inp.replace("r","t");
        inp = inp.replace("e","r");
        inp = inp.replace("w","e");

        return inp;
    }

    public String decK(String inp){

        if(inp.equals("z")){
            return "m";
        }

        if(inp.equals("a")){
            return "l";
        }

        if(inp.equals("q")){
            return "p";
        }

        inp = inp.replace("w","q");
        inp = inp.replace("e","w");
        inp = inp.replace("r","e");
        inp = inp.replace("t","r");
        inp = inp.replace("y","t");
        inp = inp.replace("u","y");
        inp = inp.replace("i","u");
        inp = inp.replace("o","i");
        inp = inp.replace("p","o");

        inp = inp.replace("s","a");
        inp = inp.replace("d","s");
        inp = inp.replace("f","d");
        inp = inp.replace("g","f");
        inp = inp.replace("h","g");
        inp = inp.replace("j","h");
        inp = inp.replace("k","j");
        inp = inp.replace("l","k");

        inp = inp.replace("x","z");
        inp = inp.replace("c","x");
        inp = inp.replace("v","c");
        inp = inp.replace("b","v");
        inp = inp.replace("n","b");
        inp = inp.replace("m","n");

        return  inp;
    }

}
