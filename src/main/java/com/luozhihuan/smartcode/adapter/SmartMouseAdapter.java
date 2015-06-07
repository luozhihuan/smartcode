package com.luozhihuan.smartcode.adapter;

import com.luozhihuan.smartcode.service.IMouseClickedService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by luozhihuan on 15/6/6.
 */
public class SmartMouseAdapter extends MouseAdapter {

    IMouseClickedService mouseClickedService;

    public SmartMouseAdapter(IMouseClickedService mouseClickedService){
        this.mouseClickedService = mouseClickedService;
    }




    @Override
    public void mouseClicked(MouseEvent e) {

        mouseClickedService.mouseClicked();
    }
}
