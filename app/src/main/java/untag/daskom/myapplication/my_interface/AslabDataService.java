package untag.daskom.myapplication.my_interface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import untag.daskom.myapplication.model.UserDetailList;

public interface AslabDataService {

    @FormUrlEncoded
    @POST("api/dataaslab")
    Call<UserDetailList> addAslab(@Header("Authorization") String auth,
                                    @Field("nama") String nama,
                                    @Field("nomor_induk") String nomor_induk,
                                    @Field("nomor_whatsapp") String nomor_whatsapp,
                                    @Field("email") String email);

    @FormUrlEncoded
    @PUT("api/dataaslab/{id}")
    Call<UserDetailList> editAslab(@Header("Authorization") String auth,
                                     @Path("id") String id,
                                     @Field("nama") String nama,
                                     @Field("nomor_induk") String nomor_induk,
                                     @Field("nomor_whatsapp") String nomor_whatsapp,
                                     @Field("email") String email);



}
