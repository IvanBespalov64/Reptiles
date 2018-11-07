package com.example;

import android.support.v4.app.DialogFragment;
import android.app.Dialog;
import android.os.Bundle;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.util.Log;
import android.widget.*;

public class SelectorDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(getActivity().getLayoutInflater().inflate(R.layout.selector_intro, null));
        return builder.create();
    }
}
