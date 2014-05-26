/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

public class ClubModel {

    private int id;
    private String name;
    private int codeFFN;

    public ClubModel(){

    }

    public ClubModel(int id, int code) {
        this.id = id;
        this.codeFFN = code;
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

    public int getCodeFFN() {
        return codeFFN;
    }
}
