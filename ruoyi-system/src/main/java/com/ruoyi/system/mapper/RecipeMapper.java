package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.Recipe;

import java.util.List;

public interface RecipeMapper {
    List<Recipe> getAllRecipesWithCategory();
    int insertRecipe( Recipe newRecipe);
    int updateRecipe(Recipe newRecipe);
    int deleteRecipe(int id);
}