## 持久化
* RDB
 Redis 默认的持久化方案。Redis服务在指定的时间间隔内，执行指定次数的写操作，则会将内存中的数据刷新到磁盘中。即在指定目录下生成一个dump.rdb文件。Redis 重启会通过加载dump.rdb文件恢复数据。
 ```bash
 # 指定触发规则, 可以配置多个
save <seconds> <changes>
# 指定rdb文件目录 
dir ./
# 指定rdb文件名称
dbfilename dump.rdb
# 默认开启数据压缩
rdbcompression yes
 ```
 
 触发方式：
 save以阻塞的方式保存快照数据；bgsave异步的方式保存快照数据。
 
 优点：
 适合大规模的数据恢复。
 缺点：
 数据的完整性和一致性不高，因为RDB可能在备份时宕机而导致数据丢失。
 备份时会独立创建一个子进程进行数据复制，会额外占用相同大小的内存空间。
 
* AOF
它采用日志的形式来记录每个写操作，并追加到文件中。Redis 重启的会根据日志文件的内容将写指令从前到后执行一次以完成数据的恢复工作。
```bash
# 开启aof
appendonly yes
# 设置aof日志文件
appendfilename "appendonly.aof"

# 指定更新日志的触发条件
# appendfsync always  同步持久化，每次发生数据变化会立刻写入到磁盘中。
appendfsync everysec  每秒异步记录一次（默认值）
# appendfsync no  不同步

# 指定aof文件重写触发机制
auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb
```

触发方式：
always每次写指令都会触发更新aof日志文件；everysec每秒异步更新一次aof日志文件；no不同步。
重写机制：
AOF的工作原理是将写操作追加到文件中，文件的冗余内容会越来越多。所以Redis通过重写的方式以当前内存数据来重新生成aof文件。

优点：数据的完整性和一致性更高
缺点：因为AOF记录的内容多，文件会越来越大，数据恢复也会越来越慢。

## 过期数据清理机制
三种不同的删除策略：
（1）立即删除。在设置键的过期时间时，同时设置一个回调事件，当过期时间达到时，由时间处理器自动执行键的删除操作。
（2）惰性删除。键过期了就过期了，不管。在每次Get获取数据时，检查此key是否已经过期，如果过期了就删除它，并返回nil，如果没过期，就返回键值。
（3）定时删除。每隔一段时间，对expires字典进行检查，删除里面的过期键。
Redis使用的过期键值删除策略是：惰性删除加上定期删除，两者配合使用。


## 数据淘汰策略
内存不够的场景下，淘汰旧内容的策略。
* LRU（最不经常使用）
https://www.jianshu.com/p/c8aeb3eee6bc