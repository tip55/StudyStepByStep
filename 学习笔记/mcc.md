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


UEFI+GPT
>> 预备知识
1. UEFI全称“统一的可扩展固件接口”(Unified Extensible Firmware Interface),优于传统的legacy BIOS。
2. GPT 是一种全新的磁盘分区表，可以弥补MBR的诸多不足。
3. 因为NTFS不能进行UEFI引导启动win7安装程序，所以必须是用U盘，而且要求格式化成FAT32。

>> 准备工作
1. 下载win7 64位的纯净版镜像IOS文件，而不是常用的ghost文件，因为我们需要对镜像文件进行修改。
2. 准备一个U盘，容量不小于4G。

>> 制作过程
1. 解压之前下载的镜像文件，如下所示：
boot
efi
sources
support
upgrade
autorun.inf
bootmgr
bootmgr.efi
setup.exe
因为纯净的win7的安装镜像并不支持UEFI启动，所以我们需要稍做修改。
从一台已经安装了win7 x64系统的电脑上提取启动文件——C:\Windows\Boot\EFI\bootmgr.efi 。在解压后的镜像文件夹找到“efi”文件夹，在其目录下新建一个名为“boot”，将提取的“bootmgr.efi”文件拷贝至“boot”目录下，并重命名为“bootX64.efi”。
2.制作U盘启动盘

http://www.xitongcheng.com/jiaocheng/xtazjc_article_33913.html