package View;
import Model.GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Map extends JPanel {
	private ArrayList<GameObject> objects;
	
	public Map(){
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public void paint(Graphics g) { 
		for(int i = 0; i<20; i++){							// Vire la valeur 20 et parametrer ca
			for(int j = 0; j<20; j++){
				int x = i;
				int y = j;
				g.setColor(Color.CYAN);
				g.fillRect(x*50, y*50, 48, 48); 
				g.setColor(Color.BLACK);
				g.drawRect(x*50, y*50, 48, 48); 
			}
		}
		
		for(GameObject object : this.objects){
			int x = object.getPosX();
			int y = object.getPosY();
			int color = object.getColor();			
			
			if(color == 0){//blockunbreakable
				g.setColor(Color.DARK_GRAY);
			}else if(color == 1){//blockbreakable
				g.setColor(Color.GRAY);
			}else if(color == 2){//player
				g.setColor(Color.BLUE);
			}else if(color == 3){//dauphin
				g.setColor(Color.GREEN);
			}else if(color == 4){//sirene
				g.setColor(Color.RED);
			}else if(color == 5){//calamar
				g.setColor(Color.ORANGE);
			}else if(color == 6){//pièces d'or
				g.setColor(Color.YELLOW);
			}else if(color == 7){//rubis
				g.setColor(Color.MAGENTA);
			}else if(color == 8){//coquillage
				g.setColor(Color.PINK);
			}else if(color == 9){//bombe
				g.setColor(Color.BLACK);

			g.fillRect(x*50, y*50, 50, 50);//pour avoir toutes les cases collées les unes derrière les autres
			g.setColor(Color.BLACK);
			g.drawRect(x*50, y*50, 50, 50); 
		}
	}
	
	public void setObjects(ArrayList<GameObject> objects){
		this.objects = objects;
	}
	
	public void redraw(){
		this.repaint();
	}
}

