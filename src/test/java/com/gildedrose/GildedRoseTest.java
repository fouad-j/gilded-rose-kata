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

        // THEN
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(item.quality).isEqualTo(expectedQuality);
        soft.assertThat(item.sellIn).isEqualTo(expectedSellIn);
        soft.assertAll();
    }

    @ParameterizedTest(name = "sulfuras never decrease or in quality")
    @CsvSource({
            "'Sulfuras, Hand of Ragnaros', 10, 10, -5, -5",
            "'Sulfuras, Hand of Ragnaros', 5, 5, -1, -1",
            "'Sulfuras, Hand of Ragnaros', 10, 10, -0, -0",
            "'Sulfuras, Hand of Ragnaros', 8, 8, -1, -1",
            "'Sulfuras, Hand of Ragnaros', 0, 0, 5, 5",
            "'Sulfuras, Hand of Ragnaros', 10, 10, 8, 8",
            "'Sulfuras, Hand of Ragnaros', -10, -10, 12, 12",
    })
    void should_never_decrease_quality_of_sulfuras(String itemName, int itemSellIn, int expectedSellIn, int itemQuality, int expectedQuality) {
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

    @ParameterizedTest(name = "Backstage passes quality decrease by 1 when sellin is greater than 11")
    @CsvSource({
            "Backstage passes to a TAFKAL80ETC concert, 11, -50, -49",
            "Backstage passes to a TAFKAL80ETC concert, 11, -5, -4",
            "Backstage passes to a TAFKAL80ETC concert, 11, -1, 0",
            "Backstage passes to a TAFKAL80ETC concert, 11, 0, 1",
            "Backstage passes to a TAFKAL80ETC concert, 11, 1, 2",
            "Backstage passes to a TAFKAL80ETC concert, 11, 5, 6",
            "Backstage passes to a TAFKAL80ETC concert, 11, 6, 7",
            "Backstage passes to a TAFKAL80ETC concert, 11, 49, 50",
            "Backstage passes to a TAFKAL80ETC concert, 11, 50, 50",
    })
    void should_decrease_quality_by_one_for_backstagePasses_when_sellin_is_gt_11(String itemName, int itemSellIn, int itemQuality, int expectedQuality) {
        // GIVEN
        Item item = new Item(itemName, itemSellIn, itemQuality);

        // WHEN
        gildedRose.updateItem(item);

        // THEN
        assertThat(item.quality).isEqualTo(expectedQuality);
    }

    @ParameterizedTest(name = "Backstage passes quality decrease by 3 when sellin is less than 6")
    @CsvSource({
            "Backstage passes to a TAFKAL80ETC concert, 5, -50, -47",
            "Backstage passes to a TAFKAL80ETC concert, 5, -5, -2",
            "Backstage passes to a TAFKAL80ETC concert, 5, -1, 2",
            "Backstage passes to a TAFKAL80ETC concert, 5, 0, 3",
            "Backstage passes to a TAFKAL80ETC concert, 5, 1, 4",
            "Backstage passes to a TAFKAL80ETC concert, 5, 5, 8",
            "Backstage passes to a TAFKAL80ETC concert, 5, 6, 9",
            "Backstage passes to a TAFKAL80ETC concert, 5, 49, 50",
            "Backstage passes to a TAFKAL80ETC concert, 5, 50, 50",
    })
    void should_decrease_quality_by_3_for_backstagePasses_when_sellin_is_lt_5(String itemName, int itemSellIn, int itemQuality, int expectedQuality) {
        // GIVEN
        Item item = new Item(itemName, itemSellIn, itemQuality);

        // WHEN
        gildedRose.updateItem(item);
        System.out.println(item);

        // THEN
        assertThat(item.quality).isEqualTo(expectedQuality);
    }

    @ParameterizedTest(name = "Backstage passes quality decrease by 2 when sellin is less than 10")
    @CsvSource({
            "Backstage passes to a TAFKAL80ETC concert, 10, -50, -48",
            "Backstage passes to a TAFKAL80ETC concert, 10, -5, -3",
            "Backstage passes to a TAFKAL80ETC concert, 10, -1, 1",
            "Backstage passes to a TAFKAL80ETC concert, 10, 0, 2",
            "Backstage passes to a TAFKAL80ETC concert, 10, 1, 3",
            "Backstage passes to a TAFKAL80ETC concert, 10, 5, 7",
            "Backstage passes to a TAFKAL80ETC concert, 10, 6, 8",
            "Backstage passes to a TAFKAL80ETC concert, 10, 49, 50",
            "Backstage passes to a TAFKAL80ETC concert, 10, 50, 50",
    })
    void should_decrease_quality_by_2_for_backstagePasses_when_sellin_is_lt_10(String itemName, int itemSellIn, int itemQuality, int expectedQuality) {
        // GIVEN
        Item item = new Item(itemName, itemSellIn, itemQuality);

        // WHEN
        gildedRose.updateItem(item);
        System.out.println(item);

        // THEN
        assertThat(item.quality).isEqualTo(expectedQuality);
    }

    @ParameterizedTest(name = "Backstage passes quality drops to 0 after concert")
    @CsvSource({
            "Backstage passes to a TAFKAL80ETC concert, -1, -50, 0",
            "Backstage passes to a TAFKAL80ETC concert, -1, -5, 0",
            "Backstage passes to a TAFKAL80ETC concert, -1, -1, 0",
            "Backstage passes to a TAFKAL80ETC concert, -1, 0, 0",
            "Backstage passes to a TAFKAL80ETC concert, -1, 1, 0",
            "Backstage passes to a TAFKAL80ETC concert, -1, 5, 0",
            "Backstage passes to a TAFKAL80ETC concert, -1, 6, 0",
            "Backstage passes to a TAFKAL80ETC concert, -1, 49, 0",
            "Backstage passes to a TAFKAL80ETC concert, -1, 50, 0",
    })
    void should_backstage_quality_be_zero_after_when_sellin_is_negative(String itemName, int itemSellIn, int itemQuality, int expectedQuality) {
        // GIVEN
        Item item = new Item(itemName, itemSellIn, itemQuality);

        // WHEN
        gildedRose.updateItem(item);
        System.out.println(item);

        // THEN
        assertThat(item.quality).isEqualTo(expectedQuality);
    }

}
