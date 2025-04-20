package cn.microboat.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 编码生成器
 */
public class CodeGenerator {

    // 默认随机字符串长度
    private static final int DEFAULT_RANDOM_LENGTH = 8;

    // 默认日期时间格式
    private static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    // 用于生成随机字符的字符集
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * 生成带前缀、时间戳和随机字符的编码
     * 格式: prefix_yyyyMMddHHmmss_randomString
     *
     * @param prefix 前缀
     * @return 生成的编码
     */
    public static String generateCode(String prefix) {
        return generateCode(prefix, LocalDateTime.now(), DEFAULT_RANDOM_LENGTH);
    }

    /**
     * 生成带前缀、时间戳和随机字符的编码，可指定随机字符长度
     *
     * @param prefix       前缀
     * @param randomLength 随机字符长度
     * @return 生成的编码
     */
    public static String generateCode(String prefix, int randomLength) {
        return generateCode(prefix, LocalDateTime.now(), randomLength);
    }

    /**
     * 生成带前缀、指定时间和随机字符的编码
     *
     * @param prefix       前缀
     * @param dateTime     日期时间
     * @param randomLength 随机字符长度
     * @return 生成的编码
     */
    public static String generateCode(String prefix, LocalDateTime dateTime, int randomLength) {
        StringBuilder stringBuilder = new StringBuilder();

        // 添加前缀
        if (prefix != null && !prefix.isEmpty()) {
            stringBuilder.append(prefix).append("_");
        }

        // 添加时间戳
        stringBuilder.append(dateTime.format(DEFAULT_DATE_FORMATTER)).append("_");

        // 添加随机字符串
        stringBuilder.append(generateRandomString(randomLength));

        return stringBuilder.toString();
    }

    /**
     * 使用自定义日期格式生成编码
     *
     * @param prefix       前缀
     * @param dateTime     日期时间
     * @param formatter    日期格式化器
     * @param randomLength 随机字符长度
     * @return 生成的编码
     */
    public static String generateCode(String prefix, LocalDateTime dateTime, DateTimeFormatter formatter, int randomLength) {
        StringBuilder stringBuilder = new StringBuilder();

        // 添加前缀
        if (prefix != null && !prefix.isEmpty()) {
            stringBuilder.append(prefix).append("_");
        }

        // 添加时间戳
        stringBuilder.append(dateTime.format(formatter)).append("_");

        // 添加随机字符串
        stringBuilder.append(generateRandomString(randomLength));

        return stringBuilder.toString();
    }

    /**
     * 生成指定长度的随机字符串
     *
     * @param length 长度
     * @return 随机字符串
     */
    public static String generateRandomString(int length) {
        if (length <= 0) {
            return "";
        }

        Random random = ThreadLocalRandom.current();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARS.length());
            stringBuilder.append(CHARS.charAt(index));
        }

        return stringBuilder.toString();
    }

    /**
     * 生成UUID（无连字符）
     *
     * @return UUID字符串
     */
    public static String generateUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成短UUID（16位）
     *
     * @return 短UUID字符串
     */
    public static String generateShortUuid() {
        UUID uuid = UUID.randomUUID();
        long mostSigBits = uuid.getMostSignificantBits();
        long leastSigBits = uuid.getLeastSignificantBits();

        return toIDString(mostSigBits) + toIDString(leastSigBits);
    }

    /**
     * 将long转为8字符的字符串
     */
    private static String toIDString(long l) {
        StringBuilder stringBuilder = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            // 取最低4位
            int b = (int) (l & 0x0f);
            stringBuilder.append(CHARS.charAt(b));
            l >>= 4;
        }
        return stringBuilder.toString();
    }

    /**
     * 生成数字编码
     *
     * @param length 长度
     * @return 数字编码
     */
    public static String generateNumericCode(int length) {
        Random random = ThreadLocalRandom.current();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            stringBuilder.append(random.nextInt(10));
        }

        return stringBuilder.toString();
    }

    /**
     * 生成前缀+流水号形式的编码
     *
     * @param prefix   前缀
     * @param sequence 序列号
     * @param width    序列号宽度（用0填充）
     * @return 编码
     */
    public static String generateSequenceCode(String prefix, long sequence, int width) {
        return String.format("%s%0" + width + "d", prefix, sequence);
    }

    /**
     * JPA实体中可使用此方法生成编码
     * 示例：在实体类的@PrePersist方法中调用
     *
     * @param entityName 实体名称（作为前缀）
     * @return 生成的编码
     */
    public static String generateEntityCode(String entityName) {
        return generateCode(entityName.toLowerCase());
    }
}
