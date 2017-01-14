package appewtc.masterung.baringame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

public class PlayGame extends AppCompatActivity {

    //Explicit
    private RelativeLayout relativeLayout;
    private int indexAnInt;
    private String tag = "15janV1";
    private int[] backgroundInts = new int[]{R.drawable.bg0, R.drawable.bg1,
            R.drawable.bg2, R.drawable.bg3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        //Bind Widget
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_play_game);

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

        } catch (Exception e) {
            e.printStackTrace();
        }


    }   // Main Method

}   // Main Class
