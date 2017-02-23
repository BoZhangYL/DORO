package doro.testcase;

import android.os.RemoteException;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import ckt.image.ImageLib;
import com.squareup.spoon.Spoon;

import org.hamcrest.Asst;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;

import ckt.base.VP4;
import doro.action.APPMenuAction;
import doro.action.EmailAction;
import doro.action.MainAction;
import doro.page.APPMenuPage;
import doro.page.ConstantPage;

/**
 * Created by Caibing.Yin  on 2017/1/12.
 */
@RunWith(AndroidJUnit4.class)
public class APPMenuCase extends VP4{
    Hashtable<String,String> apps;
    @BeforeClass
    public static void initGalleryCase() throws IOException, UiObjectNotFoundException {
        unLock();
        APPMenuAction.initAppPermissions();
     //   EmailAction.LogInEmail("woshihouzi2016@gmail.com","woshidoubi","");
    }

    @Test
    public void testClickAlarm() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[0]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(0));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[0],!Actual1&&Actual2);
    }
    @Test
    public void testClickCal​cu​lator() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[1]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(1));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[1],!Actual1&&Actual2);
    }
    @Test
    public void testClickCalen​dar() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[2]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(2));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[2],!Actual1&&Actual2);
    }
    @Test
    public void testClickCamera() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[3]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(3));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[3],!Actual1&&Actual2);
    }
    @Test
    public void testClickChrome() throws UiObjectNotFoundException, IOException {
        MainAction.clearAllApp();
//        EmailAction.RegisterChromeAccount("woshihouzi2016@gmail.com","woshidoubi");
        MainAction.startApp(APPMenuPage.AppNameList[4]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(4));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[4],!Actual1&&Actual2);
    }
    @Test
    public void testClickCodeSafe() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[5]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(5));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[5],!Actual1&&Actual2);
    }
    @Test
    public void testClickCont​acts() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[6]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(6));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[6],!Actual1&&Actual2);
    }
    @Test
    public void testClickDockMode() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[7]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(7));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[7],!Actual1&&Actual2);
    }
    @Test
    public void testClickDoroConnectCare() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[8]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(8));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[8],!Actual1&&Actual2);
    }
    @Test
    public void testClickDoroKeyboard() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[9]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(9));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[9],!Actual1&&Actual2);
    }
    @Test
    public void testClickDoroSel​ection() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[10]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(10));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[10],!Actual1&&Actual2);
    }
    @Test
    public void testClickDownloads() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[11]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(11));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[11],!Actual1&&Actual2);
    }
    @Test
    public void testClickDrive() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[12]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(12));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[12],!Actual1&&Actual2);
    }
    @Test
    public void testClickDuo() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[13]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(13));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[13],!Actual1&&Actual2);
    }
    @Test
    public void testClickEmail() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[14]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(14));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[14],!Actual1&&Actual2);
    }
    @Test
    public void testClickEmail1() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[15]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(15));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[15],!Actual1&&Actual2);
    }
    @Test
    public void testClickFileManager() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[16]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(16));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[16],!Actual1&&Actual2);
    }
    @Test
    public void testClickFMRadio() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[17]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(17));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[17],!Actual1&&Actual2);
    }
    @Test
    public void testClickGall​ery() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[18]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(18));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[18],!Actual1&&Actual2);
    }
    @Test
    public void testClickGmail() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[19]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(19));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[19],!Actual1&&Actual2);
    }
    @Test
    public void testClickGoogle() throws UiObjectNotFoundException, IOException {
        MainAction.clearAllApp();
     //   EmailAction.RegisterGoogleAccount("woshihouzi2016@gmail.com", "woshidoubi");
        MainAction.startApp(APPMenuPage.AppNameList[20]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(20));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[20],!Actual1&&Actual2);
    }
    @Test
    public void testClickInter​net() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[21]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(21));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[21],!Actual1&&Actual2);
    }
    @Test
    public void testClickMag​nifier() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[22]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(22));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[22],!Actual1&&Actual2);
    }
    @Test
    public void testClickMaps() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[23]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(23));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[23],!Actual1&&Actual2);
    }
    @Test
    public void testClickMess​ages() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[24]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(24));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[24],!Actual1&&Actual2);
    }
    @Test
    public void testClickMusic() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[25]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(25));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[14],!Actual1&&Actual2);
    }
    @Test
    public void testClickMyDoroManager() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[26]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(26));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[26],!Actual1&&Actual2);
    }
    @Test
    public void testClickNotes() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[27]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(27));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[27],!Actual1&&Actual2);
    }
    @Test
    public void testClickPhone() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[28]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(28));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[28],!Actual1&&Actual2);
    }
    @Test
    public void testClickPhotos() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[29]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(29));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[29],!Actual1&&Actual2);
    }

    /*
    * 新版本已经删除PlayMoview&TV
    * */
/*    @Test
    public void testClickPlayMoviesTV() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[30]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(30));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[30],!Actual1&&Actual2);
    }*/
    @Test
    public void testClickPlayMusic() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[31]);
        waitTime(5);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(31));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[31],!Actual1&&Actual2);
    }
    @Test
    public void testClickPlayStore() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[32]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(32));
        logger.info(gDevice.getCurrentPackageName());
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[32],!Actual1&&Actual2);
    }
    @Test
    public void testClickQuickSupport() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[33]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(33));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[33],!Actual1&&Actual2);
    }
    @Test
    public void testClickRec​order() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[34]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(34));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[34],!Actual1&&Actual2);
    }
    @Test
    public void testClickSettings() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[35]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(35));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[35],!Actual1&&Actual2);
    }
    @Test
    public void testClickSIMToolKit() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[36]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(36));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[36],!Actual1&&Actual2);
    }
    @Test
    public void testClickTimer() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[37]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(37));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[37],!Actual1&&Actual2);
    }
    @Test
    public void testClickTorch() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[38]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(38));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[38],!Actual1&&Actual2);
    }
    @Test
    public void testClickVoiceSearch() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[39]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(39));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[39],!Actual1&&Actual2);
    }
    @Test
    public void testClickWeath​er() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[40]);
        if(getObjectById("com.google.android.gms:id/message").exists()){
            clickByText("CANCEL");
            waitTime(1);
            clickByText("No");
        }
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(40));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[40],!Actual1&&Actual2);
    }
    @Test
    public void testClickYouTube() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        MainAction.startApp(APPMenuPage.AppNameList[41]);
        boolean Actual1 = gDevice.getCurrentPackageName().equals(APPMenuAction.getPkgName(41));
        gDevice.pressHome();
        boolean Actual2 =text_exists("Call");
        Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[41],!Actual1&&Actual2);
    }
    @Test
    public void testReEnterApp() throws UiObjectNotFoundException, RemoteException {
        //挨个启动APPMenuPage中的程序
        int errorAppCount=0;
        StringBuffer errorAppString=new StringBuffer();
        for (int i =0;i<APPMenuPage.AppNameList.length;i++) {
            logger.info("第。。。。。。。。。。" +i+"次循环" );
            String appToBeLaunched=APPMenuPage.AppNameList[i];
            MainAction.startApp(appToBeLaunched);//启动第一个应用
            waitTime(8);
            String Before = ImageLib.TakeScreen("");//第一个应用退出前截图
            waitTime(2);
            MainAction.startApp(APPMenuPage.AppNameList[i+1]);//启动下一个应用
            waitTime(2);
            gDevice.pressRecentApps();//调出多任务界面
            waitTime(2);
            clickByText(APPMenuPage.AppNameList[i]);//再次重新进入第一个应用
            waitTime(2);
            String After = ImageLib.TakeScreen("");//第一个应用再次进入后的截图
            if(!ImageLib.sameAs(Before,After,0.6)){
                logger.info("前后图片对比不相同，再次进入时不一致-"+appToBeLaunched);
                errorAppCount=errorAppCount+1;
                Spoon.screenshot("SuchAPPNotTheSameAsBefore");
                errorAppString.append(String.format(" Activity of%s  ComparedFailed\n",appToBeLaunched));
            }
            if(i>2){
                MainAction.killAppByPackage(APPMenuPage.AppNameList[i-3]);//结束上上个应用
            }
        }
        if (errorAppCount>=1){
            Assert.fail(errorAppString.toString());
        }
    }

}
