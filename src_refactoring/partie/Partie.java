package org.iut.mastermind.domain.partie;

import org.iut.mastermind.domain.proposition.MotSecret;
import org.iut.mastermind.domain.proposition.Reponse;

public class Partie {
    private static final int NB_ESSAIS_MAX = 5;
    private final Joueur joueur;
    private final String motADeviner;
    private int nbEssais;
    private boolean partieTerminee;

    public Partie(Joueur joueur, String motADeviner, int nbEssais, boolean partieTerminee) {
        this.joueur = joueur;
        this.motADeviner = motADeviner;
        this.nbEssais = nbEssais;
        this.partieTerminee = partieTerminee;
    }

    public static Partie create(Joueur joueur, String motADeviner) {
        return new Partie(joueur, motADeviner, 0, false);
    }

    public static Partie create(Joueur joueur, String motADeviner, int nbEssais) {
        return new Partie(joueur, motADeviner, nbEssais, false);
    }

    // si le nombre max d'essais n'est pas atteint
    // on compare la proposition au mot secret
    // et on renvoie la réponse
    // si toutes les lettres sont correctement placées,
    // on a terminé la partie
    public Reponse tourDeJeu(String motPropose) {
        Reponse reponse = new Reponse(this.motADeviner);
        if (!isTerminee()) {
            reponse.compare(motPropose);
            if (reponse.lettresToutesPlacees()) this.partieTerminee = true;
            this.nbEssais++;
        }
        return ResultatPartie.create(reponse, this.partieTerminee).resultat();
    }
    
    // la partie est-elle terminée
    public boolean isTerminee() {
        return !this.partieTerminee ? this.nbEssais >= NB_ESSAIS_MAX : this.partieTerminee;
    }
    
    public Joueur getJoueur() {
        return this.joueur;
    }
    
    public int getNbEssais() {
        return this.nbEssais;
    }
    
    public String getMot() {
        return this.motADeviner;
    }
    
    public void setPartieTerminee(boolean terminee){
        this.partieTerminee = terminee;
    }
}
