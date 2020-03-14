# FinalExam
使用梨视频API，一个简陋版山寨梨视频

## 功能介绍
- 获取梨视频实时视频
- 获取评论列表
- 录视频
- 下拉刷新，上拉加载

## 代码结构

- 整个APP展示界面由两个Activity构成，播放视频是跳转的一个Activity，然后主界面是一个Activity
主界面中的切换板块都是Fragment
- 第一个界面是由Viewpager+Tablayout+Fragment画的
- 使用了MVP架构（虽然我自己也不清楚是不是真正的MVP）

## 关于库

- 使用了glide加载图片
- 使用了JZplayer
- 使用了一个权限申请的库
- 用了retrofit框架请求网络数据

### 最后

写了录制音频把pcm数据，加上文件头转换为wav文件并本地保存的，有时间再加进去。。。可能不会加了


