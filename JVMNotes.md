# JVM

JVM系列课程

## 初识JVM

### JDK、JRE、JVM之间的联系

JDK：（Java Development Kits）

​	JavaSE API太多了，从JDK8开始，出现了compact profiles，为了实现按需分配JRE的思想。从JDK9开始，在下载安装包里，就没有JRE单独的模块了，而是出现了compact profiles，分为compact1、compact2、compact3。包含在JDK安装目录的jmods目录下。可以用jlink来进行按需分配compacts。以下截图来自JDK8。

JRE：（Java Runtime Environment）

JVM：（Java Virtual Machine）

![](pics\JDK_JRE_JVM.png)

#### jdeps工具的使用

​	》jdeps即“java dependencies”，是java8提供的一个工具，在bin目录下。

​	》jdeps命令显示Java类文件的包级或类级依赖关系。输入类可以是.class文件、目录、jar文件的路径名，或者可以是完全限定的类名称，一分析所有类文件。

​	》可以查看当前class文件依赖哪些jre文件包。

```java
例如：该class文件时对IO流文件复制的一个编译结果。在命令行运行 >jdeps -P Copy01.class的结果为：
    Copy01.class -> C:\Program Files\Java\jdk1.8.0_271\jre\lib\rt.jar (compact1)
    com.dengmin.demi.io (Copy01.class)
      -> java.io                                            compact1
      -> java.lang                                          compact1
```

#### JDK8裁剪JRE（EJDK，Embedded JDK，嵌入式版本的JDK可以实现裁剪操作）

​	》主要采用jrecreate命令完成裁剪操作；

​	》可以裁剪出JRE-compact1、JRE-compact2、JRE-compact3、JRE-Full四种JRE

### 虚拟机概念

》定义：就是一台虚拟的计算机，用来执行一系列虚拟计算机指令。

》分类：系统虚拟机和程序虚拟机。

​	系统虚拟机：对物理计算机的仿真，能够执行一整套的计算机指令。如：VM

​	程序虚拟机：如：JVM。实现一次编译，多次运行。

### JVM定义

​	》Java虚拟机是整个Java平台的基石，是Java技术实现硬件与操作系统无关的关键部分。

​	》可以看成一台抽象的计算机，有着自己的指令集以及各种运行时内存区域。

​	》Java虚拟机与Java语言并没有必然的联系，它只与特定的二进制文件格式class文件格式所关联，class文件包含Java虚拟机指令集（或者称为字节码(bytecode)）和符号表，以及其他的一些辅助信息。

### JVM规范

​	JVM规范是官方给出的整个框架的设计和约定。不同版本之间有一些差别的，规范来自于JVM官方文档。

### JVM产品

​	不止一个，主要了解HotSpot（OracleJDK和OpenJDK默认的JVM产品）。

### JVM发展史

​	》JDK 6，出现了OpenJDK，开源的JDK，虚拟机使用的是HotSpot

​	》JDK 8，Oracle启用JEP（JDK Enhancement Proposals）来管理JDK的新特性。启用了lambda表达式，彻底移除了HotSpot的永久代

### Java资源网站

#### 学习网站

​	https://www.infoworld.com/category/java/

​	https://dzone.com/

#### 官方文档

​	JVM规范：https://docs.oracle.com/javase/specs/index.html

## Class 文件结构

### Class 文件的理解

#### Class 文件是什么

​	》Java虚拟机与Java语言没有联系，只与class文件这种特定的二进制文件格式相关联。

​	》除Java外，还有Kotlin、Clojure、Groovy、JRuby、JPython、Scala都可以编译出Class文件。

​	》Java虚拟机并不关心Class文件来自于哪种语言，Class文件中包含了Java虚拟机指令集、符号表以及若干其他的辅助信息。

#### Class文件与类的对应关系

​	》每一个class文件对应唯一的一个类或者接口的定义信息，反过来，类或者接口并不一定都必须定义在文件里（比如，类或者接口也可以通过类加载器直接生成）。

#### Class文件格式

​	》每个class文件都由字节流组成，每个字节包含8个二进制位所有16位、32位、64位长度的数据将通过构造成2个、4个和8个连续的8位字节来表示。

​	》多字节数据项总是按照Big-endian（大端在前）的顺序进行存储的

- Big-endian（大端在前）：数据的低位保存在内存的高地址中，而数据的高位保存在内存的低地址中。
- Little-endian（小端在前）：数据的低位保存在内存的低地址中，而数据的高位保存在内存的高地址中。

### Class 文件的结构详述

```java
摘自官网：https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.1
ClassFile {
    u4             magic;
    u2             minor_version;
    u2             major_version;
    u2             constant_pool_count;
    cp_info        constant_pool[constant_pool_count-1];	// 常量池数组。
    u2             access_flags;
    u2             this_class;
    u2             super_class;
    u2             interfaces_count;
    u2             interfaces[interfaces_count];
    u2             fields_count;
    field_info     fields[fields_count];
    u2             methods_count;
    method_info    methods[methods_count];
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
xx_info：是class文件中表的数据结构。
例如：cp_info
cp_info {
    u1 tag;
    u1 info[];
}
```

#### 什么是魔数magic

​	文件内容起始几个字节使用固定的内容来**描述该文件类型**，因此这几个字节的内容称为魔数。u4   CA FE BA BE

#### 版本号 minor_version	major_version

​	JDK6开始，就不再叫做JDK1.6了。

​	minor_version：次版本号，起始值是0

​	major_version：主版本号，起始值为45，每个JDK大版本发布，主版本号向上+1

​	JDK8（JDK1.8）的版本号为52.0，同理，JDK11的版本号为52 + 3 = 55.0

#### 常量池总数 constant_pool_count

​	》常量池大小（constant_info_count）：常量池是class文件中第一个出现的变长结构。

​	》假设常量池大小为n，常量池真正的有效索引是1~n-1。0属于保留索引，可供特殊情况使用。

#### 常量池数组 constant_pool[constant_pool_count-1]

静态常量池（很多时候说的静态常量池指的就是class文件中的常量池）。

》常量池中主要存放两种常量：

- 字面量（Literal）
  - 字符串（UTF-8）、被声明的final的常量值（整数值、浮点数）等

- 符号引用（Symbolic References）
  - 类和接口的全限定名
  - 字段的名称和描述符
  - 方法的名称和描述符
  - 方法句柄和方法类型
  - 动态调用点和动态常量

》常量池表中所有常量项的结构必包含一个tag项，tag值用于标志一个常量是哪种常量结构。只有根据tag确定常量的结构，才能根据常量结构计算常量所占用的字节数。

》常量池的数据结构：

```java
cp_info {
	u1 tag;	// 符号引用的编号
	u1 info[];	// 
}
```

》根据tag的不同，JDK8中常量具体分为14种

​	常量类型表

|          Constant Type           | Value |                   描述                   |
| :------------------------------: | :---: | :--------------------------------------: |
|       CONSTANT_Class_info        |   7   |            类或接口的符号引用            |
|      CONSTANT_Fieldref_info      |   9   |              字段的符号引用              |
|     CONSTANT_Methodref_info      |  10   |            类中方法的符号引用            |
| CONSTANT_InterfaceMethodref_info |  11   |           接口中方法的符号引用           |
|       CONSTANT_String_info       |   8   |             字符串类型字面量             |
|      CONSTANT_Integer_info       |   3   |                整型字面量                |
|       CONSTANT_Float_info        |   4   |               浮点型字面量               |
|        CONSTANT_Long_info        |   5   |               长整型字面量               |
|       CONSTANT_Double_info       |   6   |            双精度浮点型字面量            |
|    CONSTANT_NameAndType_info     |  12   | 字段与字段类型或方法与方法类型的符号引用 |
|        CONSTANT_Utf8_info        |   1   |             utf-8编码字符串              |
|    CONSTANT_MethodHandle_info    |  15   |               表示方法句柄               |
|     CONSTANT_MethodType_info     |  16   |               表示方法类型               |
|   CONSTANT_InvokeDynamic_info    |  18   |          表示一个动态方法调用点          |

》字段描述符（field descriptor）：用来表示类、实例或局部变量

| *FieldType* term    | Type        | Interpretation                                               |
| ------------------- | ----------- | ------------------------------------------------------------ |
| `B`                 | `byte`      | signed byte                                                  |
| `C`                 | `char`      | Unicode character code point in the Basic Multilingual Plane, encoded with UTF-16 |
| `D`                 | `double`    | double-precision floating-point value                        |
| `F`                 | `float`     | single-precision floating-point value                        |
| `I`                 | `int`       | integer                                                      |
| `J`                 | `long`      | long integer                                                 |
| `L` *ClassName* `;` | `reference` | an instance of class *ClassName*                             |
| `S`                 | `short`     | signed short                                                 |
| `Z`                 | `boolean`   | `true` or `false`                                            |
| `[`                 | `reference` | one array dimension                                          |

》方法描述符

- 包含0个或多个参数描述符（parameter descriptor）以及多个返回值描述符（return descriptor）。参数描述符表示该方法所接受的参数类型。
- 实例方法除了传递自身定义的参数，还需要额外传递参数this，但是这一点不是由方法描述符来表达的，参数this的传递是由Java虚拟机中调用实例方法锁使用的指令来实现的。

例如：

- 方法为：Object m(int i, double d, Thread t) {}
- Java虚拟机中的描述符为：(IDLjava/lang/Thread;)Ljava/lang/Object
- 实例方法除了传递自身定义的参数，还需要额外传递参数this，但是这一点不是由方法描述符来表达的，参数this的传递是由Java虚拟机中调用实例方法锁使用的指令来实现的。

》常量类型具体分析

```java
1 CONSTANT_Class_info	类或接口
	(1) 数据结构：
	CONSTANT_Class_info {
		u1 tag;	// #符号引用的编号
		u2 name_index;	// 类或接口的全限定名
	}
	(2) 名词解释
		a) 名字解释：类、接口、数组、枚举、注解的符号引用；
		b) 一个有效的数组类型描述符中描述的数组，其维度必须小于等于255；
		c) 类中或接口中使用到了其他的类，只有在类中实际使用到了该类时，该类的信息才会在常量池中有对应的CONSTANT_Class_info常量池项。
	(3) 项约束
		u2 name_index：必须是一个CONSTANT_utf8_info常量，表示类（或接口）的全限定名称。
    (4) 例子
        创建一个只包含主类的Test01.java类文件，编译后用IDEA的插件jclasslib查看类的class文件的结构，可以发现：
        	CONSTANT_Class_info的组成形式为：cp_info #常量池编号(即符号引用) <类或接口的全限定名>
    (5) 类、抽象类的默认父类都是Object，如果有继承关系，父类就会变成继承的那个类。也就是说，Object类是类和抽象类的基类。
    (6) Java面试题
        Java中接口的默认父类是不是Object？
        答：Java中接口的基类不是Object。官方文档中有说到：接口中默认引用了Object类，Java的接口会隐式的实现Object中的一些方法。
    (7) 枚举类的基类是Object，但它的父类是Enum抽象类。创建一个枚举类，会出现四个CONSTANT_Class_info类的符号引用，其中有一个本身类，两个一维数组类和一个Enum抽象父类。
    (8) 注解(@interface)属于接口，其基类不是Object，注解的父接口是Annotation。
    
```

```java
2 CONSTANT_Fieldref_info	字段
    (1) 数据结构
    CONSTANT_Fieldref_info {
    	u1 tag;	// 所属类名的符号引用（包括cp_info #标签编号），如：cp_info #4，表示该常量指向常量池中的4号常量（该常量是一个类常量符号引用）
    	u2 class_index;	// 该常量对用的类全限定名称。如：<com/demi/orders/order02/MyStu>
    	u2 name_and_type_index;	// 名字和描述符的符号引用。
	}
	(2) 名词解释（能够得到CONSTANT_Fieldref_info常量的情况）
        a) 名字解释：字段的符号引用；
        b) 所有非static final的字段；(5)(6)
        c) 所有使用的字段（含赋值的字段、在其他地方使用的字段、使用了其他类的字段）。(4)(5)(6)
    (3) 项约束
        class_index：指向的常量必须是一个CONSTANT_Class_info常量，表示当前字段所在类的类名；
        name_and_type_index：指向的常量必须是一个CONSTANT_NameAndType_info常量，表示当前字段的名字和类型描述符。
    (4) 例子
        public class MyStu {
            private String id = "0001";
    		private String name;
    		private int age;
            // setter和getter省略
        }
		！！！类的属性必须在赋值后，才有字段符号引用CONSTANT_Fieldref_info出现。
	(5) 静态常量static final没有字段符号引用CONSTANT_Fieldref_info，静态变量或final修饰的变量都有字段符号引用CONSTANT_Fieldref_info。
	(6) 在类stu中创建的实例对象person，引用实例对象的非静态常量属性int age = person.pAge，在常量池中会增加三个字段符号引用：创建的实例对象引用person、实例对象的非静态常量属性引用pAge以及该类中引用实例对象的变量age。
	(7) 方法里的局部变量没有字段符号引用常量。
```

```java
3 CONSTANT_Methodref_info	类中方法的符号引用
    (1) 数据结构
    CONSTANT_Fieldref_info {
    	u1 tag;	// 符号引用的编号
    	u2 class_index;	// 方法所在的全限定类名
    	u2 name_and_type_index;	// 名字和描述符
	}
	(2) 名词解释（能出现该符号引用的情况）
        a) 名字解释：类中方法的符号引用；
        b) 所有使用的方法（就是说必须调用）；
        c) 默认调用父类无参构造方法<init>:()
```

```
4 CONSTANT_InterfaceMethodref_info	接口中方法的符号引用
	(1) 数据结构
	CONSTANT_InterfaceMethodref_info {
		u1 tag;
		u2 class_index;	// 方法所在的全限定接口名
		u2 name_and_type_index;
	}
	(2) 项约束
		> class_index：指向的常量必须是一个CONSTANT_Class_info常量，且必须是接口类型，表示当前接口方法所属的类名。
		
```

#### 常量池数组小结（前4个info）

![class文件结构图解(前4个info)](pics\class文件结构图解(前4个info).png)

#### 常量池数组续集

```
5 CONSTANT_String_info	字符串类型字面量
	(1) 数据结构
	CONSTANT_String_info {
		u1 tag;
		u2 string_index; 
	}
	(2) 可进入常量池的情况
		> static final是java语法层面上的常量，static final 修饰的字符串是class文件中的String字面量。
		> 所有出现的String类型的字面量
	(2) 项约束
		> string_index：值为常量池中某个常量的索引，该索引指向的常量必须是一个CONSTANT_utf8_info常量。
	
```

```
6 CONSTANT_Integer_info	整型字面量
	(1) 数据结构
	CONSTANT_Integer_info {
		u1 tag;
		u4 bytes;
	}
	(2) 可进入常量池的情况
		> static final修饰的所有byte char short boolean int类型都可以；
		> 可用于描述boolean(1, 0) byte(-2^7~2^7-1, 即-128~127) int short(-2^15 ~ 2^15-1, 即-32768~32767) char(0~2^16-1, 即0~65535)
		> 非final修饰，在-32768~32767（即-2^15 ~ 2^15-1，short的范围内）范围内，则直接进入字节码指令内部，超出此范围进入直接进CONSTANT_Integer_info常量池。
	(3) 项约束
		>bytes：转为10进制数就是这个常量所表示的整型值。
```

