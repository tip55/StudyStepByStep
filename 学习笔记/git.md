都是先基于本地所处分支进行相关操作，然后将最终的结果推送同步到远程仓库
<br>
origin均代表远程仓库，比如origin/master或origin:master表示远程仓库的master分支
<br>

1.本地计算机初始化相关操作
------
设置Git的username和email：
$ git config --global user.name "yangshuhua"
$ git config --global user.email "yangshuhua@gmail.com"
生存密钥：
$ ssh-keygen -t rsa -C "yangshuhua@gmail.com"
按3个回车


2.分支操作
------
$ git branch    查看本地分支
$ git branch -r    查看远程分支
$ git branch xxx    创建本地分支,注意不会自动切换到新分支上
$ git checkout xxx    切换分支
$ git checkout -b xxx    创建新分支并立即切换到新分支
$ git branch -d xxx    删除分支，-d选项无法删除仍有未合并内容的分支。若要强制删除一个分支，可以使用-D选项
$ git merge xxx    合并分支，将名称为xxx的分支与当前分支合并
$ git merge --no-ff xxx
注：如果想要合并分支，那么要合并的分支必须比当前分支的版本先进
$ git push origin :heads/xxx    删除远程分支
$ git push origin master    将本地所在分支内容推送到远程master分支上，若远程master分支不存在，则会新建远程master分支；
$ git push origin dev:master    不关心当前所在分支，只会把本地dev分支推送到远程的master分支，若远程master分支不存在，则会新建远程master分支；
注意：$ git push origin :test    表示删除远程名称为test的分支


3.版本（Tag）操作
------
$ git tag    查看本地版本
$ git tag xxx    创建版本
$ git tag -d xxx    删除版本
$ git push origin :refs/tags/xxx    删除远程版本
$ git push origin xxx    将本地Tag推送到远程仓库，新建远程Tag
$ git push origin xxx:other_name    将本地Tag推送到远程仓库，并修改远程Tag名字
注意：$ git push origin :other_name    表示删除远程名称为other_name的Tag

4.忽略
------
在仓库根目录下创建名称为".gitignore"的文件，写入不需要的文件夹名或文件，每个元素占一行即可，如:
target
bin
*.db

5.新建远程dev分支
------
$ git checkout -b dev   基于当前所在分支新建本地dev分支并切换到新建的dev分支上
$ git push origin dev   将本地dev分支推到远程以创建远程dev分支

6.git clone只能克隆远程仓库的master分支，而无法克隆其他分支；场景：将远程仓库的dev分支同步到本地并创建本地dev分支
------
$ git branch -a    列出所有远程仓库的分支
$ git checkout -b dev origin/dev    检出远程的dev分支，在本地创建dev分支，并切换到本地的dev分支
$ git remote -v    查看本地分支关联的远程分支

7.查看不同
------
$ git diff readme.txt    查看文件在本地分支相较于关联的远程分支的变化内容
$ git diff branch1 branch2 --stat    显示出所有有差异的文件列表
$ git diff branch1 branch2 文件名(带路径)    显示指定文件的详细差异
$ git diff branch1 branch2    显示出所有有差异的文件的详细差异

8.版本回退的还原
------
$ git log    查看历史提交版本记录，--pretty=oneline参数可以屏蔽不必要信息
注：每台机器checkout时，均会把所有以往的历史提交记录也会check下来
$ git reset --hard version    回退到指定版本
$ git push -f origin master    -f参数表示强制覆盖远程,一般情况下切不可使用该参数
``````第二种方式``````
在Git中，用HEAD表示当前版本，上一个版本就是HEAD^，上上一个版本就是HEAD^^，当然往上100个版本写100个^比较容易数不过来，所以写成HEAD~100。
$ git log --pretty=oneline
> 50bd7fa1de128b29eeef55be1a086c5550bfcd14 111111111111111
> 30bd7fa1de128b29eeef55be1a086c5550bfcd14 222222222222222
> 20bd7fa1de128b29eeef55be1a086c5550bfcd14 333333333333333
> 10bd7fa1de128b29eeef55be1a086c5550bfcd14 444444444444444
>     ... ...
$ git reset --hard HEAD^
or
$ git reset --hard HEAD~1
$ git log --pretty=oneline
> 30bd7fa1de128b29eeef55be1a086c5550bfcd14 222222222222222
> 20bd7fa1de128b29eeef55be1a086c5550bfcd14 333333333333333
> 10bd7fa1de128b29eeef55be1a086c5550bfcd14 444444444444444
>     ... ...
``````代码回退后怎么还原``````
$ git reflog  查看到提交记录ID
> dae675a HEAD@{0}: reset: moving to HEAD^  
> 8e27eb6 HEAD@{1}: commit: append GPL  
> dae675a HEAD@{2}: commit: add distributed  
> c412f7e HEAD@{3}: commit (initial): wrote a readme file
可看到reset操作前的最后一次提交记录ID为8e27eb6
注：$ git reflog | grep merge  根据关键字搜索提交记录
$ git reset --hard 8e27eb6
$ git push -f origin master
注：使用log/reflog/reset可以在各个版本之间自由移动，Git内部有HEAD指针，只是单纯的移动指针，所以速度很快。 

9.非版本回退的还原
------
$ git log --pretty=oneline
> 50bd7fa1de128b29eeef55be1a086c5550bfcd14 111111111111111
> 30bd7fa1de128b29eeef55be1a086c5550bfcd14 222222222222222
> 20bd7fa1de128b29eeef55be1a086c5550bfcd14 333333333333333
> 10bd7fa1de128b29eeef55be1a086c5550bfcd14 444444444444444
>     ... ...
$ git revert xxxx33    出现vim的编辑状态，主要是为本次提交添加注释，操作和vim一样，:wq保存退出
$ git push origin master

注：与reset的区别就是，revert会创建一个新的提交，而且将指定版本的修改逆过来实现还原，这里为可能使用merge提供方便，版本未回退。



10. 拉取代码时冲突后解决方法
------
$ git stash  将本地git仓库已提交文件存档
$ git diff HEAD  查看当前工作区和版本库区别, 此时什么都没有输出，说明工作区被重置为HEAD指向内容了
$ git pull 拉取代码
$ git stash list 查看存档列表
>>stash@{0}: WIP on master: 440e976 init
$ git stash pop --index stash@{0} 还原存档内容,--index参数非必需，表示不仅恢复工作区，还恢复暂存区
$ git status查看状态


11.替换
------
$ git reset --hard origin/master    将本地当前所在分支的HEAD指向远程仓库的master分支
$ git reset --hard develop    使用本地的develop分支替换覆盖本地当前所处的分支
``````temp``````


12.pull
Please specify which branch you want to merge with.
git pull push没有指定branch报错的解决方法:git pull origin develop   #指定远程分支
