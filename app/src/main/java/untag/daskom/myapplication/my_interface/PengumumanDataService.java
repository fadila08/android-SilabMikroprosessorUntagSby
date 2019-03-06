package untag.daskom.myapplication.my_interface;

import retrofit2.Call;
import retrofit2.http.GET;
import untag.daskom.myapplication.model.PengumumanList;

public interface PengumumanDataService {

    @GET("api/pengumuman")
    Call<PengumumanList> getPengumuman();

}
