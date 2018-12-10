package project.capstone.sw.yahaja;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DialogActivity extends Activity implements
        OnClickListener {

    private Button mConfirm, mCancel;

    private static final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
    private static final String SERVER_KEY = "AIzaSyDZF3zh-0_PmYHKxEprFrx4V8AXKB_dQkk";

    // Firebase - Realtime Database
    private FirebaseDatabase mFirebaseDatabase;
    String customId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);
        setContent();


        Intent intent = getIntent();
        customId = intent.getExtras().getString("customId");



    }

    private void setContent() {
        mConfirm = (Button) findViewById(R.id.btnConfirm);
        mCancel = (Button) findViewById(R.id.btnCancel);

        mConfirm.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnConfirm:
                sendPostToFCM(customId,"매칭요청");
                break;
            case R.id.btnCancel:
                Intent intent = new Intent(DialogActivity.this, ChatActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    private void sendPostToFCM(final String customId, final String message) {

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mFirebaseDatabase.getReference("users")
                .child(customId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final UserData userData = dataSnapshot.getValue(UserData.class);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    // FMC 메시지 생성 start
                                    Log.d("FMCFMCFMCFMCFMC", "FMC 메시지 생성 starwt" );
                                    JSONObject root = new JSONObject();
                                    JSONObject notification = new JSONObject();
                                    notification.put("body", message);
                                    notification.put("title", getString(R.string.app_name));
                                    root.put("notification", notification);
                                    root.put("to", userData.fcmToken);
                                    Log.d("userData.fcmToken", userData.fcmToken);

                                    URL Url = new URL(FCM_MESSAGE_URL);
                                    HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
                                    conn.setRequestMethod("POST");
                                    conn.setDoOutput(true);
                                    conn.setDoInput(true);
                                    conn.addRequestProperty("Authorization", "key=" + SERVER_KEY);
                                    conn.setRequestProperty("Accept", "application/json");
                                    conn.setRequestProperty("Content-type", "application/json");

                                    OutputStream os = conn.getOutputStream();
                                    os.write(root.toString().getBytes("utf-8"));
                                    os.flush();

                                    conn.getResponseCode();

                                  //  conn.disconnect();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

}
