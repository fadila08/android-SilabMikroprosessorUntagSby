package untag.daskom.myapplication.my_interface;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import untag.daskom.myapplication.model.DataMahasiswa;
import untag.daskom.myapplication.model.DataMahasiswaDetail;

public interface MahasiswaDataService {

    @GET("api/mahasiswa")
    Call<List<DataMahasiswa>> getMhs(@Header("Authorization") String auth);

    @GET("api/mahasiswa/{id}")
    Call<DataMahasiswaDetail> getMhsDetail(@Header("Authorization") String auth, @Path("id") String id);

}
