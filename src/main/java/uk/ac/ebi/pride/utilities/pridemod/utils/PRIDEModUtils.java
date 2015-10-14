package uk.ac.ebi.pride.utilities.pridemod.utils;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * yperez
 */
public class PRIDEModUtils {

    public enum Database {
        PSIMOD, UNIMOD, NONE
    }
    public static Database getAccessionType(String accession){
        if(accession.contains("UNIMOD")){
            return Database.UNIMOD;
        } else if(!accession.contains("UNIMOD") && accession.contains("MOD")){
            return Database.PSIMOD;
        } else if(!accession.contains("MOD") && NumberUtils.isNumber(accession)){
            return Database.UNIMOD;
        } else{
            return Database.NONE;
        }
    }
}
