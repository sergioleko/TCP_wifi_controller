package ru.linkos.tcp_wifi_controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class startErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_error);
         Intent intent = getIntent();
         String errCode = intent.getStringExtra(WiFiOperations.EXTRA_MESSAGE_ERR_NO);
         Log.i("Err number: ", errCode);
        TextView errorText = findViewById(R.id.errorText);
        errorText.setText(errCode);


    }
    public void toStart (View view){
        Intent errorIntent = new Intent(this, start_window.class);
        startActivity(errorIntent);
    }

}
