package com.luozhihuan.smartcode.service;

import com.luozhihuan.smartcode.model.DBSelectModel;
import com.luozhihuan.smartcode.model.Domain4CreateInfoModel;

import java.util.List;

/**
 * Created by luozhihuan on 15/6/13.
 */
public interface IGenerateSelectFieldByModelService {

    public List<DBSelectModel> generate(Domain4CreateInfoModel model);
}
