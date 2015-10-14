package uk.ac.ebi.pride.utilities.pridemod.io.pridemod.extractor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model.PrideMod;
import uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model.PrideModification;
import uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model.PrideModifications;

import javax.xml.bind.Unmarshaller;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yperez
 * Date: 19/07/11
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
public class PrideModExtractor {

    private static final Logger logger = LoggerFactory.getLogger(PrideModExtractor.class);

    private PrideMod prideMod = null;

    private PrideModifications modColletion = null;

    public PrideModExtractor(PrideMod prideMod) {
        this.prideMod = prideMod;
    }

    public PrideModExtractor(Unmarshaller unmarshaller) {
        this.prideMod = (PrideMod) unmarshaller;
    }

    public List<PrideModification> getModListbyMass(double mass) {
        return this.modColletion.getModbyMonoMass(mass);
    }

    public PrideModification getModbyId(int id) {
        return this.modColletion.getModbyId(id);
    }

    public List<PrideModification> getModListbySpecificity(String specificity) {
        return this.modColletion.getModListbySpecificity(specificity);
    }

    public List<PrideModification> getModListbyMassSepecificity(String specificity, double mass) {
        return this.modColletion.getListbyMassSpecificity(specificity, mass);
    }


}
