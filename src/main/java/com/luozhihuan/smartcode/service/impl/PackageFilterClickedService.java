package com.luozhihuan.smartcode.service.impl;

import com.luozhihuan.smartcode.service.IMouseClickedService;

import javax.swing.*;
import java.util.Set;

/**
 * Created by luozhihuan on 15/6/14.
 */
public class PackageFilterClickedService implements IMouseClickedService {

    private JPanel panel;
    private Set<String> packageSet;
    private JTextField filterTextField;
    private JComboBox packagesComboBox;
    private JButton filterPackageButton;


    public PackageFilterClickedService(JPanel northPanel, Set<String> packageSet, JTextField filterTextField, JComboBox packagesComboBox, JButton filterPackageButton) {
        this.panel = northPanel;
        this.packageSet = packageSet;
        this.filterTextField = filterTextField;
        this.packagesComboBox = packagesComboBox;
        this.filterPackageButton = filterPackageButton;
    }

    @Override
    public void mouseClicked() {
////        JComboBox comboBox = new JComboBox();
//        String filterWord = filterTextField.getText();
//        int size = packagesComboBox.getItemCount();
//        for (int i = 0; i < size; i++) {
//            String name = packagesComboBox.getItemAt(i).toString();
//            System.out.println(name);
//            if(!name.contains(filterWord)){
//                packagesComboBox.remove(i);
//            }
//        }
//
//
//
////        for (String packageName : packageSet) {
////            if (packageName.contains(filterWord)) {
////                packagesComboBox.addItem(packageName);
////            }
////        }
////        packagesComboBox.addItem("luo");
//
////        panel.add(packagesComboBox,2);
////        panel.add(comboBox,2);
////        panel.updateUI();
////        panel.removeAll();
////        panel.add(filterTextField);
////        panel.add(filterPackageButton);
////        panel.add(packagesComboBox);
//        panel.repaint();

    }
}
