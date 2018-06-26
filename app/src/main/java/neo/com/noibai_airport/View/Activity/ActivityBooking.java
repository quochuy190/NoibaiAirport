package neo.com.noibai_airport.View.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.ObjWebview;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.LanguageUtils;

public class ActivityBooking extends BaseActivity {
    @BindView(R.id.webview_booking)
    WebView webView;
    private String sUrl;
    private ObjWebview objWebview;

    @Override
    public int setContentViewId() {
        return R.layout.fragment_booking;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDialogLoading();
        initAppbar();
        initData();
        initEvent();
        initWebView();

    }

    private void initWebView() {
        webView.setInitialScale(1);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadUrl(sUrl);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //showDialogLoading();
            }

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                hideDialogLoading();
                // Toast.makeText(ActivityBooking.this, "Loading comple", Toast.LENGTH_SHORT).show();
            }
        });
    }
    TextView txt_title;
    public void initAppbar() {
        ImageView img_search = findViewById(R.id.img_search);
        ImageView img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title_main);
        ImageView img_chatbox = findViewById(R.id.img_chatbox);
        txt_title.setText(R.string.txt_flight_booking);
        img_chatbox.setVisibility(View.GONE);
        img_back.setVisibility(View.VISIBLE);
        img_search.setVisibility(View.GONE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else finish();
            }
        });

    }

    private void initData() {
        objWebview = (ObjWebview) getIntent().getSerializableExtra(Constants.KEY_SEND_URL_WEBVIEW);
        if (LanguageUtils.getCurrentLanguage().getCode().equals("en")) {
            txt_title.setText(objWebview.getsId());
            if (objWebview.getsUrl_En() != null&&objWebview.getsUrl_En().length()>0)
                sUrl = objWebview.getsUrl_En();
            else sUrl = objWebview.getsUrl_Vi();
        } else if (LanguageUtils.getCurrentLanguage().getCode().equals("vi")) {
            txt_title.setText(objWebview.getsName());
            if (objWebview.getsUrl_Vi() != null&&objWebview.getsUrl_Vi().length()>0)
                sUrl = objWebview.getsUrl_Vi();
            else sUrl = objWebview.getsUrl_En();
        }

        //sUrl = getIntent().getStringExtra(Constants.KEY_SEND_URL_WEBVIEW);
    }

    private void initEvent() {

    }

    @Override
    public void onBackPressed() {
        if (!webView.canGoBack()) {
            super.onBackPressed();
        } else {
            webView.goBack();
        }
    }
}
