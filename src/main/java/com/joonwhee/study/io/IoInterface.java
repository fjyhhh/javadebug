package com.joonwhee.study.io;

/**
 * 小白也看得懂的 I/O 多路复用解析（超详细案例）
 *
 * @author: 程序员囧辉
 * @date: 2022/5/4 13:46
 */
public interface IoInterface {
///**
// * 获取就绪事件
// *
// * @param nfds      3个监听集合的文件描述符最大值+1
// * @param readfds   要监听的可读文件描述符集合
// * @param writefds  要监听的可写文件描述符集合
// * @param exceptfds 要监听的异常文件描述符集合
// * @param timeval   本次调用的超时时间
// * @return 大于0：已就绪的文件描述符数；等于0：超时；小于：出错
// */
//int select(int nfds,
//           fd_set *readfds,
//           fd_set *writefds,
//           fd_set *exceptfds,
//           struct timeval *timeout);
//
///**
// * 获取就绪事件
// *
// * @param pollfd  要监听的文件描述符集合
// * @param nfds    文件描述符数量
// * @param timeout 本次调用的超时时间
// * @return 大于0：已就绪的文件描述符数；等于0：超时；小于：出错
// */
//int poll(struct pollfd *fds,
//         unsigned int nfds,
//         int timeout);
//
//struct pollfd {
//    int fd;         // 监听的文件描述符
//    short events;   // 监听的事件
//    short revents;  // 就绪的事件
//}
//
//
//
///**
// * 创建一个epoll
// *
// * @param size epoll要监听的文件描述符数量
// * @return epoll的文件描述符
// */
//int epoll_create(int size);
//
///**
// * 事件注册
// *
// * @param epfd        epoll的文件描述符，epoll_create创建时返回
// * @param op          操作类型：新增（1）、删除（2）、更新（3）
// * @param fd          本次要操作的文件描述符
// * @param epoll_event 需要监听的事件：读事件、写事件等
// * @return 如果调用成功返回0, 不成功返回-1
// */
//int epoll_ctl(int epfd,
//              int op,
//              int fd,
//              struct epoll_event *event);
//
///**
// * 获取就绪事件
// *
// * @param epfd      epoll的文件描述符，epoll_create创建时返回
// * @param events    用于回传就绪的事件
// * @param maxevents 每次能处理的最大事件数
// * @param timeout   等待I/O事件发生的超时时间，-1相当于阻塞，0相当于非阻塞
// * @return 大于0：已就绪的文件描述符数；等于0：超时；小于：出错
// */
//int epoll_wait(int epfd,
//               struct epoll_event *events,
//               int maxevents,
//               int timeout);
}
