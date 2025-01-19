package com.yarin.namegenerator;

import java.util.List;
public class NameGeneratorSDK {

    private final NameController nameController;

    public NameGeneratorSDK(NameController.CallBack_Name myCallback) {

        this.nameController = new NameController(myCallback);
    }

    public void fetchAllNames() {
        nameController.fetchAllName();
    }

    public void fetchNamesByLetter(String letter) {
        nameController.fetchByLetter(letter);
    }

    public void fetchNamesByCategory(String category) {
        nameController.fetchByCategory(category);
    }

    public void fetchNamesByCategoryAndLetter(String category, String letter) {
        nameController.fetchByCategoryAndLetter(category, letter);
    }

    public void fetchRandomName() {
        nameController.fetchByRandom();
    }

    public interface NameCallback {
        void onSuccess(List<String> names);

        void onError(String error);
    }
}
