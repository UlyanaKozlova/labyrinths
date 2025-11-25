package academy.data.processors;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Хранилище для символьного представления лабиринта. */
public class CharStorage {
    public static final char EMPTY = ' ';
    public static final char ROAD = '.';
    public static final char START = 'O';
    public static final char END = 'X';
    public static final char WALL = '#';
    public static final char NEW = '\n';
    public static final List<Character> WALLS_CHARS =
            List.of('#', '├', '┤', '┴', '┬', '┼', '┌', '┐', '└', '┘', '─', '│');
    static final Map<Integer, Character> CHARS;

    static {
        Map<Integer, Character> tempMap = new HashMap<>();
        tempMap.put(2202, '├');
        tempMap.put(2212, '├');
        tempMap.put(222, '┤');
        tempMap.put(1222, '┤');

        tempMap.put(2022, '┴');
        tempMap.put(2122, '┴');

        tempMap.put(2220, '┬');
        tempMap.put(2221, '┬');

        tempMap.put(2222, '┼');

        tempMap.put(2200, '┌');
        tempMap.put(2201, '┌');
        tempMap.put(2210, '┌');
        tempMap.put(2211, '┌');

        tempMap.put(220, '┐');
        tempMap.put(221, '┐');
        tempMap.put(1220, '┐');
        tempMap.put(1221, '┐');

        tempMap.put(2002, '└');
        tempMap.put(2012, '└');
        tempMap.put(2102, '└');
        tempMap.put(2112, '└');

        tempMap.put(22, '┘');
        tempMap.put(122, '┘');
        tempMap.put(1022, '┘');
        tempMap.put(1122, '┘');

        tempMap.put(2020, '─');
        tempMap.put(2120, '─');
        tempMap.put(2021, '─');
        tempMap.put(2121, '─');

        tempMap.put(2000, '─');
        tempMap.put(2001, '─');
        tempMap.put(2010, '─');
        tempMap.put(2011, '─');
        tempMap.put(2100, '─');
        tempMap.put(2101, '─');
        tempMap.put(2110, '─');
        tempMap.put(2111, '─');

        tempMap.put(20, '─');
        tempMap.put(21, '─');
        tempMap.put(120, '─');
        tempMap.put(121, '─');
        tempMap.put(1020, '─');
        tempMap.put(1021, '─');
        tempMap.put(1120, '─');
        tempMap.put(1121, '─');

        tempMap.put(202, '│');
        tempMap.put(1202, '│');
        tempMap.put(212, '│');
        tempMap.put(1212, '│');

        tempMap.put(2, '│');
        tempMap.put(12, '│');
        tempMap.put(102, '│');
        tempMap.put(112, '│');
        tempMap.put(1002, '│');
        tempMap.put(1012, '│');
        tempMap.put(1102, '│');
        tempMap.put(1112, '│');
        tempMap.put(200, '│');
        tempMap.put(201, '│');
        tempMap.put(210, '│');
        tempMap.put(211, '│');
        tempMap.put(1200, '│');
        tempMap.put(1201, '│');
        tempMap.put(1210, '│');
        tempMap.put(1211, '│');
        CHARS = Collections.unmodifiableMap(tempMap);
    }
}
