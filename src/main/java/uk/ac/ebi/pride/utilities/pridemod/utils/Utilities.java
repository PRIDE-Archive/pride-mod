package uk.ac.ebi.pride.utilities.pridemod.utils;


import uk.ac.ebi.pride.utilities.pridemod.model.PTM;
import uk.ac.ebi.pride.utilities.pridemod.model.Specificity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 12/10/15
 */
public class Utilities {

    public static List<PTM> filterPTMsByAminoAcidSpecificity(List<PTM> ptms, String aa) {

        List<PTM> resultPtms;
        if(aa == null || aa.isEmpty()){
            resultPtms = ptms;
        }else{
            resultPtms = new ArrayList<PTM>();
            Specificity currentSpecificity = new Specificity(Specificity.parseAminoAcid(aa), Specificity.Position.NONE);
            for(PTM ptm: ptms){
                for(Specificity specificity:ptm.getSpecificityCollection()){
                    if(specificity.getName().equals(currentSpecificity.getName()) || specificity.getName().equals(Specificity.AminoAcid.X)){
                        resultPtms.add(ptm);
                    }
                }
            }
        }
        return resultPtms;
    }

    public static String removePrefixUniMod(String uniModAccession){
        String[] stringArray = uniModAccession.split(":");
        return stringArray[1];
    }
}
