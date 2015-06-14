package com.luozhihuan.smartcode.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by luozhihuan on 15/6/7.
 */
public class Domain4CreateInfoModel {


    public Domain4CreateInfoModel(){
        domainInfoModels = new LinkedList<DomainInfoModel>();
    }

    //包名
    private String packageName;
    //类名
    private String domainClassName;
    //类文件完整路径名
    private String domainClassPath;
    //字段信息链表
    private List<DomainInfoModel> domainInfoModels;
    //项目路径
    private String projectPath;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDomainClassName() {
        return domainClassName;
    }

    public void setDomainClassName(String domainClassName) {
        this.domainClassName = domainClassName;
    }

    public String getDomainClassPath() {
        return domainClassPath;
    }

    public void setDomainClassPath(String domainClassPath) {
        this.domainClassPath = domainClassPath;
    }

    public List<DomainInfoModel> getDomainInfoModels() {
        return domainInfoModels;
    }

    public void setDomainInfoModels(List<DomainInfoModel> domainInfoModels) {
        this.domainInfoModels = domainInfoModels;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }
}
