package doro.testcase;

import org.junit.Assert;
import org.junit.Test;

import ckt.base.VP4;

import static doro.page.CodeSafePage.CODESAFE;
import static doro.page.CodeSafePage.SETSAFECODE;

/**
 * Created by admin on 2017/1/10.
 */

public class CodeSafeCase extends VP4{
    @Test
    public void OpenCodeSafe(){//进入safe code
        openAppliction(CODESAFE);
        Assert.assertTrue("没有成功进入Code Safe",getObjectByText(CODESAFE).exists());
    }
    @Test
    public void ExitCodeSafeByBack() {//BACK键退出safe code
        openAppliction(CODESAFE);
        waitTime(5);
        mDevice.pressBack();
        Assert.assertFalse("成功退出Code Safe",getObjectByText(SETSAFECODE).exists());
    }
    @Test
    public void ExitCodeSafeByHome() {//Home键退出safe code
        openAppliction(CODESAFE);
        waitTime(5);
        mDevice.pressHome();
        Assert.assertFalse("成功退出Code Safe", getObjectByText(SETSAFECODE).exists());
    }
}
