package doro.action;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;

import org.junit.Assert;

import ckt.base.VP4;

import static android.R.attr.id;
import static android.os.Build.VERSION_CODES.N;
import static android.support.test.espresso.web.webdriver.DriverAtoms.getText;
import static doro.page.CalculatorPage.CALCULATOR_BUTTON_CLASS;
import static doro.page.CalculatorPage.CALCULATOR_DELETEBUTTON_ID;
import static doro.page.CalculatorPage.CALCULATOR_DIVISBUTTON_ID;
import static doro.page.CalculatorPage.CALCULATOR_DOTBUTTON_ID;
import static doro.page.CalculatorPage.CALCULATOR_EAQUESBUTTON_ID;
import static doro.page.CalculatorPage.CALCULATOR_IPUTFIELD_ID;
import static doro.page.CalculatorPage.CALCULATOR_MINUSBUTTON_ID;
import static doro.page.CalculatorPage.CALCULATOR_MULTIBUTTON_ID;
import static doro.page.CalculatorPage.CALCULATOR_PLUSBUTTON_ID;

/**
 * Created by admin on 2016/12/8.
 */

public class CalculatorAction extends VP4 {
    public void ClearCalculatorNumber() {//清空计算器
        try {
            getObjectById(CALCULATOR_DELETEBUTTON_ID).longClick();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void InputCalculatorNumber(double CalculatorNumber){//输入数字
        initDoro();
        String Number=CalculatorNumber+"";//将数字转换成字符串
        int j=Number.length();//获取字符串长度
        for (int i=0;i<j;i++) {
            String Number1 = Number.charAt(i) + "";
            UiObject Number1Button = mDevice.findObject(new UiSelector().className(CALCULATOR_BUTTON_CLASS).text(Number1));
            try{
            if(Number1Button.equals(".")){   //判断是否输入小数点
                getObjectById(CALCULATOR_DOTBUTTON_ID);
                }
            else{
                Number1Button.click();
                }
              }catch (Exception e){
                e.printStackTrace();

            }
        }
    }

    public void ClickOperatorButton(String ButtonName) {//选择要输入的运算符
        try{
            if (ButtonName.equals("+")) {
                getObjectById(CALCULATOR_PLUSBUTTON_ID).click();
            }
            if (ButtonName.equals("-")) {
                getObjectById(CALCULATOR_MINUSBUTTON_ID).click();
            }
            if(ButtonName.equals("X")) {
                getObjectById(CALCULATOR_MULTIBUTTON_ID).click();
            }
            if (ButtonName.equals("/")) {
                getObjectById(CALCULATOR_DIVISBUTTON_ID).click();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

     public void InputEquesSign() {//输入等号计算结果
         try {
             getObjectById(CALCULATOR_EAQUESBUTTON_ID) .click();
         }catch(Exception e){
             e.printStackTrace();
         }
     }
    public void CalculatorResult(double numberA,double numberB,String operator){  //运算器
        try {
            CalculatorAction  newCalculator =new CalculatorAction();{
                Thread.sleep(2000);
                newCalculator.ClearCalculatorNumber();//清空计算器
                newCalculator.InputCalculatorNumber(numberA);//输入第一个数字
                newCalculator.ClickOperatorButton(operator);//输入运算符
                newCalculator.InputCalculatorNumber(numberB);//输入第二个数字
                newCalculator.InputEquesSign();//输入等号计算结果
                String Result=getObjectById(CALCULATOR_IPUTFIELD_ID).getText();//获取实际运算结果
                double ActualResult=Double.parseDouble(Result);//将实际运算结果转换为double类型
                if (operator.equals("+"))
                    Assert.assertEquals(numberA+numberB,ActualResult,0.11);//做加法运动时，断言函数
                if (operator.equals("-"))
                    Assert.assertEquals(numberA-numberB,ActualResult,0.11);//做减法运动时，断言函数
                if (operator.equals("X"))
                    Assert.assertEquals(numberA*numberB,ActualResult,0.11);//做乘法运动时，断言函数
                if (operator.equals("/"))
                    Assert.assertEquals(numberA/numberB,ActualResult,0.11);//做除法运动时，断言函数

            }
        }catch(Exception e){
            e.printStackTrace();

        }
    }
}
