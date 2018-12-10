package project.capstone.sw.yahaja;

import android.graphics.Color;
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
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by JINO on 2018-12-07.
 */

public class ClanRankTap extends Fragment {

    private String clanname = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        StoredUserSession storedUserSession = new StoredUserSession(getContext());
        String u_id = storedUserSession.getUserSession();
        MyClanName myClanName;

        View view = inflater.inflate(R.layout.activity_rank, container, false);
        ListView listView = (ListView) view.findViewById(R.id._listView);
        TextView textView = view.findViewById(R.id.clanID);
        textView.setText("CLAN ID");

        ClanRankTap.RankAdapter adapter = new ClanRankTap.RankAdapter();

        String API_url = "http://13.59.95.38:3000/get_billiards_rank_clan";
        String API_url_clanRank = "http://13.59.95.38:3000/find_clan";
        //String API_url = "http://222.98.25.138:5001/get_billiards_rank";
        RequestHttp requestHttp = new RequestHttp();

        String response = requestHttp.requestGet(API_url_clanRank + "?account_id=" + u_id);
        System.out.println("res: " + response);
        try{
            JSONArray clanRankingResponse = new JSONArray(response);

            myClanName = new MyClanName(clanRankingResponse.getJSONObject(0));

            clanname = myClanName.getClanname();
        } catch (JSONException e){
            System.out.println("JSON parse error. /clanRank");
        }

        System.out.println("res clan : " + clanname);
        response = requestHttp.requestGet(API_url);

        try{
            JSONArray rankingResponse = new JSONArray(response);
            for(int i = 0; i < rankingResponse.length(); i++){
                ClanRankData r = new ClanRankData(rankingResponse.getJSONObject(i));
                r.setClan_rank_num(i+1);
                adapter.addItem(r);

                TextView idView = view.findViewById(R.id.userId);
                TextView rankView = view.findViewById(R.id.rankNum);
                TextView pointView = view.findViewById(R.id.rankPoint);
                ImageView imageView = view.findViewById(R.id.userImage);

                if(r.clan_name.equals(clanname)){
                    if(r.clan_rank_num == 1){
                        imageView.setImageResource(R.drawable.ic_gold_trophy);
                    }else if(r.clan_rank_num == 2){
                        imageView.setImageResource(R.drawable.ic_silver_trophy);
                    }else if(r.clan_rank_num == 3){
                        imageView.setImageResource(R.drawable.ic_bronze_trophy);
                    }
                    idView.setText(r.getClan_name());
                    pointView.setText(r.getClan_point()+"");
                    rankView.setText(i+1 + "");
                } else if(r.clan_name.equals("")){
                    idView.setText(" - ");
                    pointView.setText(" - ");
                    rankView.setText(" - ");
                }
                System.out.println(r.clan_name + " " + r.clan_rank_num + " " + r.clan_point);
            }
        } catch (JSONException e){
            System.out.println("JSON parse error. /rankD");
        }

        //DB 에서 객체 받아와서 adapter.addItem(object);


        listView.setAdapter(adapter);
        return view;
    }

    class RankAdapter extends BaseAdapter {
        ArrayList<ClanRankData> items = new ArrayList<ClanRankData>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ClanRankData item){
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

            ClanRankData item = items.get(position);

            String clannameIdx = item.getClan_name();
            int color = Color.argb(255, 236, 236, 240);
            if(clannameIdx.equals(clanname)) {
                View rankItemBackground = view.findViewById(R.id.rankItemView);
                rankItemBackground.setBackgroundColor(color);
            }

            view.setRankNum("" + item.clan_rank_num);
            view.setIDView(item.clan_name);
            view.setPointView("" + item.clan_point);
            ImageView imageView = view.findViewById(R.id.userImage);
            if(item.getClan_rank_num() == 1) {
                imageView.setImageResource(R.drawable.ic_gold_trophy);
            } else if(item.getClan_rank_num() == 2){
                imageView.setImageResource(R.drawable.ic_silver_trophy);
            } else if(item.getClan_rank_num() == 3){
                imageView.setImageResource(R.drawable.ic_bronze_trophy);
            }

            return view;
        }
    }

    class MyClanName{
        String clanname;
        MyClanName(JSONObject rank_json){
            try{
                this.clanname = rank_json.getString("clan");
            } catch (JSONException e){
                System.out.println("JSON PARSE ERROR ./myClanName");
            }
        }

        String getClanname(){
            return clanname;
        }
    }
}
