package neo.com.noibaiairport.untils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {

    protected AlertDialog alertDialog;
    boolean layout = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        onPostSetContentView(savedInstanceState);
        setContentView(setContentViewId());
        ButterKnife.bind(this);
    }

    public void setLayout(boolean layout) {
        this.layout = layout;
    }

    protected void onPostSetContentView(Bundle savedInstanceState) {

    }

    public abstract int setContentViewId();

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void showDialogYesNo(String title, String content){

    }
    protected ProgressDialog dialog;
    private Handler StopDialogLoadingHandler = new Handler();

    public void hideDialogLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    public void showDialogLoading() {
        StopDialogLoadingHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }, 5000);
        if (!isFinishing()) {
            dialog = new ProgressDialog(this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Loading. Please wait...");
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);
        }
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void showDialogLoadingtime(int time) {
        StopDialogLoadingHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }, time);
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Loading. Please wait...");
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);
        }
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void showDialogLoading(String message) {
        StopDialogLoadingHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }, 10000);
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage(message);
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);
        }
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }


}
