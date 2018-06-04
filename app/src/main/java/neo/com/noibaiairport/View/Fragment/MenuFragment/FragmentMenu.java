package neo.com.noibaiairport.View.Fragment.MenuFragment;

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
import neo.com.noibaiairport.Config.Constants;
import neo.com.noibaiairport.View.Activity.ActivityChangeLanguage;
import neo.com.noibaiairport.View.Activity.Feedback.ActivityFeedback;
import neo.com.noibaiairport.View.Activity.MainActivity.MainActivity;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.untils.BaseFragment;

import static android.app.Activity.RESULT_OK;

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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.RequestCode.CHANGE_LANGUAGE:
                if (resultCode == RESULT_OK) {
                    updateViewByLanguage();
                }
                break;
        }
    }
    private void updateViewByLanguage() {
        init();
       // initBottomBar();
        startActivity(new Intent(getActivity(), MainActivity.class));
       getActivity().finish();
    }

    private void init() {

    }


}
