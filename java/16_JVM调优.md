# JVM常见参数
* 堆参数
```
-Xms 堆初始值
-Xmx 堆最大可用值
-Xmn 新生代堆最大可用值
-XX:SurvivorRatio 用来设置新生代中eden空间和from/to空间的比例
-XX:NewRatio 用来设置新生代和老年代的空间比例
-XX:+PrintGC 每次触发GC的时候打印相关日志
-XX:+PrintGCDetails 更详细的GC日志
-XX:+UseSerialGC 串行回收
-XX:+PrintCommandLineFlags 
```
# GC

# 分析工具



# 实例1：查找占用系统高的代码段



