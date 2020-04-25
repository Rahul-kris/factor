package com.example.factor;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    LinearLayout currentLayout;
    EditText number;
    TextView outputText;
    Button button1, button2, button3, click;
    String bs1, bs2, bs3;
    int rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentLayout = (LinearLayout) findViewById(R.id.ParentView);
        number = (EditText) findViewById(R.id.numNum);
        click = (Button) findViewById(R.id.click);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        outputText = (TextView) findViewById(R.id.outputText);

    }
    public static int getRandom(int min, int max){
        Random random = new Random();
        return random.nextInt((max-min)+1)+min;
    }

    public static long getFactor(long p){
        long f; int count = 0;
        List<Long> factors = new ArrayList<>();
        for(f = 1;f <= Math.sqrt(p);f++){
            if(p % f == 0){
                count++;
                factors.add(f);
            }
        }
        int c; long sf;
        int root = (int)Math.sqrt(p);
        if(p == root*root){
            c = count-2;
        }
        else{
            c = count-1;
        }
        for(int j = c;j >= 0;j--){
            count++;
            sf = factors.get(c);
            factors.add(p/sf);
        }
        count++;
        Random random = new Random();
        int i = random.nextInt(count);
        return factors.get(i);
    }

    public void getNum(View view){

        click.setEnabled(false);
        rand = getRandom(0,9);
        String numStr = number.getText().toString();

        if(numStr.equals("")){
            outputText.setText("No input entered");
            number.setText("");
            number.requestFocus();
        }
        else {
            long num = 0;
            if (TextUtils.isDigitsOnly(numStr)) {
                num = Long.parseLong(numStr);
            }
            if(num==0){
                outputText.setText("Illogical input");
                number.setText("");
                number.requestFocus();
            }

            else{
                int b1 = getRandom(2,25);
                long b2 = getFactor(num);
                int b3 = getRandom(25,50);

                while (num % b1 == 0) {
                    b1 = getRandom(2,25);
                }

                while (num % b3 == 0) {
                    b3 = getRandom(25,50);
                }

                bs1 = String.valueOf(b1);
                bs2 = String.valueOf(b2);
                bs3 = String.valueOf(b3);

                if (rand < 3) {
                    button1.setText(bs1);
                    button2.setText(bs2);
                    button3.setText(bs3);
                }
                else if (rand < 6) {
                    button1.setText(bs2);
                    button2.setText(bs3);
                    button3.setText(bs1);
                }
                else {
                    button1.setText(bs3);
                    button2.setText(bs1);
                    button3.setText(bs2);
                }
            }
        }
    }

    public void answer(View v){
        Button bt = (Button) v;
        if(bs2.equals("")){
            outputText.setText("No input entered");
            number.setText("");
            number.requestFocus();
        }
        else{
            if(bt.getText()==bs2) {
                outputText.setText("Correct");
                currentLayout.setBackgroundColor(Color.GREEN);
            }
            else {
                outputText.setText("Wrong. The answer is " + bs2);
                currentLayout.setBackgroundColor(Color.RED);
            }

            if(bt == button2){
                button1.setEnabled(false);
                button3.setEnabled(false);
            }
            else if (bt == button1){
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
            else{
                button1.setEnabled(false);
                button2.setEnabled(false);
            }
        }
    }

    public void refresh(View view){
        number.setText("");
        outputText.setText("");
        button1.setText("BUTTON_1");
        button2.setText("BUTTON_2");
        button3.setText("BUTTON_3");
        number.requestFocus();
        bs2 = "";
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        click.setEnabled(true);
        currentLayout.setBackgroundColor(Color.TRANSPARENT);
    }
}
