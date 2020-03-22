package com.zainco.library.permissions.outgoingcalls;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zainco.library.R;

public class OutgoinCallActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private TextView textView;
    private boolean detecting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outgoin_call);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, CallDetectionService.class);
        if(!detecting){
            detecting=true;
            button.setText("Turn off detection");
            startService(intent);
        }else{
            detecting=false;
            button.setText("Turn on detection");
            stopService(intent);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(CallDetector.MY_PREF,MODE_PRIVATE);
        String number = sharedPreferences.getString(CallDetector.NUMBER_KEY,"No Value Found");
        textView.setText(number);
    }
}
