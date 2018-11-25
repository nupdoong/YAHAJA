package project.capstone.sw.yahaja;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class RankNearActivity extends AppCompatActivity {

  //private static final String databaseURL = "jdbc:mysql://yahajainstance.cseazqbmpvdh.us-east-2.rds.amazonaws.com:3306/yahajaDB";
  //private static final String dbID = "pjw930731";
  //private static final String dbPW = "q15w96e31";

  private ArrayList<RankData> rankDataList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rank);

    ListView listView = (ListView) findViewById(R.id._listView);

    RankAdapter adapter = new RankAdapter();
    StoredUserSession storedUserSession = new StoredUserSession(getApplicationContext());
    String u_id = storedUserSession.getUserSession();
    //openDatabase();

    String API_url = "http://18.220.15.129:3000/get_billiards_rank";
    RequestHttp requestHttp = new RequestHttp();
    String response = requestHttp.requestGet(API_url);

    System.out.println(response);
    try{
      JSONArray rankingResponse = new JSONArray(response);
      for(int i = 0; i < rankingResponse.length(); i++){
        RankData r = new RankData(rankingResponse.getJSONObject(i));
        int count = 1;
        if(true){//if r.account_id 와 session_id 사이의 거리가 일정 거리 이하이면 넣기
          r.rank_num = count; //디비에 순위로 정렬되어 있을 거니까 가져오는 순서대로 순위 increment 해주면 될 듯.
          adapter.addItem(r);
          count++;
        }
        if(r.account_id == u_id){
          TextView idView = findViewById(R.id.userId);
          TextView rankView = findViewById(R.id.rankNum);
          TextView pointView = findViewById(R.id.rankPoint);
        }
        System.out.println(r.account_id + " " + r.rank_num + " " + r.rank_point);
      }
    } catch (JSONException e){
      System.out.println("JSON parse error. /rankD");
    }
    //DB 에서 객체 받아와서 adapter.addItem(object);

    listView.setAdapter(adapter);
  }

  class RankAdapter extends BaseAdapter {
    ArrayList<RankData> items = new ArrayList<RankData>();

    @Override
    public int getCount() {
      return items.size();
    }

    public void addItem(RankData item){
      items.add(item);
    }

    @Override
    public Object getItem(int position) {
      return items.get(position);
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      RankItemView view = new RankItemView(getApplicationContext());

      RankData item = items.get(position);
      view.setRankNum("" + item.rank_num);
      view.setIDView(item.account_id);
      view.setPointView("" + item.rank_point);

      return view;
    }
  }

}
