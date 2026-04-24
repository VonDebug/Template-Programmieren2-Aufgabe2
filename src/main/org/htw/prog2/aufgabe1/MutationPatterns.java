package org.htw.prog2.aufgabe1;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;

import java.io.*;
import java.util.*;

public class MutationPatterns {
    HashSet<String> mutationpatternList = new HashSet<>();



    /**
     * Contructor für MutationPatterns. Liest die CSV-Datei filename ein.
     * @param filename Pfad zu CSV-Datei zum Einlesen
     * @throws IOException bei allgemeinen IO-Fehlern
     * @throws FileNotFoundException falls die Datei nicht gefunden wurde
     * @throws FileFormatException falls das Format der Definitionszeile inkorrekt ist oder die Anzahl der Spalten
     * nicht in jeder Zeile gleich ist
     */
    public MutationPatterns(String filename) throws IOException, FileNotFoundException, FileFormatException {
        String definitionTemplate = "\"Mutation Patterns\";\"Number of Sequences\"";
        boolean secondLineisDefinition = false;
        int lengthOfDefinitionLine = 0;



        BufferedReader in;
        in = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = in.readLine()) != null) {

            if(line.startsWith("#")){
                continue;
            }

            if(line.contains("\"Mutation")) {

                if (!line.startsWith(definitionTemplate)) {

                    throw new FileFormatException("Neeeein, kaputt :(");
                }
                else{

                    String [] arrayOfLines = line.split(";");

                    for(int i = 2; i< arrayOfLines.length; i++){
                        if(arrayOfLines[i].split(" ").length !=2){

                            throw new FileFormatException("Alles mies halt.");

                        }
                    }
                    secondLineisDefinition = true;
                    lengthOfDefinitionLine = (line.split(";")).length;




                }
            }


            else{
                if(line.split(";").length != lengthOfDefinitionLine) {
                    throw new FileFormatException("All lines in a CSV file must have the same number of elements.");
                }
                else{
                    this.mutationpatternList.add(line);
                    if(!secondLineisDefinition){
                        throw new FileFormatException("First line of mutation pattern CSV file must be a header.");
                    }

                }
            }
        }





    }

    /**
     * Gibt die Anzahl der eingelesenen Mutationspattern zurück.
     * @return Anzahl der eingelesenen Mutationspattern
     */
    public int getNumberOfMutations() {

        return this.mutationpatternList.size();
    }

    /**
     * Parst die Definitionszeile.
     * @param line Definitionszeile aus der CSV-Datei
     * @return Liste der Medikamentennamen aus der Definitionszeile
     */
    public static List<String> parseDrugs(String line) throws FileFormatException {
        String templateDefinition = "\"Mutation Patterns\";\"Number of Sequences\"";
        String drugNameTemplate = "foldn\"";

        if(!line.startsWith(templateDefinition)){
            throw new FileFormatException("");
        }


        List<String> drugList = new ArrayList<>();
        String[] lineArray = line.split(";");


        for(int i =2; i< lineArray.length; i++){

            if(!lineArray[i].contains(drugNameTemplate)){
                throw new FileFormatException("");
            }

            drugList.add(lineArray[i].replace("\"", "").split(" ")[0]);
        }

        return drugList;
    }
}
