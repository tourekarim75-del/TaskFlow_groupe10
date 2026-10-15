package taskflow;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class GestionFichier {

    private static final String FICHIER = "data/taches.txt";

    public static void sauvegarder(List<Tache> taches) throws IOException {

        File fichier = new File(FICHIER);

        File dossier = fichier.getParentFile();

        if (dossier != null && !dossier.exists()) {
            dossier.mkdirs();
        }

        try (PrintWriter writer = new PrintWriter(
                new FileWriter(fichier))) {

            for (Tache tache : taches) {

                String echeance = tache.getEcheance() == null
                        ? ""
                        : tache.getEcheance().toString();

                writer.println(
                        tache.getId() + ";"
                                + tache.getTitre() + ";"
                                + tache.getPriorite().name() + ";"
                                + echeance + ";"
                                + tache.isTerminee()
                );
            }
        }
    }

    public static GestionnaireTaches charger()
            throws IOException {

        GestionnaireTaches gestionnaire =
                new GestionnaireTaches();

        File fichier = new File(FICHIER);

        if (!fichier.exists()) {
            return gestionnaire;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(fichier))) {

            String ligne;

            while ((ligne = reader.readLine()) != null) {

                if (ligne.trim().isEmpty()) {
                    continue;
                }

                String[] parties = ligne.split(";", -1);

                if (parties.length != 5) {
                    throw new IOException(
                            "Le fichier de données est mal formé."
                    );
                }

                try {

                    int id = Integer.parseInt(parties[0]);

                    String titre = parties[1];

                    Priorite priorite =
                            Priorite.valueOf(parties[2]);

                    LocalDate echeance = null;

                    if (!parties[3].isEmpty()) {
                        echeance =
                                LocalDate.parse(parties[3]);
                    }

                    boolean terminee =
                            Boolean.parseBoolean(parties[4]);

                    Tache tache = new Tache(
                            id,
                            titre,
                            priorite,
                            echeance,
                            terminee
                    );

                    gestionnaire.ajouterTacheChargee(tache);

                } catch (Exception e) {

                    throw new IOException(
                            "Le fichier de données est illisible ou mal formé."
                    );
                }
            }
        }

        return gestionnaire;
    }
}