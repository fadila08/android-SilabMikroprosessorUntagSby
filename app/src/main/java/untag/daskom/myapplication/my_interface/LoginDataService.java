package untag.daskom.myapplication.my_interface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import untag.daskom.myapplication.model.LoginList;

public interface LoginDataService {

    @FormUrlEncoded
    @POST("api/login")
    Call<LoginList> login(@Field("username") String username,
                          @Field("password") String password);
//    @POST("api/login")
//    Call<LoginList> postLogin(@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body        );

    /*
        @GET("api/pengumuman/{id}")
    Call<PengumumanDetailList> getPengumumanDetail(@Path("id") String id);
     */
}
