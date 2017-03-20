Shiro
	Apache Shiro是一个强大且易用的Java安全框架,执行身份验证、授权、密码学和会话管理。

参考资料
	跟我学Shiro			http://jinnianshilongnian.iteye.com/blog/2018398
	Shiro与spring集成		http://blog.csdn.net/xiaoyao8903/article/details/53244835
	Apache Shiro 使用手册	http://kdboy.iteye.com/blog/1154644

实践
	开启Shiro的注解需要放置到  “SpringMVC” 中，在 “Shiro”中无效
		<!-- Enable Shiro Annotations for Spring-configured beans. Only run after the lifecycleBeanProcessor has run -->  
		<bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator" depends-on="lifecycleBeanPostProcessor">
			<property name="proxyTargetClass" value="true" />
		</bean>
		<bean class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
	    	<property name="securityManager" ref="securityManager"/>
		</bean>
		
		<!-- 无权限 控制后台不报错 -->  
	    <bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">  
	        <property name="exceptionMappings">  
	            <props>  
	                <prop key="org.apache.shiro.authz.UnauthorizedException">noPermission.html</prop>  
	                <prop key="java.lang.Throwable">405</prop>  
	            </props>  
	        </property>  
	    </bean>   
		
总结
	三个核心组件：Subject, SecurityManager 和 Realms.
		Subject：即“当前操作用户”。但是，在Shiro中，Subject这一概念并不仅仅指人，也可以是第三方进程、后台帐户（Daemon Account）或其他类似事物。它仅仅意味着“当前跟软件交互的东西”。
			但考虑到大多数目的和用途，你可以把它认为是Shiro的“用户”概念。
		Subject代表了当前用户的安全操作，SecurityManager则管理所有用户的安全操作。
		SecurityManager：它是Shiro框架的核心，典型的Facade模式，Shiro通过SecurityManager来管理内部组件实例，并通过它来提供安全管理的各种服务。
		Realm： Realm充当了Shiro与应用安全数据间的“桥梁”或者“连接器”。也就是说，当对用户执行认证（登录）和授权（访问控制）验证时，Shiro会从应用配置的Realm中查找用户及其权限信息。
		从这个意义上讲，Realm实质上是一个安全相关的DAO：
			它封装了数据源的连接细节，并在需要时将相关数据提供给Shiro。当配置Shiro时，你必须至少指定一个Realm，用于认证和（或）授权。配置多个Realm是可以的，但是至少需要一个。
			Shiro内置了可以连接大量安全数据源（又名目录）的Realm，如LDAP、关系数据库（JDBC）、类似INI的文本配置资源以及属性文件等。如果缺省的Realm不能满足需求，
			你还可以插入代表自定义数据源的自己的Realm实现。
			
	实现步骤：
		1.配置web.xml
		2.在spring-shiro.xml中配置shiro相关属性
		3.在spring-mvc.xml开启Shiro的注解
		4.自定义的Realm类
		5.各方法类中添加注解：@RequiresPermissions("***/***")
