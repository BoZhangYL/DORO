package doro.testcase;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.Until;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import ckt.android.ContactsManager;
import ckt.base.VP4;
import doro.action.ContactsAction;
import doro.action.MainAction;
import doro.bean.Constant;
import doro.bean.ContactsBean;
import doro.page.ContactsPage;
import doro.page.ViewPage;

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
    public void testLaunchFromView() throws UiObjectNotFoundException {
        MainAction.clearAllApp();
        clickByIdText(ViewPage.LANUCH_LABEL,ViewPage.VIEW_TEXT);
        ScrollViewByText(ViewPage.MY_CONTACTS_BOOK_TEXT);
        clickByIdText(ViewPage.VIEW_MESSAGE_ID,ViewPage.MY_CONTACTS_BOOK_TEXT);
        gDevice.waitForWindowUpdate(ContactsPage.CONTACT_PKG_NAME,10000);
        //验证联系人启动-success
        Assert.assertEquals("CurrentPackageName is Contacts",
                ContactsPage.CONTACT_PKG_NAME,
                gDevice.getCurrentPackageName());
    }
    @Test
    public void testIWantTo(){
        ContactsAction.clickIWantToButton();
        BySelector bySelector= By.res(ContactsPage.IWANTTO_MENU_ID).text("I want to");
        boolean has=gDevice.wait(Until.hasObject(bySelector), 10000);
        Assert.assertEquals("i want to-navigate",true,has);
    }
    @Test
    public void testAddWithNameMobile() throws Exception {
        ContactsBean bean = new ContactsBean();
        bean.setEdit_picture(true);
        bean.setName(getRandomName(3,8));
        ContactsAction.pushNumber(bean,"Mobile");
        ContactsAction.addContact(bean);//使用bean添加联系人
        ContactsAction.checkContactsBean(bean);//验证联系人
    }
    @Test
    public void testAddWithNameHome() throws Exception {
        ContactsBean bean = new ContactsBean();
        bean.setEdit_picture(true);
        bean.setName(getRandomName(3,8));
        ContactsAction.pushNumber(bean,"Home");
        ContactsAction.addContact(bean);//使用bean添加联系人
        ContactsAction.checkContactsBean(bean);//验证联系人
    }

        @Test
    public void testAddWithNoStar() throws Exception {
        ContactsBean bean = new ContactsBean();
        bean.setStar(false); //step1 identity
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
    @Test
    public void testAddWithNoEditPicture() throws Exception {
        ContactsBean bean = new ContactsBean();
        bean.setStar(true); //step1 identity
        bean.setEdit_picture(false);
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
    @Test
    public void testAddWithNoMessageTone() throws Exception {
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
        //bean.setMessage_tome(ContactsAction.getRandomType(Constant.MessageTone));
        bean.setIs_play_message(true);
        ContactsAction.addContact(bean);//使用bean添加联系人
        ContactsAction.checkContactsBean(bean);//验证联系人
    }
    @Test
    public void testAddWithNoRingtone() throws Exception {
        ContactsBean bean = new ContactsBean();
        bean.setStar(true); //step1 identity
        bean.setEdit_picture(true);
        bean.setName(getRandomName(3,8));
        bean.setBirthday("20/11/1948");
        ContactsAction.pushNumber(bean,3);   //step2 numbers
        ContactsAction.pushEmail(bean,3);//step3 email
        bean.setStreet(getRandomName(20,30));//step4 details
        bean.setNote(getRandomName(10,20));
        //bean.setRing_tone(ContactsAction.getRandomType(Constant.RingTone));//step5 Tone
        bean.setIs_play_ring(true);
        bean.setMessage_tome(ContactsAction.getRandomType(Constant.MessageTone));
        bean.setIs_play_message(true);
        ContactsAction.addContact(bean);//使用bean添加联系人
        ContactsAction.checkContactsBean(bean);//验证联系人
    }
    @Test
    public void testAddWithNoNote() throws Exception {
        ContactsBean bean = new ContactsBean();
        bean.setStar(true); //step1 identity
        bean.setEdit_picture(true);
        bean.setName(getRandomName(3,8));
        bean.setBirthday("20/11/1948");
        ContactsAction.pushNumber(bean,3);   //step2 numbers
        ContactsAction.pushEmail(bean,3);//step3 email
        bean.setStreet(getRandomName(20,30));//step4 details
        //bean.setNote(getRandomName(10,20));
        bean.setRing_tone(ContactsAction.getRandomType(Constant.RingTone));//step5 Tone
        bean.setIs_play_ring(true);
        bean.setMessage_tome(ContactsAction.getRandomType(Constant.MessageTone));
        bean.setIs_play_message(true);
        ContactsAction.addContact(bean);//使用bean添加联系人
        ContactsAction.checkContactsBean(bean);//验证联系人
    }
    @Test
    public void testAddWithNoStreet() throws Exception {
        ContactsBean bean = new ContactsBean();
        bean.setStar(true); //step1 identity
        bean.setEdit_picture(true);
        bean.setName(getRandomName(3,8));
        bean.setBirthday("20/11/1948");
        ContactsAction.pushNumber(bean,3);   //step2 numbers
        ContactsAction.pushEmail(bean,3);//step3 email
        //bean.setStreet(getRandomName(20,30));//step4 details
        bean.setNote(getRandomName(10,20));
        bean.setRing_tone(ContactsAction.getRandomType(Constant.RingTone));//step5 Tone
        bean.setIs_play_ring(true);
        bean.setMessage_tome(ContactsAction.getRandomType(Constant.MessageTone));
        bean.setIs_play_message(true);
        ContactsAction.addContact(bean);//使用bean添加联系人
        ContactsAction.checkContactsBean(bean);//验证联系人
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
    @Test
    public void testDelFavourites() throws UiObjectNotFoundException {
        boolean isFind=ContactsAction.isFindFavourites();
        if (isFind){
            String name = ContactsAction.selectFavourites();
            ContactsAction.navWantToDelete();
            ContactsAction.deleteContact(name);
            ContactsAction.verifyContact(name,false);
        }else{
            Assert.assertEquals("there is no favourites contact",isFind);
        }
    }
    @Test
    public void testDeleteOne() throws UiObjectNotFoundException {
        //滑动到页面最后,输出最后面的那个联系人
        if (!id_exists(ContactsPage.FIREST_TEXT_ID)){
            ContactsAction.navWantToDelete();
            String name = ContactsAction.isFindNormalName();
            ContactsAction.deleteContact(name);
            ContactsAction.verifyContact(name,false);
        }else {
            Assert.assertTrue("No contacts to be deleted",false);
        }
    }
    @Test
    public void testDeleteAll() throws UiObjectNotFoundException {
        if (!id_exists(ContactsPage.FIREST_TEXT_ID)){
            ContactsAction.navWantToDelete();
            ContactsAction.deleteAll();
            //等待删除完成
            waitUntilFind(ContactsPage.FIREST_TEXT_ID,60000);
            Assert.assertEquals("no contacts",true,id_exists(ContactsPage.FIREST_TEXT_ID));
        }else {
            Assert.assertTrue("No contacts to be deleted",false);
        }
    }
    @Test
    public void testExportToSdcard() throws Exception {
        //delete  all vcf file
        ContactsAction.deleteSdcardContactsVCF();
        ContactsAction.exportToSdcard(true);
        File[] files=ContactsAction.getFileList();
        Assert.assertEquals("export vcf file",1,files.length);
    }
    @Test
    public void testImportFromSdcard(){
        ContactsManager contactsManager = new ContactsManager();
        contactsManager.deleteAll();
        ContactsAction.addContactFromContent();
        
    }
}
