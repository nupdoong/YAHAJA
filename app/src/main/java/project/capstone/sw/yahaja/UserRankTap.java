package project.capstone.sw.yahaja;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class UserRankTap extends Fragment {
  public UserRankTap(){}

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    StoredUserSession storedUserSession = new StoredUserSession(getContext());
    String u_id = storedUserSession.getUserSession();

    View view = inflater.inflate(R.layout.activity_rank, container, false);
    ListView listView = (ListView) view.findViewById(R.id._listView);

    RankAdapter adapter = new RankAdapter();

    String API_url = "http://13.59.95.38:3000/get_billiards_rank";
    //String API_url = "http://222.98.25.138:5001/get_billiards_rank";
    RequestHttp requestHttp = new RequestHttp();
    String response = requestHttp.requestGet(API_url);


    try{
      JSONArray rankingResponse = new JSONArray(response);
      for(int i = 0; i < rankingResponse.length(); i++){
        RankData r = new RankData(rankingResponse.getJSONObject(i));
        r.setRank_num(i+1);
        adapter.addItem(r);
        if(r.account_id.equals(u_id)){
          TextView idView = view.findViewById(R.id.userId);
          TextView rankView = view.findViewById(R.id.rankNum);
          TextView pointView = view.findViewById(R.id.rankPoint);
          ImageView imageView = view.findViewById(R.id.userImage);
          if(r.rank_num == 1){
            imageView.setImageResource(R.drawable.ic_gold_trophy);
          }else if(r.rank_num == 2){
            imageView.setImageResource(R.drawable.ic_silver_trophy);
          }else if(r.rank_num == 3){
            imageView.setImageResource(R.drawable.ic_bronze_trophy);
          }
          idView.setText(r.getAccount_id());
          rankView.setText(r.getRank_num()+"");
          pointView.setText(r.getRank_point()+"");
        }
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
      ImageView imageView = view.findViewById(R.id.userImage);
      if(item.getRank_num() == 1) {
        imageView.setImageResource(R.drawable.ic_gold_trophy);
      } else if(item.getRank_num() == 2){
        imageView.setImageResource(R.drawable.ic_silver_trophy);
      } else if(item.getRank_num() == 3){
        imageView.setImageResource(R.drawable.ic_bronze_trophy);
      }

      return view;
    }
  }
}
