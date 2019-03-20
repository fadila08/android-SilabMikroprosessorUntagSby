package untag.daskom.myapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import untag.daskom.myapplication.my_interface.LoginDataService;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static RetrofitInstance mInstance;
    private static final String BASE_URL = "http://silab.agus-hermanto.com/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static synchronized RetrofitInstance getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitInstance();
        }
        return mInstance;
    }

    public LoginDataService getApiLogin() {
        return retrofit.create(LoginDataService.class);
    }
}
