package uk.ac.ebi.pride.utilities.pridemod.controller;

import uk.ac.ebi.pride.utilities.pridemod.model.PTM;
import uk.ac.ebi.pride.utilities.pridemod.model.Specificity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * yperez
 */
public class AbstractDataAccessController implements DataAccessController {

    public Map<Comparable, PTM> ptmMap;

    private InputStream source;

    /**
     * Default constructor for Controllers
     */
    public AbstractDataAccessController(InputStream inputStream) {
        ptmMap = new HashMap<Comparable, PTM>();
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
        List<PTM> ptms = new ArrayList<PTM>();
        for (PTM ptm : ptmMap.values()) {
            if (ptm.getName().contains(namePattern))
                ptms.add(ptm);
        }
        return ptms;
    }

    @Override
    public List<PTM> getPTMListBySpecificity(Specificity specificity) {
        List<PTM> ptms = new ArrayList<PTM>();
        for (PTM ptm : ptmMap.values()) {
            if (ptm.getSpecificityCollection().contains(specificity))
                ptms.add(ptm);
        }
        return ptms;
    }

    @Override
    public List<PTM> getPTMListByPatternDescription(String descriptionPattern) {
        List<PTM> ptms = new ArrayList<PTM>();
        for (PTM ptm : ptmMap.values()) {
            if (ptm.getDescription().toUpperCase().contains(descriptionPattern.toUpperCase()))
                ptms.add(ptm);
        }
        return ptms;
    }

    @Override
    public List<PTM> getPTMListByEqualName(String name) {
        List<PTM> ptms = new ArrayList<PTM>();
        for (PTM ptm : ptmMap.values()) {
            if (ptm.getName().equalsIgnoreCase(name))
                ptms.add(ptm);
        }
        return ptms;
    }

    @Override
    public List<PTM> getPTMListByMonoDeltaMass(Double delta) {
        List<PTM> ptms = new ArrayList<PTM>();
        for (PTM ptm : ptmMap.values()) {
            if (ptm.getMonoDeltaMass() != null && Double.compare(ptm.getMonoDeltaMass(), delta) == 0)
                ptms.add(ptm);
        }
        return ptms;
    }

    @Override
    public List<PTM> getPTMListByAvgDeltaMass(Double delta) {
        List<PTM> ptms = new ArrayList<PTM>();
        for (PTM ptm : ptmMap.values()) {
            if (ptm.getAveDeltaMass() != null && Double.compare(ptm.getAveDeltaMass(), delta) == 0)
                ptms.add(ptm);
        }
        return ptms;
    }
}
