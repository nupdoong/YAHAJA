package project.capstone.sw.yahaja;

public class Url_Login {
    private final String url = "http://18.220.15.129:3000/login?";

    private String id;
    private String password;

    Url_Login(String id, String password){
        this.id = id;
        this.password = password;
    }

    public String getApiUrl(){
        String apiUrl = url;
        apiUrl += "account_id=" + id;
        apiUrl += "&account_pw=" + password;

        return apiUrl;
    }
}
