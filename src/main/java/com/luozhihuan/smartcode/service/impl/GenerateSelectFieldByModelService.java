package com.luozhihuan.smartcode.service.impl;

import com.luozhihuan.smartcode.model.DBSelectModel;
import com.luozhihuan.smartcode.model.Domain4CreateInfoModel;
import com.luozhihuan.smartcode.model.DomainInfoModel;
import com.luozhihuan.smartcode.service.IGenerateSelectFieldByModelService;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by luozhihuan on 15/6/13.
 */
public class GenerateSelectFieldByModelService implements IGenerateSelectFieldByModelService {


    @Override
    public List<DBSelectModel> generate(Domain4CreateInfoModel model) {
        List<DBSelectModel> dbSelectModels = new LinkedList<DBSelectModel>();

        List<DomainInfoModel> domainInfoModels = model.getDomainInfoModels();
        for (DomainInfoModel infoModel : domainInfoModels) {
            String fieldName = infoModel.getName();
            String typeClassName = infoModel.getColumnClassName();
            DBSelectModel selectModel = new DBSelectModel();
            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected(true);
            selectModel.setIsSelectedCheckbox(checkBox);
            JTextField field = new JTextField("字段名");
            selectModel.setFieldText(field);
            selectModel.setFieldComboBox(getComboBoxByFieldName(fieldName));
            JTextField typeField = new JTextField("类型");
            selectModel.setFieldTypeText(typeField);
            selectModel.setFieldTypeComboBox(getFieldTypeComboBox(typeClassName));
            dbSelectModels.add(selectModel);

        }

        return dbSelectModels;
    }


    public JComboBox getComboBoxByFieldName(String fieldName) {
        String[] fieldsName = fieldName.split("_");
        JComboBox fieldChomboBox = new JComboBox();
        String completeName = "";
        for (String name : fieldsName) {
            completeName += name;
            fieldChomboBox.addItem(name);
        }
        if(fieldsName!=null && fieldsName.length > 1){
            fieldChomboBox.addItem(completeName);
        }
        return fieldChomboBox;
    }

    public JComboBox getFieldTypeComboBox(String typeClassName) {
        JComboBox fieldChomboBox = new JComboBox();
        fieldChomboBox.addItem(typeClassName);
        return fieldChomboBox;
    }
}
