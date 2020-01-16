## 简介
Least Recent Used，即最不经常使用。它是一种在内存不够的场景下，淘汰旧内容的策略。
## LRU原理
用下面的方式来演示 LRU 原理，假设内存只能容纳3个页大小，按照 7 0 1 2 0 3 0 4 的次序访问页。假设内存按照栈的方式来描述访问时间，在上面的，是最近访问的，在下面的是，最远时间访问的，工作如下图所示：
[![Build Status](../images/LRU1.jpg)](https://xxx/xxx)
这样设计可能问题很多，这段内存按照访问时间进行了排序，会有大量的内存拷贝操作，所以性能会很低。
那么如何设计一个LRU缓存，使得放入和移除都是 O(1) 的，我们需要把访问次序维护起来，但是不能通过内存中的真实排序来反应，有一种方案就是使用双向链表。

## 基于HashMap+双向链表实现 LRU
使用 HashMap 存储 key，这样可以做到 save 和 get key的时间都是 O(1)，而 HashMap 的 Value 指向双向链表实现的 LRU 的 Node 节点，如下图所示：
[![Build Status](../images/LRU2.jpg)](https://xxx/xxx)

https://zhuanlan.zhihu.com/p/34133067
