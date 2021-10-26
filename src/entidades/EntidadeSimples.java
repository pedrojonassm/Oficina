package entidades;

import java.awt.Color;
import java.awt.Graphics;

public class EntidadeSimples {
	protected double x, y, speed;
	protected int sizeX, sizeY;
	protected Color debugColor;
	
	public EntidadeSimples(double x, double y, double speed, int sizeX, int sizeY, Color debugColor) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.debugColor = debugColor;
	}
	
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
	
	public void tick() {
		x++;
		y++;

	}
	
	public void render(Graphics g) {
		g.fillRect(getX(), getY(), sizeX, sizeY);

	}
	
}
