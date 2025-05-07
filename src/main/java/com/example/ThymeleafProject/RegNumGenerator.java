package com.example.ThymeleafProject;

import java.util.Random;

public class RegNumGenerator {
    private static final Random RANDOM = new Random();

    public static String generateRegNum() {
        int part1 = RANDOM.nextInt(100); // Генерируем двузначное число
        int part2 = RANDOM.nextInt(1_000_000); // Генерируем шестизначное число
        return String.format("%02d-%06d", part1, part2); // Форматируем числа в строку
    }
}

