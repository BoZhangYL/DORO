package doro.action;

import android.os.RemoteException;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.view.KeyEvent;
import android.view.SubMenu;

import ckt.base.VP4;

import static android.R.attr.id;
import static doro.page.CalculatorPage.CALCULATOR_BUTTON_CLASS;
import static doro.page.CalculatorPage.CALCULATOR_DELETEBUTTON_ID;
import static doro.page.CalculatorPage.CALCULATOR_DIVISBUTTON_ID;
import static doro.page.CalculatorPage.CALCULATOR_DOTBUTTON_ID;
import static doro.page.CalculatorPage.CALCULATOR_EAQUESBUTTON_ID;
import static doro.page.CalculatorPage.CALCULATOR_MINUSBUTTON_ID;
import static doro.page.CalculatorPage.CALCULATOR_MULTIBUTTON_ID;
import static doro.page.CalculatorPage.CALCULATOR_PLUSBUTTON_ID;
import static java.lang.Double.valueOf;

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
        String Number=CalculatorNumber+"";
        int j=Number.length();
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
                newCalculator.ClearCalculatorNumber();//清空计算器
                newCalculator.InputCalculatorNumber(numberA);//输入第一个数字
                newCalculator.ClickOperatorButton(operator);//输入运算符
                newCalculator.InputCalculatorNumber(numberB);//输入第二个数字
                newCalculator.InputEquesSign();//输入等号计算结果
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}