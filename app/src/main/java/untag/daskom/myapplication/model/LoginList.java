package untag.daskom.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginList {

    private DataLoginList user;

    private String access_token;

    public DataLoginList getUser() {
        return user;
    }

    public void setUser(DataLoginList user) {
        this.user = user;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
