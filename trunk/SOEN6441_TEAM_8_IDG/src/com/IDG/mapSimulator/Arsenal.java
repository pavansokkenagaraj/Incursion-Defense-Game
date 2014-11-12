package com.IDG.mapSimulator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Button;

import javax.swing.ImageIcon;

import com.IDG.controller.GameFileManager;

/**
 * class arsenal holds the information of tower, upgrade and sell tower
 * 
 * @author Pavan Sokke Nagaraj <pavansn8@gmail.com>
 * @version Build1
 * @since Build1
 *
 */
public class Arsenal {

	/**
	 * variable to hold the number of tower
	 */
	public static int towerNumber = 4;
	/**
	 * variable to hold the tower width
	 */
	public static int towerWidth = 40;
	/**
	 * variable to hold the gap between each towers
	 */
	public static int gap = 20;
	/**
	 * variable to hold the starting x position of tower placing point
	 */
	public static int box1Xpos = 710;
	/**
	 * variable to hold the starting y position of tower placing point
	 */
	public static int box1Ypos = 90;

	/**
	 * variable to hold the starting x position to draw tower information
	 */
	public int box2Xpos = 750;
	/**
	 * variable to hold the starting y position to draw tower information
	 */
	public int box2Ypos = 320;

	/**
	 * array of tower class to hold towers
	 */
	public static Tower[] towerBlocks = new Tower[towerNumber];
	/**
	 * array of rectangles to hold the dimensions of towers
	 */
	public Rectangle[] towers = new Rectangle[towerNumber];

	/**
	 * Rectangle to hold the dimensions of health button
	 */
	public Rectangle health;
	/**
	 * Rectangle to hold the dimensions of money button
	 */
	public Rectangle money;
	/**
	 * Rectangle to hold the dimensions of upgrade button
	 */
	public Rectangle upgradeButton;
	/**
	 * Rectangle to hold the dimensions of sell button
	 */
	public Rectangle sellButton;
	/**
	 * Rectangle to hold the dimensions of start button
	 */
	public Rectangle startGame;
	/**
	 * tower create value
	 */
	public static char heldValue = 'G';

	/**
	 * selected tower position
	 */
	public static int heldPosition = -1;
	/**
	 * selected tower x position
	 */
	public static int mapTowerXpos;
	/**
	 * selected tower y position
	 */
	public static int mapTowerYpos;

	/**
	 * flag to check if the tower is selected from tower market place
	 */
	public static boolean holdsTower = false;
	/**
	 * flag to check if the tower is selected from map
	 */
	public static boolean selectMapTower = false;
	/**
	 * flag to check if the tower information window to be cleared or not
	 */
	public static boolean clearInfo = false;
	/**
	 * flag to check if the upgrade button selected or not
	 */
	public static boolean upgradeConfirm = false;
	/**
	 * flag to check if the sell button is selected or not
	 */
	public static boolean sellConfirm = false;

	/**
	 * class constructor to initialize tower data, health data, money data
	 * Upgrade: Build 2-> serialize he objects and retrieve data from xml files
	 */
	public Arsenal() {

		for (int i = 0; i < towerNumber; i++) {

			towers[i] = new Rectangle(box1Xpos + (gap * i) + (towerWidth * i),
					box1Ypos, towerWidth, towerWidth);
			if (i == 0) {
				towerBlocks[i] = new Tower('G', 5, 1, 1, 10, "Single", 5, 5);
			} else if (i == 1) {
				towerBlocks[i] = new Tower('R', 10, 1, 1, 20, "Single", 5, 15);
			} else if (i == 2) {
				towerBlocks[i] = new Tower('B', 15, 1, 2, 20, "Multiple", 10,
						20);
			} else if (i == 3) {
				towerBlocks[i] = new Tower('D', 0, 0, 0, 0, "", 0, 0);
			}
		}

		health = new Rectangle(box1Xpos, box1Ypos + towerWidth, towerWidth,
				towerWidth);
		money = new Rectangle(box1Xpos + 100, box1Ypos + towerWidth,
				towerWidth, towerWidth);
		upgradeButton = new Rectangle(box2Xpos + 45, box2Ypos + 145, 160, 40);
		sellButton = new Rectangle(box2Xpos + 45, box2Ypos + 125, 160, 20);
	}

	/**
	 * function to draw GUI to screen
	 * 
	 * @param graphic
	 *            Graphic variable to draw the Components
	 */
	public void draw(Graphics graphic) {

		// Draw towers
		graphic.setColor(Color.GREEN);
		graphic.setFont(new Font("Courier New", Font.BOLD, 30));
		graphic.drawString("SELECT TOWERS", box1Xpos - 5, box1Ypos - 15);

		Image image = null;
		for (int i = 0; i < towerNumber; i++) {
			graphic.fillRect(box1Xpos + (gap * i) + (towerWidth * i), box1Ypos,
					towerWidth, towerWidth);
			if (towerBlocks[i].towerId == 'G')
				image = new ImageIcon("ImageSource/Tower.png").getImage();
			else if (towerBlocks[i].towerId == 'R')
				image = new ImageIcon("ImageSource/Tower2.png").getImage();
			else if (towerBlocks[i].towerId == 'B')
				image = new ImageIcon("ImageSource/Tower3.png").getImage();
			else if (towerBlocks[i].towerId == 'D')
				image = new ImageIcon("ImageSource/Trash.png").getImage();

			graphic.drawImage(image, box1Xpos + (gap * i) + (towerWidth * i),
					box1Ypos, towerWidth, towerWidth, null);

			if (towers[i].contains(MapSimulatorView.mse)) {
				graphic.setColor(new Color(255, 255, 255, 150));
				graphic.fillRect(box1Xpos + (gap * i) + (towerWidth * i),
						box1Ypos, towerWidth, towerWidth);
				towerBlocks[i].drawTowerInformation(graphic);
				selectMapTower = false;
				clearInfo = false;
			}
		}

		// Draw Healath
		graphic.setColor(Color.GREEN);
		graphic.setFont(new Font("Courier New", Font.BOLD, 20));
		graphic.drawString("\tHEALTH\t", box1Xpos, box1Ypos + towerWidth + 40);

		image = new ImageIcon("ImageSource/Health.png").getImage();
		graphic.drawImage(image, box1Xpos + 10, box1Ypos + towerWidth + 45,
				towerWidth, towerWidth, null);
		graphic.setColor(Color.RED);
		graphic.setFont(new Font("Courier New", Font.BOLD, 25));
		graphic.drawString("" + MapSimulatorView.health, box1Xpos + 10,
				box1Ypos + towerWidth + 110);

		// Draw Power
		graphic.setColor(Color.GREEN);
		graphic.setFont(new Font("Courier New", Font.BOLD, 20));
		graphic.drawString("\tPOWER\t", box1Xpos + 100, box1Ypos + towerWidth
				+ 40);
		image = new ImageIcon("ImageSource/Power.png").getImage();
		graphic.drawImage(image, box1Xpos + 110, box1Ypos + towerWidth + 45,
				towerWidth, towerWidth, null);

		drawMoney(graphic);

		if (holdsTower) {
			if (heldValue == 'G')
				image = new ImageIcon("ImageSource/Tower.png").getImage();
			else if (heldValue == 'R')
				image = new ImageIcon("ImageSource/Tower2.png").getImage();
			else if (heldValue == 'B')
				image = new ImageIcon("ImageSource/Tower3.png").getImage();
			else
				image = null;
			graphic.drawImage(image, MapSimulatorView.mse.x,
					MapSimulatorView.mse.y, towerWidth, towerWidth, null);
			towerBlocks[heldPosition].drawTowerInformation(graphic);
		}
		if (selectMapTower) {
			// System.out.println("While selectMapTower" + mapTowerXpos + "\t\t"
			// + mapTowerYpos);
			Tower tower = GameFileManager.getTowerObject(mapTowerXpos,
					mapTowerYpos);
			// MapSimulatorView.room.block[mapTowerXpos][mapTowerYpos].tower
			// .drawMarketInformation(graphic);
			tower.drawMarketInformation(graphic);
			// draw range ----> circle/ rectangle
			// int circleX =
			// MapSimulatorView.room.block[mapTowerXpos][mapTowerYpos].x;
			// int circleY =
			// MapSimulatorView.room.block[mapTowerXpos][mapTowerYpos].y;
			// int radius = tower.range;
			// circleX = circleX - ((radius * towerWidth) / 2);
			// circleY = circleX - ((radius * towerWidth) / 2);
			// graphic.setColor(Color.RED);
			// graphic.drawOval(circleX, circleY, radius * 2 * towerWidth,
			// radius
			// * 2 * towerWidth);
		}
		if (upgradeConfirm) {
			drawUpgradeTowerInfo(graphic);
		}
		if (sellConfirm) {
			drawSellTowerInfo(graphic);
		}
		if (clearInfo) {
			graphic.setColor(Color.GRAY);
			graphic.fillRect(box2Xpos, box2Ypos, 250, 200);
		}
	}

	/**
	 * function to draw money information to screen
	 * 
	 * @param graphic
	 *            Graphic variable to draw the Components
	 */
	private void drawMoney(Graphics graphic) {
		graphic.setFont(new Font("Courier New", Font.BOLD, 25));
		graphic.setColor(new Color(255, 200, 0));
		graphic.drawString("" + MapSimulatorView.power, box1Xpos + 110,
				box1Ypos + towerWidth + 110);
	}

	/**
	 * function to draw the sold tower information
	 * 
	 * @param graphic
	 *            Graphic variable to draw the Components
	 */
	private void drawSellTowerInfo(Graphics graphic) {
		// System.out.println("While drawUpgradeTowerInfo" + mapTowerXpos +
		// "\t\t"
		// + mapTowerYpos);
		Tower tower = GameFileManager
				.getTowerObject(mapTowerXpos, mapTowerYpos);
		if (tower.costToSell > 0) {
			MapSimulatorView.power = MapSimulatorView.power + tower.costToSell;
			drawMoney(graphic);
			MapSimulatorView.gameValue[mapTowerXpos][mapTowerYpos] = '*';
			MapSimulatorView.room.block[mapTowerXpos][mapTowerYpos].createId = '*';
			MapSimulatorView.room.block[mapTowerXpos][mapTowerYpos]
					.draw(graphic);
			sellConfirm = false;
			upgradeConfirm = false;
			selectMapTower = false;
			mapTowerXpos = -1;
			mapTowerYpos = -1;
			tower.drawTowerInformation(graphic);
			GameFileManager
					.deleteTowerObject(tower, mapTowerXpos, mapTowerYpos);
		}
	}

	/**
	 * function to draw the upgraded tower information
	 * 
	 * @param graphic
	 *            Graphic variable to draw the Components
	 */
	private void drawUpgradeTowerInfo(Graphics graphic) {
//		System.out.println("While drawUpgradeTowerInfo" + mapTowerXpos + "\t\t"
//				+ mapTowerYpos);
		Tower tower = GameFileManager
				.getTowerObject(mapTowerXpos, mapTowerYpos);
		if (MapSimulatorView.power >= tower.costToUpgrade) {
			MapSimulatorView.power = MapSimulatorView.power
					- tower.costToUpgrade;
			drawMoney(graphic);
			++tower.level;
			++tower.range;
			tower.power = tower.power + 5;
			tower.costToUpgrade = tower.costToUpgrade + 10;
			tower.costToSell = tower.costToSell + 5;
			tower.drawMarketInformation(graphic);
			GameFileManager.saveTowerObject(tower, mapTowerXpos, mapTowerYpos);
			upgradeConfirm = false;
			sellConfirm = false;
		}
	}

	/**
	 * function to check the selection of towers and to update the information
	 * 
	 * @param mouseClick
	 *            number of mouse clicked
	 */
	public void towerClick(int mouseClick) {
		sellConfirm = false;
		if (mouseClick == 1) {
			// Code to select the clicked tower and it's information
			for (int i = 0; i < towerNumber; i++) {
				if (towers[i].contains(MapSimulatorView.mse)) {
					if (towerBlocks[i].towerId == 'D') {
						holdsTower = false;
					} else {
						heldPosition = i;
						heldValue = towerBlocks[i].towerId;
						holdsTower = true;
					}
				}
			}
			// Code to select and populate market place tower information to
			// groid tower information
			if (holdsTower) {
				if (MapSimulatorView.power >= towerBlocks[heldPosition].costToBuy) {
					for (int i = 0; i < MapSimulatorView.room.mapRow; i++) {
						for (int j = 0; j < MapSimulatorView.room.mapColumn; j++) {
							if (MapSimulatorView.room.block[i][j]
									.contains(MapSimulatorView.mse)) {
								char createId = MapSimulatorView.gameValue[i][j];
								if (createId == '*') {
									MapSimulatorView.gameValue[i][j] = heldValue;
									// MapSimulatorView.towers[i][j] =
									// towerBlocks[heldPosition];
									MapSimulatorView.room.block[i][j].createId = heldValue;
									GameFileManager.saveTowerObject(
											towerBlocks[heldPosition], i, j);
									MapSimulatorView.power = MapSimulatorView.power
											- towerBlocks[heldPosition].costToBuy;
									holdsTower = false;
								}
							}
						}
					}

				}
			} else if (upgradeButton.contains(MapSimulatorView.mse)) {
				upgradeConfirm = true;
				sellConfirm = false;
			} else if (sellButton.contains(MapSimulatorView.mse)) {
				sellConfirm = true;
				upgradeConfirm = false;
			} else {
				for (int i = 0; i < MapSimulatorView.room.mapRow; i++) {
					for (int j = 0; j < MapSimulatorView.room.mapColumn; j++) {
						char createId = MapSimulatorView.gameValue[i][j];
						if (MapSimulatorView.room.block[i][j]
								.contains(MapSimulatorView.mse)) {
							mapTowerXpos = i;
							mapTowerYpos = j;

//							System.out.println("I click");
							if (createId == 'G' || createId == 'R'
									|| createId == 'B') {
								selectMapTower = true;
								clearInfo = false;
							} else {
								clearInfo = true;
								selectMapTower = false;
							}
						}
					}
				}
			}
		}

	}

}
