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
    private int[][] resultsgrid;

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
    public static ResultsDisplayFragment newInstance(int[][] msg, String param2) {
        ResultsDisplayFragment fragment = new ResultsDisplayFragment();
        System.out.println("ResultsDisplayFragment " + msg);
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, msg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            resultsgrid = (int[][]) getArguments().getSerializable(ARG_PARAM1);
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

        for(int row= 0; row<resultsgrid.length; row++){
            System.out.println(" resultsgrid[row][0] " + resultsgrid[row][0]+
                    "\t"+resultsgrid[row][1]+"\t"+resultsgrid[row][2]+"\t"+resultsgrid[row][3]);
            CostCategory costCategory = new CostCategory();
            List<Direction> directionArrayList = new ArrayList<>();
            Direction direction = new Direction("Go straight for "+resultsgrid[row][0]+" aisle and Turn on your right aisle\n",
                    "Go straight for "+resultsgrid[row][1]+" box slots\n",
                    "Pick on level "+resultsgrid[row][2]+" at your left\n");
            directionArrayList.add(direction);
            costCategory.setDistanceCategory("Cost of Distance: " + resultsgrid[row][3]);
            costCategory.setmListChild(directionArrayList);
            categoryList.add(costCategory);
        }
        System.out.println(" Category List " + categoryList);
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
