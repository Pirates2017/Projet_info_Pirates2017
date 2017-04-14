package Model;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Creature extends GameObject implements Demisable, Runnable{
	private int frequency;
	private int occuring_time;
	private int object_advantage;
	boolean appeared = false;
	protected ArrayList<DemisableObserver> demisableObservers = new ArrayList<DemisableObserver>();
	
	public Creature(int X, int Y, int color, int width, int height) {
		super(X, Y, color, width, height);
		this.frequency = frequency;
		this.occuring_time = occuring_time;
		this.object_advantage = object_advantage;
	}

	public void appeared(){
		if(){//comment écrire la condition d'apparition? Elles apparaissent aléatoirement
			this.appeared = true;
			run();	
		}
	}
	
	
	@Override
	public void run() {
		int count = 0;
		while(!this.appeared  && count < this.occuring_time/10.0){
			try {
				Thread.sleep(10);
				count = count + 1;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.demisableNotifyObserver();
	}

	@Override
	public void demisableAttach(DemisableObserver po) {
		demisableObservers.add(po);		
	}

	@Override
	public void demisableNotifyObserver() {
		for (DemisableObserver o : demisableObservers) {
			o.demise(this, null);
		}	
	}
	
	@Override
	public boolean isObstacle() {
		return false;
	}
	
	public int giveObject(){
		return object_advantage;
	}
}
