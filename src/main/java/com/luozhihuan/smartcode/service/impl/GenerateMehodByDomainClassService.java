package com.luozhihuan.smartcode.service.impl;

import com.luozhihuan.smartcode.constants.Constant;
import com.luozhihuan.smartcode.model.FourClassInfoModel;
import com.luozhihuan.smartcode.model.MethodModel;
import com.luozhihuan.smartcode.service.IMouseClickedService;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by luozhihuan on 15/6/14.
 */
public class GenerateMehodByDomainClassService implements IMouseClickedService{


    private JTextField domainClassName;
    private JFrame frame;
    private JPanel panel;
    private JTextField xmlField;
    private JTextField serviceAndDaoName;
    private JComboBox daoBox;
    private JComboBox daoImplBox;
    private JComboBox serviceBox;
    private JComboBox serviceImplBox;



    public GenerateMehodByDomainClassService(JTextField domainClassName,JTextField xmlField,JTextField serviceAndDaoName,JFrame frame,JPanel panel,JComboBox daoBox,JComboBox daoImplBox,JComboBox serviceBox,JComboBox serviceImplBox){
        this.domainClassName = domainClassName;
        this.frame = frame;
        this.panel = panel;
        this.xmlField = xmlField;
        this.serviceAndDaoName = serviceAndDaoName;
        this.daoBox = daoBox;
        this.daoImplBox = daoImplBox;
        this.serviceBox = serviceBox;
        this.serviceImplBox = serviceImplBox;
    }



    @Override
    public void mouseClicked() {
        String domainClass = domainClassName.getText();
        String xml = xmlField.getText();
        String nameOfServiceAndDao = serviceAndDaoName.getText();
        List<MethodModel> methodModels = getMethodModelList(domainClass,xml,nameOfServiceAndDao);
        panel.setLayout(new GridLayout(12,2,3,3));
        for(MethodModel methodModel: methodModels){
            methodModel.getMethodCheckBox().setSelected(true);
            panel.add(methodModel.getMethodCheckBox());
            panel.add(methodModel.getMethodTextField());
        }

        FourClassInfoModel fourClassInfoModel = new FourClassInfoModel();
        fourClassInfoModel.setNameInFourClassName(nameOfServiceAndDao);
        fourClassInfoModel.setDaoPackage(daoBox.getSelectedItem().toString());
        fourClassInfoModel.setDaoImpPackage(daoImplBox.getSelectedItem().toString());
        fourClassInfoModel.setServicePackage(serviceBox.getSelectedItem().toString());
        fourClassInfoModel.setServiceImplPackage(serviceImplBox.getSelectedItem().toString());
        fourClassInfoModel.setMethodModelList(methodModels);

        Constant.FOUR_CLASS_INFO_MODEL = fourClassInfoModel;



        frame.add(panel, BorderLayout.CENTER);


    }

    private List<MethodModel> getMethodModelList(String domainClass,String xmlFileName,String nameOfServiceAndDao){
        List<MethodModel> methodModels = new LinkedList<MethodModel>();
        JCheckBox methodCheckBoxDao = new JCheckBox();
        JCheckBox methodCheckBoxDaoImpl = new JCheckBox();
        JCheckBox methodCheckBoxService = new JCheckBox();
        JCheckBox methodCheckBoxServiceImpl = new JCheckBox();

        JTextField methodTextFieldDao = new JTextField();
        JTextField methodTextFieldDaoImpl = new JTextField();
        JTextField methodTextFieldService = new JTextField();
        JTextField methodTextFieldServiceImpl = new JTextField();


        String addMethodInDao = "public int add({Domain} {domainObject});";
        String addMethodInDaoImpl = "public int add({Domain} {domainObject}){" +
                "return super.add(\"{xmlFile}.add\", {domainObject});" +
                "}";
        String addMethodInService = "public boolean add({Domain} {domainObject});";
        String addMethodInServiceImpl = "@Override\n" +
                "    public boolean add({Domain} {domainObject}) {\n" +
                "        if ({nameOfServiceAndDao}DAO.add({domainObject}) == 1) {\n" +
                "            return true;\n" +
                "        }\n" +
                "        return false;\n" +
                "    }";


        addMethodInDao = addMethodInDao.replaceAll("\\{Domain\\}",domainClass);
        addMethodInDaoImpl = addMethodInDaoImpl.replaceAll("\\{Domain\\}",domainClass);
        addMethodInService = addMethodInService.replaceAll("\\{Domain\\}",domainClass);
        addMethodInServiceImpl = addMethodInServiceImpl.replaceAll("\\{Domain\\}",domainClass);


        String domainClassObject = domainClass.substring(0,1).toLowerCase()+domainClass.substring(1,domainClass.length());
        addMethodInDao = addMethodInDao.replaceAll("\\{domainObject\\}",domainClassObject);
        addMethodInDaoImpl = addMethodInDaoImpl.replaceAll("\\{domainObject\\}",domainClassObject);
        addMethodInDaoImpl = addMethodInDaoImpl.replaceAll("\\{xmlFile\\}",xmlFileName);
        addMethodInService = addMethodInService.replaceAll("\\{domainObject\\}",domainClassObject);
        addMethodInServiceImpl = addMethodInServiceImpl.replaceAll("\\{domainObject\\}",domainClassObject);
        addMethodInServiceImpl = addMethodInServiceImpl.replaceAll("\\{nameOfServiceAndDao\\}",nameOfServiceAndDao);



        methodTextFieldDao.setText(addMethodInDao);
        methodTextFieldDaoImpl.setText(addMethodInDaoImpl);
        methodTextFieldService.setText(addMethodInService);
        methodTextFieldServiceImpl.setText(addMethodInServiceImpl);

        MethodModel methodModelDao = new MethodModel(methodCheckBoxDao,methodTextFieldDao,MethodModel.DAO);
        MethodModel methodModelDaoImpl = new MethodModel(methodCheckBoxDaoImpl,methodTextFieldDaoImpl,MethodModel.DAO_IMPL);
        MethodModel methodModelService = new MethodModel(methodCheckBoxService,methodTextFieldService,MethodModel.SERVICE);
        MethodModel methodModelServiceImpl = new MethodModel(methodCheckBoxServiceImpl,methodTextFieldServiceImpl,MethodModel.SERVICE_IMPL);

        methodModels.add(methodModelDao);
        methodModels.add(methodModelDaoImpl);
        methodModels.add(methodModelService);
        methodModels.add(methodModelServiceImpl);



        //get(int id)
        JCheckBox methodGetByIdCheckBoxDao = new JCheckBox();
        JCheckBox methodGetByIdCheckBoxDaoImpl = new JCheckBox();
        JCheckBox methodGetByIdCheckBoxService = new JCheckBox();
        JCheckBox methodGetByIdCheckBoxServiceImpl = new JCheckBox();

        JTextField methodGetByIdTextFieldDao = new JTextField();
        JTextField methodGetByIdTextFieldDaoImpl = new JTextField();
        JTextField methodGetByIdTextFieldService = new JTextField();
        JTextField methodGetByIdTextFieldServiceImpl = new JTextField();


        String getByIdMethodInDao = "public {Domain} getById(int id);";
        String getByIdMethodInDaoImpl = "@Override\n" +
                "    public {Domain} getById(int id) {\n" +
                "        return super.get(\"{xmlFile}.get\", id);\n" +
                "    }";

        String getByIdMethodInService = "public {Domain} getById(int id);";
        String getByIdMethodInServiceImpl = "@Override\n" +
                "    public {Domain} getById(int id) {\n" +
                "        {Domain} {domainObject} = ({Domain}) RemoteCacheUtils.get(genIdKey(id));\n" +
                "        if ({domainObject} == null) {\n" +
                "            {domainObject} = {nameOfServiceAndDao}DAO.getById(id);\n" +
                "            if ({domainObject} != null) {\n" +
                "                RemoteCacheUtils.put(genIdKey(id), {domainObject});\n" +
                "            }\n" +
                "        }\n" +
                "        return {domainObject};\n" +
                "    }";


        getByIdMethodInDao = getByIdMethodInDao.replaceAll("\\{Domain\\}",domainClass);
        getByIdMethodInDaoImpl = getByIdMethodInDaoImpl.replaceAll("\\{Domain\\}",domainClass);
        getByIdMethodInService = getByIdMethodInService.replaceAll("\\{Domain\\}",domainClass);
        getByIdMethodInServiceImpl = getByIdMethodInServiceImpl.replaceAll("\\{Domain\\}",domainClass);


//        domainClass = domainClass.substring(0,1).toLowerCase()+domainClass.substring(1,domainClass.length());
        getByIdMethodInDao = getByIdMethodInDao.replaceAll("\\{domainObject\\}",domainClassObject);
        getByIdMethodInDaoImpl = getByIdMethodInDaoImpl.replaceAll("\\{domainObject\\}",domainClassObject);
        getByIdMethodInDaoImpl = getByIdMethodInDaoImpl.replaceAll("\\{xmlFile\\}",xmlFileName);
        getByIdMethodInService = getByIdMethodInService.replaceAll("\\{domainObject\\}",domainClassObject);
        getByIdMethodInServiceImpl = getByIdMethodInServiceImpl.replaceAll("\\{domainObject\\}",domainClassObject);
        getByIdMethodInServiceImpl = getByIdMethodInServiceImpl.replaceAll("\\{nameOfServiceAndDao\\}",nameOfServiceAndDao);



        methodGetByIdTextFieldDao.setText(getByIdMethodInDao);
        methodGetByIdTextFieldDaoImpl.setText(getByIdMethodInDaoImpl);
        methodGetByIdTextFieldService.setText(getByIdMethodInService);
        methodGetByIdTextFieldServiceImpl.setText(getByIdMethodInServiceImpl);

        MethodModel methodGetByIdModelDao = new MethodModel(methodGetByIdCheckBoxDao,methodGetByIdTextFieldDao,MethodModel.DAO);
        MethodModel methodGetByIdModelDaoImpl = new MethodModel(methodGetByIdCheckBoxDaoImpl,methodGetByIdTextFieldDaoImpl,MethodModel.DAO_IMPL);
        MethodModel methodGetByIdModelService = new MethodModel(methodGetByIdCheckBoxService,methodGetByIdTextFieldService,MethodModel.SERVICE);
        MethodModel methodGetByIdModelServiceImpl = new MethodModel(methodGetByIdCheckBoxServiceImpl,methodGetByIdTextFieldServiceImpl,MethodModel.SERVICE_IMPL);

        methodModels.add(methodGetByIdModelDao);
        methodModels.add(methodGetByIdModelDaoImpl);
        methodModels.add(methodGetByIdModelService);
        methodModels.add(methodGetByIdModelServiceImpl);



        //getList()

        //Update()
        JCheckBox methodUpdateCheckBoxDao = new JCheckBox();
        JCheckBox methodUpdateCheckBoxDaoImpl = new JCheckBox();
        JCheckBox methodUpdateCheckBoxService = new JCheckBox();
        JCheckBox methodUpdateCheckBoxServiceImpl = new JCheckBox();

        JTextField methodUpdateTextFieldDao = new JTextField();
        JTextField methodUpdateTextFieldDaoImpl = new JTextField();
        JTextField methodUpdateTextFieldService = new JTextField();
        JTextField methodUpdateTextFieldServiceImpl = new JTextField();


        String updateMethodInDao = "public int update({Domain} {domainObject});";
        String updateMethodInDaoImpl = "@Override\n" +
                "    public int update({Domain} {domainObject}) {\n" +
                "        return super.update(\"{xmlFile}.update\", {domainObject});\n" +
                "    }";
        String updateMethodInService = "public boolean update({Domain} {domainObject});";
        String updateMethodInServiceImpl = "@Override\n" +
                "    public boolean update({Domain} {domainObject}) {\n" +
                "        if ({nameOfServiceAndDao}DAO.update({domainObject}) == 1) {\n" +
                "            RemoteCacheUtils.delete(genIdKey({domainObject}.getId));\n" +
                "            return true;\n" +
                "        }\n" +
                "        return false;\n" +
                "    }";


        updateMethodInDao = updateMethodInDao.replaceAll("\\{Domain\\}",domainClass);
        updateMethodInDaoImpl = updateMethodInDaoImpl.replaceAll("\\{Domain\\}",domainClass);
        updateMethodInService = updateMethodInService.replaceAll("\\{Domain\\}",domainClass);
        updateMethodInServiceImpl = updateMethodInServiceImpl.replaceAll("\\{Domain\\}",domainClass);


//        domainClass = domainClass.substring(0,1).toLowerCase()+domainClass.substring(1,domainClass.length());
        updateMethodInDao = updateMethodInDao.replaceAll("\\{domainObject\\}",domainClassObject);
        updateMethodInDaoImpl = updateMethodInDaoImpl.replaceAll("\\{domainObject\\}",domainClassObject);
        updateMethodInDaoImpl = updateMethodInDaoImpl.replaceAll("\\{xmlFile\\}",xmlFileName);
        updateMethodInService = updateMethodInService.replaceAll("\\{domainObject\\}",domainClassObject);
        updateMethodInServiceImpl = updateMethodInServiceImpl.replaceAll("\\{domainObject\\}",domainClassObject);
        updateMethodInServiceImpl = updateMethodInServiceImpl.replaceAll("\\{nameOfServiceAndDao\\}",nameOfServiceAndDao);



        methodUpdateTextFieldDao.setText(updateMethodInDao);
        methodUpdateTextFieldDaoImpl.setText(updateMethodInDaoImpl);
        methodUpdateTextFieldService.setText(updateMethodInService);
        methodUpdateTextFieldServiceImpl.setText(updateMethodInServiceImpl);

        MethodModel methodUpdateModelDao = new MethodModel(methodUpdateCheckBoxDao,methodUpdateTextFieldDao,MethodModel.DAO);
        MethodModel methodUpdateModelDaoImpl = new MethodModel(methodUpdateCheckBoxDaoImpl,methodUpdateTextFieldDaoImpl,MethodModel.DAO_IMPL);
        MethodModel methodUpdateModelService = new MethodModel(methodUpdateCheckBoxService,methodUpdateTextFieldService,MethodModel.SERVICE);
        MethodModel methodUpdateModelServiceImpl = new MethodModel(methodUpdateCheckBoxServiceImpl,methodUpdateTextFieldServiceImpl,MethodModel.SERVICE_IMPL);

        methodModels.add(methodUpdateModelDao);
        methodModels.add(methodUpdateModelDaoImpl);
        methodModels.add(methodUpdateModelService);
        methodModels.add(methodUpdateModelServiceImpl);


        return methodModels;
    }
}
