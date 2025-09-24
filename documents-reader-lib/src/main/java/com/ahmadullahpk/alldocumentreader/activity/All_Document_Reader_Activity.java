// All_Document_Reader_Activity.java
package com.ahmadullahpk.alldocumentreader.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
// import com.ahmadullahpk.alldocumentreader.ExtensionFunctions;
import com.ahmadullahpk.alldocumentreader.util.Utility;
import com.ahmadullahpk.alldocumentreader.xs.constant.MainConstant;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class All_Document_Reader_Activity extends AppCompatActivity {

    private String fileName = "";
    private String filepath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getBooleanExtra("fromAppActivity", false)) {
                filepath = getIntent().getStringExtra("path");
            } else {
                String action = intent.getAction();
                if (Intent.ACTION_VIEW.equals(action)) {
                    Uri data = intent.getData();
                    if (data != null) {
                        filepath = getFilePathForN(data);
                    } else {
                        filepath = null;
                    }
                } else if (Intent.ACTION_SEND.equals(action)) {
                    Log.e("checkahmad", "action " + intent.getData());
                    Uri uri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
                    if (uri != null) {
                        filepath = getFilePathForN(uri);
                    } else {
                        filepath = null;
                    }
                }
            }

            String str = filepath;
            if (str != null) {
                Intent m_intent = null;
                int fileType = MainConstant.getFileType(str);
                m_intent = new Intent(this, ViewFiles_Activity.class);
                

                
                if (filepath.endsWith("rtf")) {
                    m_intent = new Intent(this, ViewRtf_Activity.class);
                }

                if (filepath.endsWith("csv")) {
                    m_intent = new Intent(this, CSVViewer_Activity.class);
                }

                if (m_intent != null) {
                    m_intent.putExtra("name", fileName);
                    m_intent.putExtra("fromConverterApp", true);
                    m_intent.putExtra("fileType", String.valueOf(fileType));
                    m_intent.putExtra("fromAppActivity", true);
                    m_intent.putExtra("path", filepath);
                    startActivity(m_intent);
                }
                finish();
                return;
            }
            // ExtensionFunctions.toasty(this, "Unable to open file from here, Go to Document Reader App and try to open from them");
            finish();
        }
    }

    private String getFilePathForN(Uri uri) {
        Cursor query = getContentResolver().query(uri, null, null, null, null);
        if (query == null) {
            String path = uri.getPath();
            if (path != null) {
                fileName = new File(path).getName();
            }
            Log.e("checkahmad", "Null query");
            return path;
        }
        
        int columnIndex = query.getColumnIndex("_display_name");
        query.moveToFirst();
        if (query.getCount() <= 0) {
            query.close();
            return null;
        }
        
        String string = query.getString(columnIndex);
        fileName = string;
        if (string == null) {
            int columnIndex2 = query.getColumnIndex("_data");
            String result = null;
            if (columnIndex2 >= 0) {
                result = query.getString(columnIndex2);
            }
            query.close();
            return result;
        }
        
        Log.e("checkahmad", "out");
        File file = new File(getCacheDir(), ".temp");
        Utility.deleteDir(file);
        file.mkdirs();
        File file2 = new File(file, fileName);
        String path2 = file2.getPath();
        query.close();
        
        try {
            InputStream openInputStream = getContentResolver().openInputStream(uri);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            
            if (openInputStream != null) {
                int available = openInputStream.available();
                int bufferSize = Math.min(available, 1048576);
                byte[] bArr = new byte[bufferSize];
                
                int read;
                while ((read = openInputStream.read(bArr)) != -1) {
                    fileOutputStream.write(bArr, 0, read);
                }
                
                openInputStream.close();
                fileOutputStream.close();
            }
        } catch (Exception e) {
            Log.e("checkahmad", e.getMessage() != null ? e.getMessage() : "Unknown error");
        }
        
        Log.e("checkahmad", path2);
        return path2;
    }

    public String getPath(Context context, Uri uri) {
        String[] strArr = {"_data"};
        Cursor query = context.getContentResolver().query(uri, strArr, null, null, null);
        String str = null;
        
        if (query != null) {
            if (query.moveToFirst()) {
                str = query.getString(query.getColumnIndexOrThrow(strArr[0]));
            }
            query.close();
        }
        
        return str != null ? str : "Not found";
    }
}