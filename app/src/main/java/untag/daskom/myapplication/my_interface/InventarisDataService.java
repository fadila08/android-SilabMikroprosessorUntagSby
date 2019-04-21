package untag.daskom.myapplication.my_interface;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import untag.daskom.myapplication.model.DataInventarisList;
import untag.daskom.myapplication.model.DeleteValue;
import untag.daskom.myapplication.model.InventarisDetailList;

public interface InventarisDataService {

    @GET("api/inventaris")
    Call<DataInventarisList> getInventaris(@Header("Authorization") String auth);

    @FormUrlEncoded
    @POST("api/inventaris")
    Call<InventarisDetailList> addInventaris(@Header("Authorization") String auth,
                                             @Field("nama_barang") String nama_barang,
                                             @Field("jumlah") String jumlah,
                                             @Field("kondisi") String kondisi);

    @DELETE("api/inventaris/{id}")
    Call<DeleteValue> deleteInventaris(@Header("Authorization") String auth,
                                    @Path("id") String id);

}
