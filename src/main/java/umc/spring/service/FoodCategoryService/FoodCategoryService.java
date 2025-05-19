package umc.spring.service.FoodCategoryService;

import java.util.List;

public interface FoodCategoryService {
    boolean doAllCategoriesExist(List<Long> categoryIds);
}
