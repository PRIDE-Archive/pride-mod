package uk.ac.ebi.pride.utilities.pridemod.io.unimod.extractor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.pride.utilities.pridemod.io.unimod.model.ModificationCollection;
import uk.ac.ebi.pride.utilities.pridemod.io.unimod.model.Unimod;
import uk.ac.ebi.pride.utilities.pridemod.io.unimod.model.UnimodModification;

import javax.xml.bind.Unmarshaller;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yperez
 * Date: 19/07/11
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
public class UnimodExtractor {

    private static final Logger logger = LoggerFactory.getLogger(UnimodExtractor.class);

    private Unimod unimod = null;

    private ModificationCollection modColletion = null;

    public UnimodExtractor(Unimod unimod) {
        this.unimod = unimod;
    }

    public UnimodExtractor(Unmarshaller unmarshaller) {
        this.unimod = (Unimod) unmarshaller;
    }

    public List<UnimodModification> getModListbyMass(double mass) {
        return this.modColletion.getModbyAvgMass(mass);
    }

    public UnimodModification getModbyId(int id) {
        return this.modColletion.getModbyId(id);
    }

    public List<UnimodModification> getModListbySpecificity(String specificity) {
        return this.modColletion.getModListbySpecificity(specificity);
    }

    public List<UnimodModification> getModListbyMassSepecificity(String specificity, double mass) {
        return this.modColletion.getListbyMassSpecificity(specificity, mass);
    }


}
