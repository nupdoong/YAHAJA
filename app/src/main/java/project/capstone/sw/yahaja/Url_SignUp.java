package project.capstone.sw.yahaja;

public class Url_SignUp {
    public final String url = "http://13.59.95.38:3000/signup?";
    //public final String url = "http://222.98.25.138:5001/signup?";
    
    private String id;
    private String password;
    private String sex;
    private String firstname;
    private String lastname;
    private String phone;

    Url_SignUp(String id, String password, String sex, String firstname, String lastname, String phone){
        this.id = id;
        this.password = password;
        this.sex = sex;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    public String getPostDataParameter(){
        String dataParameter = "";
        dataParameter += "account_id=" + id;
        dataParameter += "&account_pw=" + password;
        dataParameter += "&sex=" + sex;
        dataParameter += "&firstname=" + firstname;
        dataParameter += "&lastname=" + lastname;
        dataParameter += "&contact=" + phone;

        return dataParameter;
    }

}
