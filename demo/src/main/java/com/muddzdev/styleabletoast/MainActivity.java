package com.muddzdev.styleabletoast;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
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

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {

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
    @BindView(R.id.text_bold_cb)
    CheckBox textBoldCb;
    @BindView(R.id.icon_left_cb)
    CheckBox iconLeftCb;
    @BindView(R.id.icon_right_cb)
    CheckBox iconRightCb;
    @BindView(R.id.icon_left)
    ImageView iconLeft;
    @BindView(R.id.textview)
    TextView textview;
    @BindView(R.id.icon_right)
    ImageView iconRight;
    @BindView(R.id.demo_toast)
    LinearLayout demoToast;
    @BindView(R.id.play_btn)
    Button playBtn;

    private Typeface fontNormal;
    private Typeface fontBold;
    private GradientDrawable gradientDrawable;
    private StyleableToast.Builder toast;
    private String toastMsg = "Hello World";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ButterKnife.bind(demoToast);
        initToastPreview();

        toast = new StyleableToast.Builder(MainActivity.this);
        strokeValueTxv.setText(strokeWidthSb.getProgress() + "dp");
        cornerValueTxv.setText(cornerRadiusSb.getProgress() + "dp");
        fontNormal = Typeface.create(textview.getTypeface(), Typeface.NORMAL);
        fontBold = Typeface.create(textview.getTypeface(), Typeface.BOLD);
    }


    private void initToastPreview() {
        textview.setText(toastMsg);
        gradientDrawable = (GradientDrawable) getDrawable(R.drawable.styleabletoast_shape);
        demoToast.setBackground(gradientDrawable);
        cornerRadiusSb.setOnSeekBarChangeListener(this);
        strokeWidthSb.setOnSeekBarChangeListener(this);
        textBoldCb.setOnCheckedChangeListener(this);
        iconLeftCb.setOnCheckedChangeListener(this);
        iconRightCb.setOnCheckedChangeListener(this);
    }


    @OnClick(R.id.textcolor_btn)
    public void onTextColorClicked() {
      new StyleableToast.Builder(this)
                .text("hello")
                .length(Toast.LENGTH_LONG)
                .backgroundColor(Color.RED).show();
    }

    @OnClick(R.id.backgroundcolor_btn)
    public void onTBextColorClicked() {
        new StyleableToast.Builder(this)
                .text("hello")
                .solidBackground()
                .length(Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.play_btn)
    public void playToast() {
        demoToast.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.text(toastMsg);
                toast.length(Toast.LENGTH_LONG);
                toast.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        demoToast.setVisibility(View.VISIBLE);
                    }
                }, 3800);
            }
        }, 500);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.corner_radius_sb:
                gradientDrawable.setCornerRadius(intToDp(progress));
                demoToast.setBackground(gradientDrawable);
                toast.cornerRadius(progress);
                cornerValueTxv.setText(String.format("%s dp", progress));
                break;
            case R.id.stroke_width_sb:
                gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(intToDp(24));
                gradientDrawable.setColor(R.color.defaultBackgroundColor);
                gradientDrawable.setStroke((int) intToDp(progress), Color.RED);
                demoToast.setBackground(gradientDrawable);
                toast.stroke(progress, Color.RED);
                strokeValueTxv.setText(String.format("%s dp", progress));
                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.text_bold_cb:
                Typeface typeface = isChecked ? fontBold : fontNormal;
                textview.setTypeface(typeface);
                toast.typeface(typeface);
                break;
            case R.id.icon_left_cb:
                int iconResLeft = isChecked ? R.drawable.ic_airplanemode_inactive_black_24dp : 0;
                iconLeft.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                iconLeft.setBackgroundResource(iconResLeft);
                toast.iconResLeft(iconResLeft);
                break;
            case R.id.icon_right_cb:
                int iconResRight = isChecked ? R.drawable.ic_autorenew_black_24dp : 0;
                iconRight.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                iconRight.setBackgroundResource(iconResRight);
                toast.iconResRight(iconResRight);
                break;
        }
    }


    //TODO maybe put this in a Util class in the library module
    private float intToDp(int value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
