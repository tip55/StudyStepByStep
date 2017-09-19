一般都是先操作好本地仓库后，再推到远程仓库

1.本地计算机初始化相关操作
设置Git的user name和email：
$ git config --global user.name "yangshuhua"
$ git config --global user.email "yangshuhua@gmail.com"
生存密钥：
$ ssh-keygen -t rsa -C "yangshuhua@gmail.com"
按3个回车


2.新建远程dev分支
$ git checkout -b dev   新建本地dev分支并切换到新建的dev分支上
$ git push origin dev   将本地dev分支推到远程以创建远程dev分支


3.git clone只能克隆远程仓库的master分支，而无法克隆其他分支；场景：将远程仓库的dev分支同步到本地并创建本地dev分支
$ git branch -a    列出所有远程仓库的分支
$ git checkout -b dev origin/dev    检出远程的dev分支，在本地创建dev分支，并切换到本地的dev分支
$    查看本地分支关联的远程分支

4.查看不同
$ git diff readme.txt    查看文件在本地分支相较于关联的远程分支的变化内容
$ git diff branch1 branch2 --stat    显示出所有有差异的文件列表
$ git diff branch1 branch2 文件名(带路径)    显示指定文件的详细差异
$ git diff branch1 branch2    显示出所有有差异的文件的详细差异

5.版本回退