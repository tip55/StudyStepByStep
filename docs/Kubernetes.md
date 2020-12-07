1. Docker 容器
容器的本质是进程，通过Namespace 做隔离，Cgroups 做限制，rootfs 做文件系统。

2. Pod
Pod 是 Kubernetes 项目的原子调度单位（也是最小编排单位）。
应用间往往不是完全独立地存在，而是存在着密切的协作关系，使得它们必须部署在同一台机器上（应用间需要发生直接的文件交换、应用间需要进行本地通信等），这也是Pod被设计出来的原因。

Pod其实是一组共享了某些资源的容器：
Pod 里的所有容器，共享的是同一个 Network Namespace，并且可以声明共享同一个 Volume。

Pod间多个容器是对等关系：
Pod 的实现需要使用一个叫作 Infra 的中间容器，Infra 容器永远都是第一个被创建的容器，而其他用户定义的容器，则通过 Join Network Namespace 的方式，与 Infra 容器关联在一起。
并且对于同一个 Pod 里面的所有用户容器来说，它们的进出流量，也可以认为都是通过给 Infra 容器配置Network Namespace完成的。



