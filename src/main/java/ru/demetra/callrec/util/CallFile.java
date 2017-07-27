package ru.demetra.callrec.util;

import java.io.File;

/**
 * Class for search callrec file and get path on PBX
 *
 * @author Vyacheslav Y.
 * @version 2.0
 */
public class CallFile {


    String dir = "/var/spool/asterisk/monitor/";
    String prefix = "/sound/";
    String tmp;

    /**
     * Recursive method for search callrec file on HDD PBX
     */
    public String findFile(File dir, String path) {
        File file = new File(dir.getAbsolutePath() + "//" + path);

        if (file.isFile() && file.exists()) {
            tmp = file.getAbsolutePath();
        }

        File[] files = dir.listFiles();
        for (File f : files) {
            if (tmp == null) {
                if (f.isDirectory()) {
                    findFile(f, path);
                }
            }
        }
        return tmp;
    }

    public String pathSelector(String path) {

        if (path.contains("/")) {
            return path.replace(dir, prefix);

        } else {
            path = findFile(new File(dir), path);
            tmp = null;
            return path.replace(dir, prefix);
        }
    }
}
