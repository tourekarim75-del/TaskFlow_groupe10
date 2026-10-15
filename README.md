# Tâche Simple — TaskFlow

Application Java en ligne de commande permettant de gérer simplement une liste de tâches.

Ce projet a été réalisé en équipe afin de mettre en pratique les concepts fondamentaux de la programmation orientée objet en Java, notamment les classes, les objets, les énumérations, les collections, la gestion des fichiers et la gestion des exceptions.

## Membres de l'équipe

- AGOUA SOPIE DORYNE
- KOLIÉ ODILE HAOUSSA
- KELOMÉ BRIAN
- TOURÉ KARIM
- TRABI EMMANUEL

## Fonctionnalités

L'application permet de :

- Ajouter une nouvelle tâche
- Afficher toutes les tâches
- Marquer une tâche comme terminée
- Modifier la priorité d'une tâche
- Ajouter ou modifier une échéance
- Retirer l'échéance d'une tâche
- Sauvegarder les tâches dans un fichier
- Charger les tâches sauvegardées
- Gérer les erreurs de saisie de l'utilisateur

## Priorités disponibles

Chaque tâche peut avoir l'une des priorités suivantes :

- BASSE
- NORMALE
- ELEVEE

## Gestion des échéances

Le format utilisé pour les dates est :

```text
AAAA-MM-JJ
```

Exemple :

```text
2026-10-30
```

## Structure du projet

```text
TaskFlow_Equipe10/
├── README.md
└── src/
    ├── data/
    │   └── taches.txt
    └── taskflow/
        ├── Main.java
        ├── ConsoleUI.java
        ├── GestionnaireTaches.java
        ├── GestionFichier.java
        ├── Tache.java
        └── Priorite.java
```

## Description des classes

### Main.java

Point d'entrée de l'application. Il charge les tâches enregistrées, crée le gestionnaire de tâches et lance l'interface console.

### ConsoleUI.java

Gère les interactions entre l'utilisateur et l'application. Cette classe affiche le menu et récupère les choix de l'utilisateur.

### GestionnaireTaches.java

Contient la logique principale de gestion des tâches : ajout, recherche, modification de la priorité, gestion de l'échéance et changement du statut d'une tâche.

### Tache.java

Représente une tâche avec ses différentes informations : identifiant, titre, priorité, échéance et statut.

### Priorite.java

Énumération représentant les niveaux de priorité disponibles : BASSE, NORMALE et ELEVEE.

### GestionFichier.java

Gère la sauvegarde et le chargement des tâches dans un fichier texte.

## Menu principal

```text
TASKFLOW — Gestion des tâches

1. Ajouter une tâche
2. Afficher les tâches
3. Marquer une tâche comme terminée
4. Modifier la priorité d'une tâche
5. Gérer l'échéance d'une tâche
6. Sauvegarder les données
0. Quitter
```

## Prérequis

Pour utiliser le projet, il faut disposer de :

- Java JDK
- Un terminal
- Windows, macOS ou Linux

Pour vérifier l'installation de Java :

```bash
java -version
javac -version
```

## Compilation et exécution

Depuis le dossier principal du projet :

```bash
cd TaskFlow_Equipe10
cd src
javac taskflow/*.java
java taskflow.Main
```

## Sauvegarde des données

Les tâches sont enregistrées dans :

```text
data/taches.txt
```

Le fichier utilise le format suivant :

```text
ID;Titre;Priorité;Échéance;Statut
```

Exemple :

```text
1;Faire le devoir;ELEVEE;2026-10-30;false
```

## Gestion des erreurs

L'application gère notamment :

- les choix de menu invalides ;
- les identifiants inexistants ou non numériques ;
- les titres vides ;
- les priorités invalides ;
- les dates invalides ;
- les erreurs de lecture et d'écriture du fichier.

## Technologies et notions utilisées

- Java
- Programmation orientée objet (POO)
- ArrayList
- LocalDate
- Scanner
- Enum
- Lecture et écriture de fichiers
- Gestion des exceptions

## Objectifs pédagogiques

Ce projet permet de mettre en pratique :

- la création de classes et d'objets ;
- l'encapsulation ;
- les constructeurs et les méthodes ;
- les collections ;
- les énumérations ;
- la séparation des responsabilités entre les classes ;
- la lecture et l'écriture de fichiers ;
- la validation des entrées utilisateur ;
- la gestion des exceptions ;
- l'organisation d'un projet Java en packages.

## Équipe 10

Projet réalisé par :

**AGOUA SOPIE DORYNE**  
**KOLIÉ ODILE HAOUSSA**  
**KELOMÉ BRIAN**  
**TOURÉ KARIM**  
**TRABI EMMANUEL**

## Licence

Projet réalisé dans un cadre académique et pédagogique.
