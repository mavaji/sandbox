package persian;

public class DateToWords {
    private static final String[] monthNames = {
            "فروردین",
            "اردیبهشت",
            "خرداد",
            "تیر",
            "مرداد",
            "شهریور",
            "مهر",
            "آبان",
            "آذر",
            "دی",
            "بهمن",
            "اسفند"
    };

    public static String convert(String date) {
        String[] parts = date.split("[/-]");
        String year = parts[0];
        String month = parts[1];
        String day = parts[2];

        String result = NumberToWords.convert(day);
        result += " " + monthNames[Integer.valueOf(month) - 1];
        result += " " + NumberToWords.convert(year);

        return result;
    }

    /**
     * testing
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(DateToWords.convert("1378/12/15"));
        System.out.println(DateToWords.convert("94-2-1"));
    }
}