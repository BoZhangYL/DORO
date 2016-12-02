package doro.action;

import ckt.base.VP2;
import doro.page.SettingPage;

/**
 * Created by elon on 2016/12/1.
 */
public class SettingAction extends VP2{
    public static void openBluetooth(){
        clickById(SettingPage.BLUETOOTH_SWITCH);
    }
    public static void closeBluetooth(){


    }
}
