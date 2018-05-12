package com.tqc.game;

import com.tqc.entity.Plate;
import com.tqc.entity.Stick;

import java.awt.*;

/**
 * 游戏主逻辑基类
 */
public class BaseGame {

	public static int DEFAULT_GAME_WIDTH = 800;
	public static int DEFAULT_GAME_HEIGHT = 500;
	public static int MAX_LEVEL = 10;

	/**
	 * 游戏界面宽度
	 */
	protected int gameWidth;

	/**
	 * 游戏界面高度
	 */
	protected int gameHeight;


	/**
	 * 一共三个柱子
	 */
	protected Stick stick1;
	protected Stick stick2;
	protected Stick stick3;

	protected int plateCount = 3;
	protected GamePanel panelInstance;

	public int getPlateCount() {
		return plateCount;
	}

	public void setPlateCount(int plateCount) {
		this.plateCount = plateCount;
	}

	public void setGameWidth(int gameWidth) {
		this.gameWidth = gameWidth;
	}

	public int getGameWidth() {
		return gameWidth;
	}

	public void setGameHeight(int gameHeight) {
		this.gameHeight = gameHeight;
	}

	public int getGameHeight() {
		return gameHeight;
	}

	public void setPanelInstance(GamePanel panelInstance) {
		if (panelInstance == getPanelInstance()) {
			return;
		}

		if (panelInstance == null) {
			return;
		}

//		if (getPanelInstance() != null) {
//			getPanelInstance().setGameInstance(null);
//		}
		this.panelInstance = panelInstance;
		panelInstance.setGameInstance(this);
	}

	public GamePanel getPanelInstance() {
		return panelInstance;
	}

	public BaseGame() {
		this(DEFAULT_GAME_WIDTH, DEFAULT_GAME_HEIGHT, null);
	}

	public BaseGame(int gameWidth, int gameHeight, GamePanel panel) {
		this.setGameWidth(gameWidth);
		this.setGameHeight(gameHeight);
		this.setPanelInstance(panel);
	}

	/**
	 * 初始化
	 */
	public void init() {

		int sw = Stick.DEFAULT_STICK_WIDTH * gameWidth / DEFAULT_GAME_WIDTH;
		int sh = Stick.DEFAULT_STICK_HEIGHT * gameHeight / DEFAULT_GAME_HEIGHT;


		stick1 = new Stick(Stick.STICK1_NAME, sw, sh);
		stick2 = new Stick(Stick.STICK2_NAME, sw, sh);
		stick3 = new Stick(Stick.STICK3_NAME, sw, sh);

		reset();
	}

	/**
	 * 重置游戏
	 */
	public void reset() {
		if (stick2 != null) {
			stick2.getPlateStack().clear();
		}
		if (stick3 != null) {
			stick3.getPlateStack().clear();
		}
		if (stick1 != null) {
			stick1.getPlateStack().clear();
			int plateHeight = Plate.DEFAULT_PLATE_HEIGHT * gameHeight / DEFAULT_GAME_HEIGHT;



			for (int i = 1; i <= plateCount; i++) {
				stick1.pushAPlate(
						new Plate(i, (gameWidth >> 2), plateHeight)
				);
			}
		}

//		记得重画
		panelInstance.repaint();
	}

	/**
	 * 将一个盘子从fromStick移动到targetStick
	 *
	 * @param fromStick
	 * @param targetStick
	 */
	public void moveToStick(Stick fromStick, Stick targetStick) {
		Plate movePlate = fromStick.getTopPlate();
		if (targetStick.canMoveTo(movePlate) && !(fromStick == targetStick)) {
			movePlate = fromStick.popAPlate();
			targetStick.pushAPlate(movePlate);
		}
		if (movePlate != null) {
			movePlate.setColor(Color.BLACK);
		}

//		重画
		panelInstance.repaint();
	}


	/**
	 * 缓存
	 */
	protected Image bufferImage;

	/**
	 * 将元素画入缓存
	 */
	protected void drawBuffer() {
		if (bufferImage == null) {
			bufferImage = panelInstance.createImage(gameWidth, gameHeight);
		}
		Graphics graphics = bufferImage.getGraphics();
		graphics.clearRect(0, 0, gameWidth, gameHeight);

		if (stick1 != null) {
			int offsetH = (gameHeight >> 1) - (stick1.getStickHeight() >> 1);
			Stick.drawStick(graphics, stick1, (gameWidth >> 2), offsetH);
			Stick.drawStick(graphics, stick2, (gameWidth >> 1), offsetH);
			Stick.drawStick(graphics, stick3, (gameWidth >> 2) * 3, offsetH);
		}
	}

	/**
	 * 渲染游戏
	 *
	 * @param graphics
	 */
	public void paintGame(Graphics graphics) {
		drawBuffer();
		graphics.drawImage(bufferImage, 0, 0, panelInstance);
	}

}
