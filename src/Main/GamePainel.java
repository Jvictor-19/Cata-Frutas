package Main;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GamePainel extends JPanel implements Runnable{
	
	final int originalTilesize = 16;
	final int scale = 3;
	
	final int tilesize = originalTilesize * scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tilesize * maxScreenCol;
	final int screenHeight = tilesize * maxScreenRow;	
	
	Thread gameThread;
	
	public GamePainel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true);
		
			
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		
		
	}
}
