package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.Consumption;
import com.ruoyi.common.core.domain.entity.News;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.entity.newMemo;

import java.util.List;
import java.util.Map;

public interface wxMapper {
    /**
     * 新增用户当日摄入信息
     *
     * @param nw 用户信息
     * @return 结果
     */
    public int insertIntake(newMemo nw);

    /**
     * 新增用户当日运动信息
     *
     * @param cs 用户信息
     * @return 结果
     */
    public int insertConsumption(Consumption cs);

    /**
     * 获取当天摄入信息
     *
     * @return 结果
     */
    List<newMemo> getIntake(Map<String, Object> params);

    /**
     * 获取当天运动信息
     *
     * @return 结果
     */
    List<Consumption> getConsumption(Map<String, Object> params);

    /**
     * 修改用户当日摄入信息
     *
     * @param nw 用户信息
     * @return 结果
     */
    public int updateIntake(newMemo nw);

    /**
     * 修改用户当日摄入信息
     *
     * @param cs 用户信息
     * @return 结果
     */
    public int updateConsumption(Consumption cs);

    List<News> getAllNews();
    int insertNews(News news);
    int updateNews(News news);
    int deleteNews(int id);
}
