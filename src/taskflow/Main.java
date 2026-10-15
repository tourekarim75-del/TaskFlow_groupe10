package taskflow;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                "TASKFLOW — Gestion des tâches"
        );

        GestionnaireTaches gestionnaire;

        try {

            gestionnaire =
                    GestionFichier.charger();

            if (gestionnaire.nombreTaches() == 0) {

                System.out.println(
                        "\nAucun fichier de données trouvé "
                        + "ou aucune tâche enregistrée."
                );

                System.out.println(
                        "L'application démarre avec une liste vide."
                );

            } else {

                System.out.println(
                        "\nChargement du fichier de données..."
                );

                System.out.println(
                        gestionnaire.nombreTaches()
                                + " tâches chargées avec succès."
                );
            }

        } catch (IOException e) {

            System.out.println(
                    "\nErreur : le fichier de données est illisible ou mal formé."
            );

            System.out.println(
                    "Démarrage interrompu. "
                    + "Le fichier n'a pas été modifié."
            );

            return;
        }

        ConsoleUI console =
                new ConsoleUI(gestionnaire);

        console.demarrer();
    }
}