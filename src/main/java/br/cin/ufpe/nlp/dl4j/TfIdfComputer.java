package br.cin.ufpe.nlp.dl4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Locale;

import org.apache.uima.resource.ResourceInitializationException;
import org.deeplearning4j.bagofwords.vectorizer.TextVectorizer;
import org.deeplearning4j.bagofwords.vectorizer.TfidfVectorizer;
import org.deeplearning4j.text.documentiterator.DocumentIterator;
import org.deeplearning4j.text.documentiterator.FileDocumentIterator;
import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.UimaTokenizerFactory;

import br.cin.ufpe.nlp.api.TfIdfComputerService;
import br.cin.ufpe.nlp.api.TfIdfInfo;

public class TfIdfComputer implements TfIdfComputerService {
	public TfIdfInfo computeTfIdfRecursively(Path path) throws IOException {
		DocumentIterator docIterator = new FileDocumentIterator(path.toFile());
		TokenizerFactory tokenFactory;
		try {
			tokenFactory = new UimaTokenizerFactory();
		} catch (ResourceInitializationException e) {
			throw new IllegalStateException("Uima ResourceInitializationException", e);
		}
		tokenFactory.setTokenPreProcessor(new TokenPreProcess() {
			
			public String preProcess(String token) {
				return token.toLowerCase(Locale.ENGLISH);
			}
		});
		TextVectorizer textVectorizer = new TfidfVectorizer.Builder().setIterator(docIterator).setTokenizerFactory(tokenFactory).build();
		textVectorizer.fit();
		
		TfIdfInfo result = new InMemoryTfIdfInfo(textVectorizer.getVocabCache());
		return result;
	}
}
