package doro.testcase;

import org.junit.Assert;
import org.junit.Test;

import ckt.base.VP4;

/**
 * Created by admin on 2017/1/11.
 */

public class DiscoverCase extends VP4{
    @Test
    public void OpenDiscover(){//进入探索程序
        switchToMenuPage();
        clickByText("Discover");
        Assert.assertTrue("没有成功进入Discover",getObjectByText("What?").exists());
    }
    @Test
    public void ExitDiscover() {//退出探索程序
        switchToMenuPage();
        clickByText("Discover");
        mDevice.pressHome();
        Assert.assertFalse("成功退出Discover", getObjectByText("What?").exists());
    }
    @Test
    public void BackDiscover() {//按返回键退出探索程序
        switchToMenuPage();
        clickByText("Discover");
        mDevice.pressBack();
        Assert.assertFalse("成功退出Discover", getObjectByText("What?").exists());

    }
}
