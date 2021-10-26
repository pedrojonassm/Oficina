package graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import principal.Game;

public class Menu {
	public enum Estado{
		pausado, rodando
	}
	
	public enum Menus{
		principal, opcoes
	}
	
	public Menus menu;
	public Estado estado;
	ArrayList<String[]> opcoes;
	private int selecionado;
	
	public Menu() {
		estado = Estado.pausado;
		menu = Menus.principal;
		selecionado = 0;
		opcoes = new ArrayList<String[]>();
		String[] inicial = {"Jogar", "Opções", "Sair"};
		opcoes.add(inicial);
	}
	
	public void Desenhar_opcoes(Graphics g, String[] opcoes_a_desenhar) {
		g.setColor(Color.yellow);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		int x = 5*Game.Width/100, space = g.getFont().getSize()*3, y = Game.Height/2 - (space*(opcoes_a_desenhar.length-1))/2;
		for (int i = 0; i < opcoes_a_desenhar.length; i++) {
			if (i == selecionado) {
				g.setColor(Color.green);
				g.drawString(opcoes_a_desenhar[i], x, y+space*i);
				g.setColor(Color.yellow);
			}else
				g.drawString(opcoes_a_desenhar[i], x, y+space*i);
		}
	}
	
	public void renderizar (Graphics g) {
		if (estado == Estado.pausado) {
			Desenhar_opcoes(g, opcoes.get(menu.ordinal()));
		}
	}
	
	public void mover_selecionado(int cima_baixo) {
		selecionado += cima_baixo;
		if (selecionado < 0) {
			selecionado = opcoes.get(menu.ordinal()).length -1;
		}else if (selecionado >= opcoes.get(menu.ordinal()).length) {
			selecionado = 0;
		}
	}

	public void selecionar() {
		switch (menu) {
		case principal:
			switch (selecionado) {
			case 0:
				estado = Estado.rodando;
				opcoes.get(0)[0] = "continuar";
				break;

			case 2:
				Game.instancia.encerrar();
				break;
			}
			break;

		case opcoes:
			break;
		}
		
	}

	public void pausar() {
		if (estado == Estado.pausado) {
			if (opcoes.get(0)[0].equalsIgnoreCase("Jogar"))
				return;
			else
				estado = Estado.rodando;
			return;
		}
		estado = Estado.pausado;
		
	}
	
}
