package doro.action;

import java.io.IOException;
import java.util.Random;

import ckt.base.VP4;
import doro.page.MenuPage;

/**
 * Created by Caibing.yin on 2017/1/12.
 */

public class MenuAction extends VP4 {
    //进入菜单界面
    public static void navToMenu(){
            gDevice.pressHome();
            clickById(MenuPage.More);
            clickByText("My applications");
    }
    //从应用名列表中随机产生一个应用程序名
    public  static String getRandomName() { //length表示生成字符串的长度
        Random Rand = new Random();
        int length = MenuPage.AppNameList.length;
        int Num = Rand.nextInt(length-1);
        String RandName = MenuPage.AppNameList[Num];
        return RandName;
    }
    //根据应用程序名得到包名
    public static String getPkgName(String getRandomName){
        return "";
    }
}
