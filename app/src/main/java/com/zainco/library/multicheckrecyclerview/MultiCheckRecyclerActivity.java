package com.zainco.library.multicheckrecyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zainco.library.R;

import java.util.ArrayList;
import java.util.List;

public class MultiCheckRecyclerActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    private Adapter adapter;
    private LinearLayoutManager layoutManager;

    // dummy list of items to be populated manually
    List<Model> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multicheck_recycler);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        Adapter adapter = new Adapter();
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fillItems();

        adapter.loadItems(items);
    }

    private void fillItems() {
        for (int x = 0; x <= 100; x++) {
            Model model = new Model();
            model.setPosition(x + 1);

            items.add(model);
        }
    }
}
