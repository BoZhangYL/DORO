package doro.action;

import java.io.IOException;

import ckt.base.VP4;
import doro.page.APPMenuPage;
import doro.page.MainActivityPage;

/**
 * Created by Caibing.Yin on 2017/1/23.
 */

public class MainActivityAction extends VP4 {
    public static void CreateContact(String name,String phoneNumber) throws IOException {
        gDevice.pressHome();
        clickById(APPMenuPage.More);
        clickByText("Add");
        clickByText("A contact");
        getObject2ById(MainActivityPage.NAME_INPUT).setText(name);
        clickByText("Next");
        shellInputText(phoneNumber);
        clickByText("Next");
        shellInputText("No");
        clickByText("Next");
        getObject2ById(MainActivityPage.STREET_INPUT).setText("No");
        getObject2ById(MainActivityPage.STREET_NOTE).setText("No");
        clickByText("Next");
        clickByText("Save");
        gDevice.pressHome();
    }
}
