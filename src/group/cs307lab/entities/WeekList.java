package group.cs307lab.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AnGuangyan
 * @date 2021/3/11
 */
public class WeekList {
    private static final List<WeekList> WEEK_LISTS = new ArrayList<>();
    static {
        WEEK_LISTS.add(new WeekList("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]"));
        WEEK_LISTS.add(new WeekList("[2, 4, 6, 8, 10, 12, 14]"));
        WEEK_LISTS.add(new WeekList("[1, 3, 5, 7, 9, 11, 13, 15]"));
        WEEK_LISTS.add(new WeekList("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]"));
        WEEK_LISTS.add(new WeekList("[2, 4, 6, 8]"));
        WEEK_LISTS.add(new WeekList("[1, 2, 3, 4, 5, 6, 7, 8]"));
        WEEK_LISTS.add(new WeekList("[8, 9, 10, 11, 12, 13, 14, 15]"));
        WEEK_LISTS.add(new WeekList("[1, 3, 5, 7]"));
        WEEK_LISTS.add(new WeekList("[1, 2, 3, 4]"));
        WEEK_LISTS.add(new WeekList("[5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]"));
        WEEK_LISTS.add(new WeekList("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15]"));
        WEEK_LISTS.add(new WeekList("[2, 4, 6, 8, 10, 12, 14, 16]"));
        WEEK_LISTS.add(new WeekList("[9, 10, 11, 12, 13, 14, 15]"));
        WEEK_LISTS.add(new WeekList("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]"));
    }

    public static WeekList getInstance(String name) {
        name = name.trim();
        for (WeekList w : WEEK_LISTS) {
            if (w.timeTable.equals(name)) {
                return w;
            }
        }
        throw NoSuchInstanceException.getInstance("WeekList");
    }

    public static boolean addWeekList(String name) {
        name = name.trim();
        for (WeekList w : WEEK_LISTS) {
            if (w.timeTable.equals(name)) {
                return false;
            }
        }
        WEEK_LISTS.add(new WeekList(name));
        return true;
    }

    private WeekList(String timeTable) {
        this.timeTable = timeTable;
    }

    private final String timeTable;

    public String getTimeTable() {
        return timeTable;
    }
}
