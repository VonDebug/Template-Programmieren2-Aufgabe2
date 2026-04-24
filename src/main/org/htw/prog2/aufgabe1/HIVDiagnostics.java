package org.htw.prog2.aufgabe1;
import org.apache.commons.cli.*;

public class HIVDiagnostics {

    /**
     * Parst die Kommandozeilenargumente. Gibt null zurück, falls:
     * <ul>
     *     <li>Ein Fehler beim Parsen aufgetreten ist (z.B. eins der erforderlichen Argumente nicht angegeben wurde)</li>
     *     <li>Bei -m, -d und -r nicht die gleiche Anzahl an Argumenten angegeben wurde</li>
     * </ul>
     * @param args Array mit Kommandozeilen-Argumenten
     * @return CommandLine-Objekt mit geparsten Optionen
     */

    public static CommandLine parseOptions(String[] args) {
        Options options = new Options();

        Option m = new Option("m", "mutationfiles", true, "Pfad zur Mutationsdatei");
        m.setRequired(true);
        options.addOption(m);

        Option d = new Option("d", "drugnames", true, "Name des Medikaments");
        d.setRequired(true);
        options.addOption(d);

        Option r = new Option("r", "references", true, "Pfad zur Referenz-FASTA");
        r.setRequired(true);
        options.addOption(r);

        Option p = new Option("p", "patientseqs", true, "Pfad zur Patienten-FASTA");
        p.setRequired(true);
        options.addOption(p);

        CommandLineParser parser = new DefaultParser();
        CommandLine cli;
        try {
            cli = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
            new HelpFormatter().printHelp("HIVDiagnostics", options);
            return null;
        }
        return cli;
    }

    public static void main(String[] args) {

        CommandLine cli = parseOptions(args);
        if(cli != null) {
            try {

                SeqFile seqFile = new SeqFile(cli.getOptionValue("p"));
                SeqFile referenceFile = new SeqFile(cli.getOptionValue("r"));
                MutationPatterns mutationPatterns = new MutationPatterns(cli.getOptionValue("m"));
                System.out.println("Eingelesene Mutationen: " + mutationPatterns.getNumberOfMutations());
                System.out.println("Länge der eingelesenen Referenzsequenz: " + referenceFile.getFirstSequence().length() + " Aminosäuren");
                System.out.println("Anzahl der eingelesenen Patientensequenzen: " + seqFile.getNumberOfSequences());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
