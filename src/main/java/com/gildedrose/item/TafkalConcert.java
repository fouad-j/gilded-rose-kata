package com.gildedrose.item;

import com.gildedrose.Item;
import com.gildedrose.ItemRule;

public class TafkalConcert implements ItemRule {
    @Override
    public void updateItem(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (item.sellIn < 11) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }

            if (item.sellIn < 6) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }

        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }
}
