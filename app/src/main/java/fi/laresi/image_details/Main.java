/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package fi.laresi.image_details;


public class Main {
    private static PNGDecoder decoder = new PNGDecoder();
    private static int foundChunks;

    private static void printDetails() {
        System.out.println("Found " + foundChunks + " chunks of data in the image:");
        System.out.println(decoder.toString());
    }


    public static void main(String[] args) {
        try {
            decoder.loadFile(args[0]);
            foundChunks = decoder.readChunks();

            printDetails();
        } catch (ArrayIndexOutOfBoundsException oob) {
            System.out.println("Program requires that atleast one filepath as an argument to be given");
            System.exit(1);
        } catch (IllegalArgumentException iae) {
            System.out.println("Given file wasn't an PNG image");
            System.exit(1);
        }
    }
}
