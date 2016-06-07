package com.sodo.kumail.sudokureloaded;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



/**
 * Created by kumail on 5/14/2016.
 */
public class GameActivity extends Activity implements View.OnClickListener,GameView.OnSudokuClickListener {

    Button startTimer, submitButton;
    MyDatabaseHelper myDatabaseHelper;
    TextView minutes, seconds;
    long startTime = 0;
    Handler timerThread;
    GameView gameView;
    SQLiteDatabase sqLiteDatabase;
    Typeface typeface;


    private final
    String easyPuzzle =
            "360000000004230800000004200"
                    +
                    "070460003820000014500013020"
                    +
                    "001900000007048300000000045";
    private final
    String mediumPuzzle =
            "650000070000506000014000005"
                    +
                    "007009000002314700000700800"
                    +
                    "500000630000201000030000097";
    private final
    String hardPuzzle =
            "009000000080605020501078000"
                    +
                    "000000700706040102004000000"
                    +
                    "000720903090301080000000600";


    public static final int HARD = 0;

    public static final int MEDIUM = 1;

    public static final int EASY = 2;


    int[] sudoValues = new int[81];

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.game_activity);

        init();


        Log.d("KUL",getFromDB());
        startGame(2);

    }

    public void init() {
        typeface = Typeface.createFromAsset(getAssets(), "bridge.ttf");
        startTimer = (Button) findViewById(R.id.timer_button);
        startTimer.setOnClickListener(this);
        startTimer.setTypeface(typeface);
        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this);
        submitButton.setTypeface(typeface);
        minutes = (TextView) findViewById(R.id.minutes);
        seconds = (TextView) findViewById(R.id.seconds);
        gameView = (GameView) findViewById(R.id.gameView);
        gameView.setOnSudokuClickListener(this);
        myDatabaseHelper = new MyDatabaseHelper(this);
        sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        insertIntoDB(easyPuzzle);
        insertIntoDB(mediumPuzzle);
        insertIntoDB(hardPuzzle);



    }

    public void startGame(int i) {
        switch (i) {
            case HARD:
                setUpPuzzle(getFromDB());
                break;
            case MEDIUM:
                setUpPuzzle(getFromDB());
                break;
            case EASY:
                setUpPuzzle(getFromDB());
                break;

        }
    }

    public void setUpPuzzle(String puzzle) {
        for (int i = 0; i < puzzle.length(); i++) {
            sudoValues[i] = Character.getNumericValue(puzzle.charAt(i));
        }

    }

    public String getTileValue(int i, int j) {
        int value = sudoValues[(i * 9) + j];

        if (value == 0)
            return "";
        else
            return value + "";

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.timer_button:
                startMyTimer();
                break;

        }

    }

    public void startMyTimer() {
        startTime = SystemClock.uptimeMillis();
        timerThread = new Handler();
        timerThread.postDelayed(new MyTimer(), 1000);


    }

    @Override
    public void onSudokuCLicked(int i, int j) {
        KeypadDialog keypadDialog = KeypadDialog.newInstance(i, j);
        FragmentManager fragmentManager = getFragmentManager();
        keypadDialog.show(fragmentManager, "Keypad");
    }

    public class MyTimer implements Runnable {

        public void run() {
            long endTime = SystemClock.uptimeMillis();

            long totalTime = endTime - startTime;

            int min = (int) (totalTime / 60000);
            Log.d("IrshadTimer", totalTime + "");
            int sec = 0;
            if (min >= 1) {
                minutes.setText(min + "");
                sec = (int) ((totalTime - 60000) / 1000);
                seconds.setText(sec + "");
            } else {
                sec = (int) (totalTime / 1000);

                seconds.setText(sec + "");
            }


            timerThread.postDelayed(this, 1000);
        }
    }

    public void keypadPressed(int i, int j, String value) {
        Log.d("Keypad", Integer.valueOf(value) + "");
        sudoValues[(i * 9) + j] = Integer.valueOf(value);
        gameView.setTileValue(i, j);


    }

    public void insertIntoDB(String string) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyColumns.PUZZLE, string);

        sqLiteDatabase.insert(MyColumns.TABLE_NAME, null, contentValues);

    }

    public String getFromDB()
    {
        String[] column={MyColumns.PUZZLE};
        Cursor cursor=sqLiteDatabase.query(MyColumns.TABLE_NAME, column, null, null, null, null, null, null);

        String answer=null;
       while (cursor.moveToNext())
       {
           answer=cursor.getString(cursor.getColumnIndex(MyColumns.PUZZLE));
       }

        return answer;
    }

}
