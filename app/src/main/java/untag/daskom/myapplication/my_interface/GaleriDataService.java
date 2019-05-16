package untag.daskom.myapplication.my_interface;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import untag.daskom.myapplication.model.DeleteValue;
import untag.daskom.myapplication.model.GaleriDetailList;
import untag.daskom.myapplication.model.GaleriList;

public interface GaleriDataService {

    @GET("api/datagaleri")
    Call<GaleriList> getGaleri();

    @GET("api/datagaleri/{id}")
    Call<GaleriDetailList> getGaleriDetail(@Path("id") String id);

    @DELETE("api/galeri/{id}")
    Call<DeleteValue> deleteGaleri(@Header("Authorization") String auth,
                                       @Path("id") String id);

    @FormUrlEncoded
    @POST("api/galeri")
    Call<GaleriDetailList> addGaleri(@Header("Authorization") String auth,
                                     @Field("judul") String judul,
                                     @Field("gambar") Byte gambar) ;// ini nanti ganti format gambar;


}
