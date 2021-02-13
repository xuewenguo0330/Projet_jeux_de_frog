package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Timer;
import environment.Environment;
import frog.Frog;
import frogInf.EnvInf;
import frogInf.FrogInf;
import frogPiege.EnvPiege;
import frogPiege.EnvRiverPiege;
import frogPiege.FrogPiege;
import frogRiver.EnvRiver;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;

/*
* Pour faire fonctionner le jeux stantard, enlever le barre oblique */
public class Main {

	public static void main(String[] args) throws IOException {

		//Caractéristiques du jeu
		int width = 35;
		int height = 40;
		int tempo = 100;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 1;

		/*Création de l'interface graphique*/
		IFroggerGraphics graphic = new FroggerGraphic(width, height);
		//Création de la partie
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);

		/*Création et liason de la grenouille*/
//		IFrog frog = new Frog(game); //1.Jeu stantard
//		IFrog frog = new FrogInf(game); //2.et 3.//2. Jeux Infini et 3. jeux avec rivière
	IFrog frog = new FrogPiege(game); //4.et 5.//4. Jeux Infini avec des cases speciales
		                                      //et 5. Jeux Infini avec Rivière et des cases spéciales
		game.setFrog(frog);
		graphic.setFrog(frog);

		/*Création et liaison de l'environnement*/
//		IEnvironment env = new Environment(game);  //1.Jeu stantard
//		IEnvironment env = new EnvInf(game); //2.Jeu infini
//		IEnvironment env = new EnvRiver(game); //3.Jeux avec Rivière
//		IEnvironment env = new EnvPiege(game); //4.Jeux Infini avec des cases speciales
		IEnvironment env = new EnvRiverPiege(game); //5. Jeux Infini avec Rivière et des cases spéciales
		game.setEnvironment(env);

		/*Boucle principale : l'environnement s'acturalise tous les tempo milisecondes*/
		Timer timer = new Timer(tempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				game.update();//1.Jeu stantard
//			game.updateInf(); //2. et 3. Jeux Infini et jeux avec riviere
			game.updatePiege(); //4.et 5.//4. Jeux Infini avec des cases speciales
				                            //et 5. Jeux Infini avec Rivière et des cases spéciales
				graphic.repaint();
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}
