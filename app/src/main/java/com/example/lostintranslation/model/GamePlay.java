package com.example.lostintranslation.model;

public class GamePlay {

    String selectedWord;
    int difficulty;



    int score;



    int attempts;

    public GamePlay(){
        difficulty = 1;                 // Set to 1 by default
        score = 0;                      // Set to 0 by default
        attempts = difficulty*2 + 1;    // Set to 3 by default
    }

    public void addScore(){
        score = 50 + difficulty*50;
    }

    public void reduceAttempts(){
        attempts = attempts - 1;
    }

    public int getScore() {
        return score;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setTranscribedWord(){
        Word word = new Word();
        selectedWord = word.transformed_word;
    }



}
