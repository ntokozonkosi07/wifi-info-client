package za.co.wifi.info.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.AgeFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil extends FileUtils {
    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);
    private static final DecimalFormat SIZE_FORMAT = new DecimalFormat();

    private FileUtil() {
    }

    public static File getFile(String... paths) {
        String p = "";

        for (int i = 0; i < paths.length; ++i) {
            if (i > 0) {
                p = p + File.separator;
            }

            p = p + paths[i];
        }

        return new File(p);
    }

    public static boolean isLocked(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist");
        } else {
            FileLock lock = null;
            FileOutputStream fis = null;

            boolean var4;
            try {
                fis = new FileOutputStream(file, true);
                lock = fis.getChannel().tryLock();
                boolean var3 = lock == null;
                return var3;
            } catch (Throwable var8) {
                log.trace("File is locked or inaccessible: " + file, var8);
                var4 = true;
            } finally {
                releaseQuietly(lock);
                IOUtils.closeQuietly(fis);
            }

            return var4;
        }
    }

    public static void cleanDirectory(File dir) {
        if (dir.exists()) {
            FileUtils.deleteQuietly(dir);
        }
    }

    public static FileLock lock(File file) throws IOException {
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist");
        } else {
            FileChannel fc = (new RandomAccessFile(file, "rw")).getChannel();
            return fc.tryLock();
        }
    }

    public static boolean canRead(File file) {
        FileInputStream fis = null;

        boolean var3;
        try {
            fis = new FileInputStream(file);
            boolean var2 = true;
            return var2;
        } catch (Throwable var7) {
            var3 = false;
        } finally {
            IOUtils.closeQuietly(fis);
        }

        return var3;
    }

    public static void releaseQuietly(FileLock lock) {
        try {
            if (lock != null) {
                lock.release();
            }
        } catch (IOException var2) {
        }
    }

    public static void append(String str, File file) {
        FileWriter out = null;

        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                FileUtils.touch(file);
            }

            out = new FileWriter(file, true);
            out.append(str).append("\n");
        } catch (IOException var7) {
            throw new RuntimeException(var7);
        } finally {
            IOUtils.closeQuietly(out);
        }

    }

    public static File toFile(byte[] contents, String ext) {
        return toFile((InputStream) (new ByteArrayInputStream(contents)), (String) ext);
    }

    public static File toFile(byte[] contents) {
        return toFile(contents, "tmp");
    }

    public static File toFile(String contents, String ext) {
        return toFile((InputStream) (new ByteArrayInputStream(contents.getBytes())), (String) ext);
    }

    public static File toFile(InputStream in) {
        return toFile(in, "tmp");
    }

    public static File toFile(String filename, InputStream in) {
        FileOutputStream out = null;

        File var4;
        try {
            File temp = new File(filename);
            touch(temp);
            out = new FileOutputStream(temp);
            IOUtils.copy(in, out);
            var4 = temp;
        } catch (Exception var8) {
            throw new RuntimeException(var8);
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }

        return var4;
    }

    public static File toFile(InputStream in, String ext) {
        FileOutputStream out = null;

        File var4;
        try {
            File temp = tempFile(ext);
            touch(temp);
            out = new FileOutputStream(temp);
            IOUtils.copy(in, out);
            var4 = temp;
        } catch (Exception var8) {
            throw new RuntimeException(var8);
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }

        return var4;
    }

    public static File tempFile(String ext) {
        try {
            File file;
            do {
                String fileName = new SimpleDateFormat("dd-M-yyyy_hh:mm:ss").format(new Date());
                file = new File(FileUtil.getTempDirectory(), fileName + "." + ext.trim());
            } while (file.exists());

            FileUtils.touch(file);
            return file;
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static File tempFilename(String filename) {
        try {
            String name = FilenameUtils.getBaseName(filename).trim();
            String ext = FilenameUtils.getExtension(filename).trim();

            File file = new File(FileUtil.getTempDirectory(), name + "." + ext);
            if (file.exists()) {
                file.delete();
            }

            FileUtils.touch(file);

            return file;
        } catch (IOException var5) {
            throw new RuntimeException(var5);
        }
    }

    public static String head(File file, int lines) {
        long fileLength = file.length();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            long currentLine = 0L;
            String head = "";

            while (true) {
                String line;
                if ((line = reader.readLine()) != null) {
                    ++currentLine;
                    head = head + line;
                    if (currentLine <= (long) lines) {
                        continue;
                    }
                }

                String var9 = head;
                return var9;
            }
        } catch (IOException var13) {
            throw new RuntimeException(var13);
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    public static String tail(File file, int length) {
        long fileLength = file.length();
        BufferedReader reader = null;

        String var9;
        try {
            reader = new BufferedReader(new FileReader(file));
            long currentLength = 0L;
            String tail = "";

            String line;
            while ((line = reader.readLine()) != null) {
                currentLength += (long) line.length();
                if (currentLength > fileLength - (long) length) {
                    tail = tail + line + "\r";
                }
            }

            var9 = tail;
        } catch (IOException var13) {
            throw new RuntimeException(var13);
        } finally {
            IOUtils.closeQuietly(reader);
        }

        return var9;
    }

    public static void copy(InputStream is, File file) {
        OutputStream out = null;

        try {
            out = writeStream(file);
            IOUtils.copy(is, out);
        } catch (IOException var7) {
            throw new RuntimeException(var7);
        } finally {
            IOUtils.closeQuietly(out);
        }

    }

    public static Collection<File> find(File root, String name) {
        return listFiles(root, new NameFileFilter(name), TrueFileFilter.INSTANCE);
    }

    public static File[] find(File dir, long cutoff) {
        FileFilter filter = new AgeFileFilter(cutoff);
        return dir.listFiles(filter);
    }

    public static void deleteAll(File[] files) {
        File[] var1 = files;
        int var2 = files.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            File file = var1[var3];
            file.delete();
            if (file.exists()) {
                file.deleteOnExit();
            }
        }

    }

    public static boolean exists(File file) {
        return file.exists();
    }

    public static void delete(File file) {
        file.delete();
    }

    public static String changeExt(String filename, String ext) {
        return FilenameUtils.removeExtension(filename) + "." + ext;
    }

    public static File search(File dir, String regex, String dirRegex) {
        FileUtil.SearchingDirectoryWalker searcher = new FileUtil.SearchingDirectoryWalker();
        List<File> results = searcher.search(dir, regex, dirRegex);
        return results.isEmpty() ? null : (File) results.get(0);
    }

    public static List<File> searchAll(File dir, String regex, String dirRegex) {
        FileUtil.SearchingDirectoryWalker searcher = new FileUtil.SearchingDirectoryWalker();
        List<File> results = searcher.search(dir, regex, dirRegex);
        return results.isEmpty() ? Collections.EMPTY_LIST : results;
    }

    public static InputStream open(File file) {
        try {
            return new FileInputStream(file);
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    public static long length(File file) {
        try {
            return file.length();
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    public static OutputStream out(File file) {
        try {
            return new FileOutputStream(file);
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    public static void copyFile(File src, File dest) {
        InputStream in = null;
        OutputStream out = null;

        try {
            in = open(src);
            out = out(dest);
            IOUtils.copy(in, out);
        } catch (IOException var8) {
            throw new RuntimeException(var8);
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }

    }

    public static List<File> listFiles(File file, String regex) {
        List<File> list = listFiles(file);
        Iterator iter = list.iterator();

        while (iter.hasNext()) {
            if (!FilenameUtils.wildcardMatch(((File) iter.next()).getName(), regex, IOCase.INSENSITIVE)) {
                iter.remove();
            }
        }

        return list;
    }

    public static List<File> listFiles(File file) {
        ArrayList files = new ArrayList();

        try {

            File[] array = file.listFiles();
            if (array != null) {
                files.addAll(Arrays.asList(array));
            }

            return files;
        } catch (Exception var7) {
            throw new RuntimeException(var7);
        }
    }

    public static List<String> readLines(File file) {
        InputStream in = null;

        List var2;
        try {
            in = open(file);
            var2 = IOUtils.readLines(in, "UTF-8");
        } catch (IOException var6) {
            throw new RuntimeException(var6);
        } finally {
            IOUtils.closeQuietly(in);
        }

        return var2;
    }

    public static OutputStream writeStream(File file) throws IOException {
        return new FileOutputStream(file);
    }

    public static long sizeOfDirectory(File file) {
        if (!file.exists()) {
            return 0L;
        } else {
            long size = 0L;

            File f;
            for (Iterator var3 = (new FileUtil.SearchingDirectoryWalker()).search(file, ".*", ".*").iterator(); var3.hasNext(); size += f.length()) {
                f = (File) var3.next();
            }

            return size;
        }
    }

    static {
        SIZE_FORMAT.setMaximumFractionDigits(1);
    }

    private static class SearchingDirectoryWalker extends DirectoryWalker {
        private Pattern matcher;
        private Pattern dirMatcher;
        private File dir;

        private SearchingDirectoryWalker() {
        }

        public List<File> search(File dir, String regex, String dirRegex) {
            this.matcher = Pattern.compile(regex);
            this.dirMatcher = Pattern.compile(dirRegex);
            this.dir = dir;
            ArrayList list = new ArrayList();

            try {
                this.walk(dir, list);
                return list;
            } catch (IOException var6) {
                throw new RuntimeException(var6);
            }
        }

        protected void handleFile(File file, int i, Collection collection) throws IOException {
            if (this.matcher.matcher(file.getName()).matches()) {
                collection.add(file);
            }

        }

        protected boolean handleDirectory(File file, int i, Collection collection) throws IOException {
            if (this.matcher.matcher(file.getAbsolutePath().toLowerCase()).matches()) {
                collection.add(file);
            }

            return file.equals(this.dir) || this.dirMatcher.matcher(file.getAbsolutePath().toLowerCase()).matches();
        }
    }
}
