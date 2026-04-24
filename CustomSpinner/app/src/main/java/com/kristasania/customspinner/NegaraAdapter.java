package com.kristasania.customspinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.List;

public class NegaraAdapter extends BaseAdapter {
    private Context _context;
    private List<String> _negaraList;

    public NegaraAdapter(Context context, List<String> negaraList) {
        this._context = context;
        this._negaraList = negaraList;
    }

    @Override
    public int getCount() {
        return (_negaraList != null) ? _negaraList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(_context);
        convertView = inflater.inflate(R.layout.spinner_a, parent, false);

        String negara = _negaraList.get(position);

        TextView textViewNegara = convertView.findViewById(R.id.textView1);
        TextView textViewKota = convertView.findViewById(R.id.textViewKota);
        ImageView imageView = convertView.findViewById(R.id.imageView1);
        LinearLayout layoutCard = convertView.findViewById(R.id.layoutCard);

        textViewNegara.setText(negara);

        switch (negara) {
            case "Korea":
                imageView.setImageResource(R.drawable.korea1);
                textViewKota.setText("Korea");
                layoutCard.setBackgroundColor(ContextCompat.getColor(_context, R.color.gradientPink));
                break;

            case "France":
                imageView.setImageResource(R.drawable.paris);
                textViewKota.setText("Paris");
                layoutCard.setBackgroundColor(ContextCompat.getColor(_context, R.color.gradientOrange));
                break;

            case "Indonesia":
                imageView.setImageResource(R.drawable.monas);
                textViewKota.setText("Jakarta");
                layoutCard.setBackgroundColor(ContextCompat.getColor(_context, R.color.gradientBlue));
                break;

            case "USA":
                imageView.setImageResource(R.drawable.liberty1);
                textViewKota.setText("Washington, D.C.");
                layoutCard.setBackgroundColor(ContextCompat.getColor(_context, R.color.gray));
                break;

            case "England":
                imageView.setImageResource(R.drawable.bigben);
                textViewKota.setText("London");
                layoutCard.setBackgroundColor(ContextCompat.getColor(_context, R.color.gradientTeal));
                break;
        }

        return convertView;
    }
}