package neo.com.noibaiairport.View.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.untils.BaseActivity;

public class ActivityBooking extends BaseActivity {
    @BindView(R.id.webview_booking)
    WebView webView;
    @BindView(R.id.btn_back_booking)
    ImageView btn_back;
    @BindView(R.id.txt_title_booking)
    TextView txt_title_booking;

    @Override
    public int setContentViewId() {
        return R.layout.fragment_booking;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDialogLoading();
        initEvent();
        webView.setInitialScale(1);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadUrl("https://www.elines.vn");
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
        txt_title_booking.setText("Flight booking");
    }

    private void initEvent() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()) {
                    webView.goBack();
                }
            }
        });
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
