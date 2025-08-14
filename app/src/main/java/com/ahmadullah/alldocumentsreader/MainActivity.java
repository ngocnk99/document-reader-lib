package com.ahmadullah.alldocumentsreader;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.ahmadullah.alldocumentsreader.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rlWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List_Files_Activity.class);
                intent.putExtra("format", "doc");
                startActivity(intent);
            }
        });

        binding.rlExcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List_Files_Activity.class);
                intent.putExtra("format", "xls");
                startActivity(intent);
            }
        });

        binding.rlPpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List_Files_Activity.class);
                intent.putExtra("format", "ppt");
                startActivity(intent);
            }
        });

        binding.rlPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List_Files_Activity.class);
                intent.putExtra("format", "pdf");
                startActivity(intent);
            }
        });

        binding.rlRtf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List_Files_Activity.class);
                intent.putExtra("format", "rtf");
                startActivity(intent);
            }
        });

        binding.rlCsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List_Files_Activity.class);
                intent.putExtra("format", "csv");
                startActivity(intent);
            }
        });

        binding.rlJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List_Files_Activity.class);
                intent.putExtra("format", "json");
                startActivity(intent);
            }
        });

        binding.rlXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List_Files_Activity.class);
                intent.putExtra("format", "xml");
                startActivity(intent);
            }
        });

        binding.rlJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List_Files_Activity.class);
                intent.putExtra("format", "java");
                startActivity(intent);
            }
        });

        binding.rlKotlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List_Files_Activity.class);
                intent.putExtra("format", "kt");
                startActivity(intent);
            }
        });

        binding.rlHtml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List_Files_Activity.class);
                intent.putExtra("format", "html");
                startActivity(intent);
            }
        });
    }

    private void dialog_exit() {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.exit_dialog);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.setCancelable(true);
        
        TextView txt_yes = dialog.findViewById(R.id.txt_yes);
        TextView txt_no = dialog.findViewById(R.id.txt_no);
        
        txt_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finishAffinity();
            }
        });
        
        txt_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        dialog_exit();
    }
}