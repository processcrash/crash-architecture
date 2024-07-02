package com.crush.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Json 结果对象
 *
 * @author zhangxq
 * @since 2024/6/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult<T> {

    private T data;

}
