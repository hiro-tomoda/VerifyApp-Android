package com.yuwa_seisakusho.verifyapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * ボトムナビゲーション検証画面
 */
public class BottomNavigationFragment extends Fragment {

    MainActivity activity;

    public BottomNavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (MainActivity) getActivity();

        // レイアウトファイルからビューオブジェクトを生成
        View view = inflater.inflate(R.layout.fragment_bottom_navigation, container, false);

        // ビューを取得
        final TextView message = view.findViewById(R.id.text_message);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);

        // ボトムナビゲーションが選択された場合の処理
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // 押されたボタンごとの処理
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        message.setText("Home");
                        return true;
                    case R.id.navigation_dashboard:
                        message.setText("Dashboard");
                        return true;
                    case R.id.navigation_notifications:
                        message.setText("Notifications");
                        return true;
                }
                return false;
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
