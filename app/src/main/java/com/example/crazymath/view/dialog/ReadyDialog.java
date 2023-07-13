package com.example.crazymath.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.crazymath.R;

public class ReadyDialog extends Dialog {
    private TextView tvText, tvOk;
    private final View.OnClickListener event;

    public ReadyDialog(@NonNull Context context, View.OnClickListener event) {
        super(context, R.style.Dialog_FullScreen);
        setContentView(R.layout.view_ready);
        this.event = event;
        initViews();
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    private void initViews() {
        tvText = findViewById(R.id.tv_text);
        tvOk = findViewById(R.id.tv_ok);

        tvOk.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(getContext(), androidx.appcompat.R.anim.abc_fade_in));
            event.onClick(v);
            dismiss();
        });
    }

    public void setLoseInfo() {
        tvText.setText(R.string.txt_lose);
        tvOk.setText(R.string.txt_play_again);
    }
}
