/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

package com.mumu.java_base.hotswap;

import com.sun.tools.attach.spi.AttachProvider;
import java.util.List;

public class CheckAttachProvider {
    public static void main(String[] args) {
        List<AttachProvider> providers = AttachProvider.providers();
        if (providers.isEmpty()) {
            System.err.println("No AttachProvider found! Check JDK installation.");
        } else {
            System.out.println("Available AttachProvider: " + providers.get(0).getClass().getName());
        }
    }
}