package com.basic.util;

import lombok.experimental.UtilityClass;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数帮助类
 *
 * @author vains
 */
@UtilityClass
public class RandomUtils {

    /**
     * 根据给定长度生成一个随机字符串
     *
     * @param randomLength 字符串长度
     * @return 随机字符串
     */
    public static String randomString(int randomLength) {
        // 字符串挑选模板
        String baseCharNumber = "abcdefghijklmnopqrstuvwxyz".toUpperCase() + "abcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder captchaBuilder = new StringBuilder(randomLength);

        // 随机字符串
        for (int i = 0; i < randomLength; ++i) {
            int number = ThreadLocalRandom.current().nextInt(baseCharNumber.length());
            captchaBuilder.append(baseCharNumber.charAt(number));
        }

        return captchaBuilder.toString();
    }

    /**
     * 根据给定长度生成一个随机字符串(纯数字)
     *
     * @param randomLength 字符串长度
     * @return 随机字符串
     */
    public static String randomNumber(int randomLength) {
        // 字符串挑选模板
        String baseCharNumber = "0123456789";

        StringBuilder captchaBuilder = new StringBuilder(randomLength);

        // 随机字符串
        for (int i = 0; i < randomLength; ++i) {
            int number = ThreadLocalRandom.current().nextInt(baseCharNumber.length());
            captchaBuilder.append(baseCharNumber.charAt(number));
        }

        return captchaBuilder.toString();
    }

}
