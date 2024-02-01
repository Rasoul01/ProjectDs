package project1;

import models.StringDs;

import java.util.Scanner;

public class StringDsServiceImpl implements StringDsService {

    @Override
    public char[] add(char character,StringDs stringDs) {

       int size = stringDs.getSize();
       int capacity = stringDs.getCapacity();
       char[] charArray = stringDs.getChars();

        if (size == capacity) {

            capacity *= 2;
            char[] newCharArray = new char[capacity];

            for (int i = 0; i <size; i++){
                newCharArray[i] = charArray[i];

            }

            charArray = newCharArray;

        }

        charArray[size] = character;
        stringDs.setCapacity(capacity);


        return charArray;

    }

    @Override
    public StringDs getInputChars() {

        StringDs stringDs = new StringDs();
        Scanner scanner = new Scanner(System.in);

        boolean continueInput = true;
        int size=0;

        while (continueInput) {

            char inputChar = scanner.nextLine().charAt(0);

            if (inputChar != '0') {

                char[] charList = add(inputChar,stringDs);
                size++;
                stringDs.setSize(size);
                stringDs.setChars(charList);


            }
            else if (inputChar == ' ') {

            } else {

                continueInput = false;

            }

        }

        return stringDs;

    }

    @Override
    public void print(StringDs stringDs) {

        char [] charsTobePrinted = stringDs.getChars();

        for (int i=0;i<stringDs.getSize();i++){

            System.out.print(charsTobePrinted[i]);

        }

        System.out.println();

    }

    @Override
    public int[] findPositionsOfChar(StringDs stringDs,char searchChar) {

        char [] charArray = stringDs.getChars();

        int count = 0;

        for (char character:charArray){

            if (character==searchChar){
                count++;
            }
        }

        int[]positions = new int[count];
        int index = 0;

        for (int i = 0; i<stringDs.getSize();i++){

            if (charArray[i]==searchChar){

                positions[index]=i;
                index++;

            }
        }

         return positions;

    }

    @Override
    public int findFirstPositionOfChar(StringDs stringDs,char searchChar ) {

        char [] charArray = stringDs.getChars();

            for (int i = 0; i < stringDs.getSize(); i++) {

                if (charArray[i] == searchChar) {

                    return i;
                }

            }
         return -1;

    }

    @Override
    public int findSubarrayPosition(StringDs stringDs, StringDs subString) {

        char[] array = stringDs.getChars();
        char[] subArray = subString.getChars();

        if (array == null || subArray == null || subString.getSize() == 0 ||subString.getSize() >stringDs.getSize()) {

            return -1;

        }

        for (int i = 0; i <= stringDs.getSize() - subString.getSize(); i++) {

            boolean match = true;

            for (int j = 0; j < subString.getSize(); j++) {

                if (array[i + j] != subArray[j]) {
                    match = false;
                    break;

                }
            }

            if (match) {

                return i;

            }
        }

        return -1;
    }

    @Override
    public char[] produceSubArray(StringDs stringDs, int start, int end) {

        char[] subArray = new char[(end-start)+1];
        char[] mainChar = stringDs.getChars();

        if (start>end || start<0 || end>stringDs.getSize()){

            return null;

        }

        int j=0;

        for (int i =start;i<=end;i++){

            subArray[j]=mainChar[i];
            j++;

        }

        return subArray;

    }

    @Override
    public char[] deleteSubArrayByIndex(StringDs stringDs,int start,int end) {

        char[] charArray = stringDs.getChars();
        int size = stringDs.getSize();

        if (start < 0 || end >= size || start > end) {

            return null;

        }

        int newSize = size - (end - start + 1);
        char[] newArray = new char[newSize];

        int index = 0;

        for (int i = 0; i < start; i++) {

            newArray[index] = charArray[i];
            index++;

        }

        for (int i = end + 1; i < size; i++) {

            newArray[index] = charArray[i];
            index++;

        }

//        for (int i = 0; i < newSize; i++) {
//            charArray[i] = newArray[i];
//        }

        stringDs.setChars(newArray);
        return newArray;

    }

    @Override
    public char[] lcs(StringDs stringDs1, StringDs stringDs2) {

        int p = stringDs1.getSize();
        int q = stringDs2.getSize();
        char[] charArray1 = stringDs1.getChars();
        char[] charArray2 = stringDs2.getChars();

        int[][] tableForLCS = new int[p + 1][q + 1];

        for (int i = 0; i <= p; i++) {
            for (int j = 0; j <= q; j++) {
                if (i == 0 || j == 0)
                    tableForLCS[i][j] = 0;
                else if (charArray1[i-1] == charArray2[j-1])
                    tableForLCS[i][j] = tableForLCS[i - 1][j - 1] + 1;
                else
                    tableForLCS[i][j] = Math.max(tableForLCS[i - 1][j], tableForLCS[i][j - 1]);
            }
        }

        int index = tableForLCS[p][q];
       // int temp = index;

        char[] longestCommonSubsequence = new char[index+1];
        longestCommonSubsequence[index] = '\0';

        int i = p, j = q;
       // char[] lcs = new char[0];
        while (i > 0 && j > 0) {
            if  (charArray1[i-1] == charArray2[j-1]){

                longestCommonSubsequence[index - 1] = charArray1[i-1];
                i--;
                j--;
                index--;
            }
            else if (tableForLCS[i - 1][j] > tableForLCS[i][j - 1])
                i--;
            else
                j--;
        }

//        for (int k = 0; k <= temp; k++) {
//            lcs = longestCommonSubsequence[k];
//        }
        return longestCommonSubsequence;
    }

}



