package com.yarin.namesfinder.namesfinder;

import android.app.Activity;
import android.util.Log;

import com.yarin.namegenerator.Name;
import com.yarin.namegenerator.NameController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyCallback implements NameController.CallBack_Name {

    private Activity activity;

    // Constructor to pass reference to the activity
    public MyCallback(Activity activity) {
        this.activity = activity;
    }
    @Override
    public void successNames(List<Name> names) {
        List<String> nameContents = new ArrayList<>();
        for (Name name : names) {
            nameContents.add(name.getContent());
        }

        if (activity instanceof ActivityAllNames) {
            ((ActivityAllNames) activity).updateNames(nameContents);
        } else if (activity instanceof ActivityByCategory) {
            ((ActivityByCategory) activity).updateNames(nameContents);
        } else if (activity instanceof ActivityFirstLetter) {
            ((ActivityFirstLetter) activity).updateNames(nameContents);
        } else if (activity instanceof ActivityFirstLetterAndCategory) {
            ((ActivityFirstLetterAndCategory) activity).updateNames(nameContents);
        } else if (activity instanceof ActivityRandom) {
            ((ActivityRandom) activity).updateNames(nameContents);
        }
    }


    @Override
    public void errorNames(String error) {
        // Handle error when fetching a list of Name objects
        System.err.println("Error fetching names: " + error);
    }
     public void fillterByLetter (String letterFilter, List<Name> names){
         List<String> nameContents = new ArrayList<>();
        for (Name name:names){

            if (name.getFirstChar().equalsIgnoreCase(letterFilter)){
                nameContents.add(name.getContent());
                Log.d("ssts",""+name.getContent());
                System.out.println(name.getContent());
            }
        }
         if (activity instanceof ActivityAllNames) {
             ((ActivityAllNames) activity).updateNames(nameContents);
         } else if (activity instanceof ActivityByCategory) {
             ((ActivityByCategory) activity).updateNames(nameContents);
         } else if (activity instanceof ActivityFirstLetter) {
             ((ActivityFirstLetter) activity).updateNames(nameContents);
         } else if (activity instanceof ActivityFirstLetterAndCategory) {
             ((ActivityFirstLetterAndCategory) activity).updateNames(nameContents);
         } else if (activity instanceof ActivityRandom) {
             ((ActivityRandom) activity).updateNames(nameContents);
         }
     }

    public void fillterByCategory (String categoryFillter, List<Name> names){
        List<String> nameContents = new ArrayList<>();
        for (Name name:names){
            if (name.getCategory().equalsIgnoreCase(categoryFillter)){
                nameContents.add(name.getContent());
                Log.d("ssts",""+name.getContent());
                System.out.println(name.getContent());
            }
        }
        if (activity instanceof ActivityAllNames) {
            ((ActivityAllNames) activity).updateNames(nameContents);
        } else if (activity instanceof ActivityByCategory) {
            ((ActivityByCategory) activity).updateNames(nameContents);
        } else if (activity instanceof ActivityFirstLetter) {
            ((ActivityFirstLetter) activity).updateNames(nameContents);
        } else if (activity instanceof ActivityFirstLetterAndCategory) {
            ((ActivityFirstLetterAndCategory) activity).updateNames(nameContents);
        } else if (activity instanceof ActivityRandom) {
            ((ActivityRandom) activity).updateNames(nameContents);
        }
    }

    public void fillterByCategoryAndLetter (String categoryFillter,String letterFilter, List<Name> names){
        List<String> nameContents = new ArrayList<>();
        for (Name name:names){
            if (name.getCategory().equalsIgnoreCase(categoryFillter)&&name.getFirstChar().equalsIgnoreCase(letterFilter)){
                Log.d("ssts",""+name.getContent());
                nameContents.add(name.getContent());
            }

        }
        if (activity instanceof ActivityAllNames) {
            ((ActivityAllNames) activity).updateNames(nameContents);
        } else if (activity instanceof ActivityByCategory) {
            ((ActivityByCategory) activity).updateNames(nameContents);
        } else if (activity instanceof ActivityFirstLetter) {
            ((ActivityFirstLetter) activity).updateNames(nameContents);
        } else if (activity instanceof ActivityFirstLetterAndCategory) {
            ((ActivityFirstLetterAndCategory) activity).updateNames(nameContents);
        } else if (activity instanceof ActivityRandom) {
            ((ActivityRandom) activity).updateNames(nameContents);
        }
    }

    public void Randomfillter ( List<Name> names){
        List<String> nameContents = new ArrayList<>();
        Random random = new Random();
        int randomNumber = random.nextInt(102) + 1; // Generates random number between 0 (inclusive) and 102 (exclusive)
        nameContents.add(names.get(randomNumber).getContent());
                Log.d("ssts",""+names.get(randomNumber).getContent());
                System.out.println(names.get(randomNumber).getContent());
        if (activity instanceof ActivityAllNames) {
            ((ActivityAllNames) activity).updateNames(nameContents);
        } else if (activity instanceof ActivityByCategory) {
            ((ActivityByCategory) activity).updateNames(nameContents);
        } else if (activity instanceof ActivityFirstLetter) {
            ((ActivityFirstLetter) activity).updateNames(nameContents);
        } else if (activity instanceof ActivityFirstLetterAndCategory) {
            ((ActivityFirstLetterAndCategory) activity).updateNames(nameContents);
        } else if (activity instanceof ActivityRandom) {
            ((ActivityRandom) activity).updateNames(nameContents);
        }



    }
}