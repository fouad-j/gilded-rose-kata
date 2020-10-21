package com.gildedrose;

import com.gildedrose.item.AgedBrie;
import com.gildedrose.item.TafkalConcert;

import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;

class GildedRose {
    private final List<Item> items;

    private final Map<String, ItemRule> itemsRules = Map.of(
            "Backstage passes to a TAFKAL80ETC concert", new TafkalConcert(),
            "Aged Brie", new AgedBrie()
    );

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateQuality() {
        items.forEach(this::updateItem);
    }

    public void updateItem(Item item) {
        ofNullable(itemsRules.get(item.name))
                .ifPresent(itemRule -> itemRule.updateItem(item));
    }
}

