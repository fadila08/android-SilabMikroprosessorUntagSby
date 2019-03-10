package untag.daskom.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GaleriList {

    @SerializedName("data")
    private ArrayList<GaleriModel> galeriList;

    public ArrayList<GaleriModel> getGaleriArrayList() {
        return galeriList;
    }

    public void setGaleriArrayList(ArrayList<GaleriModel> galeriArrayList) {
        this.galeriList = galeriArrayList;
    }
}
