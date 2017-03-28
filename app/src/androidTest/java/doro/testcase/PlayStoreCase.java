package doro.testcase;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import doro.action.PlayStoreAction;

import static doro.page.PlayStorePage.*;


/**
 * Created by Tao.Wang on 2017/2/8.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlayStoreCase extends PlayStoreAction{
   /* @BeforeClass
    public static void initPlayStore() {
        unLock();
        connectWifi("CK-Test-CTS","test7878");
    }*/

    @Test
    public void Case1_testSignInGmailAccount(){
        unLock();
        connectWifi("CK-Test-CTS","test7878");
        openAppliction(PLAYSTORE_NAME);
        signInGmailAccount("buteo753753@gmail.com","19920610");
        Assert.assertTrue("Gmail账号登陆失败！",getObjectById(PLAYSTORE_SEARCHBOX_ID).exists());
    }

    @Test
    public void Case2_testDownloadAndInstallApplication(){
        openAppliction(PLAYSTORE_NAME);
        downloadAndInstallApp("IMDb");
        checkInstallApp();
    }



/**
 * 由于注册Gmail账号需要验证码
 * 如下case doro不适用
 1.创建账号
 */

}
