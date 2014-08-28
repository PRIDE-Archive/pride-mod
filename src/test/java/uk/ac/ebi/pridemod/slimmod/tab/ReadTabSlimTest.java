package uk.ac.ebi.pridemod.slimmod.tab;

import org.junit.Test;
import uk.ac.ebi.pridemod.io.slimmod.model.SlimModCollection;
import uk.ac.ebi.pridemod.io.slimmod.tab.ReadTabSlim;

/**
 * Created by IntelliJ IDEA.
 * User: local_admin
 * Date: 20/07/11
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public class ReadTabSlimTest {
    @Test
    public void testParseSlimModification() throws Exception {
        String filename = ReadTabSlimTest.class.getClassLoader().getResource("slimFile.txt").getPath();
        SlimModCollection slimModCollection = ReadTabSlim.parseSlimModification(filename);
    }
}
