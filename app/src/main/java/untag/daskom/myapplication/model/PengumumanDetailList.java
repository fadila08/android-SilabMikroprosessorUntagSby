package untag.daskom.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PengumumanDetailList extends ArrayList<PengumumanDetailList> {

//    @SerializedName("data")
//    private ArrayList<PengumumanDetail> pengumumanDetailList;
//
//    public ArrayList<PengumumanDetail> getPengumumanDetailArrayList() {
//        return pengumumanDetailList;
//    }
//
//    public void setPengumumanDetailArrayList(ArrayList<PengumumanDetail> pengumumanDetailArrayList) {
//        this.pengumumanDetailList = pengumumanDetailArrayList;
//    }

    @SerializedName("data")
    private PengumumanDetail pengumumanDetail;

    public PengumumanDetailList(PengumumanDetail pengumumanDetail) {
        this.pengumumanDetail = pengumumanDetail;
    }

    public PengumumanDetail getPengumumanDetail() {
        return pengumumanDetail;
    }

    public void setPengumumanDetail(PengumumanDetail pengumumanDetail) {
        this.pengumumanDetail = pengumumanDetail;
    }

    //    @SerializedName("data")
//    private List<PengumumanDetail> pengumumanDetails;
//
//    public PengumumanDetailList(List<PengumumanDetail> pengumumanDetails) {
//        this.pengumumanDetails = pengumumanDetails;
//    }
//
//    public List<PengumumanDetail> getPengumumanDetails() {
//        return pengumumanDetails;
//    }
//
//    public void setPengumumanDetails(List<PengumumanDetail> pengumumanDetails) {
//        this.pengumumanDetails = pengumumanDetails;
//    }
}
