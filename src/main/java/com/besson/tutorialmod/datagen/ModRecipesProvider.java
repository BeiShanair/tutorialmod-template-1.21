package com.besson.tutorialmod.datagen;

import com.besson.tutorialmod.block.ModBlocks;
import com.besson.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

// 1.21.2 ~ 1.21.3的类引用
//import net.minecraft.data.server.recipe.RecipeExporter;
//import net.minecraft.data.server.recipe.RecipeGenerator;

// 1.21.4+的类引用
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;

public class ModRecipesProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> ICE_ETHER = List.of(ModItems.RAW_ICE_ETHER, Items.ICE);
    public ModRecipesProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    // 1.21.2+
    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.ICE_ETHER,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_BLOCK);

                offerSmelting(ICE_ETHER, RecipeCategory.MISC, ModItems.ICE_ETHER,
                        0.7f, 200, "ice_ether");
                offerBlasting(ICE_ETHER, RecipeCategory.MISC, ModItems.ICE_ETHER,
                        0.7f, 100, "ice_ether");
        
                offerFoodCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new,
                        600, ModItems.RAW_ICE_ETHER, ModItems.ICE_ETHER, 0.35f);
        
                createShaped(RecipeCategory.MISC, Items.SUGAR,3)
                        .pattern("###")
                        .input('#', Ingredient.ofItems(Items.BEETROOT))
                        .criterion("has_item", conditionsFromItem(Items.BEETROOT))
                        .offerTo(recipeExporter);
        
                createShapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_ORE)
                        .input(ModItems.RAW_ICE_ETHER)
                        .input(Items.STONE)
                        .criterion("has_item", conditionsFromItem(ModItems.RAW_ICE_ETHER))
                        .criterion("has_item", conditionsFromItem(Items.STONE))
                        .offerTo(recipeExporter);
            }
        };
    }

    // 1.21.2+

    @Override
    public String getName() {
        return "Recipe Gen";
    }

    // 1.21 ~ 1.21.1
//    @Override
//    public void generate(RecipeExporter exporter) {
//        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.ICE_ETHER,
//                RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_BLOCK);
//
//        offerSmelting(exporter, ICE_ETHER, RecipeCategory.MISC, ModItems.ICE_ETHER,
//                0.7f, 200, "ice_ether");
//        offerBlasting(exporter, ICE_ETHER, RecipeCategory.MISC, ModItems.ICE_ETHER,
//                0.7f, 100, "ice_ether");
//
//        offerFoodCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new,
//                600, ModItems.RAW_ICE_ETHER, ModItems.ICE_ETHER, 0.35f);
//
//        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.SUGAR,3)
//                .pattern("###")
//                .input('#', Ingredient.ofItems(Items.BEETROOT))
//                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.BEETROOT))
//                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "beetroot_to_sugar"));
//
//        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_ORE)
//                .input(ModItems.RAW_ICE_ETHER)
//                .input(Items.STONE)
//                .criterion("has_item", RecipeProvider.conditionsFromItem(ModItems.RAW_ICE_ETHER))
//                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.STONE))
//                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "ice_ether_ore"));
//
//    }
}
