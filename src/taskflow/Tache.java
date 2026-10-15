package taskflow;

import java.time.LocalDate;

public class Tache {

    private final int id;
    private final String titre;
    private Priorite priorite;
    private LocalDate echeance;
    private boolean terminee;

    public Tache(int id, String titre, Priorite priorite,
                 LocalDate echeance, boolean terminee) {

        if (id <= 0) {
            throw new IllegalArgumentException("L'identifiant doit être positif.");
        }

        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre est obligatoire.");
        }

        this.id = id;
        this.titre = titre.trim();
        this.priorite = priorite == null ? Priorite.NORMALE : priorite;
        this.echeance = echeance;
        this.terminee = terminee;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public LocalDate getEcheance() {
        return echeance;
    }

    public boolean isTerminee() {
        return terminee;
    }

    public void setPriorite(Priorite priorite) {
        if (priorite == null) {
            throw new IllegalArgumentException("La priorité est obligatoire.");
        }

        this.priorite = priorite;
    }

    public void setEcheance(LocalDate echeance) {
        this.echeance = echeance;
    }

    public void terminer() {
        this.terminee = true;
    }

    public String getStatut() {
        return terminee ? "Terminée" : "À faire";
    }

    @Override
    public String toString() {

        String date = echeance == null
                ? "Aucune"
                : echeance.toString();

        return "ID : " + id
                + " | Titre : " + titre
                + " | Priorité : " + priorite
                + " | Échéance : " + date
                + " | Statut : " + getStatut();
    }
}