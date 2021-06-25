# 基础
```
Statement Id  ， MyBatis将XML文件中的每一个<mapper> 节点抽象为一个 Mapper 接口，而这个接口中声明的方法与<mapper> 节点中的<select|update|delete|insert> 节点项通过id进行对应。
实际运行中，通过SqlSession.getMapper(XXXMapper.class) 方法，MyBatis 会根据相应的接口声明的方法信息，通过动态代理机制生成一个Mapper 实例。
```

# Mybatis主要构件
* SqlSession
```
它表示和数据库交互的会话，完成必要数据库增删改查功能。
```
* Executor
```
它表示执行器，是MyBatis 调度的核心，负责SQL语句的生成和查询缓存的维护
```
* StatementHandler
```
封装了JDBC Statement操作，负责对JDBC statement 的操作，如设置参数、将Statement结果集转换成List集合。
```
* ParameterHandler
```
负责对用户传递的参数转换成JDBC Statement 所需要的参数。
```
* ResultSetHandler
```
负责将JDBC返回的ResultSet结果集对象转换成List类型的集合。
```
* TypeHandler
```
负责java数据类型和jdbc数据类型之间的映射和转换。
```
* MappedStatement
```
维护了一条<select|update|delete|insert>节点的封装内容。
```
* SqlSource
```
负责根据用户传递的parameterObject，动态地生成SQL语句，将信息封装到BoundSql对象中，并返回。
```
* BoundSql
```
表示动态生成的SQL语句以及相应的参数信息。
```
* Configuration
```
记录了MyBatis所有的配置信息。
```



# SQL执行过程
* 参数映射
```
参数映射指的是对于java数据类型和jdbc数据类型之间的转换。
这里有包括两个过程：
a. 查询阶段，将java类型的数据，转换成jdbc类型的数据。
b. 对查询结果集resultset的jdbcType 数据转换成java 数据类型。
Mybatis内部使用 ParameterHandler 实现这部分功能。
```
* 动态SQL解析
```
MyBatis 通过传入的参数值，使用 Ognl 来动态地构造SQL语句，使得MyBatis 有很强的灵活性和扩展性。
SqlSource
```
* SQL执行
```
Executor
```

* 结果处理和映射
```
ResultSetHandler
```

# 缓存
* 一级缓存
```
SqlSession会话级别的缓存，Mybatis默认开启一级缓存。
生命周期：
在创建一个会话（sqlSession）对象时，会为其分配一个 PerpetualCache 实例做为缓存；在会话结束时，其内部的PerpetualCache对象表示的缓存也会被一并释放掉。
```
* 二级缓存
```
Application级别的缓存，它是多个SqlSession共享的，其作用域是mapper的同一个namespace，通过 SqlSessionFactory 实现。
```
* 怎么判断某两次查询是完全相同的查询？
```
需要同时符合一下条件：
a. Statement Id相同。
b. 查询结果范围相同。
c. Sql语句字符串相同。
d. 查询传入的参数值相同。
```