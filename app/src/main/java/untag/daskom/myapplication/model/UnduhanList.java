package untag.daskom.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UnduhanList {

    @SerializedName("data")
    private ArrayList<UnduhanModel> unduhanList;

    public ArrayList<UnduhanModel> getUnduhanArrayList() {
        return unduhanList;
    }

    public void setUnduhanArrayList(ArrayList<UnduhanModel> unduhanArrayList) {
        this.unduhanList = unduhanArrayList;
    }


}
