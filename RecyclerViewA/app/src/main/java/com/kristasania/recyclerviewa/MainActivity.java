package com.kristasania.recyclerviewa;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    Spinner spinnerBenua;
    RecyclerView recyclerView1;

    List<NegaraModel> negaraModelList;
    NegaraAdapter negaraAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerBenua = findViewById(R.id.spinnerBenua);
        recyclerView1 = findViewById(R.id.recyclerView1);

        // ==================== SPINNER ====================

        String[] benua = {
                "Asia",
                "Eropa",
                "Afrika",
                "Amerika Utara",
                "Amerika Selatan",
                "Australia/Oseania",
                "Antartika"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        benua
                );

        spinnerBenua.setAdapter(adapter);

        // ==================== RECYCLERVIEW ====================

        recyclerView1.setLayoutManager(
                new LinearLayoutManager(this));

        negaraModelList = new ArrayList<>();

        negaraAdapter =
                new NegaraAdapter(this, negaraModelList);

        recyclerView1.setAdapter(negaraAdapter);

        // ==================== EVENT SPINNER ====================

        spinnerBenua.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener()
                {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view,
                                               int position,
                                               long id)
                    {
                        String selected =
                                spinnerBenua.getSelectedItem().toString();

                        loadNegara(selected);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });
    }

    // ==================== LOAD NEGARA ====================

    private void loadNegara(String benua)
    {
        negaraModelList.clear();

        NegaraModel nm;

        // ---------------- ASIA ----------------

        if(benua.equals("Asia"))
        {
            String[][] asia = {
                    {"Indonesia", "Negara kepulauan terbesar di dunia.", "Indonesia"},
                    {"Japan", "Terkenal dengan anime dan teknologi.", "Japan"},
                    {"China", "Negara dengan populasi terbesar di dunia.", "China"},
                    {"India", "Terkenal dengan Taj Mahal.", "India"},
                    {"South Korea", "Terkenal dengan K-Pop dan drama Korea.", "South-Korea"},
                    {"Thailand", "Terkenal dengan wisata pantai.", "Thailand"},
                    {"Malaysia", "Terkenal dengan Menara Petronas.", "Malaysia"},
                    {"Singapore", "Negara maju di Asia Tenggara.", "Singapore"},
                    {"Vietnam", "Terkenal dengan kuliner khas.", "Vietnam"},
                    {"Philippines", "Negara kepulauan di Asia Tenggara.", "Philippines"},
                    {"Saudi Arabia", "Pusat agama Islam.", "Saudi-Arabia"},
                    {"United Arab Emirates", "Terkenal dengan Dubai.", "United-Arab-Emirates"},
                    {"Qatar", "Negara kaya minyak.", "Qatar"},
                    {"Pakistan", "Negara dengan budaya Islam kuat.", "Pakistan"},
                    {"Turkey", "Negara penghubung Asia dan Eropa.", "Turkey"},
                    {"Nepal", "Terkenal dengan Gunung Everest.", "Nepal"},
                    {"Bangladesh", "Negara padat penduduk.", "Bangladesh"},
                    {"Sri Lanka", "Terkenal dengan teh.", "Sri-Lanka"},
                    {"Cambodia", "Memiliki Angkor Wat.", "Cambodia"},
                    {"Laos", "Negara tanpa laut di Asia Tenggara.", "Laos"}
            };

            for(String[] data : asia)
            {
                nm = new NegaraModel();
                nm.setNama(data[0]);
                nm.setDeskripsi(data[1]);
                nm.setUrl("https://icons.iconarchive.com/icons/custom-icon-design/all-country-flag/48/" +
                        data[2] + "-Flag-icon.png");
                negaraModelList.add(nm);
            }
        }

        // ---------------- EROPA ----------------

        else if(benua.equals("Eropa"))
        {
            String[][] eropa = {
                    {"Germany", "Terkenal dengan industri mobil.", "Germany"},
                    {"France", "Terkenal dengan Menara Eiffel.", "France"},
                    {"Italy", "Negara asal pizza dan pasta.", "Italy"},
                    {"Spain", "Terkenal dengan sepak bola.", "Spain"},
                    {"Belgium", "Terkenal dengan coklat dan waffle.", "Belgium"},
                    {"Netherlands", "Terkenal dengan kincir angin.", "Netherlands"},
                    {"Portugal", "Negara asal Cristiano Ronaldo.", "Portugal"},
                    {"Switzerland", "Terkenal dengan Alpen.", "Switzerland"},
                    {"Sweden", "Negara dengan kualitas hidup tinggi.", "Sweden"},
                    {"Norway", "Terkenal dengan fjord.", "Norway"},
                    {"Denmark", "Negara Viking.", "Denmark"},
                    {"Poland", "Negara bersejarah di Eropa.", "Poland"},
                    {"Austria", "Terkenal dengan musik klasik.", "Austria"},
                    {"Greece", "Tempat lahir demokrasi.", "Greece"},
                    {"Finland", "Negara paling bahagia.", "Finland"},
                    {"Ireland", "Terkenal dengan padang hijau.", "Ireland"},
                    {"Croatia", "Terkenal dengan wisata laut.", "Croatia"},
                    {"Hungary", "Negara di Eropa Tengah.", "Hungary"},
                    {"Romania", "Terkenal dengan kastil Dracula.", "Romania"},
                    {"Ukraine", "Negara terbesar kedua di Eropa.", "Ukraine"}
            };

            for(String[] data : eropa)
            {
                nm = new NegaraModel();
                nm.setNama(data[0]);
                nm.setDeskripsi(data[1]);
                nm.setUrl("https://icons.iconarchive.com/icons/custom-icon-design/all-country-flag/48/" +
                        data[2] + "-Flag-icon.png");
                negaraModelList.add(nm);
            }
        }

        // ---------------- AFRIKA ----------------

        else if(benua.equals("Afrika"))
        {
            String[][] afrika = {
                    {"Egypt", "Terkenal dengan piramida.", "Egypt"},
                    {"Nigeria", "Populasi terbesar di Afrika.", "Nigeria"},
                    {"South Africa", "Terkenal dengan safari.", "South-Africa"},
                    {"Kenya", "Memiliki padang savana.", "Kenya"},
                    {"Morocco", "Terkenal dengan gurun Sahara.", "Morocco"},
                    {"Algeria", "Negara terbesar di Afrika.", "Algeria"},
                    {"Ethiopia", "Salah satu negara tertua.", "Ethiopia"},
                    {"Ghana", "Terkenal dengan tambang emas.", "Ghana"},
                    {"Tunisia", "Negara Afrika Utara.", "Tunisia"},
                    {"Sudan", "Negara dekat Sungai Nil.", "Sudan"},
                    {"Libya", "Negara penghasil minyak.", "Libya"},
                    {"Uganda", "Terkenal dengan gorila.", "Uganda"},
                    {"Tanzania", "Memiliki Gunung Kilimanjaro.", "Tanzania"},
                    {"Zimbabwe", "Terkenal dengan Victoria Falls.", "Zimbabwe"},
                    {"Cameroon", "Miniatur Afrika kini.", "Cameroon"}
            };

            for(String[] data : afrika)
            {
                nm = new NegaraModel();
                nm.setNama(data[0]);
                nm.setDeskripsi(data[1]);
                nm.setUrl("https://icons.iconarchive.com/icons/custom-icon-design/all-country-flag/48/" +
                        data[2] + "-Flag-icon.png");
                negaraModelList.add(nm);
            }
        }

        // ---------------- AMERIKA UTARA ----------------

        else if(benua.equals("Amerika Utara"))
        {
            String[][] utara = {
                    {"United States", "Ekonomi terbesar dunia.", "United-States"},
                    {"Canada", "Terkenal dengan sirup.", "Canada"},
                    {"Mexico", "Terkenal dengan taco.", "Mexico"},
                    {"Cuba", "Negara pulau karibia.", "Cuba"},
                    {"Jamaica", "Terkenal dengan reggae.", "Jamaica"},
                    {"Panama", "Memiliki Terusan Panama.", "Panama"},
                    {"Costa Rica", "Terkenal dengan alam hijau.", "Costa-Rica"},
                    {"Haiti", "Negara karibia.", "Haiti"},
                    {"Dominican Republic", "Wisata pantai tropis.", "Dominican-Republic"},
                    {"Greenland", "Pulau es terbesar.", "Greenland"}
            };

            for(String[] data : utara)
            {
                nm = new NegaraModel();
                nm.setNama(data[0]);
                nm.setDeskripsi(data[1]);
                nm.setUrl("https://icons.iconarchive.com/icons/custom-icon-design/all-country-flag/48/" +
                        data[2] + "-Flag-icon.png");
                negaraModelList.add(nm);
            }
        }

        // ---------------- AMERIKA SELATAN ----------------

        else if(benua.equals("Amerika Selatan"))
        {
            String[][] selatan = {
                    {"Brazil", "Terkenal dengan sepak bola.", "Brazil"},
                    {"Argentina", "Negara asal Lionel Messi.", "Argentina"},
                    {"Chile", "Negara panjang di selatan.", "Chile"},
                    {"Peru", "Memiliki Machu Picchu.", "Peru"},
                    {"Colombia", "Terkenal dengan kopi.", "Colombia"},
                    {"Uruguay", "Negara kecil maju.", "Uruguay"},
                    {"Paraguay", "Negara tanpa laut.", "Paraguay"},
                    {"Bolivia", "Memiliki pegunungan Andes.", "Bolivia"},
                    {"Venezuela", "Negara penghasil minyak.", "Venezuela"},
                    {"Ecuador", "Dilalui garis khatulistiwa.", "Ecuador"},
                    {"Guyana", "Negara kecil di utara.", "Guyana"},
                    {"Suriname", "Negara kecil di Amerika Selatan.", "Suriname"}
            };

            for(String[] data : selatan)
            {
                nm = new NegaraModel();
                nm.setNama(data[0]);
                nm.setDeskripsi(data[1]);
                nm.setUrl("https://icons.iconarchive.com/icons/custom-icon-design/all-country-flag/48/" +
                        data[2] + "-Flag-icon.png");
                negaraModelList.add(nm);
            }
        }

        // ---------------- AUSTRALIA / OSEANIA ----------------

        else if(benua.equals("Australia/Oseania"))
        {
            String[][] oseania = {
                    {"Australia", "Terkenal dengan kangguru.", "Australia"},
                    {"New Zealand", "Pemandangan alam indah.", "New-Zealand"},
                    {"Fiji", "Negara kepulauan Pasifik.", "Fiji"},
                    {"Papua New Guinea", "Budaya suku beragam.", "Papua-New-Guinea"},
                    {"Samoa", "Negara kecil di Pasifik.", "Samoa"},
                    {"Tonga", "Kerajaan di Pasifik.", "Tonga"},
                    {"Vanuatu", "Memiliki banyak gunung api.", "Vanuatu"},
                    {"Solomon Islands", "Negara kepulauan Oseania.", "Solomon-Islands"},
                    {"Kiribati", "Negara kecil di Pasifik.", "Kiribati"},
                    {"Micronesia", "Negara kepulauan tropis.", "Micronesia"}
            };

            for(String[] data : oseania)
            {
                nm = new NegaraModel();
                nm.setNama(data[0]);
                nm.setDeskripsi(data[1]);
                nm.setUrl("https://icons.iconarchive.com/icons/custom-icon-design/all-country-flag/48/" +
                        data[2] + "-Flag-icon.png");
                negaraModelList.add(nm);
            }
        }

        // ---------------- ANTARTIKA ----------------

        else if(benua.equals("Antartika"))
        {
            nm = new NegaraModel();
            nm.setNama("Antarctica");
            nm.setDeskripsi("Benua terdingin di dunia dan tidak memiliki negara resmi.");
            nm.setUrl("https://icons.iconarchive.com/icons/custom-icon-design/all-country-flag/48/Antarctica-Flag-icon.png");
            negaraModelList.add(nm);
        }

        negaraAdapter.notifyDataSetChanged();
    }
}