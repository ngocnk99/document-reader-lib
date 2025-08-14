package com.filemanager;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class ExtensionFunctions {
    
    public static void toasty(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    
    public static void visible(View view) {
        view.setVisibility(View.VISIBLE);
    }
    
    public static void gone(View view) {
        view.setVisibility(View.GONE);
    }
}
