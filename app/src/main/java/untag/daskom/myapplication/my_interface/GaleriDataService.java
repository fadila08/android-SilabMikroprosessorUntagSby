package untag.daskom.myapplication.my_interface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import untag.daskom.myapplication.model.GaleriDetailList;
import untag.daskom.myapplication.model.GaleriList;

public interface GaleriDataService {

    @GET("api/galeri")
    Call<GaleriList> getGaleri();

    @GET("api/galeri/{id}")
    Call<GaleriDetailList> getGaleriDetail(@Path("id") String id);

}
