package uk.ac.ebi.pride.utilities.pridemod.io;

import uk.ac.ebi.pride.utilities.pridemod.io.slimmod.model.SlimModCollection;

/**
 * Created by IntelliJ IDEA.
 * User: yperez
 * Date: 4/14/12
 * Time: 12:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class PrideModController {


    private static final String PRIDEMOD_TXT = ".txt";
    private static final String PRIDEMOD_XML = ".xml";

    public static SlimModCollection parseSlimModCollection() {

       /* if (url.getPath().endsWith(PRIDEMOD_TXT)) {
            return ReadTabSlim.parseSlimModification(url);
        } else if (url.getPath().endsWith(PRIDEMOD_XML)) {
            PrideModReader modReader = new PrideModReader(url);
            return modReader.getSlimModCollection();
        } else {
            throw new RuntimeException("No handler defined to parse URL: " + url.toString());
        }*/
        return null;

    }

}
