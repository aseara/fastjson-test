package com.github.aseara.json;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiujingde on 2017/2/20.
 * FastJson简单测试。
 */
public class FastJsonTest {

    private static final Logger LOG = LoggerFactory.getLogger(FastJsonTest.class);

    @Test
    public void encode() {
        Group group = new Group();
        group.setId(0L);
        group.setName("admin");

        User guestUser = new User();
        guestUser.setId(2L);
        guestUser.setName("guest");

        User rootUser = new User();
        rootUser.setId(3L);
        rootUser.setName("root");

        group.addUser(guestUser);
        group.addUser(rootUser);

        String jsonString = JSON.toJSONString(group);

        LOG.info(jsonString);

        List<User> users = new ArrayList<>();
        users.add(guestUser);
        users.add(rootUser);

        LOG.info(JSON.toJSONString(users));
    }

    @Test
    public void decode() {
        String groupJson = "{" +
                "'id': 0," +
                "'name': 'admin'," +
                "'users': [" +
                "  {'id': 2, 'name': 'guest'}," +
                "  {'id': 3, 'name': 'root'}" +
                "]}";
        Group group = JSON.parseObject(groupJson, Group.class);

        LOG.info(group.toString());

        String usersJson = "[{'id':2,'name':'guest'},{'id':3,'name':'root'}]";
        List<User> users = JSON.parseArray(usersJson, User.class);

        LOG.info(users.toString());
    }

}
