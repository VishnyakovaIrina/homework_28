package home.ivishnyakova;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 *
 */
public class App 
{
    public static void main(String[] args) {

        List<String> strings = Arrays.asList("Отдых на море - это круто","Природа и море",
                "Пляж и солнце, море1, море2","Шашлык и природа","Море и пляж",
                "Жара и море","Лето и отдых","Берег моря", "Скучаю по Черному морю...");


        System.out.println(" --== Исходный текст ==-- ");
        strings.forEach(System.out::println);
        System.out.println("------------== ==--------------");

        UtilStrings.getMaxStringLength(strings).ifPresent(
                (str) -> System.out.println("Строка макс.длины - " + str +
                        ", длина = " + str.length()
                ));

        String maxWord = UtilStrings.getMaxWordLength(strings).orElse("Не найдено");
        System.out.println("Слово с максим.длиной = " + maxWord +
                ", длина = " + maxWord.length());

        List<String> oneMaxList0 = Arrays.asList(
                "В Украине есть две горные системы - Карпаты и Крымские горы.",
                "Карпатские горы относительно молодые,",
                "им более 25 миллионов лет.",
                "Например, в Карпатах находится до 20%",
                "всех лесов Украины.");
        String searchLine = "горы";
        long occurrences = UtilStrings.getWordNumberOccurrences(oneMaxList0, searchLine);
        System.out.println("Кол-во вхождений шаблона \"" + searchLine +"\" = " + occurrences);

        List<String> maxLengthStrings = UtilStrings.getStringsWithMaxLength(strings);
        System.out.println("Строки максимальной длины: \nВариант 1");
        maxLengthStrings.forEach(System.out::println);

        Optional<List<String>> maxLengthStringsOpt = UtilStrings.getStringsOptWithMaxLength(strings);
        System.out.println("Строки максимальной длины: \nВариант 2");
        maxLengthStringsOpt.ifPresent(System.out::println);

    }

}
