package dev.pychocoders.exportchatsreader;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText c;
        Button b,browse;

        browse = (Button)findViewById(R.id.browse);
        b = (Button)findViewById(R.id.button);

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("text/*");
                startActivity(i);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/ECR");
                if(!folder.exists()) {
                    folder.mkdir();
                }
                File chat = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/ECR/chat.txt");
                if(!chat.exists()) {
                    try {
                        chat.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(chat));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                String st = new String();
                try {
                    st = br.readLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent i;
                i = new Intent(MainActivity.this,Main2Activity.class);
                i.putExtra("data",st);
                startActivity(i);
            }
        });

    }
}
