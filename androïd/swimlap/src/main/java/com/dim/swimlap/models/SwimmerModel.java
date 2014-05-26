/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.models;

public class SwimmerModel {
    private int idFFN;
    private String name;
    private String firstname;
    private String dateOfBirth;
    private String gender;

    private ClubModel clubModel;

    public SwimmerModel(){
    }
    public SwimmerModel(int idFFN){
        this.idFFN = idFFN;
    }

    public int getIdFFN() {
        return idFFN;
    }

    public void setIdFFN(int idFFN) {
        this.idFFN = idFFN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ClubModel getClubModel() {
        return clubModel;
    }

    public void setClubModel(ClubModel clubModel) {
        this.clubModel = clubModel;
    }


}
