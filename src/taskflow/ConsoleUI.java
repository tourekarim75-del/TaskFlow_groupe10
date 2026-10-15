package taskflow;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleUI {

    private final GestionnaireTaches gestionnaire;
    private final Scanner scanner;

    public ConsoleUI(GestionnaireTaches gestionnaire) {
        this.gestionnaire = gestionnaire;
        this.scanner = new Scanner(System.in);
    }

    public void demarrer() {

        int choix = -1;

        while (choix != 0) {

            afficherMenu();

            System.out.print("Votre choix : ");

            String saisie = scanner.nextLine();

            try {
                choix = Integer.parseInt(saisie);
            } catch (NumberFormatException e) {
                System.out.println("Erreur : choix invalide.");
                continue;
            }

            switch (choix) {

                case 1:
                    ajouterTache();
                    break;

                case 2:
                    afficherTaches();
                    break;

                case 3:
                    terminerTache();
                    break;

                case 4:
                    modifierPriorite();
                    break;

                case 5:
                    gererEcheance();
                    break;

                case 6:
                    sauvegarder();
                    break;

                case 0:
                    System.out.println("\nFermeture de TaskFlow.");
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Erreur : choix invalide.");
            }
        }
    }

    private void afficherMenu() {

        System.out.println();
        System.out.println("TASKFLOW — Gestion des tâches");
        System.out.println();
        System.out.println("1. Ajouter une tâche");
        System.out.println("2. Afficher les tâches");
        System.out.println("3. Marquer une tâche comme terminée");
        System.out.println("4. Modifier la priorité d'une tâche");
        System.out.println("5. Gérer l'échéance d'une tâche");
        System.out.println("6. Sauvegarder les données");
        System.out.println("0. Quitter");
        System.out.println();
    }

    // ==============================
    // AJOUTER UNE TÂCHE
    // ==============================

    private void ajouterTache() {

        System.out.println("\nAJOUTER UNE TÂCHE");

        System.out.print("\nTitre : ");
        String titre = scanner.nextLine();

        if (titre.trim().isEmpty()) {
            System.out.println(
                    "Erreur : le titre est obligatoire."
            );
            return;
        }

        System.out.println(
                "\nPriorité : Basse, Normale ou Élevée"
        );

        System.out.println(
                "Appuyez sur Entrée pour choisir Normale."
        );

        System.out.print("Votre priorité : ");
        String textePriorite = scanner.nextLine();

        Priorite priorite;

        try {

            priorite =
                    Priorite.depuisTexte(textePriorite);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Erreur : " + e.getMessage()
            );

            return;
        }

        System.out.println(
                "\nÉchéance au format AAAA-MM-JJ"
        );

        System.out.println(
                "Appuyez sur Entrée pour ne pas préciser d'échéance."
        );

        System.out.print("Votre échéance : ");

        String texteDate = scanner.nextLine();

        LocalDate echeance = null;

        if (!texteDate.trim().isEmpty()) {

            try {

                echeance =
                        LocalDate.parse(texteDate.trim());

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Erreur : date ou format invalide."
                );

                System.out.println(
                        "Le format attendu est AAAA-MM-JJ."
                );

                return;
            }
        }

        Tache tache = gestionnaire.ajouterTache(
                titre,
                priorite,
                echeance
        );

        System.out.println(
                "\nTâche ajoutée avec succès."
        );

        afficherDetails(tache);
    }

    // ==============================
    // AFFICHER
    // ==============================

    private void afficherTaches() {

        System.out.println("\nLISTE DES TÂCHES\n");

        if (gestionnaire.estVide()) {

            System.out.println(
                    "Aucune tâche à afficher."
            );

            return;
        }

        for (Tache tache :
                gestionnaire.getTaches()) {

            afficherDetails(tache);

            System.out.println("-------------------------");
        }

        System.out.println(
                "Nombre de tâches : "
                        + gestionnaire.nombreTaches()
        );
    }

    // ==============================
    // TERMINER
    // ==============================

    private void terminerTache() {

        System.out.println(
                "\nMARQUER UNE TÂCHE COMME TERMINÉE"
        );

        Integer id = demanderId();

        if (id == null) {
            return;
        }

        Tache tache =
                gestionnaire.trouverTache(id);

        if (tache == null) {

            System.out.println(
                    "Erreur : aucune tâche ne correspond à l'identifiant "
                            + id + "."
            );

            return;
        }

        if (tache.isTerminee()) {

            System.out.println(
                    "La tâche " + id
                            + " est déjà terminée."
            );

            return;
        }

        gestionnaire.terminerTache(id);

        System.out.println(
                "La tâche « "
                        + tache.getTitre()
                        + " » est maintenant terminée."
        );
    }

    // ==============================
    // PRIORITÉ
    // ==============================

    private void modifierPriorite() {

        System.out.println(
                "\nMODIFIER LA PRIORITÉ"
        );

        Integer id = demanderId();

        if (id == null) {
            return;
        }

        Tache tache =
                gestionnaire.trouverTache(id);

        if (tache == null) {

            System.out.println(
                    "Erreur : aucune tâche ne correspond à l'identifiant "
                            + id + "."
            );

            return;
        }

        System.out.println(
                "Priorité actuelle : "
                        + tache.getPriorite()
        );

        System.out.print(
                "Nouvelle priorité : "
        );

        String saisie =
                scanner.nextLine();

        try {

            Priorite nouvelle =
                    Priorite.depuisTexte(saisie);

            Priorite ancienne =
                    tache.getPriorite();

            gestionnaire.modifierPriorite(
                    id,
                    nouvelle
            );

            System.out.println(
                    "La priorité de la tâche "
                            + id
                            + " a été modifiée : "
                            + ancienne
                            + " → "
                            + nouvelle
                            + "."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Erreur : la priorité doit être Basse, Normale ou Élevée."
            );

            System.out.println(
                    "La priorité actuelle est conservée."
            );
        }
    }

    // ==============================
    // ÉCHÉANCE
    // ==============================

    private void gererEcheance() {

        System.out.println(
                "\nGÉRER L'ÉCHÉANCE"
        );

        Integer id = demanderId();

        if (id == null) {
            return;
        }

        Tache tache =
                gestionnaire.trouverTache(id);

        if (tache == null) {

            System.out.println(
                    "Erreur : aucune tâche ne correspond à l'identifiant "
                            + id + "."
            );

            return;
        }

        System.out.println(
                "Échéance actuelle : "
                        + (tache.getEcheance() == null
                        ? "Aucune"
                        : tache.getEcheance())
        );

        System.out.println();
        System.out.println(
                "1. Définir ou modifier l'échéance"
        );
        System.out.println(
                "2. Retirer l'échéance"
        );
        System.out.println(
                "0. Annuler"
        );

        System.out.print(
                "\nVotre action : "
        );

        String action =
                scanner.nextLine();

        switch (action) {

            case "1":
                definirEcheance(tache);
                break;

            case "2":

                gestionnaire.retirerEcheance(
                        tache.getId()
                );

                System.out.println(
                        "L'échéance de la tâche "
                                + tache.getId()
                                + " a été retirée."
                );

                break;

            case "0":
                System.out.println(
                        "Opération annulée."
                );
                break;

            default:
                System.out.println(
                        "Erreur : choix invalide."
                );
        }
    }

    private void definirEcheance(Tache tache) {

        System.out.print(
                "Nouvelle échéance : "
        );

        String texte =
                scanner.nextLine();

        try {

            LocalDate date =
                    LocalDate.parse(texte);

            gestionnaire.modifierEcheance(
                    tache.getId(),
                    date
            );

            System.out.println(
                    "L'échéance de la tâche "
                            + tache.getId()
                            + " est maintenant : "
                            + date
                            + "."
            );

        } catch (DateTimeParseException e) {

            System.out.println(
                    "Erreur : « "
                            + texte
                            + " » n'est pas une date valide."
            );

            System.out.println(
                    "Le format attendu est AAAA-MM-JJ."
            );

            System.out.println(
                    "L'échéance actuelle est conservée."
            );
        }
    }

    // ==============================
    // SAUVEGARDE
    // ==============================

    private void sauvegarder() {

        System.out.println(
                "\nSAUVEGARDE DES DONNÉES"
        );

        try {

            GestionFichier.sauvegarder(
                    gestionnaire.getTaches()
            );

            System.out.println(
                    "\nSauvegarde réussie : "
                            + gestionnaire.nombreTaches()
                            + " tâches enregistrées."
            );

        } catch (IOException e) {

            System.out.println(
                    "Erreur : impossible d'écrire dans le fichier de données."
            );

            System.out.println(
                    "Les modifications n'ont pas été sauvegardées."
            );
        }
    }

    // ==============================
    // UTILITAIRES
    // ==============================

    private Integer demanderId() {

        System.out.print(
                "\nIdentifiant de la tâche : "
        );

        try {

            return Integer.parseInt(
                    scanner.nextLine()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Erreur : l'identifiant doit être un nombre."
            );

            return null;
        }
    }

    private void afficherDetails(Tache tache) {

        System.out.println();
        System.out.println(
                "Identifiant : " + tache.getId()
        );

        System.out.println(
                "Titre : " + tache.getTitre()
        );

        System.out.println(
                "Priorité : " + tache.getPriorite()
        );

        System.out.println(
                "Échéance : "
                        + (tache.getEcheance() == null
                        ? "Aucune"
                        : tache.getEcheance())
        );

        System.out.println(
                "Statut : " + tache.getStatut()
        );
    }
}