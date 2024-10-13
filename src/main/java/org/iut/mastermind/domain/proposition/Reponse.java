package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.unmodifiableList;

public class Reponse {
    private final String motSecret;
    private final List<Lettre> resultat = new ArrayList<>();
    private int position;

    public Reponse(String mot) {
        this.motSecret = mot;
    }

    // on récupère la lettre à la position dans le résultat
    public Lettre lettre(int position) {
        return this.resultat.get(position);
    }

    // on construit le résultat en analysant chaque lettre
    // du mot proposé
    public void compare(String essai) {
        // déclaratif
        this.position = 0;
	    for (char c : essai.toCharArray()) {
		    this.resultat.add(evaluationCaractere(c));
            this.position++;
	    }
        
        //fonctionnel
        // TODO
    }

    // si toutes les lettres sont placées
    public boolean lettresToutesPlacees() {
        boolean toutBon = true;
        this.position = 0;
        for(var lettre : resultat) {
            if(lettre.equals(Lettre.INCORRECTE) || lettre.equals(Lettre.NON_PLACEE)){
                toutBon = false;
                break;
            }
            position++;
        }
        return toutBon;
    }

    public List<Lettre> lettresResultat() {
        return unmodifiableList(resultat);
    }

    // renvoie le statut du caractère
    private Lettre evaluationCaractere(char carCourant) {
        if(estPlace(carCourant)) return Lettre.PLACEE;
        if(estPresent(carCourant)) return Lettre.NON_PLACEE;
        return Lettre.INCORRECTE;
    }

    // le caractère est présent dans le mot secret
    private boolean estPresent(char carCourant) {
        return this.motSecret.contains(Character.toString(carCourant));
    }

    // le caractère est placé dans le mot secret
    private boolean estPlace(char carCourant) {
        // TODO à verifier
        return motSecret.indexOf(carCourant)==position;
    }
}
