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
import untag.daskom.myapplication.model.UnduhanDetailList;
import untag.daskom.myapplication.model.UnduhanList;

public interface UnduhanDataService {

    @GET("api/unduhan/read")
    Call<UnduhanList> getUnduhan();

    @FormUrlEncoded
    @POST("api/unduhan/insert")
    Call<UnduhanDetailList> addUnduhan(@Header("Authorization") String auth,
                                       @Field("judul") String judul,
                                       @Field("keterangan") String keterangan,
//                                     @Field("file") Byte file,  // ini nanti ganti format file
                                       @Field("batas_tanggal_berlaku") String batas_tanggal_berlaku);

    @GET("api/unduhan/read/{id}") //belom ada
    Call<UnduhanDetailList> getUnduhanDetail(@Path("id") String id);


    @DELETE("api/unduhan/delete/{id}")
    Call<DeleteValue> deleteUnduhan(@Header("Authorization") String auth,
                                       @Path("id") String id);

    @FormUrlEncoded
    @PUT("api/unduhan/update/{id}")
    Call<UnduhanDetailList> editUnduhan(@Header("Authorization") String auth,
                                           @Path("id") String id,
                                           @Field("judul") String judul,
                                           @Field("keterangan") String keterangan,
//                                         @Field("file") Byte file,  // ini nanti ganti format file
                                           @Field("batas_tanggal_berlaku") String batas_tanggal_berlaku);


}
