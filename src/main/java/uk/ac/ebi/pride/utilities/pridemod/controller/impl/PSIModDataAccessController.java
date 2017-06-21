package uk.ac.ebi.pride.utilities.pridemod.controller.impl;

import org.apache.commons.lang3.math.NumberUtils;
import org.obolibrary.oboformat.model.Frame;
import org.obolibrary.oboformat.model.Xref;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.pride.utilities.pridemod.controller.AbstractDataAccessController;
import uk.ac.ebi.pride.utilities.pridemod.exception.DataAccessException;
import uk.ac.ebi.pride.utilities.pridemod.io.psimod.PSIModReader;
import uk.ac.ebi.pride.utilities.pridemod.model.PSIModPTM;
import uk.ac.ebi.pride.utilities.pridemod.model.Specificity;
import uk.ac.ebi.pride.utilities.pridemod.utils.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * The {@link PSIModDataAccessController} contains all the methods to read, and query the PSI Obo Ontology.
 * The Current obo file has been edited to improve the retrieval options and cross-references between
 * databases.
 *
 * @author ypriverol
 */

public class PSIModDataAccessController extends AbstractDataAccessController{

    private static final Logger logger = LoggerFactory.getLogger(UnimodDataAccessController.class);

    public PSIModDataAccessController(InputStream inputStream) {
        super(inputStream);
        try {
            PSIModReader psiModReader = new PSIModReader(inputStream);
            initPTMMap(psiModReader.getTermCollection());
        } catch (IOException e) {
            String msg = "Exception while trying to read the Unimod file";
            logger.error(msg, e);
            throw new DataAccessException(msg, e);
        }
    }

    /**
     * InitPTMMap is a parser of all the ptms included in the PSIMod database. We decide to keep all in memory for the
     * very beginning to improve the performance of the application. The present function convert all terms from PSI-MOD to
     * PTM structure and added them to a HashMap. In the future we can think to
     * @param termCollection
     */
    private void initPTMMap(Collection<Frame> termCollection) {

        ptmMap = new HashMap<>(termCollection.size());

        for(Frame frame: termCollection){
            String id = frame.getId();

            String NAME_TAG = "name";
            String name = (String) frame.getTagValue(NAME_TAG);

            String DEF_TAG = "def";
            String description = (String) frame.getTagValue(DEF_TAG);

            String SYNONYM_TAG = "synonym";
            Collection<Object> objectList = frame.getTagValues(SYNONYM_TAG);
            List<String> synonyms = null;
            if(objectList != null){
                synonyms = new ArrayList<>(objectList.size());
                for(Object synonym: objectList)
                    synonyms.add((String) synonym);
            }

            String XREF_TAG = "xref";
            Collection<Object> xrefs = frame.getTagValues(XREF_TAG);
            Double averageDeltaMass  = null;
            Double monoDeltaMass     = null;
            String formula = null;

            String source  = null;
            String aminoacid = null;
            String aminoacidPosition = null;
            String remapID = null;
            List<String> unimodID = new ArrayList<>();

            if(xrefs != null){
                for(Object xref: xrefs){
                    String AVERAGE_TAG = "DiffAvg:";
                    if(((Xref)xref).getIdref().equalsIgnoreCase(AVERAGE_TAG) && NumberUtils.isNumber(((Xref) xref).getAnnotation()))
                        averageDeltaMass = Double.parseDouble(((Xref) xref).getAnnotation());

                    String MONO_TAG = "DiffMono:";
                    if(((Xref)xref).getIdref().equalsIgnoreCase(MONO_TAG) && NumberUtils.isNumber(((Xref) xref).getAnnotation()))
                        monoDeltaMass = Double.parseDouble(((Xref)xref).getAnnotation());

                    String FORMULA_TAG = "Formula:";
                    if(((Xref)xref).getIdref().equalsIgnoreCase(FORMULA_TAG))
                        formula = ((Xref)xref).getAnnotation();

                    String SOURCE_TAG = "Source";
                    if(((Xref)xref).getIdref().equalsIgnoreCase(SOURCE_TAG))
                        source = ((Xref)xref).getAnnotation();

                    String ORIGIN_TAG = "Origin:";
                    if(((Xref)xref).getIdref().equalsIgnoreCase(ORIGIN_TAG)){
                        aminoacid = ((Xref)xref).getAnnotation();
                    }
                    String TERMSPEC_TAG = "TermSpec";
                    if(((Xref)xref).getIdref().equalsIgnoreCase(TERMSPEC_TAG)){
                        aminoacidPosition = ((Xref)xref).getAnnotation();
                    }
                    String REMAP_TAG = "Remap:";
                    if(((Xref)xref).getIdref().equalsIgnoreCase(REMAP_TAG)){
                        remapID = ((Xref)xref).getAnnotation();
                    }
                    if(((Xref)xref).getIdref().equalsIgnoreCase(Constants.UNIMOD_TAG)){
                        unimodID.add(((Xref)xref).getAnnotation());
                    }
                }
            }

            //Parse all the specificities, in case of PSI-Mod all the modifications are associated to only one specificity
            Specificity.AminoAcid specificity = null;
            Specificity.Position  position    = null;

            if(aminoacid != null)
                specificity = Specificity.parseAminoAcid(aminoacid);
            if(aminoacidPosition != null)
                position    = Specificity.parsePositon(aminoacidPosition);

            boolean obsolete = false;
            String OBSOLETE_TAG = "is_obsolete";
            if(frame.getTagValue(OBSOLETE_TAG) != null)
                obsolete = true;

            List<Specificity> specificityList = new ArrayList<>();
            if(specificity != null){
                specificityList.add(new Specificity(specificity, position));
            }
            // End of specificity parsing

            // Parser of Parent Modifications
            String IS_A_TAG = "is_a";
            objectList = frame.getTagValues(IS_A_TAG);
            List<Comparable> parentPTMs = null;
            if(objectList != null){
                parentPTMs = new ArrayList<>(objectList.size());
                for(Object object: objectList)
                    parentPTMs.add((Comparable)object);
            }

            PSIModPTM ptm = new PSIModPTM(id,name,description,monoDeltaMass,averageDeltaMass,specificityList,formula,synonyms,obsolete,source, parentPTMs, remapID,unimodID);
            ptmMap.put(id, ptm);
        }
    }

}
