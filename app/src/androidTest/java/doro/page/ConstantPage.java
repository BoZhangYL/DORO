package doro.page;

import android.os.Environment;

import junit.framework.Assert;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import ckt.tools.Property;
import doro.bean.Constant;

/**
 * Created by Caibing.Yin on 2017/2/3.
 */

public class ConstantPage {
    public HashMap addAccount() {
        HashMap<String, String> account = new HashMap<>();
        account.put("cktfalcontest@163.com", "falcon@ckt2014");
        //account.put("cktfalcontest@gmail.com","falcon@ckt2014");//(需要短信验证13458523374)
        account.put("clh309150918@gmail.com", "309150978");
        account.put("woshihouzi2016@gmail.com", "woshidoubi");
        account.put("chengduxike@hotmail.com", "falcon@ckt2015");
        account.put("cktfalcontest@qq.com", "falcon@ckt2014");
        account.put("cktfalcontest@sina.com", "falcon@ckt2014");//Not Cancle
        account.put("18780073145@139.com", "falcon@ckt2014");
        account.put("13458523374@189.cn", "falcon@ckt2014 ");
        return account;
    }

    //得到某一账号
    public String getEmail_Account(Object EmailType){
        if(addAccount().containsKey(EmailType)){
            return addAccount().get(EmailType).toString();
        }
        return null;
    }

    //gmail-address-caibing.yin
    public static  final  String userName ="yccmmy@gmail.com";
    public static  final  String passwd ="ycb1991924";

    //permission allow dialog
    public static String PERMISSION_ALLOW="com.android.packageinstaller:id/permission_allow_button";

    public static String WRONG_EMAIL = "123pp@178.com";
    private static Logger logger=Logger.getLogger(Constant.class.getName());

    public static String getUserName(){
        String config= Environment.getExternalStorageDirectory()+ File.separator+"config.properties";
        logger.info(config);
        logger.info(userName);
        String userName= Property.getValueByKey(config,"user_name");
        logger.info("userName:"+userName);
        return userName;
    }

    public static String getPassword(){
        String config= Environment.getExternalStorageDirectory()+ File.separator+"config.properties";
        String user_password=Property.getValueByKey(config,"user_password");
        logger.info(config);
        logger.info("user_password:"+user_password);
        return user_password;
    }

    public static String getUserName(String userNameType){
        String nameType=userNameType;
        String config= Environment.getExternalStorageDirectory()+ File.separator+"config.properties";
        logger.info(config);
        logger.info(userName);
        String userName=Property.getValueByKey(config,nameType);
        logger.info("userName:"+userName);
        if (userName.length()< 4){
            Assert.fail("userName.length<4,Please check the Config.properties configuration");
        }
        return userName;
    }
    public static String getPassword(String userPasswordType) {
        String passwordType=userPasswordType;
        String config = Environment.getExternalStorageDirectory() + File.separator + "config.properties";
        String userPassword = Property.getValueByKey(config, passwordType);
        logger.info(config);
        logger.info("userPassword:" + userPassword);
        if (userPassword.length()<1||userPassword==null){
            Assert.fail("Password is null,Please check the Config.properties configuration");
        }
        return userPassword;
    }
    /*
    String randomStringGenerator(int length)产生随机字符串，int length定义了该字符串最大可能长度
     */
    public static  String randomStringGenerator(int length){
        int count=(int)(1+Math.random()*(length-1+1));
        char[] alphaArray = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v','K',
                'w', 'x', 'y', 'z','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                'J','L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V','W',
                'X', 'Y', 'Z','0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '@', '#', '$','%', '&', '*', ':', ';', '/', '?', '-', '+', '=', };
        String randomSequence = "";
        for (int i = 0; i < count; i++) {
            Character c = Character.valueOf(alphaArray[new Random().nextInt(alphaArray.length)]);
            randomSequence = randomSequence + c.toString();
        }
        logger.info("randomString:"+ randomSequence);
        return randomSequence;
    }
    public static  String randomFixedLengthStringGenerator(int length){
        char[] alphaArray = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v','K',
                'w', 'x', 'y', 'z','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                'J','L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V','W',
                'X', 'Y', 'Z','0', '1', '2', '3', '4', '5', '6', '7', '8', '9','_'};
        String randomSequence = "";
        for (int i = 0; i < length; i++) {
            Character c = Character.valueOf(alphaArray[new Random().nextInt(alphaArray.length)]);
            randomSequence = randomSequence + c.toString();
        }
        logger.info("randomString:"+ randomSequence);
        return randomSequence;
    }
    public static String randomPhoneNumber() {
        String phoneNumber="";
        char[]alphaNumber={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        String[]alphaTelTitle={"130","131","132","133","134","135","136","137","138","139", "150",
                "151","152","153","155","156","157","158","159","180","181","183","186","187","188","189","170"};
        String phoneTitle= alphaTelTitle[new Random().nextInt(alphaTelTitle.length)];
        phoneNumber=phoneNumber+phoneTitle;
        for(int i=0;i<8;i++){
            Character ranNumber=Character.valueOf(alphaNumber[new Random().nextInt(alphaNumber.length)]);
            phoneNumber=phoneNumber+ranNumber.toString();
        }
        System.out.println(phoneNumber);
        return phoneNumber;
    }
    public static String randomEmail(int length) {
        int count=(int)(3+Math.random()*(length-3));
        int count1=(int)(1+Math.random()*(count-3));
        String randomEmail="";
        char[]alphaEmail={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
                'p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G',
                'H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y',
                'Z','0','1','2','3','4','5','6','7','8','9'};
        String[]alphaTLD={".com",".net",".org",".biz",".mobi",".travel",".name",".pro",
                ".cn",".cc", ".co",".in",".me",".tv",".us",".tk",".net",".gov",".edu",
                ".mil",".biz",".info",".museum",".travel",".int",".areo",".post",".rec",
                ".asia",".vip",".top",".au",".ae",".ar",".br",".ca",".ch",".de",".dk",
                ".es",".eg",".fg",".gr",".hk",".in",".iq",".it",".jp",".mo",".us",".uk"};
        for(int i=0;i<count1;i++){
            Character ranChar1=Character.valueOf(alphaEmail[new Random().nextInt(alphaEmail.length)]);
            randomEmail=randomEmail+ranChar1.toString();}
        randomEmail=randomEmail+"@";
        for(int a=0;a<count-count1-1;a++){
            Character ranChar2=Character.valueOf(alphaEmail[new Random().nextInt(alphaEmail.length)]);
            randomEmail=randomEmail+ranChar2.toString();
        }
        String emailEnding= alphaTLD[new Random().nextInt(alphaTLD.length)];
        randomEmail=randomEmail+emailEnding.toString();
        System.out.println(randomEmail);
        return randomEmail;
    }


    /*
     *   产生lengh位随机的错误手机号码，其中lengh不能等于11
     */

    public static String randomErrPhoneNumber(int lengh) {
        String phoneNumber="";
        char[]alphaNumber={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        String[]alphaTelTitle={"130","131","132","133","134","135","136","137","138","139", "150",
                "151","152","153","155","156","157","158","159","180","181","183","186","187","188","189","170"};
        String phoneTitle= alphaTelTitle[new Random().nextInt(alphaTelTitle.length)];
        phoneNumber=phoneNumber+phoneTitle;
        for(int i=0;i<lengh-3;i++){
            Character ranNumber=Character.valueOf(alphaNumber[new Random().nextInt(alphaNumber.length)]);
            phoneNumber=phoneNumber+ranNumber.toString();
        }
        System.out.println(phoneNumber);
        return phoneNumber;
    }

}
