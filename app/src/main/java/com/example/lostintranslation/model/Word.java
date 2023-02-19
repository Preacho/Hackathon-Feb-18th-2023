package com.example.lostintranslation.model;
import java.util.Locale;
import java.util.Random;

public class Word {


    private String original_word;
    private String transformed_word = "";

    // Word Banks
    private String[] easy = {"Car","Fun","Git","Tour",
                    "More","Stop","Chant","Cope",
                    "Mouse","Over"};

    private String[] normal = {"Barren","Awaken","Fabric",
                   "Hornet","Doctor","Osmosis",
                    "Caption","Gravity","Thunder","Blizzard"};

    private String[] hard = {"Xenophobic","Esophagus","Hackathon",
                    "Poliomyelitis","Xylophone","Annexation","",
                    "Cultivation","Amphibians", "Organization"};

    // Translation index
    private String[] symbols = {"(! ","(@ ","(# ","($ ","(% ","(^ ","(& ","(* ","(- ","(+ ",
                        "[! ","[@ ","[# ","[$ ","[% ","[^ ","[& ","[* ","[- ","[+ ",
                        "{! ","{@ ","{# ","{$ ","{% ","{^ ","{& ","{* ","{- ","{+ "};


    public String getOriginal_word() {
        return original_word;
    }

    public String getTransformed_word() {
        // Translates word
        original_word = original_word.toLowerCase(Locale.ROOT);
        for(int i = 0; i < original_word.length(); i++){
            int ascii = original_word.charAt(i)-97;
            transformed_word = transformed_word+symbols[ascii];
        }
        return transformed_word;
    }

    public Word(int difficulty){
        // Gets word randomly
        Random rand = new Random();
        int randomint = rand.nextInt(easy.length);
        int current_difficulty = difficulty+1;
        if(current_difficulty == 1){
            original_word = easy[randomint];
        }
        else if (current_difficulty == 2) {
            original_word = normal[randomint];
        }
        else if( current_difficulty == 3){
            original_word = hard[randomint];
        }
    }





}
