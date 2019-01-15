package com.yuwa_seisakusho.verifyapp;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 検証テーブルモデルクラス
 */
public class VerifyModel extends RealmObject implements Serializable {

    // フィールド定義
    private String name; // 名前
    private int age;     // 年齢
    private Date date;   // 日時

    @PrimaryKey
    private int id;      // ID

    // メソッド定義
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
