public class Point {
	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Point MoveUp() {
		return new Point(x-1, y );
	}

	public Point MoveDown() {
		return new Point(x+1, y );
	}

	public Point MoveLeft() {
		return new Point(x , y-1);
	}

	public Point MoveRigth() {
		return new Point(x , y+1);
	}
}
