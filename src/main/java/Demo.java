import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Path path = Paths.get("src","test","resources","framework.properties");
        System.out.println(path.toString());
        System.out.println(path.toAbsolutePath().toString());
        System.out.println(path.getFileName());
        try {
            List<String> strings = Files.readAllLines(path);
            List<String> names =  Arrays.asList("Hitesh","Chavda");
//            Files.write(path);
            for (String st: strings){
                System.out.println(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        File file = new File("C:\\Users\\Hitesh\\Desktop\\PRAGRA\\selwd2\\src\\test\\resources\\framework.properties");
////        System.out.println(file.exists());
////        System.out.println(file.canRead());
//        try {
//            FileInputStream stream = new FileInputStream(file);
//            byte [] buf = new byte[2048];
//            stream.read(buf);
//            System.out.println(new String(buf));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
