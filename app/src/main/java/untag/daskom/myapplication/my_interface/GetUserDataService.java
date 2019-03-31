package untag.daskom.myapplication.my_interface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import untag.daskom.myapplication.model.DataUserDetailList;
import untag.daskom.myapplication.model.DataUserList;

public interface GetUserDataService {

    @GET("api/kalab")
    Call<DataUserList> getKalabData();

    @GET("api/laboran")
    Call<DataUserList> getLaboranData();

    @GET("api/aslab")
    Call<DataUserList> getAslabData();

    @GET("api/datalaboran")
    Call<DataUserList> getLaboranDataKalab(@Header("Authorization") String auth);

    @GET("api/dataaslab")
    Call<DataUserList> getAslabDataKalab(@Header("Authorization") String auth);

    @GET("api/datalaboran/{id}")
    Call<DataUserDetailList> getLaboranDetailDataKalab(@Header("Authorization") String auth, @Path("id") String id);
}
