package maze;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class MazeFrame extends JFrame {
	private static final char DepthFirstSearchSolveMaze = 0;
	private static final char BreadthFirstSearchSolveMaze = 1;
	private char solveMaze = 0;
	private static final char DepthFirstSearchCreateMaze = 0;
	private static final char RandomizedPrimCreateMaze = 1;
	private static final char RecursiveDivisionCreateMaze = 2;
	private char createMaze = 0;

	private JPanel contentPane;
	private Maze maze;
	private boolean isPause = false;
	private boolean bgmStart = true;
	private JPanel panel_1;
	private JPanel panel_2;
	private Panel panel;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton prompt;
	private JButton bgm;
	private JPanel panel_3;
	private JLabel label;
	private JPanel panel_4;
	private JButton button_4;
	private JButton btnQuit;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel label_1;
	private JPanel panel_8;
	private JLabel label_2;
	private JSpinner spinner;
	private JPanel panel_9;
	private JLabel lblLatticesWidth;
	private JSpinner spinner_1;
	private JPanel panel_10;
	private JPanel panel_7;
	private JPanel panel_11;
	private JPanel panel_12;
	private JLabel label_4;
	private JSpinner spinner_2;
	private JPanel panel_13;
	private JLabel lblPleaseSelectThe;
	private JPanel panel_14;
	private JLabel lblPleaseSelectThe_1;
	private JPanel panel_15;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private ButtonGroup createMazeButton = new ButtonGroup();
	private JPanel panel_16;
	private JRadioButton rdbtnDepthFirstSearch;
	private JRadioButton rdbtnBreadthFirstSearch;
	private ButtonGroup solveMazeButton = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public MazeFrame(int rowNumber, int colNumber) {
		setTitle("Jiang's Maze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 0));

		panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		label = new JLabel("Welcome to Jiang's Maze\r\n");
		label.setFont(new Font("Lucida Handwriting", Font.PLAIN, 16));
		panel_3.add(label, BorderLayout.NORTH);

		panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.add(panel_4, BorderLayout.SOUTH);

		button_4 = new JButton("GO");
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (getSolveMaze() != maze.getSolveMaze())
					maze.setSolveMaze(getSolveMaze());
				maze.setPromptSolveMaze(false);
				button_1.setEnabled(true);
				button_2.setEnabled(false);
				button_3.setEnabled(true);
				prompt.setEnabled(true);
				int col = Integer.parseInt(spinner_2.getValue().toString());
				int row = Integer.parseInt(spinner.getValue().toString());
				if (maze.getColNumber() == col && maze.getRowNumber() == row) {
					if (getCreateMaze() != maze.getCreateMaze()) {
						maze.setCreateMaze(getCreateMaze());
						maze.createMaze();
					} else {
						maze.setComputerDo(false);
						maze.resetStepNumber();
						maze.resetTimer();
						maze.setThreadStop();
						maze.setBallPosition(maze.getEntrance());
					}
					maze.requestFocus();
					maze.repaint();
				} else {
					maze.setColNumber(col);
					maze.setRowNumber(row);
					if (getCreateMaze() != maze.getCreateMaze()) {
						maze.setCreateMaze(getCreateMaze());
					}
					maze.createMaze();
					maze.requestFocus();
				}
			}
		});
		button_4.setFont(new Font("Lucida Handwriting", Font.PLAIN, 12));
		button_4.setActionCommand("GO");
		panel_4.add(button_4);

		btnQuit = new JButton("Quit");
		btnQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setFont(new Font("Lucida Handwriting", Font.PLAIN, 12));
		btnQuit.setActionCommand("Cancel");
		panel_4.add(btnQuit);

		panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));

		panel_6 = new JPanel();
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		label_1 = new JLabel("Please set the parameters of the maze:");
		label_1.setFont(new Font("SimSun-ExtB", Font.PLAIN, 14));
		panel_6.add(label_1, BorderLayout.NORTH);

		panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new GridLayout(0, 1, 0, 0));

		panel_8 = new JPanel();
		panel_7.add(panel_8);

		label_2 = new JLabel("ROWS:");
		panel_8.add(label_2);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(11, 11, 99, 2));
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner, "0");
		spinner.setEditor(editor);
		JFormattedTextField textField = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
		textField.setEditable(false);
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

			}
		});
		panel_8.add(spinner);

		panel_11 = new JPanel();
		panel_7.add(panel_11);

		panel_12 = new JPanel();
		panel_11.add(panel_12);

		label_4 = new JLabel("COLS:");
		panel_12.add(label_4);

		spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(11, 11, 99, 2));
		// 控制输入
		editor = new JSpinner.NumberEditor(spinner_2, "0");
		spinner_2.setEditor(editor);
		textField = ((JSpinner.NumberEditor) spinner_2.getEditor()).getTextField();
		textField.setEditable(false);
		panel_12.add(spinner_2);

		panel_10 = new JPanel();
		panel_7.add(panel_10);

		panel_9 = new JPanel();
		panel_10.add(panel_9);

		lblLatticesWidth = new JLabel("Lattices' width: ");
		panel_9.add(lblLatticesWidth);

		spinner_1 = new JSpinner();
		spinner_1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				maze.setLatticeWidth(Integer.parseInt(spinner_1.getValue().toString()));
				maze.repaint();
			}
		});
		spinner_1.setModel(new SpinnerNumberModel(15, 5, 30, 1));
		panel_9.add(spinner_1);

		panel_14 = new JPanel();
		panel_14.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.add(panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));

		lblPleaseSelectThe_1 = new JLabel("Please select the method to create the maze: ");
		lblPleaseSelectThe_1.setFont(new Font("SimSun-ExtB", Font.PLAIN, 14));
		panel_14.add(lblPleaseSelectThe_1, BorderLayout.NORTH);

		panel_15 = new JPanel();
		panel_14.add(panel_15);
		panel_15.setLayout(new GridLayout(0, 1, 0, 0));

		rdbtnNewRadioButton = new JRadioButton("Depth First Search Algorithm");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCreateMaze(DepthFirstSearchCreateMaze);
			}
		});
		panel_15.add(rdbtnNewRadioButton);

		rdbtnNewRadioButton_1 = new JRadioButton("Randomized Prim's Algorithm");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCreateMaze(RandomizedPrimCreateMaze);
			}
		});
		panel_15.add(rdbtnNewRadioButton_1);

		rdbtnNewRadioButton_2 = new JRadioButton("Recursive Division Algorithm");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCreateMaze(RecursiveDivisionCreateMaze);
			}
		});
		panel_15.add(rdbtnNewRadioButton_2);

		panel_13 = new JPanel();
		panel_13.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));

		lblPleaseSelectThe = new JLabel("Please select the method to solve the maze: ");
		lblPleaseSelectThe.setFont(new Font("SimSun-ExtB", Font.PLAIN, 14));
		panel_13.add(lblPleaseSelectThe, BorderLayout.NORTH);

		panel_16 = new JPanel();
		panel_13.add(panel_16);
		panel_16.setLayout(new GridLayout(0, 1, 0, 0));

		rdbtnDepthFirstSearch = new JRadioButton("Depth First Search Algorithm");
		rdbtnDepthFirstSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setSolveMaze(DepthFirstSearchSolveMaze);
			}
		});
		panel_16.add(rdbtnDepthFirstSearch);

		rdbtnBreadthFirstSearch = new JRadioButton("Breadth First Search Algorithm");
		rdbtnBreadthFirstSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setSolveMaze(BreadthFirstSearchSolveMaze);
			}
		});
		panel_16.add(rdbtnBreadthFirstSearch);

		panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		maze = new Maze(rowNumber, colNumber);
		panel_2.add(maze);

		panel = new Panel();
		panel_2.add(panel, BorderLayout.NORTH);

		button = new JButton("Restart");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isBgmStart())
					maze.setAudioThreadStart();
				maze.init();
				maze.createMaze();
				button_1.setEnabled(true);
				button_1.setText("Pause");
				setPause(false);
				button_2.setEnabled(false);
				button_3.setEnabled(true);
				prompt.setEnabled(true);
				maze.requestFocus();
			}
		});
		panel.add(button);

		button_1 = new JButton("Pause");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!maze.isWin()) {
					if (!isPause() && maze.isStartTiming() && button_1.isEnabled()) {
						button_1.setText("Continue");
						((Timers) maze.getTimeText()).stop();
						setPause(true);
					} else if (isPause() && maze.isStartTiming() && button_1.isEnabled()) {
						button_1.setText("Pause");
						((Timers) maze.getTimeText()).proceed();
						setPause(false);
						maze.requestFocus();
					}
				}
			}
		});
		panel.add(button_1);

		prompt = new JButton("Prompt");
		prompt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!maze.isWin()) {
					maze.computerSolveMazeForBallPosition();
					maze.requestFocus();
				}
			}
		});
		panel.add(prompt);

		button_2 = new JButton("Player do");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (button_2.isEnabled() && maze.isComputerDo()) {
					maze.setComputerDo(false);
					button_3.setEnabled(true);
					button_2.setEnabled(false);
					button_1.setEnabled(true);
					prompt.setEnabled(true);
					maze.resetStepNumber();
					maze.resetTimer();
					maze.setThreadStop();
					maze.setBallPosition(maze.getEntrance());
					maze.requestFocus();
				}
			}
		});
		button_2.setEnabled(false);
		panel.add(button_2);

		button_3 = new JButton("Computer do");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (button_3.isEnabled() && !maze.isComputerDo() && maze.computerSolveMaze()) {
					button_2.setEnabled(true);
					button_3.setEnabled(false);
					button_1.setEnabled(false);
					prompt.setEnabled(false);
				}
			}
		});
		panel.add(button_3);

		bgm = new JButton();
		bgm.setIcon(new ImageIcon(System.getProperty("user.dir") + "/media//bgmStart.JPG"));
		bgm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isBgmStart()) {
					maze.setAudioThreadStop();
					bgm.setIcon(new ImageIcon(System.getProperty("user.dir") + "/media//bgmClose.JPG"));
				} else {
					maze.setAudioThreadStart();
					bgm.setIcon(new ImageIcon(System.getProperty("user.dir") + "/media//bgmStart.JPG"));
				}
				setBgmStart(!isBgmStart());
			}
		});
		panel.add(bgm);

		solveMazeButton.add(rdbtnDepthFirstSearch);
		solveMazeButton.add(rdbtnBreadthFirstSearch);
		rdbtnDepthFirstSearch.setSelected(true);

		createMazeButton.add(rdbtnNewRadioButton);
		createMazeButton.add(rdbtnNewRadioButton_1);
		createMazeButton.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton.setSelected(true);

		maze.requestFocus();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MazeFrame mazeFrame = new MazeFrame(11, 11);
			Toolkit tool = mazeFrame.getToolkit(); // 得到一个Toolkit对象
			Image myimage = tool.getImage(System.getProperty("user.dir") + "/media//maze.jpg"); // 由tool获取图像
			mazeFrame.setIconImage(myimage);
			mazeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mazeFrame.setVisible(true);
			mazeFrame.setSize(1000, 500);
			mazeFrame.maze.setAudioThreadStart();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the isPause
	 */
	public boolean isPause() {
		return isPause;
	}

	/**
	 * @param isPause the isPause to set
	 */
	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}

	/**
	 * @return the solveMaze
	 */
	public char getSolveMaze() {
		return solveMaze;
	}

	/**
	 * @param solveMaze the solveMaze to set
	 */
	public void setSolveMaze(char solveMaze) {
		this.solveMaze = solveMaze;
	}

	/**
	 * @return the createMaze
	 */
	public char getCreateMaze() {
		return createMaze;
	}

	/**
	 * @param createMaze the createMaze to set
	 */
	public void setCreateMaze(char createMaze) {
		this.createMaze = createMaze;
	}

	/**
	 * @return the bgmStart
	 */
	public boolean isBgmStart() {
		return bgmStart;
	}

	/**
	 * @param bgmStart the bgmStart to set
	 */
	public void setBgmStart(boolean bgmStart) {
		this.bgmStart = bgmStart;
	}
}
