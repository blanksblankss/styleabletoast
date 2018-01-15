package com.muddzdev.styleabletoast;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.corner_value_txv)
    TextView cornerValueTxv;
    @BindView(R.id.corner_radius_sb)
    AppCompatSeekBar cornerRadiusSb;
    @BindView(R.id.stroke_value_txv)
    TextView strokeValueTxv;
    @BindView(R.id.stroke_width_sb)
    AppCompatSeekBar strokeWidthSb;
    @BindView(R.id.strokecolor_btn)
    Button strokecolorBtn;
    @BindView(R.id.backgroundcolor_btn)
    Button backgroundcolorBtn;
    @BindView(R.id.textcolor_btn)
    Button textcolorBtn;
    @BindView(R.id.icon_left)
    ImageView iconLeftImv;
    @BindView(R.id.textview)
    TextView textview;
    @BindView(R.id.icon_right)
    ImageView iconRightImv;
    @BindView(R.id.demo_toast)
    LinearLayout demoToast;
    @BindView(R.id.text_bold_cb)
    CheckBox textBoldCb;
    @BindView(R.id.icon_left_cb)
    CheckBox iconLeftCb;
    @BindView(R.id.icon_right_cb)
    CheckBox iconRightCb;
    private Typeface fontNormal;
    private Typeface fontBold;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        new StyleableToast
                .Builder(this)
                .font(R.font.dosis)
                .text("Hello")
                .show();


//        textBoldCb.setOnCheckedChangeListener(this);
//        iconLeftCb.setOnCheckedChangeListener(this);
//        iconRightCb.setOnCheckedChangeListener(this);
//        cornerRadiusSb.setOnSeekBarChangeListener(this);
//        strokeWidthSb.setOnSeekBarChangeListener(this);

        textview.setText("Hello World");
        strokeValueTxv.setText(strokeWidthSb.getProgress() + "dp");
        cornerValueTxv.setText(cornerRadiusSb.getProgress() + "dp");
        fontNormal = Typeface.create(textview.getTypeface(), Typeface.NORMAL);
        fontBold = Typeface.create(getResources().getString(R.string.default_font), Typeface.BOLD);
    }


    private void playToast() {
        new StyleableToast.Builder(this)
                .typeface(ToastProperties.fontBold ? fontBold : fontNormal)
                .cornerRadius(ToastProperties.cornerRadius)
                .iconResLeft(ToastProperties.iconLeft ? 0 : 0)
                .iconResRight(ToastProperties.iconRight ? 0 : 0)
                .backgroundColor(ToastProperties.backgroundColor)
                .textColor(ToastProperties.textColor)
                .stroke(ToastProperties.strokeWidth, ToastProperties.strokeColor)
                .show();
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
