/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Vocabulary {
    private int vocabID;
    private String word;
    private String meaning;
    private String reading;
    private String example;

    public int getVocabID() { return vocabID; }
    public void setVocabID(int vocabID) { this.vocabID = vocabID; }

    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }

    public String getMeaning() { return meaning; }
    public void setMeaning(String meaning) { this.meaning = meaning; }

    public String getReading() { return reading; }
    public void setReading(String reading) { this.reading = reading; }

    public String getExample() { return example; }
    public void setExample(String example) { this.example = example; }
}
