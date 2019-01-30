package com.yuwa_seisakusho.verifyapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;


/**
 * UI部品検証画面
 */
public class UiPartsFragment extends Fragment {

    MainActivity activity;

    public UiPartsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (MainActivity) getActivity();

        // レイアウトファイルからビューオブジェクトを生成
        View view = inflater.inflate(R.layout.fragment_ui_parts, container, false);

        // ビューを取得
        Spinner testSpinner = view.findViewById(R.id.spinner_test);
        testSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // スピナーが選択された時の処理
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemString = (String) parent.getItemAtPosition(position);
                Log.d("TEST", selectedItemString);
            }

            // 不明
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
