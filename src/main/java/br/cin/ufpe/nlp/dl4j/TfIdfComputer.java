package br.cin.ufpe.nlp.dl4j;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

import org.apache.uima.resource.ResourceInitializationException;
import org.deeplearning4j.bagofwords.vectorizer.TextVectorizer;
import org.deeplearning4j.bagofwords.vectorizer.TfidfVectorizer;
import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.wordstore.VocabCache;
import org.deeplearning4j.text.documentiterator.FileLabelAwareIterator;
import org.deeplearning4j.text.documentiterator.LabelAwareIterator;
import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.UimaTokenizerFactory;

import br.cin.ufpe.nlp.api.bagofwords.TfIdfComputerService;
import br.cin.ufpe.nlp.api.bagofwords.TfIdfInfo;

public class TfIdfComputer implements TfIdfComputerService {
	
	public TfIdfInfo computeTfIdfRecursively(Path path) throws IOException {
		return this.computeTfIdfRecursively(path, false);
	}
	
	public TfIdfInfo computeTfIdfRecursively(Path path, boolean useSimpleTokenizer) throws IOException {
		LabelAwareIterator docIterator = new FileLabelAwareIterator.Builder().addSourceFolder(path.toFile()).build();
		TokenizerFactory tokenFactory;
		if (useSimpleTokenizer) {
			tokenFactory = new DefaultTokenizerFactory();
		} else {
			try {
				tokenFactory = new UimaTokenizerFactory();
			} catch (ResourceInitializationException e) {
				throw new IllegalStateException("Uima ResourceInitializationException", e);
			}
		}
		tokenFactory.setTokenPreProcessor(new TokenPreProcess() {
			
			public String preProcess(String token) {
				return token.toLowerCase(Locale.ENGLISH);
			}
		});
		TextVectorizer textVectorizer = new TfidfVectorizer.Builder().setIterator(docIterator).setTokenizerFactory(tokenFactory).build();
		textVectorizer.fit();
		final VocabCache<VocabWord> vocab = textVectorizer.getVocabCache();
		
		final TfIdfInfo result = new InMemoryTfIdfInfo(vocab);
		return result;
		
	}	
}

