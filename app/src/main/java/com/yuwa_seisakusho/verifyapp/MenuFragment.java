package com.yuwa_seisakusho.verifyapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * メニュー画面
 */
public class MenuFragment extends Fragment {

    MainActivity activity;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (MainActivity) getActivity();

        // レイアウトファイルからビューオブジェクトを生成
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        // WebAPI検証ボタン押下時の処理
        Button webApiButton = view.findViewById(R.id.button_webapi);
        webApiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebApiFragment webApiFragment = new WebApiFragment();
                activity.setFragment(webApiFragment);
            }
        });

        // DB検証ボタン押下時の処理
        Button dbButton = view.findViewById(R.id.button_db);
        dbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbFragment dbFragment = new DbFragment();
                activity.setFragment(dbFragment);
            }
        });


        return view;
    }

}
