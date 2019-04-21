package untag.daskom.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataInventarisList {

    @SerializedName("data")
    private ArrayList<DataInventaris> dataInventarisList;

    public ArrayList<DataInventaris> getDataInventarisArrayList() {
        return dataInventarisList;
    }

    public void setDataInventarisArrayList(ArrayList<DataInventaris> dataInventarisArrayList) {
        this.dataInventarisList = dataInventarisArrayList;
    }
}
