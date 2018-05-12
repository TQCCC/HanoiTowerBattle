package com.tqc.ui;

import com.tqc.game.GamePanel;
import com.tqc.game.MainGame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class AppMain extends JFrame {

	private JButton btnExit;
	private JButton btnReset;

	private JPanel contentPane;

	private JSpinner spinnerPlateCount;

	private GamePanel mainPane;

	private MainGame game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMain frame = new AppMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 创建窗体
	 */
	public AppMain() {
		setTitle("汉诺塔");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700 + 400, 400 + 200);
		contentPane = new JPanel();
		setContentPane(contentPane);

		mainPane = new GamePanel();
		mainPane.setBounds(0, 200, 700, 400);

		JLabel lblLevel = new JLabel("级别:");
		lblLevel.setBounds(30, 100, 80, 50);
		lblLevel.setFont(new Font("微软雅黑", Font.BOLD, 18));

		spinnerPlateCount = new JSpinner();
		spinnerPlateCount.setBounds(120, 100, 80, 50);
		spinnerPlateCount.setFont(new Font("微软雅黑", Font.BOLD, 25));
//		默认一开始加载3个盘子
		spinnerPlateCount.setModel(new SpinnerNumberModel(3, 1, 10, 1));
		spinnerPlateCount.getModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
//				关卡选择
				int count = Integer.valueOf(((SpinnerModel) e.getSource()).getValue().toString());
				game.setPlateCount(count);
				game.reset();
			}
		});


		btnReset = new JButton("重新开始");
		btnReset.setBounds(350, 100, 120, 50);
		btnReset.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						game.reset();
					}
				}
		);


		btnExit = new JButton("退出");
		btnExit.setBounds(950, 500, 120, 50);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(NORMAL);
			}
		});

		contentPane.setLayout(null);

		contentPane.add(lblLevel);
		contentPane.add(spinnerPlateCount);
		contentPane.add(mainPane);
		contentPane.add(btnExit);
		contentPane.add(btnReset);

		game = new MainGame(mainPane.getWidth(), mainPane.getHeight(), mainPane);
		game.setGameAware(new GGameAware());
		game.init();


//		窗体用户行为监听
		this.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowClosing(WindowEvent e) {
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowActivated(WindowEvent e) {
			}
		});
	}

	/**
	 * 渲染
	 *
	 * @param g
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	public class GGameAware implements MainGame.GameAware {

		public boolean OnNextLevel(int level) {

			if (JOptionPane.YES_OPTION
					== JOptionPane.showConfirmDialog(null, "下一关?")) {
//					下一关
				game.setPlateCount(game.getPlateCount() + 1);
				spinnerPlateCount.setValue(game.getPlateCount());
			} else {
				spinnerPlateCount.setEnabled(true);
			}
			return false;
		}

		public void OnFirstClick(String stickName) {
		}

		public void OnSecondClick(String fromStickName, String targetStickName) {
		}

		public void OnComplete(int plateCount) {
		}
	}

}
