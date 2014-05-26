/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

import com.dim.swimlap.data.RoundData;

public class RoundModel {

    private int id;
    private String name;

    public RoundModel(int id) {
        this.id = id;
        buidContent();
    }

    private void buidContent() {
        RoundData roundData = new RoundData();
        this.name = roundData.getRoundName(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
