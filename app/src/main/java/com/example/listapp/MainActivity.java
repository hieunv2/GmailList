package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextWatcher;
import android.text.Editable;
import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;

    public class MainActivity extends AppCompatActivity {


    List<ItemModal> items;
    List<ItemModal> itemSearchs;
    EditText searchText;
    Button btnFavorite;
    ItemAdapter adapter;

    boolean showFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        items = new ArrayList<ItemModal>();
        itemSearchs = new ArrayList<ItemModal>();

        searchText = findViewById(R.id.edt_keyword);
        btnFavorite = findViewById(R.id.btn_favorite);

        items.add(new ItemModal("Hieu Nguyen Van",R.drawable.image1,"KTMT-06 Dai Hoc BKHN","12:08 PM"));
        items.add(new ItemModal("Tran Ngoc Vinh",R.drawable.image2,"KTMT-06 Dai Hoc BKHN","15:09 PM"));
        items.add(new ItemModal("Hoang Duc Truong",R.drawable.image3,"KTMT-06 Dai Hoc BKHN","10:00 AM"));
        items.add(new ItemModal("Nguyen Doan Nam",R.drawable.image4,"KTMT-06 Dai Hoc BKHN","14:06 PM"));
        items.add(new ItemModal("Nguyen Ba Quan",R.drawable.image5,"KTMT-06 Dai Hoc BKHN","08:0 AM"));
        items.add(new ItemModal("Dao Quang Trung",R.drawable.image6,"KTMT-06 Dai Hoc BKHN","16:26 PM"));
        items.add(new ItemModal("Khi Dang Quan",R.drawable.image7,"KTMT-06 Dai Hoc BKHN","12:45 PM"));
        items.add(new ItemModal("Tran Van Nghiem",R.drawable.image8,"KTMT-06 Dai Hoc BKHN","17:15 PM"));
        items.add(new ItemModal("Hoang Duc Dung",R.drawable.image9,"KTMT-06 Dai Hoc BKHN","06:10 AM"));
        items.add(new ItemModal("Nguyen Van Hai",R.drawable.image10,"KTMT-06 Dai Hoc BKHN","15:08 PM"));


        itemSearchs.addAll(items);

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ItemAdapter(items,itemSearchs,showFavorite);
        recyclerView.setAdapter(adapter);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFavorite = !showFavorite;
                adapter.getFilter().filter("");
            }
        });

    }
}
