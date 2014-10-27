package com.IDG.mapBuilder;

import javax.swing.*;
import java.awt.event.*;

public class Wall extends JButton implements ActionListener{
	public ImageIcon path,brick,start,end;



	int id=0;
	boolean isStart;
	boolean isEnd;
	boolean isPath;
	boolean isWall;
	byte value=0;
	
	
	public Wall()
	{
		path=new ImageIcon("Resource/GameImages/green.jpg");
		brick=new ImageIcon("D://path2.png");
		start=new ImageIcon("Resource/GameImages/start.jpg");
		end=new ImageIcon("Resource/GameImages/end.png");
		
		
		isStart=false;
		isEnd=false;
		isPath=false;
		isWall=false;
		this.addActionListener(this);
		this.addMouseListener(new PopClickListener(this));
	}


	public void actionPerformed (ActionEvent e) 
	{


			if(this.isPath)
				this.setPath(false);
			value++;
			value%=2;
			System.out.println("Value"+value);
			switch(value) {
			case 0:
				setIcon(null);
				this.setPath(false);
				this.setStart(false);
				this.setEnd(false);
				break;
			case 1:
				setIcon(path);
				this.setPath(true);
				this.setStart(false);
				this.setEnd(false);
				break;
			
			}
		
		
		System.out.println("Button Pressed"+id+this.isPath());

	}

	

	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public boolean isStart() {
		return isStart;
	}


	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}


	public boolean isEnd() {
		return isEnd;
	}


	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}


	public boolean isPath() {
		return isPath;
	}


	public void setPath(boolean isPath) {
		this.isPath = isPath;
	}


	public boolean isWall() {
		return isWall;
	}


	public void setWall(boolean isWall) {
		this.isWall = isWall;
	}


	public byte getValue() {
		return value;
	}


	public void setValue(byte value) {
		this.value = value;
	}


	public ImageIcon getPath() {
		return path;
	}


	public ImageIcon getBrick() {
		return brick;
	}
	public ImageIcon getStart() {
		return start;
	}


	public void setStart(ImageIcon start) {
		this.start = start;
	}


	public ImageIcon getEnd() {
		return end;
	}


	public void setEnd(ImageIcon end) {
		this.end = end;
	}
}