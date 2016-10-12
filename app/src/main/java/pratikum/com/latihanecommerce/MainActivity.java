package pratikum.com.latihanecommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import pratikum.com.latihanecommerce.adapter.ShopListAdapter;

public class MainActivity extends AppCompatActivity
{
    public static String NAMA_PRODUK = "nama_produk";
    public static String HARGA = "harga";
    public static String TANGGAL = "tgl_masuk";
    public static String DESC = "desc";
    public static String DISKON = "diTextskon";
    public static String BERAT = "berat";
    public static String ID = "id";

    private ListView lvShoppingList;
    private Button btToCart;
    private AsyncHttpClient client;
    private ArrayList<HashMap<String, String>> list;
    private HashMap<String, String> map;
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private ShopListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new AsyncHttpClient();
        list = new ArrayList<>();
        initView();
        getData();

        btToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });

    }

    public void initView()
    {
        lvShoppingList = (ListView) findViewById(R.id.lv_shopping_list);
        btToCart = (Button) findViewById(R.id.bt_to_cart);
    }

    public void getData()
    {
        String url = "http://poerwiyanto.web.ugm.ac.id/api.php?action=get_produk";
        client.get(MainActivity.this, url, new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                String response = new String(responseBody, Charset.forName("UTF8"));
                Log.i("Response", response);
                try {
                    jsonObject = new JSONObject(response);
                    jsonArray = jsonObject.getJSONArray("produk");
                    for (int i = 0; i <= jsonArray.length() - 1; i++)
                    {
                        map = new HashMap<String, String>();
                        jsonObject = jsonArray.getJSONObject(i);
                        map.put(NAMA_PRODUK, jsonObject.getString("nama_produk"));
                        map.put(HARGA, jsonObject.getString("harga"));
                        map.put(TANGGAL, jsonObject.getString("tgl_masuk"));
                        map.put(DESC, jsonObject.getString("deskripsi"));
                        map.put(DISKON, jsonObject.getString("diskon"));
                        map.put(BERAT, jsonObject.getString("berat"));
                        map.put(ID, jsonObject.getString("id_produk"));
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
        Log.i("Response", "setView");
        adapter = new ShopListAdapter(MainActivity.this, list);
        lvShoppingList.setAdapter(adapter);
    }
}
