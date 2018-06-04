package neo.com.noibaiairport.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import neo.com.noibaiairport.Adapter.MessageListAdapter;
import neo.com.noibaiairport.App;
import neo.com.noibaiairport.Model.Message;
import neo.com.noibaiairport.Model.User;
import neo.com.noibaiairport.R;
import neo.com.noibaiairport.untils.BaseFragment;

public class FragmentChatbot extends BaseFragment {
    public static FragmentChatbot fragment;

    public static synchronized FragmentChatbot getInstance() {
        if (fragment == null) {
            fragment = new FragmentChatbot();
        }
        return (fragment);
    }
    MessageListAdapter listAdapter;
    List<Message> lisMessage;
    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.reyclerview_message_list)
    RecyclerView reyclerview_message_list;
    @BindView(R.id.edt_input_message)
    EditText edt_input_message;
    @BindView(R.id.button_chatbox_send)
    ImageView btn_send;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.myUser = new User("101", "quochuy190", "Quốc Huy", "", "", "");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatbot, container, false);
        ButterKnife.bind(this, view);
        initData();
        init();
        initEvent();
        return view;
    }

    private void initEvent() {
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_input_message.getText().toString().length()>0){
                    lisMessage.add(new Message("10",""+edt_input_message.getText().toString(),
                            new User("101", "quochuy190", "Quốc Huy",
                                    "","",""),
                            19029));
                    edt_input_message.setText("");
                   // reyclerview_message_list.scrollToPosition(lisMessage.size()-1);
                    listAdapter.notifyDataSetChanged();
                    reyclerview_message_list.scrollToPosition(lisMessage.size()-1);
                }
            }
        });
    }

    private void initData() {
        lisMessage = new ArrayList<>();
        lisMessage.add(new Message("1","You can build more advanced features like an",
                new User("101", "quochuy190", "Quốc Huy", "","",""),
                19029));
        lisMessage.add(new Message("2","We've created all the components, and now we need to initialize them inside ",
                new User("102", "chatbot", "Quốc Huy", "","",""),
                19029));
        lisMessage.add(new Message("3","We can now display a list of messages",
                new User("102", "chatbot", "Quốc Huy", "","",""),
                19029));
        lisMessage.add(new Message("4","We've created all the components",
                new User("103", "chatbot", "Quốc Huy", "","",""),
                19029));
        lisMessage.add(new Message("5","You've just created your own messaging UI.",
                new User("101", "quochuy190", "Quốc Huy", "","",""),
                19029));
        lisMessage.add(new Message("6","itemView.findViewById R.id.text_message_time",
                new User("104", "chatbot", "Quốc Huy", "","",""),
                19029));
    }

    private void init() {
        listAdapter = new MessageListAdapter(getContext(), lisMessage);
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        //recycle_category.setNestedScrollingEnabled(false);
        reyclerview_message_list.setHasFixedSize(true);
        reyclerview_message_list.setLayoutManager(mLayoutManager);
        reyclerview_message_list.setItemAnimator(new DefaultItemAnimator());
        reyclerview_message_list.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

}
