package nettyLearn;

import java.util.Timer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * ��Netty��,��д����˺Ϳͻ������Ĳ���Ψһ��ͬ��ʹ���˲�ͬ��BootStrap��Channel��ʵ��
 * @author LiZhenhua
 *
 */
public class TimeClient {

    public static void main(String[] args) throws Exception {

        String host = "localhost";
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
        	
//        	BootStrap��ServerBootstrap����,�������ǶԷǷ���˵�channel���ԣ�����ͻ��˻��������Ӵ���ģʽ��channel��
            Bootstrap b = new Bootstrap(); // (1)
            
//          �����ָֻ����һ��EventLoopGroup�������ͻἴ��Ϊһ����boss���̣߳�
//          Ҳ����Ϊһ����workder���̣߳����ܿͻ��˲���Ҫʹ�õ���boss���̡߳�
            b.group(workerGroup); // (2)
            
//          ����NioServerSocketChannel����NioSocketChannel,������ڿͻ���channel������ʱʹ�á�
            b.channel(NioSocketChannel.class); // (3)
            
//            ������ʹ��ServerBootstrapʱ��Ҫ��childOption()��������Ϊ�ͻ��˵�SocketChannelû�и�channel�ĸ��
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            
//            b.handler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                public void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new TimeClientHandler());
//                }
//            });
//            /************�����ݵĴ��䴦��2--��ʼ*************/
//            //  ��ӵ�ͨ����������˳�����Ҫ����������˳��
//            b.handler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                public void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new TimeDecoder() ,new TimeClientHandler());
//                }
//            });
//            /************�����ݵĴ��䴦��2--����*************/
            
//            /************�����ݵĴ��䴦��3--��ʼ*************/
//            //  ��ӵ�ͨ����������˳�����Ҫ����������˳��
//            b.handler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                public void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new TimeDecoder2() ,new TimeClientHandler());
//                }
//            });
//            /************�����ݵĴ��䴦��3--����*************/
            
            /************��POJO����ByteBuf--��ʼ*************/
            //  ��ӵ�ͨ����������˳�����Ҫ����������˳��
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new POJODecoder() ,new TimeClientHandler());
                }
            });
            /************��POJO����ByteBuf--����*************/

//            b.remoteAddress(remoteAddress);//���ӵķ����кܶ���
            // ������connect()����������bind()������ �����ͻ���
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // �ȴ����ӹر�
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
