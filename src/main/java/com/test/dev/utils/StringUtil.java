package com.test.dev.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

public class StringUtil {

    private static StringUtil instance = null;

    public StringUtil() {
    }

    public static final String escapeForIntro(String string) {
        // String str = escapeHTMLTags(string);
        String str = string;
        str = replace(str, "\r\n", "<br>");
        str = replace(str, "\n", "<br>");
        str = replace(str, "'", "\\'");
        return replace(str, "\r", "");

    }

    /**
     * 得到非空的字符串，若字符串对象为null，则返回""。
     *
     * @param objValue Object待转换的原字符串对象
     * @return String 转换后的字符串
     */
    public static String getNotNullStr(Object objValue) {
        return (objValue == null ? "" : objValue.toString());
    }

    /**
     * 得到非空的字符串，若字符串为null，则返回""。
     *
     * @param strValue String待转换的原字符串
     * @return String 转换后的字符串
     */
    public static String getNotNullStr(String strValue) {
        return StringUtils.defaultString(strValue, "").trim();
    }

    /**
     * 用"0"补足一个字符串到指定长度
     *
     * @param str  - 源字符串
     * @param size - 补足后应达到的长度
     * @return - 补零后的结果
     */
    public static String fillZero(String str, int size) {
        return StringUtils.leftPad(str, size, '0');
    }

    /**
     * 根据字符串（文件类型或者多行输入类型）获取字符串数组
     *
     * @param str1 String 输入字符串
     * @return String[] 返回结果
     */
    public static String[] getStrArryByString(String str1) {
        if (str1.indexOf("\t") > 0) {
            for (int i = 0; i < str1.length(); i++) {
                if (str1.substring(i, i + 1).equals("\t")) {
                    str1 = str1.substring(0, i) + " "
                            + str1.substring(i + 1);
                }
            }
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str1, "\r\n");
        String[] strId = new String[stringTokenizer.countTokens()];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            strId[i] = stringTokenizer.nextToken();
            i++;
        }
        return strId;
    }

    /**
     * 判断一个字符串是否为 NUll 或为空
     *
     * @param inStr inStr
     * @return boolean
     */
    public static boolean isValid(String inStr) {
        return StringUtils.isNotBlank(inStr) && !inStr.equals("null");
    }

    /**
     * 判断一个字符串是否为 NUll 或为空
     *
     * @param str
     * @return boolean
     */
    public static boolean checkNotNull(String str) {
        return StringUtils.isNotBlank(str);
    }

    public static boolean checkNotNull(String... str) {
        for (String t : str) {
            if (!StringUtil.checkNotNull(t)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获得指定长度的空格
     *
     * @param spaceNum spaceNum
     * @return String
     */
    public static String getStrSpace(int spaceNum) {
        return getStrWithSameElement(spaceNum, " ");
    }

    /**
     * 获得指定长度的字符串
     *
     * @param num     int
     * @param element String
     * @return String
     */
    public static String getStrWithSameElement(int num, String element) {
        if (num <= 0) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            sb.append(element);
        }
        return sb.toString();
    }

    /**
     * 从右或左加空格
     *
     * @param strIn        String
     * @param totalLength  int
     * @param isRightAlign boolean
     * @return String
     */
    public static String getFillString(String strIn, int totalLength,
                                       boolean isRightAlign) {
        int spaceLength = 0;
        String spaces = null;
        String strOut = null;

        if (strIn == null) {
            strIn = "";
        }

        spaceLength = totalLength - strIn.length();

        if (spaceLength < 0) {
            spaceLength = 0;
        }
        spaces = StringUtil.getStrSpace(spaceLength);

        if (isRightAlign) {
            strOut = spaces + strIn;
        } else {
            strOut = strIn + spaces;

        }
        return strOut;
    }

    /**
     * 以String类型返回错误抛出的堆栈信息
     *
     * @param t Throwable
     * @return String
     */
    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        t.printStackTrace(pw);
        return sw.toString();
    }

    /**
     * 转换字符串第一个字符为大写
     *
     * @param str String
     * @return String
     */
    public static String getStrByUpperFirstChar(String str) {
        try {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 转换字符串第一个字符为小写
     *
     * @param str String
     * @return String
     */
    public static String getStrByLowerFirstChar(String str) {
        try {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 通过字符串转换成相应的整型，并返回。
     *
     * @param strValue String 待转换的字符串
     * @return int 转换完成的整型
     */
    public static int getStrToInt(String strValue) {
        if (null == strValue) {
            return 0;
        }
        int iValue = 0;
        try {
            iValue = Integer.parseInt(strValue.trim());
        } catch (Exception ex) {}
        return iValue;
    }

    /**
     * 通过字符串转换成相应的DOUBLE，并返回。
     *
     * @param strValue String 待转换的字符串
     * @return double 转换完成的DOUBLE
     */
    public static double getStrToDouble(String strValue) {
        if (null == strValue) {
            return 0;
        }
        double dValue = 0;
        try {
            dValue = Double.parseDouble(strValue.trim());
        } catch (Exception ex) {
            dValue = 0;
        }
        return dValue;
    }

    /**
     * 通过字符串转换成相应的短整型，并返回。
     *
     * @param strValue String 待转换的字符串
     * @return short 转换完成的短整型
     */
    public static short getStrToShort(String strValue) {
        if (null == strValue) {
            return 0;
        }
        short iValue = 0;
        try {
            iValue = new Short(strValue.trim()).shortValue();
        } catch (Exception ex) {
            iValue = 0;
        }
        return iValue;
    }

    /**
     * 通过字符串转换成相应的长整型，并返回。
     *
     * @param strValue String 待转换的字符串
     * @return long 转换完成的长整型
     */
    public static long getStrToLong(String strValue) {
        if (null == strValue) {
            return 0;
        }
        long lValue = 0;
        try {
            lValue = new Long(strValue.trim()).longValue();
        } catch (Exception ex) {
            lValue = 0;
        }
        return lValue;
    }

    public static String toLengthForEn(String str, int length) {
        if (null != str) {
            if (str.length() <= length) {
                return str;
            } else {
                str = str.substring(0, length - 2);
                str = str + "..";
                return str;
            }
        } else {
            return "";
        }
    }

    /**
     * 降字符串转换成给定长度的字符串，如超出的话截断，并在最后以两个点结尾
     *
     * @param str    String
     * @param length int
     * @return String
     */
    public static String toLengthForIntroduce(String str, int length) {
        str = delTag(str);

        byte[] strByte = str.getBytes();
        int byteLength = strByte.length;
        char[] charArray;
        StringBuffer buff = new StringBuffer();
        if (byteLength > (length * 2)) {
            charArray = str.toCharArray();
            int resultLength = 0;
            for (int i = 0; i < charArray.length; i++) {
                resultLength += String.valueOf(charArray[i]).getBytes().length;
                if (resultLength > (length * 2)) {
                    break;
                }
                buff.append(charArray[i]);

            }
            buff.append("..");
            str = buff.toString();
        }

        // str = replace(str, "'", "\\'");
        str = replace(str, "\"", "\\\"");
        str = replace(str, "，", ",");
        return str;

    }

    public static String delTag(String str) {
        str = str + "<>";
        StringBuffer buff = new StringBuffer();
        int start = 0;
        int end = 0;

        while (str.length() > 0) {
            start = str.indexOf("<");
            end = str.indexOf(">");
            if (start > 0) {
                buff.append(str, 0, start);
            }
            if (end > 0 && end <= str.length()) {
                str = str.substring(end + 1);
            } else {
                str = "";
            }

        }
        String result = buff.toString();

        while (result.startsWith(" ")) {

            result = result.substring(result.indexOf(" ") + 1);

        }
        return result;

    }

    public static final String replace(String line, String oldString,
                                       String newString) {
        if (line == null) {
            return null;
        }
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;

    }

    // Replace
    public static String Replace(String source, String oldString,
                                 String newString) {
        if (source == null) {
            return null;
        }
        StringBuffer output = new StringBuffer();
        int lengOfsource = source.length();
        int lengOfold = oldString.length();
        int posStart = 0;
        int pos;
        while ((pos = source.indexOf(oldString, posStart)) >= 0) {
            output.append(source, posStart, pos);
            output.append(newString);
            posStart = pos + lengOfold;
        }
        if (posStart < lengOfsource) {
            output.append(source.substring(posStart));
        }
        return output.toString();
    }

    // 此函数前台使用中，请勿随便修改，不然会造成显示混乱(以前修改版本在下方注释中)
    public static String toHtml(String s) {
        s = Replace(s, "<", "&lt;");
        s = Replace(s, ">", "&gt;");
        s = Replace(s, "\t", "    ");
        s = Replace(s, "\r\n", "\n");
        s = Replace(s, "\n", "<br>");
        // s = Replace(s, " ", "&nbsp;");
        s = Replace(s, "'", "&#39;");
        s = Replace(s, "\"", "&quot;");
        s = Replace(s, "\\", "&#92;");
        s = Replace(s, "%", "％");
        // s = Replace(s, "&", "&amp;");
        return s;
    }

    // 逆
    public static String unHtml(String s) {

        // s = Replace(s, "&lt;", "<");
        // s = Replace(s, "&gt;", ">");
        // s = Replace(s, "    ", "\t");
        // s = Replace(s, "\n", "\r\n");
        s = Replace(s, "<br>", "\n");
        // s = Replace(s, "&nbsp;", " ");
        // s = Replace(s, "&amp;", "&");
        // s = Replace(s, "&#39;", "'");
        // s = Replace(s, "&#92;", "\\");
        // s = Replace(s, "％", "%");
        return s;
    }

    // 此函数后台使用中，请勿随便修改，不然会造成显示混乱(以前修改版本在下方注释中)
    public static String toHtmlBack(String s) {
        // 显示
        s = Replace(s, "&", "&amp;");
        s = Replace(s, "\\", "&#92;");
        s = Replace(s, "\"", "&quot;");
        s = Replace(s, "<", "&lt;");
        s = Replace(s, ">", "&gt;");
        s = Replace(s, "\t", "    ");
        s = Replace(s, "\r\n", "\n");
        // s = Replace(s, "\n", "<br>");
        // s = Replace(s, " ", "&nbsp;");
        // s = Replace(s, "'", "&#39;");
        // s = Replace(s, "%", "%");

        return s;
    }

    // 逆
    public static String unHtmlBack(String s) {
        s = Replace(s, "&lt;", "<");
        s = Replace(s, "&gt;", ">");
        s = Replace(s, "    ", "\t");
        s = Replace(s, "\n", "\r\n");
        s = Replace(s, "<br>", "\n");
        s = Replace(s, "&nbsp;", " ");
        s = Replace(s, "&amp;", "&");
        s = Replace(s, "&#39;", "'");
        s = Replace(s, "&#92;", "\\");
        s = Replace(s, "％", "%");
        return s;
    }

    /*
     * public static String toHtml(String s) { //显示 s = Replace(s, "&",
     * "&amp;"); s = Replace(s, "\\", "&#92;"); s = Replace(s, "\"", "&quot;");
     * s = Replace(s, "<", "&lt;"); s = Replace(s, ">", "&gt;"); s = Replace(s,
     * "\t", "    "); s = Replace(s, "\r\n", "\n"); // s = Replace(s, "\n",
     * "<br>"); s = Replace(s, " ", "&nbsp;"); // s = Replace(s, "'", "&#39;");
     * // s = Replace(s, "%", "%"); return s; } public static String
     * unHtml(String s) { s = Replace(s, "&lt;", "<"); s = Replace(s, "&gt;",
     * ">"); s = Replace(s, "    ", "\t"); s = Replace(s, "\n", "\r\n"); s =
     * Replace(s, "<br>", "\n"); s = Replace(s, "&nbsp;", " "); s = Replace(s,
     * "&amp;", "&"); s = Replace(s, "&#39;", "'"); s = Replace(s, "&#92;",
     * "\\"); s = Replace(s, "％", "%"); return s; }
     */
    // 判断是否含有中文，如果含有中文返回ture
    public static boolean containsChinese(String str)
            throws UnsupportedEncodingException {

        return str.length() < (str.getBytes()).length;

        // for (int i = 0; i < username.length(); i++) {
        // String unit=Character.toString(username.charAt(i));
        // byte[] unitByte=unit.getBytes("GBK");
        // // ((unitByte[0]+256)*256 + (unitByte[1]+256)) <= 0xFEFE)
        // if (unitByte.length == 2)
        // {
        // return true;
        // }
        // }
        // return false;

    }

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        return "".equals(str.trim());
    }

    public static String[] split(String str1, String str2) {
        return StringUtils.split(str1, str2);
    }

    /**
     * <br>
     * <b>功能：</b>将字符串转成list<br>
     * <b>作者：</b>罗泽军<br>
     * <b>日期：</b> Oct 28, 2011 <br>
     *
     * @param exp   分割符 如","
     * @param value
     * @return
     */
    public static List<String> StringToList(String value, String exp) {
        List<String> resultList = new ArrayList<>();
        String[] vals = split(value, exp);
        for (String val : vals) {
            resultList.add(val);
        }
        return resultList;
    }

    /**
     * <br>
     * <b>功能：</b>数字转换成字符串<br>
     * <b>作者：</b>罗泽军<br>
     * <b>日期：</b> Jul 30, 2011 <br>
     *
     * @param arrs
     * @return
     */
    public static String arrayToString(String[] arrs) {
        StringBuffer result = new StringBuffer();
        if (arrs != null && arrs.length > 0) {
            for (int i = 0; i < arrs.length; i++) {

                if (!result.toString().equals("")) {
                    result.append(",");
                }
                if (arrs[i] != null && !"".equals(arrs[i].trim())) {
                    result.append(arrs[i]);
                }
            }
        }
        return result.toString();
    }

    /**
     * <br>
     * <b>功能：</b>数字转换成字符串<br>
     * <b>作者：</b>罗泽军<br>
     * <b>日期：</b> Jul 30, 2011 <br>
     *
     * @param arrs
     * @return
     */
    public static String arrayToString(Object[] arrs) {
        StringBuffer result = new StringBuffer();
        if (arrs != null && arrs.length > 0) {
            for (int i = 0; i < arrs.length; i++) {

                if (!result.toString().equals("")) {
                    result.append(",");
                }
                if (arrs[i] != null && !"".equals(arrs[i].toString().trim())) {
                    result.append(arrs[i]);
                }
            }
        }
        return result.toString();
    }

    public static String left(String str, int length) {
        return StringUtils.left(str, length);
    }

    /**
     * <br>
     * <b>功能：</b>替换回车<br>
     * <b>作者：</b>罗泽军<br>
     * <b>日期：</b> Oct 26, 2011 <br>
     *
     * @param str
     * @return
     */
    public static String replaceHuiche(String str) {
        String after = str.replaceAll("\r\n", "");
        return after;
    }

    /**
     * 根据输入的长度截取字符串，单个单词超过输入长度的强制加<BR>
     * 换行
     *
     * @param str 输入的字符串
     * @param len 输入的长度
     * @return 处理过后的字符串
     */
    public static String truncateStr(String str, int len) {
        if (str != null && !("").equalsIgnoreCase(str)) {

            String[] strs = str.split(" ");
            StringBuffer buff = new StringBuffer();
            if (strs.length > 0) {
                for (int i = 0; i < strs.length; i++) {
                    StringBuffer temp = new StringBuffer();
                    while (strs[i].length() > len) {
                        temp.append(strs[i].substring(0, len) + "<BR>");
                        strs[i] = strs[i].substring(len);
                    }
                    temp.append(strs[i]);
                    buff.append(temp.toString() + " ");
                }

            }
            return buff.toString();
        } else {
            return "";
        }
    }

    public static String truncateStr2(String str, int len) {
        if (str != null && !("").equalsIgnoreCase(str) && len != 0) {
            String[] strs = str.split(" ");

            StringBuffer buff = new StringBuffer();
            for (int i = 0; i < strs.length; i++) {
                StringBuffer temp = new StringBuffer();
                String tempstr = "";
                while (strs[i].length() > len) {
                    tempstr = strs[i].substring(0, len);
                    tempstr = tempstr.replaceAll(" ", "&nbsp; ");
                    tempstr = tempstr.replaceAll("<", "&lt; ");
                    tempstr = tempstr.replaceAll("\n", "<br> ")
                            .replaceAll("\"", "&quot; ")
                            .replaceAll("'", "&#39; ");
                    tempstr = tempstr + "<br>";
                    temp.append(tempstr);

                    strs[i] = strs[i].substring(len);
                }
                tempstr = strs[i];
                tempstr = tempstr.replaceAll(" ", "&nbsp; ");
                tempstr = tempstr.replaceAll("<", "&lt; ");
                tempstr = tempstr.replaceAll("\n", "<br> ")
                        .replaceAll("\"", "&quot; ").replaceAll("'", "&#39; ");

                temp.append(tempstr);
                buff.append(temp.toString() + " ");
            }

            if (buff.length() > 0) {
                return buff.toString().substring(0, buff.length() - 1);
            } else {
                return str;
            }
        } else {
            return "";
        }
    }

    /**
     * 编码转换，从unicode转换为GBK
     *
     * @param
     * @return str编码转换后的字符串
     * @throws UnsupportedEncodingException
     */
    public static String unicodeToGB(String l_S_Source)
            throws UnsupportedEncodingException {
        String l_S_Desc = "";
        if (l_S_Source != null && !l_S_Source.trim().equals("")) {
            byte[] l_b_Proc = l_S_Source.getBytes("GBK");
            l_S_Desc = new String(l_b_Proc, "ISO8859_1");
        }
        return l_S_Desc;
    }

    /**
     * 编码转换，从GBK转换为unicode
     *
     * @param
     * @return str 编码转换后的字符串
     * @throws UnsupportedEncodingException
     */
    public static String GBToUnicode(String l_S_Source)
            throws UnsupportedEncodingException {
        String l_S_Desc = "";
        if (l_S_Source != null && !l_S_Source.trim().equals("")) {
            byte[] l_b_Proc = l_S_Source.getBytes("ISO8859_1");
            l_S_Desc = new String(l_b_Proc, "GBK");
        }
        return l_S_Desc;
    }

    /**
     * Escapes a <code>String</code> according the JavaScript string literal
     * escaping rules. The resulting string will not be quoted.
     * <p>
     * It escapes both <tt>'</tt> and <tt>"</tt>. In additional it escapes
     * <tt>></tt> as <tt>\></tt> (to avoid <tt>&lt;/script></tt>). Furthermore,
     * all characters under UCS code point 0x20, that has no dedicated escape
     * sequence in JavaScript language, will be replaced with hexadecimal escape
     * (<tt>\x<i>XX</i></tt>).
     */
    public static String javaScriptStringEnc(String s) {
        int ln = s.length();
        for (int i = 0; i < ln; i++) {
            char c = s.charAt(i);
            if (c == '"' || c == '\'' || c == '\\' || c == '>' || c < 0x20) {
                StringBuffer b = new StringBuffer(ln + 4);
                b.append(s, 0, i);
                while (true) {
                    if (c == '"') {
                        b.append("\\\"");
                    } else if (c == '\'') {
                        b.append("\\'");
                    } else if (c == '\\') {
                        b.append("\\\\");
                    } else if (c == '>') {
                        b.append("\\>");
                    } else if (c < 0x20) {
                        if (c == '\n') {
                            b.append("\\n");
                        } else if (c == '\r') {
                            b.append("\\r");
                        } else if (c == '\f') {
                            b.append("\\f");
                        } else if (c == '\b') {
                            b.append("\\b");
                        } else if (c == '\t') {
                            b.append("\\t");
                        } else {
                            b.append("\\x");
                            int x = c / 0x10;
                            b.append((char) (x < 0xA ? x + '0' : x - 0xA + 'A'));
                            x = c & 0xF;
                            b.append((char) (x < 0xA ? x + '0' : x - 0xA + 'A'));
                        }
                    } else {
                        b.append(c);
                    }
                    i++;
                    if (i >= ln) {
                        return b.toString();
                    }
                    c = s.charAt(i);
                }
            } // if has to be escaped
        } // for each characters
        return s;
    }

    public static synchronized StringUtil getInstance() {
        if (instance == null) {
            instance = new StringUtil();
        }
        return instance;
    }

    /**
     * 将多个连续空格替换为一个,"a  b"-->"a b"
     *
     * @param src
     * @return
     * @desc
     */
    public static String trimContinuousSpace(String src) {
        return src.replaceAll("\\s+", " ");
    }

    public static String replace(String src, String target, String rWith,
                                 int maxCount) {
        return StringUtils.replace(src, target, rWith, maxCount);
    }

    public static boolean equals(String str1, String str2) {
        return StringUtils.equals(str1, str2);
    }

    public static boolean isAlphanumeric(String str) {
        return StringUtils.isAlphanumeric(str);
    }

    public static boolean isNumeric(String str) {
        return StringUtils.isNumeric(str);
    }

    public static String[] stripAll(String[] strs) {
        return StringUtils.stripAll(strs);
    }

    public static String toFloatNumber(String num) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        return nf.format(Double.parseDouble(num));
    }

    public static String toFloatNumber(Double num, int accuracy) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(accuracy);
        nf.setMinimumFractionDigits(accuracy);
        return nf.format(num);
    }

    public static String wcsUnescape(String str) {
        str = str.replace("#lt;", "<");
        str = str.replace("#gt;", ">");
        str = str.replace("#quot;", "\"");
        str = str.replace("#amp;amp;", "&");
        str = str.replace("#amp;", "&");
        str = str.replace("#039;", "'");
        return str;
    }

    /**
     * <br>
     * <b>功能：</b>返回string型的字节数<br>
     * <b>作者：</b>罗泽军<br>
     * <b>日期：</b> Sep 2, 2011 <br>
     *
     * @param str
     * @return
     */
    public static int getByteLength(String str) {
        if (str == null) {
            return 0;
        }
        return str.getBytes().length;
    }

    /**
     * <br>
     * <b>功能：</b>详细的功能描述<br>
     * <b>作者：</b>罗泽军<br>
     * <b>日期：</b> Sep 2, 2011 <br>
     *
     * @param str      字符
     * @param limitLen 长度
     * @return
     */
    public static String getByteStr(String str, int limitLen) {
        StringBuffer sb = new StringBuffer();
        char[] chars = getNotNullStr(str).toCharArray();
        int len = 0;
        for (char c : chars) {
            len += getByteLength(String.valueOf(c));
            if (len <= limitLen) {
                sb.append(c);
            }
        }
        return sb.toString();

    }

    /**
     * @param content 内容
     * @param length  指定长度。 超出这个长度就截取字符串。
     * @param padding 超出长度后，尾加上字符，如"..."，可以为空
     * @return 返回结果 如果内容没有超出指定的长度。则返回原字符串，超出长度后则截取到指定的长度的内容
     */
    public static String subStr(String content, Integer length, String padding)
            throws UnsupportedEncodingException {
        String str = "";
        int paddSize = StringUtils.isBlank(padding) ? 0 : padding.length();
        // 如果内容为空，或者小于指定的长度。则返回原内容
        if (StringUtils.isBlank(content) || content.length() <= length) {
            return content;
        }
        str = content.substring(0, length - paddSize);
        if (StringUtils.isNotBlank(padding)) {
            str += padding;
        }
        return str;
    }

    /**
     * 格式化手机号码
     * 显示为 132*****123
     *
     * @param tel 传入原手机号
     * @return
     */
    public static String formatTele(String tel) {
        String showTel = "";
        if (tel != null && !"".equals(tel)) {
            showTel = tel.substring(0, 3) + "*****"
                    + tel.substring(tel.length() - 3);
        }
        return showTel;
    }

    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    /**
     * 对某些字符串属性进行替换，例如姓名为王**,手机号为132****0000,身份证号201****015
     *
     * @param source  资源字符串
     * @param start   截取字符串前半部分的起始位置
     * @param end     截取字符串前半部分的结束位置
     * @param sysmbol 要替换的字符
     * @param flag    是否截取字符串的后部分，true截取，false不截取
     * @return
     */
    public static String formatProperTy(String source, int start, int end, String sysmbol, boolean flag) {
        StringBuffer buffer = new StringBuffer();

        if (!isEmpty(source)) {

            String preString = source.substring(start, end);
            if (flag) {

                String lastString = source.substring(source.length() - end);
                buffer.append(preString).append(sysmbol).append(lastString);

            } else {

                buffer.append(preString).append(sysmbol);

            }
        }

        return buffer.toString();
    }

}
