package com.tqc.game;

import com.tqc.entity.Stick;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 主游戏逻辑
 */
public class MainGame extends BaseGame {

	private GameAware gameAware;

	private Stick fromStick = null;
	private Stick targetStick = null;

	private int clickCount = 0;

	public GameAware getGameAware() {
		return gameAware;
	}

	public void setGameAware(GameAware gameAware) {
		this.gameAware = gameAware;
	}

	public MainGame() {
		this(DEFAULT_GAME_WIDTH, DEFAULT_GAME_HEIGHT, null);
	}

	public MainGame(int gameWidth, int gameHeight, GamePanel panel) {
		super(gameWidth, gameHeight, panel);
		addPanelAction(panel);
	}

	/**
	 * 处理点击到的柱子
	 *
	 * @param clickStick
	 */
	private void handleClick(Stick clickStick) {

		clickCount++;

		if (clickCount == 1) {

			if (clickStick.isEmpty()) {
				clickCount = 0;
				return;
			}

//			第一次点击选中目标盘子
			clickStick.showTopClearly();
			fromStick = clickStick;

			if (gameAware != null) {
				gameAware.OnFirstClick(fromStick.getName());
			}

			return;
		}

		if (clickCount == 2) {
//			第二次点击移动盘子
			clickStick.hideTopClearly();
			clickCount = 0;
			targetStick = clickStick;

			if (gameAware != null) {
				gameAware.OnSecondClick(fromStick.getName(), targetStick.getName());
			}

//			移动盘子
			moveToStick(fromStick, targetStick);


//			判断胜利条件
			if (stick3.getPlateStack().size() == plateCount) {
				if (gameAware != null) {
					if (gameAware.OnNextLevel(plateCount)) {
						nextLevel();
					}
					return;
				}
				nextLevel();
			}
		}

	}

	/**
	 * 下一关
	 */
	public void nextLevel() {
//		最后一关通过
		if (plateCount == MAX_LEVEL) {
			if (gameAware != null) {
				gameAware.OnComplete(plateCount);
			}
			return;
		}

		plateCount++;

		reset();
		return;
	}

	/**
	 * @param panel
	 */
	public void addPanelAction(GamePanel panel) {

		panel.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				int mx = e.getX();
				int pw = (gameWidth >> 2);

				// 点击 stick1
				if (mx < (pw + (pw >> 1))) {
					handleClick(stick1);
				}
				// 点击 stick2
				if (mx >= (pw + (pw >> 1)) && mx < ((pw << 1) + (pw >> 1))) {
					handleClick(stick2);
				}
				// 点击 stick3
				if (mx >= ((pw << 1) + (pw >> 1))) {
					handleClick(stick3);
				}
				panelInstance.repaint();

			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}
		});

	}

	/**
	 * 获取游戏逻辑点
	 */
	public interface GameAware {
		/**
		 * 当下一关时触发
		 *
		 * @param level
		 * @return
		 */
		boolean OnNextLevel(int level);

		/**
		 * 第一次点击某柱子
		 *
		 * @param stickName
		 */
		void OnFirstClick(String stickName);

		/**
		 * 第二次点击某柱子
		 *
		 * @param fromStickName
		 * @param targetStickName
		 */
		void OnSecondClick(String fromStickName, String targetStickName);

		/**
		 * 完成最大关卡
		 *
		 * @param plateCount
		 */
		void OnComplete(int plateCount);
	}

}
