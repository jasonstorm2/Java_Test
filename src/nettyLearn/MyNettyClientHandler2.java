package nettyLearn;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class MyNettyClientHandler2 extends ChannelInboundHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.out.println("client�� channelRead����������");
		int m = (int)msg;
		System.out.println("�յ�����˷�����ֵ��"+m);
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	System.out.println("client ��channelActive����������");
    	// TODO Auto-generated method stub
    	ctx.write("100");
    	ctx.flush();
    }
}


