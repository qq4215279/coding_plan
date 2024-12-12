/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_tools.dom4j;

import com.mumu.common.config.ConfigItem;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * ParseConfigTest
 *
 * @author liuzhen
 * @version 1.0.0 2024/4/16 15:33
 */
public class ParseConfigTest {

    public static void main(String[] args) throws FileNotFoundException, JAXBException, IllegalAccessException, InstantiationException {
        File file = new File("F:\\Code\\MumuSpace\\coding_plan\\tech_enhance\\src\\main\\java\\com\\mumu\\java_tools\\dom4j\\xml\\config_item.xml");
        InputStream inputStream = new FileInputStream(file);


        Class<ConfigItem> configItemClass = ConfigItem.class;
        Object o = configItemClass.newInstance();

        o = parseXml(inputStream, o);


        System.out.println(o);

    }

    private static <T> T parseXml(InputStream inputStream, T tScript) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(tScript.getClass());
        Unmarshaller u = jc.createUnmarshaller();
        try (InputStream in = inputStream) {
            return (T) u.unmarshal(in);
        } catch (IOException e) {
            return null;
        }
    }



}
