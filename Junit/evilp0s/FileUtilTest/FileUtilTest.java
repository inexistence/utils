package evilp0s.FileUtilTest;

import evilp0s.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Filter;

import static org.junit.Assert.assertEquals;

public class FileUtilTest extends SupportTest {

    @Test
    public void testCountLines() throws IOException {

        String file = System.getProperty("user.dir") + "/Junit/Resource/English.txt";
        Assert.assertEquals("文件行数计算有误", 5, FileUtil.countLines(new File(file)));
    }

    @Test
    public void testHash() {
        String file = System.getProperty("user.dir") + "/Junit/Resource/ali.gif";
        assertEquals("文件Hash校验错误", "4FE6FF69F7257F2E8C36B0752B5393BF", FileUtil.hash(new File(file)).toUpperCase());
    }

    @Test
    public void testFileType() throws IOException {
        String efile = System.getProperty("user.dir") + "/Junit/Resource/ali.gif";
        String hfile = System.getProperty("user.dir") + "/Junit/Resource/tgepng";

        assertEquals("文件类型判断有错", "gif", FileUtil.fileType(new File(efile)));
        assertEquals("文件类型判断有错", "png", FileUtil.fileType(new File(hfile)));

        assertEquals("获取文件的mime类型错误", "image/gif", FileUtil.mimeType(efile));
    }

    @Test
    public void testModify() {
        String exefile = System.getProperty("user.dir") + "/Junit/Resource/cmdexe";
        Date   date    = FileUtil.modifyTime(new File(exefile));
        //assertEquals("获取文件修改的最后时间错误","2010-11-21 11:24:03",DateUtil.DateTime(date));
    }

    @Test
    public void testLines() {
        System.out.println("=====按List讲文件全部读入到List中======");

        PrintUtil.print("全英文文件测试");
        String       efile = System.getProperty("user.dir") + "/Junit/Resource/English.txt";
        List<String> lines = FileUtil.lines(new File(efile));
        PrintUtil.print(lines);
        PrintUtil.print("读取文件的前3行");
        lines = FileUtil.lines(new File(efile), 3);
        PrintUtil.print(lines);


        PrintUtil.print("GBK文件测试");
        String       gbkfile  = System.getProperty("user.dir") + "/Junit/Resource/GBK.txt";
        List<String> gbklines = FileUtil.lines(new File(gbkfile));
        PrintUtil.print(gbklines);
        gbklines = FileUtil.lines(new File(gbkfile), "GBK");
        PrintUtil.print(gbklines);
        PrintUtil.print("读取文件的前3行");
        lines = FileUtil.lines(new File(gbkfile), 3, "GBK");
        PrintUtil.print(lines);


        PrintUtil.print("UTF8文件测试");
        String       utf8file  = System.getProperty("user.dir") + "/Junit/Resource/UTF8.txt";
        List<String> utf8lines = FileUtil.lines(new File(utf8file));
        PrintUtil.print(utf8lines);
        utf8lines = FileUtil.lines(new File(utf8file), "UTF-8");
        PrintUtil.print(utf8lines);
        PrintUtil.print("读取文件的前3行");
        lines = FileUtil.lines(new File(utf8file), 3, "UTF-8");
        PrintUtil.print(lines);

    }


    @Test
    public void testCleanFile() {
        System.out.println("快速清空一个超大文件");
        String clearFile = System.getProperty("user.dir") + "/Junit/Resource/temp/GBKTOUTF8.txt";
        FileUtil.cleanFile(new File(clearFile));
    }

    @Test
    public void testCopy() throws Exception {
        System.out.println("测试文件拷贝");
        String copy = System.getProperty("user.dir") + "/Junit/Resource/GBKTOUTF8.txt";
        String dest = System.getProperty("user.dir") + "/Junit/Resource/temp/GBKTOUTF8.txt";
        FileUtil.copy(copy, dest);
    }

    @Test
    public void testAppendLine() {
        System.out.println("向文件中追加行");
        String line1 = "E-String";
        String line2 = "中文字符串";

        File efile    = new File(System.getProperty("user.dir") + "/Junit/Resource/EnglishWrite.txt");
        File gbkfile  = new File(System.getProperty("user.dir") + "/Junit/Resource/GBKWrite.txt");
        File utf8file = new File(System.getProperty("user.dir") + "/Junit/Resource/UTF8Write.txt");

        FileUtil.appendLine(efile, line1);
        FileUtil.appendLine(efile, line2);
        FileUtil.appendLine(efile, line1, "UTF-8");
        FileUtil.appendLine(efile, line2, "UTF-8");

        FileUtil.appendLine(gbkfile, line1);
        FileUtil.appendLine(gbkfile, line2);
        FileUtil.appendLine(gbkfile, line1, "GBK");
        FileUtil.appendLine(gbkfile, line2, "GBK");

        FileUtil.appendLine(utf8file, line1);
        FileUtil.appendLine(utf8file, line2);
        FileUtil.appendLine(utf8file, line1, "UTF-8");
        FileUtil.appendLine(utf8file, line2, "UTF-8");

    }


    @Test
    public void testDeleteFile() {
        System.out.println("删除文件");
        String delete = System.getProperty("user.dir") + "/Junit/Resource/temp/delete.txt";
        FileUtil.deleteFile(new File(delete));
    }

    @Test
    public void testDeleteBigFile() {
        System.out.println("快速删除超大文件");
        String delete = System.getProperty("user.dir") + "/Junit/Resource/temp/bigfile.txt";
        FileUtil.deleteBigFile(new File(delete));
    }

    @Test
    public void testCopyDir() {
        FileUtil.copyDir(new File(System.getProperty("user.dir") + "/conf/"), System.getProperty("user.dir") + "/Junit/Resource/temp/copy");
    }


    @Test
    public void testCreateFiles() {
        System.out.println("创建文件,支持多级目录");
        String path  = System.getProperty("user.dir") + "/Junit/Resource/temp/";
        String file1 = path + "/test/test1.txt";
        String file2 = path + "/test/test1/test.txt";
        String file3 = path + "/test/test/";
        FileUtil.createFiles(file1);
        FileUtil.createFiles(file2);
        FileUtil.createFiles(file3);
    }

    @Test
    public void testDeleteDir() {
        String path = System.getProperty("user.dir") + "/Junit/Resource/temp/";
        FileUtil.deleteDir(new File(path + "/test/"));
    }

    @Test
    public void testCreatePath() {
        System.out.println("创建文件夹,支持多级目录");
        String path  = System.getProperty("user.dir") + "/Junit/Resource/temp/";
        String path1 = path + "/test/test2/";
        String path2 = path + "/test/test3";
        FileUtil.createPaths(path1);
        FileUtil.createPaths(path2);
    }


    @Test
    public void testSimpleEncoding() {
        System.out.println("简单的利用文件头进行文件的编码探测!");
        String path = System.getProperty("user.dir") + "/Junit/Resource/";
        System.out.println(FileUtil.simpleEncoding(path + "GBK.txt"));
        System.out.println("利用cpdetector进行文件编码探测");
        System.out.println(FileUtil.cpdetector(new File(path + "GBK.txt")));
    }

    @Test
    public void testListFile() {
        System.out.println("罗列指定目录下的所有文件");
        String     path  = System.getProperty("user.dir") + "/Junit/Resource/temp/";
        List<File> files = FileUtil.listFile(new File(path));
        PrintUtil.print(files);
        List<File> files1 = FileUtil.listFile(path);
        PrintUtil.print(files1);
    }

    @Test
    public void testCopyDirs() {
        System.out.println("复制文件夹");
        String path = System.getProperty("user.dir") + "/Junit/Resource/temp";
        FileUtil.copyDir(path + "/test/", path + "/testcopy/");
    }

    @Test
    public void testListFileFilter() {
        System.out.println("罗列指定目录下的特定后缀的文件");
        String     path  = System.getProperty("user.dir") + "/Junit/Resource/";
        List<File> files = FileUtil.listFileFilter(new File(path), ".txt");
        PrintUtil.print(files);
    }

    @Test
    public void testSearchFile() {
        System.out.println("在指定的目录下搜索指定的文件");
        String     path  = System.getProperty("user.dir") + "/Junit/Resource/";
        List<File> files = FileUtil.searchFile(new File(path), "GBK.txt");
        PrintUtil.print(files);
    }

    @Test
    public void testSearchReg() {
        System.out.println("在指定的目录下搜索符合某正则的文件");
        //匹配字母和数字组成的exe文件
        String     reg   = "\\w{1,}\\.png$";
        String     path  = System.getProperty("user.dir") + "/Junit/Resource/";
        List<File> files = FileUtil.searchFileReg(new File(path), reg);
        PrintUtil.print(files);
    }

    @Test
    public void testWriteFile(){
        String path = System.getProperty("user.dir")+"/Junit/Resource/temp/";
        String etemp ="Two roads diverged in a yellow wood,\n" +
                "And sorry I could not travel both\n" +
                "And be one traveler, long I stood\n" +
                "And looked down one as far as I could\n" +
                "To where it bent in the undergrowth;\n" +
                "Then took the other, as just as fair,\n" +
                "And having perhaps the better claim,\n" +
                "Because it was grassy and wanted wear;\n" +
                "Though as for that the passing there\n" +
                "Had worn them really about the same,\n" +
                "And both that morning equally layn" +
                "In leaves no step had trodden black.\n" +
                "Oh, I kept the first for another day!\n" +
                "Yet knowing how way leads on to way,\n" +
                "I doubted if I should ever come back.\n" +
                "I shall be telling this with a sigh\n" +
                "Somewhere ages and ages hence:\n" +
                "Two roads diverged in a wood,and\n" +
                "I took the one less traveled by,\n" +
                "And that has made al lthe difference.";

        String ctemp ="黄色的树林里分出两条路\n" +
                "可惜我不能同时去涉足\n" +
                "我在那路口久久伫立\n" +
                "我向着一条路极目望去\n" +
                "直到它消失在丛林深处\n" +
                "但我却选择了另外一条路\n" +
                "它荒草萋萋，十分幽寂 　　\n" +
                "显得更诱人，更美丽　\n" +
                "虽然在这两条小路上\n" +
                "都很少留下旅人的足迹\n" +
                "虽然那天清晨落叶满地\n" +
                "两条路都未经脚印污染\n" +
                "呵，留下一条路等改日再见\n" +
                "但我知道路径延绵无尽头\n" +
                "恐怕我难以再回返\n" +
                "也许多少年后在某一个地方\n" +
                "我将轻声叹息把往事回顾\n" +
                " 一片森林里分出两条路\n" +
                "而我却选择了人迹更少的一条\n" +
                "从此决定了我一生的道路";
        FileUtil.write(new File(path+"efile1.TXT"),etemp);
        FileUtil.write(new File(path+"efile2.TXT"),etemp);
        FileUtil.writeAppend(new File(path + "efile2.TXT"), etemp);
        FileUtil.write(new File(path + "cfile1.TXT"), ctemp);
        FileUtil.write(new File(path+"cfile2.TXT"),ctemp);
        FileUtil.writeAppend(new File(path + "cfile2.TXT"), ctemp);

        FileUtil.write(new File(path + "efile1_UTF8.TXT"), etemp, CharsetUtil.UTF_8);
        FileUtil.write(new File(path + "efile2_UTF8.TXT"), etemp, CharsetUtil.UTF_8);
        FileUtil.writeAppend(new File(path + "efile2_UTF8.TXT"), etemp,CharsetUtil.UTF_8);
        FileUtil.write(new File(path + "cfile1_UTF8.TXT"), ctemp,CharsetUtil.UTF_8);
        FileUtil.write(new File(path+"cfile2_UTF8.TXT"),ctemp,CharsetUtil.UTF_8);
        FileUtil.writeAppend(new File(path + "cfile2_UTF8.TXT"), ctemp, CharsetUtil.UTF_8);
    }


}