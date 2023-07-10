# Redis
    Redis 数据结构
        
        String
        
        Hash
    单值缓存、对象缓存
    set user:1 value (json格式数据)
    Mset user:1 name  zhangsan user:1 balance 111 直接存储，频繁更新的字段值方便
    Mget 获取result
    
    分布式锁 
    setnx user:1001 ture //返回1 表示获取锁成功 示例
    
    计数器 原子加+1
    incr 
    分布式全局序列Id
    incr
    
    hash 结构 双map （缓存对象）电商购物车场景
    hmset user 1:name zhouyang 1:age 22 1:amount 10
    hmget user 1:name
    hlen key 获取总数
    Hgetall key 获取所有数据

        list 队列存放 栈、堆 ，应用场景：微博消息，公众号消息
    lpush  往左边放元素
    rpush 往右边放元素
    栈 =  lpush + Lpop  后进先出
    队列 = lpus + rpop  先进先出
    阻塞队列 lpush + BRPOP  + timeout （超时时间）
    lrange key  0-4 获取消息
    
    set 应用场景：抽奖场景
    SADD key  value 
    SMEMBERS key  查询所有用户
    SRANDMEMBER key 4（count） 随机4个用户
    spop key 3 随机抽取3个用户，抽取后用户从set集合中剔除

    