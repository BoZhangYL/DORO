package doro.action;

import java.util.Random;

import ckt.base.VP4;

/**
 * Created by Caibing.Yin on 2017/1/14.
 */

public class EmailAction extends VP4 {
    public static String reduceRandomEmail(int length) {
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

}
