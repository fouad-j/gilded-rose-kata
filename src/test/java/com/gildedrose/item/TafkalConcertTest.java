package com.gildedrose.item;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class TafkalConcertTest {

    TafkalConcert tafkalConcert;

    @BeforeEach
    void setUp() {
        tafkalConcert = new TafkalConcert();
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
        tafkalConcert.updateItem(item);

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
        tafkalConcert.updateItem(item);

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
        tafkalConcert.updateItem(item);

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
        tafkalConcert.updateItem(item);

        // THEN
        assertThat(item.quality).isEqualTo(expectedQuality);
    }
}
