package com.bereda.mateusz;

import com.bereda.mateusz.repository.UserRepo;
import com.bereda.mateusz.view.GUI;

public class Main {
    public static void main(String[] args) {
        UserRepo.initFakeData();
        GUI.helloScreen();
    }
}
