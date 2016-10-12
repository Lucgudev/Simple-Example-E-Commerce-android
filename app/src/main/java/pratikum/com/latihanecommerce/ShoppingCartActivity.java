package pratikum.com.latihanecommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import pratikum.com.latihanecommerce.adapter.CartListAdapter;

public class ShoppingCartActivity extends AppCompatActivity {

    private ListView lvCart;
    private AsyncHttpClient client;
    private String sessionId;

    public static String NAMA_PRODUK = "nama_produk";
    public static String HARGA = "harga";
    public static String TANGGAL = "tgl_masuk";
    public static String DESC = "desc";
    public static String DISKON = "diTextskon";
    public static String BERAT = "berat";
    public static String ID = "id";
    public static String JUMLAH = "jumlah";

    private ArrayList<HashMap<String, String>> list;
    private HashMap<String, String> map;
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private CartListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_chart);
        initView();
        getData();
    }

    public void initView()
    {
        lvCart = (ListView) findViewById(R.id.lv_shopping_cart);
    }

    public void getData()
    {
        list = new ArrayList<>();
        sessionId = 1124565789 + "";
        client = new AsyncHttpClient();
        client.get(ShoppingCartActivity.this, "http://poerwiyanto.web.ugm.ac.id/api.php?action=get_shopping_cart&id_session=" + sessionId, new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                String response = new String(responseBody, Charset.forName("UTF8"));
                Log.i("Response", response);
                try {
                    jsonObject = new JSONObject(response);
                    jsonArray = jsonObject.getJSONArray("shopping_cart");
                    for (int i = 0; i <= jsonArray.length() - 1; i++)
                    {
                        map = new HashMap<String, String>();
                        jsonObject = jsonArray.getJSONObject(i);
                        map.put(NAMA_PRODUK, jsonObject.getString("nama_produk"));
                        map.put(HARGA, jsonObject.getString("harga"));
                        map.put(TANGGAL, jsonObject.getString("tgl_order_temp"));
                        map.put(DESC, jsonObject.getString("deskripsi"));
                        map.put(DISKON, jsonObject.getString("diskon"));
                        map.put(BERAT, jsonObject.getString("berat"));
                        map.put(ID, jsonObject.getString("id_produk"));
                        map.put(JUMLAH, jsonObject.getString("jumlah"));
                        list.add(map);
                    }
                    setView();

                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
            {

            }
        });
    }

    public void setView()
    {
        adapter = new CartListAdapter(ShoppingCartActivity.this, list);
        lvCart.setAdapter(adapter);
    }
}
