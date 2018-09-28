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
	 git commit -m "first commit"	提交到本地仓库  
* git remote add origin https://github.com/github账户名/仓库名（仓库链接） 关联远程仓库
	 git push -u origin master（master为分支名) 	提示输入github的账号和密码		将本地文件提交到远程仓库
* git pull 将远程仓库的文件拉到本地仓库
* git push origin <分支名> -force 强制让本地的commit覆盖远程的commit，当本地的commit和仓库的commit不同时

### git本地仓库操作命令

| git命令                         | 说明                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| git add . 或者 git add <文件名> | 添加已修改的文件到本地仓库的stage(暂存区)，等待commit和push。 |
| git commit -m "msg"             | 提交刚add的文件到本地仓库，msg表示需要说明的提交详情。       |
| git status                      | 查看本地仓库的状态。                                         |
| git diff                        | 查看本地仓库的文件变化。                                     |
| git log                         | 显示从最近到最远的提交日志。                                 |
| git log -p                      | 查看详细的历史                                               |
| git log --stat                  | 查看简要统计                                                 |
| git log --pretty=oneline        | 精简显示的提交日志信息。                                     |
| git reset HEAD <文件名>         | 撤销修改，把暂存区（stage)的修改撤销掉（unstage），重新放回工作区。 |
| git checkout --<文件名>         | 撤销修改，让这个文件回到最近的一次commit或add时的状态        |
| git rm <文件名>                 | 删除本地仓库的文件，必须重新commit。然后会删除远程仓库的文件。 |
| git rm --cached <文件名>        | 删除的是本地工作区的文件，并不会删除本地仓库的文件。         |

### git高级命令

| git命令                                                      | 说明                                                   |
| ------------------------------------------------------------ | ------------------------------------------------------ |
| git commit --amend                                           | 修复当前提交的错误，生成一条新的commit替换当前的commit |
| git rebase -i \<commitId>/HEAD^<br />在编辑界面指定需要操作的commit和操作类型<br/>git rebase --continue | 修改写错的commit                                       |
| git reset --hard \<commitId>                                 | 撤销最新的提交，会撤销此次commit的修改内容             |
| git rebase -i \<commitId>/HEAD^<br />删除想撤销的commit<br />git rebase --continue | 撤销过往的提交                                         |
| 出错内容在私有branch:本地内容修正后，git push origin \<branch> -f 强制push<br />出错内容在master: git revert \<commitId>/HEAD^ | 撤销已经push的commit                                   |
| git reset --hard \<commitId>/<HEAD^> : 重置位置的同时，清空工作目录的改动<br />git reset --soft \<commitId>/<HEAD^>:重置位置的同时，保留工作目录和暂存区的内容，并把重置HEAD的位置所导致的新的文件差异放进暂存区。<br />git reset --mixed \<commitId>/<HEAD^>:重置位置的同时，保留工作目录的内容并清空暂存区。 | 重置HEAD以及它所指向的branch的位置                     |
| git stash ：临时存放工作目录的改动，只作用于被track的文件<br />git stash -u : 作用于所有文件<br />git stash pop : 恢复之前的存放内容 | 中途打包                                               |
| git reflog : 查看HEAD的移动历史<br />git checkout \<commitId> ：检出指定的commit<br />git checkout -b \<branch> : 恢复刚误删的分支 | 恢复刚误删除的分支                                     |

>  注：不再被引用直接或间接指向的 `commit`s 会在一定时间后被 Git 回收，所以使用 `reflog`来找回删除的 `branch` 的操作一定要及时，不然有可能会由于 `commit` 被回收而再也找不回来，master.

### git分支管理

| git命令                                          | 说明                             |
| ------------------------------------------------ | -------------------------------- |
| git checkout -b <分支名>                         | 创建本地分支并且切换到该分支     |
| git checkout -b <分支名> origin/<分支名>         | 拉去远程分支到本地分支           |
| git branch <分支名>                              | 创建本地分支但不会切换到该分支   |
| git checkout <分支名>                            | 切换本地分支                     |
| git branch                                       | 查看本地分支                     |
| git branch -a                                    | 查看本地分支和远程分支           |
| git branch -r                                    | 查看远程分支                     |
| git log --graph --pretty=oneline --abbrev-commit | 查看分支的合并情况               |
| git branch -d <分支名>                           | 删除本地分支                     |
| git branch -D <分支名>                           | 强行删除本地分支（指没有被合并过 |
| git merge <分支名>                               | 本地合并分支到当前分支           |
| git push origin -delete <分支名>                 | 删除远程分支                     |

- **创建远程分支：**
   * 先在本地主分支下创建分支：**git branch \<dev> ** 
   * 然后在master分支推送dev分支到远程仓库：**git push origin \<dev>**
- **合并远程分支：**
   * 先checkout下需要合并的两个分支：
     * **git checkout \<master> **
     * **git checkout \<develop>**
   * 然后在 **master** 分支上合并 **develop** 分支:（需要先切换到master分支:git checkout \<master>)
     * **git merge \<develop>** : 如果出现refusing merge unrelated history错误时,执行 **git merge \<develop>  --allow-unrelated-histories** 命令强行合并，输入完后要输入此次合并的补充信息，然后输入 **:wq**退出即可;如果出现文件冲突，要去提示的文件整理git自动标注的冲突信息，然后git add和git commit。
   * 最后提交代码： **git push origin \<master>**

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

- **cat \<fileName>** （查看文件内容）
	 **rm \<fileName>**	（删除文件）
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

  * git rm -r --cached .  或者 git rm -r --cached 文件路径/<文件名>
  * git add .
  * git commit -m "update msg"

### git error

* **如果pull失败，refusing to merge unrelated histories**：

  先pull，因为两个仓库不同，发现`refusing to merge unrelated histories`，无法pull。

  因为他们是两个不同的项目，要把两个不同的项目合并，git需要添加一句代码，在`git pull`，这句代码是在git 2.9.2版本发生的，最新的版本需要添加` --allow-unrelated-histories`

  假如我们的源是origin，分支是master，那么我们 需要这样写`git pull origin master ----allow-unrelated-histories`需要知道，我们的源可以是本地的路径。

* **failed to push some refs to**：

  没有先pull最新的仓库版本到本地，再次push时会提示冲突，这种情况大多数发生在共同开发或者两台不同的机器时，解决办法是先pull远程仓库到本地，然后再push。

* **Git在合并时遇到unrelated histories提示时无法merge**：[参考链接](https://blog.csdn.net/llllloj/article/details/52948234) 这是因为两个分支没有相同的commit记录联系，可以使用如下命令强行合并： **git merge <branchName> --allow-unrelated-histories** 。具体可参考上面的[git分支管理](#git分支管理)。





