package com.ahmadullah.alldocumentsreader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class List_Files_Activity extends AppCompatActivity {

    private Adapter_Doc_Files adapter;
    private ArrayList<model_doc_File> itemsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RelativeLayout rl_no_files;
    private TextView txt_file_name;
    private final int REQ_FILE_ACCESS = 444;
    private String file_format = "doc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_files);

        rl_no_files = findViewById(R.id.rl_no_files);
        txt_file_name = findViewById(R.id.txt_file_name);
        recyclerView = findViewById(R.id.recyclerView);

        file_format = getIntent().getStringExtra("format");
        if (file_format == null) {
            file_format = "doc";
        }
        txt_file_name.setText("All " + file_format + " Files");

        if (checkPermission()) {
            // Permission granted
        } else {
            requestPermission();
        }
    }

    private void setAdapter(ArrayList<model_doc_File> itemsList) {
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter_Doc_Files(
                List_Files_Activity.this,
                itemsList
        );
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );
        recyclerView.setAdapter(adapter);
        
        if (itemsList.size() < 1) {
            if (rl_no_files != null) {
                rl_no_files.setVisibility(View.VISIBLE);
            }
        } else {
            if (rl_no_files != null) {
                rl_no_files.setVisibility(View.GONE);
            }
        }
    }

    public ArrayList<model_doc_File> fileListDocx(String file_format) {
        String path = Environment.getExternalStorageDirectory().getPath();
        ArrayList<model_doc_File> documentArrayList = new ArrayList<>();
        String format1 = "";
        String format2 = "";
        
        if (file_format.equals("doc")) {
            format1 = "docx";
            format2 = "doc";
        } else if (file_format.equals("xls")) {
            format1 = "xlsx";
            format2 = "xls";
        } else if (file_format.equals("ppt")) {
            format1 = "pptx";
            format2 = "ppt";
        } else if (file_format.equals("pdf")) {
            format1 = "pdf";
            format2 = "PDF";
        } else if (file_format.equals("rtf")) {
            format1 = "rtf";
            format2 = "RTF";
        } else if (file_format.equals("csv")) {
            format1 = "csv";
            format2 = "CSV";
        } else if (file_format.equals("json")) {
            format1 = "json";
            format2 = "JSON";
        } else if (file_format.equals("xml")) {
            format1 = "xml";
            format2 = "XML";
        } else if (file_format.equals("html")) {
            format1 = "html";
            format2 = "HTML";
        } else if (file_format.equals("java")) {
            format1 = "java";
            format2 = "JAVA";
        } else if (file_format.equals("kt")) {
            format1 = "kt";
            format2 = "KT";
        }

        Uri uri = MediaStore.Files.getContentUri("external");
        String[] projection = {MediaStore.Files.FileColumns.DATA};
        String orderBy = MediaStore.Files.FileColumns.DATE_TAKEN;
        Cursor cursor = List_Files_Activity.this.getContentResolver().query(
                uri, 
                projection, 
                MediaStore.Files.FileColumns.DATA + " like ? ", 
                new String[]{"%" + path + "%"},
                orderBy + " DESC"
        );
        
        if (cursor != null) {
            File[] files = new File[cursor.getCount()];
            cursor.moveToFirst();
            int i = 0;
            
            if (cursor.getCount() != 0) {
                do {
                    files[i] = new File(cursor.getString(
                            cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
                    ));
                    
                    if (files[i].getName().endsWith(format1) || 
                        files[i].getName().endsWith(format2)) {
                        model_doc_File model_document = new model_doc_File();
                        model_document.setName(files[i].getName());
                        model_document.setPath(files[i].getPath());
                        documentArrayList.add(model_document);
                        i++;
                    }
                } while (cursor.moveToNext());
            } else {
                Toast.makeText(
                        List_Files_Activity.this,
                        "No Document Files Present",
                        Toast.LENGTH_LONG
                ).show();
            }
            cursor.close();
        }
        
        return documentArrayList;
    }

    public void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                Uri uri = Uri.parse("package:" + this.getPackageName());
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri);
                startActivityForResult(intent, REQ_FILE_ACCESS);
            } catch (Exception e) {
                Intent obj = new Intent();
                obj.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(obj, REQ_FILE_ACCESS);
            }
        } else {
            ActivityCompat.requestPermissions(
                    List_Files_Activity.this,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    }, 
                    REQ_FILE_ACCESS
            );
        }
    }

    public boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int write = ContextCompat.checkSelfPermission(
                    getApplicationContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            );
            int read = ContextCompat.checkSelfPermission(
                    getApplicationContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
            );
            return (write == PackageManager.PERMISSION_GRANTED &&
                    read == PackageManager.PERMISSION_GRANTED);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkPermission()) {
            itemsList = fileListDocx(file_format);
            setAdapter(itemsList);
        } else {
            requestPermission();
        }
    }
}