package pratikum.com.latihanecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import pratikum.com.latihanecommerce.R;
import pratikum.com.latihanecommerce.ShoppingCartActivity;

/**
 * Created by lucgu on 11/10/16.
 */
public class CartListAdapter extends BaseAdapter
{

    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public CartListAdapter(Context context,
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
        TextView tvJumlah;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_cart, parent, false);
        resultp = data.get(position);

        tvNamaProduk = (TextView) itemView.findViewById(R.id.tv_nama_produk);
        tvHarga = (TextView) itemView.findViewById(R.id.tv_harga);
        tvTanggal = (TextView) itemView.findViewById(R.id.tv_tanggal);
        tvJumlah = (TextView) itemView.findViewById(R.id.tv_jumlah);

        final String namaProduk = resultp.get(ShoppingCartActivity.NAMA_PRODUK);
        final String harga = resultp.get(ShoppingCartActivity.HARGA);
        String tanggal = resultp.get(ShoppingCartActivity.TANGGAL);
        final String berat = resultp.get(ShoppingCartActivity.BERAT);
        final String desc = resultp.get(ShoppingCartActivity.DESC);
        final String diskon = resultp.get(ShoppingCartActivity.DISKON);
        final String id = resultp.get(ShoppingCartActivity.ID) + "";
        String jumlah = resultp.get(ShoppingCartActivity.JUMLAH);

        tvNamaProduk.setText(namaProduk);
        tvHarga.setText("Rp " + harga);
        tvTanggal.setText("Tanggal : " + tanggal);
        tvJumlah.setText("Jumlah : " + jumlah);

        return itemView;
    }
}
