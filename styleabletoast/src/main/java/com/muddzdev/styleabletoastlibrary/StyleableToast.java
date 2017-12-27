package com.muddzdev.styleabletoastlibrary;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//        Copyright 2017 Muddii Walid (Muddz)
//
//        Licensed under the Apache License, Version 2.0 (the "License");
//        you may not use this file except in compliance with the License.
//        You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//        Unless required by applicable law or agreed to in writing, software
//        distributed under the License is distributed on an "AS IS" BASIS,
//        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//        See the License for the specific language governing permissions and
//        limitations under the License.


@SuppressLint("ViewConstructor")
public class StyleableToast extends RelativeLayout implements OnToastFinishedListener {

    private int cornerRadius = -1;
    private int backgroundColor;
    private int strokeColor;
    private int strokeWidth;
    private int iconResLeft;
    private int iconResRight;
    private int textColor;
    private int fontId;
    private int length;
    private int style;
    private float textSize;
    private boolean isTextSizeFromStyle = false;
    private boolean hasAnimation;
    private boolean solidBackground;
    private boolean textBold;
    private String text;
    private TypedArray typedArray;
    private TextView textView;
    private Typeface typeface;
    private ImageView iconLeft;
    private ImageView iconRight;
    private Toast styleableToast;
    private LinearLayout rootLayout;
    private Context context;

    public static StyleableToast makeText(@NonNull Context context, String text, int length, @StyleRes int style) {
        return new StyleableToast(context, text, length, style);
    }

    public static StyleableToast makeText(@NonNull Context context, String text, @StyleRes int style) {
        return new StyleableToast(context, text, Toast.LENGTH_SHORT, style);
    }

    private StyleableToast(@NonNull Context context, String text, int length, @StyleRes int style) {
        super(context);
        this.context = context;
        this.text = text;
        this.length = length;
        this.style = style;
    }

    private StyleableToast(StyleableToast.Builder builder) {
        super(builder.context);
        this.context = builder.context.getApplicationContext();
        this.backgroundColor = builder.backgroundColor;
        this.cornerRadius = builder.cornerRadius;
        this.iconResRight = builder.iconResRight;
        this.iconResLeft = builder.iconResLeft;
        this.strokeColor = builder.strokeColor;
        this.strokeWidth = builder.strokeWidth;
        this.hasAnimation = builder.hasAnimation;
        this.solidBackground = builder.solidBackground;
        this.textColor = builder.textColor;
        this.textSize = builder.textSize;
        this.textBold = builder.textBold;
        this.typeface = builder.typeface;
        this.fontId = builder.fontId;
        this.text = builder.text;
        this.length = builder.length;
    }

    private void initStyleableToast() {
        View v = inflate(getContext(), R.layout.styleable_layout, null);
        rootLayout = v.findViewById(R.id.root);
        textView = v.findViewById(R.id.textview);
        iconLeft = v.findViewById(R.id.icon_left);
        iconRight = v.findViewById(R.id.icon_right);
        if (style > 0) {
            typedArray = getContext().obtainStyledAttributes(style, R.styleable.StyleableToast);
        }

        makeShape();
        makeIcon();
        makeTextView();

        // Very important to recycle AFTER the make() methods!
        if (typedArray != null) {
            typedArray.recycle();
        }

        if (hasAnimation) {
            iconLeft.setAnimation(getAnimation());
            new ToastLengthTracker(length, this);
        }
    }

    public void show() {
        initStyleableToast();
        styleableToast = new Toast(context);
        styleableToast.setDuration(length == Toast.LENGTH_LONG ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        styleableToast.setView(rootLayout);
        styleableToast.show();
    }

    public void cancel() {
        if (styleableToast != null) {
            styleableToast.cancel();
        }
    }

    @Deprecated
    public Toast getStyleableToast() {
        return styleableToast;
    }


    private void makeShape() {
        loadShapeAttributes();
        GradientDrawable gradientDrawable = (GradientDrawable) rootLayout.getBackground();
        gradientDrawable.setCornerRadius(cornerRadius != -1 ? cornerRadius : R.dimen.default_corner_radius);
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        if (backgroundColor == 0) {
            gradientDrawable.setColor(ContextCompat.getColor(context, R.color.defaultBackgroundColor));
        } else {
            gradientDrawable.setColor(backgroundColor);
        }
        if (solidBackground) {
            gradientDrawable.setAlpha(getResources().getInteger(R.integer.fullBackgroundAlpha));
        } else {
            gradientDrawable.setAlpha(getResources().getInteger(R.integer.defaultBackgroundAlpha));
        }

        rootLayout.setBackground(gradientDrawable);
    }

    private void makeTextView() {
        loadTextViewStyleAttributes();
        textView.setText(text);
        if (textColor != 0) {
            textView.setTextColor(textColor);
        }

        if (textSize > 0) {
            textView.setTextSize(isTextSizeFromStyle ? 0 : TypedValue.COMPLEX_UNIT_SP, textSize);
        }

        if (textBold && typeface == null) {
            textView.setTypeface(Typeface.create(context.getString(R.string.default_font), Typeface.BOLD));
        } else if (textBold) {
            textView.setTypeface(Typeface.create(typeface, Typeface.BOLD));
        } else if (typeface != null) {
            textView.setTypeface(typeface);
            //TODO THIS CHECK IS DEPRECATED AND WILL BE DELETED SOON
        } else if (fontId > 0) {
            textView.setTypeface(ResourcesCompat.getFont(context, fontId));
        }
    }


    private void makeIcon() {
        loadIconAttributes();
        if (iconResLeft > 0 || iconResRight > 0) {
            int horizontalPadding = (int) getResources().getDimension(R.dimen.toast_horizontal_padding_with_icon);
            int verticalPadding = (int) getResources().getDimension(R.dimen.toast_vertical_padding);
            rootLayout.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        }
        if (iconResLeft > 0) {
            iconLeft.setBackgroundResource(iconResLeft);
            iconLeft.setVisibility(VISIBLE);
        }
        if (iconResRight > 0) {
            iconRight.setBackgroundResource(iconResRight);
            iconRight.setVisibility(VISIBLE);
        }
    }

    /**
     * loads style attributes from styles.xml if a style resource is used.
     */
    private void loadShapeAttributes() {
        if (style == 0) {
            return;
        }

        solidBackground = typedArray.getBoolean(R.styleable.StyleableToast_solidBackground, false);
        backgroundColor = typedArray.getColor(R.styleable.StyleableToast_colorBackground, ContextCompat.getColor(context, R.color.defaultBackgroundColor));
        cornerRadius = (int) typedArray.getDimension(R.styleable.StyleableToast_cornerRadius, R.dimen.default_corner_radius);

        if (typedArray.hasValue(R.styleable.StyleableToast_length)) {
            length = typedArray.getInt(R.styleable.StyleableToast_length, 0);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            if (typedArray.hasValue(R.styleable.StyleableToast_strokeColor) && typedArray.hasValue(R.styleable.StyleableToast_strokeWidth)) {
                strokeWidth = (int) typedArray.getDimension(R.styleable.StyleableToast_strokeWidth, 0);
                strokeColor = typedArray.getColor(R.styleable.StyleableToast_strokeColor, Color.TRANSPARENT);
            }
        }
    }

    private void loadTextViewStyleAttributes() {
        if (style == 0) {
            return;
        }

        textColor = typedArray.getColor(R.styleable.StyleableToast_textColor, Color.WHITE);
        textBold = typedArray.getBoolean(R.styleable.StyleableToast_textBold, false);
        textSize = typedArray.getDimension(R.styleable.StyleableToast_textSize, 0);
        isTextSizeFromStyle = textSize > 0;
        fontId = typedArray.getResourceId(R.styleable.StyleableToast_textFont, 0);

        String textFontPath = typedArray.getString(R.styleable.StyleableToast_textFont);
        if (textFontPath != null) {
            if (textFontPath.contains("fonts/") && (textFontPath.contains(".otf") || textFontPath.contains(".ttf"))) {
                typeface = Typeface.createFromAsset(context.getAssets(), textFontPath);
            }
        }
    }


    private void loadIconAttributes() {
        if (style == 0) {
            return;
        }
        iconResLeft = typedArray.getResourceId(R.styleable.StyleableToast_iconLeft, 0);
        iconResRight = typedArray.getResourceId(R.styleable.StyleableToast_iconRight, 0);
    }


    public Animation getAnimation() {
        if (hasAnimation) {
            RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setInterpolator(new LinearInterpolator());
            anim.setRepeatCount(Animation.INFINITE);
            anim.setDuration(1000);
            return anim;
        }
        return null;
    }

    /**
     * A callback that automatically cancels and resets animation effect from spinIcon(); when the StyleableToastListener is finished showing on screen.
     * Users should not call this method as this is used internally in the library.
     */
    @Override
    public void onToastFinished() {
        if (getAnimation() != null) {
            getAnimation().cancel();
            getAnimation().reset();
        }
    }


    public static class Builder {

        private int cornerRadius = -1;
        private int backgroundColor;
        private int strokeColor;
        private int strokeWidth;
        private int iconResLeft;
        private int iconResRight;
        private int textColor;
        private int fontId;
        private int length;
        private float textSize;
        private boolean solidBackground;
        private boolean hasAnimation;
        private boolean textBold;
        private String text;
        private Typeface typeface;
        private final Context context;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder textColor(@ColorInt int textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder textBold() {
            this.textBold = true;
            return this;
        }

        public Builder textSize(float textSize) {
            this.textSize = textSize;
            return this;
        }

        /**
         * Use the new method {@link #font(int)} instead.
         */
        @Deprecated
        public Builder typeface(Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        /**
         * @param font A font resource id like R.font.somefont as introduced with the new font api in Android 8
         */
        public Builder font(@FontRes int font) {
            this.fontId = font;
            return this;
        }

        public Builder backgroundColor(@ColorInt int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder solidBackground() {
            this.solidBackground = true;
            return this;
        }

        public Builder stroke(int strokeWidth, @ColorInt int strokeColor) {
            this.strokeWidth = strokeWidth;
            this.strokeColor = strokeColor;
            return this;
        }

        /**
         * @param cornerRadius Sets the corner radius of the StyleableToast's shape.
         */
        public Builder cornerRadius(int cornerRadius) {
            this.cornerRadius = cornerRadius;
            return this;
        }

        public Builder iconResLeft(@DrawableRes int iconResLeft) {
            this.iconResLeft = iconResLeft;
            return this;
        }

        public Builder iconResRight(@DrawableRes int iconResRight) {
            this.iconResRight = iconResRight;
            return this;
        }

        /**
         * Enables spinning animation of the passed iconResLeft by its around its own center.
         */
        @Deprecated
        public Builder spinIcon() {
            this.hasAnimation = true;
            return this;
        }

        /**
         * @param length {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}
         */
        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public void show() {
            StyleableToast styleableToast = new StyleableToast(this);
            styleableToast.show();
        }
    }
}
