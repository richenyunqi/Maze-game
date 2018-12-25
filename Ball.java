package maze;

import java.awt.Color;

class Ball {
	private int x;//横坐标
	private int y;//纵坐标
	private Color color;//小球颜色
	public Ball(int x,int y) {
		setX(x);
		setY(y);
		setColor(Color.GREEN);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public int setY(int y) {
		this.y = y;
		return y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
