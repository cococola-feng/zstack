package org.zstack.core.debug;

import org.springframework.beans.factory.annotation.Autowired;
import org.zstack.core.cloudbus.CloudBus;
import org.zstack.core.cloudbus.MessageSafe;
import org.zstack.header.AbstractService;
import org.zstack.header.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xing5 on 2016/7/25.
 */
public class DebugManagerImpl extends AbstractService implements DebugManager {
    @Autowired
    private CloudBus bus;

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public boolean stop() {
        return true;
    }

    @Override
    @MessageSafe
    public void handleMessage(Message msg) {
        if (msg instanceof APIDebugSignalMsg) {
            handle((APIDebugSignalMsg)msg);
        } else if (msg instanceof APIGetDebugSignalMsg) {
            handle((APIGetDebugSignalMsg) msg);
        }
    }

    private void handle(APIGetDebugSignalMsg msg) {
        APIGetDebugSignalReply reply = new APIGetDebugSignalReply();
        reply.setSignals(getDebugSignals());
        bus.reply(msg, reply);
    }

    private void handle(APIDebugSignalMsg msg) {
        APIDebugSignalEvent evt = new APIDebugSignalEvent(msg.getId());
        for (String sig : msg.getSignals()) {
            handleSig(sig);
        }

        bus.publish(evt);
    }

    @Override
    public void handleSig(String sig) {
        List<DebugSignalHandler> hs = sigHandlers.get(sig);
        if (hs == null) {
            return;
        }

        for (DebugSignalHandler h : hs) {
            h.handleDebugSignal();
        }
    }

    @Override
    public String getId() {
        return bus.makeLocalServiceId(DebugConstant.SERVICE_ID);
    }
}
