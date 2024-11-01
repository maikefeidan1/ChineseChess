### 中文

#### 介绍

- **其他账号**：bilibili账号：麦克飞弹1 (UID: 1477694893)

- **项目简介：**
  一个用Java Swing开发的简单象棋游戏，仅包含游戏界面。游戏素材像素不高（来源于网络下载的素材）。

  此项目属于个人业余开发，作为对Java基础知识的练习，在某些方面上可能开发的不是很好。

  

- **启动游戏：**
  通过运行`start.java`启动游戏。游戏启动后，可以使用键盘键位进行对应操作。在游戏启动前，也可以在`start.java`中进行自定义初始化开局。目前只有可以设置开局是否黑方在下、自定义所有棋子和设置黑方先走（有注释示例）。注意，自定义的所有棋子需要符合规范，即对应位置和数量需要正确，否则会报错（如果报错了可以通过终端输出查看哪里出现自定义初始化错误）。因此，你需要了解象棋规则。如果不做任何自定义初始化设置，则为默认初始化开局。

#### 键位说明

- **A键：** 撤销（Undo）
- **D键：** 重做（Redo）
- **R键：** 重新开始
- **F键：** 翻转棋盘

#### 对象及方法说明

- **游戏对象（ChineseChess）:**
  全局唯一。

  - **方法：**
    - `setBlackOnBottom()`：设置开局黑方在下，红方在上。
    - `CustomAllPiece(Piece[] customPieces, boolean isBlackOnBottom)`：第一个参数接收一个棋子数组，如果棋子数组里的所有棋子全部初始化设置正确，则可以作为开局初始化棋子数组。
    - `setBlackFirst()`：设置开局黑方先走。

- **棋子对象（pieces包里的类）：**
  通过类名调用`createPiece`方法创建出对应棋子并设置横纵坐标。棋子的限制数量除了兵或卒（限制五个）和帅或将（限制一个）外，其他皆为限制两个。

  - **方法：**
    - `createPiece(int x, int y, int sign)`：第一个参数为棋子在棋盘上的横坐标(0 - 8)，第二个参数为棋子在棋盘上的纵坐标（0 - 9），设置不在指定范围内的数字无效（会报错）。第三个参数为棋子的标识，设置1会创建红棋，设置2会创建黑棋，其他数无效（会报错）。

---

### English

#### Introduction

- **Project Introduction:**
  A simple chess game developed in Java Swing, containing only the game interface. The game material is not high pixel (from the Internet download material).

  This project is a personal amateur development, as an exercise in the basic knowledge of Java, in some aspects may not be very good development.

  bilibili Account: Mike Missile 1 (UID: 1477694893)

- **Start the game:**
  Start the game by running 'start.java'. After the game starts, you can use the keyboard keys for corresponding operations. Before the game starts, it is also possible to customize the initial opening in 'start.java'. At present, you can only set whether the opening is black next, customize all pieces, and set black to go first (there are annotation examples). Note that all the customized pieces need to meet the specifications, that is, the corresponding position and number need to be correct, otherwise an error will be reported (if an error is reported, you can check the terminal output to see where the custom initialization error occurs). Therefore, you need to know the rules of chess. If you do not perform any custom initialization settings, the start is initialized by default.

#### Key description

- **A key:** Undo
- **D key:** Redo
- **R key:** Restart
- **F key:** Flip the board

#### Object and method description

- **Game Object (ChineseChess):**
  Globally unique.

  - **Methods:**
    - `setBlackOnBottom()`: Sets the black side at the bottom and the red side at the top.
    - `CustomAllPiece(Piece[] customPieces, boolean isBlackOnBottom)`: The first argument accepts an array of pieces, which can be initialized as an opening if all pieces in the array are initialized correctly.
    - `setBlackFirst()`: Sets the black team to go first.

- **Chess object (class in the pieces package):**
  Call the 'createPiece' method by the class name to create the corresponding piece and set the horizontal and vertical coordinates. The number of pieces is limited to two, except for pawns or pawns (limited to five) and shuai or generals (limited to one).

  - **Methods:**
    - `createPiece(int x, int y, int sign)`: The first parameter is the horizontal coordinate of the piece on the board (0-8), the second parameter is the vertical coordinate of the piece on the board (0-9), and the number set outside the specified range is invalid (an error will be reported). The third parameter is the identifier of the piece, setting 1 will create a red piece, setting 2 will create a black piece, other numbers are invalid (an error will be reported).
