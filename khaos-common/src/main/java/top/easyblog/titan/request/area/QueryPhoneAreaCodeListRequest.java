package top.easyblog.titan.request.area;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: frank.huang
 * @date: 2022-02-10 22:08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryPhoneAreaCodeListRequest {
    private List<Long> ids;
    private String continentCode;
    private String areaName;
    @Builder.Default
    private Integer limit = 10;
    @Builder.Default
    private Integer offset = 0;
}
