package entidades.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entidades.EntidadeSimples;
import graficos.loadSprites;

public class Player extends EntidadeSimples {
	
	public int horizontal, vertical;
	
	public BufferedImage[][][] sprites;
	int estado, modo, index, frameSpeed, maxframeSpeed;

	public Player(double x, double y) {
		super(x, y, 3, 64, 64, Color.blue);
		sprites = loadSprites.playerSprites;
		estado = modo = index = frameSpeed = 0;
		maxframeSpeed = 5;
	}
	
	@Override
	public void tick() {
		x += horizontal*speed;
		y += vertical*speed;
		frameSpeed++;
		if (frameSpeed >= maxframeSpeed) {
			frameSpeed = 0;
			index++;
			if (index >= sprites[estado][modo].length) {
				index = 0;
				modo++;
				if (modo >= sprites[estado].length) {
					modo = 0;
				}
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(sprites[estado][modo][index], getX(), getY(), sizeX, sizeY, null);
	}

}
