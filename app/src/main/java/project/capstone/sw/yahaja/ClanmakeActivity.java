package project.capstone.sw.yahaja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ClanmakeActivity extends AppCompatActivity implements View.OnClickListener{

    EditText clan_make_name, clan_make_type, clan_make_master, clan_make_introduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clanmake);

        clan_make_name = findViewById(R.id.clan_make_name);
        clan_make_type = findViewById(R.id.clan_make_type);
        clan_make_master = findViewById(R.id.clan_make_master);
        clan_make_introduction = findViewById(R.id.clan_make_explain);

        TextView signUpBtn = findViewById(R.id.clan_make_signup);
        signUpBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.clan_make_signup:
                String clan_name = clan_make_name.getText().toString();
                String clan_type = clan_make_type.getText().toString();
                String clan_master = clan_make_master.getText().toString();
                String clan_introduction = clan_make_introduction.getText().toString();

                Url_ClanMake url_clanMake = new Url_ClanMake(clan_name, clan_type, clan_master, clan_introduction);
                String dataParameter = url_clanMake.getPostDataParameter();

                RequestHttp requestHttp = new RequestHttp();
                String result = requestHttp.requestPost(url_clanMake.url, dataParameter);

                if(result.equals("")){
                    Toast.makeText(this, "클랜을 생성했습니다.", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(this, "회원 가입에 실패했습니다.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
