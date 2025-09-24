// BaseActivity.java
package com.ahmadullahpk.alldocumentreader.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.ahmadullahpk.alldocumentreader.R;
import com.ahmadullahpk.alldocumentreader.manageui.CustomFrameLayout;
import com.ahmadullahpk.alldocumentreader.util.Utility;
import com.ahmadullahpk.alldocumentreader.util.ViewUtils;

public class BaseActivity extends AppCompatActivity {
    
    public String[] PERMISSIONS_LIST = {
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    };
    
    private static final int REQUEST_CODE = 112;
    private Intent starterActivity = null;
    
    private ActivityResultLauncher<Intent> someActivityResultLauncher = 
        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), 
            (ActivityResult activityResult) -> {
                if (activityResult.getResultCode() == RESULT_OK) {
                    startActivity(starterActivity);
                    finish();
                }
            });
    
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        if (requestCode != REQUEST_CODE) {
            return;
        }
        
        if (grantResults.length > 0) {
            int n2 = grantResults[0];
            boolean bl = n2 == 0;
            
            if (bl) {
                if (starterActivity != null) {
                    // this.startActivity(new Intent(this, Main_Home_Activity.class));
                    finish();
                    return;
                }
                Utility.Toast(this, getResources().getString(R.string.permissionGranted));
                return;
            }
            Utility.Toast(this, getResources().getString(R.string.permission_denied_message2));
        }
    }
    
    protected boolean hasReadStoragePermission() {
        if (Build.VERSION.SDK_INT >= 30) {
            return Environment.isExternalStorageManager();
        }
        
        if (Build.VERSION.SDK_INT >= 29) {
            int n = ActivityCompat.checkSelfPermission(
                getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            );
            return n == 0;
        }
        
        if (Build.VERSION.SDK_INT >= 23) {
            int n = ActivityCompat.checkSelfPermission(
                getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            );
            return n == 0;
        }
        
        return true;
    }
    
    protected void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(3846);
        decorView.setOnSystemUiVisibilityChangeListener((int i) -> {
            showAndHide((i & 4) == 0);
        });
    }
    
    private void showAndHide(boolean z) {
        // CustomFrameLayout linearLayout = findViewById(R.id.appToolbar);
        // if (linearLayout == null) {
        //     return;
        // }
        
        // if (z) {
        //     linearLayout.setVisibility(View.VISIBLE);
        // } else {
        //     linearLayout.setVisibility(View.GONE);
        // }
    }
    
    public void checkAndLunchActivity(Intent intent) {
        starterActivity = intent;
        
        if (hasReadStoragePermission()) {
            startActivity(intent);
            finish();
        } else if (Build.VERSION.SDK_INT >= 30) {
            try {
                Intent intent2 = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent2.addCategory(Intent.CATEGORY_DEFAULT);
                intent2.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
                someActivityResultLauncher.launch(intent2);
            } catch (Exception unused) {
                Intent intent3 = new Intent();
                intent3.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                someActivityResultLauncher.launch(intent3);
            }
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS_LIST, REQUEST_CODE);
        }
    }
    
    public void setStatusBar() {
        isTransparentEnabled(true);
    }
    
    public void isTransparentEnabled(boolean z) {
        setTransparentForWindow(z, false);
    }
    
    public void setTransparentForWindow(boolean z, boolean z2) {
        android.view.Window window = getWindow();
        
        if (Build.VERSION.SDK_INT >= 23) {
            window.getDecorView().setSystemUiVisibility(ViewUtils.setWidth(z, z2));
            window.setNavigationBarColor(ContextCompat.getColor(this, R.color.black));
        } else if (Build.VERSION.SDK_INT == 21 || Build.VERSION.SDK_INT == 22) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
            window.setNavigationBarColor(ContextCompat.getColor(this, R.color.black));
        }
    }
    
    public void adaptFitsSystemWindows(View view) {
        if (view != null) {
            view.setFitsSystemWindows(false);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    viewGroup.getChildAt(i).setFitsSystemWindows(false);
                }
            }
        }
    }
}