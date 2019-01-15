package com.yuwa_seisakusho.verifyapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * DB検証画面
 */
public class DbFragment extends Fragment {

    MainActivity activity;

    // Realmオブジェクト
    private Realm mRealm;

    public DbFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (MainActivity) getActivity();

        // レイアウトファイルからビューオブジェクトを生成
        View view = inflater.inflate(R.layout.fragment_db, container, false);

        // 処理結果ビュー取得
        final TextView resultText = view.findViewById(R.id.text_result);

        // Realmの設定
        mRealm = Realm.getDefaultInstance();

        // SELECTボタン押下時の処理
        Button selectButton = view.findViewById(R.id.button_select);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultText.setText("");

                // DBからデータを検索して取得
                RealmResults<VerifyModel> realmResults = mRealm.where(VerifyModel.class).findAll();
                List<VerifyModel> resultList =  mRealm.copyFromRealm(realmResults);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.JAPANESE);
                for (VerifyModel model : resultList) {
                    // 画面に検索結果を表示
                    resultText.setText(resultText.getText().toString() + model.getId() + "," + model.getName() + "," + model.getAge() + "," + simpleDateFormat.format(model.getDate()) + "\n");
                }
            }
        });

        // INSERTボタン押下時の処理
        Button insertButton = view.findViewById(R.id.button_insert);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DBに登録されているIDの最大値を取得する
                int id;
                RealmResults<VerifyModel> realmResults = mRealm.where(VerifyModel.class).findAll();
                if (realmResults.max("id") != null) {
                    id = realmResults.max("id").intValue() + 1;
                } else {
                    id = 0;
                }

                // DBに登録するモデルを作成
                VerifyModel model = new VerifyModel();
                model.setId(id);
                model.setName("共田");
                model.setAge(39);
                model.setDate(new Date());

                // トランザクション開始
                mRealm.beginTransaction();
                // DB登録、更新
                mRealm.copyToRealmOrUpdate(model);
                // コミット
                mRealm.commitTransaction();

                resultText.setText("DB登録に成功しました。");
            }
        });

        // UPDATEボタン押下時の処理
        Button updateButton = view.findViewById(R.id.button_update);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DBのレコード検索
                RealmResults<VerifyModel> realmResults = mRealm.where(VerifyModel.class).findAll();
                List<VerifyModel> resultList =  mRealm.copyFromRealm(realmResults);

                // 全レコードの日時を更新
                List<VerifyModel> newModelList = new ArrayList();
                for (VerifyModel model : resultList) {
                    model.setDate(new Date());
                    newModelList.add(model);
                }

                // トランザクション開始
                mRealm.beginTransaction();
                // DB登録、更新
//                for (VerifyModel model : newModelList) {
//                    mRealm.copyToRealmOrUpdate(model);
//                }
                mRealm.copyToRealmOrUpdate(newModelList); // 上のコードと同じ意味
                // コミット
                mRealm.commitTransaction();

                resultText.setText("DB更新に成功しました。");
            }
        });


        // DELETEボタン押下時の処理
        Button deleteButton = view.findViewById(R.id.button_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DBのレコード検索
                RealmResults<VerifyModel> realmResults = mRealm.where(VerifyModel.class).findAll();

                // トランザクション開始
                mRealm.beginTransaction();
                // レコード削除
                realmResults.deleteAllFromRealm();
                // コミット
                mRealm.commitTransaction();

                resultText.setText("DB削除に成功しました。");
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
