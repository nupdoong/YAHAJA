package project.capstone.sw.yahaja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    StoredUserSession storedUserSession;
    EditText id, pw;

    // Firebase - Realtime Database
    private FirebaseDatabase mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_login);

        storedUserSession = new StoredUserSession(this);
        String storedId = storedUserSession.getUserSession();


        id = findViewById(R.id.editText_login_id);
        pw = findViewById(R.id.editText_login_pw);
        TextView signinBtn = findViewById(R.id.textView_login_signin);
        signinBtn.setOnClickListener(this);
        TextView signupBtn = findViewById(R.id.textView_login_signup);
        signupBtn.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }



    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.textView_login_signin:
                String user_id = id.getText().toString();
                String user_pw = pw.getText().toString();


                Url_Login url_login = new Url_Login(user_id, user_pw);
                String apiUrl = url_login.getApiUrl();
                RequestHttp requestHttp = new RequestHttp();
                String response = requestHttp.requestGet(apiUrl);


                if(response.equals("")){
                    Toast.makeText(this,
                            "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요!", Toast.LENGTH_LONG).show();
                } else {
                    storedUserSession.storeUserSession(user_id);


                    UserData userData = new UserData();
                    userData.userEmailID = user_id;
                    userData.fcmToken = FirebaseInstanceId.getInstance().getToken();

                    Log.d("FMCFMCFMCFMCFMC", FirebaseInstanceId.getInstance().getToken());

                    mFirebaseDatabase = FirebaseDatabase.getInstance();
                    mFirebaseDatabase.getReference("users").child(userData.userEmailID).setValue(userData);

                    String dataParameter = "";
                    dataParameter += "account_id=" +  userData.userEmailID;
                    dataParameter += "&fcm_token=" +  userData.fcmToken;

                    RequestHttp requestHttpToken = new RequestHttp();
                    requestHttpToken.requestPost("http://13.59.95.38:3000/login_fcm", dataParameter);

                    Log.d("sexsex", FirebaseInstanceId.getInstance().getToken());


                    Toast.makeText(this,
                            "로그인에 성공했습니다!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }

                break;


            case R.id.textView_login_signup:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

}
