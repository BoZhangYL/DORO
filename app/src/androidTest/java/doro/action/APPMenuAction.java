package doro.action;

import org.hamcrest.Asst;

import java.io.IOException;
import java.util.Random;

import ckt.base.VP4;
import doro.page.APPMenuPage;

/**
 * Created by Caibing.yin on 2017/1/12.
 */

public class APPMenuAction extends VP4 {
    //进入菜单界面
    public static void navToMenu(){
            gDevice.pressHome();
            clickById(APPMenuPage.More);
            clickByText("My applications");
    }
    //从应用名列表中随机产生一个应用程序名
    public  static String getRandomName() { //length表示生成字符串的长度
        Random Rand = new Random();
        int length = APPMenuPage.AppNameList.length;
        int Num = Rand.nextInt(length-1);
        String RandName = APPMenuPage.AppNameList[Num];
        return RandName;
    }
    //根据应用程序的序号得到包名
    public static String getPkgName(int index) {
        if (index < APPMenuPage.AppNameList.length){
            return APPMenuPage.PkgNameList[index];
        }
        else {
            return "Error Name,Please input App Name Again!";
        }
    }
}
