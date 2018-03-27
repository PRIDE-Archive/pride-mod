package uk.ac.ebi.pride.utilities.pridemod.utils;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * This class contains important constants that are need on all classes and packages.
 */
public class Constants {

    public static final String UNIMOD_TAG = "UniMod:";

    public enum Database {
        PSIMOD, UNIMOD, MS, NONE
    }
    public static Database getAccessionType(String accession){
        if(accession.contains("UNIMOD")){
            return Database.UNIMOD;
        } else if(!accession.contains("UNIMOD") && accession.contains("MOD")){
            return Database.PSIMOD;
        } else if(!accession.contains("MOD") && NumberUtils.isNumber(accession)){
            return Database.UNIMOD;
        } else if(accession.contains("MS")){
            return Database.MS;
        }else{
            return Database.NONE;
        }
    }
}
