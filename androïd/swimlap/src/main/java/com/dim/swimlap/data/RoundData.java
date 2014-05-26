/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.data;

import java.util.HashMap;

public class RoundData {
    private HashMap<Integer, String> round;

    public RoundData() {
        this.round = new HashMap<Integer, String>();
        makeData();
    }

    private void makeData() {
        round.put(11, "Finale A");
        round.put(12, "Finale B");
        round.put(13, "Finale C");
        round.put(14, "Finale D");
        round.put(31, "1/2 Finale (1)");
        round.put(32, "1/2 Finale (2)");
        round.put(39, "Barrage 1/2 Finales");
        round.put(41, "1/4 Finale (1)");
        round.put(42, "1/4 Finale (2)");
        round.put(43, "1/4 Finale (3)");
        round.put(44, "1/4 Finale (4)");
        round.put(49, "Barrage 1/4 Finales");
        round.put(51, "1/8 Finale (1)");
        round.put(52, "1/8 Finale (2)");
        round.put(53, "1/8 Finale (3)");
        round.put(54, "1/8 Finale (4)");
        round.put(55, "1/8 Finale (5)");
        round.put(56, "1/8 Finale (6)");
        round.put(57, "1/8 Finale (7)");
        round.put(58, "1/8 Finale (8)");
        round.put(59, "Barrage 1/8 Finales");
        round.put(60, "Séries");
        round.put(61, "Série rapide");
        round.put(62, "Séries lentes");
    }

    public String getRoundName(int roundId) {
        return round.get(roundId);
    }
}
