/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.data;

import java.util.HashMap;

public class RaceData {
    private HashMap<Integer, String> styles;
    private HashMap<Integer, Integer> distances;
    private HashMap<Integer, String> genders;
    private HashMap<Integer, Integer> nbRelayers;

    public RaceData() {
        styles = new HashMap<Integer, String>();
        distances = new HashMap<Integer, Integer>();
        genders = new HashMap<Integer, String>();
        nbRelayers = new HashMap<Integer, Integer>();
        makeData();
    }

    public String giveStyle(int raceId) {
        return styles.get(raceId);
    }

    public int giveDistance(int raceId) {
        return distances.get(raceId);
    }

    public String giveGender(int raceId) {
        return genders.get(raceId);
    }

    public int giveNbRelayer(int raceId) {
        int nbToReturn = 1;
        if (nbRelayers.containsKey(raceId)) {
            nbToReturn = nbRelayers.get(raceId);
        }
        return nbToReturn;
    }

    public boolean raceIdExists(int raceId) {
        boolean raceIdExists = false;
        if (distances.containsKey(raceId)) {
            raceIdExists = true;
        }
        return raceIdExists;
    }

//    public boolean isRelay(int raceId) {
//        boolean isRelay = false;
//        if (nbRelayers.containsKey(raceId)) {
//            isRelay = true;
//        }
//        return isRelay;
//    }

    public void makeData() {

        distances.put(100, 25);
        styles.put(100, "FREE");
        genders.put(100, "F");

        distances.put(150, 25);
        styles.put(150, "FREE");
        genders.put(150, "M");

        distances.put(200, 25);
        styles.put(200, "FREE");
        genders.put(200, "MIXED");

        distances.put(1, 50);
        styles.put(1, "FREE");
        genders.put(1, "F");

        distances.put(51, 50);
        styles.put(51, "FREE");
        genders.put(51, "M");

        distances.put(201, 50);
        styles.put(201, "FREE");
        genders.put(201, "MIXED");

        distances.put(2, 100);
        styles.put(2, "FREE");
        genders.put(2, "F");

        distances.put(52, 100);
        styles.put(52, "FREE");
        genders.put(52, "M");

        distances.put(202, 100);
        styles.put(202, "FREE");
        genders.put(202, "MIXED");

        distances.put(3, 200);
        styles.put(3, "FREE");
        genders.put(3, "F");

        distances.put(53, 200);
        styles.put(53, "FREE");
        genders.put(53, "M");

        distances.put(203, 200);
        styles.put(203, "FREE");
        genders.put(203, "MIXED");

        distances.put(4, 400);
        styles.put(4, "FREE");
        genders.put(4, "F");

        distances.put(54, 400);
        styles.put(54, "FREE");
        genders.put(54, "M");

        distances.put(204, 400);
        styles.put(204, "FREE");
        genders.put(204, "MIXED");

        distances.put(5, 800);
        styles.put(5, "FREE");
        genders.put(5, "F");

        distances.put(55, 800);
        styles.put(55, "FREE");
        genders.put(55, "M");

        distances.put(205, 800);
        styles.put(205, "FREE");
        genders.put(205, "MIXED");

        distances.put(7, 1000);
        styles.put(7, "FREE");
        genders.put(7, "F");

        distances.put(57, 1000);
        styles.put(57, "FREE");
        genders.put(57, "M");

        distances.put(207, 1000);
        styles.put(207, "FREE");
        genders.put(207, "MIXED");

        distances.put(6, 1500);
        styles.put(6, "FREE");
        genders.put(6, "F");

        distances.put(56, 1500);
        styles.put(56, "FREE");
        genders.put(56, "M");

        distances.put(206, 1500);
        styles.put(206, "FREE");
        genders.put(206, "MIXED");

        distances.put(16, 3000);
        styles.put(16, "FREE");
        genders.put(16, "F");

        distances.put(66, 3000);
        styles.put(66, "FREE");
        genders.put(66, "M");

        distances.put(216, 3000);
        styles.put(216, "FREE");
        genders.put(216, "MIXED");

        distances.put(15, 5000);
        styles.put(15, "FREE");
        genders.put(15, "F");

        distances.put(65, 5000);
        styles.put(65, "FREE");
        genders.put(65, "M");

        distances.put(215, 5000);
        styles.put(215, "FREE");
        genders.put(215, "MIXED");

        distances.put(110, 25);
        styles.put(110, "BACK");
        genders.put(110, "F");

        distances.put(160, 25);
        styles.put(160, "BACK");
        genders.put(160, "M");

        distances.put(210, 25);
        styles.put(210, "BACK");
        genders.put(210, "MIXED");

        distances.put(11, 50);
        styles.put(11, "BACK");
        genders.put(11, "F");

        distances.put(61, 50);
        styles.put(61, "BACK");
        genders.put(61, "M");

        distances.put(211, 50);
        styles.put(211, "BACK");
        genders.put(211, "MIXED");

        distances.put(12, 100);
        styles.put(12, "BACK");
        genders.put(12, "F");

        distances.put(62, 100);
        styles.put(62, "BACK");
        genders.put(62, "M");

        distances.put(212, 100);
        styles.put(212, "BACK");
        genders.put(212, "MIXED");

        distances.put(13, 200);
        styles.put(13, "BACK");
        genders.put(13, "F");

        distances.put(63, 200);
        styles.put(63, "BACK");
        genders.put(63, "M");

        distances.put(213, 200);
        styles.put(213, "BACK");
        genders.put(213, "MIXED");

        distances.put(120, 25);
        styles.put(120, "BREAST");
        genders.put(120, "F");

        distances.put(170, 25);
        styles.put(170, "BREAST");
        genders.put(170, "M");

        distances.put(220, 25);
        styles.put(220, "BREAST");
        genders.put(220, "MIXED");

        distances.put(21, 50);
        styles.put(21, "BREAST");
        genders.put(21, "F");

        distances.put(71, 50);
        styles.put(71, "BREAST");
        genders.put(71, "M");

        distances.put(221, 50);
        styles.put(221, "BREAST");
        genders.put(221, "MIXED");

        distances.put(22, 100);
        styles.put(22, "BREAST");
        genders.put(22, "F");

        distances.put(72, 100);
        styles.put(72, "BREAST");
        genders.put(72, "M");

        distances.put(222, 100);
        styles.put(222, "BREAST");
        genders.put(222, "MIXED");

        distances.put(23, 200);
        styles.put(23, "BREAST");
        genders.put(23, "F");

        distances.put(73, 200);
        styles.put(73, "BREAST");
        genders.put(73, "M");

        distances.put(223, 200);
        styles.put(223, "BREAST");
        genders.put(223, "MIXED");

        distances.put(130, 25);
        styles.put(130, "FLY");
        genders.put(130, "F");

        distances.put(180, 25);
        styles.put(180, "FLY");
        genders.put(180, "M");

        distances.put(230, 25);
        styles.put(230, "FLY");
        genders.put(230, "MIXED");

        distances.put(31, 50);
        styles.put(31, "FLY");
        genders.put(31, "F");

        distances.put(81, 50);
        styles.put(81, "FLY");
        genders.put(81, "M");

        distances.put(231, 50);
        styles.put(231, "FLY");
        genders.put(231, "MIXED");

        distances.put(32, 100);
        styles.put(32, "FLY");
        genders.put(32, "F");

        distances.put(82, 100);
        styles.put(82, "FLY");
        genders.put(82, "M");

        distances.put(232, 100);
        styles.put(232, "FLY");
        genders.put(232, "MIXED");

        distances.put(33, 200);
        styles.put(33, "FLY");
        genders.put(33, "F");

        distances.put(83, 200);
        styles.put(83, "FLY");
        genders.put(83, "M");

        distances.put(233, 200);
        styles.put(233, "FLY");
        genders.put(233, "MIXED");

        distances.put(40, 100);
        styles.put(40, "IM");
        genders.put(40, "F");

        distances.put(90, 100);
        styles.put(90, "IM");
        genders.put(90, "M");

        distances.put(240, 100);
        styles.put(240, "IM");
        genders.put(240, "MIXED");

        distances.put(41, 200);
        styles.put(41, "IM");
        genders.put(41, "F");

        distances.put(91, 200);
        styles.put(91, "IM");
        genders.put(91, "M");

        distances.put(241, 200);
        styles.put(241, "IM");
        genders.put(241, "MIXED");

        distances.put(42, 400);
        styles.put(42, "IM");
        genders.put(42, "F");

        distances.put(92, 400);
        styles.put(92, "IM");
        genders.put(92, "M");


        distances.put(242, 400);
        styles.put(242, "IM");
        genders.put(242, "MIXED");

        distances.put(8, 100);
        styles.put(8, "4x25_FRE");
        nbRelayers.put(8, 4);
        genders.put(8, "F");

        distances.put(58, 100);
        styles.put(58, "4x25_FRE");
        nbRelayers.put(58, 4);
        genders.put(58, "M");

        distances.put(86, 100);
        styles.put(86, "4x25_FRE");
        nbRelayers.put(86, 4);
        genders.put(86, "MIXED");

        distances.put(47, 100);
        styles.put(47, "4x50_FRE");
        nbRelayers.put(47, 4);
        genders.put(47, "F");

        distances.put(97, 200);
        styles.put(97, "4x50_FRE");
        nbRelayers.put(97, 4);
        genders.put(97, "M");

        distances.put(87, 200);
        styles.put(87, "4x50_FRE");
        nbRelayers.put(87, 4);
        genders.put(87, "MIXED");

        distances.put(43, 400);
        styles.put(43, "4x100_FRE");
        nbRelayers.put(43, 4);
        genders.put(43, "F");

        distances.put(93, 400);
        styles.put(93, "4x100_FRE");
        nbRelayers.put(93, 4);
        genders.put(93, "M");

        distances.put(88, 400);
        styles.put(88, "4x100_FRE");
        nbRelayers.put(88, 4);
        genders.put(88, "MIXED");


        distances.put(44, 800);
        styles.put(44, "4x200_FRE");
        nbRelayers.put(44, 4);
        genders.put(44, "F");

        distances.put(94, 800);
        styles.put(94, "4x200_FREE");
        nbRelayers.put(94, 4);
        genders.put(94, "M");

        distances.put(34, 800);
        styles.put(34, "4x200_FREE");
        nbRelayers.put(34, 4);
        genders.put(34, "MIXED");

        distances.put(111, 200);
        styles.put(111, "4x50_BACK");
        nbRelayers.put(111, 4);
        genders.put(111, "F");

        distances.put(161, 200);
        styles.put(161, "4x50_BACK");
        nbRelayers.put(161, 4);
        genders.put(161, "M");

        distances.put(39, 100);
        styles.put(39, "4x25_MED");
        nbRelayers.put(39, 4);
        genders.put(39, "F");

        distances.put(89, 100);
        styles.put(89, "4x25_MED");
        nbRelayers.put(89, 4);
        genders.put(89, "M");

        distances.put(38, 100);
        styles.put(38, "4x25_MED");
        nbRelayers.put(38, 4);
        genders.put(38, "MIXED");

        distances.put(121, 200);
        styles.put(121, "4x50_BRE");
        nbRelayers.put(121, 4);
        genders.put(121, "F");

        distances.put(171, 200);
        styles.put(171, "4x50_BRE");
        nbRelayers.put(171, 4);
        genders.put(171, "M");

        distances.put(48, 200);
        styles.put(48, "4x50_MED");
        nbRelayers.put(48, 4);
        genders.put(48, "F");

        distances.put(98, 200);
        styles.put(98, "4x50_MED");
        nbRelayers.put(98, 4);
        genders.put(98, "M");

        distances.put(37, 200);
        styles.put(37, "4x50_MED");
        nbRelayers.put(37, 4);
        genders.put(37, "MIXED");

        distances.put(131, 200);
        styles.put(131, "4x50_FLY");
        nbRelayers.put(131, 4);
        genders.put(131, "F");

        distances.put(181, 200);
        styles.put(181, "4x50_FLY");
        nbRelayers.put(181, 4);
        genders.put(181, "M");

        distances.put(49, 300);
        styles.put(49, "6x50_FRE");
        nbRelayers.put(49, 6);
        genders.put(49, "F");

        distances.put(99, 300);
        styles.put(99, "6x50_FRE");
        nbRelayers.put(99, 6);
        genders.put(99, "M");

        distances.put(35, 300);
        styles.put(35, "6x50_FRE");
        nbRelayers.put(35, 6);
        genders.put(35, "MIXED");

        distances.put(46, 400);
        styles.put(46, "4x100_MED");
        nbRelayers.put(46, 4);
        genders.put(46, "F");

        distances.put(96, 400);
        styles.put(96, "4x100_MED");
        nbRelayers.put(96, 4);
        genders.put(96, "M");

        distances.put(36, 400);
        styles.put(36, "4x100_MED");
        nbRelayers.put(36, 4);
        genders.put(36, "MIXED");

        distances.put(9, 500);
        styles.put(9, "10x50_FRE");
        nbRelayers.put(9, 10);
        genders.put(9, "F");

        distances.put(59, 500);
        styles.put(59, "10x50_FRE");
        nbRelayers.put(59, 500);
        genders.put(59, "M");

        distances.put(84, 500);
        styles.put(84, "10x50_FRE");
        nbRelayers.put(84, 10);
        genders.put(84, "MIXED");

        distances.put(14, 800);
        styles.put(14, "8x100_FRE");
        nbRelayers.put(14, 8);
        genders.put(14, "F");

        distances.put(64, 800);
        styles.put(64, "8x100_FRE");
        nbRelayers.put(64, 8);
        genders.put(64, "M");

        distances.put(214, 800);
        styles.put(214, "8x100_FRE");
        nbRelayers.put(217, 8);
        genders.put(214, "MIXED");

        distances.put(45, 1000);
        styles.put(45, "10x100_FRE");
        nbRelayers.put(45, 10);
        genders.put(45, "F");

        distances.put(95, 1000);
        styles.put(95, "10x100_FRE");
        nbRelayers.put(95, 10);
        genders.put(95, "M");

        distances.put(85, 1000);
        styles.put(85, "10x100_FRE");
        nbRelayers.put(85, 10);
        genders.put(85, "MIXED");
    }
}
