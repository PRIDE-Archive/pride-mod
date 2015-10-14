package uk.ac.ebi.pride.utilities.pridemod.model;

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

    private String remapID;

    private List<String> unimodId;

    /**
     *
     * @param accession
     * @param name
     * @param description
     * @param monoDeltaMass
     * @param averageDeltaMass
     * @param specificityList
     * @param formula
     * @param synonyms
     * @param obsolete
     * @param source
     * @param parentPTMList
     * @param remapID
     * @param unimodId
     */
    public PSIModPTM(String accession, String name, String description, Double monoDeltaMass, Double averageDeltaMass, List<Specificity> specificityList, String formula, List<String> synonyms, boolean obsolete, String source, List<Comparable> parentPTMList, String remapID, List<String> unimodId) {
        super(accession, name, description, monoDeltaMass, averageDeltaMass, specificityList, formula);
        this.synonyms = synonyms;
        this.obsolete = obsolete;
        this.source = source;
        this.parentPTMList = parentPTMList;
        this.remapID = remapID;
        this.unimodId = unimodId;
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

    public String getRemapID() {
        return remapID;
    }

    public void setRemapID(String remapID) {
        this.remapID = remapID;
    }

    public List<String> getUnimodId() {
        return unimodId;
    }

    public void setUnimodId(List<String> unimodId) {
        this.unimodId = unimodId;
    }

    @Override
    public String toString() {
        return "PSIModPTM{" +
                "synonyms=" + synonyms +
                ", obsolete=" + obsolete +
                ", source='" + source + '\'' +
                ", parentPTMList=" + parentPTMList +
                ", remapID='" + remapID + '\'' +
                ", unimodId='" + unimodId + '\'' +
                '}';
    }
}
