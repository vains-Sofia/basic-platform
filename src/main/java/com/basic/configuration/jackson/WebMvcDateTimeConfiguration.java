package com.basic.configuration.jackson;

import com.basic.constant.DateFormatConstants;
import jakarta.annotation.Nonnull;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 添加 GET 参数的 LocalDate、LocalTime和LocalDateTime 转换的支持
 *
 * @author vains
 */
public class WebMvcDateTimeConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        // LocalDateTime
        registry.addFormatter(new Formatter<LocalDateTime>() {
            @Nonnull
            @Override
            public LocalDateTime parse(@Nonnull String text, @Nonnull Locale locale) {
                return LocalDateTime.parse(text,
                        DateTimeFormatter.ofPattern(DateFormatConstants.DEFAULT_DATE_TIME_FORMAT));
            }

            @Nonnull
            @Override
            public String print(@Nonnull LocalDateTime object, @Nonnull Locale locale) {
                return object.format(DateTimeFormatter.ofPattern(DateFormatConstants.DEFAULT_DATE_TIME_FORMAT));
            }
        });

        // LocalDate
        registry.addFormatter(new Formatter<LocalDate>() {
            @Nonnull
            @Override
            public LocalDate parse(@Nonnull String text, @Nonnull Locale locale) {
                return LocalDate.parse(text,
                        DateTimeFormatter.ofPattern(DateFormatConstants.DEFAULT_DATE_FORMAT));
            }

            @Nonnull
            @Override
            public String print(@Nonnull LocalDate object, @Nonnull Locale locale) {
                return object.format(DateTimeFormatter.ofPattern(DateFormatConstants.DEFAULT_DATE_FORMAT));
            }
        });

        // LocalTime
        registry.addFormatter(new Formatter<LocalTime>() {
            @Nonnull
            @Override
            public LocalTime parse(@Nonnull String text, @Nonnull Locale locale) {
                return LocalTime.parse(text,
                        DateTimeFormatter.ofPattern(DateFormatConstants.DEFAULT_TIME_FORMAT));
            }

            @Nonnull
            @Override
            public String print(@Nonnull LocalTime object, @Nonnull Locale locale) {
                return object.format(DateTimeFormatter.ofPattern(DateFormatConstants.DEFAULT_TIME_FORMAT));
            }
        });
    }
}
