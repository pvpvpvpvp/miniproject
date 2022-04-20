import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CreateData {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        Writer out = new FileWriter("userRepo.txt");
        BufferedWriter bOut = new BufferedWriter(out);

        Long sequence = 0L;
        String name = "";
        String pw = "null";

        int createDataCnt = 40;

        for (int i = 0; i < createDataCnt; i++) {

            name = getName();
            String str = name + " " + name+(float) (Math.random()*100000);;

            bOut.write(str);
            bOut.newLine();
            sequence++;
        }

        bOut.close();
    }

    private static String getName() {
        String[] lastNameArray = {"Smith", "Johnson", "Martin", "Jackson", "Moore", "Taylor", "Thomas", "Anderson", "Wilson", "Gonzalez", "Lopez", "Hernandez", "Martinez", "Rodriguez", "Davis", "Miller", "Garcia", "Jones", "Brown", "Williams"};
        String[] firstNameArray = {"Armaan", "Deven", "Richard", "Lucas", "Kevin", "Ishaan", "Elijah", "Andrew", "Elijah", "Charles", "Aiden", "Alexander", "Angel", "Babara", "Fiona", "Zoe", "Olivia", "Madison", "Linda", "Susan"};

        int lastNameRandIndex = (int)(Math.random()*lastNameArray.length);
        int firstNameRandIndex = (int)(Math.random()*firstNameArray.length);

        String lastName = lastNameArray[lastNameRandIndex];
        String firstName = firstNameArray[firstNameRandIndex];

        sb.setLength(0);
        sb.append(lastName);
        sb.append(firstName);

        return sb.toString();
    }
}
