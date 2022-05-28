import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

import org.jooq.types.*;

public class Function {
    String name;
    UInteger index;
    ULong cpu_tick;


    private Function(String name, UInteger index, ULong cpu_tick) {
        this.name = name;
        this.index = index;
        this.cpu_tick = cpu_tick;
    }

    public static ArrayList<Function> getRandomFuncs(UInteger count) {
        var result = new ArrayList<Function>();
        var rnd = new Random();

        for (var i = UInteger.valueOf(0); i.compareTo(count) < 0; i = i.add(1)) {
            var rndWord = Main.generateRandomWords();
            String name = rndWord.getBytes(StandardCharsets.UTF_8).length + rndWord;
            //byte[] preIndex = new byte[]{0, 0, 0, 0};
            UInteger index = i;
            ULong cpu_ticks = ULong.valueOf(rnd.nextLong());
            result.add(new Function(name, index, cpu_ticks));
        }
        return result;
    }
}

