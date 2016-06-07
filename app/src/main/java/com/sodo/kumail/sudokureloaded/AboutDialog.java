package com.sodo.kumail.sudokureloaded;

import android.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by kumail on 5/14/2016.
 */
public class AboutDialog extends DialogFragment implements View.OnClickListener {

    TextView aboutText;
    Button dismissButton;

    public View onCreateView(LayoutInflater layoutInflater,ViewGroup parent,Bundle bundle)
    {
        View view=layoutInflater.inflate(R.layout.about_dialog,parent,false);

        aboutText= (TextView) view.findViewById(R.id.dialog_about_text);
        dismissButton= (Button) view.findViewById(R.id.dialog_dismiss);
        Typeface aboutType=Typeface.createFromAsset(getActivity().getAssets(),"bridge.ttf");
        aboutText.setTypeface(aboutType);
        dismissButton.setOnClickListener(this);

        return view;
    }
    public void onClick(View view)
    {
        dismiss();
    }
}
