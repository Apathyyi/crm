# crm
ssh整合的简易crm系统:
用于管理客户与联系人之间的关系，对相应的数据进行统计和增删改查。
主要有五大功
##一:用户管理员模块
通过拦截器对未登录的用户的请求进行拦截跳转到登陆界面，只有登录的用户才可以对数据进行操作；
细节：对用户注册和登录的表单均有简单的提示，同时对密码有简单的加密处理。
##二:客户管理模块
分页查询客户列表，可以对客户进行增删改和条件查询上传公司资质，删除客户时级联删除联系人。
细节:客户上传资质图片到磁盘并保存图片路径，防止重名和单个目录下文件过多采取随机文件名和目录分离。
##三:联系人管理模块

