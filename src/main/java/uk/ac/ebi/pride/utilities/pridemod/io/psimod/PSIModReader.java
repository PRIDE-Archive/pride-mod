package uk.ac.ebi.pride.utilities.pridemod.io.psimod;

import org.obolibrary.oboformat.model.Frame;
import org.obolibrary.oboformat.model.OBODoc;
import org.obolibrary.oboformat.parser.OBOFormatParser;

import java.io.*;
import java.util.Collection;
import java.util.Collections;

/**
 * yperez
 */
public class PSIModReader {

    /**
     * OboDoc that contains PSI-Mod PTms
     */
    private OBODoc oboDoc = null;

    public PSIModReader(InputStream inputStream) throws IOException {
        OBOFormatParser oboReader = new OBOFormatParser();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            oboDoc = oboReader.parse(bufferedReader);
            oboDoc.getInstanceFrames();
        } finally {
            bufferedReader.close();
        }
    }

    public Collection<Frame> getTermCollection(){
        if(oboDoc != null)
            return oboDoc.getTermFrames();
        return Collections.emptyList();
    }

}
