package com.jywy.woodpersons.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 高 on 2017/3/27.
 */

public class MarqueTextView extends TextView {
    public MarqueTextView(Context context) {
        super(context);
    }

    public MarqueTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
