package com.muddzdev.styleabletoast;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class MainActivity extends AppCompatActivity {

    String toastMsg = "Hello World!";
    int toastColor = Color.parseColor("#FF5A5F");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    //--------------------------------------------------

    @OnClick(R.id.b1)
    public void coloredBackground() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .backgroundColor(toastColor)
                .show();
    }

    @OnLongClick(R.id.b1)
    public boolean coloredBackgroundStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.coloredBackground).show();
        return true;
    }

    //--------------------------------------------------

    @OnClick(R.id.b2)
    public void coloredText() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .textColor(toastColor)
                .show();
    }

    @OnLongClick(R.id.b2)
    public boolean coloredTextStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.coloredText).show();
        return true;
    }

    //--------------------------------------------------

    @OnClick(R.id.b3)
    public void coloredBoldText() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .textColor(toastColor)
                .textBold()
                .show();
    }

    @OnLongClick(R.id.b3)
    public boolean coloredBoldTextStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.coloredBoldText).show();
        return true;
    }


    //--------------------------------------------------

    @OnClick(R.id.b4)
    public void customFont() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .font(R.font.dosis)
                .show();
    }


    @OnLongClick(R.id.b4)
    public boolean customFontStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.customFont).show();
        return true;
    }

    //--------------------------------------------------

    @OnClick(R.id.b5)
    public void cornerRadius5dp() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .cornerRadius(5)
                .show();
    }


    @OnLongClick(R.id.b5)
    public boolean cornerRadius5dpStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.cornerRadius5Dp).show();
        return true;
    }

    //--------------------------------------------------


    @OnClick(R.id.b6)
    public void iconStart() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .iconStart(R.drawable.ic_airplanemode_inactive_black_24dp)
                .show();
    }

    @OnLongClick(R.id.b6)
    public boolean iconStartStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.iconStart).show();
        return true;
    }


    //--------------------------------------------------

    @OnClick(R.id.b7)
    public void iconEnd() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .iconEnd(R.drawable.ic_airplanemode_inactive_black_24dp)
                .show();
    }


    @OnLongClick(R.id.b7)
    public boolean iconEndStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.iconEnd).show();
        return true;
    }


    //--------------------------------------------------

    @OnClick(R.id.b8)
    public void iconStartEnd() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .iconStart(R.drawable.ic_airplanemode_inactive_black_24dp)
                .iconEnd(R.drawable.ic_airplanemode_inactive_black_24dp)
                .show();
    }


    @OnLongClick(R.id.b8)
    public boolean iconStartEndStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.iconStartEnd).show();
        return true;
    }

    //--------------------------------------------------


    @OnClick(R.id.b9)
    public void coloredStroke() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .stroke(2, Color.YELLOW)
                .show();
    }

    @OnLongClick(R.id.b9)
    public boolean coloredStrokeStyle() {
        StyleableToast.makeText(this, toastMsg, R.style.coloredStroke).show();
        return true;
    }


    //--------------------------------------------------

    @OnClick(R.id.b10)
    public void allStyles() {
        new StyleableToast.Builder(this)
                .text(toastMsg)
                .stroke(2, Color.CYAN)
                .backgroundColor(Color.BLACK)
                .solidBackground()
                .textColor(Color.YELLOW)
                .textBold()
                .font(R.font.dosis)
                .iconStart(R.drawable.ic_airplanemode_inactive_black_24dp)
                .iconEnd(R.drawable.ic_airplanemode_inactive_black_24dp)
                .cornerRadius(12)
                .textSize(18)
                .show();
    }


    @OnLongClick(R.id.b10)
    public boolean allStylesStyles() {
        StyleableToast.makeText(this, toastMsg, R.style.AllStyles).show();
        return true;
    }


}
