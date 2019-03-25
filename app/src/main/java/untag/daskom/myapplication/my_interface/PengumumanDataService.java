package untag.daskom.myapplication.my_interface;

import retrofit2.Call;
import retrofit2.http.GET;
import untag.daskom.myapplication.model.PengumumanList;

public interface PengumumanDataService {

    @GET("api/datapengumuman")
    Call<PengumumanList> getPengumuman();

}
