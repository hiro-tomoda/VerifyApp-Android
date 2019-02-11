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

        // UI部品検証ボタン押下時の処理
        Button uiPartsButton = view.findViewById(R.id.button_ui_parts);
        uiPartsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiPartsFragment uiPartsFragment = new UiPartsFragment();
                activity.setFragment(uiPartsFragment, true);
            }
        });

        // WebAPI検証ボタン押下時の処理
        Button webApiButton = view.findViewById(R.id.button_webapi);
        webApiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebApiFragment webApiFragment = new WebApiFragment();
                activity.setFragment(webApiFragment, true);
            }
        });

        // DB検証ボタン押下時の処理
        Button dbButton = view.findViewById(R.id.button_db);
        dbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbFragment dbFragment = new DbFragment();
                activity.setFragment(dbFragment, true);
            }
        });

        // WebView検証ボタン押下時の処理
        Button webViewButton = view.findViewById(R.id.button_webview);
        webViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewFragment webViewFragment = new WebViewFragment();
                activity.setFragment(webViewFragment, true);
            }
        });

        // カレンダー検証ボタン押下時の処理
        Button calendarButton = view.findViewById(R.id.button_calendar);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarFragment calendarFragment = new CalendarFragment();
                activity.setFragment(calendarFragment, true);
            }
        });

        // ボトムナビゲーション検証ボタン押下時の処理
        Button bottomNavigationButton = view.findViewById(R.id.button_bottom_navigation);
        bottomNavigationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationFragment bottomNavigationFragment = new BottomNavigationFragment();
                activity.setFragment(bottomNavigationFragment, true);
            }
        });

        // ボトムナビゲーション検証ボタン押下時の処理
        Button preferenceButton = view.findViewById(R.id.button_preference);
        preferenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceFragment preferenceFragment = new PreferenceFragment();
                activity.setFragment(preferenceFragment, true);
            }
        });

        // ログイン検証ボタン押下時の処理
        Button loginButton = view.findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment loginFragment = new LoginFragment();
                activity.setFragment(loginFragment, true);
            }
        });

        return view;
    }

}
