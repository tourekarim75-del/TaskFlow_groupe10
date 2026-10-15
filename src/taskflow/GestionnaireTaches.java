package taskflow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionnaireTaches {

    private final List<Tache> taches;
    private int prochainId;

    public GestionnaireTaches() {
        taches = new ArrayList<>();
        prochainId = 1;
    }

    public Tache ajouterTache(String titre,
                              Priorite priorite,
                              LocalDate echeance) {

        Tache tache = new Tache(
                prochainId,
                titre,
                priorite,
                echeance,
                false
        );

        taches.add(tache);
        prochainId++;

        return tache;
    }

    public Tache trouverTache(int id) {

        for (Tache tache : taches) {
            if (tache.getId() == id) {
                return tache;
            }
        }

        return null;
    }

    public boolean terminerTache(int id) {

        Tache tache = trouverTache(id);

        if (tache == null) {
            return false;
        }

        tache.terminer();
        return true;
    }

    public boolean modifierPriorite(int id, Priorite nouvellePriorite) {

        Tache tache = trouverTache(id);

        if (tache == null) {
            return false;
        }

        tache.setPriorite(nouvellePriorite);
        return true;
    }

    public boolean modifierEcheance(int id, LocalDate nouvelleEcheance) {

        Tache tache = trouverTache(id);

        if (tache == null) {
            return false;
        }

        tache.setEcheance(nouvelleEcheance);
        return true;
    }

    public boolean retirerEcheance(int id) {

        Tache tache = trouverTache(id);

        if (tache == null) {
            return false;
        }

        tache.setEcheance(null);
        return true;
    }

    public List<Tache> getTaches() {
        return new ArrayList<>(taches);
    }

    public boolean estVide() {
        return taches.isEmpty();
    }

    public int nombreTaches() {
        return taches.size();
    }

    // Utilisé lors du chargement du fichier
    public void ajouterTacheChargee(Tache tache) {

        taches.add(tache);

        if (tache.getId() >= prochainId) {
            prochainId = tache.getId() + 1;
        }
    }
}