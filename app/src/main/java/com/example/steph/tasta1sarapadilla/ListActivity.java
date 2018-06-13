package com.example.steph.tasta1sarapadilla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    ProgressBar pb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        this.setTitle("Listado");

        pb1 = (ProgressBar) findViewById(R.id.indeterminateBar);

        API.getInstance().api.getPartides().enqueue(new Callback<List<Partida>>() {
            @Override
            public void onResponse(Call<List<Partida>> call, Response<List<Partida>> response) {
                List<Partida> partides = response.body();
                ArrayList<String> noms = partides.stream().map(Partida::getNombre).collect(Collectors.toCollection(ArrayList::new));
                ListView lv = (ListView)findViewById(R.id.listview);
                ArrayAdapter<String> aa =
                        new ArrayAdapter<String>
                                (getApplicationContext(), android.R.layout.simple_expandable_list_item_1, android.R.id.text1, noms);
                lv.setAdapter(aa);

                pb1.setVisibility(ProgressBar.INVISIBLE);//al final de la tasca
            }

            @Override
            public void onFailure(Call<List<Partida>> call, Throwable t) {
                pb1.setVisibility(ProgressBar.INVISIBLE);//al final de la tasca
                Toast.makeText(getApplicationContext(),"No hay conexión", Toast.LENGTH_LONG).show();
            }
        });

    }
}
