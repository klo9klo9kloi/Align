package edu.tjhsst.align;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean[][] buttons;
    int x;
    int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get punchsheet linear layout
        //for loop of 4
        //for loop of 7
        //add buttons
        //text should say the date
        //http://stackoverflow.com/questions/5270272/how-to-determine-day-of-week-by-passing-specific-date\
        LinearLayout punchsheet = (LinearLayout) findViewById(R.id.punchsheet);
        buttons = new boolean[4][7];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(120, 120);
        lp.setMargins(20, 20, 20, 20);
        for(int i = 0; i < 4; i++){
            LinearLayout week = new LinearLayout(this);
            week.setOrientation(LinearLayout.HORIZONTAL);
            Log.d("alyssa", "adding week " + i);
            for(int j = 0; j < 7; j++){
                Button b = new Button(this);
                b.setBackgroundResource(R.drawable.circle);
                b.setLayoutParams(lp);
                b.setId(i * 10 + j);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("alyssa", "ya clicked me ");
                        Button b = (Button) v;
                        int id = b.getId();
                        if (!buttons[id / 10][id % 10]) {
                            b.setBackgroundResource(R.drawable.greencircle);
                            buttons[id/10][id%10] = true;
                        } else {
                            b.setBackgroundResource(R.drawable.circle);
                            buttons[id/10][id%10] = false;
                        }
                        //buttons[i][j].setBackgroundColor(Color.parseColor("FF66AA"));
                    }
                });
                week.addView(b);
                buttons[i][j] = false;
            }
            punchsheet.addView(week);
            Log.d("alyssa", "added week " + i);
        }
        Log.d("alyssa", "finished adding stuff");

    }


    public void punch(View view){
        Log.d("alyssa", "hello");
    }

}
