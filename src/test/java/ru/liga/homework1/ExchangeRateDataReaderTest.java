package ru.liga.homework1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeRateDataReaderTest {

    @DisplayName("Кидает исключение, когда на вход подаём несуществующий файл")
    @Test
    void shouldThrowRuntimeException() {
        var reader = new ExchangeRateDataReader();
        assertThrows(RuntimeException.class, () -> reader.readExchangeData("SomeNonexistentFile.csv"));
    }

    @DisplayName("Кидает исключение, когда на вход подаём файл с некорректным содержанием")
    @Test
    void shouldThrowNumberFormatException() {
        var reader = new ExchangeRateDataReader();
        assertThrows(NumberFormatException.class, () -> reader.readExchangeData("EUR_wrong_data.csv"));
    }

    @DisplayName("Проверяем, что в заданном файле 5 записей")
    @Test
    void checkFiveRecordsInFile() {
        var reader = new ExchangeRateDataReader();
        var data = reader.readExchangeData("EUR_5_records.csv");
        assertEquals(5, data.size());
    }
}


//    /**
//     * Аналитик попросил посмотреть есть ли и какие города с именами больше 16 символов (чтобы влезло в GUI на вебе)
//     * <p>
//     * Вывести все города, имена которых больше 16 символов, игнорировать города с пробелами в названии, отсортировать по названию
//     */
//    @Test
//    public void test() {
//        File file = new File(
//                getClass().getClassLoader().getResource("world.sqlite").getFile()
//        );

//
//        var reporter = new Reporter(new ReportBuilder(), new EmailReportSender());
//        Assertions.assertThrows(NoReportsException.class, () -> reporter.sendReports());
//
//        assertThat(world.getCountries().get(0).hasCitiesWithThreeOrLessLetterName()).isEqualTo(true);
//        assertThat(world.getCountries().get(2).hasCitiesWithThreeOrLessLetterName()).isEqualTo(false);
//
//        World world = new WorldRepository(file.getAbsolutePath()).helloWorld();
//        assertThat(world.threeOrLessLetterNameOfCityCountryCount()).isEqualTo(54);


//        World world = new WorldRepository(file.getAbsolutePath()).helloWorld();
//        List<City> cityList = world.getAllCitiesNamesWithoutWhitespacesMoreSixteenSymbolsSortedByName();
//        System.out.println(cityList);
//        assertThat(cityList.size()).isEqualTo(188);
//        assertThat(cityList.get(1).getName()).isEqualTo("Abergement-le-Petit");
//    }
