package uk.ac.ebi.pride.utilities.pridemod.io.pridemod.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.pride.utilities.pridemod.io.pridemod.extractor.PrideModExtractor;
import uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model.PrideModification;
import uk.ac.ebi.pride.utilities.pridemod.io.slimmod.model.SlimModification;
import uk.ac.ebi.pride.utilities.pridemod.io.pridemod.model.PrideMod;
import uk.ac.ebi.pride.utilities.pridemod.io.pridemod.xml.unmarshaller.PrideModUnmarshallerFactory;
import uk.ac.ebi.pride.utilities.pridemod.io.slimmod.model.SlimModCollection;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;


/**
 * Created by IntelliJ IDEA.
 * User: yperez
 * Date: 18-Jul-2011
 * Time: 12:13:31
 */
public class PrideModReader {

    private static final Logger logger = LoggerFactory.getLogger(PrideModReader.class);

    /**
     * internal unmashaller
     */
    private Unmarshaller unmarshaller = null;
    /**
     * internal xml extractor
     */
    private PrideModExtractor extractor = null;

    private PrideMod prideMod_whole = null;


    /*public PrideModReader(URL url) {
        this(FileUtils.getFileFromURL(url));
    } */
    public PrideModReader(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("Xml file to be indexed must not be null");
        }
        // create extractor
        // this.extractor = new UnimodExtractor(xml);

        try {
            // create unmarshaller
            this.unmarshaller = PrideModUnmarshallerFactory.getInstance().initializeUnmarshaller();
            prideMod_whole = (PrideMod) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            throw new IllegalArgumentException("Error unmarshalling XML file: " + e.getMessage(), e);
        }
    }

    public SlimModCollection getSlimModCollection() {
        SlimModCollection slimModCollection = new SlimModCollection();
        for (PrideModification prideModification : prideMod_whole.getPrideModifications().getPrideModification()) {
            //Todo: id is not clear, description, and information ...
            SlimModification slimModification = new SlimModification(prideModification.getPsiId(),
                    prideModification.getDiffMono().doubleValue(),
                    prideModification.getUnimodMappings().getUnimodMapping().get(0).getId().intValue(),
                    prideModification.getPsiName(),
                    prideModification.getPsiName(),
                    prideModification.getSpecificityList());
            slimModCollection.add(slimModification);
        }
        return slimModCollection;
    }

    public PrideMod getPrideMod(){
        return prideMod_whole;
    }


}
