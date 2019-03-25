package untag.daskom.myapplication.my_interface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import untag.daskom.myapplication.model.DataUserList;

public interface GetUserDataService {

    @GET("api/kalab")
    Call<DataUserList> getKalabData();

    @GET("api/laboran")
    Call<DataUserList> getLaboranData();

    @GET("api/aslab")
    Call<DataUserList> getAslabData();

    @GET("api/laboran")
    Call<DataUserList> getLaboranDataKalab(@Header("Authorization") String auth);
}
