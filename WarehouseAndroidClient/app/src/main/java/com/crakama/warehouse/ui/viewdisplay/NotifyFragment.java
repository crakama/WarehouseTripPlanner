package com.crakama.warehouse.ui.viewdisplay;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.crakama.warehouse.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NotifyFragment.OnUpdateDialogListener} interface
 * to handle interaction events.
 * Use the {@link NotifyFragment#@newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotifyFragment extends AppCompatDialogFragment {
    private OnUpdateDialogListener mListener;
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_notify,null);
        String reply = getArguments().getString("reply");
        builder.setView(view).setTitle("Changes on Product").setMessage(reply).setNegativeButton("Ignore", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("View Updates", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onUpdateOKBtnPressed(view, "yes");
            }
        });
        return builder.create();
    }

    public void onUpdateOKBtnPressed(View v, String s) {
        if (mListener != null) {
            mListener.btnOKClicked(s);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUpdateDialogListener) {
            mListener = (OnUpdateDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnUpdateDialogListener");
        }
    }

    public interface OnUpdateDialogListener {
        // TODO: Update argument type and name
        void btnOKClicked(String text);
    }

}