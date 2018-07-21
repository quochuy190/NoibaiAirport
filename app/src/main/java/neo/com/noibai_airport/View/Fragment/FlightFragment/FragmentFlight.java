package neo.com.noibai_airport.View.Fragment.FlightFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibai_airport.App;
import neo.com.noibai_airport.Config.Constants;
import neo.com.noibai_airport.Model.ObjWebview;
import neo.com.noibai_airport.R;
import neo.com.noibai_airport.View.Activity.ActivityBooking;
import neo.com.noibai_airport.View.Activity.ActivityFlight.ActivityAirlines;
import neo.com.noibai_airport.View.Activity.ActivityFlight.ActivityCheckinOnline;
import neo.com.noibai_airport.View.Activity.ActivityFlight.ActivityFlightInstuction;
import neo.com.noibai_airport.untils.BaseFragment;
import neo.com.noibai_airport.untils.DialogUtil;

public class FragmentFlight extends BaseFragment implements View.OnClickListener {
    public static FragmentFlight fragment;

    public static synchronized FragmentFlight getInstance() {
        if (fragment == null) {
            fragment = new FragmentFlight();
        }
        return (fragment);
    }

    @BindView(R.id.ll_checkin)
    LinearLayout ll_checkin;
    @BindView(R.id.ll_booking)
    LinearLayout ll_booking;
    @BindView(R.id.ll_instuction)
    LinearLayout ll_instuction;
    @BindView(R.id.ll_ariline_info)
    LinearLayout ll_ariline_info;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight, container, false);
        ButterKnife.bind(this, view);
        initEvent();
        return view;
    }

    private void initEvent() {
        ll_checkin.setOnClickListener(this);
        ll_booking.setOnClickListener(this);
        ll_instuction.setOnClickListener(this);
        ll_ariline_info.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (isNetwork()) {
            switch (view.getId()) {
                case R.id.ll_checkin:
                    click(new ActivityCheckinOnline());
                    break;
                case R.id.ll_booking:
                    if (App.isFlightBoking) {
                        showDialogWarning(
                                getString(R.string.title_warning),
                                getString(R.string.message_warning),
                                false,
                                new DialogUtil.ClickDialog() {
                                    @Override
                                    public void onClickYesDialog() {
                                        App.isFlightBoking = false;
                                        Intent intent = new Intent(getActivity(), ActivityBooking.class);
                                        ObjWebview obj = new ObjWebview("", "BookingOnline", 0,
                                                "https://www.elines.vn/",
                                                "https://www.elines.vn/");
                                        intent.putExtra(Constants.KEY_SEND_URL_WEBVIEW, obj);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onClickNoDialog() {

                                    }
                                });
                        /*DialogUtil.ShowAlertDialogAnimationUpBottom2Button(getContext(),
                                getString(R.string.title_warning),
                                getString(R.string.message_warning),
                                getString(R.string.btn_ok),
                                getString(R.string.btn_cancel), new DialogUtil.ClickDialog() {
                                    @Override
                                    public void onClickYesDialog() {
                                        App.isFlightBoking = false;
                                        Intent intent = new Intent(getActivity(), ActivityBooking.class);
                                        ObjWebview obj = new ObjWebview("", "BookingOnline", 0,
                                                "https://www.elines.vn/",
                                                "https://www.elines.vn/");
                                        intent.putExtra(Constants.KEY_SEND_URL_WEBVIEW, obj);
                                        startActivity(intent);

                                    }

                                    @Override
                                    public void onClickNoDialog() {

                                    }
                                });*/
                    } else {
                        Intent intent = new Intent(getActivity(), ActivityBooking.class);
                        ObjWebview obj = new ObjWebview("", "BookingOnline", 0,
                                "https://www.elines.vn/",
                                "https://www.elines.vn/");
                        intent.putExtra(Constants.KEY_SEND_URL_WEBVIEW, obj);
                        startActivity(intent);
                    }

                    break;
                case R.id.ll_instuction:
                    click(new ActivityFlightInstuction());
                    break;
                case R.id.ll_ariline_info:
                    click(new ActivityAirlines());
                    break;

            }
        }
    }

    private void click(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(getActivity(), appCompatActivity.getClass());
        startActivity(intent);
    }
}
