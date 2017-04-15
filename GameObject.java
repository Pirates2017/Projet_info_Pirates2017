package Model;

public abstract class GameObject {
	protected int posX;
	protected int posY;
	protected int color;
	protected int health; //durée de vie
	protected int width, height;
	
	public GameObject(int X, int Y, int color){
		this.posX = X;
		this.posY = Y;
		this.color = color;
		this.width = width;
		this.height = height;
	}
	
	public int getPosX(){
		return this.posX;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getPosY(){
		return this.posY;
	}
	
	public int getColor(){
		return this.color;
	}
	
	public boolean isAtPosition(int x, int y){
		return this.posX == x && this.posY == y;
	}
	
	public abstract boolean isObstacle();
}
