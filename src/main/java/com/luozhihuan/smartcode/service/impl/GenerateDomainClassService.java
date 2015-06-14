package com.luozhihuan.smartcode.service.impl;

import com.luozhihuan.smartcode.constants.Constant;
import com.luozhihuan.smartcode.model.DBSelectModel;
import com.luozhihuan.smartcode.model.Domain4CreateInfoModel;
import com.luozhihuan.smartcode.model.DomainInfoModel;
import com.luozhihuan.smartcode.service.ICreateDomainService;
import com.luozhihuan.smartcode.service.IMouseClickedService;

import javax.swing.*;
import java.util.List;

/**
 * Created by luozhihuan on 15/6/13.
 */
public class GenerateDomainClassService implements IMouseClickedService {

    private List<DBSelectModel> dbSelectModels;
    private ICreateDomainService createDomainService;
    private JComboBox packagesComboBox;
    private JTextField className;

    public GenerateDomainClassService(List<DBSelectModel> dbSelectModels,JComboBox packagesComboBox,JTextField className){
        this.dbSelectModels = dbSelectModels;
        this.createDomainService = new CreateDomainService();
        this.packagesComboBox = packagesComboBox;
        this.className = className;
    }

    @Override
    public void mouseClicked() {
        //将dbSelectModels转换为一个Domain4CreateInfoModel对象

        createDomainService.createDomain(convertTo(dbSelectModels));
    }

    public Domain4CreateInfoModel convertTo(List<DBSelectModel> dbSelectModels){
        Domain4CreateInfoModel infoModel = new Domain4CreateInfoModel();
        infoModel.setPackageName(packagesComboBox.getSelectedItem().toString());
        infoModel.setProjectPath(Constant.PROJECT_PATH);

//        infoModel.set
        for(DBSelectModel selectModel:dbSelectModels){
            if(selectModel.getIsSelectedCheckbox().isSelected()){
                DomainInfoModel domainInfoModel = new DomainInfoModel();
                domainInfoModel.setName(selectModel.getFieldComboBox().getSelectedItem().toString());
                domainInfoModel.setColumnClassName(selectModel.getFieldTypeComboBox().getSelectedItem().toString());
                infoModel.getDomainInfoModels().add(domainInfoModel);
            }
        }

        infoModel.setDomainClassName(className.getText());
//        infoModel.setPackageName("com.luozhihuan.smartcode.test");
//        infoModel.setDomainClassPath("");
        return infoModel;
    }


}
