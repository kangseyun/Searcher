package com.monad.searcher.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.monad.searcher.Adapter.NoticeBoardRecyclerViewAdapter;
import com.monad.searcher.Model.MyData;
import com.monad.searcher.R;

import java.util.ArrayList;

public class MyFragment4 extends Fragment {
    private View v;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myDataset;
    private Button btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_4, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.notice_board);
        btn = (Button) v.findViewById(R.id.btn);
        setRecyclerView();
        setNoticeBoard();
        return v;
    }

    private void setRecyclerView()
    {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        myDataset = new ArrayList<>();
        mAdapter = new NoticeBoardRecyclerViewAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setNoticeBoard()
    {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                alert.setTitle("제목을 입력하세요");
                alert.setMessage("실험용");

                final EditText name = new EditText(getContext());
                alert.setView(name);

                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        final String username = name.getText().toString(); // 유효성 검사

                        AlertDialog.Builder contents = new AlertDialog.Builder(getContext()) ;

                        contents.setTitle("내용을 입력하세요") ;
                        contents.setMessage("실험용");

                        final EditText message = new EditText(getContext());
                        contents.setView(message);

                        contents.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dlg, int sumthin) {
                                        final String usermessage = message.getText().toString(); // 유효성 검사
                                        myDataset.add(new MyData(username,usermessage));
                                        Log.i("AAAAAA",username);
                                        Log.i("AAAAAA",usermessage);
                                    }
                        });
                        contents.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                            }
                        });
                        contents.show();
                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alert.show();
            }
        });
    }
}
