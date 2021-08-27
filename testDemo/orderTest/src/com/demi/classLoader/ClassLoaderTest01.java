/*
package com.demi.classLoader;

import sun.misc.Launcher;
import sun.security.util.CurveDB;


import java.net.URL;
import java.security.Provider;

public class ClassLoaderTest01 {
    public static void main(String[] args) {
        System.out.println("--------------Bootstrap ClassLoader--------------");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urLs) {
            System.out.println(url.toExternalForm());
        }

        // 从上面获取的路径中随意选择一个类，来查看它们的类加载器是什么：引导类加载器Bootstrap ClassLoader
        ClassLoader classLoader = Provider.class.getClassLoader();
        System.out.println(classLoader);    // null。说明它们的类加载器是引导类加载器Bootstrap ClassLoader

        System.out.println("--------------Extension ClassLoader--------------");
        String extDirs = System.getProperty("java.ext.dirs");
        for (String path : extDirs.split(";")) {
            System.out.println(path);
        }

        // 从上面获取的路径中随意选择一个类，来查看它们的类加载器是什么：引导类加载器Bootstrap ClassLoader
        ClassLoader classLoader1 = CurveDB.class.getClassLoader();
        System.out.println(classLoader1);   // null
    }
}
*/
