package neo.com.noibaiairport.Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import neo.com.noibaiairport.View.Activity.MainActivity.MainActivity;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.untils.BaseActivity;
import neo.com.noibaiairport.untils.LanguageUtils;


public class SplashScreen extends BaseActivity {
    private static final String TAG = "SplashScreen";


    ImageView img_splash;
    // public static Storage storage; // this Preference comes for free from the library
    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    Intent mainIntent = new Intent();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        LanguageUtils.loadLocale();
        mainIntent.setClass(SplashScreen.this, MainActivity.class);
        img_splash = (ImageView) findViewById(R.id.img_splash);
        Glide.with(this).load(R.drawable.img_plash).into(img_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public int setContentViewId() {
        return R.layout.activity_splash_screen;
    }


    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */


}
