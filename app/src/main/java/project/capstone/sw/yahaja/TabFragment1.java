package project.capstone.sw.yahaja;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class TabFragment1 extends Fragment {
  public TabFragment1(){}

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    View view = inflater.inflate(R.layout.activity_rank, container, false);
    ListView listView = (ListView) view.findViewById(R.id._listView);

    RankAdapter adapter = new RankAdapter();
    String session_id = "lonecitizen";
    //openDatabase();

    String API_url = "http://18.220.15.129:3000/get_billiards_rank";
    RequestHttp requestHttp = new RequestHttp();
    String response = requestHttp.requestGet(API_url);

    System.out.println("sibal");
    Log.d("RANK", response);

    try{
      JSONArray rankingResponse = new JSONArray(response);
      for(int i = 0; i < rankingResponse.length(); i++){
        RankData r = new RankData(rankingResponse.getJSONObject(i));
        adapter.addItem(r);
                /*if(r.account_id == session_id){
                  TextView idView = findViewById(R.id.userId);
                  TextView rankView = findViewById(R.id.rankNum);
                  TextView pointView = findViewById(R.id.rankPoint);
                }*/
        System.out.println(r.account_id + " " + r.rank_num + " " + r.rank_point);
      }
    } catch (JSONException e){
      System.out.println("JSON parse error. /rankD");
    }
    //DB 에서 객체 받아와서 adapter.addItem(object);


    listView.setAdapter(adapter);
    return view;
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
      RankItemView view = new RankItemView(getContext().getApplicationContext());

      RankData item = items.get(position);
      view.setRankNum("" + item.rank_num);
      view.setIDView(item.account_id);
      view.setPointView("" + item.rank_point);

      return view;
    }
  }
}
