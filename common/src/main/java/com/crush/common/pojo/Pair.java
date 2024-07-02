package com.crush.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 一对数据 POJO
 *
 * @author zhangxq
 * @since 2024/6/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pair<F, S> {

    private F first;

    private S second;

}
