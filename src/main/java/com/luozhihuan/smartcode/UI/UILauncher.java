package com.luozhihuan.smartcode.UI;

import com.luozhihuan.smartcode.adapter.SmartMouseAdapter;
import com.luozhihuan.smartcode.constants.Constant;
import com.luozhihuan.smartcode.model.DBUIInfoModel;
import com.luozhihuan.smartcode.service.impl.DBInfoMouseClickedService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by luozhihuan on 15/6/6.
 */
public class UILauncher extends JFrame {


    private JLabel dbLabel = new JLabel("数据库地址");
    private JLabel usrLabel = new JLabel("username");
    private JLabel passLabel = new JLabel("password");
    private JLabel tableLabel = new JLabel("表名");

    private JTextField dbTextField = new JTextField(30);
    private JTextField usrTextField = new JTextField(30);
    private JTextField passTextField = new JTextField(30);
    private JTextField tableTextField = new JTextField(30);

    private DBUIInfoModel dbuiInfoModel;


    JButton confirmButton = new JButton("确认");
    JButton resetButton = new JButton("重置");


    public UILauncher() {
        super("发射器");
        setGridLayout(5, 2, 5, 5);
        this.setSize(400, 300);
        //居中显示
        this.setLocationRelativeTo(null);
        //界面启动后点击关闭，程序将完全关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        UILauncher launcher = new UILauncher();
        launcher.launch();


    }

    public void addJButton(String buttonContent) {
        this.add(new JButton(buttonContent));
    }

    public void setFlowLayout(int alignment, int hgap, int vgap) {
        this.setLayout(new FlowLayout(alignment, hgap, vgap));
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


    public void launch() {

        FileChooser chooser = new FileChooser();
        Constant.PROJECT_PATH = chooser.getChooseFilePath();
//        init();
        //button
        confirmButton = new JButton("确认");
        resetButton = new JButton("重置");
        dbuiInfoModel = new DBUIInfoModel();
        dbuiInfoModel.getDbTextField().setText("192.168.2.218:3306/movie_touch?characterEncoding=utf8");
        dbuiInfoModel.getUsrTextField().setText("q3boy");
        dbuiInfoModel.getPassTextField().setText("123");
        dbuiInfoModel.getTableTextField().setText("robtickets_campaign");
        addComponent(dbuiInfoModel);
        //确认和重置按钮组件
        this.add(confirmButton);
        this.add(resetButton);
        confirmButton.addMouseListener(new SmartMouseAdapter(new DBInfoMouseClickedService(dbuiInfoModel)));
        this.setVisible(true);
    }


    public void addComponent(DBUIInfoModel dbuiInfoModel) {
        //*********数据库界面添加组件*****
        //数据库地址组件
        this.add(dbuiInfoModel.getDbLabel());
        this.add(dbuiInfoModel.getDbTextField());
        //用户名组件
        this.add(dbuiInfoModel.getUsrLabel());
        this.add(dbuiInfoModel.getUsrTextField());
        //密码组件
        this.add(dbuiInfoModel.getPassLabel());
        this.add(dbuiInfoModel.getPassTextField());
        //表明组件
        this.add(dbuiInfoModel.getTableLabel());
        this.add(dbuiInfoModel.getTableTextField());

    }

}
