package com.luozhihuan.smartcode.model;

import javax.swing.*;

/**
 * Created by luozhihuan on 15/6/14.
 */
public class MethodModel {

    public static int SERVICE = 0;
    public static int SERVICE_IMPL = 1;
    public static int DAO = 2;
    public static int DAO_IMPL = 3;

    public MethodModel(JCheckBox methodCheckBox, JTextField methodTextField, int level) {
        this.methodCheckBox = methodCheckBox;
        this.methodTextField = methodTextField;
        this.level = level;
    }

    private JCheckBox methodCheckBox;

    private JTextField methodTextField;

    private int level;


    public JCheckBox getMethodCheckBox() {
        return methodCheckBox;
    }

    public void setMethodCheckBox(JCheckBox methodCheckBox) {
        this.methodCheckBox = methodCheckBox;
    }

    public JTextField getMethodTextField() {
        return methodTextField;
    }

    public void setMethodTextField(JTextField methodTextField) {
        this.methodTextField = methodTextField;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
