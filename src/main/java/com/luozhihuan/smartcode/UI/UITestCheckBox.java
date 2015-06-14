package com.luozhihuan.smartcode.UI;

import com.luozhihuan.smartcode.adapter.SmartMouseAdapter;
import com.luozhihuan.smartcode.model.DBUIInfoModel;
import com.luozhihuan.smartcode.service.impl.DBInfoMouseClickedService;

import javax.swing.*;
import java.awt.*;

/**
 * Created by luozhihuan on 15/6/13.
 */
public class UITestCheckBox extends JFrame {

    public UITestCheckBox() {
        super("发射器");
        setGridLayout(5, 2, 5, 5);
        this.setSize(400, 300);
        //居中显示
        this.setLocationRelativeTo(null);
        //界面启动后点击关闭，程序将完全关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setCheckBox();;
        this.setVisible(true);
    }


    /**
     * @param rows 行数
     * @param cols 列数
     * @param hgap 横向间距，单位：像素
     * @param vgap 竖向间距，单位：像素
     */
    public void setGridLayout(int rows, int cols, int hgap, int vgap) {
        this.setLayout(new GridLayout(rows, cols, hgap, vgap));
    }

    public static void main(String[] args) {
        UITestCheckBox box = new UITestCheckBox();
//        box.setCheckBox();

    }

    public void setCheckBox() {
        JCheckBox box = new JCheckBox("包装费");
//        box.isSelected()
        this.add(box);

        JComboBox comboBox=new JComboBox();
        comboBox.addItem("身份证");
        comboBox.addItem("驾驶证");
        comboBox.addItem("军官证");
//        comboBox.get
        this.add(comboBox);

    }
}
