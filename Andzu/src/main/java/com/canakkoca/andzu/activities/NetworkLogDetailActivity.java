package com.canakkoca.andzu.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.canakkoca.andzu.R;
import com.canakkoca.andzu.base.NetworkLog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by can.akkoca on 4/13/2017.
 */
public class NetworkLogDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networklogdetail);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        NetworkLog networkLog = (NetworkLog) getIntent().getSerializableExtra("networkLog");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        TextView date = (TextView) findViewById(R.id.date);
        TextView url = (TextView) findViewById(R.id.url);
        TextView code = (TextView) findViewById(R.id.code);
        ImageView status = (ImageView) findViewById(R.id.code_img);
        TextView latency = (TextView) findViewById(R.id.latency);
        TextView headers = (TextView) findViewById(R.id.headers);
        TextView postData = (TextView) findViewById(R.id.postData);
        TextView response = (TextView) findViewById(R.id.response);

        date.setText(dateFormat.format(new Date(networkLog.getDate())));
        url.setText("[" + networkLog.getRequestType() + "] " + networkLog.getUrl());
        code.setText(networkLog.getResponseCode());
        latency.setText(String.format("%sms", networkLog.getDuration().intValue()));
        headers.setText(networkLog.getHeaders());
        postData.setText(networkLog.getPostData());
        response.setText(networkLog.getResponseData());
        response.setTextSize(15);
        postData.setText(networkLog.getPostData());

        if(networkLog.getResponseCode().startsWith("2")){
            status.setBackgroundColor(Color.GREEN);
            code.setTextColor(Color.GREEN);
        }else if(networkLog.getResponseCode().startsWith("4")) {
            status.setBackgroundColor(Color.parseColor("#ffa500"));
            code.setTextColor(Color.parseColor("#ffa500"));
        }else if(networkLog.getResponseCode().startsWith("5")) {
            status.setBackgroundColor(Color.RED);
            code.setTextColor(Color.RED);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
