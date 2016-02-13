package com.luozhihuan.smartcode.model;

import java.util.List;

/**
 * Created by luozhihuan on 15/6/14.
 */
public class FourClassInfoModel {

    private String nameInFourClassName;
    private String daoPackage;
    private String daoImpPackage;
    private String servicePackage;
    private String serviceImplPackage;
    List<MethodModel> methodModelList;

    public String getNameInFourClassName() {
        return nameInFourClassName;
    }

    public void setNameInFourClassName(String nameInFourClassName) {
        this.nameInFourClassName = nameInFourClassName;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public String getDaoImpPackage() {
        return daoImpPackage;
    }

    public void setDaoImpPackage(String daoImpPackage) {
        this.daoImpPackage = daoImpPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getServiceImplPackage() {
        return serviceImplPackage;
    }

    public void setServiceImplPackage(String serviceImplPackage) {
        this.serviceImplPackage = serviceImplPackage;
    }

    public List<MethodModel> getMethodModelList() {
        return methodModelList;
    }

    public void setMethodModelList(List<MethodModel> methodModelList) {
        this.methodModelList = methodModelList;
    }
}
