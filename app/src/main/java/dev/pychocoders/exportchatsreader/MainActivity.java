package dev.pychocoders.exportchatsreader;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                i.setType("*/*");

                startActivityForResult(i, 123);
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
                try {
                    BufferedReader fr = new BufferedReader(new FileReader(chat));
                    StringBuilder st = new StringBuilder();
                    String line;
                    while ((line = fr.readLine()) != null){
                        st.append(line).append("\n");
                        line = fr.readLine();
                    }
                    Intent i;
                    i = new Intent(MainActivity.this,Main2Activity.class);
                    i.putExtra("data",st.toString());
                    startActivity(i);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123 && resultCode == Activity.RESULT_OK){
            Uri uri;
            if(data != null){
                uri = data.getData();
                if (uri != null) {
                    String path = uri.getPath();
                    launchFile(path);
                }
                else{
                    Toast.makeText(MainActivity.this,"Cant access ",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void launchFile(String path) {
        String[] paths = path.split(":");
        File chat = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + paths[1]);
        try {
            BufferedReader fr = new BufferedReader(new FileReader(chat));
            StringBuilder st = new StringBuilder();
            String line;
            while ((line = fr.readLine()) != null){
                st.append(line).append("\n");
                line = fr.readLine();
            }
            Intent i;
            i = new Intent(MainActivity.this, Main2Activity.class);
            i.putExtra("data", st.toString());
            startActivity(i);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
