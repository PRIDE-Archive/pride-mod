package uk.ac.ebi.pride.utilities.pridemod.utils;


import uk.ac.ebi.pride.utilities.pridemod.model.PTM;
import uk.ac.ebi.pride.utilities.pridemod.model.Specificity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            resultPtms = new ArrayList<>();
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

    /**
     * This function filter Possible modifications by the position of the modification
     * @param resultMaps original list of modifications
     * @param position   position of the modifications
     * @return A list of modifications.
     */
    public static List<PTM> filterPTMsByAminoAcidSpecificityPosition(List<PTM> resultMaps, String position) {
        List<Specificity> specificities = new ArrayList<>();
        Set<PTM> resultPTMs = new HashSet<>();

        if(Specificity.parsePositon(position) == Specificity.Position.NTERM){
            // For NTerm some time the original information do not keep the diffent at peptide level between peptide and protein
            specificities.add(new Specificity(Specificity.AminoAcid.NONE, Specificity.Position.NTERM));
            specificities.add(new Specificity(Specificity.AminoAcid.NONE, Specificity.Position.PNTERM));
        }else if(Specificity.parsePositon(position) == Specificity.Position.CTERM){
            specificities.add(new Specificity(Specificity.AminoAcid.NONE, Specificity.Position.CTERM));
            specificities.add(new Specificity(Specificity.AminoAcid.NONE, Specificity.Position.PCTERM));
        }else{
            specificities.add(new Specificity(Specificity.AminoAcid.NONE, Specificity.parsePositon(position)));
        }

        for(PTM ptm: resultMaps){
            for(Specificity currentSpecificity: ptm.getSpecificityCollection())
                for(Specificity specificity: specificities)
                    if(specificity.getPosition().equals(currentSpecificity.getPosition()))
                        resultPTMs.add(ptm);
        }
        List<PTM> ptms = new ArrayList<>();
        ptms.addAll(resultPTMs);

        return ptms;
    }

    public static boolean isUniModAccession(String accession){
        return accession.contains("Unimod:") && (accession.replace("Unimod:", "").matches("^-?\\d+$"));
    }

    public static Integer getIntegerForUnimodAccession(String accession){
        if(isUniModAccession(accession)){
            return Integer.parseInt(accession.replace("Unimod:", ""));
        }else if(accession.matches("^-?\\d+$"))
            return Integer.parseInt(accession);
        return null;

    }

    public static boolean isPSIModAccession(String accession){
        return accession.contains(("MOD:")) && (accession.replace("MOD:", "").length() == 5);
    }
}
