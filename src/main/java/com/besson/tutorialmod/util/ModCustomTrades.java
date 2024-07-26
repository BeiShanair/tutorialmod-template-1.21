package com.besson.tutorialmod.util;

import com.besson.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

public class ModCustomTrades {
    public static void registerModCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
            factories.add(new TradeOffers.BuyItemFactory(ModItems.CORN, 5, 12, 5, 2));
            factories.add(new TradeOffers.SellItemFactory(ModItems.CORN_SEEDS, 1, 12, 5, 2, 0.5f));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, factories -> {
            factories.add(new TradeOffers.BuyItemFactory(ModItems.STRAWBERRY, 5, 12, 5, 2));
            factories.add(new TradeOffers.ProcessItemFactory(Items.MILK_BUCKET, 1, 2, ModItems.CHEESE, 3, 16, 1, 0.5f));
        });
    }
}
