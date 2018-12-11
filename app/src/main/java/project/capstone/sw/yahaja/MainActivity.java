package project.capstone.sw.yahaja;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends NavigationActivity implements View.OnClickListener{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private android.support.v4.app.FragmentManager fragmentManager;


    //MatchTap matchTap = new MatchTap();

    @Override
    protected void onCreate(Bundle savedInstanceState){


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView navigationIcon = findViewById(R.id.imageView_menuIcon);
        navigationIcon.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();

        tabLayout = findViewById(R.id.tabLayoutMain);
        viewPager = findViewById(R.id.viewPagerMain);
        FragmentAdapter adapter = new FragmentAdapter(fragmentManager);

        adapter.addFragment(new HomeTap(), "HOME", false);
        adapter.addFragment(new MatchTap(), "MATCH", false);
        adapter.addFragment(new RankTapActivity(), "RANK", false);
        adapter.addFragment(new ClanTap(), "CLAN", false);

        viewPager.setAdapter(adapter);

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setupWithViewPager(viewPager);


// Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            String u_id;
            String opponentID;
            String matchPlace;
            String opponentContact;
            String opponentPoint;
            String matchPlaceContact;
            String matchPlaceLatitude;
            String matchPlaceLongitude;
            MatchData myMatch;
            FacilityData facilityData;


            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition() == 1){
                    StoredUserSession storedUserSession = new StoredUserSession(getApplicationContext());
                    u_id = storedUserSession.getUserSession();

                    System.out.println("u_id : " + u_id);
                    String API_url_myMatch = "http://13.59.95.38:3000/find_match?account_id=" + u_id;
                    RequestHttp requestHttp = new RequestHttp();
                    String response = requestHttp.requestGet(API_url_myMatch);

                    System.out.println("find_match res : " + response);

                    try{
                        JSONArray myMatchResponse= new JSONArray(response);

                        myMatch = new MatchData(myMatchResponse.getJSONObject(0));

                        if(myMatch.getMember1().equals(u_id)) opponentID = myMatch.getMember2();
                        else opponentID = myMatch.getMember1(); //상대 아이디 찾기

                        System.out.println("oppo id : " + opponentID);

                        matchPlace = myMatch.getMatchPlace();

                    } catch (JSONException e){
                        System.out.println("JSON parse error. /clanRank");
                    }

                    String API_url_opponentPoint = "http://13.59.95.38:3000/partner_points?partner_id=" + opponentID;
                    String API_url_opponentContact = "http://13.59.95.38:3000/partner_contact?partner_id=" + opponentID;
                    String API_url_facility = "http://13.59.95.38:3000/fc_info?name=" + matchPlace;

                    String responsePoint = requestHttp.requestGet(API_url_opponentPoint);
                    String responseContact = requestHttp.requestGet(API_url_opponentContact);
                    String responseFacility = requestHttp.requestGet(API_url_facility);

                    try{
                        JSONArray opponentPointJSON = new JSONArray(responsePoint);
                        opponentPoint = opponentPointJSON.getJSONObject(0).getString("points");
                        JSONArray opponentContactJSON = new JSONArray(responseContact);
                        opponentContact = opponentContactJSON.getJSONObject(0).getString("contact");

                        JSONArray facilityResponse = new JSONArray(responseFacility);

                        facilityData = new FacilityData(facilityResponse.getJSONObject(0));

                        matchPlaceContact = facilityData.getContact();
                        matchPlaceLatitude = facilityData.getLatitude();
                        matchPlaceLongitude = facilityData.getLongitude();
                    }catch (JSONException e){

                    }

                    TextView opponentIDView = findViewById(R.id.opponentIDView);
                    TextView opponentPointView = findViewById(R.id.opponentPointView);
                    TextView opponentContactView = findViewById(R.id.opponentContactView);
                    TextView facilityNameView = findViewById(R.id.facilityNameView);
                    TextView facilityContactView = findViewById(R.id.facilityContactView);

                    opponentIDView.setText("  상대 ID   : " + opponentID);
                    opponentPointView.setText("  상대 점수 : " + opponentPoint);
                    opponentContactView.setText("  연락처    : " + opponentContact);
                    facilityNameView.setText("  상호명 : " + matchPlace);
                    facilityContactView.setText("  연락처 : " + matchPlaceContact);

                    //matchTap.setLat(Double.parseDouble(matchPlaceLatitude));
                    //matchTap.setLon(Double.parseDouble(matchPlaceLongitude));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_menuIcon:
                super.drawerLayout.openDrawer(Gravity.START);
                StoredUserSession storedUserSession = new StoredUserSession(this);
                String u_id = storedUserSession.getUserSession();
                TextView textView = drawerLayout.findViewById(R.id.drawerHeader);
                textView.setText(u_id);
                break;
            default:
                break;
        }
    }
}
