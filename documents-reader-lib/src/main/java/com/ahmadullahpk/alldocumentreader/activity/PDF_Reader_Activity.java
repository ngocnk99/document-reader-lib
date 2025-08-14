package com.filemanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.filemanager.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.File;

public class PDF_Reader_Activity extends AppCompatActivity {

    PDFView pdfView;
    ImageView imgBack, imgShare;
    TextView headerTitleText;
    private String filePath;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_pdf_reader);

        initViews();
        setupData();
        setupClickListeners();
        loadPDF();
    }

    private void initViews() {
        pdfView = findViewById(R.id.pdfView);
        imgBack = findViewById(R.id.img_back);
        imgShare = findViewById(R.id.img_share);
        headerTitleText = findViewById(R.id.header_title_text);
    }

    private void setupData() {
        if (getIntent() != null) {
            this.filePath = getIntent().getStringExtra("path");
            this.fileName = getIntent().getStringExtra("name");
            
            headerTitleText.setTextAppearance(this, R.style.PageTitleBold);
            headerTitleText.setMaxLines(1);
            headerTitleText.setText(this.fileName != null ? this.fileName : "PDF Document");
        }
    }

    private void setupClickListeners() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFile();
            }
        });
    }

    private void loadPDF() {
        if (filePath != null) {
            File filepdf = new File(filePath);
            
            pdfView.fromFile(filepdf)
                    .enableSwipe(true)
                    .swipeHorizontal(true)
                    .enableDoubletap(true)
                    .enableAnnotationRendering(false)
                    .password(null)
                    .scrollHandle(null)
                    .enableAntialiasing(true)
                    .spacing(0)
                    .autoSpacing(false)
                    .pageFitPolicy(FitPolicy.WIDTH)
                    .fitEachPage(false)
                    .pageSnap(false)
                    .pageFling(true)
                    .nightMode(false)
                    .load();
        }
    }

    private void shareFile() {
        if (filePath != null) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            Uri parse = Uri.parse(filePath);
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_STREAM, parse);
            startActivity(Intent.createChooser(intent, getResources().getString(R.string.shareUsing)));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}