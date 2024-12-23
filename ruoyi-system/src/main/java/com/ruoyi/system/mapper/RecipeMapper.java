package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.Recipe;

import java.util.List;

public interface RecipeMapper {
    List<Recipe> getAllRecipesWithCategory();
}