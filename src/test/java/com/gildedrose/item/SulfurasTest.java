package com.gildedrose.item;

import com.gildedrose.Item;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SulfurasTest {

    Sulfuras sulfuras;

    @BeforeEach
    void setUp() {
        sulfuras = new Sulfuras();
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
        sulfuras.updateItem(item);

        // THEN
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(item.quality).isEqualTo(expectedQuality);
        soft.assertThat(item.sellIn).isEqualTo(expectedSellIn);
        soft.assertAll();
    }
}
