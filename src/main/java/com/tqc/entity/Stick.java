package com.tqc.entity;

import com.tqc.game.BaseGame;

import java.awt.*;
import java.util.Stack;

/**
 * 柱子类
 */
public class Stick {

	/**
	 * 用于存储盘子的栈
	 */
	private Stack<Plate> plateStack = new Stack<Plate>();

	/**
	 * 柱子宽度，单位像素
	 */
	private int stickWidth;

	/**
	 * 柱子高度，单位像素
	 */
	private int stickHeight;

	/**
	 * 柱子名称
	 */
	private String name = "";

	/**
	 * 一些默认值
	 */
	public static int DEFAULT_STICK_WIDTH = 10;
	public static int DEFAULT_STICK_HEIGHT = 300;
	public static String STICK1_NAME = "stick1";
	public static String STICK2_NAME = "stick2";
	public static String STICK3_NAME = "stick3";

	public int getStickWidth() {
		return stickWidth;
	}

	public void setStickWidth(int stickWidth) {
		this.stickWidth = stickWidth;
	}

	public int getStickHeight() {
		return stickHeight;
	}

	public void setStickHeight(int stickHeight) {
		this.stickHeight = stickHeight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Stick() {
		this("", DEFAULT_STICK_WIDTH, DEFAULT_STICK_HEIGHT);
	}

	public Stick(String name, int width, int height) {
		this.setName(name);
		this.setStickHeight(height);
		this.setStickWidth(width);
	}

	/**
	 * movePlate是否能够移动到该柱子上
	 *
	 * @param movePlate
	 * @return
	 */
	public boolean canMoveTo(Plate movePlate) {
		if (movePlate == null) {
			return false;
		}
		if (plateStack.isEmpty()) {
			return true;
		}

		Plate topPlate = plateStack.peek();
		return (
				topPlate != null)
				&& (movePlate.getSize() > topPlate.getSize()
		);

	}

	/**
	 * 弹出一个盘子
	 *
	 * @return
	 */
	public Plate popAPlate() {

		if (!plateStack.isEmpty()) {
			return plateStack.pop();
		}
		return null;
	}

	/**
	 * 放入一个盘子
	 *
	 * @param pushPlate
	 * @return
	 */
	public Plate pushAPlate(Plate pushPlate) {
		if (this.canMoveTo(pushPlate)) {
			return plateStack.push(pushPlate);
		}
		return null;
	}


	/**
	 * 获取顶部盘子
	 *
	 * @return
	 */
	public Plate getTopPlate() {
		if (plateStack.size() != 0) {
			return plateStack.peek();
		}
		return null;
	}

	public Stack<Plate> getPlateStack() {
		return plateStack;
	}

	/**
	 * 将顶部盘子显示为红色
	 */
	public void showTopClearly() {
		if (plateStack.isEmpty()) {
			return;
		}

		Plate tp = plateStack.pop();
		tp.setColor(Color.RED);
		pushAPlate(tp);
	}

	/**
	 * 将顶部盘子还原为原色
	 */
	public void hideTopClearly() {
		if (plateStack.isEmpty()) {
			return;
		}
		Plate tp = plateStack.pop();
		tp.setColor(Color.BLACK);
		pushAPlate(tp);
	}

	/**
	 * 判断柱子上是否有盘子
	 *
	 * @return
	 */
	public boolean isEmpty() {
		return getPlateStack().isEmpty();
	}


	/**
	 * 渲染时调用
	 *
	 * @param graphics
	 * @param stick
	 * @param x
	 * @param y
	 */
	public static void drawStick(Graphics graphics, Stick stick, int x, int y) {
		if (stick == null) {
			return;
		}


		graphics.setColor(Color.BLACK);
		graphics.fillRect(
				x - (stick.getStickWidth() >> 1),
				y,
				stick.getStickWidth(),
				stick.getStickHeight()
		);

		int i = BaseGame.MAX_LEVEL - 1; // 最大盘子号码为10
		for (Plate plate : stick.plateStack) {
			Plate.drawPlate(
					graphics,
					plate,
					x - (plate.getWidth() >> 1),    //盘子居中
					y + plate.getHeight() * i
			);
			i--;
		}
	}

}
