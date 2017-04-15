package Model;

import java.util.ArrayList;

public class Bomb extends BombObject implements Demisable{
	
	public Bomb(int x, int y, int duration, int range) {
		super(x, y, duration, range, 4);//chiffre = couleur
	}

	//////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void run() {//lance le décompte avant l'explosion
		int count = 0;
		while(!this.detonated  && count < this.duration/10.0){
			try {
				Thread.sleep(10);
				count = count + 1;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.demisableNotifyObserver();
		this.explodableNotifyObserver();
	}

	//////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void exploded(Explodable e) {
		BombObject bomb = (BombObject) e;
		boolean distanceX = Math.abs(this.getPosX() - bomb.getPosX()) <= bomb.getRange() && this.getPosY() == bomb.getPosY();
		boolean distanceY = Math.abs(this.getPosY() - bomb.getPosY()) <= bomb.getRange() && this.getPosX() == bomb.getPosX();
		if(distanceX || distanceY){
			this.detonated = true;
			this.demisableNotifyObserver();//on fait en sorte que ça se voit sur l'écran		
		}
	}
	

	@Override
	synchronized public void demisableNotifyObserver() {
		ArrayList<GameObject> loot = new ArrayList<GameObject>();
		int range = this.getRange();
		int x = this.getPosX();
		int y = this.getPosY();
		for(int i = x-range; i <= x+range; i++){
			Explosion explosion = new Explosion(i,y,500);
			Thread thread = new Thread(explosion);
			thread.start();
			for(DemisableObserver observer : demisableObservers){
				explosion.demisableAttach(observer);
			}
			loot.add(explosion);//ajoute l'explosion a la liste d'objets se trount sur le plateau de jeu
		}
		for(int i = y-range; i <= y+range; i++){
			Explosion explosion = new Explosion(x,i,500);
			Thread thread = new Thread(explosion);
			thread.start();
			for(DemisableObserver observer : demisableObservers){
				explosion.demisableAttach(observer);
			}
			loot.add(explosion);
		}
		
		for (DemisableObserver o : this.demisableObservers) {
			o.demise(this, loot);
		}	
	}
}
