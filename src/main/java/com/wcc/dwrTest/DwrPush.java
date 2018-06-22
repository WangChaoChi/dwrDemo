package com.wcc.dwrTest;

import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

//@SuppressWarnings("deprecation")
public class DwrPush {
    public void Send(String msg) {

        Runnable run = new Runnable() {

            private ScriptBuffer script = new ScriptBuffer();

            public void run() {
                //设置要调用的 js及参数
                script.appendCall("callback", msg);

                //得到所有ScriptSession
                Collection<ScriptSession> sessions = Browser.getTargetSessions();

                //遍历每一个ScriptSession
                for (ScriptSession scriptSession : sessions) {
                    scriptSession.addScript(script);
                }
            }
        };

        //执行推送
        Browser.withAllSessions(run);

       /* WebContext webContext = WebContextFactory.get();

        Collection<ScriptSession> sessions = webContext.getAllScriptSessions();
        //构造发送所需的js脚本
        ScriptBuffer scriptBuffer = new ScriptBuffer();
        //调用客户端的js脚本函数
        scriptBuffer.appendScript("callback(");
        //这个msg可以被过滤处理一下，或者做其他的处理操作。这视需求而定。
        scriptBuffer.appendData(msg);
        scriptBuffer.appendScript(")");

        //为所有的用户服务
        Util util =new Util(sessions);
        util.addScript(scriptBuffer);
        System.out.println("发送了..." + msg);*/
    }

}
