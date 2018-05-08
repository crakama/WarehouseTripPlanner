package com.crakama.warehouse.ui.viewdisplay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crakama.warehouse.R;
import com.crakama.warehouse.model.CostCategory;
import com.crakama.warehouse.model.Direction;
import com.crakama.warehouse.ui.viewadapter.CostCategoryAdapter;

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
    private static String ARG_PARAM1 = null;
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private List<CostCategory> categoryList = new ArrayList<>();
    private CostCategoryAdapter costCategoryAdapter;
    private OnItemClickedListener mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param msg Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultsDisplayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultsDisplayFragment newInstance(String msg, String param2) {
        ResultsDisplayFragment fragment = new ResultsDisplayFragment();
        Bundle args = new Bundle();
        System.out.println(" MSG " + msg);
        args.putString(ARG_PARAM1, msg);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            ARG_PARAM1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerview);
        setData();
        costCategoryAdapter = new CostCategoryAdapter(categoryList,getContext());
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(costCategoryAdapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
        }
    }
    public void setData() {
        final Bundle bundle = getArguments();
        String reply = bundle.getString("ARG_PARAM1");
        System.out.println(" BUNDLE GETARGS " + reply);

        List<Direction> mChildBiodata1 = new ArrayList<>();
        mChildBiodata1.add(new Direction("", ARG_PARAM1));
        List<Direction> mChildBiodata2 = new ArrayList<>();
        mChildBiodata2.add(new Direction("", "Directions to Path 2"));
        List<Direction> mChildBiodata3 = new ArrayList<>();
        mChildBiodata3.add(new Direction("", "Directions to Path 3"));
        List<Direction> mChildBiodata4 = new ArrayList<>();
        mChildBiodata4.add(new Direction("", "Directions to Path 4"));
        List<Direction> mChildBiodata5 = new ArrayList<>();
        mChildBiodata5.add(new Direction("", "Directions to Path 5"));


        CostCategory costCategory1 = new CostCategory();
        //costCategory1.setCost("Cost of Distance:");
        costCategory1.setDistanceCategory("Cost of Distance:");
        costCategory1.setmListChild(mChildBiodata1);

        CostCategory costCategory2 = new CostCategory();
        costCategory2.setCost("DistanceCost:");
        costCategory2.setDistanceCategory("Cost of Distance:");
        costCategory2.setmListChild(mChildBiodata2);

        CostCategory costCategory3 = new CostCategory();
        costCategory3.setCost("DistanceCost:");
        costCategory3.setDistanceCategory("Cost of Distance:");
        costCategory3.setmListChild(mChildBiodata3);

        CostCategory costCategory4 = new CostCategory();
        costCategory4.setCost("DistanceCost:");
        costCategory4.setDistanceCategory("Cost of Distance:");
        costCategory4.setmListChild(mChildBiodata4);

        CostCategory costCategory5 = new CostCategory();
        costCategory5.setCost("DistanceCost:");
        costCategory5.setDistanceCategory("Cost of Distance:");
        costCategory5.setmListChild(mChildBiodata5);

        categoryList.add(costCategory1);
        categoryList.add(costCategory2);
        categoryList.add(costCategory3);
        categoryList.add(costCategory4);
        categoryList.add(costCategory5);
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
