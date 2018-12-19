package my;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;
import utils.utils;

/**
 * 
 * Redis ��ѧϰ
 * 
 * 3.0�汾���Ѿ������˼�Ⱥ���� 2.X�汾 û�м�Ⱥ���ܡ�
 * ���ʹ��2.x�汾���Դ���һ������ShardedJedis����ʵ�ֲַ�ʽ���档(������չ(����໥���������ӷ�����Ⱥ))
 * ShardedJedis�ǻ���һ���Թ�ϣ�㷨ʵ�ֵķֲ�ʽRedis��Ⱥ�ͻ���
 * 
 * redis�£����ݿ�����һ������������ʶ����������һ�����ݿ����ơ�Ĭ������£�һ���ͻ������ӵ����ݿ�0��
 * redis�����ļ�������Ĳ������������ݿ������� * 
 * /etc/redis/redis.conf * 
 * �ļ��У��и������� databases = 16 //Ĭ����16�����ݿ� 
 * 
 * ����select 1 ��ʾ�л���1���ݿ� ����redis
 * Ĭ���ǽ���0���ݿ⣬redis�����ݿ���0-15,ÿ�����п��Դ治ͬ����Ҫ�����ݣ�
 * ����redis����nosql�����������ݻ��棬Ҳ�������洢��ȱ�������������ơ�
 * 
 * @author LiZhenhua
 * 
 * 
 * 
 *         ������������ 1�����Ӳ������� quit���ر����ӣ�connection�� auth����������֤ help cmd��
 *         �鿴cmd���������磺help quit
 * 
 *         2���־û� save��������ͬ�����浽���� bgsave���������첽���浽����
 *         lastsave�������ϴγɹ������ݱ��浽���̵�Unixʱ�� shundown��������ͬ�����浽���̣�Ȼ��رշ���
 * 
 *         3��Զ�̷������ info���ṩ����������Ϣ��ͳ�� monitor��ʵʱת���յ������� slaveof���ı临�Ʋ�������
 *         config��������ʱ����Redis������
 * 
 *         4����value���������� exists(key)��ȷ��һ��key�Ƿ���� del(key)��ɾ��һ��key
 *         type(key)������ֵ������ keys(pattern)�������������pattern������key
 *         randomkey���������key�ռ��һ�� keyrename(oldname, newname)��������key
 *         dbsize�����ص�ǰ���ݿ���key����Ŀ expire���趨һ��key�Ļʱ�䣨s�� ttl�����һ��key�Ļʱ��
 *         select(index)����������ѯ move(key, dbindex)���ƶ���ǰ���ݿ��е�key��dbindex���ݿ�
 *         flushdb��ɾ����ǰѡ�����ݿ��е�����key flushall��ɾ���������ݿ��е�����key
 * 
 *         5��String set(key, value)�������ݿ�������Ϊkey��string����ֵvalue
 *         get(key)���������ݿ�������Ϊkey��string��value getset(key,
 *         value)��������Ϊkey��string������һ�ε�value mget(key1, key2,��, key
 *         N)�����ؿ��ж��string��value setnx(key, value)�����string������Ϊkey��ֵΪvalue
 *         setex(key, time, value)����������string���趨����ʱ��time mset(key N, value
 *         N)���������ö��string��ֵ msetnx(key N, value N)�������������Ϊkey i��string��������
 *         incr(key)������Ϊkey��string��1���� incrby(key,
 *         integer)������Ϊkey��string����integer decr(key)������Ϊkey��string��1����
 *         decrby(key, integer)������Ϊkey��string����integer append(key,
 *         value)������Ϊkey��string��ֵ����value substr(key, start,
 *         end)����������Ϊkey��string��value���Ӵ�
 * 
 *         6��List rpush(key, value)��������Ϊkey��listβ���һ��ֵΪvalue��Ԫ�� lpush(key,
 *         value)��������Ϊkey��listͷ���һ��ֵΪvalue�� Ԫ�� llen(key)����������Ϊkey��list�ĳ���
 *         lrange(key, start, end)����������Ϊkey��list��start��end֮���Ԫ�� ltrim(key,
 *         start, end)����ȡ����Ϊkey��list lindex(key, index)����������Ϊkey��list��indexλ�õ�Ԫ��
 *         lset(key, index, value)��������Ϊkey��list��indexλ�õ�Ԫ�ظ�ֵ lrem(key, count,
 *         value)��ɾ��count��key��list��ֵΪvalue��Ԫ�� lpop(key)�����ز�ɾ������Ϊkey��list�е���Ԫ��
 *         rpop(key)�����ز�ɾ������Ϊkey��list�е�βԪ�� blpop(key1, key2,�� key N,
 *         timeout)��lpop�����block�汾�� brpop(key1, key2,�� key N,
 *         timeout)��rpop��block�汾�� rpoplpush(srckey,
 *         dstkey)�����ز�ɾ������Ϊsrckey��list��βԪ�أ�
 * 
 *         ����������������������������������Ԫ����ӵ�����Ϊdstkey��list��ͷ��
 * 
 *         7��Set sadd(key, member)��������Ϊkey��set�����Ԫ��member srem(key, member)
 *         ��ɾ������Ϊkey��set�е�Ԫ��member spop(key) ��������ز�ɾ������Ϊkey��set��һ��Ԫ��
 *         smove(srckey, dstkey, member) ���Ƶ�����Ԫ�� scard(key) ����������Ϊkey��set�Ļ���
 *         sismember(key, member) ��member�Ƿ�������Ϊkey��set��Ԫ�� sinter(key1, key2,��key
 *         N) ���󽻼� sinterstore(dstkey, (keys)) ���󽻼������������浽dstkey�ļ��� sunion(key1,
 *         (keys)) ���󲢼� sunionstore(dstkey, (keys)) ���󲢼������������浽dstkey�ļ���
 *         sdiff(key1, (keys)) ���� sdiffstore(dstkey, (keys))
 *         ������������浽dstkey�ļ��� smembers(key) ����������Ϊkey��set������Ԫ��
 *         srandmember(key) �������������Ϊkey��set��һ��Ԫ��
 * 
 *         8��Hash hset(key, field, value)��������Ϊkey��hash�����Ԫ��field hget(key,
 *         field)����������Ϊkey��hash��field��Ӧ��value hmget(key,
 *         (fields))����������Ϊkey��hash��field i��Ӧ��value hmset(key,
 *         (fields))��������Ϊkey��hash�����Ԫ��field hincrby(key, field,
 *         integer)��������Ϊkey��hash��field��value����integer hexists(key,
 *         field)������Ϊkey��hash���Ƿ���ڼ�Ϊfield���� hdel(key,
 *         field)��ɾ������Ϊkey��hash�м�Ϊfield���� hlen(key)����������Ϊkey��hash��Ԫ�ظ���
 *         hkeys(key)����������Ϊkey��hash�����м� hvals(key)����������Ϊkey��hash�����м���Ӧ��value
 *         hgetall(key)����������Ϊkey��hash�����еļ���field�������Ӧ��value
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
public class RedisClient {
	
	public static int TIMEOUT=1000;
	public static int MAX_ACTIVE=100;
	public static int MAX_IDLE=30;
	public static int MAX_WAIT=1000;
	public static boolean TEST_ON_BORROW=true;

    private Jedis jedis;//����Ƭ��ͻ�������
    private JedisPool jedisPool;//����Ƭ���ӳ�
    
    private ShardedJedis shardedJedis;//��Ƭ��ͻ�������
    private ShardedJedisPool shardedJedisPool;//��Ƭ���ӳ�
    
    public RedisClient() 
    { 
        initialPool(); 
        initialShardedPool(); 
        shardedJedis = shardedJedisPool.getResource(); 
        jedis = jedisPool.getResource();         
    } 
 
    /**
     * Jedis�ͻ��˴���
     * ��ʼ������Ƭ�� -- ����
     */
    private void initialPool() 
    { 
        // �ػ������� 
        JedisPoolConfig config = new JedisPoolConfig();        
		config.setMaxTotal(MAX_ACTIVE);
		config.setMaxIdle(MAX_IDLE);
		config.setMaxWaitMillis(MAX_WAIT);
		config.setTestOnBorrow(TEST_ON_BORROW);
        
        jedisPool = new JedisPool(config,"127.0.0.1",6379);
    }
    
    /** 
     * SharedJedis�ͻ��˴���
     * ��ʼ����Ƭ�� -- ��Ⱥ
     */ 
    private void initialShardedPool() 
    { 
        // �ػ������� 
        JedisPoolConfig config = new JedisPoolConfig(); 
		config.setMaxTotal(MAX_ACTIVE);
		config.setMaxIdle(MAX_IDLE);
		config.setMaxWaitMillis(MAX_WAIT);
		config.setTestOnBorrow(TEST_ON_BORROW);
        // slave���� 
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(); 
        shards.add(new JedisShardInfo("127.0.0.1", 6379, "master")); 
//        shards.add(new JedisShardInfo("127.0.0.1", 6370, "master")); 
//        shards.add(new JedisShardInfo("127.0.0.1", 6371, "master")); 

        // ����� 
        shardedJedisPool = new ShardedJedisPool(config, shards); 
    } 
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new RedisClient().show(); 
    }

    @SuppressWarnings("deprecation")
	public void show() {     
//        KeyValueOperate(); 
//        ListOperate(); 
//        SetOperate();
//        SortedSetOperate();
        HashOperate(); 
//        jedisPool.returnResource(jedis);
//        shardedJedisPool.returnResource(shardedJedis);
    } 


    
    
    /**
	 * jedis��key-value����ɾ�ò飬��� key-value���ӣ�ɾ�����鿴
	 * �ж�ָ��key�Ĵ��ڡ���key������ʱ��Ĳ���
	 * 
	 * 
	 * 
	 * �鿴�� jedis.get("key001") ���ӣ� jedis.set("key001","value001")
	 * 
	 * ɾ���� jedis.del("key003")
	 * 
	 * �޸ģ� 1.jedis.set("key001","xxx") 2.jedis.append("key002","+appendString")
	 * 
	 * �Ƿ����:jedis.exists("key001"): �ж�ֵkey001�Ƿ����
	 * 
	 * һ����������
	 * jedis.mset("key201","value201","key202","value202","key203","value203"
	 * ,"key204","value204") һ���Ի�ȡ��
	 * jedis.mget("key201","key202","key203","key204") һ����ɾ���� jedis.del(new
	 * String[]{"key201", "key202"})
	 * 
	 * jedis.expire("key001", 5); //������ֵ����ʱ�� 
	 * jedis.persist("key001");	 //�Ƴ�key001������ʱ�� 
	 * jedis.ttl("key001"); //�鿴key001��ʣ������ʱ��
	 * jedis.type("key001"); //�鿴key�������ֵ������
	 * 
	 * 
	 */
    private void KeyValueOperate() 
    {  
        // ������� 
        System.out.println("��տ����������ݣ�"+jedis.flushDB());
        
        System.out.println("=============��=============");
        jedis.set("key001","value001");
        jedis.set("key002","value002");
        jedis.set("key003","value003");
        System.out.println("��������3����ֵ�����£�");
        System.out.println(jedis.get("key001"));
        System.out.println(jedis.get("key002"));
        System.out.println(jedis.get("key003"));
        System.out.println("�ж�ֵkey001�Ƿ���ڣ�"+jedis.exists("key001"));
        
        System.out.println("=============ɾ=============");  
        System.out.println("ɾ��key003��ֵ�ԣ�"+jedis.del("key003"));  
        System.out.println("��ȡkey003����Ӧ��ֵ��"+jedis.get("key003"));
        
        System.out.println("=============��=============");
        //1��ֱ�Ӹ���ԭ��������
        System.out.println("ֱ�Ӹ���key001ԭ�������ݣ�"+jedis.set("key001","value001-update"));
        System.out.println("��ȡkey001��Ӧ����ֵ��"+jedis.get("key001"));
        //2��ֱ�Ӹ���ԭ��������  
        System.out.println("��key002ԭ��ֵ����׷�ӣ�"+jedis.append("key002","+appendString"));
        System.out.println("��ȡkey002��Ӧ����ֵ"+jedis.get("key002")); 
   
        System.out.println("=============����ɾ���飨�����=============");
        /** 
         * mset,mgetͬʱ�������޸ģ���ѯ�����ֵ�� 
         * �ȼ��ڣ�
         * jedis.set("name","ssss"); 
         * jedis.set("jarorwar","xxxx"); 
         */  
        System.out.println("һ��������key201,key202,key203,key204�����Ӧֵ��"+jedis.mset("key201","value201",
                        "key202","value202","key203","value203","key204","value204"));  
        System.out.println("һ���Ի�ȡkey201,key202,key203,key204���Զ�Ӧ��ֵ��"+
                        jedis.mget("key201","key202","key203","key204"));
        System.out.println("һ����ɾ��key201,key202��"+jedis.del(new String[]{"key201", "key202"}));
        System.out.println("һ���Ի�ȡkey201,key202,key203,key204���Զ�Ӧ��ֵ��"+
                jedis.mget("key201","key202","key203","key204")); 
        System.out.println();

        utils.PrintLine("������ʱ��Ĳ���");
        System.out.println("key203����ʱ�䣺"+jedis.ttl("key203"));	//�鿴key001��ʣ������ʱ�� 
        System.out.println("����5�������ʱ��");
        jedis.expire("key203", 5);			//������ֵ����ʱ��
        System.out.println("�̳߳�˯����");
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("key203����ʱ�䣺"+jedis.ttl("key203"));			//�鿴key001��ʣ������ʱ��
        System.out.println("�Ƴ�����ʱ��");
        jedis.persist("key203");			//�Ƴ�key001������ʱ��
        System.out.println("key203����ʱ�䣺"+jedis.ttl("key203"));				//�鿴key001��ʣ������ʱ��        			
        System.out.println("key203�������ݵ����ͣ�"+jedis.type("key203"));	//�鿴key�������ֵ������
    }
    
    /**
     * jedis ��set���ϲ�����
     * 
	 * jedis.sadd(setname, value) �򼯺ϼ���Ԫ�� jedis.smembers(setname)
	 * ��ȡָ������Set<String> jedis.srem("sets", "element003") ɾ��ָ�������е�Ԫ��
	 * jedis.sismember("sets", "element001") �ж�Ԫ���Ƿ���ָ��������
	 * 
	 * �������Ͻ�����jedis.sinter("sets1", "sets2")
	 * �������ϲ�����jedis.sunion("sets1", "sets2")
	 * �������ϲ��jedis.sdiff("sets1", "sets2")  -- ��˳��sets1�У���sets2û�е�ֵ��������ֵ��һ��
	 * 
	 */
    private void SetOperate() 
    { 

        System.out.println("======================set=========================="); 
        // ������� 
        System.out.println("��տ����������ݣ�"+jedis.flushDB());
        
        System.out.println("=============��=============");
        System.out.println("��sets�����м���Ԫ��element001��"+jedis.sadd("sets", "element001")); 
        System.out.println("��sets�����м���Ԫ��element002��"+jedis.sadd("sets", "element002")); 
        System.out.println("��sets�����м���Ԫ��element003��"+jedis.sadd("sets", "element003"));
        System.out.println("��sets�����м���Ԫ��element004��"+jedis.sadd("sets", "element004"));
        System.out.println("�鿴sets�����е�����Ԫ��:"+jedis.smembers("sets")); 
        System.out.println();
        
        System.out.println("=============ɾ=============");
        System.out.println("����sets��ɾ��Ԫ��element003��"+jedis.srem("sets", "element003"));
        System.out.println("�鿴sets�����е�����Ԫ��:"+jedis.smembers("sets"));
        /*System.out.println("sets����������λ�õ�Ԫ�س�ջ��"+jedis.spop("sets"));//ע����ջԪ��λ�þ�Ȼ������--��ʵ������
        System.out.println("�鿴sets�����е�����Ԫ��:"+jedis.smembers("sets"));*/
        System.out.println();
        
        System.out.println("=============��=============");
        System.out.println();
        
        System.out.println("=============��=============");
        System.out.println("�ж�element001�Ƿ��ڼ���sets�У�"+jedis.sismember("sets", "element001"));
        System.out.println("ѭ����ѯ��ȡsets�е�ÿ��Ԫ�أ�");
        Set<String> set = jedis.smembers("sets");   
        Iterator<String> it=set.iterator() ;   
        while(it.hasNext()){   
            Object obj=it.next();   
            System.out.println(obj);   
        }  
        System.out.println();
        
        System.out.println("=============��������=============");
        System.out.println("sets1�����Ԫ��element001��"+jedis.sadd("sets1", "element001")); 
        System.out.println("sets1�����Ԫ��element002��"+jedis.sadd("sets1", "element002")); 
        System.out.println("sets1�����Ԫ��element003��"+jedis.sadd("sets1", "element003")); 
        System.out.println("sets1�����Ԫ��element002��"+jedis.sadd("sets2", "element002")); 
        System.out.println("sets1�����Ԫ��element003��"+jedis.sadd("sets2", "element003")); 
        System.out.println("sets1�����Ԫ��element004��"+jedis.sadd("sets2", "element004"));
        System.out.println("�鿴sets1�����е�����Ԫ��:"+jedis.smembers("sets1"));
        System.out.println("�鿴sets2�����е�����Ԫ��:"+jedis.smembers("sets2"));
        System.out.println("sets1��sets2������"+jedis.sinter("sets1", "sets2"));
        System.out.println("sets1��sets2������"+jedis.sunion("sets1", "sets2"));
        System.out.println("sets1��sets2���"+jedis.sdiff("sets1", "sets2"));//���set1���У�set2��û�е�Ԫ��
        System.out.println("sets2��sets1���"+jedis.sdiff("sets2", "sets1"));//���set2���У�set1��û�е�Ԫ��
        
    }
    
    
    /**
     * jedis �� List<T>����Ĳ���
     * 
     * jedis.lpush("�б���", "Ԫ��ֵ"); ����ָ�����Ƶ�List<T>ջ,�Ƚ�������������
     * jedis.lrange("�б���", 0, -1)  �õ����е�listԪ�� List<T>�� -1�����б����һ��Ԫ��,-2�����ڶ���Ԫ�أ��Դ�����...
     * jedis.lrem("�б���", num, "ָ����Ԫ��ֵ")   ɾ��ָ���б�� ����num�������ظ�ʱ�� ָ��Ԫ�ء���add��ȥ��ֵ�ȱ�ɾ�������ڳ�ջ
     * jedis.ltrim("�б���", 0, 3)  ɾ��ָ���б�� ָ�������������
     * jedis.lpop("�б���")   �б�Ԫ�س�ջ�������ȳ�
     * jedis.lset("�б���", 0, "hello list!")  �޸�ָ���б�ָ��λ�õ���ֵ
     * jedis.llen("�б���") ��ȡ����ĳ���
     * jedis.sort("�б���")  ��ָ���б��������
     * 
     */
    private void ListOperate() 
    {
        System.out.println("======================list=========================="); 
        // ������� 
        System.out.println("��տ����������ݣ�"+jedis.flushDB()); 

        System.out.println("=============��=============");
        jedis.lpush("stringlists", "vector"); 
        jedis.lpush("stringlists", "ArrayList"); 
        jedis.lpush("stringlists", "vector");
        jedis.lpush("stringlists", "vector");
        jedis.lpush("stringlists", "LinkedList");
        jedis.lpush("stringlists", "MapList");
        jedis.lpush("stringlists", "SerialList");
        jedis.lpush("stringlists", "HashList");
        jedis.lpush("numberlists", "3");
        jedis.lpush("numberlists", "1");
        jedis.lpush("numberlists", "5");
        jedis.lpush("numberlists", "2");
        System.out.println("����Ԫ��-stringlists��"+jedis.lrange("stringlists", 0, -1));
        System.out.println("����Ԫ��-numberlists��"+jedis.lrange("numberlists", 0, -1));
        
        System.out.println("=============ɾ=============");
        // ɾ���б�ָ����ֵ ���ڶ�������Ϊɾ���ĸ��������ظ�ʱ������add��ȥ��ֵ�ȱ�ɾ�������ڳ�ջ
        System.out.println("�ɹ�ɾ��ָ��Ԫ�ظ���-stringlists��"+jedis.lrem("stringlists", 2, "vector")); 
        System.out.println("ɾ��ָ��Ԫ��֮��-stringlists��"+jedis.lrange("stringlists", 0, -1));
        // ɾ��������������� 
        System.out.println("ɾ���±�0-3����֮���Ԫ�أ�"+jedis.ltrim("stringlists", 0, 3));
        System.out.println("ɾ��ָ������֮��Ԫ�غ�-stringlists��"+jedis.lrange("stringlists", 0, -1));
        // �б�Ԫ�س�ջ 
        System.out.println("��ջԪ�أ�"+jedis.lpop("stringlists")); 
        System.out.println("Ԫ�س�ջ��-stringlists��"+jedis.lrange("stringlists", 0, -1));
        
        System.out.println("=============��=============");
        // �޸��б���ָ���±��ֵ 
        jedis.lset("stringlists", 0, "hello list!"); 
        System.out.println("�±�Ϊ0��ֵ�޸ĺ�-stringlists��"+jedis.lrange("stringlists", 0, -1));
        System.out.println("=============��=============");
        // ���鳤�� 
        System.out.println("����-stringlists��"+jedis.llen("stringlists"));
        System.out.println("����-numberlists��"+jedis.llen("numberlists"));
        // ���� 
        /*
         * list�д��ַ���ʱ����ָ������Ϊalpha�������ʹ��SortingParams������ֱ��ʹ��sort("list")��
         * �����"ERR One or more scores can't be converted into double"
         */
        SortingParams sortingParameters = new SortingParams();
        sortingParameters.alpha();
        sortingParameters.limit(0, 3);
        System.out.println("���������Ľ��-stringlists��"+jedis.sort("stringlists",sortingParameters)); 
        System.out.println("���������Ľ��-numberlists��"+jedis.sort("numberlists"));
        // �Ӵ���  startΪԪ���±꣬endҲΪԪ���±ꣻ-1������һ��Ԫ�أ�-2�������ڶ���Ԫ��
        System.out.println("�Ӵ�-�ڶ�����ʼ��������"+jedis.lrange("stringlists", 1, -1));
        // ��ȡ�б�ָ���±��ֵ 
        System.out.println("��ȡ�±�Ϊ2��Ԫ�أ�"+jedis.lindex("stringlists", 2)+"\n");
    }
    
    

    
    
    private void SortedSetOperate() 
    { 
        System.out.println("======================zset=========================="); 
        // ������� 
        System.out.println(jedis.flushDB()); 
        
        System.out.println("=============��=============");
        System.out.println("zset�����Ԫ��element001��"+jedis.zadd("zset", 7.0, "element001")); 
        System.out.println("zset�����Ԫ��element002��"+jedis.zadd("zset", 8.0, "element002")); 
        System.out.println("zset�����Ԫ��element003��"+jedis.zadd("zset", 2.0, "element003")); 
        System.out.println("zset�����Ԫ��element004��"+jedis.zadd("zset", 3.0, "element004"));
        System.out.println("zset�����е�����Ԫ�أ�"+jedis.zrange("zset", 0, -1));//����Ȩ��ֵ����
        System.out.println();
        
        System.out.println("=============ɾ=============");
        System.out.println("zset��ɾ��Ԫ��element002��"+jedis.zrem("zset", "element002"));
        System.out.println("zset�����е�����Ԫ�أ�"+jedis.zrange("zset", 0, -1));
        System.out.println();
        
        System.out.println("=============��=============");
        System.out.println();
        
        System.out.println("=============��=============");
        System.out.println("ͳ��zset�����е�Ԫ���и�����"+jedis.zcard("zset"));
        System.out.println("ͳ��zset������Ȩ��ĳ����Χ�ڣ�1.0����5.0����Ԫ�صĸ�����"+jedis.zcount("zset", 1.0, 5.0));
        System.out.println("�鿴zset������element004��Ȩ�أ�"+jedis.zscore("zset", "element004"));
        System.out.println("�鿴�±�1��2��Χ�ڵ�Ԫ��ֵ��"+jedis.zrange("zset", 1, 2));

    }
    
    /**
     * �����ֵ�����صĶ���List��ʽ
     * 
     * jedis.hset("ָ����mapֵ", "map�е�key", "map�е�value"));  ��ָ����map����� ��ֵ�� String - String
     * jedis.hincrBy("hashs", "key004", 4l));   ��ָ����map����� ��ֵ�� String - int�����߶�ָ��ֵ������
     * jedis.hvals("hashs"))  ��ӡ�� ָ�� map �еģ�����ֵ
     * jedis.hkeys("hashs"))  ��ӡ�� ָ�� map �еģ�����key
     * jedis.hdel("hashs", "key002")  ɾ�� ָ���ļ�ֵ��
     * jedis.hexists("hashs", "key003") �ж� ָ���ļ�ֵ�� �Ƿ����
     * jedis.hget("hashs", "key004") ��ȡָ����keyָ����ֵ
     * jedis.hmget("hashs", "key001", "key003"))  ������ȡ ָ��map �У�ָ���Ķ��key��ֵ
     * 
     */
    private void HashOperate() 
    { 
        System.out.println("======================hash==========================");
        //������� 
        System.out.println(jedis.flushDB()); 
        
        System.out.println("=============��=============");
        System.out.println("hashs�����key001��value001��ֵ�ԣ�"+jedis.hset("hashs", "key001", "value001")); 
        System.out.println("hashs�����key002��value002��ֵ�ԣ�"+jedis.hset("hashs", "key002", "value002")); 
        System.out.println("hashs�����key003��value003��ֵ�ԣ�"+jedis.hset("hashs", "key003", "value003"));
        System.out.println("����key004��4�����ͼ�ֵ�ԣ�"+jedis.hincrBy("hashs", "key004", 4l));
        System.out.println("hashs�е�����ֵ��"+jedis.hvals("hashs"));
        System.out.println("hashs�����е�key��"+jedis.hkeys("hashs"));
        System.out.println();
        
        System.out.println("=============ɾ=============");
        System.out.println("hashs��ɾ��key002��ֵ�ԣ�"+jedis.hdel("hashs", "key002"));
        System.out.println("hashs�е�����ֵ��"+jedis.hvals("hashs"));
        System.out.println();
        
        System.out.println("=============��=============");
        System.out.println("key004���ͼ�ֵ��ֵ����100��"+jedis.hincrBy("hashs", "key004", 100l));
        System.out.println("hashs�е�����ֵ��"+jedis.hvals("hashs"));
        System.out.println();
        
        System.out.println("=============��=============");
        System.out.println("�ж�key003�Ƿ���ڣ�"+jedis.hexists("hashs", "key003"));
        System.out.println("��ȡkey004��Ӧ��ֵ��"+jedis.hget("hashs", "key004"));
        List<String> list = jedis.hmget("hashs", "key004");
        
        
        System.out.println("������ȡkey001��key003��Ӧ��ֵ��"+jedis.hmget("hashs", "key001", "key003")); 
        System.out.println("��ȡhashs�����е�key��"+jedis.hkeys("hashs"));
        System.out.println("��ȡhashs�����е�value��"+jedis.hvals("hashs"));
        System.out.println();
              
    }    
    
    /**
     * ��ȡ jedis �����е�key
     * @param jedis
     */
    public static void getAllKeys(Jedis jedis){
    	 Set<String> keys = jedis.keys("*"); 
         Iterator<String> it=keys.iterator() ;   
         while(it.hasNext()){   
             String key = it.next();   
             System.out.println(key);   
         }
    }
     
}