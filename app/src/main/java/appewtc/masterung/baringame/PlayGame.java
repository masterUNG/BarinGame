package appewtc.masterung.baringame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class PlayGame extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private RelativeLayout relativeLayout;
    private int indexAnInt;
    private String tag = "15janV1";
    private int[] backgroundInts = new int[]{R.drawable.bg0, R.drawable.bg1,
            R.drawable.bg2, R.drawable.bg3};
    private TextView questionTextView, scoreTextView, showTimeTextView;
    private Button[] buttons = new Button[4];
    private int[] buttonInts = new int[]{R.id.button2, R.id.button3,
            R.id.button4, R.id.button5};
    private String[] questionStrings, choice1Strings, choice2Strings,
            choice3Strings, choice4Strings, answerStrings;
    private int indexTimes = 0; //  หมายถึงข้อ 0,1,2,3 ...
    private int scoreAnInt = 0; // คะแนน
    private int total; // จำนวนข้อ
    private boolean aBoolean = true;
    private int startTime = 120;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        //Bind Widget
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_play_game);
        questionTextView = (TextView) findViewById(R.id.textView3);
        for (int i = 0; i < buttonInts.length; i++) {
            buttons[i] = (Button) findViewById(buttonInts[i]);
        }
        scoreTextView = (TextView) findViewById(R.id.textView);
        showTimeTextView = (TextView) findViewById(R.id.textView2);


        //Setup
        indexAnInt = getIntent().getIntExtra("Index", 0);
        Log.d(tag, "ค่าที่ส่งมาจาก Mode ==> " + indexAnInt);

        //Change View
        relativeLayout.setBackgroundResource(backgroundInts[indexAnInt]);

        try {

            SynQuestion synQuestion = new SynQuestion(PlayGame.this);
            synQuestion.execute(Integer.toString(indexAnInt));
            String strJSON = synQuestion.get();
            if (strJSON.length() != 0) {
                synQuestion.progressDialog.dismiss();
            }
            Log.d(tag, "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            questionStrings = new String[jsonArray.length()];
            choice1Strings = new String[jsonArray.length()];
            choice2Strings = new String[jsonArray.length()];
            choice3Strings = new String[jsonArray.length()];
            choice4Strings = new String[jsonArray.length()];
            answerStrings = new String[jsonArray.length()];
            total = jsonArray.length();

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                questionStrings[i] = jsonObject.getString("Question");
                choice1Strings[i] = jsonObject.getString("Choice1");
                choice2Strings[i] = jsonObject.getString("Choice2");
                choice3Strings[i] = jsonObject.getString("Choice3");
                choice4Strings[i] = jsonObject.getString("Choice4");
                answerStrings[i] = jsonObject.getString("Answer");

            }   // for

            showView();


        } catch (Exception e) {
            e.printStackTrace();
        }

        //Button Controller
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener(PlayGame.this);
        }


        myLoop();


    }   // Main Method

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private void myLoop() {

        if (aBoolean) {

            showTimeTextView.setText(Integer.toString(startTime) + " Sec");
            startTime -= 1;

            if (startTime == 0) {
                showScore();
            }


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    myLoop();
                }
            }, 1000);


        }   //if
    }   // myLoop

    private void showView() {

        questionTextView.setText(Integer.toString(indexTimes + 1) + ". " + questionStrings[indexTimes]);
        buttons[0].setText(choice1Strings[indexTimes]);
        buttons[1].setText(choice2Strings[indexTimes]);
        buttons[2].setText(choice3Strings[indexTimes]);
        buttons[3].setText(choice4Strings[indexTimes]);

    }   // showView

    @Override
    public void onClick(View view) {

        if (indexTimes < total) {

            int i = 0;

            switch (view.getId()) {
                case R.id.button2:
                    i = 1;
                    break;
                case R.id.button3:
                    i = 2;
                    break;
                case R.id.button4:
                    i = 3;
                    break;
                case R.id.button5:
                    i = 4;
                    break;
            }

            if (i == Integer.parseInt(answerStrings[indexTimes])) {
                scoreAnInt += 1;
                scoreTextView.setText("Score = " + Integer.toString(scoreAnInt));
            }


            indexTimes += 1;
            showView();

        } else {
            showScore();
        }


    }   // onClick

    private void showScore() {
        Intent intent = new Intent(PlayGame.this, ShowScore.class);
        intent.putExtra("Score", scoreAnInt);
        startActivity(intent);
        finish();
    }
}   // Main Class
