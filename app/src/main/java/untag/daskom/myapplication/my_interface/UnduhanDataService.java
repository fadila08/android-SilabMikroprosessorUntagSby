package untag.daskom.myapplication.my_interface;

import retrofit2.Call;
import retrofit2.http.GET;
import untag.daskom.myapplication.model.UnduhanList;

public interface UnduhanDataService {

    @GET("api/unduhan/read")
    Call<UnduhanList> getUnduhan();

}
