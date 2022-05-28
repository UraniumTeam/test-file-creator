import org.jooq.types.UInteger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        var scanner = new Scanner(System.in);
        System.out.println("Введите количество файлов:");
        var countFiles = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите количество функций:");
        UInteger funcsCount = UInteger.valueOf(scanner.nextLong());
        System.out.println("Введите количество событий:");
        scanner.nextLine();
        UInteger eventCount = UInteger.valueOf(scanner.nextLong());
        for (var i = 0; i < countFiles; i++) {
            var timeStamp = new SimpleDateFormat("MM-DD.HH.MM.SS.MS").format(new java.util.Date());
            createFile("testNew" + timeStamp + ".ups", fileConstructor(funcsCount, eventCount));
        }
    }

    public static String fileConstructor(UInteger funcsCount, UInteger eventCount) {
        var rnd = new Random();
        var nsInTicks = rnd.nextDouble(100, 1000);
        var funcs = Function.getRandomFuncs(funcsCount);

        StringBuilder sb = new StringBuilder();
        sb.append(nsInTicks + "\n" + funcs.size() + "\n");
        for (var i = 0; i < funcs.size(); i++) {
            sb.append(funcs.get(i).name);
        }
        sb.append("\n" + eventCount + "\n");
        for (var i = 0; i < funcs.size(); i++) {
            sb.append("0000" + funcs.get(i).index + funcs.get(i).cpu_tick);
        }
        return sb.toString();
    }

    public static void createFile(String name, String data) throws IOException {
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(name), StandardCharsets.UTF_8));
        try {
            out.write(data);
        } finally {
            out.close();
        }
    }

    public static String generateRandomWords() {
        Random random = new Random();
        char[] word = new char[random.nextInt(8) + 3]; // words of length 3 through 10
        word[0] = (char) ('A' + random.nextInt(25));
        for (int i = 1; i < word.length; i++) {
            word[i] = (char) ('a' + random.nextInt(26));
        }
        var randomWord = new String(word);
        return randomWord;
    }
}
