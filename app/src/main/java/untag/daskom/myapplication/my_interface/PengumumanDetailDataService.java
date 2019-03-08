package untag.daskom.myapplication.my_interface;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import untag.daskom.myapplication.model.PengumumanDetailList;

public interface PengumumanDetailDataService {

    @GET("api/pengumuman/{id}")
    Call<PengumumanDetailList> getPengumumanDetail(@Path("id") String id);


}
