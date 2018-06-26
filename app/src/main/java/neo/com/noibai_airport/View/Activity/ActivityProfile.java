package neo.com.noibai_airport.View.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.Language;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.MainActivity.MainActivity;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.LanguageUtils;

public class ActivityProfile extends BaseActivity {
    @BindView(R.id.img_avata)
    CircleImageView img_avata;
    @BindView(R.id.btn_change_language)
    Button btn_change_language;
   /* @BindView(R.id.btn_vie)
    Button btn_vie;*/
    @Override
    public int setContentViewId() {
        return R.layout.activity_profile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvent();
        initAppbar();
    }
    public void initAppbar(){
        TextView txt_title= findViewById(R.id.txt_title_main);
        txt_title.setText(R.string.txt_language);
        ImageView img_back = findViewById(R.id.img_back);
        img_back.setVisibility(View.VISIBLE);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void initEvent() {
       /* btn_vie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_language("vi");
            }
        });*/
        btn_change_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    change_language("en");
                Intent intent = new Intent(ActivityProfile.this, ActivityChangeLanguage.class);
                startActivityForResult(intent, Constants.RequestCode.CHANGE_LANGUAGE);
                //startActivity(new Intent(ActivityProfile.this, ActivityChangeLanguage.class));
            }
        });
    }

    private void change_language(String language) {
        Locale locale = new Locale(language);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        startActivity(new Intent(ActivityProfile.this, MainActivity.class));
        //finish();

    }
    private void onChangeLanguageSuccessfully(final Language language) {
      //  mLanguageAdapter.setCurrentLanguage(language);
        LanguageUtils.changeLanguage(language);
        setResult(RESULT_OK, new Intent());
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.RequestCode.CHANGE_LANGUAGE:
                if (resultCode == RESULT_OK) {
                    setResult(RESULT_OK, new Intent());
                    finish();
                }
                break;
        }
    }
}
