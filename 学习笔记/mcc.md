正规安装方式有4种：
A、BIOS+MBR 传统安装方式。
B、CSM+MBR 注：CSM是指UEFI主板，且开启CSM，这种办法就感觉UEFI不存在一样。
C、CSM+GPT 本文重点讨论的部分，难度不低。
D、纯UEFI+GPT 注：纯UEFI是指UEFI主板，且关闭CSM，或者主板没有CSM。

MBR
主引导记录（MBR，Main Boot Record）是位于磁盘最前边的一段引导（Loader）代码。
它负责磁盘操作系统(DOS)对磁盘进行读写时分区合法性的判别、分区引导信息的定位，
它由磁盘操作系统(DOS)在对硬盘进行初始化时产生的。
通常，我们将包含MBR引导代码的扇区称为主引导扇区。

GPT
在MBR硬盘中，分区信息直接存储于主引导记录（MBR）中，但在GPT硬盘中，分区表的位置信息储存在GPT头中。但出于兼容性考虑，硬盘的第一个扇区仍然用作MBR，之后才是GPT头。


UEFI

CSM
是BIOS上Boot选项里的一个下拉子项目,与Secure Boot（安全启动）是并列项。
CSM开启使得可以支持UEFI启动和非UEFI启动,需要启动传统MBR设备，则需开启CSM。关闭CSM则变成纯UEFI启动，且完全支持安全启动。
Secure Boot（安全启动），安全启动仅适用于使用UEFI启动的操作系统
要使得电脑能启动不完全支持UEFI的设备，就必须关闭Secure Boot，然后打开CSM。

老式BIOS主板-Legacy

在Win8下，将U盘格式化为FAT32，软件有很多选择，官方推荐使用UNetbootin，可以去华军下载，以管理员身份运行
http://unetbootin.github.io/
http://www.onlinedown.net/soft/88566.htm




