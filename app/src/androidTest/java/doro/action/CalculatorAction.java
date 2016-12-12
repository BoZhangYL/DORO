package doro.action;

import android.os.RemoteException;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.view.KeyEvent;
import android.view.SubMenu;

import ckt.base.VP4;

import static doro.page.CalculatorPage.CALCULATOR_BUTTON_CLASS;
import static doro.page.CalculatorPage.CALCULATOR_DELETEBUTTON_ID;
import static doro.page.CalculatorPage.CALCULATOR_DIVISBUTTON_ID;
import static doro.page.CalculatorPage.CALCULATOR_EAQUESBUTTON_ID;
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

    public void InputCalculatorNumber(int CalculatorNumber){//输入数字
        initDoro();
        String Number=CalculatorNumber+"";
        int i;
        int j=Number.length();
        for (i=0;i<j;i++) {
            String Number1 = Number.charAt(i) + "";
            UiObject Number1Button = mDevice.findObject(new UiSelector().className(CALCULATOR_BUTTON_CLASS).text(Number1));
            try{
                Number1Button.click();
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

        public void InputEquesSign() {//清空计算器
            try {
                getObjectById(CALCULATOR_EAQUESBUTTON_ID) .click();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
}