package maze;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

abstract class AbstractCreateMaze {
	// 验证横纵坐标是否超界
	protected boolean isOutofBorder(int x, int y, int colNumber, int rowNumber) {
		if ((x == 0 && y == 1) || (x == colNumber + 1 && y == rowNumber))
			return false;
		else
			return (x > colNumber || y > rowNumber || x < 1 || y < 1) ? true : false;
	}

	abstract void createMaze(Lattice[][] mazeLattice, int colNumber, int rowNumber);
}

class DepthFirstSearchCreateMaze extends AbstractCreateMaze {

	// 随机选择一个p点未访问的相邻迷宫单元 ,并移除两者之间的墙，为createMaze()函数调用
	protected Point ArroundPoint(Lattice[][] mazeLattice, Point p, Stack<Point> s, Random rand, int colNumber,
			int rowNumber) {
		final int[] arroundPoint = { -2, 0, 2, 0, -2 };// 一个点周围四个点的坐标变化，顺序为左上右下
		int r = rand.nextInt(4);
		for (int i = 0; i < 4; ++i) {
			int j = r % 4;
			int x = p.x + arroundPoint[j];
			int y = p.y + arroundPoint[j + 1];
			++r;
			if (!isOutofBorder(x, y, colNumber, rowNumber) && !mazeLattice[y][x].isPassable()) {
				mazeLattice[y][x].setPassable(true);
				mazeLattice[p.y + arroundPoint[j + 1] / 2][p.x + arroundPoint[j] / 2].setPassable(true);
				return new Point(x, y);
			}
		}
		return null;
	}

	@Override
	public void createMaze(Lattice[][] mazeLattice, int colNumber, int rowNumber) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		Point currentPoint = new Point(2 * rand.nextInt(colNumber / 2) + 1, 2 * rand.nextInt(rowNumber / 2) + 1);
		mazeLattice[currentPoint.y][currentPoint.x].setPassable(true);
		Stack<Point> pathStack = new Stack<Point>();
		pathStack.push(currentPoint);
		currentPoint = ArroundPoint(mazeLattice, currentPoint, pathStack, rand, colNumber, rowNumber);
		while (true) {
			Point p = ArroundPoint(mazeLattice, currentPoint, pathStack, rand, colNumber, rowNumber);
			if (p != null) {
				pathStack.push(currentPoint);
				currentPoint = p;
			} else if (!pathStack.isEmpty())
				currentPoint = pathStack.pop();
			else
				break;
		}
	}
}

class RandomizedPrimCreateMaze extends AbstractCreateMaze {

	// 将点p的邻墙放入列表中
	protected void pushArroundWallToList(Lattice[][] mazeLattice, Point p, List<Point> list, int colNumber,
			int rowNumber) {
		final int[] arroundWall = { -1, 0, 1, 0, -1 };// 一个点周围四个墙的坐标变化，顺序为左上右下
		for (int i = 0; i < 4;) {
			int x = p.x + arroundWall[i];
			int y = p.y + arroundWall[++i];
			if (!isOutofBorder(x, y, colNumber, rowNumber) && !mazeLattice[y][x].isPassable()) {
				list.add(new Point(x, y));
			}
		}
	}

	// 找到墙wall未被访问过的点，如果没有返回Null
	protected Point findPoint(Point wall, Lattice[][] mazeLattice) {
		final int[] arroundWall = { -1, 0, 1, 0, -1 };// 顺序为左上右下
		Point p = null;
		for (int i = (wall.y + 1) % 2; i < 2; i += 2) {
			boolean add = mazeLattice[wall.y + arroundWall[i + 1]][wall.x + arroundWall[i]].isPassable(),
					sub = mazeLattice[wall.y - arroundWall[i + 1]][wall.x - arroundWall[i]].isPassable();
			if (add && !sub) {
				p = new Point(wall.x - arroundWall[i], wall.y - arroundWall[i + 1]);
				break;
			}
			if (!add && sub) {
				p = new Point(wall.x + arroundWall[i], wall.y + arroundWall[i + 1]);
				break;
			}
		}
		return p;
	}

	@Override
	public void createMaze(Lattice[][] mazeLattice, int colNumber, int rowNumber) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		Point currentPoint = new Point(2 * rand.nextInt(colNumber / 2) + 1, 2 * rand.nextInt(rowNumber / 2) + 1);
		mazeLattice[currentPoint.y][currentPoint.x].setPassable(true);
		List<Point> listWall = new LinkedList<Point>();
		pushArroundWallToList(mazeLattice, currentPoint, listWall, colNumber, rowNumber);
		while (!listWall.isEmpty()) {
			int k = rand.nextInt(listWall.size());
			Point wall = listWall.remove(k);
			currentPoint = findPoint(wall, mazeLattice);
			if (currentPoint != null) {
				mazeLattice[wall.y][wall.x].setPassable(true);
				mazeLattice[currentPoint.y][currentPoint.x].setPassable(true);
				pushArroundWallToList(mazeLattice, currentPoint, listWall, colNumber, rowNumber);
			}
		}
	}

}

class RecursiveDivisionCreateMaze extends AbstractCreateMaze {

	// 随机在给定墙壁上开一个门
	protected void openAdoor(Lattice[][] mazeLattice, Point p1, Point p2, Random r) {
		if(p1.y==p2.y&&p1.x==p2.x){
			mazeLattice[p1.y][p1.x].setPassable(true);
			return;
		}
		if (p1.y == p2.y) {
			int pos = p1.x + r.nextInt((p2.x - p1.x) / 2 ) * 2;// 在奇数位置开门
			mazeLattice[p1.y][pos].setPassable(true);
		} else if (p1.x == p2.x) {
			int pos = p1.y + r.nextInt((p2.y - p1.y) / 2 ) * 2;
			mazeLattice[pos][p1.x].setPassable(true);
		}
	}

	// 递归生成迷宫
	private void recursiveCreateMaze(Lattice[][] mazeLattice, Point start, int height, int width, Random rand) {
		if (height <= 2 || width <= 2)
			return;
		// 在偶数行建立一条墙壁
		int drawx = start.y + rand.nextInt(height / 2) * 2 + 1;
		for (int i = start.x; i < start.x + width; ++i)
			mazeLattice[drawx][i].setPassable(false);
		// 在偶数列建立一条墙壁
		int drawy = start.x + rand.nextInt(width / 2) * 2 + 1;
		for (int i = start.y; i < start.y + height; ++i)
			mazeLattice[i][drawy].setPassable(false);
		// 从左侧墙壁开始按逆时针顺序随机在四个墙壁上开三个门，左侧墙壁记为1
		int opendoor =  rand.nextInt(4)+ 1;
		switch (opendoor) {
		case 1:
			openAdoor(mazeLattice, new Point(drawy, drawx + 1), new Point(drawy, start.y + height - 1), rand);// 2
			openAdoor(mazeLattice, new Point(drawy + 1, drawx), new Point(start.x + width - 1, drawx), rand);// 3
			openAdoor(mazeLattice,new Point(drawy, start.y), new Point(drawy, drawx - 1) , rand);// 4
			break;
		case 2:
			openAdoor(mazeLattice, new Point(drawy + 1, drawx), new Point(start.x + width - 1, drawx), rand);// 3
			openAdoor(mazeLattice,new Point(drawy, start.y), new Point(drawy, drawx - 1) , rand);// 4
			openAdoor(mazeLattice, new Point(start.x, drawx), new Point(drawy - 1, drawx), rand);// 1
			break;
		case 3:
			openAdoor(mazeLattice,new Point(drawy, start.y), new Point(drawy, drawx - 1) , rand);// 4
			openAdoor(mazeLattice, new Point(start.x, drawx), new Point(drawy - 1, drawx), rand);// 1
			openAdoor(mazeLattice, new Point(drawy, drawx + 1), new Point(drawy, start.y + height - 1), rand);// 2
			break;
		case 4:
			openAdoor(mazeLattice, new Point(start.x, drawx), new Point(drawy - 1, drawx), rand);// 1
			openAdoor(mazeLattice, new Point(drawy, drawx + 1), new Point(drawy, start.y + height - 1), rand);// 2
			openAdoor(mazeLattice, new Point(drawy + 1, drawx), new Point(start.x + width - 1, drawx), rand);// 3
			break;
		default:
			break;
		}
		// 左上角
        recursiveCreateMaze(mazeLattice, start,drawx-start.y, drawy-start.x, rand);
        // 右上角
        recursiveCreateMaze(mazeLattice, new Point(drawy+1,start.y),drawx-start.y, width-drawy+start.x-1, rand);
        // 左下角
        recursiveCreateMaze(mazeLattice, new Point(start.x,drawx+1),height - drawx + start.y - 1, drawy-start.x, rand);
        // 右下角
        recursiveCreateMaze(mazeLattice,new Point(drawy+1,drawx+1),height - drawx + start.y - 1, width-drawy+start.x-1, rand);
	}

	@Override
	public void createMaze(Lattice[][] mazeLattice, int colNumber, int rowNumber) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		for (int i = 1; i < colNumber + 1; ++i)
			for (int j = 1; j < rowNumber + 1; ++j)
				mazeLattice[j][i].setPassable(true);
		Point start = new Point(1, 1);
		recursiveCreateMaze(mazeLattice, start, rowNumber, colNumber, rand);
	}

}