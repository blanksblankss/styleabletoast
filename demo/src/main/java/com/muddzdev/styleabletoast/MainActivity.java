package com.muddzdev.styleabletoast;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private Typeface fontNormal;
    private Typeface fontBold;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        findViewById(R.id.strokecolor_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new StyleableToast.Builder(MainActivity.this)
                        .text("Android O!!")
                        .backgroundColor(Color.RED)
                        .textColor(Color.WHITE)
                        .textBold()
                        .length(Toast.LENGTH_LONG)
                        .show();
            }
        });

        findViewById(R.id.backgroundcolor_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new StyleableToast.Builder(MainActivity.this)
                        .text("Hello World")
                        .length(Toast.LENGTH_LONG)
                        .iconResLeft(R.drawable.ic_file_download)
                        .show();
            }
        });

        findViewById(R.id.textcolor_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new StyleableToast.Builder(MainActivity.this)
                        .text("Hello World")
                        .show();
            }
        });


//        textBoldCb.setOnCheckedChangeListener(this);
//        iconLeftCb.setOnCheckedChangeListener(this);
//        iconRightCb.setOnCheckedChangeListener(this);
//        cornerRadiusSb.setOnSeekBarChangeListener(this);
//        strokeWidthSb.setOnSeekBarChangeListener(this);

//        textview.setText("Hello World");
//        strokeValueTxv.setText(strokeWidthSb.getProgress() + "dp");
//        cornerValueTxv.setText(cornerRadiusSb.getProgress() + "dp");
//        fontNormal = Typeface.create(textview.getTypeface(), Typeface.NORMAL);
//        fontBold = Typeface.create(getResources().getString(R.string.default_font), Typeface.BOLD);
    }


    private void playToast() {
    }


    private static class ToastProperties {
        private static int strokeWidth;
        private static int strokeColor;
        private static int backgroundColor;
        private static int textColor;
        private static int cornerRadius;
        private static boolean iconRight;
        private static boolean iconLeft;
        private static boolean fontBold;
    }

}
