package doro.action;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.Assert;

import java.util.List;

import ckt.base.VP4;
import doro.bean.Constant;
import doro.bean.ContactsBean;
import doro.bean.TypeBean;
import doro.page.ContactsPage;
import doro.page.GalleryPage;


/**
 * Created by qiang.zhang on 2017/1/12.
 */
public class ContactsAction extends VP4 {
    //启动联系人
    public static void launchContacts(){
        try {
            //清除所有启动的app
            MainAction.clearAllApp();
            //启动联系人App
            MainAction.startApp(ContactsPage.CONTACTS);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    //click i want to
    public static void clickIWantToButton(){
        clickById(ContactsPage.I_WANT_TO_BTN_ID);
    }
    //添加联系人
    public static void click_AddContacts_Button(){
        clickByIdText(ContactsPage.COMMAND_TEXT_VIEW_BTN_ID,"Add contact");
    }
    public static String birthdayString(){
        String birthday="";
        birthday =getObject2ById(ContactsPage.BIRTHDAY_EDIT_TEXT_ID).getText();
        return birthday;
    }
    public static void cmdNext(){
        clickByIdText(ContactsPage.COMMAND_TEXT_VIEW_BTN_ID,"Next");
    }
    public static void cmdSave(){
        clickByIdText(ContactsPage.COMMAND_TEXT_VIEW_BTN_ID,"Save");
    }

    //is start
    public static void doStarred(){
        clickById(ContactsPage.STARRED_ID);
    }
    //设置姓名
    public static void nameEnterString(ContactsBean bean){
        String name=bean.getName();
        try {
            setText(ContactsPage.NAME_EDIT_TEXT_ID,name);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void nameEnterOK() {
        clickById(ContactsPage.OK_CMD_BTN_ID);
    }
    //设置姓名
    public static void phoneNumberEnterString(TypeBean phone) throws UiObjectNotFoundException {
        if (!text_exists("Enter phone number")){
            clickById(ContactsPage.ADD_PHONE_BTN);
        }
        UiObject2 enterObj=getUiObject2ByText("Enter phone number");
        enterObj.setText(phone.getNumber());
        enterObj.getParent().findObject(By.res(ContactsPage.TYPE_ID_BTN)).click();
        clickByIdText(ContactsPage.CHOOSE_TYPE_ID,phone.getType());
    }
    //点击电话号码文本框
    public static void clickPhoneNumberTextView(){
        getObject2ByClass(android.widget.EditText.class).click();
    }
    public static void clickAddNumberBtn(){
        clickById(ContactsPage.ADD_PHONE_BTN);
    }
    //设置邮箱
    public static void emailEnterString(TypeBean email) throws UiObjectNotFoundException {
        if (!text_exists("Enter email address")){
            clickById(ContactsPage.ADD_EMAIL_BTN);
        }
        UiObject2 enterObj=getUiObject2ByText("Enter email address");
        enterObj.setText(email.getNumber());
        enterObj.getParent().findObject(By.res(ContactsPage.TYPE_ID_BTN)).click();
        clickByIdText(ContactsPage.CHOOSE_TYPE_ID,email.getType());
    }
    //PostStreet
    public static void streetEnterString(String street){
        try {
            getObjectById(ContactsPage.STREET_ID_EDIT_TEXT).clearTextField();
            getObjectById(ContactsPage.STREET_ID_EDIT_TEXT).setText(street);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    //PostStreet
    public static void noteEnterString(String note){
        try {
            getObjectById(ContactsPage.NOTE_ID_EDIT_TEXT).clearTextField();
            getObjectById(ContactsPage.NOTE_ID_EDIT_TEXT).setText(note);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    //PostStreet
    public static String streetPullString(String street){
        String street_pull="";
        try {
            street_pull= getObjectById(ContactsPage.STREET_ID_EDIT_TEXT).getText();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        return street_pull;
    }
    //PostStreet
    public static String notePullString(){
        String note_pull="";
        try {
            note_pull= getObjectById(ContactsPage.NOTE_ID_EDIT_TEXT).getText();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        return note_pull;
    }
    //选择一张图片
    public static void choosePictureFromGallery(){
        waitUntilFind(GalleryPage.GALLERY_GRAID_VIEW,20000);
        List<UiObject2> imageViews=gDevice.findObject(By.res(GalleryPage.GALLERY_GRAID_VIEW)).findObjects(By.clazz(android.widget.ImageView.class));
        if (imageViews.size()>=1){
            imageViews.get(0).click();
            //Confirm button
            clickById(ContactsPage.CROP_PICTURE);
            //等待图片设置成功
            waitUntilFind(ContactsPage.COMMAND_TEXT_VIEW_BTN_ID,10000);
        }else {

        }
    }
    //设置ring tone
    public static void setRingTone(String ringtone) throws UiObjectNotFoundException {
        if (ringtone!=null){
            clickById(ContactsPage.NAME_RING_TONE_TEXT_VIEW_ID);
            ScrollViewByText(ringtone);
            clickByIdText(ContactsPage.CHOOSE_TYPE_ID,ringtone);
            clickById(ContactsPage.COMMAND_CONFIRM);
        }
    }
    //设置message tone
    public static void setMessageTone(String message_tone) throws Exception {
        if (message_tone!=null){
            clickById(ContactsPage.NAME_MESSAGE_TONE_TEXT_VIEW_ID);
            ScrollViewByText(message_tone);
            clickByIdText(ContactsPage.CHOOSE_TYPE_ID,message_tone);
            clickById(ContactsPage.COMMAND_CONFIRM);
        }
    }

    public static void addContact(ContactsBean bean) throws Exception {
        logger.info(bean.toString());
        click_AddContacts_Button();
        //step1
        if (bean.isStar()){
            doStarred();
        }
        nameEnterString(bean);
        setBirthday(bean);
        if (bean.isEdit_picture()){
            clickById(ContactsPage.EDIT_PICTURE_ID);
            choosePictureFromGallery();
        }
        cmdNext();
        //step2 设置电话号码
        List<TypeBean> list_numbers=bean.getNumber_list();
        for (TypeBean number:list_numbers) {
            phoneNumberEnterString(number);
        }
        cmdNext();
        //step3  设置email
        List<TypeBean> list_email=bean.getEmail_list();
        for (TypeBean email:list_email) {
            emailEnterString(email);
        }
        cmdNext();
        //step4  设置details
        setText(ContactsPage.STREET_ID_EDIT_TEXT,bean.getStreet());
        setText(ContactsPage.NOTE_ID_EDIT_TEXT,bean.getNote());
        cmdNext();
        //step5 Tone
        setRingTone(bean.getRing_tone());
        setMessageTone(bean.getMessage_tome());
        if (bean.is_play_ring()){
            clickById(ContactsPage.PLAY_RING_TONE_BTN_ID);
        }
        waitTime(3);
        if (bean.is_play_message()){
            clickById(ContactsPage.PLAY_MESSAGE_TONE_BTN_ID);
        }
        cmdSave();
        //等待保存完成
        waitUntilFind(ContactsPage.COMMAND_TEXT_VIEW_BTN_ID,"Edit",20000);
    }
    public static void checkPhoneEmailKeyValue(TypeBean bean,String type){
        logger.info("["+type+"]-To check- start"+bean.toString());
        ScrollViewByText(bean.getNumber());
        List<UiObject2> relativeLayouts=gDevice.findObjects(By.clazz(android.widget.RelativeLayout.class));
        boolean isFind = false;
        String key="";
        String value=bean.getNumber();
        if (bean.getType().contains("Fax")){
            key="Home fax";
        }else{
            key=bean.getType()+" "+type.toLowerCase();
        }
        logger.info(String.format("To check [%s]|[%s]",key,value));
        for (UiObject2 relativeLayout: relativeLayouts) {
            List<UiObject2> textViews=relativeLayout.findObjects(By.depth(1).clazz(android.widget.TextView.class));
            if (textViews.size()==2){
                String text1=textViews.get(0).getText();
                String text2=textViews.get(1).getText();
                if (text1.equals(key)&&
                        text2.equals(value)){
                    logger.info("Type["+type+"]|["+text1+"[|["+text2+"]");
                    isFind=true;
                    break;
                }
            }
        }
        logger.info("["+type+"]-To check- stop");
        Assert.assertEquals(bean.toString(),true,isFind);
    }
    public static void checkKeyValue(String key,String value) throws UiObjectNotFoundException {
        logger.info(String.format("To check- %s|%s start",key,value));
        ScrollViewByText(value);
        waitTime(2);
        ScrollViewByText(key);
        waitTime(2);
        UiObject2 keyObj=getUiObjectByIdText(ContactsPage.ITEM_TYPE_ID,key);
        UiObject2 valueObj=keyObj.getParent().findObject(By.res(ContactsPage.ITEM_VALUE_ID));
        String activeValue=valueObj.getText();
        logger.info("key  -"+key);
        activeValue=activeValue.replaceAll("-","");
        logger.info("checkKeyValue - ActiveValue -"+activeValue);
        logger.info("checkKeyValue - ExpectValue -"+value);
        Assert.assertEquals(String.format("%s|%s",key,value),value,activeValue);
        logger.info("To check- stop");
    }
    public static void checkBirthday(ContactsBean bean) throws UiObjectNotFoundException {
        if (bean.getBirthday()!=null){
            String expectBirthday=bean.getBirthday();
            String activeBirthday=getTex(ContactsPage.BIRTHDAY_ID);
            Assert.assertEquals("birthday",bean.getBirthday(),activeBirthday);
        }
    }
    public static void checkContactsBean(ContactsBean bean) throws UiObjectNotFoundException {
        ScrollViewByText(bean.getName());
        //verify name
        Assert.assertEquals("name",bean.getName(),getTex(ContactsPage.NAME_EDIT_TEXT_ID));
        //verify birthday
        checkBirthday(bean);
        //verify numbers
        for (TypeBean number:bean.getNumber_list()) {
            checkPhoneEmailKeyValue(number,"phone");
        }
        //verify emails
        for (TypeBean email:bean.getEmail_list()) {
            checkPhoneEmailKeyValue(email,"email");
        }
        if (bean.getStreet()!=null){
            checkKeyValue("Post address",bean.getStreet());
        }
        if (bean.getNote()!=null){
            checkKeyValue("Note",bean.getNote());
        }
        if (bean.getRing_tone()!=null){
            checkKeyValue("Ringtone",bean.getRing_tone());
        }
        if (bean.getMessage_tome()!=null){
            checkKeyValue("Message tone",bean.getMessage_tome());
        }

    }
    public static void pushNumber(ContactsBean bean,int size){
        for (int i = 0; i <size ; i++) {
            TypeBean typeBean= new TypeBean();
            typeBean.setNumber(getRandomTel());
            typeBean.setType(getRandomType(Constant.PhoneType));
            bean.getNumber_list().add(typeBean);
        }
    }
    public static void pushNumber(ContactsBean bean,String type){
        TypeBean typeBean= new TypeBean();
        typeBean.setNumber(getRandomTel());
        typeBean.setType(type);
        bean.getNumber_list().add(typeBean);
    }
    public static void pushEmail(ContactsBean bean,int size){
        for (int i = 0; i <size ; i++) {
            TypeBean typeBean= new TypeBean();
            typeBean.setType(getRandomType(Constant.EmailType));
            typeBean.setNumber(getRandomEmail(3,7));
            bean.getEmail_list().add(typeBean);
        }
    }
    public static void setBirthday(ContactsBean bean){
        if (bean.getBirthday()!=null){
            String birthday=bean.getBirthday();
            //01/01/1999
            clickById(ContactsPage.BIRTHDAY_EDIT_TEXT_ID);
            String[] births=birthday.split("/");
            String day=births[0];
            String month=births[1];
            String year=births[2];
            logger.info(String.format("%s|%s|%s",day,month,year));
            //do day
            Integer dayInt=Integer.parseInt(day);
            int clickDay=dayInt-1;
            for (int i = 0; i <clickDay ; i++) {
                clickById(ContactsPage.DAY_INCREASE);
            }
            //do year
            int yearInt=Integer.parseInt(year);
            logger.info(""+yearInt);
            int clickYear=0;
            if (yearInt>=1945){
                clickYear=yearInt-1945;
                logger.info("clickYear-YEAR_INCREASE:"+clickYear);
                for (int i = 0; i <clickYear ; i++) {
                    clickById(ContactsPage.YEAR_INCREASE);
                }
            }else{
                clickYear=1455-yearInt;
                logger.info("clickYear-YEAR_DECREASE:"+clickYear);
                for (int i = 0; i <clickYear ; i++) {
                    clickById(ContactsPage.YEAR_DECREASE);
                }
            }
            //do month
            int monthInt=Integer.parseInt(month);
            for (int i = 0; i <monthInt-1; i++) {
                clickById(ContactsPage.MONTH_INCREASE);
            }
            clickById(ContactsPage.COMMAND_CONFIRM);
        }
    }
    public static String getRandomType(String[] type){
        int number = (int)(Math.random()*type.length);
        String randomString=type[number];
        logger.info("random-"+randomString);
        return randomString;
    }
    public static void selectWhatToDo(int index){
        int menuHeight=getObject2ById(ContactsPage.MENU_CLOSE_ID).getVisibleBounds().height();
        int x=getObject2ById(ContactsPage.MENU_CLOSE_ID).getVisibleBounds().centerX();
        int displayHeight=gDevice.getDisplayHeight();
        int itemHeight=(displayHeight-menuHeight)/5;
        int y=index*itemHeight;
        gDevice.click(x,y);
    }
    public static void  navWantToDelete(){
        for (int i = 1; i <=5 ; i++) {
            clickIWantToButton();
            selectWhatToDo(4);
            try {
                if (id_exists(ContactsPage.DELETE_SELECT_ALL_CHECKBOX)){
                    logger.info("enter delete page");
                    break;
                }
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public static void deleteAll(){
        clickById(ContactsPage.DELETE_SELECT_ALL_CHECKBOX);
        clickById(ContactsPage.DELETE_CONTACT_CONFIREM_ID);
        clickByIdText(ContactsPage.DELETE_CONTACT_CONFIREM_POP_TEXT_ID,"OK");
    }
    public static void verifyContact(String name,boolean isExpectFind){
        boolean isActiveFind=ScrollViewByText(name);
        Assert.assertEquals("verify contact:"+name,isExpectFind,isActiveFind);
    }
    public static void deleteContact(String name){
        logger.info("Now to delete -"+name);
        ScrollViewByText(name);
        List<UiObject2> items=findObjects(ContactsPage.CONTENT_ITEM);
        for (UiObject2 item:items) {
            if (item.hasObject(By.text(name))){
                item.click();
                logger.info("=================================");
                break;
            }
        }
        //clickByText(name);
        clickById(ContactsPage.DELETE_CONTACT_CONFIREM_ID);
        clickByIdText(ContactsPage.DELETE_CONTACT_CONFIREM_POP_TEXT_ID,"OK");
        //等待删除完成
        waitUntilFind(ContactsPage.COMMAND_TEXT_VIEW_BTN_ID,20000);
    }
    public static String  getTheLastName(){
        List<UiObject2> names=findObjects(ContactsPage.NAME_LABEL_ID);
        return names.get(names.size()-1).getText();
    }
    public static boolean isFindFavourites(){
        UiSelector selector=new UiSelector().resourceId(ContactsPage.STARRED_ID);
        return  scrollIntoView(selector);
    }
    public static String isFindNormalName(){
        scrollToBegin(100);
        List<UiObject2> icons;
        boolean isFind=false;
        String name="";
        icons=findObjects(ContactsPage.CONTACT_ICON_ID);
        for (UiObject2 icon:icons) {
            UiObject2 pp=icon.getParent().getParent().getParent();
            if (!pp.hasObject(By.res(ContactsPage.STARRED_ID))&&!pp.hasObject(By.textContains("In Case of Emergency (ICE)"))){
                isFind=true;
                name=pp.findObject(By.res(ContactsPage.NAME_LABEL_ID)).getText();
                break;
            }
        }
        for (int i = 0; i <20 ; i++) {
            if (!isFind){
                scrollForward(55);
                icons=findObjects(ContactsPage.CONTACT_ICON_ID);
                for (UiObject2 icon:icons) {
                    UiObject2 pp=icon.getParent().getParent().getParent();
                    if (!pp.hasObject(By.res(ContactsPage.STARRED_ID))&&!pp.hasObject(By.textContains("In Case of Emergency (ICE)"))){
                        isFind=true;
                        name=pp.findObject(By.res(ContactsPage.NAME_LABEL_ID)).getText();
                        break;
                    }
                }
            }
        }
        logger.info("Find-Normal-Name-"+name);
        return  name;
    }
    public static String selectFavourites(){
        String favouritesName="";
        UiObject2 starObj=findObject(ContactsPage.STARRED_ID) ;
        UiObject2 nameObj=starObj.getParent().findObject(By.res(ContactsPage.NAME_LABEL_ID));
        favouritesName=nameObj.getText();
        return  favouritesName;
    }
    /*choose the last name to be delete*/
    public static String selectAContactToBeDel(){
        scrollToEnd(50);
        return  getTheLastName();
    }
    /*export form mobile to sd card*/
    public void exportFromMobileToSdcard(){

    }
    public void exportFromMobileToSim(){

    }
/*    public static UiObject2 chooseOneContact(){

    }*/


}
