package top.easyblog.titan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.bean.header.UserHeaderImgBean;
import top.easyblog.titan.feign.client.HeaderClient;
import top.easyblog.titan.request.header.CreateUserHeaderImgRequest;
import top.easyblog.titan.request.header.QueryUserHeaderImgRequest;

/**
 * @author: frank.huang
 * @date: 2023-03-11 15:31
 */
@Service
public class HeaderService {

    @Autowired
    private HeaderClient headerClient;

    public void create(CreateUserHeaderImgRequest request) {
        headerClient.request(() -> headerClient.create(request));
    }

    public UserHeaderImgBean details(QueryUserHeaderImgRequest request) {
        return headerClient.request(() -> headerClient.details(request));
    }

}
