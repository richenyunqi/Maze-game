[![996.icu](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu) [![LICENSE](https://img.shields.io/badge/license-Anti%20996-blue.svg)](https://github.com/996icu/996.ICU/blob/master/LICENSE)

**撒娇打滚求star哦\~\~ღ( ´･ᴗ･ \` )比心**

本仓库代码是经过建议使用eclipse编译运行过的，一般情况下将本仓库代码下载下来之后，使用eclipse编译直接可以运行。

# 软件总体设计
## 软件总体框架
该软件主要分为如下三个模块：
1. 参数设置模块
2. 按钮功能模块按钮功能模块
3. 迷宫主界面模块迷宫主界面模块
## 软件各模块介绍
### 参数设置模块
1. 迷宫大小相关参数：
 - ROWS（即迷宫行数，默认设置为奇数，最小值为11，最大值为99，默认值为11）；
 - COLS（即迷宫列数，默认设置为奇数，最小值为11，最大值为99，默认值为11）；
 - Lattice’s width（即组成迷宫的格子的宽度，迷宫格子默认设置为正方形，指定了迷宫格子的宽度相当于指定了迷宫格子的大小，默认设置为自然数，最小值为5，最大值为30，默认值为15）。
 
这些参数设置的显示图下图所示：

![参数设置图](https://img-blog.csdnimg.cn/201812251512198.png)

2. 迷宫创建算法相关参数
本游戏中创建一个迷宫的算法有三种：
 - Depth First Search Algorithm（深度优先搜索算法）
 - Randomized Prim's Algorithm（随机普利姆算法）
 - Recursive Division Algorithm（递归分割算法）。
用户需在同时也只能在这三种迷宫创建算法中任意选择一种，默认选择的迷宫创建算法为Depth First Search Algorithm（深度优先搜索算法）。迷宫创建算法相关参数的显示图如下图所示：

![迷宫创建算法相关参数设置图](https://img-blog.csdnimg.cn/20181225151342642.png)

3. 迷宫寻路算法相关参数
本游戏中走出一个迷宫的迷宫寻路算法有两种：
 - Depth First Search Algorithm（深度优先搜索算法）
 - Breadth First Search Algorithm（广度优先搜索算法）。
用户需在同时也只能在这两种迷宫创建算法中任意选择一种，默认选择的迷宫创建算法为Depth First Search Algorithm（深度优先搜索算法）。迷宫寻路算法相关参数的显示图如下图所示：

![迷宫寻路算法相关参数设置图](https://img-blog.csdnimg.cn/2018122515142879.png)

4. 整个参数设置模块的显示图如下图所示：

![参数设置模块的显示图](https://img-blog.csdnimg.cn/20181225151510164.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3JpY2hlbnl1bnFp,size_16,color_FFFFFF,t_70)

### 按钮功能模块
本游戏中，按进行游戏的主体不同共设计两个游戏状态：
 - 用户进行游戏的状态
 - 计算机进行游戏的状态

本游戏软件刚刚运行时刻，按钮功能模块的显示图如下图所示：

![游戏软件刚刚运行时刻，按钮功能模块的显示图](https://img-blog.csdnimg.cn/20181225151958428.png)

该模块涉及到的游戏功能按钮及相关的适用状态有以下六种：
1. Restart按钮：即重新开始游戏按钮，在用户进行游戏的状态和计算机进行游戏的状态两种状态下均可使用。点击该按钮，当前所有正在进行的游戏行为都立刻被终止，程序使用参数设置模块的相关参数——用户选中的迷宫创建算法按照用户选择的迷宫行列数创建一个新的迷宫，并以用户选择的格子宽度显示在游戏界面上。
2. Pause/Continue按钮：即暂停/继续按钮，只能在用户进行游戏的状态下才能使用。在用户进行游戏的状态下，点击Pause按钮，当前迷宫游戏被暂停，游戏计时器、游戏计步器被停止，按钮上的Pause文字被立刻替换成Continue文字；当用户点击Continue按钮时，被暂停的迷宫游戏可以继续进行，游戏计时器、游戏计步器也在被停止的地方再次开始，按钮上的Continue文字被立刻替换成Pause文字。
3. Prompt按钮：即提示按钮，只能在用户进行游戏的状态下才能使用。在用户进行游戏的状态下，Prompt按钮的主要提示功能是在迷宫主界面上会显示出一条绿色的由用户当前的游戏位置到迷宫出口处的路径，进而对用户走出迷宫的路径进行提示。在用户点击Prompt按钮时，会跳出一个对话框要求用户指定提示路径的显示时间，用户可选择的显示时间有：1s、3s、5s、10s、forever。
4. Play do按钮：即指定游戏转换为用户进行游戏状态的按钮，只能在计算机进行游戏的状态下才能使用。在用户进行游戏的状态下，点击Play do按钮，当前计算机进行的游戏行为被立刻终止，游戏状态切换到用户进行游戏的状态，将进行游戏的控制权由计算机转交给用户。
5. Computer do按钮：即指定游戏转换为计算机进行游戏状态的按钮，只能在用户进行游戏的状态下才能使用。在用户进行游戏的状态下，点击Computer do按钮，会跳出一个对话框要求用户指定计算机进行游戏时每走一步的所用速度，用户可选择的速度有：lower seed 、low speed、 medium speed、 high speed、 higher speed。用户选择后，当前用户进行的游戏行为被立刻终止，游戏状态切换到计算机进行游戏的状态，将进行游戏的控制权由用户转交给计算机。在计算机取得游戏控制权后，程序将使用参数设置模块中用户选中的迷宫寻路算法计算出从游戏入口到游戏出口的路径，并按用户进行游戏的形式按用户选择的进行游戏时每走一步的所用时间将从入口到出口的行走路径演示一遍。
6. 声音设置按钮：即指定是否开启背景音乐的按钮，在用户进行游戏的状态和计算机进行游戏的状态两种状态下均可使用。
### 迷宫主界面模块
本游戏软件刚刚运行时刻，迷宫主界面模块的显示图如下图所示：

![本游戏软件刚刚运行时刻，迷宫主界面模块的显示图](https://img-blog.csdnimg.cn/20181225152310967.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3JpY2hlbnl1bnFp,size_16,color_FFFFFF,t_70)

整个迷宫使用大量的方格（正方形）进行显示，其中，可行走的路径用白色方格显示，不可行走的路径即障碍物用黑色方格表示，出口用红色方格表示，用户用于行走的标识用绿色小球（圆形）表示。用户通过键盘上上下左右的方向键操纵小球在迷宫主界面上运动。

参数设计模块中的ROWS（即迷宫行数）、COLS（即迷宫列数）在迷宫主界面的表示分别指的是迷宫主界面中每行、每列方格的数量，参数设计模块中的Lattice’s width（即组成迷宫的格子的宽度）在迷宫主界面的表示是迷宫主界面中每个方格的宽。此外，在迷宫主界面模块的正上方，存在统计用户进行游戏的当前时刻的所用时间和所走步数的计时器和计步器。
### 迷宫整体界面

![迷宫游戏界面](https://img-blog.csdnimg.cn/20181225153650242.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3JpY2hlbnl1bnFp,size_16,color_FFFFFF,t_70)

# 软件设计方案
## 软件相关原理说明
要设计一款迷宫的游戏软件，其中最主要也是必须要解决的两大主要问题就是如何去生成一个随机的迷宫以及如何在一个随机生成的迷宫中找到从迷宫入口到迷宫出口的路径。在图论中，这两个问题的表示就是：
1. 如何随机生成一个无权连通图
2. 如何在一个找到一个无权连通图中任意两点间的路径

关于这两个问题的解决，目前存在许多算法，本款软件采取并实现了目前应用最为广泛的三大随机无权连通图生成算法：
 - Depth First Search Algorithm（深度优先搜索算法）
 - Randomized Prim's Algorithm（随机普利姆算法）
 - Recursive Division Algorithm（递归分割算法）
 
 以及两大无权连通图遍历算法：
  - Depth First Search Algorithm（深度优先搜索算法）
  - Breadth First Search Algorithm（广度优先搜索算法）
 
## 迷宫生成算法
在对迷宫生成算法进行具体阐述之前，有两个概念首先要明确定义一下：迷宫单元和墙。迷宫单元可以映射到无权连通图中的点，而墙壁则可以映射到无权连通图中两点之间的边。如果墙壁是打通的，则记为无权连通图中的墙壁两侧的点间有边；如果墙壁是没有打通的，则记为无权连通图中的墙壁两侧的点间没有边。要生成一个随机的迷宫，就需要做到迷宫中任意两个迷宫单元之间都有一条路径。

我们用二维数组表示一个迷宫，每个迷宫单元表示为一个二维数组元素，由于生成算法的限制，迷宫的行数和列数均需设置为奇数，在了解了相关算法原理后，就能明白其中原因。在下列迷宫生成算法执行之前，假设位于奇数行奇数列的点为迷宫单元，位于偶数行或者偶数列的点为墙壁，且所有的点均初始化为未访问状态、不可通过状态。
### Depth First Search Algorithm（深度优先搜索算法）
#### 算法描述
1. 将起点作为当前迷宫单元并标记为已访问  
2. 当还存在未标记的迷宫单元，进行循环  
    1. 如果当前迷宫单元有未被访问过的的相邻的迷宫单元  
          1. 随机选择一个未访问的相邻迷宫单元  
          2. 将当前迷宫单元入栈  
          3. 移除当前迷宫单元与相邻迷宫单元的墙  
          4. 标记相邻迷宫单元并用它作为当前迷宫单元  
    2. 如果当前迷宫单元不存在未访问的相邻迷宫单元，并且栈不空  
          1. 栈顶的迷宫单元出栈  
          2. 令其成为当前迷宫单元 
#### 生成的迷宫特点
一般来说，Depth First Search Algorithm生成的迷宫极度扭曲，有着一条明显的主路。生成的51行51列迷宫如下图所示：

![由Depth First Search Algorithm生成的51行51列迷宫](https://img-blog.csdnimg.cn/20181225153133563.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3JpY2hlbnl1bnFp,size_16,color_FFFFFF,t_70)

### Randomized Prim's Algorithm（随机普利姆算法）
#### 算法描述
1. 让迷宫全是墙.  
2. 随机选一个单元格作为迷宫的通路，然后把它的邻墙放入列表  
3. 当列表里还有墙时  
    1. 从列表里随机选一个墙，如果这面墙分隔的两个单元格只有一个单元格被访问过  
        1. 那就从列表里移除这面墙，即把墙打通，让未访问的单元格成为迷宫的通路
         2. 把这个格子的墙加入列表  
    2. 如果墙两面的单元格都已经被访问过，那就从列表里移除这面墙
#### 生成的迷宫特点
相对于深度优先的算法，Randomized Prim's Algorithm不是优先选择最近选中的单元格，而是随机的从所有的列表中的单元格进行选择，新加入的单元格和旧加入的单元格同样概率会被选择，新加入的单元格没有优先权。因此其分支更多，生成的迷宫更复杂，岔路更多，难度更大，也更自然。生成的51行51列迷宫如下图所示：

![由Randomized Prim's Algorithm生成的51行51列迷宫](https://img-blog.csdnimg.cn/20181225153207186.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3JpY2hlbnl1bnFp,size_16,color_FFFFFF,t_70)

### Recursive Division Algorithm（递归分割算法）
#### 算法描述
1. 让迷宫全是迷宫单元
2. 随机选择一偶数行和一偶数列让其全部变为墙，通过这两堵墙将整个迷宫分为四个子迷宫
3. 在3面墙上各挖一个洞（为了确保连通）
4. 如果子迷宫仍可分割成四个子迷宫，返回1. 继续分割子迷宫
#### 生成的迷宫特点
Recursive Division Algorithm十分高效，生成的迷宫较为简单，有点像四叉树，直路多且不扭曲。生成的51行51列迷宫如下图所示：

![由Recursive Division Algorithm生成的51行51列迷宫](https://img-blog.csdnimg.cn/2018122515323874.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3JpY2hlbnl1bnFp,size_16,color_FFFFFF,t_70)

## 迷宫寻路算法
由于迷宫相当于连通图，所以通过使用图论算法中由任意一点出发遍历整个连通图的其他所有顶点的遍历算法即可找到一条从迷宫入口到迷宫出口的路径，本游戏软件使用的是目前应用最为广泛的Depth First Search Algorithm（深度优先搜索算法）和Breadth First Search Algorithm（广度优先搜索算法）。注意，此时迷宫中所有可行走的点均视为迷宫单元，所有不可行走的点均视为墙壁。
### Depth First Search Algorithm（深度优先搜索算法）
#### 算法描述
1. 访问入口顶点v，并以此顶点为当前顶点
2. 将当前顶点的未被访问的邻接点压入栈中
3. 弹栈，将弹出的顶点作为当前顶点
4. 若当前顶点没有未被访问的邻接点且栈不空，重复第3步，否则，重复第2步
5. 重复第3、4步，直至搜索到出口顶点
#### 生成的路径特点：
Depth First Search Algorithm 在由Recursive Division Algorithm生成的51行51列迷宫中生成的路径如下图所示：

![Depth First Search Algorithm生成的路径](https://img-blog.csdnimg.cn/20181225153459916.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3JpY2hlbnl1bnFp,size_16,color_FFFFFF,t_70)

### Breadth First Search Algorithm（广度优先搜索算法）
#### 算法描述
1. 访问入口顶点v，并以此顶点为当前顶点
2. 将当前顶点的未被访问的邻接点压入栈中
3. 弹栈，将弹出的顶点作为当前顶点
4. 若当前顶点没有未被访问的邻接点且栈不空，重复第3步，否则重复第2步
5. 重复第3、4步，直至搜索到出口顶点
#### 生成的路径特点：
 Breadth First Search Algorithm 在由Recursive Division Algorithm生成的51行51列迷宫中生成的路径如下图所示：
 
 ![Breadth First Search Algorithm生成的路径](https://img-blog.csdnimg.cn/20181225153603624.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3JpY2hlbnl1bnFp,size_16,color_FFFFFF,t_70)
 
