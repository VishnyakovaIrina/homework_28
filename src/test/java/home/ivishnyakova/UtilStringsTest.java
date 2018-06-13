package home.ivishnyakova;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;


public class UtilStringsTest {

    private static List<String> nullList;
    private static List<String> emptyList, emptyStringList;
    private static List<String> textSource;
    private static String stringSource;

    @BeforeClass
    public static void setUpClass(){
        nullList = null;
        emptyList = Collections.emptyList();
        emptyStringList = Collections.singletonList("");

        textSource =  Arrays.asList(
                "В Украине есть две горные системы - Карпаты и Крымские горы.",
                "Карпатские горы относительно молодые,",
                "им более 25 миллионов лет.",
                "Например, в Карпатах находится до 20%",
                "всех лесов Украины.");

        stringSource = "Самая высокая гора Украинских Карпат – гора Говерла";
    }

    /*Набор тестов для проверки метода getMaxStringLength - максимальная по длине строка*/
    @Test(expected = IllegalArgumentException.class)
    public void testGetMaxStringLength_Null_Exception(){
        assertThat("Source is null", UtilStrings.getMaxStringLength(nullList), equalTo(Optional.empty()));
    }
    @Test
    public void testGetMaxStringLength_EmptyList_Empty(){
        assertThat("Source is empty", UtilStrings.getMaxStringLength(emptyList), equalTo(Optional.empty()));
    }
    @Test
    public void testGetMaxStringLength_EmptyString_Empty(){
        assertThat("Source is empty string", UtilStrings.getMaxStringLength(emptyStringList), equalTo(Optional.of("")));
    }
    @Test
    public void testGetMaxStringLength_OneString_ThisString(){
        List<String> singleList = Collections.singletonList("отдых");
        assertThat("Source is one string", UtilStrings.getMaxStringLength(singleList), equalTo(Optional.of(singleList.get(0))));
    }
    @Test
    public void testGetMaxStringLength_DiffStringsByLength_FirstString(){
        List<String> strings = Arrays.asList("прекрасная погода", "морской бриз",  "Крымские горы");
        assertThat("Source is text, that contains the first max row",
                UtilStrings.getMaxStringLength(strings),
                equalTo(Optional.of(strings.get(0))));
    }
    @Test
    public void testGetMaxStringLength_DiffStringsByLength_MiddleString(){
        List<String> strings = Arrays.asList("морской бриз", "прекрасная погода", "Крымские горы");
        assertThat("Source is text, that contains the max row in middle",
                UtilStrings.getMaxStringLength(strings),
                equalTo(Optional.of(strings.get(1))));
    }
    @Test
    public void testGetMaxStringLength_DiffStringsByLength_LastString(){
        List<String> strings = Arrays.asList("морской бриз", "Крымские горы", "погода прекрасная");
        assertThat("Source is text, that contains the last max row",
                UtilStrings.getMaxStringLength(strings),
                equalTo(Optional.of(strings.get(2))));
    }
    @Test
    public void testGetMaxStringLength_SomeStringsEqualsByLength_2Strings(){
        List<String> strings = Arrays.asList(
                "В Украине есть две горные системы",
                "- Карпаты и Крымские горы.",
                "Карпатские горы относительно",
                "молодые, им более 25 миллионов лет",
                "Например, в Карпатах находится до ",
                "20% всех лесов Украины.");
        assertThat("Source is text, that contains the several rows with max length",
                UtilStrings.getMaxStringLength(strings),
                anyOf(equalTo(Optional.of(strings.get(3))),equalTo(Optional.of(strings.get(4)))));
    }

    /*Набор тестов для проверки метода getMaxWordLength - максимальное по длине слово*/
    @Test(expected = IllegalArgumentException.class)
    public void testGetMaxWordLength_Null_Exception(){
        assertThat("Source is null",
                UtilStrings.getMaxWordLength(nullList),
                equalTo(Optional.empty()));
    }
    @Test
    public void testGetMaxWordLength_EmptyList_Empty(){
        assertThat("Source is empty",
                UtilStrings.getMaxWordLength(emptyList),
                equalTo(Optional.empty()));
    }
    @Test
    public void testGetMaxWordLength_EmptyString_EmptyString(){
        assertThat("Source is empty string",
                UtilStrings.getMaxWordLength(emptyStringList),
                equalTo(Optional.of("")));
    }
    @Test
    public void testGetMaxWordLength_OneWord_ThisWord(){
        List<String> singleList = Collections.singletonList("отдых");
        assertThat("Source is a word",
                UtilStrings.getMaxWordLength(singleList),
                equalTo(Optional.of(singleList.get(0))));
    }
    @Test
    public void testGetMaxWordLength_SeveralStrings_FirstWord(){
        List<String> strings = Arrays.asList("прекрасная погода", "морской бриз",  "Крымские горы");
        assertThat("The max word is in the begin of string",
                UtilStrings.getMaxWordLength(strings),
                equalTo(Optional.of("прекрасная")));
    }
    @Test
    public void testGetMaxWordLength_SeveralStrings_WordInMiddle(){
        List<String> strings = Arrays.asList("морской бриз", "прекрасная погода", "Крымские горы");
        assertThat("The max word is in the middle of string",
                UtilStrings.getMaxWordLength(strings),
                equalTo(Optional.of("прекрасная")));
    }
    @Test
    public void testGetMaxWordLength_SeveralStrings_LastWord(){
        List<String> strings = Arrays.asList("морской бриз", "Крымские горы", "погода прекрасная");
        assertThat("The max word is in the end of string",
                UtilStrings.getMaxWordLength(strings),
                equalTo(Optional.of("прекрасная")));
    }
    @Test
    public void testGetMaxWordLength_SeveralStrings_SeveralMaxWords(){
        List<String> strings = Arrays.asList("Крымские горы", "прекрасная природа", "прекрасный день");
        assertThat("The string contains more than one max word by length",
                UtilStrings.getMaxWordLength(strings),
                anyOf(equalTo(Optional.of("прекрасная")), equalTo(Optional.of("прекрасный"))));
    }

    /*Набор тестов для проверки методов getStringsWithMaxLength - поиск строк с максимальной длиной*/
    @Test(expected = IllegalArgumentException.class)
    public void testGetStringsWithMaxLength_Null_Exception(){
        assertThat("Source is null",
                UtilStrings.getStringsWithMaxLength(nullList),
                equalTo(Collections.emptyList()));
    }
    @Test
    public void testGetStringsWithMaxLength_EmptyList_EmptyList(){
        assertThat("Source is empty",
                UtilStrings.getStringsWithMaxLength(emptyList),
                equalTo(Collections.emptyList()));
    }
    @Test
    public void testGetStringsWithMaxLength_EmptyString_EmptyList(){
        assertThat("Source is empty string",
                UtilStrings.getStringsWithMaxLength(emptyStringList),
                equalTo(Collections.singletonList("")));
    }
    @Test
    public void testGetStringsWithMaxLength_OneString_ListWithThisString(){
        List<String> singleList = Collections.singletonList("The country music is good!");
        assertThat("Source is one string",
                UtilStrings.getStringsWithMaxLength(singleList),
                equalTo(Collections.singletonList(singleList.get(0))));
    }
    @Test
    public void testGetStringsWithMaxLength_Strings_ListWithFirstString(){
        List<String> strings =  Arrays.asList(
                "В Украине есть две горные системы - Карпаты и Крымские горы.",
                "Карпатские горы относительно молодые,",
                "им более 25 миллионов лет.",
                "Например, в Карпатах находится до 20%",
                "всех лесов Украины.");
        assertThat("The first line is max",
                UtilStrings.getStringsWithMaxLength(strings),
                equalTo(Collections.singletonList(strings.get(0))));
    }
    @Test
    public void testGetStringsWithMaxLength_Strings_ListWithMiddleString(){
        List<String> strings =  Arrays.asList(
                "В Украине есть две горные системы",
                "- Карпаты и Крымские горы.",
                "Карпатские горы относительно молодые,",
                "им более 25 миллионов лет.",
                "Например, в Карпатах находится",
                " до 20% всех лесов Украины.");
        assertThat("The first line is max",
                UtilStrings.getStringsWithMaxLength(strings),
                equalTo(Collections.singletonList(strings.get(2))));
    }
    @Test
    public void testGetStringsWithMaxLength_Strings_ListWithLastString(){
        List<String> strings =  Arrays.asList(
                "В Украине есть две горные системы",
                "- Карпаты и Крымские горы.",
                "Карпатские горы относительно молодые,",
                "им более 25 миллионов лет.",
                "Например, в Карпатах находится до 20% всех лесов Украины.");
        assertThat("The last line is max",
                UtilStrings.getStringsWithMaxLength(strings),
                equalTo(Collections.singletonList(strings.get(strings.size() - 1))));
    }
    @Test
    public void testGetStringsWithMaxLength_Strings_ListWithSeveralString(){
        List<String> strings =  Arrays.asList(
                "В Украине есть две горные системы ",
                "- Карпаты и Крымские горы.",
                "Карпатские горы относительно",
                "молодые, им более 25 миллионов лет",
                "Например, в Карпатах находится до ",
                "20% всех лесов Украины.");
        assertThat("There are several max lines",
                UtilStrings.getStringsWithMaxLength(strings),
                is(Arrays.asList(strings.get(0), strings.get(3), strings.get(4))));
    }


    /*Набор тестов для проверки методов getStringsOptWithMaxLength - поиск строк с максимальной длиной*/
    @Test(expected = IllegalArgumentException.class)
    public void testGetStringsOptWithMaxLength_Null_Exception(){
        assertThat("Source is null",
                UtilStrings.getStringsOptWithMaxLength(nullList),
                equalTo(Collections.emptyList()));
    }
    @Test
    public void testGetStringsOptWithMaxLength_EmptyList_Empty(){
        assertThat("Source is empty",
                UtilStrings.getStringsOptWithMaxLength(emptyList),
                equalTo(Optional.empty()));
    }
    @Test
    public void testGetStringsOptWithMaxLength_ListWithEmptyString_ListWithEmptyString(){
        assertThat("Source is empty string",
                UtilStrings.getStringsOptWithMaxLength(emptyStringList),
                equalTo(Optional.of(Collections.singletonList(""))));
    }
    @Test
    public void testGetStringsOptWithMaxLength_ListWithOneString_ListWithThisString(){
        List<String> singleList = Collections.singletonList("отдых");
        assertThat("Source is one string",
                UtilStrings.getStringsOptWithMaxLength(singleList),
                equalTo(Optional.of(Collections.singletonList(singleList.get(0)))));    }
    @Test
    public void testGetStringsOptWithMaxLength_Strings_FirstString(){
        List<String> strings = Arrays.asList(
                "В Украине есть две горные системы - Карпаты и Крымские горы.",
                "Карпатские горы относительно молодые,",
                "им более 25 миллионов лет.",
                "Например, в Карпатах находится до 20%",
                "всех лесов Украины.");
        assertThat("The first line is max",
                UtilStrings.getStringsOptWithMaxLength(strings),
                equalTo(Optional.of(Collections.singletonList(strings.get(0)))));
    }
    @Test
    public void testGetStringsOptWithMaxLength_Strings_MiddleString(){
        List<String> strings = Arrays.asList(
                "В Украине есть две горные системы",
                "- Карпаты и Крымские горы.",
                "Карпатские горы относительно молодые,",
                "им более 25 миллионов лет.",
                "Например, в Карпатах находится",
                " до 20% всех лесов Украины.");
        assertThat("The middle line is max",
                UtilStrings.getStringsOptWithMaxLength(strings),
                equalTo(Optional.of(Collections.singletonList(strings.get(2)))));
    }
    @Test
    public void testGetStringsOptWithMaxLength_Strings_LastString(){
        List<String> strings = Arrays.asList(
                "В Украине есть две горные системы",
                "- Карпаты и Крымские горы.",
                "Карпатские горы относительно молодые,",
                "им более 25 миллионов лет.",
                "Например, в Карпатах находится до 20% всех лесов Украины.");

        assertThat("The last line is max",
                UtilStrings.getStringsOptWithMaxLength(strings),
                equalTo(Optional.of(Collections.singletonList(strings.get(strings.size() - 1)))));
    }
    @Test
    public void testGetStringsOptWithMaxLength_Strings_SeveralStrings(){
        List<String> strings = Arrays.asList(
                "В Украине есть две горные системы ",
                "- Карпаты и Крымские горы.",
                "Карпатские горы относительно",
                "молодые, им более 25 миллионов лет",
                "Например, в Карпатах находится до ",
                "20% всех лесов Украины.");
        assertThat("There are several max lines",
                UtilStrings.getStringsOptWithMaxLength(strings),
                is(Optional.of(Arrays.asList(strings.get(0), strings.get(3), strings.get(4)))));

    }

    /*Набор тестов для проверки метода getWordNumberOccurrences, которые генерируют исключение*/
    @Test(expected = IllegalArgumentException.class)
    public void testGetWordNumberOccurrences_DblNull_Exception() {
        assertThat("Text source and search line are null",
                UtilStrings.getWordNumberOccurrences(nullList, null),
                equalTo(0L));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetWordNumberOccurrences_NullAndEmpty_Exception() {
        assertThat("Text source is null, search line is empty",
                UtilStrings.getWordNumberOccurrences(nullList, ""),
                equalTo(0L));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetWordNumberOccurrences_NullAndBlank_Exception() {
        assertThat("Text source is null, search line is blank",
                UtilStrings.getWordNumberOccurrences(nullList, " "),
                equalTo(0L));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetWordNumberOccurrences_NullAndWord_Exception() {
        assertThat("Text source is null, search line is one word",
                UtilStrings.getWordNumberOccurrences(nullList, "горы"),
                equalTo(0L));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetWordNumberOccurrences_EmptyListAndNull_Exception() {
        assertThat("Text source is empty, search line is null",
                UtilStrings.getWordNumberOccurrences(emptyList, null),
                equalTo(0L));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetWordNumberOccurrences_EmptyStringAndNull_Exception() {
        assertThat("Text source is empty line, search line is null",
                UtilStrings.getWordNumberOccurrences(emptyStringList, null),
                equalTo(0L));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetWordNumberOccurrences_WordAndNull_Exception() {
        assertThat("Text source is single word, search line is null",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList("горы"), null),
                equalTo(0L));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetWordNumberOccurrences_StringsAndNull_Exception() {
        assertThat("Text source is not empty, search line is null",
                UtilStrings.getWordNumberOccurrences(
                        Arrays.asList("Самая высокая вершина крымских гор ","– гора Роман-Кош высотой 1945 м"),
                        null),
                equalTo(0L));
    }

    /*Набор тестов для проверки метода getWordNumberOccurrences, когда источник текста пуст*/
    @Test
    public void testGetWordNumberOccurrences_EmptyListAndEmpty_Zero() {
        assertThat("Text source is empty, search line is empty",
                UtilStrings.getWordNumberOccurrences(emptyList, ""),
                equalTo(0L));
    }
    @Test
    public void testGetWordNumberOccurrences_EmptyListAndBlank_Zero() {
        assertThat("Text source is empty, search line is blank",
                UtilStrings.getWordNumberOccurrences(emptyList, " "),
                equalTo(0L));
    }
    @Test
    public void testGetWordNumberOccurrences_EmptyListAndWord_Zero() {
        assertThat("Text source is empty, search line is not empty",
                UtilStrings.getWordNumberOccurrences(emptyList, "горы"),
                equalTo(0L));
    }
    @Test
    public void testGetWordNumberOccurrences_EmptyStringAndEmpty_Zero() {
        assertThat("Text source is empty string, search line is empty",
                UtilStrings.getWordNumberOccurrences(emptyStringList, ""),
                equalTo(0L));
    }
    @Test
    public void testGetWordNumberOccurrences_EmptyStringAndBlank_Zero() {
        assertThat("Text source is empty string, search line is blank",
                UtilStrings.getWordNumberOccurrences(emptyStringList, " "),
                equalTo(0L));
    }
    @Test
    public void testGetWordNumberOccurrences_EmptyStringAndWord_Zero() {
        assertThat("Text source is empty string, search line is not empty",
                UtilStrings.getWordNumberOccurrences(emptyStringList, "горы"),
                equalTo(0L));
    }

    /*Набор тестов для проверки метода getWordNumberOccurrences, когда источник содержит одно слово*/
    @Test
    public void testGetWordNumberOccurrences_WordAndEmpty_Zero() {
        assertThat("Text source is one word, search line is empty",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList("горы"), ""),
                equalTo(0L));
    }
    @Test
    public void testGetWordNumberOccurrences_WordAndBlank_Zero() {
        assertThat("Text source is one word, search line is blank",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList("горы"), " "),
                equalTo(0L));
    }
    @Test
    public void testGetWordNumberOccurrences_WordsIsNotEquals_Zero() {
        assertThat("Text source is one word, search line is not in source",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList("море"), "горы"),
                equalTo(0L));
    }
    @Test
    public void testGetWordNumberOccurrences_WordsIsEquals_Once() {
        assertThat("Text source and search line is equals",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList("горы"), "горы"),
                equalTo(1L));
    }
    @Test
    public void testGetWordNumberOccurrences_WordAndSeveralWords_Zero() {
        assertThat("Text source is one word, search line is several words",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList("горы"), "высокая гора"),
                equalTo(0L));
    }

    /*Набор тестов для проверки метода getWordNumberOccurrences, когда источник содержит одну строку*/
    @Test
    public void testGetWordNumberOccurrences_OneStringAndEmpty_Zero() {
        assertThat("Text source is one string and search line is empty string",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList(stringSource), ""),
                equalTo(0L));
    }
    @Test
    public void testGetWordNumberOccurrences_OneStringAndBlank_Zero() {
        assertThat("Text source is one string and search line is empty blank",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList(stringSource), " "),
                equalTo(0L));
    }
    @Test
    public void testGetWordNumberOccurrences_OneStringAndString_Zero() {
        assertThat("Text source is one string and search line is not in source",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList(stringSource), "горы"),
                equalTo(0L));}
    @Test
    public void testGetWordNumberOccurrences_OneStringAndString_Once() {
        assertThat("Text source is one string and search line is in source once",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList(stringSource), "высокая"),
                equalTo(1L));
    }
    @Test
    public void testGetWordNumberOccurrences_OneStringAndString_Twice() {
        assertThat("Text source is one string and search line is in source more than one time",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList(stringSource), "гора"),
                equalTo(2L));
    }
    @Test
    public void testGetWordNumberOccurrences_OneStringAndSeveralWords_Zero() {
        assertThat("Text source is one string and search line (several words) is in source",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList(stringSource), "высокая гора"),
                equalTo(0L));
    }

    /*Набор тестов для проверки метода getWordNumberOccurrences, когда источник содержит многострочный текст*/
    @Test
    public void testGetWordNumberOccurrences_StringsAndString_Zero() {
        assertThat("Text source is text and search line is not in text",
                UtilStrings.getWordNumberOccurrences(textSource, "лето"),
                equalTo(0L));
    }
    @Test
    public void testGetWordNumberOccurrences_StringsAndString_Once() {
        assertThat("Text source is text and search line is in text only once",
                UtilStrings.getWordNumberOccurrences(textSource, "Карпаты"),
                equalTo(1L));
    }
    @Test
    public void testGetWordNumberOccurrences_StringsAndString_Twice() {
        assertThat("Text source is text and search line is in text only twice",
                UtilStrings.getWordNumberOccurrences(textSource, "горы"),
                equalTo(2L));
    }
    @Test
    public void testGetWordNumberOccurrences_StringsAndEmpty_Zero() {
        assertThat("Text source is text and search line is empty string",
                UtilStrings.getWordNumberOccurrences(textSource, ""),
                equalTo(0L));
    }
    @Test
    public void testGetWordNumberOccurrences_StringsAndBlank_Zero() {
        assertThat("Text source is text and search line is blank",
                UtilStrings.getWordNumberOccurrences(textSource, " "),
                equalTo(0L));
    }
    @Test
    public void testGetWordNumberOccurrences_StringsAndSeveralWords_Zero() {
        assertThat("Text source is text and search line (several words) is in text",
                UtilStrings.getWordNumberOccurrences(textSource, "высокая гора"),
                equalTo(0L));
    }
}