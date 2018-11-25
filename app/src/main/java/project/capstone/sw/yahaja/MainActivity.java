package project.capstone.sw.yahaja;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void start_map_activity(View v) {
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    public void start_chat_activity(View v) {

     Intent intent = new Intent(MainActivity.this, ChatActivity.class);
     startActivity(intent);


    }

    public void start_rank_activity(View v) {
        Intent intent = new Intent(MainActivity.this, RankTapActivity.class);
        startActivity(intent);

    }


}
