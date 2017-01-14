package appewtc.masterung.baringame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Mode extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private ImageView[] imageViews = new ImageView[4];
    private int[] ints = new int[]{R.id.imageButton, R.id.imageButton2,
            R.id.imageButton3, R.id.imageButton4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        //Bind Widget and Image Controller
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = (ImageView) findViewById(ints[i]);
            imageViews[i].setOnClickListener(Mode.this);
        }   // for

    }   // Main Method

    @Override
    public void onClick(View view) {

        int i = 0;
        switch (view.getId()) {
            case R.id.imageButton:
                i = 0;
                break;
            case R.id.imageButton2:
                i = 1;
                break;
            case R.id.imageButton3:
                i = 2;
                break;
            case R.id.imageButton4:
                i = 3;
                break;
        }   // switch
        Intent intent = new Intent(Mode.this, PlayGame.class);
        intent.putExtra("Index", i);
        startActivity(intent);
    }   // onClick

}   // Main Class
