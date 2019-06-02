package Service;

import model.AviaTicket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class WriterInFile {

    private String FILENAME = "output.txt";
    private File file = new File(System.getProperty("user.dir") + "/" + FILENAME);

    public Boolean writeAviaTicket(AviaTicket aviaTicket){
        return writeStringInFile(aviaTicket.toString());

    }


    private Boolean writeStringInFile(String str){
        try {
            FileWriter fileWriter = new FileWriter(checkFile(file),true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(str);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (Exception e) {
            e.getMessage();
        }
        return true;
    }

    private File checkFile(File file){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return file;
    }
}
