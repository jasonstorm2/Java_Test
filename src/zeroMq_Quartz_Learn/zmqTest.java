package zeroMq_Quartz_Learn;


/**
 * 2016��Ĳ��ͣ�
 * ���ȣ�����������һ��ʲô��ZMQ��ȫ�ƣ�ZeroMQ����
 * 
 * �ٷ��� ��ZMQ(����ZeroMQ���ZMQ)��һ���򵥺��õĴ���㣬����һ����һ��socket
 * library����ʹ��Socket��̸��Ӽ򵥡��������ܸ���
 * ����һ����Ϣ�������п⣬���ڶ���̡߳��ں˺�������֮�䵯��������ZMQ����ȷĿ���ǡ���Ϊ��׼����Э��ջ��һ����
 * ��֮�����Linux�ںˡ������ڻ�δ�������ǵĳɹ�
 * �����ǣ��������Ǽ���ǰ���ġ����������Ǹ�����Ҫ�ġ���ͳ��BSD�׽���֮�ϵ�һ���װ��ZMQ�ñ�д����������Ӧ�ó���Ϊ�򵥺���Ȥ����
 * 
 * ��������Ϣ�м����ȣ�ZMQ��������һ����ͳ�����ϵ���Ϣ���з���������ʵ�ϣ���Ҳ��������һ������������������һ���ײ������ͨѶ�⣬��Socket
 * API֮������һ���װ��������ͨѶ������ͨѶ���߳�ͨѶ����Ϊͳһ��API�ӿڡ�
 * 
 * �Ķ���ZMQ��Guide�ĵ����Ҿ���ZMQ�Ǹ�������Socket��һϵ�нӿڣ�����Socket�������ǣ���ͨ��socket�Ƕ˵��˵ģ�1:1�Ĺ�ϵ����
 * ��ZMQȴ�ǿ���N��M
 * �Ĺ�ϵ�����Ƕ�BSD�׽��ֵ��˽�϶���ǵ�Ե�����ӣ���Ե�������Ҫ��ʽ�ؽ������ӡ��������ӡ�ѡ��Э�飨TCP/UDP���ʹ��������
 * ����ZMQ��������Щϸ��
 * ������������̸�Ϊ�򵥡�ZMQ����node��node���ͨ�ţ�node���������������ǽ��̡����������ǾͲ�ϸ˵���г��õļ���ģʽ��
 * �Ժ��������������д�ġ�
 * 
 * ���ڣ�������˵һ�������windowsƽ̨��ʹ��ZeroMQ��java������Ϊ��ʱֻ��windowsƽ̨�ϴ��ZeroMQ�Ļ�����
 * �Ժ�ʹ��linux��ʱ���������䡿
 * 
 * ������֪��������git�����ص�ZMQ����Ҫʹ��c�Ļ��������������jar����.dll�ļ������ǵ�java������ʹ�õģ�
 * �����������ص���û��build�ļ��е�
 * �����������صĹ����ļ�ȴ��û��jar���ģ��Ҳ�֪�������������Ŀ��ʹ�ã���������·���Ҳ��ۣ���������64bit�����ϱ����ʱ��
 * ������ɹ����ر�ͣ������²�һ�£��Ҿ�û����ɹ���������������·�ܿ��Ҿͷ����ˣ���Ȼ��־֮ʿ���Գ���һ�£������Ľ̳�Ҳ�ࣩܶ��
 * 
 * ������ߴ��������γɹ�ʹ�õģ�
 * 
 * ���ϻ������ѷ������Ѿ�����õ��ļ���һ����������jzmq.dll��libzmq.dll��zmq.jar�ļ�����ʱ���Ҿͻ��������ˣ�
 * ��֪��jar����������Ŀ�е�
 * ������������dll�ļ���ô���أ��������Ҳ�кܶ�˵�������Ǿ����Լ�ʵ����ֻҪ�����������jdk�����м��ɣ�����jdk����Ŀ¼/bin
 * /.dll��ok�ˡ�������ʹ��ZMQ��д����Ŀ����ʱ�Ͳ��ᱨ���Ҳ���ZMQ���ʹ����ǾͿ�������Ŀ��ʹ��ZMQ�ˡ�
 * 
 * Ϊ�˸�л�����޳��������ұ���֮����ļ�����Щ�а������ǣ�������Ҳ��ų�ZMQ��32bit��64bit�����ļ�������������أ�ͬ��Ҳ���Է�������������Ҫ����
 * ���ڴ�лл��^0^���������ʣ���ӭ����ѧϰ��
 * 
 * �������ļ����������ӣ�������ӱ�ȡ���ˣ��뼰ʱ������ϵ������
 * 
 * ZMQ��java��64bit��http://pan.baidu.com/s/1boCjeiz
 * 
 * ZMQ��java��32bit��http://pan.baidu.com/s/1kUOecHD
 * 
 * 
 * 2012��Ĳ��ͣ�
 * ZMQ֮�����ܹ�����״̬��������ʵ��1��N�����ӣ��ؼ������ŷ�Ļ��ƣ��ŷ��ﱣ����Ӧ��Ŀ���λ�á�ZMQ�漰������-��Ӧģʽ��Socketһ����4�����ͣ�
 * 
 * DEALER��һ�ָ��ؾ��⣬���Ὣ��Ϣ�ַ��������ӵĽڵ㣬��ʹ�ù�ƽ���еĻ��ƴ������ܵ�����Ϣ��
 * 
 * REQ������Ϣʱ������Ϣ��������һ����֡������ʱ�Ὣ��֡��ȥ����ʵREQ�ǽ�����DEALER֮�ϵģ���REQֻ�е���Ϣ���Ͳ����ܵ���Ӧ����ܼ������С�
 * 
 * ROUTER���յ���Ϣʱ���ڶ�������һ���ŷ⣬�����Ϣ��Դ������ʱ��ͨ�����ŷ�����ĸ��ڵ���Ի�ȡ��������Ϣ��
 * 
 * REP���յ���Ϣʱ�Ὣ��һ����֮֡ǰ��������Ϣ��������
 * ����ԭʼ��Ϣ���͸�Ӧ�ó����ڷ�����Ϣʱ��REP���øղű������Ϣ����Ӧ����Ϣ��REP��ʵ�ǽ�����ROUTER֮�ϵ�
 * ������REQһ����������ɽ��ܺͷ�����������������ܼ�����
 * 
 *
 * ZMQʹ��ע����� ZMQ���ڷ��Ͷ˻�����Ϣ�ģ�����ͨ����ֵ������Ϣ�������
 * ZMQ�������߳�֮�乲��Socket������ᱨorg.zeromq.ZMQException: Operation cannot be
 * accomplished in current state���� ZMQһ������ֻ������һ��Context��new Context(int arg)
 * arg��ʾ��̨�̵߳�������
 * ZMQ��Socket����һ��Linger����������ͨ��SetLinger���ã���Ҫ���ڱ�ʾ��Socket�ر��Ժ�δ���ͳɹ�����Ϣ�Ƿ񻹱���
 * ���������Ϊ-1
 * ��ʾ����Ϣ�����ñ��棨����崻���ZMQ�ǲ��־û���Ϣ�ģ������Ϊ0��ʾ����δ���ͳɹ�����Ϣ��Socker�ر��Ժ󶼽���������������һ������
 * �����ʾ����Ϣ��Socket�رպ���ٺ����ڱ�ɾ������������ǳ����ã������ڿ��Ʒ���ʧ��ʱ���Ƿ��ط���Ϣ��
 * 
 * @author LiZhenhua
 *
 */
public class zmqTest {

}