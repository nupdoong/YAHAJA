package project.capstone.sw.yahaja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class RankActivity extends AppCompatActivity {

    //private static final String databaseURL = "jdbc:mysql://yahajainstance.cseazqbmpvdh.us-east-2.rds.amazonaws.com:3306/yahajaDB";
    //private static final String dbID = "pjw930731";
    //private static final String dbPW = "q15w96e31";

    ArrayList<RankData> rankDataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        ListView listView = (ListView) findViewById(R.id._listView);

        RankAdapter adapter = new RankAdapter();
        //openDatabase();

        String API_url = "http://192.168.0.44:5001/get_billiards_rank";
        RequestHttp requestHttp = new RequestHttp();
        String response = requestHttp.requestGet(API_url);

        System.out.println(response);
        try{
            JSONArray rankingResponse = new JSONArray(response);
            for(int i = 0; i < rankingResponse.length(); i++){
                RankData r = new RankData(rankingResponse.getJSONObject(i));
                adapter.addItem(r);
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
