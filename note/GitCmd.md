## git本地配置和上传项目到github上

[参考链接1](http://www.open-open.com/lib/view/open1476001142540.html)

[参考链接2](http://jingyan.baidu.com/article/a65957f4e91ccf24e77f9b11.html)

[参考链接3](http://www.mamicode.com/info-detail-1248674.html)

[参考链接4](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000/0013744142037508cf42e51debf49668810645e02887691000)

### 配置SSH

* 先检查本机是否配置SSH key设置
	
* 使用gitbash生成新的ssh key

* **ssh-keygen -t rsa -C "youremail@example.com"** (第一步，需要把邮件地址换成你自己的邮件地址，然后一路回车，使用默认值即可，由于这个Key也不是用于军事目的，所以也无需设置密码。如果一切顺利的话，可以在用户主目录里找到.ssh目录，里面有id_rsa和id_rsa.pub两个文件，这两个就是SSH Key的秘钥对，id_rsa是私钥，不能泄露出去，id_rsa.pub是公钥,第二部，登陆GitHub，打开“Account settings”，“SSH Keys”页面，然后，点“Add SSH Key”，填上任意Title，在Key文本框里粘贴id_rsa.pub文件的内容）
	
### Git基本操作

1、从远程仓库克隆到本地仓库(cd切换到要存放git项目的目录)

* git init

* git clone https://github.com/13286810620/Record_R9.git

2、关联远程仓库，把本地仓库推送到远程仓库

* cd Record_R9	切换到克隆项目的工作目录下
* echo "# Record_R9" >> README.md  创建README.md文件
* git add . 提交当前目录下的所有文件 
* git add README.md 提交当前目录下的README.md文件
* git commit -m "first commit"	提交到本地仓库 
* git remote add origin https://github.com/github账户名/仓库名（仓库链接） 关联远程仓库
* git push -u origin master（master为分支名) 	提示输入github的账号和密码		将本地文件提交到远程仓库
* git pull 将远程仓库的文件拉到本地仓库

### git本地仓库操作命令

- **git add <文件名或者.>**（添加到git仓库)
- **git commit -m <这次提交的变化>**	(提交到本地仓库)
- **git status**  (查看仓库的状态变化)
- **git diff**   (查看仓库的文件哪些发生变化)
- **git log**   (显示从最近到最远的提交日志)  git log --pretty=oneline (精简输出信息)
- **git reset --hard <版本号>**  (版本回退，回退到选择的版本号）
- **git reflog** (查看命令历史）
- **git checkout -- <文件名>** (撤销修改，让这个文件回到最近一次git commit或git add时的状态)
- **git reset HEAD <文件名>**		(撤销修改，把暂存区的修改撤销掉（unstage），重新放回工作区)
- **git rm <文件名>**		(删除文件，把仓库的文件删除,然后必须重新commit)
- **git checkout -- <文件名>**  (用版本库里的版本替换工作区的版本，无论工作区是修改还是删除，都可以“一键还原”）

### git分支管理

- **git checkout -b <分支名>**  (创建分支，并且切换到该分支）
- **git branch <分支名>** (创建分支)
- **git checkout <分支名>** (切换到该分支)
- **git branch** 	(查看当前分支,当前分支前面有个*号)
- **git merge <分支名>** (合并某分支到当前分支）
<<<<<<< HEAD
- **git branch -d <分支名>** (删除分支）
- **git branch -D <分支名>** (丢弃一个没有被合并过的分支，强行删除）
- **git log --graph --pretty=oneline --abbrev-commit** (查看分支的合并情况）
- **git merge --no-ff -m "merge with no-ff" dev** (禁用merge的fast forward模式,可以看出曾合并的历史）
- **git push origin(远程仓库名）dev(分支名)** （推送分支，如果推送失败，先用git pull抓取远程的新提交）  
=======
- **git branch -d <分支名>** (删除本地分支）
- **git push origin  --delete <分支名>** （删除远程分支）
- **git branch -D <分支名>** (丢弃一个没有被合并过的分支，强行删除）
- **git log --graph --pretty=oneline --abbrev-commit** (查看分支的合并情况）
- **git merge --no-ff -m "merge with no-ff" dev** (禁用merge的fast forward模式,可以看出曾合并的历史）
- **git push origin(远程仓库名或者git地址）dev(分支名)** （推送到远程分支，如果推送失败，先用git pull抓取远程的新提交）  
>>>>>>> dev
- **git checkout -b dev origin/dev** (另一台电脑在dev分支上开发，创建远程origin的dev分支到本地）

### git标签命令

- **git tag <标签名>** （新建一个标签，默认为HEAD）

- **git log --pretty=oneline --abbrev-commit** （找到历史提交的commit id)

- **git tag <标签名> <提交的ID>** (在对应的commit id打上标签）

- **git show <标签名>** （显示标签信息）

- **git tag** （查看所有标签）

- **git tag -d <标签名>** （删除标签）

- **git push origin <标签名>** (推送标签到远程仓库）

- **git push origin --tags** （推送所有标签到远程仓库）

- **git push origin :refs/tags/v0.9** （删除远程仓库的标签)

### 其他命令

- **cat <fileName>** （查看文件内容）
- **rm <fileName>**	（删除文件）
- **git remote -v** (显示远程仓库更详细的信息）

### git忽略文件配置

* 在根目录下创建并配置.gitignore文件

* 常用规则

  * /build/* 		只忽略项目根目录下的build文件夹

  * build/*           忽略项目所有目录名字为build的文件夹

  * *.class            忽略后缀名为class的文件

  * /local.properties      忽略名称为local.properties的文件

  * /* 

     !.gitignore

     !/fw/bin/  

     !/fw/sf/ 			忽略全部内容，但不忽略.gitignore文件、根目录下的/fw/bin/和/fw/sf/ 目录

* 不要误解了 .gitignore 文件的用途，该文件只能作用于 Untracked Files，也就是那些从来没有被 Git 记录过的文件（自添加以后，从未 add 及 commit 过的文件）。 如果文件曾经被 Git 记录过，那么.gitignore 就对它们完全无效。 解决方法如下：

  * git rm -r --cached .
  * git add .
  * git commit -m "update msg"

### git error

* **refusing to merge unrelated histories**：

  先pull，因为两个仓库不同，发现`refusing to merge unrelated histories`，无法pull。

  因为他们是两个不同的项目，要把两个不同的项目合并，git需要添加一句代码，在`git pull`，这句代码是在git 2.9.2版本发生的，最新的版本需要添加`--allow-unrelated-histories`

  假如我们的源是origin，分支是master，那么我们 需要这样写`git pull origin master ----allow-unrelated-histories`需要知道，我们的源可以是本地的路径。

* **failed to push some refs to**：

<<<<<<< HEAD
=======
  没有先pull最新的仓库版本到本地，再次push时会提示冲突，这种情况大多数发生在共同开发或者两台不同的机器时，解决办法是先pull远程仓库到本地，然后再push。

>>>>>>> dev




