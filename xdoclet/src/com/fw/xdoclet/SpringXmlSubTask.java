package com.fw.xdoclet;

import xdoclet.XmlSubTask;

public class SpringXmlSubTask extends XmlSubTask {
    private final static String DEFAULT_TEMPLATE_FILE = "resources/spring_xml.xdt";

    private final static String GENERATED_FILE_NAME = "spring.xml";
    
    public SpringXmlSubTask()
    {
        setTemplateURL(getClass().getResource(DEFAULT_TEMPLATE_FILE));
        setDestinationFile(GENERATED_FILE_NAME);
    }
}
