package com.exanple.wallpaperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ArrayList<ImageModel>imageModelArrayList;
    Adapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CardView mnatue,mbus,mtrain,mcar,mtranding;
    EditText editText;
    ImageView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recy);
        mbus=findViewById(R.id.bus);
        mcar=findViewById(R.id.car);
        mnatue=findViewById(R.id.nature);
        mtrain=findViewById(R.id.train);
        mtranding=findViewById(R.id.tranding);
        editText=findViewById(R.id.edittext);
        search=findViewById(R.id.search);

        imageModelArrayList=new ArrayList<>();
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter=new Adapter(getApplicationContext(),imageModelArrayList);
        recyclerView.setAdapter(adapter);
        findPhotos();
        mnatue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "nature";
                getSearchImage(query);
            }
        });
        mbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "bus";
                getSearchImage(query);
            }
        });
        mcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "car";
                getSearchImage(query);
            }
        });
        mtrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "train";
                getSearchImage(query);
            }
        });

        mtranding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPhotos();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = editText.getText().toString().trim().toLowerCase();
                getSearchImage(query);
            }
        });


    }

    private void getSearchImage(String query) {
        ApiUtilities.getApiInterface().getSearchImage(query,1,88).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                imageModelArrayList.clear();
                if (response.isSuccessful()){
                    imageModelArrayList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });

    }

    private void findPhotos() {
        ApiUtilities.getApiInterface().getImage(1,88).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if (response.isSuccessful()){
                    imageModelArrayList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });

    }
}