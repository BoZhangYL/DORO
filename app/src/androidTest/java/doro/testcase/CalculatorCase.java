package doro.testcase;

import android.os.RemoteException;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import ckt.base.VP4;
import doro.action.CalculatorAction;

import static android.R.attr.id;
import static android.support.test.espresso.web.webdriver.DriverAtoms.getText;
import static doro.page.CalculatorPage.CALCULATOR_DELETEBUTTON_ID;
import static doro.page.CalculatorPage.CALCULATOR_EAQUESBUTTON_ID;
import static doro.page.CalculatorPage.CALCULATOR_IPUTFIELD_ID;

/**
 * Created by admin on 2016/12/9.
 */
public class CalculatorCase extends VP4{
    CalculatorAction calculatorAction = new CalculatorAction();
    @Test
    public void setCalculatortor()throws RemoteException{
        //unLock();
       openAppliction("Cal\u200Bcu\u200Blator");//打开计算器
        calculatorAction.CalculatorResult(0.0078,45,"/");//进行一次运算
    }
}



