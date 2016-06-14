package edu.tjhsst.align;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Calendar;

//http://bangalorebanerjee.blogspot.com/2014/03/how-to-swipe-between-layouts-in-android.html
//store the calendar booleans


public class MainActivity extends AppCompatActivity {
    boolean[][] buttons;
    boolean[][][] wholeYear = new boolean[12][6][7];
    String[] months = new String[]{"january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};
    int currentMonth;
    int x;
    int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Calendar c = Calendar.getInstance();
        setContentView(R.layout.activity_main);
        LinearLayout sv = (LinearLayout) findViewById(R.id.scroll);
        LinearLayout ps = displayCalendar(c, true, new boolean[6][7], 0);
        sv.addView(ps);

        int currentMonth = c.get(Calendar.MONTH) + 1;
        for(int i = 0; i < 6; i++){
            currentMonth += 1;
            if(currentMonth > 11)
                c.set(Calendar.YEAR, c.get(Calendar.YEAR)+1);
            i = i % 12;
            c.set(Calendar.MONTH, currentMonth);
            c.set(Calendar.DAY_OF_MONTH, 1);
            ps = displayCalendar(c, false, new boolean[6][7], i+1);
            sv.addView(ps);
        }
    }

    private LinearLayout displayCalendar(Calendar c, boolean current, boolean[][] bu, int index){
        buttons = bu;
        currentMonth = 0;
        int day = c.get(Calendar.DATE) ;
        int month = c.get(Calendar.MONTH);
        c.set(Calendar.DAY_OF_MONTH, 0);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        //TextView monthname = (TextView) findViewById(R.id.monthname);
        TextView monthname = new TextView(this);
        monthname.setText(months[month] + " "  + c.get(Calendar.YEAR));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(1180, LinearLayout.LayoutParams.WRAP_CONTENT);
        monthname.setLayoutParams(lp);
        monthname.setTextSize(30);
        int maxDay = c.get(Calendar.DATE) ;
        // LinearLayout punchsheet = (LinearLayout) findViewById(R.id.punchsheet);
        LinearLayout punchsheet = new LinearLayout(this);
        lp.setMargins(40, 40, 40, 40);
        punchsheet.setLayoutParams(lp);
        punchsheet.setOrientation(LinearLayout.VERTICAL);
        punchsheet.setPadding(20, 20, 20, 20);
        punchsheet.setBackgroundColor(0xff2299dd);
        punchsheet.addView(monthname);
        lp = new LinearLayout.LayoutParams(120, 120);
        lp.setMargins(20, 20, 20, 20);
        String[] days = new String[]{"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
        LinearLayout weeklabels = new LinearLayout(this);
        for(int d = 0; d < 7; d++){
            TextView tv = new TextView(this);
            tv.setText(days[d]);
            tv.setTextSize(20);
            tv.setLayoutParams(lp);
            weeklabels.addView(tv);
        }
        punchsheet.addView(weeklabels);
        int currentdate = 0 - dayOfWeek;
        for(int i = 0; i < 6; i++){
            LinearLayout week = new LinearLayout(this);
            week.setOrientation(LinearLayout.HORIZONTAL);
            for(int j = 0; j < 7; j++) {
                Button b = new Button(this);
                currentdate += 1 ;
                b.setBackgroundResource(R.drawable.circle);
                b.getBackground().setAlpha(0);
                b.setTextSize(10);
                b.setText(currentdate + "");
                b.setLayoutParams(lp);
                b.setId(i * 10 + j);
                b.setPadding(0, 0, 0, 0);
                if(currentdate > day && currentdate <= maxDay) {
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Button b = (Button) v;
                            int id = b.getId();
                            if (!buttons[id / 10][id % 10]) {
                                b.getBackground().setAlpha(250);
                                buttons[id / 10][id % 10] = true;
                            } else {
                                b.getBackground().setAlpha(0);
                                buttons[id / 10][id % 10] = false;
                            }
                            //buttons[i][j].setBackgroundColor(Color.parseColor("FF66AA"));
                        }
                    });
                }
                else if(currentdate < day && currentdate > 0){ //add when date is past number of days in the month
                    b.getBackground().setAlpha(50);
                }
                else if(currentdate == day && current) {
                    b.setBackgroundResource(R.drawable.darkcircle);
                }
                else{
                    b.setBackgroundResource(R.drawable.darkcircle);
                    b.setText("");
                    b.getBackground().setAlpha(10);
                }
                week.addView(b);
                buttons[i][j] = false;
            }
            punchsheet.addView(week);
            wholeYear[index] = buttons;
        }
        return punchsheet;
    }



}
