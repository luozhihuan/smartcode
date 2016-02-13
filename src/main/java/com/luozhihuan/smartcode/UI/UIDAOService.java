package com.luozhihuan.smartcode.UI;

import com.luozhihuan.smartcode.adapter.SmartMouseAdapter;
import com.luozhihuan.smartcode.constants.Constant;
import com.luozhihuan.smartcode.model.MethodModel;
import com.luozhihuan.smartcode.service.impl.DBInfoMouseClickedService;
import com.luozhihuan.smartcode.service.impl.GenerateDomainClassService;
import com.luozhihuan.smartcode.service.impl.GenerateFourClassService;
import com.luozhihuan.smartcode.service.impl.GenerateMehodByDomainClassService;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by luozhihuan on 15/6/14.
 */
public class UIDAOService extends JFrame{


    public UIDAOService(){

        super("发射器");



        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5,2,3,3));

        //*************
        JPanel selectPackagePanel = new JPanel();
        selectPackagePanel.setLayout(new GridLayout(7,2,3,3));

        JLabel serviceLabel = new JLabel("service层");
        JLabel serviceImplLabel = new JLabel("service.impl层");
        JLabel daoLabel = new JLabel("dao层");
        JLabel daoImlLabel = new JLabel("dao.impl层");

        JComboBox serviceCombox = new JComboBox();
        JComboBox serviceImplCombox = new JComboBox();
        JComboBox daoCombox = new JComboBox();
        JComboBox daoImplCombox = new JComboBox();

        Set<String> packageNamesSet = Constant.PACKAGE_NAMES_SET;
        for(String packageName : packageNamesSet){
            serviceCombox.addItem(packageName);
            serviceImplCombox.addItem(packageName);
            daoCombox.addItem(packageName);
            daoImplCombox.addItem(packageName);
        }

        selectPackagePanel.add(serviceLabel);
        selectPackagePanel.add(serviceCombox);
        selectPackagePanel.add(serviceImplLabel);
        selectPackagePanel.add(serviceImplCombox);
        selectPackagePanel.add(daoLabel);
        selectPackagePanel.add(daoCombox);
        selectPackagePanel.add(daoImlLabel);
        selectPackagePanel.add(daoImplCombox);

        //domain类
        JButton domainClassName = new JButton("类名：");
        JTextField domianClass = new JTextField();

        //XML文件
        JButton xmlFileNameButton = new JButton("xml文件：");
        JTextField xmlFile = new JTextField();

        //service和dao的名称
        JButton nameOfServiceAndDaoButton = new JButton("service和dao：");
        JTextField nameOfServiceAndDao = new JTextField();

        domainClassName.addMouseListener(new SmartMouseAdapter(new GenerateMehodByDomainClassService(domianClass,xmlFile,nameOfServiceAndDao,this,centerPanel,daoCombox,daoImplCombox,serviceCombox,serviceImplCombox)));


        selectPackagePanel.add(domainClassName);
        selectPackagePanel.add(domianClass);

        selectPackagePanel.add(xmlFileNameButton);
        selectPackagePanel.add(xmlFile);

        selectPackagePanel.add(nameOfServiceAndDaoButton);
        selectPackagePanel.add(nameOfServiceAndDao);




        //**************



















        this.setSize(800, 600);
        //居中显示
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(selectPackagePanel,BorderLayout.NORTH);
        this.add(centerPanel,BorderLayout.CENTER);




        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1,2,3,3));
        JButton confirm = new JButton("确定");
        JButton cancel = new JButton("取消");
        southPanel.add(confirm);
        southPanel.add(cancel);
        confirm.addMouseListener(new SmartMouseAdapter(new GenerateFourClassService()));
        this.add(southPanel,BorderLayout.SOUTH);

        this.setVisible(true);
    }


    public static void main(String[] args) {


        new UIDAOService();




    }










}
