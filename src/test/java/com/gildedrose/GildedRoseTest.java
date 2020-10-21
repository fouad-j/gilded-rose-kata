package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
}
