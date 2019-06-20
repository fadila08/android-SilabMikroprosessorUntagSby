package untag.daskom.myapplication.my_interface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import untag.daskom.myapplication.model.PraktikumList;

public interface FilterDataService {

    @GET("api/praktikum")
    Call<PraktikumList> getPraktikum(@Header("Authorization") String auth);

}
