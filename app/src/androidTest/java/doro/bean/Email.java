package doro.bean;

import android.app.FragmentBreadCrumbs;

import java.util.Date;

/**
 * Created by bo.zhang on 2017/03/21   .
 */

public class Email {
    private String To = null;
    private String Cc = null;
    private String Bcc = null;
    private String Subject = null;
    private String Message = null;

    public Email(String To, String Cc, String Bcc, String Subject, String Message) {
        this.To = To;
        this.Cc = Cc;
        this.Bcc = Bcc;
        this.Subject = Subject;
        this.Message = Message;
    }

    public Email() {
        this.To = "cktcdtest1@163.com";
        this.Cc = "cktcdtest1@163.com";
        this.Bcc = "cktcdtest1@163.com";
        this.Subject = getCurrentDate();
        this.Message = getRandomString(20);
    }

    public String getSubject() {
        return this.Subject;
    }

    public String getMessage() {
        return this.Message;
    }

    public boolean isAttch() {
        return true;
    }

    public String getRandomString(int Length) {
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
                'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
                'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String RandomString = "";
        for (int i = 0; i < Length; i++) {
            int randomLength = (int) (Math.random() * (chars.length - 1));
            RandomString = RandomString + chars[randomLength];
        }
        return RandomString;
    }

    public String getCurrentDate() {
        Date date = new Date();
        String Currentdatep = String.valueOf(date.getTime());
        return Currentdatep;
    }
}
