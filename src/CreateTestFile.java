package fileio.ex08.bufferedwriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CreateTestFile {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        Writer out = new FileWriter("userRepo.txt");
        BufferedWriter bOut = new BufferedWriter(out);

        Long sequence = 0L;
        String name = "";
        String pw = "null";
        int createDataCnt = 2;

        for (int i = 0; i < createDataCnt; i++) {

            name = getName();
            String str = sequence + "/" + name + "/" + pw ;

            bOut.write(str);
            bOut.newLine();
            sequence++;
        }

        bOut.close();
    }

    private static String getName() {
        String[] lastNameArray = {"김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안", "송", "전", "홍"};
        String[] firstNameArray = {"민준", "서준", "예준", "도윤", "시우", "주원", "하준", "지호", "지후", "준서", "준우", "현우", "도현", "서연", "서윤", "지우", "민서", "지민", "예은", "채원"};

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
