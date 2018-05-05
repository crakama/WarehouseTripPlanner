package com.crakama.warehouseclient;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchItemFragment.OnItemClickedListener} interface
 * to handle interaction events.
 * Use the {@link SearchItemFragment#@newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchItemFragment extends Fragment {
    private OnItemClickedListener mListener;
    private EditText productID;
    static Button searchButton;
    static TextView searchInfo;
    private static Boolean viewable = false;
    // Required empty public constructor
    public SearchItemFragment() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_item, container, false);
        this.productID = view.findViewById(R.id.productID);
        searchInfo = view.findViewById(R.id.search_info);
        this.searchButton = view.findViewById(R.id.search_button);
        this.searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onButtonPressed(v);
            }
        });

        viewable = true;
        return view;
    }
    public static void setButtonState(Boolean state){
        searchButton.setEnabled(state);
    }
    public static void setSearchInfo(String connectionInfo) {
        if(viewable == true) {
            SearchItemFragment.searchInfo.setText(connectionInfo);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(View view) {
        if (mListener != null) {
            mListener.searchBtnClicked(this.productID.getText().toString());
            System.out.println("SEARCH BUTTON CLICKED");
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
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnItemClickedListener {
        void searchBtnClicked(String productid);
    }
}
