package com.sodo.kumail.sudokureloaded;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class StartActivity extends ActionBarActivity implements View.OnClickListener {

    TextView gameNameText,gameSubnameText;

    Button continueButton,newGameButton,aboutButton,exitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

       init();


    }
    public void init()
    {

        gameNameText= (TextView) findViewById(R.id.game_name_text);
        gameSubnameText= (TextView) findViewById(R.id.gameSubnameText);
        Typeface typefaceSub=Typeface.createFromAsset(getAssets(),"quadrats.ttf");
        Typeface typefaceMain=Typeface.createFromAsset(getAssets(),"bridge.ttf");
        gameNameText.setTypeface(typefaceMain);
        gameSubnameText.setTypeface(typefaceSub);
        continueButton= (Button) findViewById(R.id.continueButton);
        continueButton.setOnClickListener(this);
        newGameButton=(Button)findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(this);
        aboutButton=(Button)findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(this);
        exitButton=(Button)findViewById(R.id.exitButton);
        exitButton.setOnClickListener(this);


    }
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.newGameButton:
                startActivity(new Intent(this,GameActivity.class));
                break;
            case R.id.aboutButton:
                FragmentManager fragmentManager=getFragmentManager();
                AboutDialog aboutDialog= new AboutDialog();
                aboutDialog.show(fragmentManager,"About Sudoku");
                break;
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
