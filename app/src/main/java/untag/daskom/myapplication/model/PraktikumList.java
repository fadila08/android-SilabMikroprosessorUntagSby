package untag.daskom.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PraktikumList {

    @SerializedName("data")
    private ArrayList<DataPraktikumList> dataPraktikumList;

    public ArrayList<DataPraktikumList> getDataPraktikumArrayList() {
        return dataPraktikumList;
    }

    public void setDataPraktikumArrayList(ArrayList<DataPraktikumList> dataPraktikumArrayList) {
        this.dataPraktikumList = dataPraktikumArrayList;
    }
}
