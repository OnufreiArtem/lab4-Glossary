package com.lodbrock;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Glossary glossary = new Glossary();
        String filePath = "D:\\harry.txt";

        try {
            FileReader reader = new FileReader(filePath);
            Scanner sc = new Scanner(reader);

            while(sc.hasNext()){

                for(String word : cleanString(sc.nextLine()).split(" ")){
                    if(!word.equals("") && !word.equals("'") && !word.replaceAll("-", "").equals(""))
                        glossary.addWord(word);
                }
            }

            sc.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        glossary.displayOrderedGlossary(new Glossary.GlossaryByCount());
    }

    static String cleanString(String line){
        String resultLine = "";
        for(int i = 0; i < line.length(); i++){
            char currentChar = line.charAt(i);
            if(Character.isLetter(currentChar) || currentChar == ' ' || currentChar =='\'' || currentChar == '-'){
                resultLine += currentChar;
            }
        }
        resultLine = resultLine.replaceAll("\\s{2,}", " ").trim();
        return resultLine;
    }
}
