package pratikum.com.latihanecommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.w3c.dom.Text;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import cz.msebera.android.httpclient.Header;
import pratikum.com.latihanecommerce.network.URL;

public class DetailProdukActivity extends AppCompatActivity {

    private TextView tvNamaProduk;
    private TextView tvHarga;
    private TextView tvDesc;
    private TextView tvDiskon;
    private TextView tvBerat;
    private EditText etJumlah;
    private Button btCart;

    private String id;
    private String sessionId;
    private String jumlah;
    private String dateOrder;
    private String jamOrder;

    private AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
        initView();
        setView();

        btCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addToCart();
            }
        });
    }

    public void initView()
    {
        tvNamaProduk = (TextView) findViewById(R.id.tv_detail_nama_produk);
        tvHarga = (TextView) findViewById(R.id.tv_detail_harga);
        tvDesc = (TextView) findViewById(R.id.tv_detail_desc);
        tvDiskon = (TextView) findViewById(R.id.tv_detail_diskon);
        tvBerat = (TextView) findViewById(R.id.tv_detail_berat);
        btCart = (Button) findViewById(R.id.bt_add_to_cart);
        etJumlah = (EditText) findViewById(R.id.et_jumlah);
    }

    public void setView()
    {
        Intent intent = getIntent();
        tvNamaProduk.setText(intent.getStringExtra("nama"));
        tvHarga.setText("Harga : Rp " + intent.getStringExtra("harga"));
        tvDiskon.setText("Diskon : " + intent.getStringExtra("diskon") + "%");
        tvBerat.setText("Berat : " + intent.getStringExtra("berat"));
        tvDesc.setText(intent.getStringExtra("desc").replace("&lt;br /&gt;", "").replace("&lt;/p&gt", ""));
    }

    public void addToCart()
    {
        id = getIntent().getStringExtra("id");
        sessionId = 1124565789 + "";
        getCurrentTime();
        if (etJumlah.getText().length() != 0)
        {
            jumlah = etJumlah.getText().toString() + "";
        }
        else
        {
            jumlah = "1";
        }
        client = new AsyncHttpClient();
        client.get(DetailProdukActivity.this, "http://poerwiyanto.web.ugm.ac.id/api.php?action=insert_shopping_cart&" + "id_produk=" + id + "&jumlah=" + jumlah + "&tgl_order_temp=" + dateOrder + "&jam_order_temp=" + jamOrder + "&id_session=" + sessionId, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                Toast.makeText(DetailProdukActivity.this, "Succesful add to cart", Toast.LENGTH_SHORT).show();
                String response = new String(responseBody, Charset.forName("UTF8"));
                Log.i("Response", response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
            {
                Toast.makeText(DetailProdukActivity.this, "Failed to add to cart", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getCurrentTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateOrder = sdf.format(new Date());
        sdf = new SimpleDateFormat("HH:mm:ss");
        jamOrder = sdf.format(new Date());
        Log.i("Date : ", dateOrder);
        Log.i("Time : ", jamOrder);
    }
}
