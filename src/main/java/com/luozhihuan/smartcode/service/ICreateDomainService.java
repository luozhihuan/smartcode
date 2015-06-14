package com.luozhihuan.smartcode.service;

import com.luozhihuan.smartcode.model.Domain4CreateInfoModel;

import java.util.List;

/**
 * Created by luozhihuan on 15/6/6.
 */
public interface ICreateDomainService {

    /**
     * 创建一个Domain类
     * @param createInfoModel
     */
    public void createDomain(Domain4CreateInfoModel createInfoModel);
}
