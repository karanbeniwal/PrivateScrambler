package com.littleindia18.privatescrambler;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DisplayMessageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_display_message);
        // Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MyActivity.EXTRA_MESSAGE);
        Boolean toenc = intent.getBooleanExtra(MyActivity.EXTRA_TOENC, true);

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        if(toenc) {
            textView.setText(this.encAll(message));
        } else {
            textView.setText(this.decAll(message));
        }

        // Set the text view as the activity layout
        setContentView(textView);
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
