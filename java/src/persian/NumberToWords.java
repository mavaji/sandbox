package persian;

import java.text.DecimalFormat;

public class NumberToWords {

    private static final String[] tensNames = {
            "",
            " ده",
            " بیست",
            " سی",
            " چهل",
            " پنجاه",
            " شصت",
            " هفتاد",
            " هشتاد",
            " نود"
    };

    private static final String[] numNames = {
            "",
            " یک",
            " دو",
            " سه",
            " چهار",
            " پنج",
            " شش",
            " هفت",
            " هشت",
            " نه",
            " ده",
            " یازده",
            " دوازده",
            " سیزده",
            " چهارده",
            " پانزده",
            " شانزده",
            " هفده",
            " هجده",
            " نوزده"
    };

    private static final String[] hundredNames = {
            "",
            " صد",
            " دویست",
            " سیصد",
            " چهارصد",
            " پانصد",
            " ششصد",
            " هفتصد",
            " هشتصد",
            " نهصد"
    };

    private NumberToWords() {
    }

    private static String convertLessThanOneThousand(int number) {
        String soFar;

        if (number % 100 < 20) {
            soFar = numNames[number % 100];
            number /= 100;
        } else {
            soFar = numNames[number % 10];
            number /= 10;

            if (tensNames[number % 10].length() > 0 && soFar.length() > 0) {
                soFar = tensNames[number % 10] + " و " + soFar;
            } else {
                soFar = tensNames[number % 10] + soFar;
            }
            number /= 10;
        }
        if (number == 0) {
            return soFar;
        }

        String result = hundredNames[number];
        if (soFar.length() > 0) {
            result += " و " + soFar;
        }
        return result;
    }


    private static String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0) {
            return "صفر";
        }

        String snumber = Long.toString(number);

        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(snumber.substring(0, 3));
        // nnnXXXnnnnnn
        int millions = Integer.parseInt(snumber.substring(3, 6));
        // nnnnnnXXXnnn
        int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(snumber.substring(9, 12));

        String tradBillions;
        switch (billions) {
            case 0:
                tradBillions = "";
                break;
            case 1:
                tradBillions = convertLessThanOneThousand(billions)
                        + " میلیارد ";
                break;
            default:
                tradBillions = convertLessThanOneThousand(billions)
                        + " میلیارد ";
        }
        String result = tradBillions;

        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            case 1:
                tradMillions = convertLessThanOneThousand(millions)
                        + " میلیون ";
                break;
            default:
                tradMillions = convertLessThanOneThousand(millions)
                        + " میلیون ";
        }
        if (result.length() > 0 && tradMillions.length() > 0) {
            result = result + " و " + tradMillions;
        } else {
            result = result + tradMillions;
        }

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1:
                tradHundredThousands = "هزار ";
                break;
            default:
                tradHundredThousands = convertLessThanOneThousand(hundredThousands)
                        + " هزار ";
        }
        if (result.length() > 0 && tradHundredThousands.length() > 0) {
            result = result + " و " + tradHundredThousands;
        } else {
            result = result + tradHundredThousands;
        }

        String tradThousand;
        tradThousand = convertLessThanOneThousand(thousands);
        if (result.length() > 0 && tradThousand.length() > 0) {
            result = result + " و " + tradThousand;
        } else {
            result = result + tradThousand;

        }

        // remove extra spaces!
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ").trim();
    }

    public static String convert(String number) {
        String[] parts = number.split("\\.");

        long part0 = 0;
        try {
            part0 = Long.valueOf(parts[0]);
        } catch (Exception e) {

        }

        String result = convert(part0);

        if (parts.length > 1) {
            String s = "." + parts[1];
            s = s.indexOf(".") < 0 ? s : s.replaceAll("0*$", "").replaceAll("\\.$", "");//remove trail zeros

            s = s.substring(1);

            Long part1 = Long.valueOf(s);

            String convert1 = convert(part1);

            if (convert1.length() > 0 && !convert1.trim().equals("صفر")) {
                if (result.equals("صفر")) {
                    result = "";
                }
                if (s.length() == 1) {
                    if (result.length() > 0) {
                        result += " و " + convert1 + " دهم";
                    } else {
                        result += convert1 + " دهم";
                    }
                } else if (s.length() == 2) {
                    if (result.length() > 0) {
                        result += " و " + convert1 + " صدم";
                    } else {
                        result += convert1 + " صدم";
                    }
                } else if (s.length() == 3) {
                    if (result.length() > 0) {
                        result += " و " + convert1 + " هزارم";
                    } else {
                        result += convert1 + " هزارم";
                    }
                } else {
                    int leadingZeroCount = 0;
                    try {
                        leadingZeroCount = s.substring(0, s.indexOf(s.split("0+")[1])).length();
                    } catch (Exception e) {

                    }

                    result += " ممیز ";
                    if (leadingZeroCount > 0) {
                        if (leadingZeroCount == 1) {
                            result += " صفر ";
                        } else {
                            result += convert(leadingZeroCount) + " صفر ";
                        }
                    }
                    result += convert1;
                }
            }
        }


        return result;
    }

    /**
     * testing
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(NumberToWords.convert(0));
        System.out.println(NumberToWords.convert(1));
        System.out.println(NumberToWords.convert(16));
        System.out.println(NumberToWords.convert(100));
        System.out.println(NumberToWords.convert(118));
        System.out.println(NumberToWords.convert(200));
        System.out.println(NumberToWords.convert(219));
        System.out.println(NumberToWords.convert(800));
        System.out.println(NumberToWords.convert(801));
        System.out.println(NumberToWords.convert(1316));
        System.out.println(NumberToWords.convert(1000000));
        System.out.println(NumberToWords.convert(2000000));
        System.out.println(NumberToWords.convert(3000200));
        System.out.println(NumberToWords.convert(700000));
        System.out.println(NumberToWords.convert(9000000));
        System.out.println(NumberToWords.convert(9001000));
        System.out.println(NumberToWords.convert(123456789));
        System.out.println(NumberToWords.convert(2147483647));
        System.out.println(NumberToWords.convert(3000000010L));
        System.out.println("*****************************************************************************************");
        System.out.println(NumberToWords.convert("2147483647.0012000"));
        System.out.println(NumberToWords.convert("2147483647.1000"));
        System.out.println(NumberToWords.convert("12.1"));
        System.out.println(NumberToWords.convert("12.01"));
        System.out.println(NumberToWords.convert("12.001"));
        System.out.println(NumberToWords.convert("12.123"));
        System.out.println(NumberToWords.convert("12.0123"));
        System.out.println(NumberToWords.convert("0.0123"));
        System.out.println(NumberToWords.convert("0.123"));
        System.out.println(NumberToWords.convert(".0000123"));
    }
}