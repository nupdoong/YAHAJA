package project.capstone.sw.yahaja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ClanmakeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clanmake);

        //id = findViewById(R.id.editText_signup_id);
        //password = findViewById(R.id.editText_signup_pw);
        //user_sex= findViewById(R.id.editText_signup_sex);
        //user_firstname = findViewById(R.id.editText_signup_firstname);
        //user_lastname = findViewById(R.id.editText_signup_lastname);
        //phone = findViewById(R.id.editText_signup_phone);

        TextView signUpBtn = findViewById(R.id.textView_signup_signup);
        signUpBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

    }
}
