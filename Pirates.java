import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Keyboard;
import Model.Game;
import View.Window;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.*;

public class Pirates extends JFrame implements ActionListener {
	public Pirates() {
		createMenubar();
		createButtons();
		setTitle("Projet info: Pirates");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(700, 700));
    }
	
	public void createMenubar(){
	    JMenuBar Menubar = new JMenuBar();
	    ImageIcon icon = new ImageIcon("exit4.png");
	
	    JMenu file = new JMenu("Options");
	    file.setMnemonic(KeyEvent.VK_F);
	
	    JMenuItem nMenuItem = new JMenuItem("New game", new ImageIcon("new.gif"));
	    nMenuItem.setMnemonic(KeyEvent.VK_N);
	    nMenuItem.setToolTipText("Start a new game");
	    nMenuItem.addActionListener((ActionEvent event) -> {	
	    	Window window = new Window();//ouvre une nouvelle fenêtre
	    	Game game = new Game(window);//lance le jeu dans la fenêtre
			Keyboard keyboard = new Keyboard(game);//met en lien le jeu et le clavier
			window.setKeyListener(keyboard);//met la fenêtre en lien avec le clavier
	    });
	    
	    file.add(nMenuItem);
	    Menubar.add(file);
	    setJMenuBar(Menubar);
	    
	    JMenuItem eMenuItem = new JMenuItem("Exit", icon);
	    eMenuItem.setMnemonic(KeyEvent.VK_E);
	    eMenuItem.setToolTipText("Exit application");
	    eMenuItem.addActionListener((ActionEvent event) -> {
	        System.exit(0);
	    });
	
	    file.add(eMenuItem);
	    Menubar.add(file);
	    setJMenuBar(Menubar);
	}
	public JPanel createButtons(){
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JButton play = new JButton ("Play!", new ImageIcon("new.gif"));
		
	    play.setVerticalTextPosition(AbstractButton.BOTTOM);
	    play.setHorizontalTextPosition(AbstractButton.CENTER);
	    play.setAlignmentX(panel.CENTER_ALIGNMENT);
	    play.setMnemonic(KeyEvent.VK_P);
	    play.addActionListener(this);
	    play.setActionCommand("play");
	    
		panel.add(play);
		add(panel, BorderLayout.CENTER);
		
		JButton quit = new JButton ("Exit", new ImageIcon("exit4.png"));
		
	    quit.setVerticalTextPosition(AbstractButton.BOTTOM);
	    quit.setHorizontalTextPosition(AbstractButton.CENTER);
	    quit.setAlignmentX(panel.CENTER_ALIGNMENT);
	    quit.setMnemonic(KeyEvent.VK_Q);
	    quit.addActionListener(this);
	    quit.setActionCommand("quit");
		
	    panel.add(quit);

		return panel;
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            Pirates ex = new Pirates();
            ex.setVisible(true);
        });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("play" == e.getActionCommand()){
			Window window = new Window();//ouvre une nouvelle fenêtre
	    	Game game = new Game(window);//lance le jeu dans la fenêtre
			Keyboard keyboard = new Keyboard(game);//met en lien le jeu et le clavier
			window.setKeyListener(keyboard);//met la fenêtre en lien avec le clavier
		}
		else if ("quit" == e.getActionCommand()){
			System.exit(0);
		}
		
	}
}
