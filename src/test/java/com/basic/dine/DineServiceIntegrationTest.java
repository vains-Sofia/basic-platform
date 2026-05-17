package com.basic.dine;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.basic.domain.entity.DineAttributeGroup;
import com.basic.domain.entity.DineAttributeOption;
import com.basic.domain.entity.DineCategory;
import com.basic.domain.entity.DineDish;
import com.basic.domain.entity.DineDishAttributeGroup;
import com.basic.domain.entity.DineDishAttributeOption;
import com.basic.domain.entity.DineStore;
import com.basic.domain.entity.DineTableBinding;
import com.basic.domain.entity.DineTableInfo;
import com.basic.domain.model.BasicUserDetails;
import com.basic.domain.request.DineAttributeGroupRequest;
import com.basic.domain.request.DineAttributeOptionRequest;
import com.basic.domain.request.DineCategoryRequest;
import com.basic.domain.request.DineDishAttributeGroupRequest;
import com.basic.domain.request.DineDishAttributeOptionRequest;
import com.basic.domain.request.DineDishRequest;
import com.basic.domain.request.DineTableBindingRequest;
import com.basic.domain.request.DineTableInfoRequest;
import com.basic.domain.request.DineStoreRequest;
import com.basic.domain.response.FindDineAttributeGroupResponse;
import com.basic.domain.response.FindDineAttributeOptionResponse;
import com.basic.domain.response.FindDineCategoryResponse;
import com.basic.domain.response.FindDineDishAttributeGroupResponse;
import com.basic.domain.response.FindDineDishAttributeOptionResponse;
import com.basic.domain.response.FindDineDishResponse;
import com.basic.domain.response.FindDineTableBindingResponse;
import com.basic.domain.response.FindDineTableInfoResponse;
import com.basic.domain.response.FindDineInfoResponse;
import com.basic.enums.RecommendEnum;
import com.basic.enums.SelectTypeEnum;
import com.basic.enums.StatusEnum;
import com.basic.enums.TableStatusEnum;
import com.basic.exception.CloudServiceException;
import com.basic.mapper.DineAttributeGroupMapper;
import com.basic.mapper.DineAttributeOptionMapper;
import com.basic.mapper.DineCategoryMapper;
import com.basic.mapper.DineDishAttributeGroupMapper;
import com.basic.mapper.DineDishAttributeOptionMapper;
import com.basic.mapper.DineDishMapper;
import com.basic.mapper.DineStoreMapper;
import com.basic.mapper.DineTableBindingMapper;
import com.basic.mapper.DineTableInfoMapper;
import com.basic.service.DineAttributeGroupService;
import com.basic.service.DineAttributeOptionService;
import com.basic.service.DineCategoryService;
import com.basic.service.DineDishAttributeGroupService;
import com.basic.service.DineDishAttributeOptionService;
import com.basic.service.DineDishService;
import com.basic.service.DineStoreService;
import com.basic.service.DineTableBindingService;
import com.basic.service.DineTableInfoService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DineServiceIntegrationTest {

    private static final Long LOGIN_USER_ID = 900000000000000001L;

    private static final Long REQUEST_USER_ID = 900000000000000002L;

    private final String prefix = "IT" + Long.toString(System.currentTimeMillis(), 36) + "_";

    @Autowired
    private DineStoreService dineStoreService;

    @Autowired
    private DineCategoryService dineCategoryService;

    @Autowired
    private DineDishService dineDishService;

    @Autowired
    private DineAttributeGroupService dineAttributeGroupService;

    @Autowired
    private DineAttributeOptionService dineAttributeOptionService;

    @Autowired
    private DineDishAttributeGroupService dineDishAttributeGroupService;

    @Autowired
    private DineDishAttributeOptionService dineDishAttributeOptionService;

    @Autowired
    private DineTableInfoService dineTableInfoService;

    @Autowired
    private DineTableBindingService dineTableBindingService;

    @Autowired
    private DineStoreMapper dineStoreMapper;

    @Autowired
    private DineCategoryMapper dineCategoryMapper;

    @Autowired
    private DineDishMapper dineDishMapper;

    @Autowired
    private DineAttributeGroupMapper dineAttributeGroupMapper;

    @Autowired
    private DineAttributeOptionMapper dineAttributeOptionMapper;

    @Autowired
    private DineDishAttributeGroupMapper dineDishAttributeGroupMapper;

    @Autowired
    private DineDishAttributeOptionMapper dineDishAttributeOptionMapper;

    @Autowired
    private DineTableInfoMapper dineTableInfoMapper;

    @Autowired
    private DineTableBindingMapper dineTableBindingMapper;

    private Long storeAId;

    private Long storeBId;

    private Long categoryAId;

    private Long categoryBId;

    private Long groupAId;

    private Long groupBId;

    private Long groupBoundOnlyId;

    private Long groupCascadeId;

    private Long optionAId;

    private Long optionBId;

    private Long optionCascadeId;

    private Long referencedDishId;

    private Long cascadeDishId;

    private Long tableAId;

    private String categoryAName;

    private String dishRefName;

    private String groupAName;

    private String optionAName;

    private String tableAName;

    @BeforeAll
    void cleanBeforeAll() {
        cleanFixtures();
    }

    @BeforeEach
    void setLoginUser() {
        BasicUserDetails userDetails = new BasicUserDetails();
        userDetails.setId(LOGIN_USER_ID);
        userDetails.setUsername("dine-it-user");
        userDetails.setNickname("餐饮集成测试用户");
        userDetails.setAuthorities(Collections.emptyList());
        UsernamePasswordAuthenticationToken authentication = UsernamePasswordAuthenticationToken.authenticated(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @AfterEach
    void clearLoginUser() {
        SecurityContextHolder.clearContext();
    }

    @AfterAll
    void cleanAfterAll() {
        cleanFixtures();
    }

    @Test
    @Order(1)
    void createStoreFixtures() {
        FindDineInfoResponse storeA = dineStoreService.create(storeRequest(name("STORE_A")));
        FindDineInfoResponse storeB = dineStoreService.create(storeRequest(name("STORE_B")));

        storeAId = storeA.getId();
        storeBId = storeB.getId();

        assertNotNull(storeAId);
        assertNotNull(storeBId);
        assertEquals(StatusEnum.ENABLE, dineStoreMapper.selectById(storeAId).getStatus());
        assertEquals(StatusEnum.ENABLE, dineStoreMapper.selectById(storeBId).getStatus());
    }

    @Test
    @Order(2)
    void createCategoryFixtures() {
        categoryAName = name("CATEGORY_A");
        FindDineCategoryResponse categoryA = dineCategoryService.create(categoryRequest(storeAId, categoryAName));
        FindDineCategoryResponse categoryB = dineCategoryService.create(categoryRequest(storeBId, name("CATEGORY_B")));

        categoryAId = categoryA.getId();
        categoryBId = categoryB.getId();

        assertNotNull(categoryAId);
        assertNotNull(categoryBId);
        assertEquals(storeAId, dineCategoryMapper.selectById(categoryAId).getStoreId());
        assertEquals(storeBId, dineCategoryMapper.selectById(categoryBId).getStoreId());
    }

    @Test
    @Order(3)
    void createAttributeGroupFixtures() {
        groupAName = name("GROUP_A");
        FindDineAttributeGroupResponse groupA = dineAttributeGroupService.create(attributeGroupRequest(storeAId, groupAName));
        FindDineAttributeGroupResponse groupB = dineAttributeGroupService.create(attributeGroupRequest(storeBId, name("GROUP_B")));
        FindDineAttributeGroupResponse groupBoundOnly = dineAttributeGroupService.create(attributeGroupRequest(storeAId, name("GROUP_BOUND_ONLY")));
        FindDineAttributeGroupResponse groupCascade = dineAttributeGroupService.create(attributeGroupRequest(storeAId, name("GROUP_CASCADE")));

        groupAId = groupA.getId();
        groupBId = groupB.getId();
        groupBoundOnlyId = groupBoundOnly.getId();
        groupCascadeId = groupCascade.getId();

        assertNotNull(groupAId);
        assertNotNull(groupBId);
        assertNotNull(groupBoundOnlyId);
        assertNotNull(groupCascadeId);
        assertEquals(storeAId, dineAttributeGroupMapper.selectById(groupAId).getStoreId());
        assertEquals(storeBId, dineAttributeGroupMapper.selectById(groupBId).getStoreId());
        assertEquals(storeAId, dineAttributeGroupMapper.selectById(groupBoundOnlyId).getStoreId());
        assertEquals(storeAId, dineAttributeGroupMapper.selectById(groupCascadeId).getStoreId());
    }

    @Test
    @Order(4)
    void createAttributeOptionFixtures() {
        optionAName = name("OPTION_A");
        FindDineAttributeOptionResponse optionA = dineAttributeOptionService.create(attributeOptionRequest(groupAId, optionAName));
        FindDineAttributeOptionResponse optionB = dineAttributeOptionService.create(attributeOptionRequest(groupBId, name("OPTION_B")));
        FindDineAttributeOptionResponse optionCascade = dineAttributeOptionService.create(attributeOptionRequest(groupCascadeId, name("OPTION_CASCADE")));

        optionAId = optionA.getId();
        optionBId = optionB.getId();
        optionCascadeId = optionCascade.getId();

        assertNotNull(optionAId);
        assertNotNull(optionBId);
        assertNotNull(optionCascadeId);
        assertEquals(groupAId, dineAttributeOptionMapper.selectById(optionAId).getGroupId());
        assertEquals(groupBId, dineAttributeOptionMapper.selectById(optionBId).getGroupId());
        assertEquals(groupCascadeId, dineAttributeOptionMapper.selectById(optionCascadeId).getGroupId());
    }

    @Test
    @Order(5)
    void createDishFixtures() {
        dishRefName = name("DISH_REF");
        FindDineDishResponse referencedDish = dineDishService.create(dishRequest(storeAId, categoryAId, dishRefName));
        FindDineDishResponse cascadeDish = dineDishService.create(dishRequest(storeAId, categoryAId, name("DISH_CASCADE")));

        referencedDishId = referencedDish.getId();
        cascadeDishId = cascadeDish.getId();

        assertNotNull(referencedDishId);
        assertNotNull(cascadeDishId);
        assertEquals(storeAId, dineDishMapper.selectById(referencedDishId).getStoreId());
        assertEquals(categoryAId, dineDishMapper.selectById(referencedDishId).getCategoryId());
        assertEquals(storeAId, dineDishMapper.selectById(cascadeDishId).getStoreId());
        assertEquals(categoryAId, dineDishMapper.selectById(cascadeDishId).getCategoryId());
    }

    @Test
    @Order(6)
    void createTableFixture() {
        tableAName = name("TABLE_A");
        FindDineTableInfoResponse tableA = dineTableInfoService.create(tableInfoRequest(storeAId, tableAName, name("TABLE_CODE_A")));

        tableAId = tableA.getId();

        assertNotNull(tableAId);
        assertEquals(storeAId, dineTableInfoMapper.selectById(tableAId).getStoreId());
    }

    @Test
    @Order(7)
    void createDishAttributeRelationFixtures() {
        FindDineDishAttributeGroupResponse boundOnlyRelation = dineDishAttributeGroupService.create(
                dishAttributeGroupRequest(referencedDishId, groupBoundOnlyId));
        FindDineDishAttributeGroupResponse cascadeGroupRelation = dineDishAttributeGroupService.create(
                dishAttributeGroupRequest(cascadeDishId, groupCascadeId));
        FindDineDishAttributeOptionResponse cascadeOptionRelation = dineDishAttributeOptionService.create(
                dishAttributeOptionRequest(cascadeDishId, optionCascadeId));

        assertNotNull(boundOnlyRelation.getId());
        assertNotNull(cascadeGroupRelation.getId());
        assertNotNull(cascadeOptionRelation.getId());
        assertEquals(1L, countDishAttributeGroups(referencedDishId, groupBoundOnlyId));
        assertEquals(1L, countDishAttributeGroups(cascadeDishId, groupCascadeId));
        assertEquals(1L, countDishAttributeOptions(cascadeDishId, optionCascadeId));
    }

    @Test
    @Order(8)
    void createDishWithOtherStoreCategoryShouldFail() {
        String dishName = name("DISH_OTHER_STORE_CATEGORY");
        CloudServiceException exception = assertThrows(CloudServiceException.class,
                () -> dineDishService.create(dishRequest(storeAId, categoryBId, dishName)));

        assertTrue(exception.getMessage().contains("菜品所属门店必须与分类所属门店一致"));
        assertEquals(0L, countDishesByStoreAndName(storeAId, dishName));
    }

    @Test
    @Order(9)
    void bindDishToOtherStoreAttributeGroupShouldFail() {
        CloudServiceException exception = assertThrows(CloudServiceException.class,
                () -> dineDishAttributeGroupService.create(dishAttributeGroupRequest(referencedDishId, groupBId)));

        assertTrue(exception.getMessage().contains("菜品与属性组必须属于同一门店"));
        assertEquals(0L, countDishAttributeGroups(referencedDishId, groupBId));
    }

    @Test
    @Order(10)
    void bindDishToOtherStoreAttributeOptionShouldFail() {
        CloudServiceException exception = assertThrows(CloudServiceException.class,
                () -> dineDishAttributeOptionService.create(dishAttributeOptionRequest(referencedDishId, optionBId)));

        assertTrue(exception.getMessage().contains("菜品与属性选项所属属性组必须属于同一门店"));
        assertEquals(0L, countDishAttributeOptions(referencedDishId, optionBId));
    }

    @Test
    @Order(11)
    void createDuplicateCategoryInSameStoreShouldFail() {
        CloudServiceException exception = assertThrows(CloudServiceException.class,
                () -> dineCategoryService.create(categoryRequest(storeAId, categoryAName)));

        assertTrue(exception.getMessage().contains("同一门店下分类名称已存在"));
        assertEquals(1L, countCategoriesByStoreAndName(storeAId, categoryAName));
    }

    @Test
    @Order(12)
    void createDuplicateDishInSameStoreShouldFail() {
        CloudServiceException exception = assertThrows(CloudServiceException.class,
                () -> dineDishService.create(dishRequest(storeAId, categoryAId, dishRefName)));

        assertTrue(exception.getMessage().contains("同一门店下菜品名称已存在"));
        assertEquals(1L, countDishesByStoreAndName(storeAId, dishRefName));
    }

    @Test
    @Order(13)
    void createDuplicateAttributeGroupInSameStoreShouldFail() {
        CloudServiceException exception = assertThrows(CloudServiceException.class,
                () -> dineAttributeGroupService.create(attributeGroupRequest(storeAId, groupAName)));

        assertTrue(exception.getMessage().contains("同一门店下属性组名称已存在"));
        assertEquals(1L, countAttributeGroupsByStoreAndName(storeAId, groupAName));
    }

    @Test
    @Order(14)
    void createDuplicateAttributeOptionInSameGroupShouldFail() {
        CloudServiceException exception = assertThrows(CloudServiceException.class,
                () -> dineAttributeOptionService.create(attributeOptionRequest(groupAId, optionAName)));

        assertTrue(exception.getMessage().contains("同一属性组下选项名称已存在"));
        assertEquals(1L, countAttributeOptionsByGroupAndName(groupAId, optionAName));
    }

    @Test
    @Order(15)
    void createDuplicateTableInSameStoreShouldFail() {
        CloudServiceException exception = assertThrows(CloudServiceException.class,
                () -> dineTableInfoService.create(tableInfoRequest(storeAId, tableAName, name("TABLE_CODE_DUPLICATE_NAME"))));

        assertTrue(exception.getMessage().contains("同一门店下桌台名称已存在"));
        assertEquals(1L, countTablesByStoreAndName(storeAId, tableAName));
    }

    @Test
    @Order(16)
    void deleteCategoryReferencedByDishShouldFail() {
        CloudServiceException exception = assertThrows(CloudServiceException.class,
                () -> dineCategoryService.delete(categoryAId));

        assertTrue(exception.getMessage().contains("分类下存在菜品，不能删除"));
        assertNotNull(dineCategoryMapper.selectById(categoryAId));
        assertNotNull(dineDishMapper.selectById(referencedDishId));
    }

    @Test
    @Order(17)
    void deleteAttributeGroupReferencedByOptionShouldFail() {
        CloudServiceException exception = assertThrows(CloudServiceException.class,
                () -> dineAttributeGroupService.delete(groupAId));

        assertTrue(exception.getMessage().contains("属性组下存在选项，不能删除"));
        assertNotNull(dineAttributeGroupMapper.selectById(groupAId));
        assertNotNull(dineAttributeOptionMapper.selectById(optionAId));
    }

    @Test
    @Order(18)
    void deleteAttributeGroupReferencedByDishBindingShouldFail() {
        CloudServiceException exception = assertThrows(CloudServiceException.class,
                () -> dineAttributeGroupService.delete(groupBoundOnlyId));

        assertTrue(exception.getMessage().contains("属性组已被菜品绑定，不能删除"));
        assertNotNull(dineAttributeGroupMapper.selectById(groupBoundOnlyId));
        assertEquals(1L, countDishAttributeGroups(referencedDishId, groupBoundOnlyId));
    }

    @Test
    @Order(19)
    void deleteDishShouldCascadeDeleteDishAttributeRelations() {
        assertEquals(1L, countDishAttributeGroups(cascadeDishId, groupCascadeId));
        assertEquals(1L, countDishAttributeOptions(cascadeDishId, optionCascadeId));

        dineDishService.delete(cascadeDishId);

        assertNull(dineDishMapper.selectById(cascadeDishId));
        assertEquals(0L, countDishAttributeGroupsByDish(cascadeDishId));
        assertEquals(0L, countDishAttributeOptionsByDish(cascadeDishId));
    }

    @Test
    @Order(20)
    void createTableBindingShouldIgnoreRequestUserIdAndUseCurrentLoginUser() {
        FindDineTableBindingResponse response = dineTableBindingService.create(tableBindingRequest(tableAId));

        Long firstBindingId = response.getId();
        DineTableBinding binding = dineTableBindingMapper.selectById(firstBindingId);

        assertNotNull(firstBindingId);
        assertEquals(LOGIN_USER_ID, response.getUserId());
        assertNotEquals(REQUEST_USER_ID, response.getUserId());
        assertEquals(LOGIN_USER_ID, binding.getUserId());
        assertNotEquals(REQUEST_USER_ID, binding.getUserId());
        assertEquals(StatusEnum.ENABLE, binding.getStatus());
        assertNotNull(binding.getBindTime());
        assertNull(binding.getUnbindTime());
    }

    @Test
    @Order(21)
    void createSecondActiveTableBindingForSameTableShouldFail() {
        CloudServiceException exception = assertThrows(CloudServiceException.class,
                () -> dineTableBindingService.create(tableBindingRequest(tableAId)));

        assertTrue(exception.getMessage().contains("该桌台已存在绑定中的记录，不能重复绑定"));
        assertEquals(1L, dineTableBindingMapper.selectCount(Wrappers.lambdaQuery(DineTableBinding.class)
                .eq(DineTableBinding::getTableId, tableAId)
                .eq(DineTableBinding::getStatus, StatusEnum.ENABLE)));
    }

    private void cleanFixtures() {
        List<Long> storeIds = dineStoreMapper.selectList(Wrappers.lambdaQuery(DineStore.class)
                        .likeRight(DineStore::getName, prefix))
                .stream()
                .map(DineStore::getId)
                .toList();
        if (storeIds.isEmpty()) {
            return;
        }

        List<Long> dishIds = dineDishMapper.selectList(Wrappers.lambdaQuery(DineDish.class)
                        .in(DineDish::getStoreId, storeIds))
                .stream()
                .map(DineDish::getId)
                .toList();
        List<Long> groupIds = dineAttributeGroupMapper.selectList(Wrappers.lambdaQuery(DineAttributeGroup.class)
                        .in(DineAttributeGroup::getStoreId, storeIds))
                .stream()
                .map(DineAttributeGroup::getId)
                .toList();
        List<Long> optionIds = groupIds.isEmpty() ? List.of() : dineAttributeOptionMapper.selectList(
                        Wrappers.lambdaQuery(DineAttributeOption.class).in(DineAttributeOption::getGroupId, groupIds))
                .stream()
                .map(DineAttributeOption::getId)
                .toList();
        List<Long> tableIds = dineTableInfoMapper.selectList(Wrappers.lambdaQuery(DineTableInfo.class)
                        .in(DineTableInfo::getStoreId, storeIds))
                .stream()
                .map(DineTableInfo::getId)
                .toList();

        if (!tableIds.isEmpty()) {
            dineTableBindingMapper.delete(Wrappers.lambdaQuery(DineTableBinding.class)
                    .in(DineTableBinding::getTableId, tableIds));
        }
        if (!dishIds.isEmpty()) {
            dineDishAttributeOptionMapper.delete(Wrappers.lambdaQuery(DineDishAttributeOption.class)
                    .in(DineDishAttributeOption::getDishId, dishIds));
            dineDishAttributeGroupMapper.delete(Wrappers.lambdaQuery(DineDishAttributeGroup.class)
                    .in(DineDishAttributeGroup::getDishId, dishIds));
            dineDishMapper.deleteByIds(dishIds);
        }
        if (!optionIds.isEmpty()) {
            dineDishAttributeOptionMapper.delete(Wrappers.lambdaQuery(DineDishAttributeOption.class)
                    .in(DineDishAttributeOption::getOptionId, optionIds));
            dineAttributeOptionMapper.deleteByIds(optionIds);
        }
        if (!groupIds.isEmpty()) {
            dineDishAttributeGroupMapper.delete(Wrappers.lambdaQuery(DineDishAttributeGroup.class)
                    .in(DineDishAttributeGroup::getGroupId, groupIds));
            dineAttributeGroupMapper.deleteByIds(groupIds);
        }
        if (!tableIds.isEmpty()) {
            dineTableInfoMapper.deleteByIds(tableIds);
        }
        dineCategoryMapper.delete(Wrappers.lambdaQuery(DineCategory.class)
                .in(DineCategory::getStoreId, storeIds));
        dineStoreMapper.deleteByIds(storeIds);
    }

    private DineStoreRequest storeRequest(String name) {
        DineStoreRequest request = new DineStoreRequest();
        request.setName(name);
        request.setLogo("");
        request.setStatus(StatusEnum.ENABLE);
        return request;
    }

    private DineCategoryRequest categoryRequest(Long storeId, String name) {
        DineCategoryRequest request = new DineCategoryRequest();
        request.setStoreId(storeId);
        request.setName(name);
        request.setSort(0);
        request.setStatus(StatusEnum.ENABLE);
        return request;
    }

    private DineDishRequest dishRequest(Long storeId, Long categoryId, String name) {
        DineDishRequest request = new DineDishRequest();
        request.setStoreId(storeId);
        request.setCategoryId(categoryId);
        request.setName(name);
        request.setImage("");
        request.setImages(List.of());
        request.setDescription("");
        request.setPrice(1000);
        request.setLabels(List.of());
        request.setRecommend(RecommendEnum.NORMAL);
        request.setCookingTime(0);
        request.setUnit("份");
        request.setSort(0);
        request.setStatus(StatusEnum.ENABLE);
        return request;
    }

    private DineAttributeGroupRequest attributeGroupRequest(Long storeId, String name) {
        DineAttributeGroupRequest request = new DineAttributeGroupRequest();
        request.setStoreId(storeId);
        request.setName(name);
        request.setSelectType(SelectTypeEnum.SINGLE);
        request.setSort(0);
        request.setStatus(StatusEnum.ENABLE);
        return request;
    }

    private DineAttributeOptionRequest attributeOptionRequest(Long groupId, String name) {
        DineAttributeOptionRequest request = new DineAttributeOptionRequest();
        request.setGroupId(groupId);
        request.setName(name);
        request.setPriceAdjustment(0);
        request.setSort(0);
        request.setStatus(StatusEnum.ENABLE);
        return request;
    }

    private DineDishAttributeGroupRequest dishAttributeGroupRequest(Long dishId, Long groupId) {
        DineDishAttributeGroupRequest request = new DineDishAttributeGroupRequest();
        request.setDishId(dishId);
        request.setGroupId(groupId);
        request.setRequired(true);
        request.setSort(0);
        return request;
    }

    private DineDishAttributeOptionRequest dishAttributeOptionRequest(Long dishId, Long optionId) {
        DineDishAttributeOptionRequest request = new DineDishAttributeOptionRequest();
        request.setDishId(dishId);
        request.setOptionId(optionId);
        request.setIsAvailable(true);
        return request;
    }

    private DineTableInfoRequest tableInfoRequest(Long storeId, String name, String code) {
        DineTableInfoRequest request = new DineTableInfoRequest();
        request.setStoreId(storeId);
        request.setName(name);
        request.setCode(code);
        request.setCapacity(4);
        request.setTableStatus(TableStatusEnum.IDLE);
        return request;
    }

    private DineTableBindingRequest tableBindingRequest(Long tableId) {
        DineTableBindingRequest request = new DineTableBindingRequest();
        request.setTableId(tableId);
        request.setUserId(DineServiceIntegrationTest.REQUEST_USER_ID);
        return request;
    }

    private String name(String suffix) {
        return prefix + suffix;
    }

    private long countCategoriesByStoreAndName(Long storeId, String name) {
        return dineCategoryMapper.selectCount(Wrappers.lambdaQuery(DineCategory.class)
                .eq(DineCategory::getStoreId, storeId)
                .eq(DineCategory::getName, name));
    }

    private long countDishesByStoreAndName(Long storeId, String name) {
        return dineDishMapper.selectCount(Wrappers.lambdaQuery(DineDish.class)
                .eq(DineDish::getStoreId, storeId)
                .eq(DineDish::getName, name));
    }

    private long countAttributeGroupsByStoreAndName(Long storeId, String name) {
        return dineAttributeGroupMapper.selectCount(Wrappers.lambdaQuery(DineAttributeGroup.class)
                .eq(DineAttributeGroup::getStoreId, storeId)
                .eq(DineAttributeGroup::getName, name));
    }

    private long countAttributeOptionsByGroupAndName(Long groupId, String name) {
        return dineAttributeOptionMapper.selectCount(Wrappers.lambdaQuery(DineAttributeOption.class)
                .eq(DineAttributeOption::getGroupId, groupId)
                .eq(DineAttributeOption::getName, name));
    }

    private long countTablesByStoreAndName(Long storeId, String name) {
        return dineTableInfoMapper.selectCount(Wrappers.lambdaQuery(DineTableInfo.class)
                .eq(DineTableInfo::getStoreId, storeId)
                .eq(DineTableInfo::getName, name));
    }

    private long countDishAttributeGroups(Long dishId, Long groupId) {
        return dineDishAttributeGroupMapper.selectCount(Wrappers.lambdaQuery(DineDishAttributeGroup.class)
                .eq(DineDishAttributeGroup::getDishId, dishId)
                .eq(DineDishAttributeGroup::getGroupId, groupId));
    }

    private long countDishAttributeGroupsByDish(Long dishId) {
        return dineDishAttributeGroupMapper.selectCount(Wrappers.lambdaQuery(DineDishAttributeGroup.class)
                .eq(DineDishAttributeGroup::getDishId, dishId));
    }

    private long countDishAttributeOptions(Long dishId, Long optionId) {
        return dineDishAttributeOptionMapper.selectCount(Wrappers.lambdaQuery(DineDishAttributeOption.class)
                .eq(DineDishAttributeOption::getDishId, dishId)
                .eq(DineDishAttributeOption::getOptionId, optionId));
    }

    private long countDishAttributeOptionsByDish(Long dishId) {
        return dineDishAttributeOptionMapper.selectCount(Wrappers.lambdaQuery(DineDishAttributeOption.class)
                .eq(DineDishAttributeOption::getDishId, dishId));
    }
}
