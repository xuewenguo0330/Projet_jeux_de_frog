package gameCommons;

public interface IFrog {
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition();
	
	/**
	 * Donne la direction de la grenouille, c'est à dire de son dernier mouvement
	 * @return
	 */
	public Direction getDirection();

	public int getScore();
	/**
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key);

	public int getGrade();//Savoir les Points Bonus

	public void Bonus();//Ajoute les bonus


}
