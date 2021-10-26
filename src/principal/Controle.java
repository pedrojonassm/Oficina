package principal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import graficos.Menu.Estado;

public class Controle implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Game.menu.pausar();
			return;
		}
		if (Game.menu.estado == Estado.pausado) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				Game.menu.mover_selecionado(1);
			}else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
				Game.menu.mover_selecionado(-1);
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				Game.menu.selecionar();
			}
		}else {
			if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				Game.player.vertical = 1;
			}else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
				Game.player.vertical = -1;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
				Game.player.horizontal = -1;
			}else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				Game.player.horizontal = 1;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (Game.menu.estado == Estado.rodando) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_W) {
				Game.player.vertical = 0;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
				Game.player.horizontal = 0;
			}
		}
	}

}
