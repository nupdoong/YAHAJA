package project.capstone.sw.yahaja;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Url_RandomPartner {

    class RandomPartner{
        int rank;
        int points;

        RandomPartner(){
        }
    }

    public final String url = "http://13.59.95.38:3000/random_partner";
    //public final String url = "http://222.98.25.138:5001/random_partner";

    Url_RandomPartner(){
    }

    public String getApiUrl(){
        String apiUrl = url + "?";

        return apiUrl;
    }

    public RandomPartner parseJSON(String response){
        RandomPartner randomPartner = new RandomPartner();

        try {
            JSONArray resArr = new JSONArray(response);
            JSONObject res = resArr.getJSONObject(0);
            int rank = Integer.parseInt(res.getString("rank"));
            int points = Integer.parseInt(res.getString("points"));
            randomPartner.rank = rank;
            randomPartner.points = points;
        } catch (JSONException e){
            System.out.println("JSON parse error. / random_partner");
        }

        return randomPartner;
    }
}
