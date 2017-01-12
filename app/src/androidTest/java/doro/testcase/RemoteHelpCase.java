package doro.testcase;

import junit.framework.Assert;

import org.junit.Test;

import ckt.base.VP4;

import static doro.page.RemoteHelpPage.RemoteHelpTitle;

/**
 * Created by admin on 2017/1/12.
 */

public class RemoteHelpCase extends VP4{
    @Test
    public void EnterRemoteHelp(){//进入remoteHelp
        switchToMenuPage();
        clickByText("Set");
        clickByText("An assistance option");
        clickByText("Remote help");
        Assert.assertTrue("未成功进入Remote Help",getObjectByText(RemoteHelpTitle).exists());
    }
    @Test
    public void ExitRemoteHelp1(){//通过back键退出remote help
        switchToMenuPage();
        clickByText("Set");
        clickByText("An assistance option");
        clickByText("Remote help");
        waitTime(5);
        mDevice.pressBack();
        Assert.assertFalse("成功退出Remote Help",getObjectByText(RemoteHelpTitle).exists());
    }
    @Test
    public void ExitRemoteHelp2(){//通过home键退出remote help
        switchToMenuPage();
        clickByText("Set");
        clickByText("An assistance option");
        clickByText("Remote help");
        waitTime(5);
        mDevice.pressHome();
        Assert.assertFalse("成功退出Remote Help",getObjectByText(RemoteHelpTitle).exists());
    }
}
