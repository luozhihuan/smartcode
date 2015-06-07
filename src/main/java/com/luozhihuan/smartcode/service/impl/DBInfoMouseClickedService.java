package com.luozhihuan.smartcode.service.impl;

import com.luozhihuan.smartcode.model.DBInfoModel;
import com.luozhihuan.smartcode.model.DBUIInfoModel;
import com.luozhihuan.smartcode.model.Domain4CreateInfoModel;
import com.luozhihuan.smartcode.model.DomainInfoModel;
import com.luozhihuan.smartcode.service.ICreateDomainService;
import com.luozhihuan.smartcode.service.IMouseClickedService;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by luozhihuan on 15/6/6.
 */
public class DBInfoMouseClickedService implements IMouseClickedService {

    private DBUIInfoModel dbuiInfoModel;

    private DBInfoModel dbInfoModel;

    private ICreateDomainService createDomainService;

    public DBInfoMouseClickedService(DBUIInfoModel dbuiInfoModel) {
        this.dbuiInfoModel = dbuiInfoModel;
        createDomainService = new CreateDomainService();

    }

    public void mouseClicked() {
        this.dbInfoModel = new DBInfoModel(dbuiInfoModel);
        Connection conn = null;
        PreparedStatement stmt = null;
        LinkedList<DomainInfoModel> domainInfoModels = null;
        Domain4CreateInfoModel createInfoModel = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + dbInfoModel.getDbAddress();
            String user = dbInfoModel.getUserName();
            String pass = dbInfoModel.getPassword();
            conn = DriverManager.getConnection(url, user, pass);
            String sql = "select * from {tableName}";
            sql = sql.replaceAll("\\{tableName\\}", dbInfoModel.getTableName());

            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();

            domainInfoModels = new LinkedList<DomainInfoModel>();

            createInfoModel = new Domain4CreateInfoModel();


            createInfoModel.setPackageName("com.luozhihuan.smartcode.test");

            for (int i = 1; i <= data.getColumnCount(); i++) {
                // 获得指定列的列名
                String columnName = data.getColumnName(i);
                // 对应数据类型的类
                String columnClassName = data.getColumnClassName(i);

                System.out.println("列名"+columnName);
                System.out.println("类型"+columnClassName);


                createInfoModel.getDomainInfoModels().add(new DomainInfoModel(columnName,columnClassName));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //待删除，这里假设设置了生产的名称
        createInfoModel.setDomainClassName("Test");
        createDomainService.createDomain(createInfoModel);


    }

}
