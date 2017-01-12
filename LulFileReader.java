import java.io.File;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.stream.Stream;

public class LulFileReader {
    public static void main(String[] args) {
        try {
            Stream<String> lines = Files.lines(FileSystems.getDefault().getPath("input.txt"));
            PrintWriter pw = new PrintWriter(new File("output.txt"));
            lines.forEach((e) -> {
                System.out.println(e);
                pw.println(e);
            });
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
