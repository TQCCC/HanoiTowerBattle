package com.tqc.game;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.tqc.entity.Plate;
import com.tqc.entity.Stick;

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

	private void handleClick(Stick clickStick) {
		clickCount++;

		if (clickCount == 1) {
			clickStick.showTopClearly();
			fromStick = clickStick;

			/* Send "firstClick;fromStickName" */
			if (gameAware != null) {
				gameAware.OnFirstClick(fromStick.getName());
			}

		} else if (clickCount == 2) {
			clickStick.hideTopClearly();
			clickCount = 0;
			targetStick = clickStick;

			/* Send "secondClick;targetStickName" */
			if (gameAware != null) {
				
				gameAware.OnSecondClick(fromStick.getName(), targetStick.getName());
			}
			
			moveToStick(fromStick, targetStick);
			/* Judge win */
			if (stick3.getPlateStack().size() == plateCount) {
				/* Send a "win;" */
				if (gameAware != null) {
					if (gameAware.OnNextLevel(plateCount)) {
						nextLevel();
					}
				}else{
					nextLevel();
				}
			}
		}

	}

	public void nextLevel() {
		if (plateCount == MAX_LEVEL) {
			// "You have completed all levels, You are a master!"
			if (gameAware!=null) {
				gameAware.OnComplete(plateCount);
			}
			
			return;
		}

		plateCount++;

		reset();
		return;
	}

	public void addPanelAction(GamePanel panel) {

		panel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) { }
			@Override
			public void mousePressed(MouseEvent e) {
				int mx = e.getX();
				int pw = (gameWidth >> 2);

				// Click stick1
				if (mx < (pw + (pw >> 1))) {
					handleClick(stick1);
				}
				// Click stick2
				if (mx >= (pw + (pw >> 1)) && mx < ((pw << 1) + (pw >> 1))) {
					handleClick(stick2);
				}
				// Click stick3
				if (mx >= ((pw << 1) + (pw >> 1))) {
					handleClick(stick3);
				}
				panelInstance.repaint();

			}
			@Override
			public void mouseExited(MouseEvent e) { }
			@Override
			public void mouseEntered(MouseEvent e) { }
			@Override
			public void mouseClicked(MouseEvent e) { }
		});

	}

	public interface GameAware {
		public boolean OnNextLevel(int level);
		public void OnFirstClick(String stickName);
		public void OnSecondClick(String fromStickName, String targetStickName);
		public void OnComplete(int plateCount);
	}

}
