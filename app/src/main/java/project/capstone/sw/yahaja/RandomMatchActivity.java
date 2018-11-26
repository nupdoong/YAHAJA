package project.capstone.sw.yahaja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import project.capstone.sw.yahaja.http.RequestHttp;

public class RandomMatchActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_match);
        RequestHttp requestHttp = new RequestHttp();

        StoredUserSession storedUserSession = new StoredUserSession(getApplicationContext());
        String u_id = storedUserSession.getUserSession();

        Url_RandomMatch url_randomMatch = new Url_RandomMatch(u_id);

        // Http Request Post로 사용자의 status를 ready로 변경
        String api_url_match = url_randomMatch.getApiUrl();
        String resultMatchGet = requestHttp.requestGet(api_url_match);
        String firstName = url_randomMatch.parseJSON(resultMatchGet);
        if(firstName.equals("")){
            // 주변 매칭이 없는 경우 fail
            System.out.println("No match.");
            return;
        }

        // Http Request Get으로 주변 상대를 검색
        String dataParameter = url_randomMatch.getPostDataParameter();
        String resultMatchPost = requestHttp.requestPost(url_randomMatch.url, dataParameter);


        // Http Request Get으로 상대방의 rank, point 가져오기
        Url_RandomPartner url_randomPartner = new Url_RandomPartner();
        String api_url_partner = url_randomPartner.getApiUrl();
        String resultPartnerGet = requestHttp.requestGet(api_url_partner);
        Url_RandomPartner.RandomPartner randomPartner = url_randomPartner.parseJSON(resultPartnerGet);


        // Http request Get으로 시설 정보 가져오기
        Url_RandomFc url_randomFc = new Url_RandomFc();
        String api_url_fc = url_randomFc.getApiUrl();
        String resultFcGet = requestHttp.requestGet(api_url_fc);
        Url_RandomFc.RandomFc randomFc = url_randomFc.parseJSON(resultFcGet);


        // TextView 표시
        TextView t_partner = findViewById(R.id.textView_partner);
        TextView t_rank = findViewById(R.id.textView_rank);
        TextView t_average = findViewById(R.id.textView_average);
        TextView t_facility = findViewById(R.id.textView_facility);
        TextView t_contact = findViewById(R.id.textView_contact);
        TextView t_address = findViewById(R.id.textView_address);

        t_partner.setText(firstName);
        t_facility.setText(randomFc.name);
        t_contact.setText(randomFc.contact);
        t_address.setText(randomFc.location);
        t_rank.setText(Integer.toString(randomPartner.rank));
        t_average.setText(Integer.toString(randomPartner.points));
    }

    // ButtonClick
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button_chatting:
                Intent intent = new Intent(RandomMatchActivity.this, ChatActivity.class);
                startActivity(intent);
                break;
            case R.id.button_result:
                // goto result

                break;
        }
    }
}