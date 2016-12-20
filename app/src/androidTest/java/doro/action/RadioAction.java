package doro.action;

import org.junit.Assert;

import ckt.base.VP4;
import static doro.page.RadioPage.*;


/**
 * Created by admin on 2016/12/20.
 */

public class RadioAction extends VP4 {
    public void openRadio() {
        openAppliction(Radio_Text);
    }
    public void checkRadioWithoutHeadsetResult() {
        try{
            Assert.assertEquals("Case1_enterRadioWithoutHeadset",Radio_HeadsetNote_Text,getObjectById(Radio_HeadsetNote_ID).getText());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
