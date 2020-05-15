import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public static ArrayList<String> readFileToArrayList(String fileName) throws IOException {
        File file = new File(fileName);
        ArrayList<String> calculatedValues = new ArrayList<>();

        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader)
        ) {
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                calculatedValues.add(processLine(nextLine));
            }
            return calculatedValues;
        }
    }


    public static void saveResultToFile(ArrayList<String> result, String fileName) {
        try (
                var fileWriter = new FileWriter(fileName);
                var writer = new BufferedWriter(fileWriter)
        ) {
            for (String s : result) {
                writer.write(s);
                writer.newLine();
            }

            System.out.println("Report created");
        } catch (IOException e) {
            System.err.println("Unable to save the file " + fileName);
        }
    }

    private static String processLine(String nextLine) {
        String[] splitValues;
        if (nextLine.contains("+")) {
            splitValues = nextLine.split("\\+");
            return splitValues[0] + "+" + splitValues[1] + " = " + (Integer.parseInt(splitValues[0].trim()) + Integer.parseInt(splitValues[1].trim()));
        } else if (nextLine.contains("-")) {
            splitValues = nextLine.split("-");
            return splitValues[0] + "-" + splitValues[1] + " = " + String.valueOf(Integer.parseInt(splitValues[0].trim()) - Integer.parseInt(splitValues[1].trim()));
        }
        if (nextLine.contains("*")) {
            splitValues = nextLine.split("\\*");
            return splitValues[0] + "*" + splitValues[1] + " = " + String.valueOf(Integer.parseInt(splitValues[0].trim()) * Integer.parseInt(splitValues[1].trim()));
        } else {
            throw new IllegalArgumentException("Operator not recognized");
        }
    }
}
