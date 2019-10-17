package com.example.shailee.camo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class Activity_search extends AppCompatActivity {


    DatabaseAccess databaseAccess;
    EditText et_search;

    ArrayList<ModelItem> modelItemArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    Adapter_SearchResult adapter_searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());

        recyclerView = findViewById(R.id.recyclerView_searchResult);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        adapter_searchResult = new Adapter_SearchResult(modelItemArrayList, this, recyclerView);
        recyclerView.setAdapter(adapter_searchResult);

        et_search = (EditText) findViewById(R.id.et_search);

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String srchText  = et_search.getText().toString();
                if(srchText.length()==0){
                    modelItemArrayList.clear();
                    adapter_searchResult.notifyDataSetChanged();
                }else{
                    databaseAccess.open();

                    modelItemArrayList.clear();
                    modelItemArrayList.addAll(databaseAccess.getData(srchText));
                    adapter_searchResult.notifyDataSetChanged();

                    databaseAccess.close();
                }
            }
        });
    }
}
