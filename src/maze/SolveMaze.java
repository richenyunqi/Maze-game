package maze;

import java.awt.Point;
import java.util.*;

abstract class AbstractSolveMaze {
	protected Stack<Point> pathStack = null;

	// 验证横纵坐标是否超界
	protected boolean isOutofBorder(int x, int y, int colNumber, int rowNumber) {
		if ((x == 0 && y == 1) || (x == colNumber + 1 && y == rowNumber))
			return false;
		else
			return (x > colNumber || y > rowNumber || x < 1 || y < 1) ? true : false;
	}

	abstract Stack<Point> solveMaze(Lattice[][] mazeLattice, Point entrance, Point exit, int colNumber, int rowNumber);
}

class DepthFirstSearchSolveMaze extends AbstractSolveMaze {
	// 按深度优先搜索的方式选择一个p点未访问的相邻迷宫单元 ,并将选择的这一单元的visited置true，为solveMaze()函数调用
	protected Point ArroundPointDepthFirst(Lattice[][] mazeLattice, Point p, int colNumber, int rowNumber) {
		final int[] arroundPoint = { -1, 0, 1, 0, -1 };// 一个点周围四个点的坐标变化，顺序为左上右下
		for (int i = 0; i < 4;) {
			int x = p.x + arroundPoint[i];
			int y = p.y + arroundPoint[++i];
			if (!isOutofBorder(x, y, colNumber, rowNumber) && mazeLattice[y][x].isPassable()
					&& mazeLattice[y][x].getFather() == null) {
				p = new Point(x, y);
				mazeLattice[y][x].setFather(p);
				return p;
			}
		}
		return null;
	}

	@Override
	public Stack<Point> solveMaze(Lattice[][] mazeLattice, Point entrance, Point exit, int colNumber, int rowNumber) {
		// TODO Auto-generated method stub
		pathStack = new Stack<Point>();
		Deque<Point> pathDeque = new ArrayDeque<Point>();
		Point judge = new Point(0, 0);
		Point end = new Point(exit.x - 1, exit.y);
		for (int i = 0; i < rowNumber + 2; ++i)
			for (int j = 0; j < colNumber + 2; ++j)
				mazeLattice[i][j].setFather(null);
		mazeLattice[entrance.y][entrance.x].setFather(judge);
		pathDeque.addLast(entrance);
		Point currentPoint = entrance;
		while (!currentPoint.equals(end)) {
			currentPoint = ArroundPointDepthFirst(mazeLattice, currentPoint, colNumber, rowNumber);
			if (currentPoint == null) {
				pathDeque.removeLast();
				if (pathDeque.isEmpty())
					break;
				currentPoint = pathDeque.getLast();
			} else {
				pathDeque.addLast(currentPoint);
			}
		}
		mazeLattice[exit.y][exit.x].setFather(end);
		pathDeque.addLast(exit);
		while (!pathDeque.isEmpty())
			pathStack.push(pathDeque.removeLast());
		return pathStack;
	}
}

class BreadthFirstSearchSolveMaze extends AbstractSolveMaze {

	protected Point[] ArroundPointBreadthFirst(Lattice[][] mazeLattice, Point p, int colNumber, int rowNumber) {
		final int[] arroundPoint = { -1, 0, 1, 0, -1 };// 一个点周围四个点的坐标变化，顺序为左上右下
		Point[] point = { null, null, null, null };
		for (int i = 0; i < 4; ++i) {
			int x = p.x + arroundPoint[i];
			int y = p.y + arroundPoint[i + 1];
			if (!isOutofBorder(x, y, colNumber, rowNumber) && mazeLattice[y][x].isPassable()
					&& mazeLattice[y][x].getFather() == null) {
				point[i] = new Point(x, y);
				mazeLattice[y][x].setFather(p);
			}
		}
		return point;
	}

	@Override
	public Stack<Point> solveMaze(Lattice[][] mazeLattice, Point entrance, Point exit, int colNumber, int rowNumber) {
		// TODO Auto-generated method stub
		pathStack = new Stack<Point>();
		Point judge = new Point(0, 0);
		Deque<Point> pathDeque = new ArrayDeque<Point>();
		Point end = new Point(exit.x - 1, exit.y);
		for (int i = 0; i < rowNumber + 2; ++i)
			for (int j = 0; j < colNumber + 2; ++j)
				mazeLattice[i][j].setFather(null);
		mazeLattice[entrance.y][entrance.x].setFather(judge);
		pathDeque.addLast(entrance);
		Point currentPoint = entrance;
		while (!currentPoint.equals(end)) {
			Point[] p = ArroundPointBreadthFirst(mazeLattice, currentPoint, colNumber, rowNumber);
			int count = 0;// 对能压入栈中的currentPoint点周围的四个点中的点进行计数
			for (int i = 0; i < p.length; ++i)
				if (p[i] != null) {
					pathDeque.addLast(p[i]);
					++count;
				}
			if (count == 0) {
				pathDeque.removeLast();
				if (pathDeque.isEmpty())
					break;
				currentPoint = pathDeque.getLast();
			} else {
				pathDeque.addLast(currentPoint);
			}
		}
		mazeLattice[exit.y][exit.x].setFather(end);
		for (currentPoint = exit; !currentPoint
				.equals(judge); currentPoint = mazeLattice[currentPoint.y][currentPoint.x].getFather()) {
			pathStack.push(currentPoint);
		}
		return pathStack;
	}
}