package principal;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import entidades.player.Player;
import graficos.Menu;
import graficos.Menu.Estado;
import graficos.loadSprites;

public class Game extends Canvas implements Runnable{
	
	public static final int Width = 1080, Height = 720;
	public static JFrame frame;
	public static Game instancia;
	
	public Thread thread;
	public boolean rodando;
	
	private Controle controle;
	
	private BufferedImage image;
	private BufferedImage fundo;
	public static Random random;
	
	public static Menu menu;
	
	public static Player player;
	
	public static loadSprites loadSprites;
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		instancia = this;
		iniciarFrame();
		
		controle = new Controle();
		addKeyListener(controle);
		
		image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
		random = new Random();
		
		loadSprites = new loadSprites();
		
		menu = new Menu();
		
		try {
			fundo = ImageIO.read(getClass().getResource("/fundo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		player = new Player(32, 32);
		
		thread = new Thread(this);
		thread.start();
	}

	private void iniciarFrame() {
		setPreferredSize(new Dimension(Width, Height));
		frame = new JFrame("qualquer coisa");
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	@Override
	public void run() {
		rodando = true;
		long lastTime = System.nanoTime(), now;
		double limiteFPS = 60, ns = 1000000000/limiteFPS, delta = 0;
		
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (rodando) {
			now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			
			if (delta >= 1) {
				frames++;
				delta--;
				tick();
				renderizar();
			}
			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				System.out.println(frames);
				frames = 0;
			}
		}
		
	}
	

	private void tick() {
		if (menu.estado != Estado.pausado) {
			player.tick();
		}
			
	}

	private void renderizar() {
		BufferStrategy buffer = this.getBufferStrategy();
		if (buffer == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.drawImage(fundo, 0, 0, Width, Height, 0, 0, Width, Height, null);
		
		if (menu.estado != Estado.pausado) {
			player.render(g);
		}else {
			menu.renderizar(g);
		}
		
		g.dispose();
		g = buffer.getDrawGraphics();
		g.drawImage(image, 0, 0, Width, Height, null);
		buffer.show();
	}

	public void encerrar() {
		rodando = false;
		
		try {
			thread.join();
			frame.dispose();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}


}
