package com.basic.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页出参
 *
 * @author vains
 */
@Data
@NoArgsConstructor
@Schema(title = "分页请求出参")
@EqualsAndHashCode(callSuper = true)
public class PageResult<T> extends BasicPageable {

    @Schema(description = "总条数")
    private Long total;

    @Schema(description = "分页数据")
    private List<T> records;

    public PageResult(Long current, Long size, Long total, List<T> records) {
        super.setSize(size);
        super.setCurrent(current);
        this.total = total;
        this.records = records;
    }

    /**
     * 根据分页对象构建响应对象
     *
     * @param current 当前页码
     * @param size    每页行数
     * @param total   总数据数量
     * @param records 分页后的具体数据
     * @param <T>     泛型，数据的类型
     * @return 公共分页响应bean
     */
    public static <T> PageResult<T> of(Long current, Long size, Long total, List<T> records) {
        return new PageResult<>(current, size, total, records);
    }

}
