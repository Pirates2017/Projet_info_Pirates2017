package Model;

public class BlockUnbreakable extends Block{

	public BlockUnbreakable(int X, int Y) {
		super(X, Y, 0, 50, 50);
	}

	@Override
	public boolean isObstacle() {
		return true;
	}
}
