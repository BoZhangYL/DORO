package doro.testcase;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ckt.base.VP4;
import doro.action.ContactsAction;
import doro.bean.Constant;
import doro.bean.ContactsBean;

/**
 * Created by qiang.zhang on 2017/1/12.
 */
@RunWith(AndroidJUnit4.class)
public class ContactsCase extends VP4{
    @Before
    public void before(){
        ContactsAction.launchContacts();
    }
    @Test
    public void testAddWithNoBirthday() throws Exception {
        ContactsBean bean = new ContactsBean();
        bean.setStar(true); //step1 identity
        bean.setEdit_picture(true);
        bean.setName(getRandomName(3,8));
        //bean.setBirthday("20/11/1948");
        ContactsAction.pushNumber(bean,3);   //step2 numbers
        ContactsAction.pushEmail(bean,3);//step3 email
        bean.setStreet(getRandomName(20,30));//step4 details
        bean.setNote(getRandomName(10,20));
        bean.setRing_tone(ContactsAction.getRandomType(Constant.RingTone));//step5 Tone
        bean.setIs_play_ring(true);
        bean.setMessage_tome(ContactsAction.getRandomType(Constant.MessageTone));
        bean.setIs_play_message(true);
        ContactsAction.addContact(bean);//使用bean添加联系人
        ContactsAction.checkContactsBean(bean);//验证联系人
    }
        @Test
    public void testAddWithNoNumber() throws Exception {
        ContactsBean bean = new ContactsBean();
        bean.setStar(true); //step1 identity
        bean.setEdit_picture(true);
        bean.setName(getRandomName(3,8));
        bean.setBirthday("20/11/1948");
        //ContactsAction.pushNumber(bean,3);   //step2 numbers
        ContactsAction.pushEmail(bean,3);//step3 email
        bean.setStreet(getRandomName(20,30));//step4 details
        bean.setNote(getRandomName(10,20));
        bean.setRing_tone(ContactsAction.getRandomType(Constant.RingTone));//step5 Tone
        bean.setIs_play_ring(true);
        bean.setMessage_tome(ContactsAction.getRandomType(Constant.MessageTone));
        bean.setIs_play_message(true);
        ContactsAction.addContact(bean);//使用bean添加联系人
        ContactsAction.checkContactsBean(bean);//验证联系人
    }
    @Test
    public void testAddWithNoEmail() throws Exception {
        ContactsBean bean = new ContactsBean();
        bean.setStar(true); //step1 identity
        bean.setEdit_picture(true);
        bean.setName(getRandomName(3,8));
        bean.setBirthday("20/11/1948");
        ContactsAction.pushNumber(bean,3);   //step2 numbers
        //ContactsAction.pushEmail(bean,3);//step3 email
        bean.setStreet(getRandomName(20,30));//step4 details
        bean.setNote(getRandomName(10,20));
        bean.setRing_tone(ContactsAction.getRandomType(Constant.RingTone));//step5 Tone
        bean.setIs_play_ring(true);
        bean.setMessage_tome(ContactsAction.getRandomType(Constant.MessageTone));
        bean.setIs_play_message(true);
        ContactsAction.addContact(bean);//使用bean添加联系人
        ContactsAction.checkContactsBean(bean);//验证联系人
    }
    @Test
    public void testAddAllItems() throws Exception {
        ContactsBean bean = new ContactsBean();
        bean.setStar(true); //step1 identity
        bean.setEdit_picture(true);
        bean.setName(getRandomName(3,8));
        bean.setBirthday("20/11/1948");
        ContactsAction.pushNumber(bean,3);   //step2 numbers
        ContactsAction.pushEmail(bean,3);//step3 email
        bean.setStreet(getRandomName(20,30));//step4 details
        bean.setNote(getRandomName(10,20));
        bean.setRing_tone(ContactsAction.getRandomType(Constant.RingTone));//step5 Tone
        bean.setIs_play_ring(true);
        bean.setMessage_tome(ContactsAction.getRandomType(Constant.MessageTone));
        bean.setIs_play_message(true);
        ContactsAction.addContact(bean);//使用bean添加联系人
        ContactsAction.checkContactsBean(bean);//验证联系人
    }
}
