import java.io.IOException;
import java.util.ArrayList;

public class Tester {

    public static void main(String[] args) throws IOException {
        ArrayList<String> calculatedData = FileHandler.readFileToArrayList("inputData.txt");
        FileHandler.saveResultToFile(calculatedData, "Result.txt");
    }
}
