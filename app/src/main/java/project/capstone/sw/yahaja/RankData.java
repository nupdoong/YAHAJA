package project.capstone.sw.yahaja;

import org.json.JSONException;
import org.json.JSONObject;

public class RankData {
    int rank_num;
    String account_id;
    int rank_point;

    RankData(JSONObject rank_json) {
        try {
            this.account_id = rank_json.getString("user_account_id");
            this.rank_point = rank_json.getInt("points");
            this.rank_num = rank_json.getInt("rank");
        } catch(JSONException e){
            System.out.println("JSON parse error. /rank");
        }
    }

    public int getRank_num() {
        return rank_num;
    }

    public String getAccount_id() {
        return account_id;
    }

    public int getRank_point() {
        return rank_point;
    }
}
