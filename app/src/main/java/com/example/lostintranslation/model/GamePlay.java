package com.example.lostintranslation.model;

public class GamePlay {

    private String selectedWord;
    private String[] selectedLevel;
    private int difficulty;
    private int score;
    private int attempts;

    public GamePlay(int difficulty){
        this.difficulty = difficulty;   // Set to 1 by default
        score = 0;                      // Set to 0 by default
        attempts = 7 - difficulty * 2;  // Set to 3 by default
    }

    public void addScore(){
        score += 50 + difficulty*50;
    }

    public void reduceAttempts(){
        attempts--;
    }

    public int getScore() {
        return score;
    }

    public int getAttempts() {
        return attempts;
    }

    public String[] getSelectedLevel() {
        return selectedLevel;
    }

    public void setSelectedLevel(String[] selectedLevel) {
        this.selectedLevel = selectedLevel;
    }





}
