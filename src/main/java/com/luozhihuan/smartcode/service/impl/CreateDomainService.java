package com.luozhihuan.smartcode.service.impl;

import com.luozhihuan.smartcode.constants.FieldNameConstant;
import com.luozhihuan.smartcode.model.Domain4CreateInfoModel;
import com.luozhihuan.smartcode.model.DomainInfoModel;
import com.luozhihuan.smartcode.service.ICreateDomainService;

import java.io.*;
import java.util.*;

/**
 * Created by luozhihuan on 15/6/6.
 */
public class CreateDomainService implements ICreateDomainService{


    private final String MAVEN_PATH = "/src/main/java/";
    //换行
    private final String LINE_FEED = "\n";
    //空格
    private final String SPACE = " ";

    public static void main(String[] args) {
        CreateDomainService service = new CreateDomainService();

        service.createDomain(null);
    }

    public void createDomain(Domain4CreateInfoModel createInfoModel) {
        String projectPath = null;
        String packageName = createInfoModel.getPackageName();
        File directory = new File("");// 参数为空
        projectPath = createInfoModel.getProjectPath();
        packageName = packageName.replaceAll("\\.","/");
        createInfoModel.setDomainClassPath(projectPath + MAVEN_PATH + packageName + "/" + createInfoModel.getDomainClassName());



        //写文件
        try {
            File f = new File(createInfoModel.getDomainClassPath()+".java");
            if (f.exists()) {
                System.out.println("文件存在");
            } else {
                System.out.println("文件不存在，正在创建...");
                if (f.createNewFile()) {
                    System.out.println("文件创建成功！");
                } else {
                    System.out.println("文件创建失败！");
                }
            }
            String classContent = genenrateDomainClassContent(createInfoModel);
            System.out.println("文件内容：" + classContent);
            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(classContent);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String genenrateDomainClassContent(Domain4CreateInfoModel createInfoModel){
        List<DomainInfoModel> domainInfoModels = createInfoModel.getDomainInfoModels();

        StringBuilder importContent = new StringBuilder();
        StringBuilder packageContent = new StringBuilder();
        StringBuilder classNameContent = new StringBuilder();
        StringBuilder fieldNameContent = new StringBuilder();
        StringBuilder getSetMethodContent = new StringBuilder();

        packageContent.append("package ");
        packageContent.append(createInfoModel.getPackageName());
        packageContent.append(";");
        packageContent.append(LINE_FEED);

        classNameContent.append("public class ");
        classNameContent.append(createInfoModel.getDomainClassName());
        classNameContent.append("{");
        classNameContent.append(LINE_FEED);

        Set<String> importSet = new HashSet<String>();


        for(DomainInfoModel infoModel:domainInfoModels){
            String fieldString = "private {fieldType} {fieldName};";
            String getMethod = "public {fieldType} get{methodName}(){return {fieldName};}";
            String setMethod = "public void set{methodName}({fieldType} {fieldName}){this.{fieldName} = {fieldName};}";
            String fieldType = null;

            if(FieldNameConstant.INT_FIELD_NAME.equals(infoModel.getColumnClassName())){
                fieldType = FieldNameConstant.INT_NAME;
            }else if (FieldNameConstant.DOUBLE_FIELD_NAME.equals(infoModel.getColumnClassName())){
                fieldType = FieldNameConstant.DOUBLE_NAME;
            }else if (FieldNameConstant.LONG_FIELD_NAME.equals(infoModel.getColumnClassName())){
                fieldType = FieldNameConstant.LONG_NAME;
            }else if (FieldNameConstant.SQL_TIMESTAMP_FIELD_NAME.equals(infoModel.getColumnClassName())){
                fieldType = FieldNameConstant.DATE_NAME;
                importSet.add(FieldNameConstant.DATE_NAME);
            }else if (FieldNameConstant.STRING_FIELD_NAME.equals(infoModel.getColumnClassName())){
                fieldType = FieldNameConstant.STRING_NAME;
            }

            //设置类的变量
            fieldString = fieldString.replaceAll("\\{fieldType\\}",fieldType);
            fieldString = fieldString.replaceAll("\\{fieldName\\}",infoModel.getName());
            fieldNameContent.append(fieldString);
            fieldNameContent.append(LINE_FEED);


            //设置变量对应的get方法
            getMethod = getMethod.replaceAll("\\{fieldType\\}",fieldType);
            String methodNameWithFirstWordUpper = infoModel.getName().substring(0,1).toUpperCase()+infoModel.getName().substring(1,infoModel.getName().length()).toLowerCase();
            getMethod = getMethod.replaceAll("\\{methodName\\}",methodNameWithFirstWordUpper);
            getMethod = getMethod.replaceAll("\\{fieldName\\}",infoModel.getName());
            getSetMethodContent.append(getMethod);
            getSetMethodContent.append(LINE_FEED);

            //设置变量对应的set方法
            setMethod = setMethod.replaceAll("\\{methodName\\}",methodNameWithFirstWordUpper);
            setMethod = setMethod.replaceAll("\\{fieldType\\}",fieldType);
            setMethod = setMethod.replaceAll("\\{fieldName\\}",infoModel.getName());
            getSetMethodContent.append(setMethod);
            getSetMethodContent.append(LINE_FEED);

        }

        //写入import部分代码

        String importTypeName = null;
        for(String fieldTypeName : importSet){
            String importString = "import {classPackage};";
            if(FieldNameConstant.DATE_NAME.equals(fieldTypeName)){
                importTypeName = FieldNameConstant.DATE_FIELD_NAME;
            }
            importString = importString.replaceAll("\\{classPackage\\}", importTypeName);
            importContent.append(importString);
            importContent.append(LINE_FEED);
        }




        StringBuilder classContent = new StringBuilder();
        classContent.append(packageContent).append(importContent).append(classNameContent).append(fieldNameContent).append(getSetMethodContent);
        classContent.append("}");

        return classContent.toString();

    }






}
