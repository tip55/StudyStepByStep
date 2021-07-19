* Bean加载过程
```
Spring 作为 Ioc 框架，实现了依赖注入，由一个中心化的 Bean 工厂来负责各个 Bean 的实例化和依赖管理。

入口：
ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
Person person = context.getBean("person", Person.class);

过程：
a. 解析Bean配置或注解
重要核心类 - BeanDefintaion
通过扫描指定包路径下的Bean注解或者解析XML配置，将一个Bean的属性封装成为一个BeanDefintaion对象。
每个Bean都对应一个BeanDefintaion对象，其包含对应Bean的className、scope、lazy等属性。

Spring IOC容器最终会维护一个名为beanDefinitionMap的ConcurrentHashMap结构，保存所有的BeanDefintaion实例。

b. Bean 的实例化
重要核心类 - BeanPostProcessor
通过反射机制创建Bean的实例对象，之后会触发BeanPostProcessor#postProcessBeforeInstantiation动作。
在该阶段我们可以自定义逻辑，如返回一个代理对象等。

c. Bean 的初始化
重要核心类 - BeanPostProcessor
给Bean对象的依赖属性进行填充注入，以及

d. Bean 的使用

e. Bean 的销毁
重要核心类 - DisposableBean

```
* SpringBoot启动过程
```

```

* AOP
```
gclib不需要接口
jdk需要接口
```

* 注解事务
```
应用在调用 @Transactional 声明的目标方法时，Spring会默认生成一个AOP代理对象，代理对象根据 @Transactional 的属性配置信息，决定 @Transactional 声明的目标方法是否由事务拦截器
TransactionInterceptor来拦截，在TransactionInterceptor拦截时会在目标方法执行之前创建并加入事务，然后根据执行的过程是否出现异常，再由拦截器间接控制数据源DataSource是否要提交
还是回滚事务。
```
* 事务传播机制
```
事务传播机制是在多个事务方法相互调用时，事务如何在这些方法间传播。

Spring事务传播机制有以下类型：
required：默认的事务传播类型，如果当前没有事务，则自己新建一个事务，如果当前存在事务，则加入这个事务。
supports：当前存在事务，则加入当前事务，如果当前没有事务，就以非事务方法执行。
requires_new：创建一个新事务，如果存在当前事务，则挂起该事务。
not_supported：始终以非事务方式执行,如果当前存在事务，则挂起当前事务。
mandatory：当前存在事务，则加入当前事务，如果当前事务不存在，则抛出异常。
never：不使用事务，如果当前事务存在，则抛出异常。
nested：如果当前事务存在，开启一个嵌套事务（子事务）执行，否则开启一个事务。
```

Netty的零拷贝（Zero-Copy）机制




