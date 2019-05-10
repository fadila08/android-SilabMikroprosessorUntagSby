package untag.daskom.myapplication.my_interface;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import untag.daskom.myapplication.model.DeleteValue;
import untag.daskom.myapplication.model.PengumumanDetailList;
import untag.daskom.myapplication.model.PengumumanList;

public interface PengumumanDataService {

    @GET("api/datapengumuman")
    Call<PengumumanList> getPengumuman();

    @GET("api/pengumuman")
    Call<PengumumanList> getPengumumanAuth(@Header("Authorization") String auth);

    @FormUrlEncoded
    @POST("api/pengumuman")
    Call<PengumumanDetailList> addPengumuman(@Header("Authorization") String auth,
                                             @Field("judul") String judul,
                                             @Field("isi") String isi,
//                                             @Field("file_lampiran") Byte file_lampiran,  // ini nanti ganti format file
                                             @Field("batas_tanggal_berlaku") String batas_tanggal_berlaku);

    @DELETE("api/pengumuman/{id}")
    Call<DeleteValue> deletePengumuman(@Header("Authorization") String auth,
                                        @Path("id") String id);

    @FormUrlEncoded
    @PUT("api/dataaslab/{id}")
    Call<PengumumanDetailList> editPengumuman(@Header("Authorization") String auth,
                                   @Path("id") String id,
                                   @Field("judul") String judul,
                                   @Field("isi") String isi,
//                                 @Field("file_lampiran") Byte file_lampiran,  // ini nanti ganti format file
                                   @Field("batas_tanggal_berlaku") String batas_tanggal_berlaku);


}
