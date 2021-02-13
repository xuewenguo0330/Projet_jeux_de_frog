package gameCommons;

public interface ILane {
    //Mettre à jour les positions des objets sur la voie courante
    public void update();

    //Déterminer si les voitures ou les rondins flottants sont sain et sauf sur sa voie
    public boolean isSafe(Case c);

    //Déterminer si la grenouille n'est pas en danger dans sa position sur l'écran et donc au Case c
    public boolean isSafeFrog(Case c);

    // Ajouter les objets de la voie courant dans l'écran
    public void addToGraphics();

    // get_block(), avoir_bonus() et glissante()
    // Pour tous les version du jeux avec piege
    public boolean get_block(Case pos);
    public boolean avoir_bonus(Case pos);
    public boolean glissante(Case pos);
}
