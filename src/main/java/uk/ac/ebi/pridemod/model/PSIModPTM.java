package uk.ac.ebi.pridemod.model;

import java.util.List;

/**
 * yperez.
 */
public class PSIModPTM extends AbstractPTM{

    /**
     * List of possible names of a modification
     */
    private List<String> synonyms;

    private boolean obsolete;

    private String source;

    private List<Comparable> parentPTMList;

    public PSIModPTM(String accession,
                     String name,
                     String description,
                     Double monoDeltaMass,
                     Double averageDeltaMass,
                     List<Specificity> specificityList,
                     String formula,
                     List<String> synonyms,
                     String source,
                     List<Comparable> parentPTMList,
                     boolean obsolete) {
        super(accession, name, description, monoDeltaMass, averageDeltaMass, specificityList, formula);
        this.synonyms = synonyms;
        this.obsolete = obsolete;
        this.parentPTMList = parentPTMList;
        this.source = source;
    }

    public boolean isObsolete() {
        return obsolete;
    }

    public void setObsolete(boolean obsolete) {
        this.obsolete = obsolete;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Comparable> getParentPTMList() {
        return parentPTMList;
    }

    public void setParentPTMList(List<Comparable> parentPTMList) {
        this.parentPTMList = parentPTMList;
    }
}
