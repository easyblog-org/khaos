package top.easyblog.titan.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.easyblog.titan.bean.enums.StaticOption;
import top.easyblog.titan.enums.ContinentEnum;
import top.easyblog.titan.enums.IdentifierType;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: frank.huang
 * @date: 2023-02-26 21:03
 */
@Slf4j
@Service
public class StaticService {


    public List<StaticOption> queryAllIdentityType() {
        return Arrays.stream(IdentifierType.values()).filter(Objects::nonNull)
                .map(item -> {
                    StaticOption option = new StaticOption();
                    option.setKey(item.getCode());
                    option.setValue(item.getDesc());
                    return option;
                }).distinct().collect(Collectors.toList());
    }

    public List<StaticOption> queryAllContinent() {
        return Arrays.stream(ContinentEnum.values())
                .filter(item -> Objects.nonNull(item) && !StringUtils.equals(item.name(), ContinentEnum.GLOBAL.name()))
                .map(item -> {
                    StaticOption option = new StaticOption();
                    option.setKey(item.getCode());
                    option.setValue(item.getDesc());
                    return option;
                }).collect(Collectors.toList());
    }


}
