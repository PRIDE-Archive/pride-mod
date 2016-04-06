package uk.ac.ebi.pride.utilities.pridemod.io.unimod.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.pride.utilities.pridemod.io.unimod.model.Unimod;
import uk.ac.ebi.pride.utilities.pridemod.io.unimod.xml.unmarshaller.UnimodUnmarshallerFactory;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;


/**
 * yperez
 */
public class UnimodReader {

    private static final Logger logger = LoggerFactory.getLogger(UnimodReader.class);

    /**
     * internal unmashaller
     */
    private Unmarshaller unmarshaller = null;

    private Unimod unimod_whole = null;

    /**
     *
     * @param xml
     * @throws JAXBException
     */
    public UnimodReader(InputStream xml) throws JAXBException {
        if (xml == null) {
            throw new IllegalArgumentException("Xml file to be indexed must not be null");
        }
        // create unmarshaller
        this.unmarshaller = UnimodUnmarshallerFactory.getInstance().initializeUnmarshaller();
        unimod_whole = (Unimod) unmarshaller.unmarshal(xml);
    }

    public Unimod getUnimodObject(){
        return unimod_whole;
    }
}
