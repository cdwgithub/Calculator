package com.jkxy.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int[] numberID={R.id.btnZero,R.id.btnOne,R.id.btnTwo,R.id.btnThree,R.id.btnFourth,
                              R.id.btnFive,R.id.btnSix,R.id.btnSeven,R.id.btnEight,R.id.btnNine };
    private int[] signID={R.id.btnPlus,R.id.btnSub,R.id.btnMult,R.id.btnDiv};
    private Button[] btnNumber=new Button[numberID.length];//数字按钮
    private Button[] btnSign=new Button[signID.length];//运算按钮
    private Button btnEqual,btnClear;
    private TextView tvDisplay;

    private static String fistNumber = "0";// 第一次输入的值
    private static String secondNumber = "0";// 第二次输入的值
    private static String result= "0";// 显示的结果
    private static int flag = 0;// 结果累加一次
    private Counts counts=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 初始化
     */
    private void init(){
        tvDisplay=(TextView)findViewById(R.id.tvDisplay);
        //数字按钮初始化
        for (int i=0;i<numberID.length;i++){
            btnNumber[i]=(Button)findViewById(numberID[i]);
            btnNumber[i].setOnClickListener(getNumber);
        }
        //运算按钮初始化
        for (int i=0;i<signID.length;i++){
            btnSign[i]=(Button)findViewById(signID[i]);
            btnSign[i].setOnClickListener(calculate);
        }

        btnEqual=(Button)findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==0){
                    secondNumber = tvDisplay.getText().toString();
                    if (counts == Counts.DIV && secondNumber.equals("0")) {
                        Toast.makeText(MainActivity.this, "0不能为被除数", Toast.LENGTH_SHORT).show();
                    }
                    else if (counts != null) {
                        result = counts.Values(fistNumber, secondNumber);
                        fistNumber = result;
                        secondNumber = "0";
                        tvDisplay.setText(result);
                        flag=1;
                    }
                }
            }
        });
        //清除
        btnClear=(Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = "0";
                fistNumber = secondNumber = result;
                tvDisplay.setText(result);
                flag=0;
            }
        });

    }

    /**
     * 获取输入的数字
     */
    private View.OnClickListener getNumber =new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if (flag==1){
                result="0";
            }
            if (result.equals("0")){
                tvDisplay.setText("");
                result="";
            }
            result +=((Button)view).getText().toString();
            tvDisplay.setText(result);
        }
    };
    /**
     * 计算
     */
    private View.OnClickListener calculate=new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            fistNumber=tvDisplay.getText().toString();
            switch (view.getId()){
                case R.id.btnPlus:
                    counts=Counts.PLUS;//加
                    break;
                case R.id.btnSub:
                    counts=Counts.SUB;//减
                    break;
                case R.id.btnMult:
                    counts=Counts.MUL;//乘
                    break;
                case R.id.btnDiv:
                    counts=Counts.DIV;//除
                    break;
                default:
                    break;
            }
            result="0";
            flag=0;

        }
    };
}
