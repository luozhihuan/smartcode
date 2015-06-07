package com.luozhihuan.smartcode.model;

import javax.swing.*;

/**
 * Created by luozhihuan on 15/6/6.
 */
public class DBUIInfoModel {

    private JLabel dbLabel;
    private JLabel usrLabel;
    private JLabel passLabel;
    private JLabel tableLabel;

    private JTextField dbTextField;
    private JTextField usrTextField;
    private JTextField passTextField;
    private JTextField tableTextField;

    public DBUIInfoModel(){
        dbLabel = new JLabel("数据库地址");
        usrLabel = new JLabel("username");
        passLabel = new JLabel("password");
        tableLabel = new JLabel("表名");

        dbTextField = new JTextField(30);
        usrTextField = new JTextField(30);
        passTextField = new JTextField(30);
        tableTextField = new JTextField(30);
    }
    public JLabel getDbLabel() {
        return dbLabel;
    }

    public void setDbLabel(JLabel dbLabel) {
        this.dbLabel = dbLabel;
    }

    public JLabel getUsrLabel() {
        return usrLabel;
    }

    public void setUsrLabel(JLabel usrLabel) {
        this.usrLabel = usrLabel;
    }

    public JLabel getPassLabel() {
        return passLabel;
    }

    public void setPassLabel(JLabel passLabel) {
        this.passLabel = passLabel;
    }

    public JLabel getTableLabel() {
        return tableLabel;
    }

    public void setTableLabel(JLabel tableLabel) {
        this.tableLabel = tableLabel;
    }

    public JTextField getDbTextField() {
        return dbTextField;
    }

    public void setDbTextField(JTextField dbTextField) {
        this.dbTextField = dbTextField;
    }

    public JTextField getUsrTextField() {
        return usrTextField;
    }

    public void setUsrTextField(JTextField usrTextField) {
        this.usrTextField = usrTextField;
    }

    public JTextField getPassTextField() {
        return passTextField;
    }

    public void setPassTextField(JTextField passTextField) {
        this.passTextField = passTextField;
    }

    public JTextField getTableTextField() {
        return tableTextField;
    }

    public void setTableTextField(JTextField tableTextField) {
        this.tableTextField = tableTextField;
    }
}
