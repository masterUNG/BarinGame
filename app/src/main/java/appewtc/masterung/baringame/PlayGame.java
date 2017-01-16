package appewtc.masterung.baringame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class PlayGame extends AppCompatActivity {

    //Explicit
    private RelativeLayout relativeLayout;
    private int indexAnInt;
    private String tag = "15janV1";
    private int[] backgroundInts = new int[]{R.drawable.bg0, R.drawable.bg1,
            R.drawable.bg2, R.drawable.bg3};
    private TextView questionTextView;
    private Button[] buttons = new Button[4];
    private int[] buttonInts = new int[]{R.id.button2, R.id.button3,
            R.id.button4, R.id.button5};
    private String[] questionStrings, choice1Strings, choice2Strings,
            choice3Strings, choice4Strings, answerStrings;
    private int indexTimes = 0;


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


    }   // Main Method

    private void showView() {

        questionTextView.setText(Integer.toString(indexTimes + 1) + ". " + questionStrings[indexTimes]);
        buttons[0].setText(choice1Strings[indexTimes]);
        buttons[1].setText(choice2Strings[indexTimes]);
        buttons[2].setText(choice3Strings[indexTimes]);
        buttons[3].setText(choice4Strings[indexTimes]);

    }   // showView

}   // Main Class
