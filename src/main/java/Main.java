import Service.Parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        Parser parser = new Parser();
        while (true){
            try {
                parser.parse(new BufferedReader( new InputStreamReader(System.in)).readLine());
            }
            catch (Exception ex){
                //mmmm exception
                System.out.println(ex.getMessage());
            }

        }
    }

}
