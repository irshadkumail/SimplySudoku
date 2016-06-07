package com.sodo.kumail.sudokureloaded;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by kumail on 5/14/2016.
 */
public class GameView extends RelativeLayout {

    int tileWidth;

    int tileHeight;
    GameActivity gameActivity;
    int currentX=0;
    int currentY=0;
    OnSudokuClickListener onSudokuClickListener;

    Button[][] buttons= new Button[9][9];

    public GameView(Context context)
    {
        super(context);
        this.gameActivity= (GameActivity) context;


    }
    public GameView(Context context,AttributeSet attributeSet)
    {
        super(context,attributeSet);
        this.gameActivity= (GameActivity) context;
    }
    public GameView(Context context,AttributeSet attributeSet,int defStyle)
    {
        super(context,attributeSet,defStyle);
        this.gameActivity= (GameActivity) context;
    }
    public void setOnSudokuClickListener(OnSudokuClickListener onSudokuClickListener)
    {
        this.onSudokuClickListener=onSudokuClickListener;
    }
    public void onSizeChanged(int newW,int newH,int oldW,int oldH)
    {
        tileWidth=newW/9;
        tileHeight=newH/9;
        Log.d("Height",tileHeight+"");
        setUp();

    }
    public void setUp()
    {

         LinearLayout gameLayout= new LinearLayout(getContext());
         LinearLayout.LayoutParams gameParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
         gameLayout.setLayoutParams(gameParams);
         gameLayout.setOrientation(LinearLayout.VERTICAL);

         addView(gameLayout);



         for (int i=0;i<9;i++)
         {
             LinearLayout rowLayout= new LinearLayout(getContext());
             rowLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,tileHeight));
             rowLayout.setOrientation(LinearLayout.HORIZONTAL);

             gameLayout.addView(rowLayout);

             if(i==2 || i==5 ) {
                 View rowBorder = new View(getContext());
                 rowBorder.setLayoutParams(new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 7));
                 rowBorder.setBackground(getResources().getDrawable(R.drawable.border));
                 rowBorder.setY(tileHeight*(i+1));
                 addView(rowBorder);

                 View columnBorder= new View(getContext());
                 columnBorder.setLayoutParams(new LayoutParams(7, RelativeLayout.LayoutParams.MATCH_PARENT));
                 columnBorder.setBackground(getResources().getDrawable(R.drawable.border));

                 columnBorder.setX(tileWidth*(i+1));
                 addView(columnBorder);
             }


             for(int j=0;j<9;j++)
             {
                 buttons[i][j]= new Button(getContext());
                 buttons[i][j].setLayoutParams(new LayoutParams(tileWidth, tileHeight));
                 buttons[i][j].setBackgroundResource(R.drawable.sudoku_tile);
                 rowLayout.addView(buttons[i][j]);
                 setTileValue(i,j);
                 final int m=i;
                 final int n=j;
                 buttons[i][j].setOnClickListener(new OnClickListener() {
                     @Override
                     public void onClick(View v) {
                       onSudokuClickListener.onSudokuCLicked(m,n);

                     }
                 });


             }


         }





    }
    public void setTileValue(int i,int j)
    {
        buttons[i][j].setText(gameActivity.getTileValue(i,j));
        Log.d("ZALA",gameActivity.getTileValue(i,j));
        buttons[i][j].setTextSize(tileHeight*0.28f);

        Typeface typeface=Typeface.createFromAsset(gameActivity.getAssets(),"bridge.ttf");
        buttons[i][j].setTypeface(typeface);
    }

    public interface OnSudokuClickListener
    {
        public void onSudokuCLicked(int i,int j);
    }

}
