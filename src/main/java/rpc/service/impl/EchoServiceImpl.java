package rpc.service.impl;

import rpc.service.EchoService;

/**
 * Created by ${WangChengYong} on 2018/1/19.
 */
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String ping) {
        return ping != null ? ping + "- -> I am ok" : "I am ok";
    }
}
