package com.yuwa_seisakusho.verifyapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;


/**
 * カレンダー検証画面
 */
public class CalendarFragment extends Fragment {

    MainActivity activity;

    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (MainActivity) getActivity();

        // レイアウトファイルからビューオブジェクトを生成
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        // ビューを取得
        CalendarView calendarView = view.findViewById(R.id.calendar_view);
        final TextView dateText = view.findViewById(R.id.text_date);

        // カレンダーの日付が押下された場合の処理
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dateText.setText(String.valueOf(year) + "年" + String.valueOf(month + 1) + "月" + String.valueOf(dayOfMonth) + "日");
            }
        });

        return view;
    }

}
