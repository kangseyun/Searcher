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

public class HelpDialog extends Dialog {
    private View.OnClickListener ClickListener;
    private TextView content;
    private Button close;
    private String mContent;

    public HelpDialog(Context context, String content,
                      View.OnClickListener singleListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContent = content;
        this.ClickListener = singleListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);
        setContentView(R.layout.activity_help_dialog);

        content = (TextView) findViewById(R.id.help_dialog_content);
        close = (Button) findViewById(R.id.help_dialog_close);

        close.setOnClickListener(ClickListener);

        content.setText(mContent);
    }

    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.


}
