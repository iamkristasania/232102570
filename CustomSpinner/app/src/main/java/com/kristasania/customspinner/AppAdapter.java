package com.kristasania.customspinner;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

// Juan Minardi Toda (232102624)
// April 21, 2026

public class AppAdapter extends BaseAdapter {

    private Context _context;
    private List<ApplicationInfo> _appList;
    private PackageManager pm;

    public AppAdapter(Context context, List<ApplicationInfo> appList) {
        this._context = context;
        this._appList = appList;
        this.pm = context.getPackageManager();
    }

    @Override
    public int getCount() { return (_appList != null) ? _appList.size() : 0; }

    @Override
    public Object getItem(int position) { return _appList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    // View saat tertutup
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return buildView(position, parent);
    }

    // View saat dropdown (WAJIB)
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return buildView(position, parent);
    }

    // 🔥 Pakai layout YANG SAMA (spinner_a.xml) biar IDENTIK
    private View buildView(int position, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(_context);
        View view = inflater.inflate(R.layout.spinner_a, parent, false);

        TextView textViewNegara = view.findViewById(R.id.textView1);
        TextView textViewKota = view.findViewById(R.id.textViewKota);
        ImageView imageView = view.findViewById(R.id.imageView1);
        LinearLayout layoutCard = view.findViewById(R.id.layoutCard);

        ApplicationInfo appInfo = _appList.get(position);
        String appName = pm.getApplicationLabel(appInfo).toString();

        textViewNegara.setText(appName);
        textViewKota.setText(appInfo.packageName);
        imageView.setImageDrawable(pm.getApplicationIcon(appInfo));

        // warna full block (gantiin gradien negara)
        layoutCard.setBackgroundColor(getColor(appInfo.packageName));

        return view;
    }

    private int getColor(String key) {
        Random r = new Random(key.hashCode());
        return Color.rgb( 100 + r.nextInt(156),
                100 + r.nextInt(156),
                100 + r.nextInt(156));
    }
}