package com.example.lostintranslation.model;
import java.util.Random;

public class Word {
    String original_word;
    String transformed_word;

    // Word Banks
    String[] easy = {"Car","Fun","Git","Tour",
                    "More","Stop","Chant","Cope",
                    "Mouse","Over"};

    String[] normal = {"Barren","Awaken","Fabric",
                   "Hornet","Doctor","Osmosis",
                    "Caption","Gravity","Thunder","Blizzard"};

    String[] hard = {"Xenophobic","Esophagus","Hackathon",
                    "Poliomyelitis","Xylophone","Annexation","Meningitis",
                    "Cultivation","Amphibians"};

    // Translation index
    String[] symbols = {"(!","(@","(#","($","(%","(^","(&","(*","(-","(+",
                        "[!","[@","[#","[$","[%","[^","[&","[*","[-","[+",
                        "{!","{@","{#","{$","{%","{^","{&","{*","{-","{+"};

    GamePlay select = new GamePlay();
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

        // Translates word
        for(int i = 0; i < original_word.length(); i++){
            int ascii = original_word.indexOf(i) - 65;
            transformed_word += symbols[ascii];

        }


    }



}
