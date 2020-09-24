package com.lodbrock;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Glossary {

    private final int ARRAY_INCREASE = 100;
    private GlossaryWord[] glossaryWords;
    private int currentGlossarySize;

    public Glossary(){
        glossaryWords = new GlossaryWord[ARRAY_INCREASE];
        currentGlossarySize = 0;
    }

    public void increaseGlossary(){
        GlossaryWord[] n_glossaryWords = new GlossaryWord[ARRAY_INCREASE + glossaryWords.length];
        System.arraycopy(glossaryWords, 0, n_glossaryWords, 0, currentGlossarySize);
        glossaryWords = n_glossaryWords;
    }

    public void addWord(String word){
        for(int i = 0; i < currentGlossarySize; i++){
            if(glossaryWords[i].getWord().toLowerCase().equals(word.toLowerCase())){
                glossaryWords[i].addCount();
                return;
            }
        }

        if(glossaryWords.length >= currentGlossarySize) increaseGlossary();
        glossaryWords[currentGlossarySize] = new GlossaryWord(word);
        currentGlossarySize++;
    }

    public GlossaryWord[] getGlossaryList(){
        GlossaryWord[] n_glossaryWords = new GlossaryWord[currentGlossarySize];
        System.arraycopy(glossaryWords, 0, n_glossaryWords, 0, currentGlossarySize);
        return n_glossaryWords;
    }

    public void displayGlossary(){
        for (GlossaryWord glossaryWord : glossaryWords) {
            if (glossaryWord == null) return;
            System.out.println(glossaryWord.toString());
        }

    }

    public void displayOrderedGlossary(Comparator<GlossaryWord> comparator){
        GlossaryWord[] glossaryContent = getGlossaryList();
        Arrays.sort(glossaryContent, comparator);

        for(GlossaryWord glossaryWord : glossaryContent){
            System.out.println(glossaryWord.toString());
        }
    }



    static class GlossaryWord{
        private int count;
        private final String word;

        public GlossaryWord(String word){
            this.word = word;
            this.count = 1;
        }

        public void addCount(){
            count++;
        }

        @Override
        public String toString() {
            return "The word <" + word + "> was mentioned " + count + " times.";
        }

        public int getCount() {
            return count;
        }

        public String getWord() {
            return word;
        }
    }

    static class GlossaryByAlpha implements Comparator<GlossaryWord>{

        @Override
        public int compare(GlossaryWord o1, GlossaryWord o2) {
            return o1.getWord().toLowerCase().compareTo(o2.getWord().toLowerCase());
        }
    }
    static class GlossaryByCount implements Comparator<GlossaryWord>{

        @Override
        public int compare(GlossaryWord o1, GlossaryWord o2) {
            return o2.getCount() - o1.getCount();
        }
    }
}
