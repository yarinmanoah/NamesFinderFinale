package com.yarin.namegenerator;

import java.util.List;
import java.util.ArrayList;

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
        // When the list of names is returned, we convert List<Name> to List<String> before passing it
        void onSuccess(List<String> names);

        void onError(String error);
    }

    // This callback is passed to the NameController to handle responses
    public static class NameControllerCallback implements NameController.CallBack_Name {

        private final NameCallback nameCallback;

        public NameControllerCallback(NameCallback nameCallback) {
            this.nameCallback = nameCallback;
        }

        @Override
        public void successNames(List<Name> names) {
            // Convert List<Name> to List<String>
            List<String> nameStrings = new ArrayList<>();
            for (Name name : names) {
                nameStrings.add(name.getContent());
            }
            nameCallback.onSuccess(nameStrings);
        }

        @Override
        public void errorNames(String error) {
            nameCallback.onError(error);
        }

        @Override
        public void fillterByLetter(String letterFilter, List<Name> names) {
            List<String> nameStrings = new ArrayList<>();
            for (Name name : names) {
                nameStrings.add(name.getContent());
            }
            nameCallback.onSuccess(nameStrings);
        }

        @Override
        public void fillterByCategory(String categoryFilter, List<Name> names) {
            List<String> nameStrings = new ArrayList<>();
            for (Name name : names) {
                nameStrings.add(name.getContent());
            }
            nameCallback.onSuccess(nameStrings);
        }

        @Override
        public void fillterByCategoryAndLetter(String categoryFilter, String letterFilter, List<Name> names) {
            List<String> nameStrings = new ArrayList<>();
            for (Name name : names) {
                nameStrings.add(name.getContent());
            }
            nameCallback.onSuccess(nameStrings);
        }

        @Override
        public void Randomfillter(Name name) {
            List<String> nameStrings = new ArrayList<>();
            nameStrings.add(name.getContent());
            nameCallback.onSuccess(nameStrings);
        }
    }
}


//package com.yarin.namegenerator;
//
//import java.util.List;
//public class NameGeneratorSDK {
//
//    private final NameController nameController;
//
//    public NameGeneratorSDK(NameController.CallBack_Name myCallback) {
//
//        this.nameController = new NameController(myCallback);
//    }
//
//    public void fetchAllNames() {
//        nameController.fetchAllName();
//    }
//
//    public void fetchNamesByLetter(String letter) {
//        nameController.fetchByLetter(letter);
//    }
//
//    public void fetchNamesByCategory(String category) {
//        nameController.fetchByCategory(category);
//    }
//
//    public void fetchNamesByCategoryAndLetter(String category, String letter) {
//        nameController.fetchByCategoryAndLetter(category, letter);
//    }
//
//    public void fetchRandomName() {
//        nameController.fetchByRandom();
//    }
//
//    public interface NameCallback {
//        void onSuccess(List<String> names);
//
//        void onError(String error);
//    }
//}
