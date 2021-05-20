## 概念
* Feature分支表示开发分支
* origin代表远程仓库，比如，origin/master或origin:master表示远程master分支
* GIT操作都是修改本地仓库，再同步到远程仓库

## 配置GIT本地信息

```bash
# 配置username和email
$ git config --global user.name "xxx"
$ git config --global user.email "xxx@gmail.com"
# 生存密钥
$ ssh-keygen -t rsa -C "xxx@gmail.com"
按3个回车，默认yes
```

## 创建版本库

## 基本操作
```bash

$ git add ./
$ git commit -m "xxx"
$ git push origin master
$ git status

```

## 分支(Branch)管理
```bash
# 查看本地分支
$ git branch

# 查看本地分支关联的远程分支
$ git remote -v

# 查看远程分支
$ git branch -r/-a

# 创建本地分支，不自动切换到新分支上
$ git branch xxx

# 切换本地分支
$ git checkout xxx

# 创建本地分支并切换到新分支
$ git checkout -b xxx

# 关联本地分支和远程分支
$ git branch --set-upstream-to=origin/remote_branch  your_branch
# 将本地当前的新分支推送到远程仓库并进行关联
$ git push --set-upstream origin newbranch

# 检出远程的dev分支，在本地创建dev分支，并切换到本地的dev分支
$ git checkout -b dev origin/dev

# 删除本地分支(-d选项无法删除仍有未合并内容的分支，可使用-D选项强制删除分支)
$ git branch -d xxx

# 合并本地分支，将xxx本地分支合并到当前分支
$ git merge xxx

# 
$ git merge --no-ff -m "comment" xxx

# 删除远程分支
$ git push origin :heads/xxx

# 将本地分支推送到远程dev分支上
$ git push origin dev

# 将本地dev分支推送到远程master分支
$ git push origin dev:master
```

## 版本(Tag)操作
```bash
# 查看本地版本
$ git tag

# 创建本地版本
$ git tag xxx

# 删除本地版本
$ git tag -d xxx

# 删除远程版本
$ git push origin :refs/tags/xxx

# 将本地Tag推送到远程xxx仓库,自动创建远程xxx版本
$ git push origin xxx

# 将本地Tag推送到远程other_name仓库
$ git push origin xxx:other_name
```

## 比较
```bash
# 查看文件在本地分支相较于关联的远程分支的变化内容
$ git diff file

# 显示出本地两个分支所有有差异的文件列表
$ git diff branch1 branch2 --stat

# 显示指定文件的详细差异
$ git diff branch1 branch2 file
```

## 还原
* 版本回退进行还原（版本指针后移）
```bash
# 查看历史提交版本记录，--pretty=oneline参数屏蔽不必要信息
$ git log --pretty=oneline

# 回退到指定版本
$ git reset --hard version

# 在Git中，用HEAD表示当前版本，上一个版本就是HEAD^，上上一个版本就是HEAD^^
# 往上100个版本可使用HEAD~100
$ git log --pretty=oneline
 50bd7fa1de128b29eeef55be1a086c5550bfcd14 111111111111111
 30bd7fa1de128b29eeef55be1a086c5550bfcd14 222222222222222
 20bd7fa1de128b29eeef55be1a086c5550bfcd14 333333333333333
 10bd7fa1de128b29eeef55be1a086c5550bfcd14 444444444444444
 ... ...
$ git reset --hard HEAD^ 或者 $ git reset --hard HEAD~1
$ git log --pretty=oneline
 30bd7fa1de128b29eeef55be1a086c5550bfcd14 222222222222222
 20bd7fa1de128b29eeef55be1a086c5550bfcd14 333333333333333
 10bd7fa1de128b29eeef55be1a086c5550bfcd14 444444444444444
 ... ...

# 强制覆盖远程分支
$ git push -f origin master


用reset代码版本回退后怎么还原？
# 查看提交记录ID
$ git reflog
 dae675a HEAD@{0}: reset: moving to HEAD^  
 8e27eb6 HEAD@{1}: commit: append GPL  
 dae675a HEAD@{2}: commit: add distributed  
 c412f7e HEAD@{3}: commit (initial): wrote a readme file

# 可看到reset操作前的最后一次提交记录ID为8e27eb6

$ git reset --hard 8e27eb6
$ git push -f origin master

# 根据关键字搜索提交记录
$ git reflog | grep merge
```

* 非版本回退进行还原（通过反做创建一个新的版本将要还原的内容还原，新的版本提交，推荐使用）
```bash
$ git log --pretty=oneline
 70c943a0df1ffb1aa957bbc38a11fd5a00a3ef30 111111111111111
 dc641f126c92dd22b9706e0693bb5787c7179700 222222222222222
 d85f3b5abdd40be8f68748854e1a484dc4686072 333333333333333
 55a49d55f921a9658ded2135fbe9b45ec9642b88 444444444444444
 a4e1a2176b7b5a91400cdf7c0e34c2d5931be819 555555555555555
 :
 
 ... ...
还原上个版本"111111111111111"的内容：
$ git revert 70c943a0df1ffb1aa957bbc38a11fd5a00a3ef30
# 出现vim的编辑状态，主要是为本次提交添加注释，操作和vim一样，:wq保存退出

还原多个版本，可能在中间怎么办？
$ git revert OLDER_COMMIT^..NEWER_COMMIT
比如还原444444444444444~dc641f126c92dd22b9706e0693bb5787c7179700版本的内容，包含前后：
$ git revert 55a49d55f921a9658ded2135fbe9b45ec9642b88^..dc641f126c92dd22b9706e0693bb5787c7179700


最后推上远程即可
$ git push origin master
```

* reset和revert区别
```bash
revert会创建一个新的提交，而且将指定版本的修改逆过来实现还原。
注意版本回退由于降低了版本，可能带来无法使用merge命令合并分支。
```


## 冲突解决
* pull拉取代码冲突

```bash
# 工作区和暂存区

# 将本地git仓库已提交的文件进行存档
$ git stash

# 查看当前工作区和版本库区别
$ git diff HEAD

# 拉取代码
$ git pull

# 查看本地分支的存档列表
$ git stash list
 stash@{0}: WIP on master: 440e976 init

# 还原存档内容
$ git stash pop --index stash@{0}

# 查看状态
$ git status
```

## 替换
```bash
# 将本地当前所在分支的HEAD指向远程master分支
# 也就是用远程master分支替换覆盖本地当前分支
$ git reset --hard origin/master

# 使用本地的develop分支替换覆盖本地当前分支
$ git reset --hard develop
```

## 常见问题
* Please specify which branch you want to merge with.
```bash
#指定远程分支
$ git pull origin develop
```
