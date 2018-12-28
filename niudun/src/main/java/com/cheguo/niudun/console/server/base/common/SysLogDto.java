package com.cheguo.niudun.console.server.base.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2018/04/25
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysLogDto implements Serializable {

    private String sourceIp;
    private String url;
    private String requestMethod;
    private String request;
    private String response;
    private long   cost;
}
