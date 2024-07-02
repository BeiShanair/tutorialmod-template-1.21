package com.besson.tutorialmod.datagen;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.block.ModBlocks;
import com.besson.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> ICE_ETHER = List.of(ModItems.RAW_ICE_ETHER, Items.ICE);
    public ModRecipesProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.ICE_ETHER,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_BLOCK);

        offerSmelting(exporter, ICE_ETHER, RecipeCategory.MISC, ModItems.ICE_ETHER,
                0.7f, 200, "ice_ether");
        offerBlasting(exporter, ICE_ETHER, RecipeCategory.MISC, ModItems.ICE_ETHER,
                0.7f, 100, "ice_ether");

        offerFoodCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new,
                600, ModItems.RAW_ICE_ETHER, ModItems.ICE_ETHER, 0.35f);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.SUGAR,3)
                .pattern("###")
                .input('#', Ingredient.ofItems(Items.BEETROOT))
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.BEETROOT))
                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "beetroot_to_sugar"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_ORE)
                .input(ModItems.RAW_ICE_ETHER)
                .input(Items.STONE)
                .criterion("has_item", RecipeProvider.conditionsFromItem(ModItems.RAW_ICE_ETHER))
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.STONE))
                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "ice_ether_ore"));

    }
}
