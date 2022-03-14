package persian;

public class Normalizer {
    public static String correctPersianString(String s) {
        String result = null;

        if (s != null) {
            result = s.trim().replaceAll("\\b\\s{2,}\\b", " ");
            result = result.replace("\u00A0", "");

            result = result.replace("\u064a", "\u06cc"); // yeh
            result = result.replace("\u0649", "\u06cc"); // yeh
            result = result.replace("\u0643", "\u06a9"); // keh
            result = result.replace("\u0651", ""); // tashdid
            result = result.replace("\u0652", ""); // sukon
            result = result.replace("\u064b", ""); // fathatan
            result = result.replace("\u064f", ""); // oh
            result = result.replace("\u064e", ""); // fatha
            result = result.replace("\u0650", ""); // kasra
            result = result.replace("\u0640", ""); // kashida
            result = result.replace("\u200c", " "); // half spaces
            result = result.replace("\u200e", " "); // half spaces
            result = result.replace("\u200f", " "); // half spaces
            result = result.replace("1", "۱"); // numbers
            result = result.replace("2", "۲"); // numbers
            result = result.replace("3", "۳"); // numbers
            result = result.replace("4", "۴"); // numbers
            result = result.replace("5", "۵"); // numbers
            result = result.replace("6", "۶"); // numbers
            result = result.replace("7", "۷"); // numbers
            result = result.replace("8", "۸"); // numbers
            result = result.replace("9", "۹"); // numbers
            result = result.replace("0", "۰"); // numbers
            result = result.replace(",", "،"); // punct
            result = result.replace("!", "!"); // punct
            result = result.replace("?", "؟"); // punct
            result = result.replace(";", "؛"); // punct
            result = result.replace("%", "٪"); // punct
            result = result.replace("٬", "\""); // punct
            result = result.replace("ـ", "_"); // punct
            result = result.replace("»", "\""); // punct
            result = result.replace("«", "\""); // punct
            result = result.replace("”", "\""); // punct
            result = result.replace("“", "\""); // punct
            result = result.replace("‘", "\""); // punct
            result = result.replace("’", "\""); // punct
            result = result.replace("|", ""); // punct

        }

        return result;
    }

    public static void main(String[] args) {
        String s = "حسن گفت : «دیشب, ساعت 9:12 هوایت را کردم; فهمیدی?»";

        System.out.println(Normalizer.correctPersianString(s));
    }
}