package project.capstone.sw.yahaja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    EditText id, password, passwordConfirm, user_sex, user_firstname, user_lastname, phone;


    // Firebase - Realtime Database
    private FirebaseDatabase mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        id = findViewById(R.id.editText_signup_id);
        password = findViewById(R.id.editText_signup_pw);
        passwordConfirm = findViewById(R.id.editText_signup_pw2);
        user_sex= findViewById(R.id.editText_signup_sex);
        user_firstname = findViewById(R.id.editText_signup_firstname);
        user_lastname = findViewById(R.id.editText_signup_lastname);
        phone = findViewById(R.id.editText_signup_phone);



        TextView signUpBtn = findViewById(R.id.textView_signup_signup);
        signUpBtn.setOnClickListener(this);
    }



    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.textView_signup_signup:
                String account_id = id.getText().toString();
                String account_pw = password.getText().toString();
                String account_pw_confirm = passwordConfirm.getText().toString();
                String sex = user_sex.getText().toString();
                String firstname = user_firstname.getText().toString();
                String lastname = user_lastname.getText().toString();
                String contact = phone.getText().toString();

                if(!account_pw.equals(account_pw_confirm)){
                    Toast.makeText(this, "Password INCORRECT !", Toast.LENGTH_SHORT).show();
                    return;
                }
                Url_SignUp url_signUp = new Url_SignUp(account_id, account_pw, sex, firstname, lastname, contact);
                String dataParameter = url_signUp.getPostDataParameter();

                RequestHttp requestHttp = new RequestHttp();
                String result = requestHttp.requestPost(url_signUp.url, dataParameter);

                if(result.equals("")){
                    Toast.makeText(this, "회원가입 성공! 다시 로그인해주세요.", Toast.LENGTH_LONG).show();


                    UserData userData = new UserData();
                    userData.userEmailID = account_id;
                    userData.fcmToken = FirebaseInstanceId.getInstance().getToken();

                    Log.d("FMCFMCFMCFMCFMC", FirebaseInstanceId.getInstance().getToken());

                    mFirebaseDatabase = FirebaseDatabase.getInstance();
                    mFirebaseDatabase.getReference("users").child(userData.userEmailID).setValue(userData);

                    Log.d("sexsex", FirebaseInstanceId.getInstance().getToken());

                    finish();
                } else if(result.equals("There is already same ID.")){
                    Toast.makeText(this, "중복된 아이디가 존재합니다", Toast.LENGTH_LONG).show();
                } else if (result.equals("phone_number is duplicated")){
                    Toast.makeText(this, "중복된 핸드폰 번호가 존재합니다", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "회원 가입에 실패했습니다.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
