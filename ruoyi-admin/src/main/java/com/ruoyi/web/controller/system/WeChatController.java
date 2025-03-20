package com.ruoyi.web.controller.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.*;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.mapper.RecipeMapper;
import com.ruoyi.system.mapper.wxMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
public class WeChatController extends BaseController
{
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private wxMapper wxMapper;

    private static final String APP_ID = "wxb732598e30842adf";
    private static final String APP_SECRET = "73a4f458cde719a7dce3a3577f7bb31e";
    private static final String WX_URL = "https://api.weixin.qq.com/sns/jscode2session";
    /**
     * 获取用户唯一身份标识openid
     */
    @PostMapping("/api/getOpenId")
    public ResponseEntity<Map<String, Object>> getOpenId(@RequestBody Map<String, String> request) {
        //获取前端传的code到wx服务器交换openid
        String code = request.get("code");
        String url = UriComponentsBuilder.fromHttpUrl(WX_URL)
                .queryParam("appid", APP_ID)
                .queryParam("secret", APP_SECRET)
                .queryParam("js_code", code)
                .queryParam("grant_type", "authorization_code")
                .toUriString();
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = new HashMap<>();
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> wxResponse = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            String responseBody = wxResponse.getBody();

            // 手动解析 JSON 响应
            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(responseBody, Map.class);

            // 检查微信返回的错误码
            if (response.containsKey("errcode")) {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("errcode", 500);
            response.put("errmsg", "Internal server error: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        String openid = (String) response.get("openid");
        String info = userMapper.checkUserOpenid(openid);
        if(info == null){
            userMapper.insertUserOpenid(openid);
        }

        return ResponseEntity.ok(response);
    }
    /**
     * 获取菜谱
     */
    @GetMapping("/api/recipes")
    public ResponseEntity<List<Map<String, Object>>> getRecipes() {
        List<Recipe> recipes = recipeMapper.getAllRecipesWithCategory();
        List<Map<String, Object>> formattedRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            Map<String, Object> formattedRecipe = new HashMap<>();
            formattedRecipe.put("id", recipe.getId());
            formattedRecipe.put("name", recipe.getName());
            formattedRecipe.put("imageUrl", recipe.getImageUrl());
            formattedRecipe.put("calories", recipe.getCalories());
            formattedRecipe.put("ingredients",recipe.getIngredients() );
            formattedRecipe.put("category_id", recipe.getCategory_id());
            formattedRecipe.put("category", recipe.getCategory());
            formattedRecipe.put("effect", recipe.getEffect());
            formattedRecipe.put("suitpeople", recipe.getSuitpeople());
            formattedRecipe.put("make", recipe.getMake());
            formattedRecipes.add(formattedRecipe);
        }

        return ResponseEntity.ok(formattedRecipes);
    }
    /**
     * 新增菜谱
     */
    @PostMapping("/api/recipes")
    public ResponseEntity<String> addRecipe(@RequestBody Map<String, Object> recipeData) {
        try {
            Recipe newRecipe = new Recipe();
            newRecipe.setName((String) recipeData.get("name"));
            newRecipe.setImageUrl((String) recipeData.get("imageUrl"));
            newRecipe.setCalories((Integer) recipeData.get("calories"));
            newRecipe.setIngredients((String) recipeData.get("ingredients"));
            newRecipe.setCategory_id((String) recipeData.get("category_id"));
            newRecipe.setEffect((String) recipeData.get("effect"));
            newRecipe.setSuitpeople((String) recipeData.get("suitpeople"));
            newRecipe.setMake((String) recipeData.get("make"));

            int result = recipeMapper.insertRecipe(newRecipe);

            if (result > 0) {
                return ResponseEntity.ok("菜谱新增成功");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("菜谱新增失败");
            }
        } catch (Exception e) {
            logger.error("Error adding recipe", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("菜谱新增失败");
        }
    }

    /**
     * 修改菜谱
     */
    @PutMapping("/api/recipes/{id}")
    public ResponseEntity<String> updateRecipe(@PathVariable("id") int id, @RequestBody Map<String, Object> recipeData) {
        try {
            Recipe updatedRecipe = new Recipe();
            updatedRecipe.setId(id);
            updatedRecipe.setName((String) recipeData.get("name"));
            updatedRecipe.setImageUrl((String) recipeData.get("imageUrl"));
            updatedRecipe.setCalories((Integer) recipeData.get("calories"));
            updatedRecipe.setIngredients((String) recipeData.get("ingredients"));
            updatedRecipe.setCategory_id((String) recipeData.get("category_id"));
            updatedRecipe.setEffect((String) recipeData.get("effect"));
            updatedRecipe.setSuitpeople((String) recipeData.get("suitpeople"));
            updatedRecipe.setMake((String) recipeData.get("make"));

            int result = recipeMapper.updateRecipe(updatedRecipe);

            if (result > 0) {
                return ResponseEntity.ok("菜谱修改成功");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("菜谱修改失败");
            }
        } catch (Exception e) {
            logger.error("Error updating recipe", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("菜谱修改失败");
        }
    }

    /**
     * 删除菜谱
     */
    @DeleteMapping("/api/recipes/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") int id) {
        try {
            int result = recipeMapper.deleteRecipe(id);

            if (result > 0) {
                return ResponseEntity.ok("菜谱删除成功");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("菜谱删除失败");
            }
        } catch (Exception e) {
            logger.error("Error deleting recipe", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("菜谱删除失败");
        }
    }

    /**
     * 新增饮食记录
     */
    @Log(title = "新增饮食记录", businessType = BusinessType.INSERT)
    @PostMapping("/newMemo")
    public AjaxResult addnewMemo( @RequestBody newMemo nw)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("openid", nw.getOpenid());
        params.put("timestamp", nw.getTimestamp());
        List<newMemo> memos = wxMapper.getIntake(params);
        if (memos != null && !memos.isEmpty()) {
            // 取到了值，执行相应的逻辑
            wxMapper.updateIntake(nw);
            return AjaxResult.success("记录已添加");
        } else {
            // 没有取到值，执行插入操作
            wxMapper.insertIntake(nw);
            return AjaxResult.success("记录已添加");
        }
    }


    @PostMapping("/uploadimg")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file uploaded.");
        }

        try {
            // 保存文件到指定目录
            String uploadDir = System.getProperty("user.dir") +"/static/";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filePath = uploadDir + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            return ResponseEntity.ok("File uploaded successfully: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed.");
        }catch (Exception e) {
            e.printStackTrace(); // 捕获其他可能的异常
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * 新增运动记录
     */
    @Log(title = "新增运动记录", businessType = BusinessType.INSERT)
    @PostMapping("/consumption")
    public AjaxResult addConsumption( @RequestBody Consumption cs)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("openid", cs.getOpenid());
        params.put("timestamp", cs.getTimestamp());
        List<Consumption> memos = wxMapper.getConsumption(params);
        if (memos != null && !memos.isEmpty()) {
            // 取到了值，执行相应的逻辑
            wxMapper.updateConsumption(cs);
            return AjaxResult.success("记录已更新");
        } else {
            // 没有取到值，执行插入操作
            wxMapper.insertConsumption(cs);
            return AjaxResult.success("记录已添加");
        }
    }

    /**
     * 查询摄入和消耗
     */
    @Log(title = "查询摄入和消耗", businessType = BusinessType.INSERT)
    @GetMapping("/newMemo")
    public AjaxResult getnewMemo(@RequestParam String openid, @RequestParam String timestamp)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("openid", openid);
        params.put("timestamp", timestamp);

        // 查询第一个数据库表的数据
        List<newMemo> memos = wxMapper.getIntake(params);

        // 查询另一个数据库表的数据
        List<Consumption> otherDataList = wxMapper.getConsumption(params);

        // 创建一个 Map 或自定义对象来合并结果
        Map<String, Object> result = new HashMap<>();
        result.put("Intake", memos);
        result.put("Consumption", otherDataList);

        return AjaxResult.success(result);
    }


    @GetMapping("/AllnewMemo")
    public AjaxResult getAllnewMemo(@RequestParam String openid) {
        Map<String, Object> params = new HashMap<>();
        params.put("openid", openid);

        // 查询第一个数据库表的数据
        List<newMemo> memos = wxMapper.getIntake(params);

        // 查询另一个数据库表的数据
        List<Consumption> otherDataList = wxMapper.getConsumption(params);

        // 创建一个 Map 来按日期合并结果
        Map<String, Map<String, Object>> resultMap = new HashMap<>();

        // 合并摄入数据
        for (newMemo memo : memos) {
            String date = memo.getTimestamp();
            resultMap.putIfAbsent(date, new HashMap<>());
            resultMap.get(date).put("Intake", memo);
        }

        // 合并消耗数据
        for (Consumption consumption : otherDataList) {
            String date = consumption.getTimestamp();
            resultMap.putIfAbsent(date, new HashMap<>());
            resultMap.get(date).put("Consumption", consumption);
        }

        // 将合并后的结果按日期排序并转换为列表
        List<Map<String, Object>> resultList = resultMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())) // 按日期排序
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        return AjaxResult.success(resultList);
    }

    /**
     * 获取微信步数，从微信服务端获取加密数据，解密后返回小程序
     */
    @PostMapping("/api/decryptWeRunData")
    public ResponseEntity<Map<String, Object>> decryptWeRunData(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String encryptedData = request.get("encryptedData");
            String iv = request.get("iv");
            String sessionKey = request.get("sessionKey");

            // 检查传入的参数是否为 null
            if (encryptedData == null || iv == null || sessionKey == null) {
                response.put("errcode", 400);
                response.put("errmsg", "请求参数不能为空");
                return ResponseEntity.badRequest().body(response);
            }

            byte[] encryptedDataBytes = Base64.getDecoder().decode(encryptedData);
            byte[] ivBytes = Base64.getDecoder().decode(iv);
            byte[] sessionKeyBytes = Base64.getDecoder().decode(sessionKey);

            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            SecretKeySpec keySpec = new SecretKeySpec(sessionKeyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            byte[] decryptedData = cipher.doFinal(encryptedDataBytes);
            String decryptedString = new String(decryptedData, "UTF-8");

            // 解析解密后的 JSON 数据
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> weRunData = objectMapper.readValue(decryptedString, Map.class);

            response.put("data", weRunData);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("errcode", 500);
            response.put("errmsg", "解密失败: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }


    /**
     * 查询文章
     */
    @GetMapping("/news")
    public AjaxResult getAllNews()
    {
        // 查询新闻数据（假设有对应的 newsMapper 和 News 实体类）
        List<News> newsList = wxMapper.getAllNews();

        // 直接返回新闻列表（保持与模板一致的返回格式）
        return AjaxResult.success(newsList);
    }

    /**
     * 新增文章
     */
    @PostMapping("/news")
    public AjaxResult addNews(@RequestBody News news) {
        int result = wxMapper.insertNews(news);
        return result > 0 ? AjaxResult.success("新增成功") : AjaxResult.error("新增失败");
    }

    /**
     * 更新文章
     */
    @PutMapping("/news/{id}")
    public AjaxResult updateNews(@RequestBody News news) {
        int result = wxMapper.updateNews(news);
        return result > 0 ? AjaxResult.success("更新成功") : AjaxResult.error("更新失败");
    }

    /**
     * 删除文章
     */
    @DeleteMapping("/news/{id}")
    public AjaxResult deleteNews(@PathVariable int id) {
        int result = wxMapper.deleteNews(id);
        return result > 0 ? AjaxResult.success("删除成功") : AjaxResult.error("删除失败");
    }
}