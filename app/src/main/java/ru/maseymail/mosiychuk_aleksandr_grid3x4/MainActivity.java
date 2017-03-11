package ru.maseymail.mosiychuk_aleksandr_grid3x4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button appActivity;
    private Button dialerActivity;
    private Button smsActivity;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appActivity = (Button) findViewById(R.id.App);
        dialerActivity = (Button) findViewById(R.id.dialer);
        smsActivity = (Button) findViewById(R.id.sms);

        appActivity.setOnClickListener(this);
        dialerActivity.setOnClickListener(this);
        smsActivity.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.App:
                intent = new Intent(MainActivity.this, AppActivity.class);
                break;
            case R.id.dialer:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"1234"));
                break;
            case R.id.sms:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + "1234"));
                break;
        }
        startActivity(intent);
    }

}




