package uk.ac.ebi.pride.utilities.pridemod.controller;

import uk.ac.ebi.pride.utilities.pridemod.model.PTM;
import uk.ac.ebi.pride.utilities.pridemod.model.Specificity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The abstract data access controller contains the ptmMap a key->value pair object that store all the information
 * for the PTMs in the ontology. It also contains the reference to the original source file that
 * has been used to load the ontology. The abstract controller contains default methods to
 * retrieve, and populate the ontology.
 *
 * @author ypriverol
 */
public class AbstractDataAccessController implements DataAccessController {

    public Map<Comparable, PTM> ptmMap;
    private InputStream source;

    /**
     * Default constructor for Controllers
     */
    public AbstractDataAccessController(InputStream inputStream) {
        ptmMap = new HashMap<>();
        source = inputStream;
    }

    @Override
    public InputStream getSource() {
        return source;
    }

    /**
     * Return the PTM with an specific accession.
     *
     * @param accession
     * @return
     */
    @Override
    public PTM getPTMbyAccession(String accession) {
        return ptmMap.get(accession);
    }

    /**
     * Return the ptm with an specific pattern in the name
     *
     * @param namePattern
     * @return
     */
    @Override
    public List<PTM> getPTMListByPatternName(String namePattern) {
        List<PTM> ptms = new ArrayList<>();
        for (PTM ptm : ptmMap.values()) {
            if (ptm.getName().contains(namePattern))
                ptms.add(ptm);
        }
        return ptms;
    }

    @Override
    public List<PTM> getPTMListBySpecificity(Specificity specificity) {
        List<PTM> ptms = new ArrayList<>();
        for (PTM ptm : ptmMap.values()) {
            if (ptm.getSpecificityCollection().contains(specificity))
                ptms.add(ptm);
        }
        return ptms;
    }

    @Override
    public List<PTM> getPTMListByPatternDescription(String descriptionPattern) {
        List<PTM> ptms = new ArrayList<>();
        for (PTM ptm : ptmMap.values()) {
            if (ptm.getDescription().toUpperCase().contains(descriptionPattern.toUpperCase()))
                ptms.add(ptm);
        }
        return ptms;
    }

    @Override
    public List<PTM> getPTMListByEqualName(String name) {
        List<PTM> ptms = new ArrayList<>();
        for (PTM ptm : ptmMap.values()) {
            if (ptm.getName().equalsIgnoreCase(name))
                ptms.add(ptm);
        }
        return ptms;
    }

    @Override
    public List<PTM> getPTMListByMonoDeltaMass(Double delta) {
        List<PTM> ptms = new ArrayList<>();
        for (PTM ptm : ptmMap.values()) {
            if (ptm.getMonoDeltaMass() != null && Math.abs(ptm.getMonoDeltaMass() - delta) < 0.00001)
                ptms.add(ptm);
        }
        return ptms;
    }

    @Override
    public List<PTM> getPTMListByAvgDeltaMass(Double delta) {
        List<PTM> ptms = new ArrayList<>();
        for (PTM ptm : ptmMap.values()) {
            if (ptm.getAveDeltaMass() != null && Math.abs(ptm.getAveDeltaMass()-delta) < 0.00001)
                ptms.add(ptm);
        }
        return ptms;
    }

    public List<PTM> getPTMListByMonoDeltaMassSpecificity(Double mass, String aa) {
        List<PTM> ptms = getPTMListByMonoDeltaMass(mass);
        List<PTM> ptmsResults = new ArrayList<>();
        if(!ptms.isEmpty()){
            for(PTM ptm: ptms){
                for(Specificity specificity: ptm.getSpecificityCollection())
                    if(specificity.getName().compareTo(Specificity.AminoAcid.valueOf(aa)) == 0)
                        ptmsResults.add(ptm);
            }
        }
        return ptmsResults;
    }
}
