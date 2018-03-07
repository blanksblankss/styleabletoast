package com.muddzdev.styleabletoastlibrary;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.util.TypedValue;

/**
 * Created by Muddz on 07-03-2018.
 */

class StyleableToastUtils {

    static int toDp(Context context, int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

}
