package home.ivishnyakova;

import org.junit.Before;
import org.junit.Test;

import java.util.*;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;


public class UtilStringsTest {

    private List<String> nullList = null;
    private List<String> emptyList, emptyStringList;
    private List<String> singleList;
    private List<String> oneList0;
    private List<String> oneList1;
    private List<String> oneList2;
    private List<String> moreOneList;

    @Before
    public void setUpMaxString(){
        nullList = null;
        emptyList = Collections.emptyList();
        emptyStringList = Collections.singletonList("");
    }

    /*Набор тестов для проверки метода getMaxStringLength - максимальная по длине строка*/
    @Test(expected = IllegalArgumentException.class)
    public void testMaxStringWithException(){
        assertThat("Source is null", UtilStrings.getMaxStringLength(nullList), equalTo(Optional.empty()));
    }

    /*Набор тестов для проверки метода getMaxStringLength - максимальная по длине строка*/
    @Test
    public void testMaxString(){
        singleList = Collections.singletonList("отдых");
        oneList0 = Arrays.asList("прекрасная погода", "морской бриз",  "Крымские горы");
        oneList1 = Arrays.asList("морской бриз", "прекрасная погода", "Крымские горы");
        oneList2 = Arrays.asList("морской бриз", "Крымские горы", "погода прекрасная");
        moreOneList = Arrays.asList(
                "В Украине есть две горные системы",
                "- Карпаты и Крымские горы.",
                "Карпатские горы относительно",
                "молодые, им более 25 миллионов лет",
                "Например, в Карпатах находится до ",
                "20% всех лесов Украины.");

        assertThat("Source is empty", UtilStrings.getMaxStringLength(emptyList), equalTo(Optional.empty()));
        assertThat("Source is empty string", UtilStrings.getMaxStringLength(emptyStringList), equalTo(Optional.of("")));
        assertThat("Source is one word", UtilStrings.getMaxStringLength(singleList), equalTo(Optional.of(singleList.get(0))));

        assertThat("Source is text, that contains the first max row",
                UtilStrings.getMaxStringLength(oneList0),
                equalTo(Optional.of(oneList0.get(0))));

        assertThat("Source is text, that contains the max row in middle",
                UtilStrings.getMaxStringLength(oneList1),
                equalTo(Optional.of(oneList1.get(1))));

        assertThat("Source is text, that contains the last max row",
                UtilStrings.getMaxStringLength(oneList2),
                equalTo(Optional.of(oneList2.get(2))));

        assertThat("Source is text, that contains the several rows with max length",
                UtilStrings.getMaxStringLength(moreOneList),
                anyOf(equalTo(Optional.of(moreOneList.get(3))),equalTo(Optional.of(moreOneList.get(4)))));
    }

    /*Набор тестов для проверки метода getMaxWordLength - максимальное по длине слово*/
    @Test(expected = IllegalArgumentException.class)
    public void testMaxWordLengthWitException(){
        assertThat("Source is null",
                UtilStrings.getMaxWordLength(nullList),
                equalTo(Optional.empty()));
    }

    /*Набор тестов для проверки метода getMaxWordLength - максимальное по длине слово*/
    @Test
    public void testMaxWordLength(){
        singleList = Collections.singletonList("отдых");
        oneList0 = Arrays.asList("прекрасная погода", "морской бриз",  "Крымские горы");
        oneList1 = Arrays.asList("морской бриз", "прекрасная погода", "Крымские горы");
        oneList2 = Arrays.asList("морской бриз", "Крымские горы", "погода прекрасная");
        moreOneList = Arrays.asList("Крымские горы", "прекрасная природа", "прекрасный день");

        assertThat("Source is empty",
                UtilStrings.getMaxWordLength(emptyList),
                equalTo(Optional.empty()));

        assertThat("Source is empty string",
                UtilStrings.getMaxWordLength(emptyStringList),
                equalTo(Optional.of("")));

        assertThat("Source is a word",
                UtilStrings.getMaxWordLength(singleList),
                equalTo(Optional.of(singleList.get(0))));

        assertThat("The max word is in the begin of string",
                UtilStrings.getMaxWordLength(oneList0),
                equalTo(Optional.of("прекрасная")));

        assertThat("The max word is in the middle of string",
                UtilStrings.getMaxWordLength(oneList1),
                equalTo(Optional.of("прекрасная")));

        assertThat("The max word is in the end of string",
                UtilStrings.getMaxWordLength(oneList2),
                equalTo(Optional.of("прекрасная")));

        assertThat("The string contains more than one max word by length",
                UtilStrings.getMaxWordLength(moreOneList),
                anyOf(equalTo(Optional.of("прекрасная")), equalTo(Optional.of("прекрасный"))));
    }

    /*==================================================================*/

    /*Подготовка списков - источников текста*/
    private void setUpLists(){
        singleList = Collections.singletonList("отдых");

        oneList0 = Arrays.asList(
                "В Украине есть две горные системы - Карпаты и Крымские горы.",
                "Карпатские горы относительно молодые,",
                "им более 25 миллионов лет.",
                "Например, в Карпатах находится до 20%",
                "всех лесов Украины.");
        oneList1 = Arrays.asList(
                "В Украине есть две горные системы",
                "- Карпаты и Крымские горы.",
                "Карпатские горы относительно молодые,",
                "им более 25 миллионов лет.",
                "Например, в Карпатах находится",
                " до 20% всех лесов Украины.");
        oneList2 = Arrays.asList(
                "В Украине есть две горные системы",
                "- Карпаты и Крымские горы.",
                "Карпатские горы относительно молодые,",
                "им более 25 миллионов лет.",
                "Например, в Карпатах находится до 20% всех лесов Украины.");
        moreOneList = Arrays.asList(
                "В Украине есть две горные системы ",
                "- Карпаты и Крымские горы.",
                "Карпатские горы относительно",
                "молодые, им более 25 миллионов лет",
                "Например, в Карпатах находится до ",
                "20% всех лесов Украины.");
    }

    /*Набор тестов для проверки методов getStringsWithMaxLength - поиск строк с максимальной длиной*/
    @Test(expected = IllegalArgumentException.class)
    public void testStringsWithMaxLengthWithException(){
        assertThat("Source is null",
                UtilStrings.getStringsWithMaxLength(nullList),
                equalTo(Collections.emptyList()));
    }

    /*Набор тестов для проверки методов getStringsWithMaxLength - поиск строк с максимальной длиной*/
    @Test
    public void testStringsWithMaxLength(){

        setUpLists();

        assertThat("Source is empty",
                UtilStrings.getStringsWithMaxLength(emptyList),
                equalTo(Collections.emptyList()));

        assertThat("Source is empty string",
                UtilStrings.getStringsWithMaxLength(emptyStringList),
                equalTo(Collections.singletonList("")));

        assertThat("Source is one string",
                UtilStrings.getStringsWithMaxLength(singleList),
                equalTo(Collections.singletonList(singleList.get(0))));

        assertThat("The first line is max",
                UtilStrings.getStringsWithMaxLength(oneList0),
                equalTo(Collections.singletonList(oneList0.get(0))));

        assertThat("The middle line is max",
                UtilStrings.getStringsWithMaxLength(oneList1),
                equalTo(Collections.singletonList(oneList1.get(2))));

        assertThat("The last line is max",
                UtilStrings.getStringsWithMaxLength(oneList2),
                equalTo(Collections.singletonList(oneList2.get(oneList2.size() - 1))));

        assertThat("There are several max lines",
                UtilStrings.getStringsWithMaxLength(moreOneList),
                is(Arrays.asList(moreOneList.get(0), moreOneList.get(3), moreOneList.get(4))));
    }

    /*Набор тестов для проверки методов getStringsOptWithMaxLength - поиск строк с максимальной длиной*/
    @Test(expected = IllegalArgumentException.class)
    public void testStringsOptWithMaxLengthWithException(){
        assertThat("Source is null",
                UtilStrings.getStringsOptWithMaxLength(nullList),
                equalTo(Collections.emptyList()));
    }

    /*Набор тестов для проверки методов getStringsOptWithMaxLength - поиск строк с максимальной длиной*/
    @Test
    public void testStringsOptWithMaxLength(){

        setUpLists();

        assertThat("Source is empty",
                UtilStrings.getStringsOptWithMaxLength(emptyList),
                equalTo(Optional.empty()));

        assertThat("Source is empty string",
                UtilStrings.getStringsOptWithMaxLength(emptyStringList),
                equalTo(Optional.of(Collections.singletonList(""))));

        assertThat("Source is one string",
                UtilStrings.getStringsOptWithMaxLength(singleList),
                equalTo(Optional.of(Collections.singletonList(singleList.get(0)))));

        assertThat("The first line is max",
                UtilStrings.getStringsOptWithMaxLength(oneList0),
                equalTo(Optional.of(Collections.singletonList(oneList0.get(0)))));

        assertThat("The middle line is max",
                UtilStrings.getStringsOptWithMaxLength(oneList1),
                equalTo(Optional.of(Collections.singletonList(oneList1.get(2)))));

        assertThat("The last line is max",
                UtilStrings.getStringsOptWithMaxLength(oneList2),
                equalTo(Optional.of(Collections.singletonList(oneList2.get(oneList2.size() - 1)))));

        assertThat("There are several max lines",
                UtilStrings.getStringsOptWithMaxLength(moreOneList),
                is(Optional.of(Arrays.asList(moreOneList.get(0), moreOneList.get(3), moreOneList.get(4)))));
    }

    /*Набор тестов для проверки метода getWordNumberOccurrences, которые генерируют исключение*/
    @Test(expected = IllegalArgumentException.class)
    public void testWordCountWithException0() {
        assertThat("Text source and search line are null",
                UtilStrings.getWordNumberOccurrences(nullList, null),
                equalTo(0L));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testWordCountWithException1() {
        assertThat("Text source is null, search line is empty",
                UtilStrings.getWordNumberOccurrences(nullList, ""),
                equalTo(0L));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testWordCountWithException2() {
        assertThat("Text source is null, search line is blank",
                UtilStrings.getWordNumberOccurrences(nullList, " "),
                equalTo(0L));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testWordCountWithException3() {
        assertThat("Text source is null, search line is one word",
                UtilStrings.getWordNumberOccurrences(nullList, "горы"),
                equalTo(0L));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testWordCountWithException4() {
        assertThat("Text source is empty, search line is null",
                UtilStrings.getWordNumberOccurrences(emptyList, null),
                equalTo(0L));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testWordCountWithException5() {
        assertThat("Text source is empty line, search line is null",
                UtilStrings.getWordNumberOccurrences(emptyStringList, null),
                equalTo(0L));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testWordCountWithException6() {
        assertThat("Text source is single word, search line is null",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList("горы"), null),
                equalTo(0L));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testWordCountWithException7() {
        assertThat("Text source is not empty, search line is null",
                UtilStrings.getWordNumberOccurrences(Arrays.asList("Самая высокая вершина крымских гор ","– гора Роман-Кош высотой 1945 м"), null),
                equalTo(0L));
    }

    /*Набор тестов для проверки метода getWordNumberOccurrences, когда источник текста пуст*/
    @Test
    public void testWordCountEmptySource(){
        assertThat("Text source is empty, search line is empty",
                UtilStrings.getWordNumberOccurrences(emptyList, ""),
                equalTo(0L));

        assertThat("Text source is empty, search line is blank",
                UtilStrings.getWordNumberOccurrences(emptyList, " "),
                equalTo(0L));

        assertThat("Text source is empty, search line is not empty",
                UtilStrings.getWordNumberOccurrences(emptyList, "горы"),
                equalTo(0L));

        assertThat("Text source is empty string, search line is empty",
                UtilStrings.getWordNumberOccurrences(emptyStringList, ""),
                equalTo(0L));

        assertThat("Text source is empty string, search line is blank",
                UtilStrings.getWordNumberOccurrences(emptyStringList, " "),
                equalTo(0L));

        assertThat("Text source is empty string, search line is not empty",
                UtilStrings.getWordNumberOccurrences(emptyStringList, "горы"),
                equalTo(0L));
    }

    /*Набор тестов для проверки метода getWordNumberOccurrences, когда источник содержит одно слово*/
    @Test
    public void testWordCountSingleStringInSource(){
        assertThat("Text source is one word, search line is empty",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList("горы"), ""),
                equalTo(0L));

        assertThat("Text source is one word, search line is blank",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList("горы"), " "),
                equalTo(0L));

        assertThat("Text source is one word, search line is not in source",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList("море"), "горы"),
                equalTo(0L));

        assertThat("Text source and search line is equals",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList("горы"), "горы"),
                equalTo(1L));
    }

    /*Набор тестов для проверки метода getWordNumberOccurrences, когда источник содержит одну строку*/
    @Test
    public void testWordCountSingleLineInSource(){
        String source = "Самая высокая гора Украинских Карпат – гора Говерла";
        assertThat("Text source is one string and search line is empty string",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList(source), ""),
                equalTo(0L));

        assertThat("Text source is one string and search line is empty blank",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList(source), " "),
                equalTo(0L));

        assertThat("Text source is one string and search line is not in source",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList(source), "горы"),
                equalTo(0L));

        assertThat("Text source is one string and search line is in source once",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList(source), "высокая"),
                equalTo(1L));

        assertThat("Text source is one string and search line is in source more than one time",
                UtilStrings.getWordNumberOccurrences(Collections.singletonList(source), "гора"),
                equalTo(2L));
    }

    /*Набор тестов для проверки метода getWordNumberOccurrences, когда источник содержит многострочный текст*/
    @Test
    public void testWordNumberOccurrences(){
        List<String> source =  Arrays.asList(
                "В Украине есть две горные системы - Карпаты и Крымские горы.",
                "Карпатские горы относительно молодые,",
                "им более 25 миллионов лет.",
                "Например, в Карпатах находится до 20%",
                "всех лесов Украины.");

        assertThat("Text source is text and search line is not in text",
                UtilStrings.getWordNumberOccurrences(source, "лето"),
                equalTo(0L));

        assertThat("Text source is text and search line is in text only once",
                UtilStrings.getWordNumberOccurrences(source, "Карпаты"),
                equalTo(1L));

        assertThat("Text source is text and search line is in text only twice",
                UtilStrings.getWordNumberOccurrences(source, "горы"),
                equalTo(2L));

        assertThat("Text source is text and search line is empty string",
                UtilStrings.getWordNumberOccurrences(source, ""),
                equalTo(0L));

        assertThat("Text source is text and search line is blank",
                UtilStrings.getWordNumberOccurrences(source, " "),
                equalTo(0L));
    }

}
