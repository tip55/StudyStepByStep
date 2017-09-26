三个基本组件：镜像、容器、仓库；
https://github.com/leoChaoGlut/log-sys
https://github.com/junneyang/xxproject



GitHub将repo的每一个push通知给Jenkins
Jenkins触发一个Maven build
Maven 构建所有的东西，包括Docker镜像
最后，Maven会把镜像推送到私有的Docker Registry。


Docker 概念
------
Docker是基于容器的虚拟化技术，它是系统的一个服务单元进程，具有计算、网络访问、反馈运行情况、接受和响应外部控制信号等能力。
特点是：
轻量级：单机可以轻松支持上百container
快速就绪：一秒以内启动
弱安全：docker能够对多种OS资源进行隔离，但是它本质上依托于内核，因此所有的内核漏洞都是docker的致命伤

Docker安装
CentOS-7
$ sudo yum install docker


Docker基本用法

Docker 容器管理

Docker 数据卷管理

Docker 网络管理

编写 DockerFile


Docker运行Web应用

Docker运行MongoDB及Redis

Docker运行数据库应用


搭建自己的Docker Register

Docker 安全

Docker Compose项目

Docker Swarm项目

Kubernate

Docker API


基于Docker API开发应用