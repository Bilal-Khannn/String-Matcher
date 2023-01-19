package com.example.javafxdemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Scene1Controller implements Initializable {

    @FXML
    private CheckBox checkbox2;
    @FXML
    private Label toplabel;
    @FXML
    private ChoiceBox<String> choicebox;

    @FXML
    private Button findbutton;

    @FXML
    private TextField textbox;

    @FXML ListView<String> list;            //To display the results in the form of a list

    BruteForce bruteForce;                  //An object of BruteForce class to access its contents

    RabinKarp rabinKarp;                    //An object of RabinKarp class to access its contents

    KMP kmp;                                //An object of KMP class to access its contents

    String[] algos = {"Brute Force", "Rabin-Karp", "KMP"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choicebox.getItems().addAll(algos);
        choicebox.setOnAction(this::getAlgo);
    }

    public void getAlgo(ActionEvent event){
        String myAlgo = choicebox.getValue();
        toplabel.setText(myAlgo);
    }

    public void onFind(ActionEvent event) throws FileNotFoundException
    {
        list.getItems().clear();

        if(choicebox.getSelectionModel().isEmpty())
        {
            list.getItems().add("Please select an algorithm to perform the search!");
        }
        else if (choicebox.getSelectionModel().getSelectedItem() == "Brute Force")
        {
            if(checkbox2.isSelected())
            {
                searchbyBruteForce();               //Call the method that uses brute force to search
            }
            else
            {
                searchbyBruteForceCaseFree();
            }

        }
        else if(choicebox.getSelectionModel().getSelectedItem() == "Rabin-Karp")
        {
            if(checkbox2.isSelected())
            {
                searchbyRabinKarp();                //Call the method that uses Rabin Karp to search
            }
            else
            {
                searchbyRabinKarpCaseFree();
            }
        }
        else if(choicebox.getSelectionModel().getSelectedItem() == "KMP")
        {
            if(checkbox2.isSelected())
            {
                searchbyKMP();
            }
            else
            {
                searchbyKMPMatchCase();                //Call the method that uses KMP to search
            }

        }
    }

    public void searchbyBruteForce() throws FileNotFoundException {
        list.getItems().clear();            //Remove current items from the list when find is pressed again

        String tofind = textbox.getText();

        if(tofind == " " || tofind == "")
        {
            list.getItems().add("Please enter a word to search!");
            return;
        }
        else
        {
            bruteForce = new BruteForce();

            File folder = new File("C:\\Users\\786-786\\Documents\\JavaFXdemo\\src\\main\\java\\com\\example\\javafxdemo\\DataFiles");
            File[] listOfFiles = folder.listFiles();
            Scanner input;

            List<Integer> counter;

            for (int i = 0; i < listOfFiles.length; i++)
            {
                File file = listOfFiles[i];

                if (file.isFile() && file.getName().endsWith(".txt"))
                {
                    List<String>matchedIndexes = bruteForce.bruteForceStringMatcher(file, tofind);

                    for (String matchedIndex : matchedIndexes)
                    {
                        list.getItems().add("'" + matchedIndex + "' found in file " + file.getName());
                    }
                    if (matchedIndexes.size() == 0)
                    {
                        list.getItems().add("String not found in file " + file.getName());
                    }
                }
            }
        }
    }


    public void searchbyBruteForceCaseFree() throws FileNotFoundException {
        list.getItems().clear();            //Remove current items from the list when find is pressed again

        String tofind = textbox.getText();

        if(tofind == " " || tofind == "")
        {
            list.getItems().add("Please enter a word to search!");
            return;
        }
        else
        {
            bruteForce = new BruteForce();

            File folder = new File("C:\\Users\\786-786\\Documents\\JavaFXdemo\\src\\main\\java\\com\\example\\javafxdemo\\DataFiles");
            File[] listOfFiles = folder.listFiles();
            Scanner input;

            List<Integer> counter;

            for (int i = 0; i < listOfFiles.length; i++)
            {
                File file = listOfFiles[i];

                if (file.isFile() && file.getName().endsWith(".txt"))
                {
                    List<String>matchedIndexes = bruteForce.bruteForceStringMatcherCaseFree(file, tofind);

                    for (String matchedIndex : matchedIndexes)
                    {
                        list.getItems().add("'" + matchedIndex + "' found in file " + file.getName());
                    }
                    if (matchedIndexes.size() == 0)
                    {
                        list.getItems().add("String not found in file " + file.getName());
                    }
                }
            }
        }
    }


    public void searchbyRabinKarp() throws FileNotFoundException {
        list.getItems().clear();            //Remove current items from the list when find is pressed again

        String tofind = textbox.getText();

        if(tofind == " " || tofind == "")
        {
            list.getItems().add("Please enter a word to search!");
            return;
        }
        else
        {
            rabinKarp = new RabinKarp();

            File folder = new File("C:\\Users\\786-786\\Documents\\JavaFXdemo\\src\\main\\java\\com\\example\\javafxdemo\\DataFiles");
            File[] listOfFiles = folder.listFiles();
            Scanner input;

            for (int i = 0; i < listOfFiles.length; i++)
            {
                File file = listOfFiles[i];

                if (file.isFile() && file.getName().endsWith(".txt"))
                {
                    List<String>matchedIndexes = rabinKarp.search(file, tofind, 13);

                    for (String matchedIndex : matchedIndexes)
                    {
                        list.getItems().add("'" + matchedIndex + "' found in file " + file.getName());
                    }
                    if (matchedIndexes.size() == 0)
                    {
                        list.getItems().add("String not found in file " + file.getName());
                    }
                }
            }
        }
    }


    public void searchbyRabinKarpCaseFree() throws FileNotFoundException {
        list.getItems().clear();            //Remove current items from the list when find is pressed again

        String tofind = textbox.getText();

        if(tofind == " " || tofind == "")
        {
            list.getItems().add("Please enter a word to search!");
            return;
        }
        else
        {
            rabinKarp = new RabinKarp();
            bruteForce = new BruteForce();

            File folder = new File("C:\\Users\\786-786\\Documents\\JavaFXdemo\\src\\main\\java\\com\\example\\javafxdemo\\DataFiles");
            File[] listOfFiles = folder.listFiles();
            Scanner input;

            for (int i = 0; i < listOfFiles.length; i++)
            {
                File file = listOfFiles[i];

                if (file.isFile() && file.getName().endsWith(".txt"))
                {
                    List<String>matchedIndexes = bruteForce.bruteForceStringMatcherCaseFree(file, tofind);

                    for (String matchedIndex : matchedIndexes)
                    {
                        list.getItems().add("'" + matchedIndex + "' found in file " + file.getName());
                    }
                    if (matchedIndexes.size() == 0)
                    {
                        list.getItems().add("String not found in file " + file.getName());
                    }
                }
            }
        }
    }

    public void searchbyKMP() throws FileNotFoundException
    {
        list.getItems().clear();            //Remove current items from the list when find is pressed again

        String tofind = textbox.getText();

        if(tofind == " " || tofind == "")
        {
            list.getItems().add("Please enter a word to search!");
            return;
        }
        else
        {
            kmp = new KMP();

            File folder = new File("C:\\Users\\786-786\\Documents\\JavaFXdemo\\src\\main\\java\\com\\example\\javafxdemo\\DataFiles");
            File[] listOfFiles = folder.listFiles();
            Scanner input;

            for (int i = 0; i < listOfFiles.length; i++)
            {
                File file = listOfFiles[i];

                if (file.isFile() && file.getName().endsWith(".txt"))
                {
                    List<String>matchedIndexes = kmp.KMPSearchMatchCase(file, tofind);

                    for (String matchedIndex : matchedIndexes)
                    {
                        list.getItems().add("'" + matchedIndex + "' found in file " + file.getName());
                    }
                    if (matchedIndexes.size() == 0)
                    {
                        list.getItems().add("String not found in file " + file.getName());
                    }
                }
            }
        }
    }

    public void searchbyKMPMatchCase() throws FileNotFoundException
    {
        list.getItems().clear();            //Remove current items from the list when find is pressed again

        String tofind = textbox.getText();

        if(tofind == " " || tofind == "")
        {
            list.getItems().add("Please enter a word to search!");
            return;
        }
        else
        {
            kmp = new KMP();

            File folder = new File("C:\\Users\\786-786\\Documents\\JavaFXdemo\\src\\main\\java\\com\\example\\javafxdemo\\DataFiles");
            File[] listOfFiles = folder.listFiles();
            Scanner input;

            for (int i = 0; i < listOfFiles.length; i++)
            {
                File file = listOfFiles[i];

                if (file.isFile() && file.getName().endsWith(".txt"))
                {
                    List<String>matchedIndexes = kmp.KMPSearch(file, tofind);

                    for (String matchedIndex : matchedIndexes)
                    {
                        list.getItems().add("'" + matchedIndex + "' found in file " + file.getName());
                    }
                    if (matchedIndexes.size() == 0)
                    {
                        list.getItems().add("String not found in file " + file.getName());
                    }
                }
            }
        }
    }
}