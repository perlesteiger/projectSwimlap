SwimLap is an Android Application to chronometer swimmers in competition and during training. The principle is based on FFNex data files from the swimming french federation (FFN). The appliaction can parse these files to get informations for each competition. Then, the trainer would be able to chronometer all his swimmers without forgetting one, without taking care of recording. The Android application is also linked too a computer application. At the end of the competition the Android app can send a FFnex file of the meeting by mail. The trainer can put this file in the computer application to be parsed and get statistics and results of all his club swimmers, for all competitions and all seasons.

Pour mettre en place l'application Swimlap :

1. Application Androïd :
  - Aller dans le dossier androïd
  - Ouvrir le projet avec un logiciel Androïd


2. Application web :
  - Mettre tout le dossier sous votre localhost
  - Initialiser la base de données : Exécuter swimlap_bd_functions.sql dans votre base de données Postgres
  - Changer les informations concernant la base de données si nécessaire dans le fichier : model/fonctions.inc.php, dans la fonction connection_bdd()
  - Lancer l'application : aller dans paramètre/fichier et importer quelques fichiers FFNEX présent dans le dossier ffnex_import pour remplir la base de données
