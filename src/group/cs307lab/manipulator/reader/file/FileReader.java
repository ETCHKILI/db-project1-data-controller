package group.cs307lab.manipulator.reader.file;

import group.cs307lab.manipulator.FileManipulation;

/**
 * @author AnGuangyan
 * @date 2021/3/11
 */
public interface FileReader {

    /**
     * Read object from String array
     * @param arr array split by "--"
     * @param manipulation which contains the List
     *                     of course, student, etc.
     * @return the read in object
     */
    Object read(String[] arr, FileManipulation manipulation);
}
