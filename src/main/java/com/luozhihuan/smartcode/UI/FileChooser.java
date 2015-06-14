package com.luozhihuan.smartcode.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FileChooser extends JFrame implements ActionListener{
	public final static int START_DIRECT = 1;
	public final static int START_TEST = 0;
	public final static int START_NOTHING = 2;

	JButton open=null;
	public static void main(String[] args) {
		new FileChooser(START_DIRECT);
	}
	public FileChooser(int startMethod){
		if(startMethod == START_TEST) {
			open = new JButton("open");
			this.add(open);
			this.setBounds(400, 200, 100, 100);
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			open.addActionListener(this);
		}else if(startMethod == START_DIRECT){
			System.out.println(getChooseFilePath());
		}
	}

	public FileChooser(){}

	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.println(getChooseFilePath());
	}

	public String getChooseFilePath(){
		JFileChooser jfc=new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
		jfc.showDialog(new JLabel(), "选择");
		File file=jfc.getSelectedFile();
		if(file.isDirectory()){
			System.out.println("文件夹:"+file.getAbsolutePath());
		}else if(file.isFile()){
			System.out.println("文件:"+file.getAbsolutePath());
		}
		System.out.println("路径"+file.getAbsolutePath());
		return jfc.getSelectedFile().getAbsolutePath();
	}

}
