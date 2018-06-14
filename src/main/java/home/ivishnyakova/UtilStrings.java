package  home.ivishnyakova;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* Класс UtilStrings содержит статические методы обработки текстовой инфо с целью:
* - поиска одной самой длинной строки (всех длинных строк в тексте);
* - поиска самого длинного слова;
* - поиска заданного слова в тексте и подсчета количества вхождений этого слова.
*
* Автор: Вишнякова И.
* Дата: 14/06/18
* */
public class UtilStrings {

    /*Метод getMaxStringLength выполняет поиск строки с максимальной длиной в списке строк strings.
    * Возвращает Optional<String> - найденную строку или пустой Optional.
    * Если в списке имеется несколько строк одинаковой длины ,
    * то возвращается любая (порядок не гарантирован).*/
    public static Optional<String> getMaxStringLength(List<String> strings){
        List<String> stringsOpt = Optional.ofNullable(strings).orElseThrow(IllegalArgumentException::new);

        if (stringsOpt.isEmpty()){
            return Optional.empty();
        }

        return stringsOpt.stream()
                .reduce( (String a, String b) -> a.length() > b.length() ? a : b
        );
    }

    /*Метод getMaxWordLength выполняет поиск слова с максимальной длиной в списке строк strings.
    * Возвращает Optional<String> - найденное слово или пустой Optional*/
    public static Optional<String> getMaxWordLength(List<String> strings){
        List<String> stringsOpt = Optional.ofNullable(strings).orElseThrow(IllegalArgumentException::new);

        if (stringsOpt.isEmpty()){
            return Optional.empty();
        }
        
        return stringsOpt
                .stream()
                .flatMap(s -> Arrays.stream(s.split("[^а-яА-Яa-zA-Z]")))
                .max(Comparator.comparing(String::length));
    }

    /*Метод getStringsWithMaxLength создает список строк из strings,
    которые имеют максимальную длину.*/
    public static List<String> getStringsWithMaxLength(List<String> strings){
        List<String> stringsOpt = Optional.ofNullable(strings).orElseThrow(IllegalArgumentException::new);

        if (stringsOpt.isEmpty()){
            return Collections.emptyList();
        }

        int maxLength = getMaxStringLength(stringsOpt).orElse("").length();
        return stringsOpt.stream()
                .parallel()
                .filter( s -> s.length() == maxLength)
                .collect(Collectors.toList());
    }

    /*Метод getStringsOptWithMaxLength создает контейнер типа Optional,
    содержащий список строк из strings, которые имеют максимальную длину.*/
    public static Optional<List<String>> getStringsOptWithMaxLength(List<String> strings){
        List<String> stringsOpt = Optional.ofNullable(strings).orElseThrow(IllegalArgumentException::new);

        if (stringsOpt.isEmpty()){
            return Optional.empty();
        }

        //сгруппировать строки по длине
        Map<Integer,List<String>> res = stringsOpt
                .stream()
                .collect(Collectors.groupingBy(String::length));

        //найти строки с максимальной длиной
        Optional<Map.Entry<Integer,List<String>>> maxRes = res.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry<Integer, List<String>>::getKey).reversed())
                .findFirst();

        return maxRes.map(Map.Entry::getValue);
    }

    /*Метод getWordNumberOccurrences выполняет поиск и подсчет количества вхождений заданного
    *слова searchWord в списке строк strings.*/
    public static long getWordNumberOccurrences(List<String> strings, String searchWord){

        List<String> stringsOpt = Optional.ofNullable(strings).orElseThrow(IllegalArgumentException::new);
        String searchWordOpt = Optional.ofNullable(searchWord).orElseThrow(IllegalArgumentException::new);

        if (stringsOpt.isEmpty() || searchWord.isEmpty()) return 0;

        Optional<Stream<String>> allWords =  stringsOpt
                .stream()
                .map(s -> Arrays.stream(s.split("[^а-яА-Яa-zA-Z]")))
                .reduce(Stream::concat);

        long countOccurrences = 0;
        if (allWords.isPresent()){
            countOccurrences = allWords.get()
                    .filter(item -> item.equals(searchWordOpt))
                    .count();
        }
        return countOccurrences;
    }
}
