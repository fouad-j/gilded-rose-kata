package com.gildedrose;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    private GildedRose gildedRose;

    @BeforeEach
    void setUp() {
        gildedRose = new GildedRose(emptyList());
    }

    @ParameterizedTest(name = "The Quality of {0} is never negative")
    @CsvSource({
            "Aged Brie, 0",
            "Backstage passes to a TAFKAL80ETC concert, 0",
            "'Sulfuras, Hand of Ragnaros', 0",

            "Aged Brie, 10",
            "Backstage passes to a TAFKAL80ETC concert, 10",
            "'Sulfuras, Hand of Ragnaros', 10",
    })
    void should_always_be_positive_for_quality_value(String itemName, int itemQuality) {
        // GIVEN
        Item item = new Item(itemName, 1, itemQuality);

        // WHEN
        gildedRose.updateItem(item);

        // THEN
        assertThat(item.quality).isGreaterThanOrEqualTo(0);
    }

    @ParameterizedTest(name = "The Quality of {0} is never more than 50 ")
    @CsvSource({
            "Aged Brie, 50",
            "Backstage passes to a TAFKAL80ETC concert, 50",
            "'Sulfuras, Hand of Ragnaros', 50",

            "Aged Brie, 49",
            "Backstage passes to a TAFKAL80ETC concert, 49",
            "'Sulfuras, Hand of Ragnaros', 49",
    })
    void shouldnt_not_be_more_than_50_for_quality(String itemName, int itemQuality) {
        // GIVEN
        Item item = new Item(itemName, 1, itemQuality);

        // WHEN
        gildedRose.updateItem(item);

        // THEN
        assertThat(item.quality).isLessThanOrEqualTo(50);
    }

    @ParameterizedTest(name = "Aged Brie increases in Quality by 2 when it gets older")
    @CsvSource({
            "Aged Brie, -50, -51, 50, 50",
            "Aged Brie, -50, -51, 49, 50",
            "Aged Brie, -2, -3, 44, 46",
            "Aged Brie, -1, -2, 7, 9",
            "Aged Brie, 0, -1, 10, 12",
            "Aged Brie, 1, 0, 10, 11",
    })
    void should_increase_quality_by_2_when_quality_of_AgedBrie_gets_older(String itemName, int itemSellIn, int expectedSellIn, int itemQuality, int expectedQuality) {
        // GIVEN
        Item item = new Item(itemName, itemSellIn, itemQuality);

        // WHEN
        gildedRose.updateItem(item);

        // THEN
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(item.quality).isEqualTo(expectedQuality);
        soft.assertThat(item.sellIn).isEqualTo(expectedSellIn);
        soft.assertAll();
    }


    @ParameterizedTest(name = "Aged Brie increases in Quality by 1 when it didnt get older yet")
    @CsvSource({
            "Aged Brie, 1, 0, 49, 50",
            "Aged Brie, 2, 1, 20, 21",
            "Aged Brie, 7, 6, 35, 36",
            "Aged Brie, 10, 9, 11, 12",
            "Aged Brie, 15, 14, 50, 50",
            "Aged Brie, 50, 49, 10, 11",
    })
    void should_increase_quality_by_1_when_quality_of_AgedBrie_gets_older(String itemName, int itemSellIn, int expectedSellIn, int itemQuality, int expectedQuality) {
        // GIVEN
        Item item = new Item(itemName, itemSellIn, itemQuality);

        // WHEN
        gildedRose.updateItem(item);
        System.out.println(item);

        // THEN
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(item.quality).isEqualTo(expectedQuality);
        soft.assertThat(item.sellIn).isEqualTo(expectedSellIn);
        soft.assertAll();
    }
}
