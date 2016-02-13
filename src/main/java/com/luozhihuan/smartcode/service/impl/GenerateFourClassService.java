package com.luozhihuan.smartcode.service.impl;

import com.luozhihuan.smartcode.constants.Constant;
import com.luozhihuan.smartcode.model.FourClassInfoModel;
import com.luozhihuan.smartcode.model.MethodModel;
import com.luozhihuan.smartcode.service.IMouseClickedService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * Created by luozhihuan on 15/6/14.
 */
public class GenerateFourClassService implements IMouseClickedService {

    private List<MethodModel> methodModels;

    //换行
    private final String LINE_FEED = "\n";

//    public GenerateFourClassService(List<MethodModel> methodModels) {
//        this.methodModels = methodModels;
//
//    }


    @Override
    public void mouseClicked() {
        FourClassInfoModel fourClassInfoModel = Constant.FOUR_CLASS_INFO_MODEL;
        String daoContent = "package {daoPackage};" + LINE_FEED + "public interface I{name}DAO{" + LINE_FEED + "{daoMethods}" + LINE_FEED + "}";
        String daoImplContent = "package {daoImplPackage};" + LINE_FEED + "Repository(\"\")" + LINE_FEED + "public class {name}DAOImpl extends BaseDAO<> implements I{name}DAO{" + LINE_FEED + "{daoImplMethods}" + LINE_FEED + "}";
        String serviceContent = "package {servicePackage};" + LINE_FEED + "public interface I{name}Service{"
                + LINE_FEED + "{serviceMethods}" + LINE_FEED + "}";
        String serviceImplContent = "package {serviceImplPackage};" + LINE_FEED + "public class {name}ServiceImpl implements I{name}Service{"
                + LINE_FEED + "{serviceImplMethods}" + LINE_FEED + "}";

        String daoMethods = "";
        String daoImplMethods = "";
        String serviceMethods = "";
        String serviceImplMethods = "";


        List<MethodModel> methodModels = fourClassInfoModel.getMethodModelList();
        for (MethodModel methodInfo : methodModels) {
            if (methodInfo.getLevel() == MethodModel.DAO) {
                daoMethods += methodInfo.getMethodTextField().getText() + LINE_FEED;
            } else if (methodInfo.getLevel() == MethodModel.DAO_IMPL) {
                daoImplMethods += methodInfo.getMethodTextField().getText() + LINE_FEED;
            } else if (methodInfo.getLevel() == MethodModel.SERVICE) {
                serviceMethods += methodInfo.getMethodTextField().getText() + LINE_FEED;
            } else if (methodInfo.getLevel() == MethodModel.SERVICE_IMPL) {
                serviceImplMethods += methodInfo.getMethodTextField().getText() + LINE_FEED;
            }
        }


        daoContent = daoContent.replaceAll("\\{daoPackage\\}", fourClassInfoModel.getDaoPackage());
        daoContent = daoContent.replaceAll("\\{name\\}", fourClassInfoModel.getNameInFourClassName());
        daoContent = daoContent.replaceAll("\\{daoMethods\\}", daoMethods);

        daoImplContent = daoImplContent.replaceAll("\\{daoImplPackage\\}", fourClassInfoModel.getDaoImpPackage());
        daoImplContent = daoImplContent.replaceAll("\\{name\\}", fourClassInfoModel.getNameInFourClassName());
        daoImplContent = daoImplContent.replaceAll("\\{daoImplMethods\\}", daoImplMethods);

        serviceContent = serviceContent.replaceAll("\\{servicePackage\\}", fourClassInfoModel.getServicePackage());
        serviceContent = serviceContent.replaceAll("\\{name\\}", fourClassInfoModel.getNameInFourClassName());
        serviceContent = serviceContent.replaceAll("\\{serviceMethods\\}", serviceMethods);

        serviceImplContent = serviceImplContent.replaceAll("\\{serviceImplPackage\\}", fourClassInfoModel.getServiceImplPackage());
        serviceImplContent = serviceImplContent.replaceAll("\\{name\\}", fourClassInfoModel.getNameInFourClassName());
        serviceImplContent = serviceImplContent.replaceAll("\\{serviceImplMethods\\}", serviceImplMethods);


        String daoPackage = fourClassInfoModel.getDaoPackage();
        daoPackage = daoPackage.replaceAll("\\.", "/");

        String daoImplPackage = fourClassInfoModel.getDaoImpPackage();
        daoImplPackage = daoImplPackage.replaceAll("\\.", "/");

        String servicePackage = fourClassInfoModel.getServicePackage();
        servicePackage = servicePackage.replaceAll("\\.", "/");

        String serviceImplPackage = fourClassInfoModel.getServiceImplPackage();
        serviceImplPackage = serviceImplPackage.replaceAll("\\.", "/");


        String fileName = fourClassInfoModel.getNameInFourClassName();

        wirteFile(Constant.PROJECT_PATH + "/src/main/java/" + daoPackage, daoContent, "I" + fileName + "DAO");
        wirteFile(Constant.PROJECT_PATH + "/src/main/java/" + daoImplPackage, daoImplContent, fileName + "DAOImpl");
        wirteFile(Constant.PROJECT_PATH + "/src/main/java/" + servicePackage, serviceContent, "I" + fileName + "Service");
        wirteFile(Constant.PROJECT_PATH + "/src/main/java/" + serviceImplPackage, serviceImplContent, fileName + "ServiceImpl");


    }


    public void wirteFile(String filePath, String classContent, String fileName) {
        System.out.println("文件路径" + filePath);
        //写文件
        try {
            File f = new File(filePath + "/" + fileName + ".java");
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
//            String classContent = genenrateDomainClassContent(createInfoModel);
            System.out.println("文件内容：" + classContent);
            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(classContent);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
