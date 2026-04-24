package org.htw.prog2.aufgabe1;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;

import java.io.IOException;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashSet;


public class SeqFile {
    LinkedHashSet<String> orderedFasta = new LinkedHashSet<>();
    HashSet<String> fasta = new HashSet<>();





    /**
     * Reads the specified FASTA file and stores sequences. In case the file does not exist or is not a valid FASTA
     * file, the Constructor does not throw an Exception. Instead, isValid() on the resulting object will return false.
     * @param
     */
    public SeqFile(String filename)throws IOException, FileFormatException {
        boolean lasttLineWasHeader = false;
        StringBuilder string = new StringBuilder();



        BufferedReader in;
        in = new BufferedReader(new FileReader(filename));
        String line;
        int lineCounter = 0;

        while ((line = in.readLine()) != null) {
            lineCounter++;


            if(line.startsWith(">")) {

                if(lasttLineWasHeader){
                    throw new FileFormatException("Two header lines are directly following each other.");

                }


                if(string.length() != 0){
                    orderedFasta.add(string.toString());
                    string.setLength(0);

                }

                lasttLineWasHeader = true;
            }


            else if(!(line.startsWith(">")) && lineCounter==1){
                throw new FileFormatException("FASTA File does not start with sequence header line.");
            }
            else{
                string.append(line);
                lasttLineWasHeader = false;
            }





        }
        if(string.length() == 0 && lasttLineWasHeader){
            throw new FileFormatException("The last line is a sequence header.");
        }
        else{
            orderedFasta.add(string.toString());
        }


    }

    /**
     * Reads the specified FASTA file.
     * @param filename The path to the FASTA file
     * @return false if the file could not be parsed (wrong format, does not exist), true otherwise.
     */
    private boolean readFile(String filename) {
        return false;
    }

    /**
     * Adds the sequence in the passed StringBuilder to the internal hash set and also sets the first sequence if it
     * is still empty.
     * @param seq SequenceBuilder to get the sequence from.
     * @return The length of the added sequence.
     */
    private int addSequence(StringBuilder seq) {
        return -1;
    }

    /**
     *
     * @return The number of sequences read from the FASTA file, or 0 if isValid() is false.
     */
    public int getNumberOfSequences() {
        return orderedFasta.size();
    }

    /**
     *
     * @return The sequences read from the FASTA file, or an empty HashSet if isValid() is false.
     */
    public HashSet<String> getSequences() {
        fasta = orderedFasta;
        return fasta;
    }

    /**
     *
     * @return The first sequence read from the FASTA file, or an empty String if isValid() is false.
     */
    public String getFirstSequence() {
        return orderedFasta.iterator().next();
    }


}
