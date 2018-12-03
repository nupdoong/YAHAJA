package project.capstone.sw.yahaja;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Url_RandomMatch {
    public final String url = "http://13.59.95.38:3000/random_match";
    private String id;

    Url_RandomMatch(String id){
        this.id = id;
    }

    public String getApiUrl(){
        String apiUrl = url + "?";
        apiUrl += "account_id=" + id;

        return apiUrl;
    }

    public String getPostDataParameter(){
        String dataParameter = "";
        dataParameter += "account_id=" + id;

        return dataParameter;
    }


    public String parseJSON(String response){
        String firstname = "";

        try {
            JSONObject res = new JSONObject(response);
            firstname = res.getString("firstname");
        } catch (JSONException e){
            System.out.println("JSON parse error. / random_match");
        }

        return firstname;
    }
}
