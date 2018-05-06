package com.crakama.warehouseclient.ui.viewdisplay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crakama.warehouseclient.R;
import com.crakama.warehouseclient.model.CostCategory;
import com.crakama.warehouseclient.model.Direction;
import com.crakama.warehouseclient.ui.viewadapter.CostCategoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ResultsDisplayFragment.OnItemClickedListener} interface
 * to handle interaction events.
 * Use the {@link ResultsDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultsDisplayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private List<CostCategory> categoryList;
    private CostCategoryAdapter costCategoryAdapter;
    private OnItemClickedListener mListener;

    public ResultsDisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultsDisplayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultsDisplayFragment newInstance(String param1, String param2) {
        ResultsDisplayFragment fragment = new ResultsDisplayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_results, container, false);
        getGenres();
        costCategoryAdapter = new CostCategoryAdapter(categoryList);
        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(costCategoryAdapter);
        return rv;
        // Inflate the layout for this fragment
        //return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
        }
    }
    public void getGenres() {
        categoryList = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            List<Direction> artists = new ArrayList<>(3);
            artists.add(new Direction("Paramore"));
            artists.add(new Direction("Flyleaf"));
            artists.add(new Direction("The Script"));
            categoryList.add(new CostCategory("Rock_ " + i, artists));
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnItemClickedListener {
        // TODO: Update argument type and name
        //void onFragmentInteraction(Uri uri);
    }
}
