package com.crakama.warehouseclient.ui.viewdisplay;

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
    private List<CostCategory> categoryList = new ArrayList<>();
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
        List<Direction> mChildBiodata1 = new ArrayList<>();
        mChildBiodata1.add(new Direction("Single", "I am a free man!"));
        List<Direction> mChildBiodata2 = new ArrayList<>();
        mChildBiodata2.add(new Direction("Married", "I am a married man!"));
        List<Direction> mChildBiodata3 = new ArrayList<>();
        mChildBiodata3.add(new Direction("Young", "I am a young man!"));
        List<Direction> mChildBiodata4 = new ArrayList<>();
        mChildBiodata4.add(new Direction("Married", "I am a married man!"));
        List<Direction> mChildBiodata5 = new ArrayList<>();
        mChildBiodata5.add(new Direction("Semelekete", "I am a semelekete man!"));


        CostCategory mHeadBiodata1 = new CostCategory();
        mHeadBiodata1.setAge("10");
        mHeadBiodata1.setName("Komak");
        mHeadBiodata1.setmListChild(mChildBiodata1);

        CostCategory mHeadBiodata2 = new CostCategory();
        mHeadBiodata2.setAge("20");
        mHeadBiodata2.setName("Kacang");
        mHeadBiodata2.setmListChild(mChildBiodata2);

        CostCategory mHeadBiodata3 = new CostCategory();
        mHeadBiodata3.setAge("15");
        mHeadBiodata3.setName("Kare");
        mHeadBiodata3.setmListChild(mChildBiodata3);

        CostCategory mHeadBiodata4 = new CostCategory();
        mHeadBiodata4.setAge("24");
        mHeadBiodata4.setName("Kedelai");
        mHeadBiodata4.setmListChild(mChildBiodata4);

        CostCategory mHeadBiodata5 = new CostCategory();
        mHeadBiodata5.setAge("32");
        mHeadBiodata5.setName("Rozan");
        mHeadBiodata5.setmListChild(mChildBiodata5);

        categoryList.add(mHeadBiodata1);
        categoryList.add(mHeadBiodata2);
        categoryList.add(mHeadBiodata3);
        categoryList.add(mHeadBiodata4);
        categoryList.add(mHeadBiodata5);
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
