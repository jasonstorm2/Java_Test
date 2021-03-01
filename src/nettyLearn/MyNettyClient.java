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
public class MyNettyClient {

    public static void main(String[] args) throws Exception {

        String host = "localhost";
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {        	
            Bootstrap b = new Bootstrap(); // (1)            
            b.group(workerGroup); // (2)            
            b.channel(NioSocketChannel.class); // (3)            
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)        

            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new Plus2(),new PlusDecoder() ,new MyNettyClientHandler());
                }
            });
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // �ȴ����ӹر�
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}