package com.muddzdev.styleabletoast;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    String toastMsg = "Hello World!";
    int toastColor = Color.parseColor("#FF5A5F");
    //TODO TEST ALL METHOD VIA XML LAYOUT TOO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.b1)
    public void coloredBackground() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .backgroundColor(toastColor)
                .show();
    }

    @OnClick(R.id.b2)
    public void coloredText() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .textColor(toastColor)
                .show();
    }

    @OnClick(R.id.b3)
    public void coloredBoldText() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .textColor(toastColor)
                .textBold()
                .show();
    }

    @OnClick(R.id.b4)
    public void customFont() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .font(R.font.dosis)
                .show();
    }

    @OnClick(R.id.b5)
    public void cornerRadius5dp() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .cornerRadius(5)
                .show();
    }

    @OnClick(R.id.b6)
    public void iconStart() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .iconStart(R.drawable.ic_airplanemode_inactive_black_24dp)
                .show();
    }

    @OnClick(R.id.b7)
    public void iconEnd() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .iconEnd(R.drawable.ic_airplanemode_inactive_black_24dp)
                .show();
    }

    @OnClick(R.id.b8)
    public void iconStartEnd() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .iconStart(R.drawable.ic_airplanemode_inactive_black_24dp)
                .iconEnd(R.drawable.ic_airplanemode_inactive_black_24dp)
                .show();
    }

    @OnClick(R.id.b9)
    public void coloredStroke() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .stroke(2, Color.YELLOW)
                .show();
    }

    @OnClick(R.id.b10)
    public void allStyles() {
        StyleableToast.makeText(this, toastMsg, R.style.AllStyles).show();
    }
}
