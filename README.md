> 天猫整站SSM

# 一、项目结构

- 项目名称 
    - tmall_ssm
- java源代码包结构
    - pojo 实体类
    - mapper Mapper类
    - interceptor 拦截器
    - controller 控制层
    - service Service层
    - test 测试类
    - util 工具类
    - comparator 比较类
- web目录
    - css css文件
    - img 图片资源
    - js js文件
    - admin 后台管理用到的jsp文件
    - fore 前台展示用到的jsp文件
    - include 被包含的jsp文件

![folder.png](https://upload-images.jianshu.io/upload_images/688387-ab4277eae3409d2a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


# 二、项目配置

## 1、创建数据库
- 创建数据库:tmall_ssm
- 并且将数据库的编码设置为utf8，便于存放中文

```
DROP DATABASE IF EXISTS tmall_ssm;
CREATE DATABASE tmall_ssm DEFAULT CHARACTER SET utf8;
```


```
CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE category (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE property (
  id int(11) NOT NULL AUTO_INCREMENT,
  cid int(11) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_property_category FOREIGN KEY (cid) REFERENCES category (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE product (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  subTitle varchar(255) DEFAULT NULL,
  originalPrice float DEFAULT NULL,
  promotePrice float DEFAULT NULL,
  stock int(11) DEFAULT NULL,
  cid int(11) DEFAULT NULL,
  createDate datetime DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_product_category FOREIGN KEY (cid) REFERENCES category (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE propertyvalue (
  id int(11) NOT NULL AUTO_INCREMENT,
  pid int(11) DEFAULT NULL,
  ptid int(11) DEFAULT NULL,
  value varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_propertyvalue_property FOREIGN KEY (ptid) REFERENCES property (id),
  CONSTRAINT fk_propertyvalue_product FOREIGN KEY (pid) REFERENCES product (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE productimage (
  id int(11) NOT NULL AUTO_INCREMENT,
  pid int(11) DEFAULT NULL,
  type varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_productimage_product FOREIGN KEY (pid) REFERENCES product (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE review (
  id int(11) NOT NULL AUTO_INCREMENT,
  content varchar(4000) DEFAULT NULL,
  uid int(11) DEFAULT NULL,
  pid int(11) DEFAULT NULL,
  createDate datetime DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_review_product FOREIGN KEY (pid) REFERENCES product (id),
    CONSTRAINT fk_review_user FOREIGN KEY (uid) REFERENCES user (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE order_ (
  id int(11) NOT NULL AUTO_INCREMENT,
  orderCode varchar(255) DEFAULT NULL,
  address varchar(255) DEFAULT NULL,
  post varchar(255) DEFAULT NULL,
  receiver varchar(255) DEFAULT NULL,
  mobile varchar(255) DEFAULT NULL,
  userMessage varchar(255) DEFAULT NULL,
  createDate datetime DEFAULT NULL,
  payDate datetime DEFAULT NULL,
  deliveryDate datetime DEFAULT NULL,
  confirmDate datetime DEFAULT NULL,
  uid int(11) DEFAULT NULL,
  status varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_order_user FOREIGN KEY (uid) REFERENCES user (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE orderitem (
  id int(11) NOT NULL AUTO_INCREMENT,
  pid int(11) DEFAULT NULL,
  oid int(11) DEFAULT NULL,
  uid int(11) DEFAULT NULL,
  number int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_orderitem_user FOREIGN KEY (uid) REFERENCES user (id),
  CONSTRAINT fk_orderitem_product FOREIGN KEY (pid) REFERENCES product (id),
  CONSTRAINT fk_orderitem_order FOREIGN KEY (oid) REFERENCES order_ (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

```

## 2、导入ssm项目
大部分ssm项目都是现成的，不需要自己从头创建，所以本知识点演示如何把现成maven风格的ssm项目导入到Idea中去。

- File->New->Project from existing sources 输入
- 注：这里一定要指定pom.xml文件，而非目录。
- 后续的就一直点Next就行了
- 等IDEA右下角进度条加载完成即可


## 3、配置tomcat
- Run -> Edit Configurations -> Tomcat -> local ->
- 选择本地tomcat 目录
- 选择

## 4、修改配置文件

> jdbc.properties

```
#数据库配置文件
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/tmall_ssm?useUnicode=true&characterEncoding=utf8
jdbc.username=root
jdbc.password=1234567890
```
## 5、运行结果

> 运行log
```
DEBUG [http-bio-8080-exec-7] - ooo Using Connection [com.mysql.jdbc.JDBC4Connection@5a701b1a]
DEBUG [http-bio-8080-exec-7] - ==>  Preparing: select 'false' as QUERYID, id, name from category order by id desc 
DEBUG [http-bio-8080-exec-7] - ==> Parameters: 
DEBUG [http-bio-8080-exec-7] - ooo Using Connection [com.mysql.jdbc.JDBC4Connection@5a701b1a]
DEBUG [http-bio-8080-exec-7] - ==>  Preparing: select 'false' as QUERYID, id, name from category order by id desc 
DEBUG [http-bio-8080-exec-7] - ==> Parameters: 
DEBUG [http-bio-8080-exec-10] - ooo Using Connection [com.mysql.jdbc.JDBC4Connection@5a701b1a]
DEBUG [http-bio-8080-exec-10] - ==>  Preparing: select 'false' as QUERYID, id, name from category order by id desc 
DEBUG [http-bio-8080-exec-10] - ==> Parameters: 
DEBUG [http-bio-8080-exec-10] - ooo Using Connection [com.mysql.jdbc.JDBC4Connection@5a701b1a]
DEBUG [http-bio-8080-exec-10] - ==>  Preparing: select 'false' as QUERYID, id, name from category order by id desc 
DEBUG [http-bio-8080-exec-10] - ==> Parameters: 
DEBUG [http-bio-8080-exec-5] - ooo Using Connection [com.mysql.jdbc.JDBC4Connection@5a701b1a]
DEBUG [http-bio-8080-exec-5] - ==>  Preparing: select 'false' as QUERYID, id, name from category order by id desc 
DEBUG [http-bio-8080-exec-5] - ==> Parameters: 
DEBUG [http-bio-8080-exec-5] - ooo Using Connection [com.mysql.jdbc.JDBC4Connection@5a701b1a]
DEBUG [http-bio-8080-exec-5] - ==>  Preparing: select 'false' as QUERYID, id, name from category order by id desc 
DEBUG [http-bio-8080-exec-5] - ==> Parameters: 
DEBUG [http-bio-8080-exec-5] - ooo Using Connection [com.mysql.jdbc.JDBC4Connection@5a701b1a]
DEBUG [http-bio-8080-exec-5] - ==>  Preparing: select 'false' as QUERYID, id, name from category order by id desc 
DEBUG [http-bio-8080-exec-5] - ==> Parameters: 
DEBUG [http-bio-8080-exec-5] - ooo Using Connection [com.mysql.jdbc.JDBC4Connection@5a701b1a]
DEBUG [http-bio-8080-exec-5] - ==>  Preparing: select 'false' as QUERYID, id, name from category order by id desc 
DEBUG [http-bio-8080-exec-5] - ==> Parameters: 
```

> 页面跳转到前端：`http://localhost:8080/forehome#nowhere`

![tmall.png](https://upload-images.jianshu.io/upload_images/688387-36632aa2ff8a1b18.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


> 后台管理页面：`http://localhost:8080/admin_category_list`

![backbond.png](https://upload-images.jianshu.io/upload_images/688387-19692227bf356df4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


# 三、功能模块

- 分类管理
- 用户管理
- 订单管理

![分页](http://how2j.cn/img/site/step/6451.png)

![ER.png](https://upload-images.jianshu.io/upload_images/688387-616805d25ed65b3c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
