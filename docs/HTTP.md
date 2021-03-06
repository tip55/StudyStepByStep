## 从输入一个url到浏览器页面展示都经历了哪些过程？
* 域名解析(DNS解析)，解析获取相应的IP地址。
* 浏览器向服务器发起tcp连接，与浏览器建立tcp三次握手。
* 握手成功后，浏览器向服务器发送http请求，请求数据包。
* 服务器处理收到的请求，将数据返回至浏览器。
* 浏览器收到HTTP响应。
* 浏览器渲染页面。

## 浏览器在与服务器建立了一个 TCP 连接后是否会在一个 HTTP 请求完成后断开？什么情况下会断开？
```bash
在 HTTP/1.0 中，一个服务器在发送完一个 HTTP 响应后，会断开 TCP 链接。但是这样每次请求都会重新建立和断开 TCP 连接，代价过大。所以虽然标准中没有设定，某些服务器对 Connection: keep-alive 的 Header 进行了支持。意思是说，完成这个 HTTP 请求之后，不要断开 HTTP 请求使用的 TCP 连接。这样的好处是连接可以被重新使用，之后的 HTTP 请求可以避免连接的初始化和 SSL 的开销。
所以默认情况下浏览器在与服务器建立 TCP 连接不会断开，只有在请求报头中声明 Connection: close 才会在请求完成后关闭连接。
```
## 一个 TCP 连接可以对应几个 HTTP 请求？
```bash
如果维持连接，一个 TCP 连接是可以发送多个 HTTP 请求的。
```
## 一个 TCP 连接中 HTTP 请求发送可以一起发送么？
```bash
HTTP/1.1 存在一个问题：单个 TCP 连接在同一时刻只能处理一个请求。
意思是说：两个请求的生命周期不能重叠，任意两个 HTTP 请求从开始到结束的时间在同一个 TCP 连接里不能重叠。
虽然 HTTP/1.1 规范中规定了 Pipelining 来试图解决这个问题。
 Pipelining 是什么？
 一个支持持久连接的客户端可以在一个连接中发送多个请求（不需要等待任意请求的响应）。收到请求的服务器必须按照请求收到的顺序发送响应。
 
 由于 Pipelining 在实践中会出现许多难以解决的问题，这个功能在浏览器中默认是关闭的，但是HTTP2 提供了 Multiplexing 多路传输特性，可以在一个 TCP 连接中同时完成多个 HTTP 请求。
```
## 为什么有的时候刷新页面不需要重新建立 SSL 连接？
```bash
TCP 连接有的时候会被浏览器和服务端维持一段时间。TCP 不需要重新建立，SSL 自然也会用之前的。
```
