package br.cin.ufpe.nlp.dl4j;

import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.wordstore.VocabCache;

import br.cin.ufpe.nlp.api.TfIdfInfo;

public class InMemoryTfIdfInfo implements TfIdfInfo {
	
	private VocabCache<VocabWord> vocab;
	
	public InMemoryTfIdfInfo(VocabCache<VocabWord> vocab) {
		this.vocab = vocab;
	}
	
	public int totalNumberOfDocs() {
		return this.vocab.totalNumberOfDocs();
	}
	
	public int docAppearedIn(String word) {
		return this.vocab.docAppearedIn(word);	
	}
}
