package br.cin.ufpe.nlp.dl4j;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import br.cin.ufpe.nlp.api.TfIdfComputerService;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		//registering TfIdfComputerService with empty properties for now...
		Hashtable<String, String> props = new Hashtable<String, String>();
		/*
		URL types = context.getBundle().getEntry("/org/cleartk/TypeSystem.xml");
		File f = File.createTempFile("TypeSystem", "xml");
		//f.deleteOnExit();
		BufferedInputStream bufIs = new BufferedInputStream(types.openStream());
		BufferedOutputStream bufFile = new BufferedOutputStream(new FileOutputStream(f));
		int byteval;
		while((byteval = bufIs.read()) != -1) {
			bufFile.write(byteval);
		}
		bufIs.close();
		bufFile.close();
		final String tempFileTypeSystem = f.toURI().toURL().toString();
		System.out.println("TypeSystem file is " + f.toURI().toURL().toString()); */
		//System.setProperty("org.uimafit.type.import_pattern", "/org/cleartk/TypeSystem.xml");
		context.registerService(TfIdfComputerService.class.getName(), new TfIdfComputer(), props);
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		// NOTE: The service is automatically unregistered.
	}

}
