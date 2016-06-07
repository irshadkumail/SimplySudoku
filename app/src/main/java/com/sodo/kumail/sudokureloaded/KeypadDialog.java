package com.sodo.kumail.sudokureloaded;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by kumail on 5/15/2016.
 */
public class KeypadDialog extends DialogFragment implements View.OnClickListener {



    Button[] keypadButtons= new Button[9];
    GameActivity gameActivity;


   public static KeypadDialog newInstance(int i,int j)
   {
       KeypadDialog keypadDialog= new KeypadDialog();

       Bundle bundle= new Bundle();

       bundle.putInt("iValue",i);
       bundle.putInt("jValue",j);
       keypadDialog.setArguments(bundle);


       return keypadDialog;
   }


    public View onCreateView(LayoutInflater layoutInflater,ViewGroup parent,Bundle bundle)
    {

        View view=layoutInflater.inflate(R.layout.keypad_dialog,parent,false);

initButtons(view);
        return view;
    }
    public void initButtons(View view)
    {
        keypadButtons[0]= (Button) view.findViewById(R.id.button_1);
        keypadButtons[1]= (Button) view.findViewById(R.id.button_2);
        keypadButtons[2]= (Button) view.findViewById(R.id.button_3);
        keypadButtons[3]= (Button) view.findViewById(R.id.button_4);
        keypadButtons[4]= (Button) view.findViewById(R.id.button_5);
        keypadButtons[5]= (Button) view.findViewById(R.id.button_6);
        keypadButtons[6]= (Button) view.findViewById(R.id.button_7);
        keypadButtons[7]= (Button) view.findViewById(R.id.button_8);
        keypadButtons[8]= (Button) view.findViewById(R.id.button_9);

        for (int i=0;i<keypadButtons.length;i++)
        {

            keypadButtons[i].setOnClickListener(this);
        }

    }


    @Override
    public void onClick(View v) {
        Button button= (Button) v;
        GameActivity gameActivity= (GameActivity) getActivity();
        Log.d("Keypad",button.getText().toString());
        gameActivity.keypadPressed(getArguments().getInt("iValue"),getArguments().getInt("jValue"),button.getText().toString());


    }
}
