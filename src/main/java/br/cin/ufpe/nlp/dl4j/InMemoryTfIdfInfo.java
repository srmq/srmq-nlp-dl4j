package br.cin.ufpe.nlp.dl4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.wordstore.VocabCache;

import br.cin.ufpe.nlp.api.bagofwords.TfIdfInfo;

public class InMemoryTfIdfInfo implements TfIdfInfo {
	
	private class VocabWordComparator implements Comparator<VocabWord> {

		@Override
		public int compare(VocabWord w1, VocabWord w2) {
			if (w1.getIndex() < w2.getIndex()) return -1;
			else if (w1.getIndex() > w2.getIndex()) return 1;
			else return 0;
		}
		
	}
	
	private VocabCache<VocabWord> vocab;
	
	public InMemoryTfIdfInfo(VocabCache<VocabWord> vocab) {
		this.vocab = vocab;
	}
	
	public long totalNumberOfDocs() {
		return this.vocab.totalNumberOfDocs();
	}
	
	public int docAppearedIn(String word) {
		return this.vocab.docAppearedIn(word);	
	}
	
	public Collection<String> getVocabWords() {
		Collection<String> result = null;
		if (this.vocab != null) {
			List<VocabWord> words = orderedWordsByIndex();
			result = new ArrayList<String>(words.size());
			for (VocabWord vocabWord : words) {
				result.add(vocabWord.getWord());
			}
		}
		return result;
	}
	
	public Collection<Long> getIndices() {
		Collection<Long> result = null;
		if (this.vocab != null) {
			List<VocabWord> words = orderedWordsByIndex();
			result = new ArrayList<Long>(words.size());
			for (VocabWord vocabWord : words) {
				result.add(new Long(vocabWord.getIndex()));
			}
		}
		return result;
	}

	private List<VocabWord> orderedWordsByIndex() {
		List<VocabWord> words = new ArrayList<VocabWord>(vocab.vocabWords());
		Collections.sort(words, new VocabWordComparator());
		return words;
	}

	@Override
	public int indexOf(String word) {
		return vocab.indexOf(word);
	}

	@Override
	public String wordAtIndex(int index) {
		return vocab.wordAtIndex(index);
	}
	
}
