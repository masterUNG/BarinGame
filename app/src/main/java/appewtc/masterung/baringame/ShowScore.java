package appewtc.masterung.baringame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowScore extends AppCompatActivity {

    private int scoreAnInt;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);

        scoreAnInt = getIntent().getIntExtra("Score", 0);
        textView = (TextView) findViewById(R.id.textView4);
        textView.setText(Integer.toString(scoreAnInt) + " คะแนน");


    }   // Main Method
}   // Main Class
