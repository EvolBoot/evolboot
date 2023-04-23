package org.evolboot.ws.core.test;

import org.evolboot.core.util.JsonUtil;
import org.evolboot.core.websocket.WsAuthentication;
import org.evolboot.core.websocket.WsSecurityContextHolder;
import org.evolboot.shared.event.mq.TestMessage;
import org.evolboot.ws.core.WsOnMessage;
import org.evolboot.ws.core.WsService;
import org.evolboot.ws.core.convert.ListStringArrayConvert;
import org.evolboot.ws.core.convert.ListStringConvert;

import java.util.List;

/**
 * @author evol
 */
@WsService
public class WsServiceTest {


    @WsOnMessage("test")
    public void test(String msg) {
        System.out.println(msg);
    }


    @WsOnMessage("test2")
    public String test2(String msg) throws InterruptedException {
        String principalId = WsSecurityContextHolder.getContext().getPrincipalId();
        System.out.println(msg);
        Thread.sleep(20 * 1000);
        return principalId + "返回Test2";
    }

    @WsOnMessage("test3")
    public void test3(Long msg) {
        System.out.println(msg);
    }


    @WsOnMessage("test4")
    public void test4(long msg) {
        System.out.println(msg);
    }


    @WsOnMessage("test5")
    public void test5(int msg) {
        System.out.println(msg);
    }


    @WsOnMessage("test6")
    public void test6(float msg) {
        System.out.println(msg);
    }


    @WsOnMessage("test7")
    public void test7(boolean msg) {
        System.out.println(msg);
    }

    @WsOnMessage("test8")
    public void test8(byte msg) {
        System.out.println(msg);
    }

    @WsOnMessage("test9")
    public void test9(short msg) {
        System.out.println(msg);
    }


    @WsOnMessage("test10")
    public void test10(char msg) {
        System.out.println(msg);
    }

    @WsOnMessage("test11")
    public Object test11(TestMessage msg) {
        System.out.println(msg);
        return JsonUtil.stringify(msg);
    }

    @WsOnMessage(value = "test12", convert = ListStringConvert.class)
    public void test12(List<String> msg) {
        System.out.println(msg);
    }


    @WsOnMessage(value = "test13", convert = ListStringArrayConvert.class)
    public void test13(String[] msg) {
        System.out.println(msg);
    }


    public static void main(String[] args) {
        String ss = JsonUtil.stringify(111);
        System.out.println(ss);
    }

}
