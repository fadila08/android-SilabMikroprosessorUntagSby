package untag.daskom.myapplication.my_interface;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import untag.daskom.myapplication.model.DeleteValue;
import untag.daskom.myapplication.model.UserDetailList;

public interface LaboranDataService {

    @FormUrlEncoded
    @POST("api/datalaboran")
    Call<UserDetailList> addLaboran(@Header("Authorization") String auth,
                                    @Field("nama") String nama,
                                    @Field("nomor_induk") String nomor_induk,
                                    @Field("nomor_whatsapp") String nomor_whatsapp,
                                    @Field("email") String email);

    @FormUrlEncoded
    @DELETE("api/datalaboran/{id}")
    Call<DeleteValue> deleteLaboran(@Header("Authorization") String auth,
                                    @Path("id") String id);

}
