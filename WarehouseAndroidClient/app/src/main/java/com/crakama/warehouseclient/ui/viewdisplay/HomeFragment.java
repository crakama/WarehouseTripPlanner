package com.crakama.warehouseclient.ui.viewdisplay;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.crakama.warehouseclient.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnItemClickedListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#@newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private OnItemClickedListener mListener;
    private EditText username,password;
    static TextView connection_info;
    static Button connection_btn;
    private static Boolean viewable = false;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        this.username = view.findViewById(R.id.username);
        this.password = view.findViewById(R.id.password);
        connection_info = view.findViewById(R.id.connection_info);
        connection_btn = view.findViewById(R.id.connection_button);
        connection_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onButtonPressed(v);
            }
        });

        viewable = true;
        return view;
    }
    public static void setButtonState(Boolean state){
        connection_btn.setEnabled(state);
    }
    public static void setConnectionInfo(String connectionInfo) {
        if(viewable == true) {
            HomeFragment.connection_info.setText(connectionInfo);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(View view) {
        if (mListener != null) {
            mListener.connectionBtnClicked(this.username.getText().toString(),this.password.getText().toString());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemClickedListener) {
            mListener = (OnItemClickedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other viewdisplay contained in that
     * activity.
     */
    public interface OnItemClickedListener {
        void connectionBtnClicked(String text, String s);
    }
}
