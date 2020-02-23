package com.zainco.library.firebase.remoteconfig;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.zainco.library.R;

public class RemoteConfigEDMTVActivity extends AppCompatActivity {

    FirebaseRemoteConfig firebaseRemoteConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_config_edmtv);
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder().build();
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        /*Map<String, Object> defaultData = new HashMap<>();
        defaultData.put("buuton3", "Button3");
        defaultData.put("buuton_enable", true);
        defaultData.put("textview", "its me");
        firebaseRemoteConfig.setDefaultsAsync(defaultData);*/
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseRemoteConfig.fetch(0).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            firebaseRemoteConfig.fetchAndActivate();
                            ((Button) findViewById(R.id.button3)).setText(firebaseRemoteConfig.getString("buuton3"));
                            ((Button) findViewById(R.id.button3)).setEnabled(firebaseRemoteConfig.getBoolean("buuton_enable"));
                            ((TextView) findViewById(R.id.textView3)).setText(firebaseRemoteConfig.getString("textview"));
                        } else {
                            Toast.makeText(RemoteConfigEDMTVActivity.this, "zzzzzzzzzzzzzz", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
