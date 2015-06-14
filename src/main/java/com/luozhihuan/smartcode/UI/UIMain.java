package com.luozhihuan.smartcode.UI;

/**
 * Created by luozhihuan on 15/6/13.
 */
public class UIMain {

    public static void main(String[] args) {
        FileChooser chooser = new FileChooser(FileChooser.START_NOTHING);
        String path = chooser.getChooseFilePath();
        System.out.println(path);
    }
}
