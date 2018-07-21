package neo.com.noibai_airport.View.Fragment.MenuFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibai_airport.Config.ConstansWebview;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.ObjWebview;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.ActivityBooking;
import neo.com.noibai_airport.View.Activity.ActivityChangeLanguage;
import neo.com.noibai_airport.View.Activity.Feedback.ActivityFeedback;
import neo.com.noibai_airport.View.Activity.MainActivity.MainActivity;
import neo.com.noibai_airport.untils.BaseFragment;
import neo.com.noibai_airport.untils.SharedPrefs;

import static android.app.Activity.RESULT_OK;
import static neo.com.noibai_airport.Config.Constants.KEY_SEND_URL_WEBVIEW;

public class FragmentMenu extends BaseFragment {
    public static FragmentMenu fragment;

    public static synchronized FragmentMenu getInstance() {
        if (fragment == null) {
            fragment = new FragmentMenu();
        }
        return (fragment);
    }

    @BindView(R.id.linear_language)
    LinearLayout linear_language;
    @BindView(R.id.linear_feedback)
    LinearLayout linear_feedback;
    @BindView(R.id.ll_map)
    LinearLayout ll_map;
    @BindView(R.id.ll_about)
    LinearLayout ll_about;
    @BindView(R.id.linear_hotline)
    LinearLayout linear_hotline;
    @BindView(R.id.ll_tax_refund)
    LinearLayout ll_tax_refund;
    @BindView(R.id.ll_baggage_lost)
    LinearLayout ll_baggage_lost;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        init();
        initEvent();
        return view;
    }

    private void initEvent() {
        linear_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                Intent intent = new Intent(getActivity(), ActivityChangeLanguage.class);
                startActivityForResult(intent, Constants.RequestCode.CHANGE_LANGUAGE);*/
                Intent intent = new Intent(getContext(), ActivityChangeLanguage.class);
                startActivityForResult(intent, Constants.RequestCode.CHANGE_LANGUAGE);

            }
        });
        linear_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityFeedback.class);
                // startActivityForResult(intent, Constants.RequestCode.CHANGE_LANGUAGE);
                startActivity(intent);
            }
        });
        ll_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityMap.class);
                startActivity(intent);
            }
        });
        ll_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjWebview objWebview = new ObjWebview("About", "Thông tin", 0,
                        "http://noibai.online/nia/vi/chungtoi.html",
                        "http://noibai.online/nia/en/about.html");
                Intent intent = new Intent(getActivity(), ActivityBooking.class);
                intent.putExtra(KEY_SEND_URL_WEBVIEW, objWebview);
                startActivity(intent);
            }
        });
        ll_baggage_lost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjWebview objWebview = new ObjWebview("Baggage Lost Found", "Hành lý thất lạc", 0,
                        ConstansWebview.URL_WEB_BAGGAGE_LOST_FOUND_VI,
                        ConstansWebview.URL_WEB_BAGGAGE_LOST_FOUND_EN);
                Intent intent = new Intent(getActivity(), ActivityBooking.class);
                intent.putExtra(KEY_SEND_URL_WEBVIEW, objWebview);
                startActivity(intent);
            }
        });
        ll_tax_refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjWebview objWebview = new ObjWebview("TAX Refund", "Hoàn VAT", 0,
                        ConstansWebview.URL_WEB_TAX_REFUND_VI,
                        ConstansWebview.URL_WEB_TAX_REFUND_EN);
                Intent intent = new Intent(getActivity(), ActivityBooking.class);
                intent.putExtra(KEY_SEND_URL_WEBVIEW, objWebview);
                startActivity(intent);
            }
        });
        linear_hotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.call_phone(getContext(), getString(R.string.hotline));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.RequestCode.CHANGE_LANGUAGE:
                if (resultCode == RESULT_OK) {
                    SharedPrefs.getInstance().put(Constants.KEY_CHANGE_LANGUAGE, true);
                    updateViewByLanguage();
                }
                break;
        }
    }

    private void updateViewByLanguage() {
      /*  // initBottomBar();
        getActivity().finish();
        startActivity(new Intent(getActivity(), MainActivity.class));
        FragmentFlightHome.isLoadViewDeparture = true;
        FragmentFlightHome.isLoadViewArrivals = true;*/

    }

    private void init() {

    }


}
