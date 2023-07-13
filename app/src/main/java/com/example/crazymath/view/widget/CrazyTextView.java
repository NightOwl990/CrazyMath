package com.example.crazymath.view.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.crazymath.App;

public class CrazyTextView extends AppCompatTextView {
    public CrazyTextView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public CrazyTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CrazyTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "font/Rowdies-Bold.ttf"));
    }
}
