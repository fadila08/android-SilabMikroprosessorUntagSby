package untag.daskom.myapplication.my_interface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import untag.daskom.myapplication.model.UserDetailList;

public interface AslabDataService {

    @FormUrlEncoded
    @POST("api/dataaslab")
    Call<UserDetailList> addLaboran(@Header("Authorization") String auth,
                                    @Field("nama") String nama,
                                    @Field("nomor_induk") String nomor_induk,
                                    @Field("nomor_whatsapp") String nomor_whatsapp,
                                    @Field("email") String email);


}
