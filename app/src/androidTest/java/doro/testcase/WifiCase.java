package doro.testcase;

import junit.framework.Assert;

import org.junit.Test;

import ckt.base.VP4;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static doro.page.WifiPage.WIFI_ONANDOFFID;

/**
 * Created by admin on 2017/1/1.
 */

public class WifiCase extends VP4{
    @Test
    public void OpenWifi(){//从菜单进入wifi
        openAppliction("Settings");
        clickByText("Wi‑Fi");
        Assert.assertTrue("成从菜单进入wifi界面",getObjectById(WIFI_ONANDOFFID).exists());
    }
    @Test


}
