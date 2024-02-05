package project2;

import models.StringDs;
import project1.StringDsService;
import project1.StringDsServiceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHelper {

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

    public static void EditLineInFile(String fileName, String oldLine, String newLine) throws IOException {

        File file = new File(fileName);


        File temp = new File("Temp");
        temp.createNewFile();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));


        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.equals(oldLine)) {
                continue;
            }
            writer.write(currentLine);
            writer.newLine();
        }


        writer.write(newLine);
        writer.newLine();

        reader.close();
        writer.close();

        reader = new BufferedReader(new FileReader(temp));
        writer = new BufferedWriter(new FileWriter(file));
        while ((currentLine = reader.readLine()) != null) {
            writer.write(currentLine);
            writer.newLine();
        }

        reader.close();
        writer.close();

        temp.delete();

    }



}

