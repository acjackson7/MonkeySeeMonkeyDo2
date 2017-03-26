package com.example.autumn.monkeyseemonkeydo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);}
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            String text = parent.getItemAtPosition(pos).toString();
            Intent nextScreen = new Intent(view.getContext(),MainActivity.class);
            nextScreen.putExtra("sign", text);
            startActivity(nextScreen);
        }
    public void onNothingSelected(AdapterView<?> parent){
        System.out.println("Nothing selected.");
    }

}
