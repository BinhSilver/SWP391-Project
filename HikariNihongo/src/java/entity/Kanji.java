package entity;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class Kanji {
    private int kanjiID;
    private String character;
    private String onyomi;
    private String kunyomi;
    private String meaning;
    private int strokeCount;
    private Lesson lesson; // khóa ngoại LessonID

    public int getKanjiID() { return kanjiID; }
    public void setKanjiID(int kanjiID) { this.kanjiID = kanjiID; }

    public String getCharacter() { return character; }
    public void setCharacter(String character) { this.character = character; }

    public String getOnyomi() { return onyomi; }
    public void setOnyomi(String onyomi) { this.onyomi = onyomi; }

    public String getKunyomi() { return kunyomi; }
    public void setKunyomi(String kunyomi) { this.kunyomi = kunyomi; }

    public String getMeaning() { return meaning; }
    public void setMeaning(String meaning) { this.meaning = meaning; }

    public int getStrokeCount() { return strokeCount; }
    public void setStrokeCount(int strokeCount) { this.strokeCount = strokeCount; }

    public Lesson getLesson() { return lesson; }
    public void setLesson(Lesson lesson) { this.lesson = lesson; }
}
