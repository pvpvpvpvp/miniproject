package service.user;

import config.Config;

import java.io.*;

public class UserIndex {
    private int index;

    public synchronized int indexUp(){
        String userIndex = Config.USER_INDEX;

        try {

            Reader in = new FileReader("index.txt");
            BufferedReader br = new BufferedReader(in);

            String line;
            while (true) {
                line = br.readLine();

                if (line == null) {
                    break;
                }
                index = Integer.parseInt(line);
            }

            index++;

            OutputStream out = new FileOutputStream("index.txt");
            OutputStreamWriter outW = new OutputStreamWriter(out);
            PrintWriter pw = new PrintWriter(outW);

            br.close();
            pw.println(index);
            pw.flush();
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return index;
    }

}
