package nettyLearn;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty ��ֻ���Դ��� nio���͵����ݣ�Ҳ���Դ���oio�����͵�����
 * nio��Ŀǰʹ�����Ĵ������ͣ�transport 
 * @author LiZhenhua
 *
 */
public class DiscardServer {
    private int port;

    public DiscardServer(int port) {
        super();
        this.port = port;
    }

    public void run() throws Exception {

        /***
         * NioEventLoopGroup ����������I/O�����Ķ��߳��¼�ѭ������
         * Netty�ṩ����಻ͬ��EventLoopGroup��ʵ����������ͬ����Э�顣 ���������������ʵ����һ������˵�Ӧ�ã�
         * ��˻���2��NioEventLoopGroup�ᱻʹ�á� ��һ��������������boss�����������ս��������ӡ�
         * �ڶ���������������worker�������������ѽ������ӵ�ͨ������ һ����boss�����յ����ӣ��ͻ��������Ϣע�ᵽ��worker���ϡ�
         * ʹ���˶����߳� �Լ���ν�����ӳ�䵽������ͨ�� ȡ����EventLoopGroupʵ�֣���������ͨ�����캯���������á�
         * 
         * ��Ϊ����ʹ��NIO transport������ָ��NioEventLoopGroup�����������Ӻʹ����Ѿ����ܵ�����
         *  You create and assign the NioEventLoopGroup instances to handle event processing, 
         *  such as accepting new connections, receiving data, writing data, and so on. 
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        System.out.println("׼�����ж˿ڣ�" + port);
        try {
            /**
             * ServerBootstrap ��һ������server�ĸ��������� �����ʹ��һ��Channel������һ��server�����������һ�����ӵĴ�����̣��ںܶ�������㲢����Ҫ��������
             *You create a ServerBootstrap instance to bootstrap the server and bind it later
             */
            ServerBootstrap b = new ServerBootstrap();
            /**
             * ��һ���Ǳ���ģ����û������group���ᱨjava.lang.IllegalStateException: group not
             * set�쳣
             */
            b = b.group(bossGroup, workerGroup);
            /***
             * ServerSocketChannel��NIO��selectorΪ��������ʵ�ֵģ����������µ�����
             * ���������ָ��ʹ��NioServerSocketChannel�࣬������ʵ����һ���µ�channel�����ܴ�������ӣ�connections��
             * ָ��NioServerSocketChannel Ϊchannel�����ͣ���Ȼ��Ҳ����ʹ���������ͣ�����OioServerSocketChannel
             */
            b = b.channel(NioServerSocketChannel.class);
            /***
             * You set up a childHandler that executes for every accepted connection. 
             * ����ָ���Ĵ������(Handler)��ʼ�����½��ܵ�ͨ��(Channel)���м��㡣
             * ������¼������ྭ���ᱻ��������һ��������Ѿ����յ�Channel�� 
             * ChannelInitializer��һ������Ĵ�����(Handler)������Ŀ���ǰ���ʹ��������һ���µ�(Channel)��
             * Ҳ������ͨ������һЩ������(Handler)����DiscardServerHandler ������һ���µ�Channel��(ChannelPipeline) ��ʵ������������ 
             * ����ĳ����ĸ���ʱ������������Ӹ���Ĵ����ൽpipline�ϣ�
             * Ȼ����ȡ��Щ�����ൽ�������ϡ�
             */
//            b = b.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
//                @Override
//                public void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new DiscardServerHandler());// demo1.discard
//                    // ch.pipeline().addLast(new
//                    // ResponseServerHandler());//demo2.echo
//                    // ch.pipeline().addLast(new
//                    // TimeServerHandler());//demo3.time
//                }
//            });
            
            /************��POJO����ByteBuf--��ʼ*************/ 
            //��ChannelPipeline����ӷ�����Ϣ�ı��봦��ע�⣬��DiscardServerHandler֮ǰ
            b = b.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new POJOEncoder(),new DiscardServerHandler());// demo1.discard
                    // ch.pipeline().addLast(new
                    // ResponseServerHandler());//demo2.echo
                    // ch.pipeline().addLast(new
                    // TimeServerHandler());//demo3.time
                }
            });
            /************��POJO����ByteBuf--����*************/
            
            /***
             * ��������ò�������Щ�����ض���channel��ʵ�� 
             * ��������дһ��TCP/IP�ķ���ˣ�
             * ������Ǳ���������socket�Ĳ���ѡ�����tcpNoDelay��keepAlive��
             * ��ο�ChannelOption����ϸ��ChannelConfigʵ�ֵĽӿ��ĵ��Դ˿��Զ�ChannelOptions����һ����ŵ���ʶ��
             */
            b = b.option(ChannelOption.SO_BACKLOG, 128);// (5)
            /***
             * option()���ṩ��NioServerSocketChannel�������ս��������ӡ�
             * childOption()���ṩ���ɸ��ܵ�ServerChannel���յ������ӣ�����������и��ܵ���NioServerSocketChannel��
             */
            b = b.childOption(ChannelOption.SO_KEEPALIVE, true);// (6)
            /***
             * �󶨶˿ڲ�����ȥ���ս���������
             * Here, we bind to the port 8080 of all NICs (network interface cards) in the machine. 
             * You can now call the bind() method as many times as you want (with different bind addresses.)
             * 
             * sync() �������������������ֱ�����ӱ�����
             */
            ChannelFuture f = b.bind(port).sync(); //(7)
            System.out.println(DiscardServer.class.getName() + "��ʼ�����Ҽ�����"+f.channel().localAddress());
            /**
             * �����һֱ�ȴ���ֱ��socket���ر�
             * 
             */
            f.channel().closeFuture().sync();
        } finally {
            /***
             * �ر�
             */
        	System.out.println("finally���ִ��");
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            
//            workerGroup.shutdownGracefully().sync();
//            bossGroup.shutdownGracefully().sync();
        }
    }
    
    //������������
    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new DiscardServer(port).run();
        System.out.println("server:run()");
    }
}
