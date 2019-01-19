package com.yuwa_seisakusho.verifyapp;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * WebView検証画面
 */
public class WebViewFragment extends Fragment {

    MainActivity activity;

    public WebViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (MainActivity) getActivity();

        // ビュー取得
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        final WebView webView = view.findViewById(R.id.web_view);

        // WebViewClient設定
        WebViewClient client = new WebViewClient() {
            // 新しいURLが指定されたときの処理
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                // WebView内に読み込み結果を表示する場合はfalse
                // 別のアクティビティやアプリを起動する場合はtrue
                return false;
            }

            // ページ読み込み開始時の処理
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Toast.makeText(activity, "読み込み開始", Toast.LENGTH_SHORT).show();
            }

            // ページ読み込み完了時の処理
            @Override
            public void onPageFinished(WebView view, String url) {
                Toast.makeText(activity, "読み込み完了", Toast.LENGTH_SHORT).show();
            }

            // ページ読み込みエラー時の処理
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(activity, "読み込みエラー", Toast.LENGTH_SHORT).show();
            }

        };

        // WebView設定
        webView.setWebViewClient(client);                    // WebViewClientをセット
        webView.getSettings().setJavaScriptEnabled(true);    // Javascript設定
        webView.getSettings().setBuiltInZoomControls(false); // ズームコントロール設定
        webView.getSettings().setDomStorageEnabled(true);    // DOMストレージ設定（ローカルに値を保存できる）
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE); // キャッシュを使わない
        webView.setVerticalScrollBarEnabled(true);           // 縦方向のスクロールバ設定
        webView.setHorizontalScrollBarEnabled(true);         // 横方向のスクロールバー設定

        // Javascriptからの呼び出しを可能にするための登録処理
        webView.addJavascriptInterface(new JavascriptInterface(activity), "android");

        // Webページ読み込み
        // サーバが起動していない場合などのタイムアウト設定はなさそう
        webView.loadUrl("http://133.167.108.164/webview.html");

        // Javascript関数呼び出しボタン押下時の処理
        final TextView nativeText = view.findViewById(R.id.text_native);
        Button jsCallButton = view.findViewById(R.id.button_js_call);
        jsCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "Javascript呼び出し");
                // Javascript実行 第二引数はJSからコールバックされる関数(Javascriptから直接戻り値を受け取れない) 引数、戻り値は文字列のみ
                webView.evaluateJavascript("javascript:outputTextJs('ネイティブから渡した値')", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.d("TEST", value);

                        // Javascriptからの戻り値にはダブルクォートが付いているので、取り除く必要がある
                        value = value.substring(1, value.length()-1);
                        nativeText.setText(value);
                    }
                });
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
