JMS（JAVA Message Service,java消息服务）
	是一个消息服务的标准或者说是规范，允许应用程序组件基于JavaEE平台创建、发送、接收和读取消息。它使分布式通信耦合度更低，消息服务更加可靠以及异步性。

参考资料
	阿里云MQ				https://help.aliyun.com/document_detail/29532.html?spm=5176.doc29532.6.539.uaP3Co
	使用消息队列的 10 个理由		https://www.oschina.net/translate/top-10-uses-for-message-queue
	消息队列的两种模式			http://blog.csdn.net/heyutao007/article/details/50131089
	ActiveMQ			http://blog.csdn.net/jiuqiyuliang/article/details/46701559
	Spring + activeMQ	http://ihenu.iteye.com/blog/2270078
	RabbitMQ入门安装及测试	http://blog.csdn.net/boonya/article/details/37879739
	Spring + RabbitMQ	http://www.cnblogs.com/libra0920/p/6230421.html

实践
	ActiveMQ 启动报无法连接JVM时，修改配置：wrapper.java.command 后的 java bin地址 。如wrapper.java.command=C:\ProgramData\Oracle\Java
	RabbitMQ 
			 1.ERLang安装			http://www.erlang.org/downloads								cmd->erl 验证成功
			 2.RabbitMQ安装		http://www.rabbitmq.com/download.html
			 rabbitmq管理界面开启 	http://blog.csdn.net/yongche_shi/article/details/53319770	用户名和密码（默认为guest）
	
总结
JMS “ 一个中心，两种模式，三步实现”
	1.以 消息服务器为中心
		消息生产者 通过客户端发消息给消息服务器； 消息消费者通过消息服务器接收消息；
	2. 两种消息发送模型规范：
		点对点		（PTP 消息传递模型规定了一条消息只能传递给一个接收方。 采用javax.jms.Queue 表示）		一个消息只有一个接收者
		发布订阅 	（Pub/sub 消息传递模型允许一条消息传递给多个接收方。采用javax.jms.Topic表示）			一个消息可以有多个接收者
	3 .实现方法分为三步
		1、 统一消息服务器，建立连接Connections ；
		2 、通过连接建立队列会话session；
		3 、准备就绪后，执行 生产者 发消息和消费者 接消息（异步）。
		
使用场景：
	在某些情况下，由于SessionBean方法的执行时间比较长，这就需要异步地调用该方法，否则客户端就需要等待比较长的时间。要实现异步调用， 就需要使用消息驱动Bean。
	消息驱动Bean的基本原理是客户端向消息服务器发送一条消息后，消息服务器会将该消息保存在消息队列中。在这时消 息服务器中的某个消费者（读取并处理消息的对象）会读取该消息，并进行处理。发送消息的客户端被称为消息生产者。

JMS中的消息
	消息传递系统的中心就是消息。一条 Message 由三个部分组成： 头（header）,属性（property）和主体（body）。
 	消息有下面几种类型，他们都是派生自 Message 接口。
		StreamMessage	一种主体中包含 Java 基元值流的消息。其填充和读取均按顺序进行。
		MapMessage		一种主体中包含一组名-值对的消息。没有定义条目顺序。
		TextMessage		一种主体中包含 Java 字符串的消息（例如，XML 消息）。
		ObjectMessage	一种主体中包含序列化 Java 对象的消息。
		BytesMessage	一种主体中包含连续字节流的消息
	
消息确认模式
	AUTO_ACKNOWLEDGE			自动确认模式，只有接收方有返回就算收到了，具体异常还是正常不要管。
	CLIENT_ACKNOWLEDGE			客户端确认模式，接收方要调用acknowledge方法，其它情况视为未接收，批量确认是有规定的，最后一个或一次消息调用acknowledge方法。
	DUPS_OK_ACKNOWLEDGE			允许重复确认模式
	NO_ACKNOWLEDGE				不确认模式
	MULTICAST_NO_ACKNOWLEDGE	IP组播下的不确认模式

一般发送消息有以下步骤：
	1.得到一个JNDI初始化上下文(Context)
		InitialContext ctx = new InitialContext();
	2.根据上下文查找一个连接工厂 QueueConnectionFactory 。该连接工厂是由JMS提供的，不需我们自己创建，每个厂商都为它绑定了一个全局JNDI，我们通过它的全局JNDI便可获取它；
		QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
	3.从连接工厂得到一个连接 QueueConnection
		conn = factory.createQueueConnection();
	4.通过连接来建立一个会话(Session); 
		session = conn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
	5.查找目标地址：
		Destination destination = (Destination ) ctx.lookup("queue/foshanshop"); //上面配置的那个目标地址
	6.根据会话以及目标地址来建立消息生产者MessageProducer （QueueSender和TopicPublisher都扩展自MessageProducer接口）
