package com.example.lostintranslation.model;
import java.util.Random;

public class Word {


    String original_word;
    String transformed_word;

    public String getOriginal_word() {
        return original_word;
    }

    public String getTransformed_word() {
        // Translates word
        for(int i = 0; i < original_word.length(); i++){
            int ascii = original_word.indexOf(i) - 97;
            transformed_word += symbols[ascii];
        }
        return transformed_word;
    }



    // Word Banks
    private String[] easy = {"Car","Fun","Git","Tour",
                    "More","Stop","Chant","Cope",
                    "Mouse","Over"};

    private String[] normal = {"Barren","Awaken","Fabric",
                   "Hornet","Doctor","Osmosis",
                    "Caption","Gravity","Thunder","Blizzard"};

    private String[] hard = {"xenophobic","Esophagus","Hackathon",
                    "Poliomyelitis","Xylophone","Annexation","Meningitis",
                    "Cultivation","Amphibians"};

    // Translation index
    private String[] symbols = {"(!","(@","(#","($","(%","(^","(&","(*","(-","(+",
                        "[!","[@","[#","[$","[%","[^","[&","[*","[-","[+",
                        "{!","{@","{#","{$","{%","{^","{&","{*","{-","{+"};

    private GamePlay select = new GamePlay();
    int current_difficulty = select.difficulty;

    Random rand = new Random();
    int easy_rand = rand.nextInt(easy.length);
    int normal_rand = rand.nextInt(normal.length);
    int hard_rand = rand.nextInt(hard.length);


    public Word(){
        // Gets word randomly
        if(current_difficulty == 1){
            original_word = easy[easy_rand];
        }
        else if (current_difficulty == 2) {
            original_word = normal[normal_rand];
        }
        else if( current_difficulty == 3){
            original_word = hard[hard_rand];
        }
    }





}
