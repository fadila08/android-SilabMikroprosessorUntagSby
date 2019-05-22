package untag.daskom.myapplication.my_interface;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import untag.daskom.myapplication.model.DataMahasiswa;

public interface MahasiswaDataService {

    @GET("api/mahasiswa")
    Call<List<DataMahasiswa>> getMhs(@Header("Authorization") String auth);

}
