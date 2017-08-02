package com.monad.searcher.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.monad.searcher.R;

/**
 * Created by temp on 2017. 8. 1..
 */

public class LodingDialog extends Dialog {
    private TextView content;
    private Button close;

    public LodingDialog(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);
        setContentView(R.layout.activity_loding_dialog);

        content = (TextView) findViewById(R.id.help_dialog_content);
        close = (Button) findViewById(R.id.help_dialog_close);
    }
}
