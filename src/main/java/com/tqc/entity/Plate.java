package com.tqc.entity;

import java.awt.*;

/**
 * 盘子类
 */
public class Plate {
	/**
	 * 尺寸
	 */
	private int size;

	/**
	 * 颜色
	 */
	private Color color;

	/**
	 * 宽度，单位像素
	 */
	private int width;

	/**
	 * 高度，单位像素
	 */
	private int height;

	/**
	 * 一些默认值
	 */
	public static int DEFAULT_PLATE_WIDTH = 200;
	public static int DEFAULT_PLATE_HEIGHT = 30;

	public Plate(int size) {
		this.setSize(size);
		this.setColor(Color.BLACK);
//		根据尺寸设置宽度
		this.setWidth(DEFAULT_PLATE_WIDTH - (size - 1) * 15);
		this.setHeight(DEFAULT_PLATE_HEIGHT);

	}

	public Plate(int size, int width, int height) {
		this.setSize(size);
		this.setHeight(height);
//		根据尺寸设置宽度
		this.setWidth(width - (size - 1) * 15);
		this.setColor(Color.BLACK);
	}

	public Plate() {
		this(1);
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}


	/**
	 * 渲染时调用
	 *
	 * @param graphics
	 * @param plate
	 * @param x
	 * @param y
	 */
	public static void drawPlate(Graphics graphics, Plate plate, int x, int y) {

		if (plate == null) {
			return;
		}

		int width = plate.getWidth();
		int height = plate.getHeight();

		graphics.setColor(plate.getColor());
		graphics.fillRect(x, y, width, height);

//		画边框
		graphics.setColor(Color.WHITE);
		graphics.drawRect(x, y, width, height);

		graphics.setFont(new Font("", Font.BOLD, 15));
//			文字居中
		graphics.drawString("" + plate.getSize(), x + (width >> 1), y + (height >> 1));
	}

}
