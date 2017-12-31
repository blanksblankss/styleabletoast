package com.muddzdev.styleabletoast;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Toaster(View v) {
        switch (v.getId()) {
            case R.id.button1:
                StyleableToast.makeText(this, "Uploading image", R.style.allStyles).show();
                break;

            case R.id.button2:
                StyleableToast.makeText(this, "Image saved", Toast.LENGTH_LONG, R.style.style2).show();
                break;

            case R.id.button3:
                new StyleableToast
                        .Builder(this)
                        .font(R.font.toastfont)
                        .textBold()
                        .text("Turn off flight mode")
                        .iconResLeft(R.drawable.ic_airplanemode_inactive_black_24dp)
                        .backgroundColor(Color.parseColor("#865aff"))
                        .show();

                break;

            case R.id.button4:

                new StyleableToast
                        .Builder(this)
                        .text("Updating profile")
                        .textColor(Color.WHITE)
                        .backgroundColor(Color.parseColor("#ff5a5f"))
                        .iconResLeft(R.drawable.ic_autorenew_black_24dp)
                        .show();


                break;

            case R.id.button5:
                new StyleableToast
                        .Builder(this)
                        .text("Milk added to list")
                        .font(R.font.dosis)
                        .backgroundColor(Color.parseColor("#74ba82"))
                        .show();


                break;

            case R.id.button6:
                new StyleableToast
                        .Builder(this)
                        .text("Picture saved to gallery")
                        .textColor(Color.parseColor("#fc2d3e"))
                        .backgroundColor(Color.parseColor("#dddddd"))
                        .show();
                break;
        }
    }
}
