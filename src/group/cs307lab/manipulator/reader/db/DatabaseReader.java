package group.cs307lab.manipulator.reader.db;

import java.sql.*;

/**
 * @author AnGuangyan
 * @date 2021/3/13
 */
public interface DatabaseReader {

    /**
     * read object from database
     * @return the object you want
     */
    Object readFromDatabase();
}
