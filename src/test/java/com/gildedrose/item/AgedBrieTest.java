package com.gildedrose.item;

import com.gildedrose.Item;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class AgedBrieTest {

    AgedBrie agedBrie;

    @BeforeEach
    void setUp() {
        agedBrie = new AgedBrie();
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
        agedBrie.updateItem(item);

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
        agedBrie.updateItem(item);

        // THEN
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(item.quality).isEqualTo(expectedQuality);
        soft.assertThat(item.sellIn).isEqualTo(expectedSellIn);
        soft.assertAll();
    }

}
