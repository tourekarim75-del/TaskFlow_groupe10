package taskflow;

public enum Priorite {
    BASSE("Basse"),
    NORMALE("Normale"),
    ELEVEE("Élevée");

    private final String affichage;

    Priorite(String affichage) {
        this.affichage = affichage;
    }

    @Override
    public String toString() {
        return affichage;
    }

    public static Priorite depuisTexte(String texte) {
        if (texte == null || texte.trim().isEmpty()) {
            return NORMALE;
        }

        String valeur = texte.trim()
                .toLowerCase()
                .replace("é", "e");

        switch (valeur) {
            case "basse":
                return BASSE;
            case "normale":
                return NORMALE;
            case "elevee":
                return ELEVEE;
            default:
                throw new IllegalArgumentException(
                        "La priorité doit être Basse, Normale ou Élevée."
                );
        }
    }
}