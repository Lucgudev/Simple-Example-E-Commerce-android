package pratikum.com.latihanecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import pratikum.com.latihanecommerce.DetailProdukActivity;
import pratikum.com.latihanecommerce.MainActivity;
import pratikum.com.latihanecommerce.R;

/**
 * Created by lucgu on 11/10/16.
 */
public class ShopListAdapter extends BaseAdapter
{

    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ShopListAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist)
    {
        this.context = context;
        data = arraylist;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        TextView tvNamaProduk;
        TextView tvHarga;
        TextView tvTanggal;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_shop, parent, false);
        resultp = data.get(position);

        tvNamaProduk = (TextView) itemView.findViewById(R.id.tv_nama_produk);
        tvHarga = (TextView) itemView.findViewById(R.id.tv_harga);
        tvTanggal = (TextView) itemView.findViewById(R.id.tv_tanggal);

        final String namaProduk = resultp.get(MainActivity.NAMA_PRODUK);
        final String harga = resultp.get(MainActivity.HARGA);
        String tanggal = resultp.get(MainActivity.TANGGAL);
        final String berat = resultp.get(MainActivity.BERAT);
        final String desc = resultp.get(MainActivity.DESC);
        final String diskon = resultp.get(MainActivity.DISKON);
        final String id = resultp.get(MainActivity.ID) + "";

        tvNamaProduk.setText(namaProduk);
        tvHarga.setText("Rp " + harga);
        tvTanggal.setText("Tanggal : " + tanggal);

        itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context.getApplicationContext(), DetailProdukActivity.class);
                intent.putExtra("nama", namaProduk);
                intent.putExtra("harga", harga);
                intent.putExtra("desc", desc);
                intent.putExtra("diskon", diskon);
                intent.putExtra("berat", berat);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });

        return itemView;
    }
}
