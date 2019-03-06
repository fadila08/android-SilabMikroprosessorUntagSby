package untag.daskom.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Unduhan extends AppCompatActivity {
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<DataRvUnduhan> lstData;
    private RecyclerView myrv;
    private RVAdapterUnduhan myAdapter;

    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unduhan);

        lstData =   new ArrayList<>();
        myrv    =   findViewById(R.id.rvunduhan);
        initDataset(myrv, 0);
    }
    private void initDataset(RecyclerView myrv, int i){
        String URL      =   "http://ptmdaskom.nand-project.com/soal_4.php?tipe=2";
        ArrayRequest    =   new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {

            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        DataRvUnduhan model = new DataRvUnduhan();
                        model.setJudul_unduhan(jsonObject.getString("Judul Unduhan"));
                        model.setTanggal_upload_unduhan(jsonObject.getString("Tanggal Upload"));
                        lstData.add(model);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setRvadapter(lstData);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
            }
        });
        requestQueue    =   Volley.newRequestQueue(Unduhan.this);
        requestQueue.add(ArrayRequest);
    }
    public void setRvadapter(List<DataRvUnduhan>lst) {
        myAdapter       =   new RVAdapterUnduhan(Unduhan.this, lst);
        layoutManager   =   new LinearLayoutManager(this);
        myrv.setLayoutManager(layoutManager);
        myrv.setAdapter(myAdapter);
    }
}
