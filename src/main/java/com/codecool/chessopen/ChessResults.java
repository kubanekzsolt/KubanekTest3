package com.codecool.chessopen;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName) {
        SortedMap<Integer, String> sortedMap = new TreeMap<>();
        try {
            Stream<String> lineStream = getLineStream(fileName);
            lineStream.forEach(line -> {
                String[] split = line.split(",");
                int sum = 0;
                for (int i = 1; i < split.length; i++) {
                    sum += Integer.parseInt(split[i]);
                }
                sortedMap.put(sum, split[0]);
            });
            List<String> ret = new ArrayList<String>(sortedMap.values());
            Collections.reverse(ret);
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    Stream<String> getLineStream(String fileName) throws IOException {
        Path path = Paths.get(fileName, "");
        return Files.lines(path);
    }
}