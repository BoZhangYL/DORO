package doro.testcase;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import doro.action.PlayStoreAction;


/**
 * Created by Tao.Wang on 2017/2/8.
 */

@RunWith(AndroidJUnit4.class)
public class PlayStoreCase extends PlayStoreAction{
    @Before
    public void initPlayStore() {
        unLock();
        connectWifi("CKT","ck88888!");
    }

    @Test
    public void testCreateGmailAccount(){


    }

    @Test
    public void testSignInGmailAccount(){

    }

    @Test
    public void testDownloadAndInstall(){

    }
/**
 * 由于国内网络连接PlayStore经常出现验证不通过的情况
 * 如下case doro不适用
 1.创建账号
 2.登录账号
 3.下载安装应用
 */

}
