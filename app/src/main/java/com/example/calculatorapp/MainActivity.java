package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv,solutionTV;
    MaterialButton buttonC,buttonOpen,buttonClose,buttonDiv,buttonMul,buttonPlus,buttonMinus,buttonEquals,button1,button2,button3,button4,button5,button6,button7,button8,button9,button0,buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        assignID(button0,R.id.button_0);
        assignID(button1,R.id.button_1);
        assignID(button2,R.id.button_2);
        assignID(button3,R.id.button_3);
        assignID(button4,R.id.button_4);
        assignID(button5,R.id.button_5);
        assignID(button6,R.id.button_6);
        assignID(button7,R.id.button_7);
        assignID(button8,R.id.button_8);
        assignID(button9,R.id.button_9);
        assignID(buttonC,R.id.button_c);
        assignID(buttonOpen,R.id.button_open_bracket);
        assignID(buttonClose,R.id.button_close_bracket);
        assignID(buttonDiv,R.id.button_divide);
        assignID(buttonMul,R.id.button_multiply);
        assignID(buttonPlus,R.id.button_add);
        assignID(buttonMinus,R.id.button_minus);
        assignID(buttonEquals,R.id.button_equal);
        assignID(buttonAC,R.id.button_ac);
        assignID(buttonDot,R.id.button_ddot);
        resultTv= (TextView) findViewById(R.id.resultTV);
        solutionTV= (TextView) findViewById(R.id.solutionTV);
    }

    void assignID(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button=(MaterialButton) v;
        String buttonText=button.getText().toString();
        String dataToCalculate=solutionTV.getText().toString();

        if(buttonText.equals("AC")){
            solutionTV.setText("");
            resultTv.setText("");
            return;
        }

        if (buttonText.equals("=")){
            solutionTV.setText(resultTv.getText());
            return;
        }

        if (buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else {
            dataToCalculate=dataToCalculate+buttonText;
        }
        solutionTV.setText(dataToCalculate);

        String result=getResult(dataToCalculate);

        if (!result.equals("Error!")){
            resultTv.setText(result);
        }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initSafeStandardObjects();
            String result=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (result.endsWith(".0")){
                result=result.replace(".0","");
            }
            return result;
        }catch (Exception e){
            return "Error!";
        }
    }
}