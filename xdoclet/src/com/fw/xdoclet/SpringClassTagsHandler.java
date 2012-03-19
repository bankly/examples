/*
 * Copyright (c) 2001, 2002 The XDoclet team
 * All rights reserved.
 */
package com.fw.xdoclet;

import xdoclet.XDocletException;
import xdoclet.tagshandler.ClassTagsHandler;

/**
 * Spring Validator tag handler tags
 *
 * @author               Matt Raible (matt@raibledesigns.com)
 * @created              April 26, 2004
 * @xdoclet.taghandler   namespace="CommonsValidator"
 * @version              $Revision: 1.3 $
 */
public class SpringClassTagsHandler extends ClassTagsHandler
{
    public String springBeanName() throws XDocletException
    {
        String beanName = className().replaceAll("Impl", "");
        return Character.toLowerCase(beanName.charAt(0)) + beanName.substring(1);
    }
    
    public void ifIsDao(String template) throws XDocletException
    {
        if (className().indexOf("Dao") >= 0)
        {
            generate(template);
        }
    }
}