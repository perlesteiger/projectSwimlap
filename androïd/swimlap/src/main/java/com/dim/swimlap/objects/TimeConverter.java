/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap.objects;

public class TimeConverter {

    public String makeString(float timeInMilli) {
        int milliSec = (int) timeInMilli % 1000;
        String milliInString = String.valueOf(milliSec);
        milliInString += "00";
        milliInString = milliInString.substring(0, 2);
        int secondsTotal = (int) (timeInMilli - milliSec) / 1000;
        int seconds = secondsTotal % 60;
        int minutes = (secondsTotal - seconds) / 60;
        String zeroForMinutes = "", zeroForSeconds = "";
        if (minutes < 10) {
            zeroForMinutes = "0";
        }
        if (seconds < 10) {
            zeroForSeconds = "0";
        }
        return zeroForMinutes + minutes + ":" + zeroForSeconds + seconds + "." + milliInString;
    }

    public String makeForFFNex(float timeInMilli) {
        float milliSec = (int) timeInMilli % 1000;
        float hundredtSec = Math.round(milliSec / 10);

        float secondsTotal = (int) (timeInMilli - milliSec) / 1000;
        float seconds = secondsTotal % 60;
        float minutes = (secondsTotal - seconds) / 60;

        float timeFFNex = minutes + seconds / 100 + hundredtSec / 10000;

        String toReturn = String.valueOf(timeFFNex);
        toReturn += "0000";
        if(minutes>9){
            toReturn = toReturn.substring(0, 7);
        }else{
            toReturn = toReturn.substring(0, 6);
        }
        return toReturn;
    }
}
