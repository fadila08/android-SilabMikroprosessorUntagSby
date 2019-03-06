package untag.daskom.myapplication.my_interface;

import retrofit2.Call;
import retrofit2.http.GET;
import untag.daskom.myapplication.model.DataUserList;

public interface GetUserDataService {

    @GET("api/datakalab")
    Call<DataUserList> getKalabData();

    @GET("api/datalaboran")
    Call<DataUserList> getLaboranData();

    @GET("api/dataaslab")
    Call<DataUserList> getAslabData();
}
