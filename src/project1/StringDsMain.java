package project1;

import models.StringDs;
import java.util.Scanner;

public class StringDsMain {

    public static void main(String[] args) {

        StringDsMain.stringDsMain();

    }

    public static void stringDsMain(){

        StringDsService stringDsService = new StringDsServiceImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter characters (Hit 0 when finished!): ");
        StringDs stringDs =  stringDsService.getInputChars();
        stringDsService.print(stringDs);

        System.out.println("Enter the character you're searching for it's position(First position): ");
        char searchChar = scanner.nextLine().charAt(0);
        int charPosition = stringDsService.findFirstPositionOfChar(stringDs,searchChar);
        System.out.println("Found at index: " + charPosition);

        System.out.println("Enter the character you're searching for it's position: ");
        char searchChar2 = scanner.nextLine().charAt(0);
        int[] positionArray = stringDsService.findPositionsOfChar(stringDs,searchChar2);
        int numOfArrayElements = 0;
        for (int element:positionArray){
            numOfArrayElements++;
        }
        if (numOfArrayElements>0){
            System.out.println("Found at positions: ");
            for (int position:positionArray){

                System.out.println(position);

            }
        }else System.out.println("Not found");


        System.out.println("Enter a substring you're searching for: ");
        models.StringDs subStringDs = stringDsService.getInputChars();
        stringDsService.print(subStringDs);
        int position= stringDsService.findSubarrayPosition(stringDs,subStringDs);
        if (position != -1) {
            System.out.println("Subarray found at position: " + position);}
        else {
            System.out.println("Subarray not found in the array.");
        }

        System.out.println("Enter the start number: ");
        int start = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the end number: ");
        int end = scanner.nextInt();
        scanner.nextLine();
        if (stringDsService.produceSubArray(stringDs,start,end)==null){
            System.out.println("Not available");
        }
        else {
            char[] subArray = stringDsService.produceSubArray(stringDs,start,end);
            for (char c:subArray){
                System.out.print(c);
            }
            System.out.println();
        }

        System.out.println("Enter start number for deletion: ");
        int startForDeletion = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter end number for deletion: ");
        int endForDeletion = scanner.nextInt();
        scanner.nextLine();
        char[] newArray = stringDsService.deleteSubArrayByIndex(stringDs,startForDeletion,endForDeletion);
        for (char c:newArray) {
            System.out.print(c);
        }

        System.out.println("Enter the second string to find the lcs: ");
        StringDs stringDs2 =  stringDsService.getInputChars();
        char[] lcs = stringDsService.lcs(stringDs,stringDs2);
        for (char c: lcs){
            if (c!=0){
                System.out.print(c);
            }
        }
    }
}


