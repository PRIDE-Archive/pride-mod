package uk.ac.ebi.pridemod.utils;


import uk.ac.ebi.pridemod.model.PTM;
import uk.ac.ebi.pridemod.model.Specificity;

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
            uk.ac.ebi.pridemod.model.Specificity currentSpecificity = new Specificity(Specificity.parseAminoAcid(aa), Specificity.Position.NONE);
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
}
