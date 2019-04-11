package dev.pychocoders.exportchatsreader;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       ListView lv = (ListView) findViewById(R.id.listview);
        Intent inet = getIntent();
        String chat = (String) inet.getSerializableExtra("data");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(chat);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        lv.setAdapter(adapter);
    }
}
