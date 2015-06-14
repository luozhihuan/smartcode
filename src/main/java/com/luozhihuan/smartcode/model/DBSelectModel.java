package com.luozhihuan.smartcode.model;

import javax.swing.*;

/**
 *
 * Created by luozhihuan on 15/6/13.
 */
public class DBSelectModel {
    public DBSelectModel(){
        isSelectedCheckbox = new JCheckBox();
        fieldText = new JTextField();
        fieldComboBox = new JComboBox();
        fieldTypeText = new JTextField();
        fieldTypeComboBox = new JComboBox();
    }
    //checkbox，即是否选中作为类的一个字段来生成
    private JCheckBox isSelectedCheckbox;
    //字段名text标记
    private JTextField fieldText;
    //字段名多选盒
    private JComboBox fieldComboBox;
    //字段类型标记
    private JTextField fieldTypeText;
    //字段类型多选盒
    private JComboBox fieldTypeComboBox;

    public JCheckBox getIsSelectedCheckbox() {
        return isSelectedCheckbox;
    }

    public void setIsSelectedCheckbox(JCheckBox isSelectedCheckbox) {
        this.isSelectedCheckbox = isSelectedCheckbox;
    }

    public JTextField getFieldText() {
        return fieldText;
    }

    public void setFieldText(JTextField fieldText) {
        this.fieldText = fieldText;
    }

    public JComboBox getFieldComboBox() {
        return fieldComboBox;
    }

    public void setFieldComboBox(JComboBox fieldComboBox) {
        this.fieldComboBox = fieldComboBox;
    }

    public JTextField getFieldTypeText() {
        return fieldTypeText;
    }

    public void setFieldTypeText(JTextField fieldTypeText) {
        this.fieldTypeText = fieldTypeText;
    }

    public JComboBox getFieldTypeComboBox() {
        return fieldTypeComboBox;
    }

    public void setFieldTypeComboBox(JComboBox fieldTypeComboBox) {
        this.fieldTypeComboBox = fieldTypeComboBox;
    }
}
