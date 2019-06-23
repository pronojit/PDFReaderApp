package com.example.pronojitmallick.mypdfreaderapp;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ListView lv = (ListView) findViewById(R.id.lv);

        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET}, 101);
           /* ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 3);*/
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv.setAdapter(new CustomAdapter(MainActivity.this, getPDFs()));

            }
        });
    }

    private ArrayList<PDFDoc> getPDFs() {


        {
            ArrayList<PDFDoc> pdfDocs = new ArrayList<>();
            //TARGET FOLDER
            /*Environment.getExternalStorageDirectory().getAbsolutePath();*/
            File downloadsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            /*Intent intent = new Intent(this, FolderPicker.class);
            startActivityForResult(intent, 1);*/


            PDFDoc pdfDoc;

            if (downloadsFolder.exists()) {
                //GET ALL FILES IN DOWNLOAD FOLDER
                File[] files = downloadsFolder.listFiles();

                //LOOP THRU THOSE FILES GETTING NAME AND URI
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];

                    if (file.getPath().endsWith("pdf")) {
                        pdfDoc = new PDFDoc();
                        pdfDoc.setName(file.getName());
                        pdfDoc.setPath(file.getAbsolutePath());

                        pdfDocs.add(pdfDoc);
                    }

                }
            }

            return pdfDocs;
        }
    }
}