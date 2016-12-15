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
    @Test
    public void setCalculatorto30s()throws RemoteException{
        //unLock();
        openAppliction("Cal\u200Bcu\u200Blator");//打开计算器
        new CalculatorAction().CalculatorResult(0.0078,45,"/");
        try {
            double A=0.0078/45;
            String C=getObjectById(CALCULATOR_IPUTFIELD_ID).getText();
            Double B=Double.parseDouble(C);
            Assert.assertEquals(A,B,0.11);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



