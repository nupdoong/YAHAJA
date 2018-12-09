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

public class ClanTap extends Fragment {
    public ClanTap(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        StoredUserSession storedUserSession = new StoredUserSession(getContext());
        String u_id = storedUserSession.getUserSession();

        View view = inflater.inflate(R.layout.activity_clan, container, false);
        ListView listView = (ListView) view.findViewById(R.id._listView);

        ClanAdapter adapter = new ClanAdapter();

        String API_url = "http://13.59.95.38:3000/clans";
        //String API_url = "http://222.98.25.138:5001/clans";
        RequestHttp requestHttp = new RequestHttp();
        String response = requestHttp.requestGet(API_url);


        try{
            JSONArray rankingResponse = new JSONArray(response);
            for(int i = 0; i < rankingResponse.length(); i++){
                ClanData r = new ClanData(rankingResponse.getJSONObject(i));
                adapter.addItem(r);
                if(r.clan_master.equals(u_id)){
                    TextView idView = view.findViewById(R.id.clan_name);
                    TextView rankView = view.findViewById(R.id.clan_type);
                    TextView pointView = view.findViewById(R.id.clan_point);
                    idView.setText(r.get_clan_name());
                    rankView.setText(r.get_type());
                    pointView.setText(r.get_points()+"");
                }
                System.out.println(r.clan_name + " " + r.type + " " + r.points);
            }
        } catch (JSONException e){
            System.out.println("JSON parse error. /clan");
        }
        //DB 에서 객체 받아와서 adapter.addItem(object);


        listView.setAdapter(adapter);
        return view;
    }

    class ClanAdapter extends BaseAdapter {
        ArrayList<ClanData> items = new ArrayList<ClanData>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ClanData item){
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

        public View getView(int position, View convertView, ViewGroup parent) {
            ClanItemView view = new ClanItemView(getContext().getApplicationContext());

            ClanData item = items.get(position);

            view.setRankNum("" + item.clan_name);
            view.setIDView(item.type);
            view.setPointView("" + item.points);
            ImageView imageView = view.findViewById(R.id.userImage);

            return view;
        }
    }

}
