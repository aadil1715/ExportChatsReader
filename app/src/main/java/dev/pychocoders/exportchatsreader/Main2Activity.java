package dev.pychocoders.exportchatsreader;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tv = findViewById(R.id.textView);
        Intent inet = getIntent();
        String chat = (String) inet.getSerializableExtra("data");
        tv.setText(chat);
    }
}
