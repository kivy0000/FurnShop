package com.kfhstu;



/**
 * 当前ssm项目笔记：
 * 以下的配置大部分都可以使用注解解决，灵活应用
 *    1.配置  * 容器的配置（web.xml文件中）
 *             1) 配置上下文参数,让监听器读取xml文件并启动spring容器
 *                   <context-param>
 *                      <param-name>contextConfigLocation</param-name>
 *                      <param-value>classpath:applicationContext.xml</param-value>
 *                   </context-param>
 *             2) 配置监听器，监听web容器（tomcat的启动）让监听器启动spring容器-->
 *                  <listener>
 *                      <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
 *                  </listener>
 *             3) 配置前端控制器，唯一的servlet，和其他插件
 *
 *          * springmvc的配置（sp...-servlet.xml文件中）
 *             1) 默认使用前端控制器名字-servlet.xml在web-inf下 ，也可以通过初始化参数指定SpringMVC配置文件的位置和名称
 *             2) 只让springmvc接管controller注解，其余的交给spring
 *
 *
 *           * spring的配置（applicationContext.xml文件中）
 *             1) 配置要扫描的包（除了controller）
 *             3)配置外部数据源
 *
 *           * mybatis整合spring的配置（首先引入和spring的适配包）
 *              1)配置全局配置文件，数据源/池，mapper.xml文件的位置
 *              2)配置扫描器，将所有mybatis相关的mapper接口的实现放入ioc容器中，mapper的接口放置在dao包中
 *              3)配置数据管理
 *
 *     2.mybatis逆向工程：根据表快速生成bean/pojo/mapper和对应xml文件
 *             1)配置指定包全局别名
 *             2)引入逆向工程包
 *             3)配置文件（一般直接放pom路径）,mbg.xml,注意useSSL=false
 *             4)创建测试文件，运行一次并生成相关内容
 *       注意: 5)insertSelective和insert，Selective选择性的，insertSelective会将设置的对象字段放入sql语句中，未设置则不指定值
 *                  而insert则会填入所有字段，未设置则写入null值，推荐insertSelective
 *                  其他增删改查方法同理
 *      注意：当数据库连接超过八小时（28800），时，会出现问题，记得修改超时时间,其次是ip和主机的访问数据库权限问题
 *
 *
 *   3. 搭建前端开发环境：使用vue-cli脚手架工具（veu3）
 *          1)安装node.js（类似于浏览器，一个可以解析js语言的工具,包含了npm）
 *          2)全局安装vue.cli
 *          3)创建前端项目（前后端分离）, cd ssm_vue  npm run serve,运行起来，搭建完成ctrl +c 终止
 *          4)使用idea打开,配置npm
 *          5) vue3项目的路由机制：
 *                  1.在app.vue编译后，会替换div=app的层
 *                  2.<nav>
 *                      <router-link to="/">Home</router-link> |
 *                      <router-link to="/about">About</router-link>
 *                   </nav>(经常被用作导航)
 *                  这里是路由导航界面，根据不同的url，通过index.js，指向不同的视图
 *                 3. <router-view/> 路由指令，进行动态视图的替换，<template>标签类似于html标签
 *                  导向了homeView.vue，在homeVue中，通过import HelloWorld from '@/components/HelloWorld.vue'  (@/为src目录)
 *                 导入helloWord组件，并使用组件展示下面的内容 <HelloWorld msg="Welcome to Your Vue.js App"/> (msg内容会被带入组件)
 *                 4.目录指引：index.html ：项目的首页；
 *                            app.vue ：编译后，会替换div=app的层
 *                            assets目录：放资源文件，比如图片
 *                            components目录：放自定义组件,比如helloWord.vue
 *                            router/index.js：用于路由指向
 *                            store/index.js ：用于存放数据
 *                            views：存放视图
 *                            main.js：用于引入配置的资源/组件等
 *                            package.json:类似于pom，存放依赖
 *          6)配置vue项目的端口：添加vue.config.js的配置
 *                      module.export = { devServer: { port: 10000}}
 *
 *          7)使用Element Plus： 组件库
 *              Element Plus是针对vue3的 , UI则是针对vue2的
 *              Element UI官方文档: https://element.eleme.cn/#/zh-CN
 *              Element Plus官方文档: https://element-plus.gitee.io/zh-CN/
 *              npm install element-plus --save 安装
 *              如何使用查看文档
 *
 *    4.搭建前端界面（使用vue3+elementPlus）
 *          1)修改app.vue文件,homeView.vue,删除helloWord.vue
 *          2)创建Heed.vue，作为顶部导航栏,分为三个部分
 *          3)将header组件引入到APP.VUE中
 *          4)创建全局css文件global.css，引入css
 *          5)引入elementPlus，记得安装，在main.js中import ElementPlus from 'element-plus' import 'element-plus/dist/index.css'
 *          6)在Head.vue中放入下拉框
 *          7)创建侧边栏和主界面
 *          8)主界面填充表格，国际化
 *          9)添加日期排序，sortable
 *
 *   5.实现添加功能：
 *      前端：弹窗，表单，提交到后端
 *      后端：controller层接收响应，service层调用dao层完成服务
 *
 *      后端：
 *      1)所有service的方法已配置事务管理
 *      2)对每一项进行单元测试
 *      3) 使用StringUtils.HanText判断是否为空,或者Optional
 *      4)创建要相应给前端的json数据类对象Msg
 *      5)创建controller,引入jackson处理json数据,
 *           注意：- postman测试，注意contentType，否则415错误
 *                - 未使用requestBody注解，500错误
 *                - 如若要返回json数据，需要responseBody注解，否则404错误
 *
 *
 *
 *
 *      前端：
 *      1)点击提交，出现表单弹窗
 *      2)前端添加axios，生成axios对象，发送json请求
 *      3)在homeView中添加save方法，使用axios发送请求,
 *          注意：跨域问题(前端)Access to XMLHttpRequest at 'http://localhost:8080/ssm/save' from origin
 *               使用vue config配置拦截器解决
 *
 *  6.实现显示家具的功能：
 *
 *      前端：修改homeView，增加list方法,
 *          钩子函数访问，双向绑定到tableData
 *          配置响应拦截器，将响应转换为json对象
 *
 *      后端：配置service方法,findALL，postman测试
 *
 *
 *  7.实现修改家具功能：
 *      前端：<template #default="scope">,通过插头机制，将本行数据传递出去
 *          弹窗回显当前行的数据
 *           save方法判断,修改,弹窗方法修改
 *          注意：由于异步刷新机制，res=>函数会等到有响应执行，而剩余代码会直接执行，所以刷新列表要放在函数体内
 *
 *      后端：增加update方法
 *
 *
 *  8.实现删除家具功能：
 *
 *          前端：添加删除成功和失败的提示，axios发送请求，修改按钮
 *                假的登陆验证loginView.vue
 *          后端：添加方法，删除 
 *
 *  9.实现分页功能：
 *              前端：加入分页组件
 *              后端：mybatis pageHelper
 *                  原生web中，使用page=1去改变查询条件，返回不同的books
 *                  现在使用插件，首先引入依赖，然后在mybatis-config.xml文件中配置plugins
 *                  使用pageHelper,返回分页后的pageInfo
 *              本质仍是limit ？（从第几条开始查） ？（查多少条）和count（*）的sql语句
 *
 *  10.实现关键字查找功能：
 *          前端：修改list方法（思路：增加一个关键字，默认给空值，这样不用的时候不改变查询条件）
 *          后端：更改list方法，添加search系数
 *
 *  11.实现验证和校验（添加和修改的时候）：
 *          前端：添加表单验证
 *                添加验证不通过后，save方法拒绝提交的内容
 *          后端：校验(防止前端校验被绕过)
 *          使用JSR303和hibernate validator，比如notEmpty(相比notNull更适合字符串)注解
 *          添加hibernate-validator jar包，在字段上添加验证注解
 * 12.总结：这是一个ssm项目，前后端分离，
 *          前端：主要是vue，node.js，element plus，axios
 *          后端：ssm，逆向工程，pageHelper，hibernate vial(验证)
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
//@ComponentScan(basePackages = "com.kfhstu.furn.controller",useDefaultFilters = false,includeFilters =
//                {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class)}
//                )
//    @Repository
public class SSMBase {
}
