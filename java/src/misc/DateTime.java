package misc;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateTime {
    public static void main(String[] args) {
//        OffsetDateTime offsetDateTime = OffsetDateTime.parse("98186");
//
//        System.out.println("offsetDateTime = " + offsetDateTime);

//        LocalDateTime startDate = LocalDateTime.parse("2018-08-06T00:00:00");
//        startDate.plus(1000, ChronoUnit.MILLIS);
//        System.out.println("startDate = " + startDate);

        System.out.println(toMillis(LocalDateTime.parse("2018-08-06T00:17:38.535")) -
                toMillis(LocalDateTime.parse("2018-08-06T00:10:38.520")));
    }

    public static long toMillis(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
    }

}
