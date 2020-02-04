package com.itrainasia.myform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity
        implements
        AdapterView.OnItemClickListener {
String[] items = {"Item 1", "Item 2",
        "Item 3", "Item 4", "Item 5"};
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.listView);

        ArrayAdapter<String> adapter
                = new ArrayAdapter<>(

                        ListActivity.this,
                android.R.layout.simple_list_item_1,
                items
        );
        adapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        registerForContextMenu(listView);



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(
                ListActivity.this,
                "Selected :"+
                items[position],
                Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,
                menu);
        super.onCreateContextMenu(menu, v, menuInfo);

    }
}
