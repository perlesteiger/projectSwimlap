<?php
// Connexion, sélection de la base de données
/*    function connect_bdd() {
            $host = 'localhost';
            $dbname = 'swimlapbd';
            $user = 'admin';
            $password = 'admin';

            $dbconn = pg_connect("host=".$host."
                                  dbname=".$dbname."
                                  user=".$user."
                                  password=".$password)
                    or die('Connexion impossible : ' . pg_last_error());

            return $dbconn;
    }*/

// Connexion, sélection de la base de données
function connect_bdd() {
    $host = 'localhost';
    $dbname = 'swimlap';
    $user = 'postgres';
    $password = 'postgres';

    $dbconn = pg_connect("host=".$host." 
                          dbname=".$dbname." 
                          user=".$user." 
                          password=".$password)
        or die('Connexion impossible : ' . pg_last_error());
    
    return $dbconn;
}

//recuperer le nom du club
function recoverClub() {
    
    $dbconn = connect_bdd();
    
    // Exécution de la requête SQL
    $query = 'SELECT clu_name, clu_id FROM t_e_club_clu';
    $result = pg_query($query) or die('Échec de la requête : ' . pg_last_error());
    
    while ($line = pg_fetch_object($result)) {
        $club = $line;
    }
    
    // Ferme la connexion
    pg_close($dbconn);  
    
    return $club;
}

//modifier le nom du club
function updateClub($new_name, $id) {
    
    $dbconn = connect_bdd();
    
    // Exécution de la requête SQL
    $query = "UPDATE t_e_club_clu SET clu_name = '$new_name' WHERE clu_id = '$id'";
    $result = pg_query($query) or die('Échec de la requête : ' . pg_last_error());
    
    // Ferme la connexion
    pg_close($dbconn);  
    
    return $result;
}

//recuperer les nageurs
function recoverSwimmer() {
    
    $dbconn = connect_bdd();
    
    // Exécution de la requête SQL
    $query = 'SELECT swi_firstname, swi_lastname, swi_dateofbirth, swi_id FROM t_e_swimmer_swi';
    $result = pg_query($query) or die('Échec de la requête : ' . pg_last_error());
    
    $list_swimmer = array();
    
    while ($line = pg_fetch_object($result)) {
        array_push($list_swimmer, $line->swi_lastname."|".$line->swi_firstname."|".$line->swi_dateofbirth."|".$line->swi_id);
    }
    
    // Ferme la connexion
    pg_close($dbconn);  
    
    return $list_swimmer;
}

//ajouter un nageur
function addSwimmer($lastname, $firstname, $id, $sexe, $date) {
    
    $dbconn = connect_bdd();
    
    // Exécution de la requête SQL
    $query = "INSERT INTO t_e_swimmer_swi VALUES ('$id', '$firstname', '$lastname', '$date', '$sexe', '1209')";
    $result = pg_query($query) or die('Échec de la requête : ' . pg_last_error());
    
    // Ferme la connexion
    pg_close($dbconn);  
    
    return $result;
}

//recuperer les records
function recoverRecord() {
    
    $dbconn = connect_bdd();
    
    // Exécution de la requête SQL
    $query = 'SELECT rac_name, rec_swimtime_25, rec_swimtime_50, swi_lastname, swi_firstname FROM t_j_record_rec 
                JOIN t_e_swimmer_swi ON rec_swi_id = swi_id
                JOIN t_e_race_rac ON rec_rac_id = rac_id';
    $result = pg_query($query) or die('Échec de la requête : ' . pg_last_error());
    
    $list_record = array();
    
    while ($line = pg_fetch_object($result)) {
        if (empty($line->rec_swimtime_25)) $pool25 = 'pas de temps enregistré';
        else $pool25 = $line->rec_swimtime_25;
        if (empty($line->rec_swimtime_50)) $pool50 = 'pas de temps enregistré';
        else $pool50 = $line->rec_swimtime_50;
        array_push($list_record, $line->swi_lastname." ".$line->swi_firstname."|".$pool25."|".$pool50."|".$line->rac_name);
    }
    
    // Ferme la connexion
    pg_close($dbconn);  
    
    return $list_record;
}

//ajouter un record
function addRecord($record,$id_swim,$id_race,$pool) {
    
    $dbconn = connect_bdd();

    $query = "INSERT INTO t_j_record_rec (rec_swi_id, rec_rac_id, rec_swimtime_$pool) VALUES ($id_swim, $id_race, $record)";
    $result = pg_query($query) or die('Échec de la requête : ' . pg_last_error());
    
    // Ferme la connexion
    pg_close($dbconn); 
    
    return $result;
    
}

//recuperer les competitions
function recoverCompetition() {
    
    $dbconn = connect_bdd();
    
    // Exécution de la requête SQL
    $query = 'SELECT mee_name, mee_city, mee_start_date, mee_end_date, mee_id FROM t_e_meeting_mee ORDER BY mee_start_date DESC';
    $result = pg_query($query) or die('Échec de la requête : ' . pg_last_error());
    
    $list_competition = array();
    
    while ($line = pg_fetch_object($result)) {
        array_push($list_competition, $line->mee_name."|".$line->mee_city."|".$line->mee_start_date."|".$line->mee_end_date."|".$line->mee_id);
    }
    
    // Ferme la connexion
    pg_close($dbconn);  
    
    return $list_competition;
}

//ajouter une competition
function addCompetition($id,$name,$begin,$end,$city,$pool,$season) {
    
    $dbconn = connect_bdd();
    
  // Exécution de la requête SQL
    $query = "INSERT INTO t_e_meeting_mee VALUES ('$id', '$name', '$city', '$begin', '$end', '$pool', '$season')";
    $result = pg_query($query) or die('Échec de la requête : ' . pg_last_error());
    
    // Ferme la connexion
    pg_close($dbconn);  
    
    return $result;
}

//recuperer les saisons
function recoverSeason() {
    
    $dbconn = connect_bdd();
    
    // Exécution de la requête SQL
    $query = 'SELECT sea_name, sea_id FROM t_e_season_sea ORDER BY sea_start_date DESC';
    $result = pg_query($query) or die('Échec de la requête : ' . pg_last_error());
    
    while ($line = pg_fetch_object($result)) {
        $list_season[$line->sea_id] = $line->sea_name; 
    }
    
    // Ferme la connexion
    pg_close($dbconn);  
    
    return $list_season;
}

//recuperer les races
function recoverRace() {
    
    $dbconn = connect_bdd();
    
    // Exécution de la requête SQL
    $query = 'SELECT rac_name, rac_id FROM t_e_race_rac';
    $result = pg_query($query) or die('Échec de la requête : ' . pg_last_error());
    
    while ($line = pg_fetch_object($result)) {
        $list_race[$line->rac_id] = $line->rac_name; 
    }
    
    // Ferme la connexion
    pg_close($dbconn);  
    
    return $list_race;
}

//recuperer les nageurs pour un meeting
function recoverSwimmerMeeting($id_meeting) {
    
    $dbconn = connect_bdd();
    
    // Exécution de la requête SQL
    $query = 'SELECT DISTINCT swi.swi_firstname, swi.swi_lastname, swi.swi_id 
                FROM t_e_meeting_mee mee
                    JOIN t_e_event_eve eve ON mee.mee_id = eve.eve_mee_id
                    JOIN t_j_result_res res ON eve.eve_id = res.res_eve_id
                    JOIN t_e_swimmer_swi swi ON res.res_swi_id = swi.swi_id';
             if (!empty($id_meeting))
               $query .= ' WHERE mee.mee_id = '.$id_meeting.'';
    $result = pg_query($query) or die('Échec de la requête : ' . pg_last_error());
    
    $list_swimmer = array();
    
    while ($line = pg_fetch_object($result)) {
        $list_swimmer[]= array('swimmer'=>$line->swi_lastname.' '.$line->swi_firstname, 'swimmer_id'=>$line->swi_id);
    }
    
    // Ferme la connexion
    pg_close($dbconn);  
    
    return $list_swimmer;
}

//recuperer les races pour un meeting
function recoverRaceMeeting($id_meeting) {
    
    $dbconn = connect_bdd();
    
    // Exécution de la requête SQL
    $query = 'SELECT DISTINCT rac.rac_name, rac.rac_id 
                FROM t_e_meeting_mee mee
                    JOIN t_e_event_eve eve ON mee.mee_id = eve.eve_mee_id
                    JOIN t_e_race_rac rac ON eve.eve_rac_id = rac.rac_id';
             if (!empty($id_meeting))
               $query .= ' WHERE mee.mee_id = '.$id_meeting.'';
    $result = pg_query($query) or die('Échec de la requête : ' . pg_last_error());
    
    while ($line = pg_fetch_object($result)) {
        $list_race[]= array('race_id'=>$line->rac_id, 'race'=>$line->rac_name); 
    }
    
    // Ferme la connexion
    pg_close($dbconn);  
    
    return $list_race;
}
?>
