package top.easyblog.titan.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.bean.area.PhoneAreaCodeBean;
import top.easyblog.titan.bean.area.PhoneAreaCodeMapBean;
import top.easyblog.titan.constant.Constants;
import top.easyblog.titan.enums.ContinentEnum;
import top.easyblog.titan.exception.BusinessException;
import top.easyblog.titan.feign.client.PhoneAreaClient;
import top.easyblog.titan.request.area.CreatePhoneAreaCodeRequest;
import top.easyblog.titan.request.area.QueryPhoneAreaCodeListRequest;
import top.easyblog.titan.request.area.UpdatePhoneAreaCodeRequest;
import top.easyblog.titan.response.KhaosResultCode;
import top.easyblog.titan.response.PageResponse;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: frank.huang
 * @date: 2023-03-12 17:52
 */
@Slf4j
@Service
public class PhoneAreaService {

    @Autowired
    private PhoneAreaClient phoneAreaClient;


    public void create(CreatePhoneAreaCodeRequest request) {
        phoneAreaClient.request(() -> phoneAreaClient.create(request));
    }


    public PhoneAreaCodeMapBean queryPhoneAreaCodeMap(QueryPhoneAreaCodeListRequest request) {
        ContinentEnum continentEnum = ContinentEnum.codeOf(request.getContinentCode());
        if (Objects.isNull(continentEnum)) {
            throw new BusinessException(KhaosResultCode.INVALID_CONTINENT_TYPE);
        }
        PageResponse<PhoneAreaCodeBean> response = phoneAreaClient.request(() -> phoneAreaClient.list(request));
        List<PhoneAreaCodeBean> phoneAreaCodeBeans = response.getData();
        if (CollectionUtils.isEmpty(phoneAreaCodeBeans)) {
            return null;
        }
        PhoneAreaCodeMapBean phoneAreaCodeMapBean = new PhoneAreaCodeMapBean();
        phoneAreaCodeMapBean.setContinentCode(continentEnum.getCode());
        phoneAreaCodeMapBean.setContinentName(continentEnum.getDesc());
        phoneAreaCodeMapBean.setChildren(phoneAreaCodeBeans);
        phoneAreaCodeMapBean.setTotal(response.getTotal());
        return phoneAreaCodeMapBean;
    }


    private List<PhoneAreaCodeBean> queryAllPhoneAreaCodeList(QueryPhoneAreaCodeListRequest request) {
        AtomicReference<QueryPhoneAreaCodeListRequest> atomicPhoneAreaCodeRequest = new AtomicReference<>(request);
        if (Objects.isNull(request)) {
            atomicPhoneAreaCodeRequest.set(QueryPhoneAreaCodeListRequest.builder().build());
        }

        QueryPhoneAreaCodeListRequest queryPhoneAreaCodeListRequest = atomicPhoneAreaCodeRequest.get();
        queryPhoneAreaCodeListRequest.setLimit(0);
        queryPhoneAreaCodeListRequest.setOffset(0);
        PageResponse<PhoneAreaCodeBean> pageResponse = phoneAreaClient.request(() -> phoneAreaClient.list(atomicPhoneAreaCodeRequest.get()));
        long total = pageResponse.getTotal();
        int page = (int) Math.ceil(((double) total / Constants.DEFAULT_PAGE_SIZE));
        List<PhoneAreaCodeBean> phoneAreaCodeBeans = Lists.newArrayList();
        for (int pageNo = 0; pageNo < page; pageNo++) {
            queryPhoneAreaCodeListRequest.setLimit(Constants.DEFAULT_PAGE_SIZE);
            queryPhoneAreaCodeListRequest.setOffset(pageNo * Constants.DEFAULT_PAGE_SIZE);
            PageResponse<PhoneAreaCodeBean> response = phoneAreaClient.request(() -> phoneAreaClient.list(atomicPhoneAreaCodeRequest.get()));
            phoneAreaCodeBeans.addAll(response.getData());
        }
        return phoneAreaCodeBeans;
    }

    public void update(Long phoneAreaCodeId, UpdatePhoneAreaCodeRequest request) {
        phoneAreaClient.request(() -> phoneAreaClient.update(phoneAreaCodeId, request));
    }
}
