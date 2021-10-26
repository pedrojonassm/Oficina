package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class loadSprites {
	
	public static BufferedImage[][][] playerSprites;
	
	public loadSprites() {
		String estado = "handgun";
		playerSprites = new BufferedImage[4][][];
		playerSprites[0] = new BufferedImage[5][];
		playerSprites[0][0] = carregar_imagens_da_pasta("/Top_Down_Survivor/"+estado+"/idle/survivor-idle_"+estado+"_", 20, ".png");
		playerSprites[0][1] = carregar_imagens_da_pasta("/Top_Down_Survivor/"+estado+"/meleeattack/survivor-meleeattack_"+estado+"_", 15, ".png");
	}

	private BufferedImage[] carregar_imagens_da_pasta(String inicio, int quantidade, String fim) {
		ArrayList<BufferedImage> array = new ArrayList<BufferedImage>();
		try {
			for (int i = 0; i < quantidade; i++) {
					array.add(ImageIO.read(getClass().getResource(inicio+i+fim)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedImage[] retorno = new BufferedImage[array.size()];
		for (int i = 0; i < retorno.length; i++) {
			retorno[i] = array.get(i);
		}
		return retorno;
		
	}

}
