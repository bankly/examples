package com.fw.dwr;

import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.convert.StringConverter;
import org.directwebremoting.dwrp.SimpleOutboundVariable;
import org.directwebremoting.extend.InboundContext;
import org.directwebremoting.extend.InboundVariable;
import org.directwebremoting.extend.MarshallException;
import org.directwebremoting.extend.OutboundContext;
import org.directwebremoting.extend.OutboundVariable;
import org.directwebremoting.util.JavascriptUtil;
import org.directwebremoting.util.LocalUtil;

public class StringEncodingConverter extends StringConverter {
    private static Log log = LogFactory.getLog(StringEncodingConverter.class);

    public Object convertInbound(Class paramType, InboundVariable iv, InboundContext inctx) throws MarshallException {
        String str = LocalUtil.decode(iv.getValue());

        try {
            str = new String(str.getBytes("big5"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            log.error("decode error", e);
        }

        return str;
    }

    public OutboundVariable convertOutbound(Object data, OutboundContext outctx) throws MarshallException {
        String str = null;

        try {
            str = new String(data.toString().getBytes("iso8859-1"), "big5");
        } catch (UnsupportedEncodingException e) {
            log.error("encode error", e);
        }

        String escaped = JavascriptUtil.escapeJavaScript(str);
        return new SimpleOutboundVariable('\"' + escaped + '\"', outctx, true);
    }
}
