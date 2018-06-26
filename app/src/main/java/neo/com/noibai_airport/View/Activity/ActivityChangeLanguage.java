package neo.com.noibai_airport.View.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import neo.com.noibai_airport.Adapter.AdapterLanguage;
import neo.com.noibai_airport.Model.Language;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.untils.BaseActivity;
import neo.com.noibai_airport.untils.DialogUtil;
import neo.com.noibai_airport.untils.ItemClickListener;
import neo.com.noibai_airport.untils.LanguageUtils;

public class ActivityChangeLanguage extends BaseActivity {
    @BindView(R.id.recycle_language)
    RecyclerView recycle_language;
    AdapterLanguage adapterLanguage;
    private List<Language> lisLanguage;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    public int setContentViewId() {
        return R.layout.activity_change_language;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        initAppbar();
    }

    public void initAppbar() {
        TextView txt_title = findViewById(R.id.txt_title_main);
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

    private void init() {
        lisLanguage = LanguageUtils.getLanguageData();
        Language objLan = LanguageUtils.getCurrentLanguage();
        for (int i = 0; i < lisLanguage.size(); i++) {
            if (lisLanguage.get(i).getId() == objLan.getId()) {
                lisLanguage.get(i).setChecked(true);
            } else {
                lisLanguage.get(i).setChecked(false);
            }
        }
        adapterLanguage = new AdapterLanguage(this, lisLanguage);
        mLayoutManager = new GridLayoutManager(this, 1);
        recycle_language.setNestedScrollingEnabled(false);
        recycle_language.setHasFixedSize(true);
        recycle_language.setLayoutManager(mLayoutManager);
        recycle_language.setItemAnimator(new DefaultItemAnimator());
        recycle_language.setAdapter(adapterLanguage);
        adapterLanguage.notifyDataSetChanged();

        adapterLanguage.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClickItem(int position, final Object item) {
                   DialogUtil.showDialogCancelable(ActivityChangeLanguage.this,
                           getResources().getString(R.string.dialog_title),
                           getResources().getString(R.string.dialog_message),
                           getResources().getString(R.string.btn_ok),
                           getResources().getString(R.string.btn_cancel),
                           new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   LanguageUtils.changeLanguage((Language) item);
                                   setResult(RESULT_OK, new Intent());
                                   finish();
                               }
                           });
            }
        });
    }
}
