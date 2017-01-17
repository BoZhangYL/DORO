package doro.action;

import android.os.Environment;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;

import org.hamcrest.Asst;
import org.junit.Assert;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import ckt.base.VP4;

import static doro.page.DownloadsPage.*;

/**
 * Created by admin on 2017/1/16.
 */

public class DownloadsAction extends VP4 {
    public void sortBy(String Method){
        try {
            clickById(DOWNLOADS_SORTMENU_ID);
            getObjectByText(Method).clickAndWaitForNewWindow();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void checkSoryBy(String Method){
        try {
            UiCollection Collection = new UiCollection(new UiSelector().resourceId(DOWNLOADS_APPS_ID).className(DOWNLOADS_APPS_CLASS));
            int Appcounts = Collection.getChildCount();
            System.out.println(Appcounts);
            switch (Method) {
                case DOWNLOADS_SORTBY_NAME:
                    UiSelector childPattern = new UiSelector().resourceId(DOWNLOADS_APPS_NAME);
                    String APPNAME1=Collection.getChildByInstance(childPattern,0).getText();
                    System.out.println(APPNAME1);
                    if (Appcounts > 4) {
                        for (int i=0;i<=3;i++) {
                            String APPNAMEi = Collection.getChildByInstance(childPattern,i).getText();
                            System.out.println(APPNAMEi);
                            Assert.assertTrue("APPS没有按名字排序!", APPNAME1.compareToIgnoreCase(APPNAMEi) <= 0);
                        }
                    }else {
                        for (int i =0;i<= Appcounts;i++) {
                            String APPNAMEi = Collection.getChildByInstance(childPattern, i).getText();
                            System.out.println(APPNAMEi);
                            Assert.assertTrue("APPS没有按名字排序!", APPNAME1.compareToIgnoreCase(APPNAMEi) <= 0);
                        }
                    }
                    break;
                case DOWNLOADS_SORTBY_DATEMODIFIED:
                    UiSelector childPattern1= new UiSelector().resourceId(DOWNLOADS_APPS_DATEMODIFIED);
                    String APPDATE1=Collection.getChildByInstance(childPattern1,0).getText();
                    System.out.println(APPDATE1);
                    if (Appcounts>4) {
                        for (int i=0;i<=3;i++) {
                            String APPDATEi = Collection.getChildByInstance(childPattern1,i).getText();
                            System.out.println(APPDATEi);
                            Assert.assertTrue("APPS没有按名字排序!", APPDATE1.compareToIgnoreCase(APPDATEi) <= 0);
                        }
                    }else {
                        for (int i=0;i<= Appcounts;i++) {
                            String APPDATEi = Collection.getChildByInstance(childPattern1, i).getText();
                            System.out.println(APPDATEi);
                            Assert.assertTrue("APPS没有按名字排序!", APPDATE1.compareToIgnoreCase(APPDATEi) <= 0);
                        }
                    }
                    break;
                case DOWNLOADS_SORTBY_SIZE:

                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }






































}
