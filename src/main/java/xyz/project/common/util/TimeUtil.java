package xyz.project.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xyz.project.common.exception.InternalException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TimeUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static LocalDate parse(String birthDate) {
        try {
            return LocalDate.parse(birthDate, formatter);
        } catch (Exception e) {
            throw new InternalException("Failed to parse" + birthDate, e);
        }
    }
}
