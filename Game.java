package Model;
import View.Window;

import java.util.ArrayList;
import java.util.Random;

public class Game implements DemisableObserver{
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	private Window window;
	private int width = 20;
	private int height = 20;
	private int bombTimer = 3000;
	private int numberOfBreakableBlocks = 40;
	private int numberOfCreatures = 30;
	private int numberOfMonsters = 30;
	
	public Game(Window window){
		this.window = window;

		// Creating one Player at position (1,1)
		objects.add(new Player(10,10,3,5));
		
		// Map building 
		
		//unbreakable blocks
		for(int i = 0; i < size; i++){
			objects.add(new BlockUnbreakable(i,0));
			objects.add(new BlockUnbreakable(0,i));
			objects.add(new BlockUnbreakable(i, size-1));
			objects.add(new BlockUnbreakable(size-1, i));
		}
		
		//breakable blocks
		Random rand = new Random();
		for(int i = 0; i < numberOfBreakableBlocks; i++){
			int x = rand.nextInt(16) + 2;
			int y = rand.nextInt(16) +2;
			BlockBreakable block = new BlockBreakable(x,y);
			block.demisableAttach(this);
			objects.add(block);
		}
		
		window.setGameObjects(this.getGameObjects());
		notifyView();
		
		//creatures
		//creatures
		Random randc = new Random();
		for(int i = 0; i < numberOfCreatures; i++){
			int x = randc.nextInt(18) +1;//+2 pour qu'il n'y ait aucun bloc sur les deux premiers cadres du jeu
			int y = randc.nextInt(18) +1;
			int color = randc.nextInt(9);
			Creature creature = new Creature(x,y, color, 50, 50);
			creature.demisableAttach(this);//fait en sorte que le block disparaisse
			objects.add(creature);
	}
		
		
	public ArrayList<GameObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<GameObject> objects) {
		this.objects = objects;
	}

	public int getNumberOfBreakableBlocks() {
		return numberOfBreakableBlocks;
	}

	public void setNumberOfBreakableBlocks(int numberOfBreakableBlocks) {
		this.numberOfBreakableBlocks = numberOfBreakableBlocks;
	}

	public int getNumberOfCreatures() {
		return numberOfCreatures;
	}

	public void setNumberOfCreatures(int numberOfCreatures) {
		this.numberOfCreatures = numberOfCreatures;
	}
	
	public void dropBomb(String bombType, int playerNumber){
		Player player = ((Player) objects.get(playerNumber));
		
		BombObject bombDropped = player.dropBomb(bombType);
		if(bombDropped != null){
			bombDropped.demisableAttach(this);
			for(GameObject object : objects){
				if(object instanceof Explodable){
					((BombObject) object).explodableAttach(((ExplodableObserver) bombDropped));
				}
				if(object instanceof ExplodableObserver){
					bombDropped.explodableAttach(((ExplodableObserver) object));
				}
			}
			objects.add(bombDropped);
			notifyView();
		}
	}
	
	public void movePlayer(int x, int y, int playerNumber){
		Player player = ((Player) objects.get(playerNumber));
		int nextX = player.getPosX()+x;
		int nextY = player.getPosY()+y;
		
		boolean obstacle = false;
		for(GameObject object : objects){
			if(object.isAtPosition(nextX, nextY)){
				obstacle = object.isObstacle();
			}
			if(obstacle == true){
				break;
			}
		}
		
		if(obstacle == false){
			player.move(x,y);
			notifyView();
		}
	}
	
	private void notifyView(){
		window.update();
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}
		
	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	synchronized public void demise(Demisable ps, ArrayList<GameObject> loot) {
		objects.remove(ps);
		if(loot != null){
			objects.addAll(loot);
		}
		notifyView();
	}	
}
