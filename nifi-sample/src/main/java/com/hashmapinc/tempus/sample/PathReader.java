/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hashmapinc.tempus.sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.nifi.annotation.documentation.CapabilityDescription;
import org.apache.nifi.annotation.documentation.SeeAlso;
import org.apache.nifi.annotation.documentation.Tags;
import org.apache.nifi.annotation.lifecycle.OnDisabled;
import org.apache.nifi.annotation.lifecycle.OnEnabled;
import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.controller.AbstractControllerService;
import org.apache.nifi.controller.ConfigurationContext;
import org.apache.nifi.expression.ExpressionLanguageScope;
import org.apache.nifi.processor.Relationship;
import org.apache.nifi.processor.exception.ProcessException;
import org.apache.nifi.processor.util.StandardValidators;
import org.apache.nifi.reporting.InitializationException;

@Tags({"file","path","input","filesystem","local","source","get"})
@CapabilityDescription("This will take a file path named as OUTPUT. And that property then can be used by any other processor")
public class PathReader extends AbstractControllerService implements MyService {

    public static final PropertyDescriptor OUTPUT = new PropertyDescriptor
            .Builder().name("OUTPUT")
            .displayName("Output Path")
            .description("The directory to which files should be written. You may use expression language such as /aa/bb/${path}\r\n" + 
            		"Supports Expression Language: true")
            .required(true)
            .addValidator(StandardValidators.NON_EMPTY_VALIDATOR)
            .expressionLanguageSupported(ExpressionLanguageScope.FLOWFILE_ATTRIBUTES)
            .build();
			
	
	/*
	 * public static final PropertyDescriptor MY_NUM = new PropertyDescriptor
	 * .Builder().name("MY_NUM") .displayName("My number")
	 * .description("Example Property") .required(true)
	 * .addValidator(StandardValidators.NON_EMPTY_VALIDATOR) .build();
	 */

    private static final List<PropertyDescriptor> properties;

    static {
        final List<PropertyDescriptor> props = new ArrayList<>();
        props.add(OUTPUT);
		//props.add(MY_NUM);
        properties = Collections.unmodifiableList(props);
    //braces end 
    //public static final Relationship REL_SUCCESS = new Relationship.Builder().name("success").description("All files are routed to success").build();
    @Override
    protected List<PropertyDescriptor> getSupportedPropertyDescriptors() {
        return properties;
    }

    /**
     * @param context
     *            the configuration context
     * @throws InitializationException
     *             if unable to create a database connection
     */
 public String path;
    @OnEnabled
    public void onEnabled(final ConfigurationContext context) throws InitializationException {
	path=context.getProperty(OUTPUT).getValue();
    }

    @OnDisabled
    public void shutdown() {

    }
public String getPath() {
		return path;
	}

    @Override
    public void execute() throws ProcessException {

    }

}
