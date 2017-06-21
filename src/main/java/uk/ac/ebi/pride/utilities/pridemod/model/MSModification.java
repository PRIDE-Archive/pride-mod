package uk.ac.ebi.pride.utilities.pridemod.model;

import java.util.Collections;
import java.util.List;

/**
 * The MS modifications contains a set of modifications that are encoded in MS like the fragment neutral losses
 *
 * @author ypriverol
 */
public enum MSModification implements PTM{

    FRAGMENT_NEUTRAL_LOSS("MS", "MS:1001524", "fragment neutral loss", 63.998283, 63.998283, "fragment neutral loss");


    private String cvLabel;
    private String accession;
    private String name;
    private Double monoDelta;
    private Double avgDelta;
    private String description;

    MSModification(String cvLabel, String accession, String name, Double monoDelta, Double avgDelta, String description) {
        this.cvLabel = cvLabel;
        this.accession = accession;
        this.name = name;
        this.monoDelta = monoDelta;
        this.avgDelta = avgDelta;
        this.description = description;
    }

    @Override
    public String getAccession() {
        return accession;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getMonoDeltaMass() {
        return monoDelta;
    }

    @Override
    public Double getAveDeltaMass() {
        return avgDelta;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<Specificity> getSpecificityCollection() {
        return Collections.emptyList();
    }

    @Override
    public String getFormula() {
        return null;
    }

    @Override
    public String getCvLabel() {
        return cvLabel;
    }

    public static PTM getByAccession(String accession) {
        for(MSModification mod: values())
            if(mod.getAccession().equalsIgnoreCase(accession))
                return mod;
        return null;
    }
}
