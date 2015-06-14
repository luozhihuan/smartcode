package com.luozhihuan.smartcode.UI;

import com.luozhihuan.smartcode.adapter.SmartMouseAdapter;
import com.luozhihuan.smartcode.constants.Constant;
import com.luozhihuan.smartcode.model.DBSelectModel;
import com.luozhihuan.smartcode.model.Domain4CreateInfoModel;
import com.luozhihuan.smartcode.service.IGenerateSelectFieldByModelService;
import com.luozhihuan.smartcode.service.impl.DBInfoMouseClickedService;
import com.luozhihuan.smartcode.service.impl.GenerateDomainClassService;
import com.luozhihuan.smartcode.service.impl.GenerateSelectFieldByModelService;
import com.luozhihuan.smartcode.service.impl.PackageFilterClickedService;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

/**
 * Created by luozhihuan on 15/6/13.
 */
public class UIDBDomainDisplay extends JFrame {
    Domain4CreateInfoModel createInfoModel = null;
    private IGenerateSelectFieldByModelService generateSelectFieldByModelService;
    private List<DBSelectModel> dbSelectModels = null;

    public UIDBDomainDisplay(Domain4CreateInfoModel createInfoModel) {
        super("发射器");
        this.createInfoModel = createInfoModel;
        generateSelectFieldByModelService = new GenerateSelectFieldByModelService();
        JPanel dbpane = displayDBField(generateSelectFieldByModelService);
        this.setLayout(new BorderLayout());
        this.add(dbpane, BorderLayout.CENTER);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1, 3, 3, 3));

        JTextField className = new JTextField();
        JLabel classNameLabel = new JLabel("类名:");


        JComboBox packagesComboBox = new JComboBox();
        Set<String> packageSet = getAllPackages(Constant.PROJECT_PATH);
        for (String packageName : packageSet) {
            packagesComboBox.addItem(packageName);
        }

//        filterPackageButton.addMouseListener(new SmartMouseAdapter(new PackageFilterClickedService(northPanel, packageSet, filterTextField, packagesComboBox, filterPackageButton)));

        northPanel.add(classNameLabel);
        northPanel.add(className);
        northPanel.add(packagesComboBox);


        this.add(northPanel, BorderLayout.NORTH);
//        northPanel.add()


        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1, 2, 3, 3));
        JButton confirm = new JButton("确定");
        confirm.addMouseListener(new SmartMouseAdapter(new GenerateDomainClassService(dbSelectModels, packagesComboBox,className)));
        southPanel.add(confirm);
        southPanel.add(new JButton("取消"));
        this.add(southPanel, BorderLayout.SOUTH);


        this.setSize(600, 400);
        //居中显示
        this.setLocationRelativeTo(null);
        //界面启动后点击关闭，程序将完全关闭
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public JPanel displayDBField(IGenerateSelectFieldByModelService generateSelectFieldByModelService) {
//        JScrollPane mainPanel = new JScrollPane();
        JPanel dbPanel = new JPanel();

        this.dbSelectModels = generateSelectFieldByModelService.generate(createInfoModel);
        int rows = dbSelectModels.size();
//        setGridLayout(rows, 1, 5, 5);
        dbPanel.setLayout(new GridLayout(rows, 1, 5, 5));
        for (DBSelectModel model : dbSelectModels) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 5, 5, 5));
            panel.add(model.getIsSelectedCheckbox());
            panel.add(model.getFieldText());
            panel.add(model.getFieldComboBox());
            panel.add(model.getFieldTypeText());
            panel.add(model.getFieldTypeComboBox());
            dbPanel.add(panel);
//            mainPanel.add(panel);
        }
//        this.add(mainPanel);
        return dbPanel;

    }

    /**
     * @param rows 行数
     * @param cols 列数
     * @param hgap 横向间距，单位：像素
     * @param vgap 竖向间距，单位：像素
     */
    public void setGridLayout(int rows, int cols, int hgap, int vgap) {
        this.setLayout(new GridLayout(rows, cols, hgap, vgap));
    }

    /**
     * 方法有待改进
     *
     * @param packages
     * @param packageNameList
     * @param projectRoot
     * @return
     */
    public static Set<String> getProjectAllPackages(Set<String> packages, LinkedList<String> packageNameList, String projectRoot) {
        String path = "";
        String packageName = null;
        for (String fileName : packageNameList) {
            path += "/" + fileName;
            if (packageName == null) {
                packageName = fileName;
            } else {
                packageName += "." + fileName;
            }
        }
        String finalPath = projectRoot + path;

        File file = new File(finalPath);
        File[] childrenFiles = file.listFiles();
        //判断该层目录中是否有java文件，有的话就需要把该层的包加入到packages链表中作为报名储备
        boolean hasJavaFile = false;
        for (File childFile : childrenFiles) {
            if (childFile.isFile() && childFile.getName().endsWith(".java")) {
                hasJavaFile = true;
                break;
            }
        }
        boolean alreadyAdd = false;
        for (File childFile : childrenFiles) {
            if (childFile.isDirectory()) {
                if (hasJavaFile) {
                    packages.add(packageName + "." + childFile.getName());
                    packages.add(packageName);
                    alreadyAdd = true;
                }
                packageNameList.add(childFile.getName());
                getProjectAllPackages(packages, packageNameList, projectRoot);
                packageNameList.remove(packageNameList.size() - 1);
            }
        }
        if (hasJavaFile && !alreadyAdd) {
            packages.add(packageName);
        }

        return packages;
    }


    public Set<String> getAllPackages(String projectRootPath) {
        Set<String> packages = new HashSet<String>();
        LinkedList<String> packageNameList = new LinkedList<String>();
        String projectRoot = projectRootPath + "/src/main/java";
        return getProjectAllPackages(packages, packageNameList, projectRoot);
    }
}
