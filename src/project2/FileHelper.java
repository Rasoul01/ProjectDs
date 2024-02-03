package project2;

import models.StringDs;
import project1.StringDsService;
import project1.StringDsServiceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHelper {

//  public ArrayList<StringDs> fileHelper(String fileName) throws FileNotFoundException {
//
//      ArrayList<StringDs> lines = new ArrayList<>();
//      Scanner fileReader = new Scanner(new File(fileName));
//      char currentChar = 0;
//      int i = 0;
//      StringDs stringDs = new StringDs();
//      StringDsService stringDsService = new StringDsServiceImpl();
//
//      while (fileReader.hasNext()) {
//
//          while (currentChar != ' ') {
//
//              currentChar = fileReader.nextLine().charAt(i);
//              char[] currentCharArray = stringDsService.add(currentChar, stringDs);
//              stringDs.setChars(currentCharArray);
//              i++;
//
//          }
//
//          lines.add(stringDs);
//
//      }
//
//      fileReader.close();
//
//      for (StringDs s:lines){
//          System.out.println(s.toString());
//      }
//
//      return lines;
//
//  }
    public static ArrayList<String> fileHelper(String fileName) {

        ArrayList<String> lines = new ArrayList<>();

        try (Scanner UsersFileReader = new Scanner(new File(fileName))) {

            while (UsersFileReader.hasNext()) {

                String newLine = UsersFileReader.nextLine();
                lines.add(newLine);

            }

        }
        catch (FileNotFoundException e) {

            e.printStackTrace();

        }

        System.out.println("Main expressions are: ");
        for (String s:lines){

            System.out.println(s);

        }

        return lines;

    }



}

