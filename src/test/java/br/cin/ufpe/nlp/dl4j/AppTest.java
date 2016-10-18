package br.cin.ufpe.nlp.dl4j;

import java.io.File;
import java.io.IOException;

import br.cin.ufpe.nlp.api.TfIdfComputerService;
import br.cin.ufpe.nlp.api.TfIdfInfo;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
        TfIdfComputerService service = new TfIdfComputer();
        try {
			TfIdfInfo tfInfo = service.computeTfIdfRecursively(new File("/home/srmq/Documents/Research/textmining/devel/data/reuters21578/ModApteTestWithBodySingleTopic/originalText/top11MinusEarn/").toPath());
			System.out.println("Word 'bank' has appeared in " + tfInfo.docAppearedIn("bank") + " documents out of " + tfInfo.totalNumberOfDocs());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
    }
}
