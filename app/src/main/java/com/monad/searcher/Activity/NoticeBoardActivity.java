package com.monad.searcher.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.monad.searcher.R;

public class NoticeBoardActivity extends AppCompatActivity {

    private Button mWrite;
    private EditText mtitle;
    private String usertitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticeboard);
        mWrite = (Button) findViewById(R.id.btn_noticeboard);
        mtitle = (EditText) findViewById(R.id.title);

        setWrite();
    }

    private void setWrite()
    {
        mWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}