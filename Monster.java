package Model;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Monster extends GameObject implements Demisable{
	private int power;
	private boolean disappeared = false;
	private int duration = 0;
	protected ArrayList<DemisableObserver> demisableObservers = new ArrayList<DemisableObserver>();

	public Monster(int X, int Y, int color, int width, int height) {
		super(X, Y, color, width, height);
		this.power = power;
	}
	
	public void move(int X, int Y){
		super.posX = this.posX + X;
		this.posY = this.posY + Y;
	}
	
	public void variePower(){
		power -= 1;
		if (power == 0){
			disappeared();
		}
	}

	public void disappeared(){
		if(power == 0){
			this.disappeared = true;
			run();	
		}
	}
	
	public void run() {
		int count = 0;
		while(!this.disappeared  && count < this.duration/10.0){
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
	public boolean isObstacle() {
		return true;
	}

	@Override
	public void demisableAttach(DemisableObserver po) {
		demisableObservers.add(po);		
	}

	public void demisableNotifyObserver() {
		for (DemisableObserver o : demisableObservers) {
			o.demise(this, null);
		}	
	}
}