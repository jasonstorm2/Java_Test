package nettyLearn;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;

/**
 * ChannelHandlerAdapter��ChannelHandler��ʵ���� Handles a server-side channel
 * ChannelHandler �ṩ�������override�Ķ���¼�������
 * ��Ŀǰ����ֻ��Ҫ�̳�ChannelInboundHandlerAdapter�������������Լ�ȥʵ�����ĸ���ӿ�
 * @author Administrator
 *
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
	/**
     * �������Ǹ�����chanelRead()�¼��������� ÿ���ӿͻ����յ��µ�����ʱ�� ������������յ���Ϣʱ�����ã�
     * ��������У��յ�����Ϣ��������ByteBuf
     * 
     * @param ctx
     *            ͨ���������������Ϣ
     * @param msg
     *            ���յ���Ϣ
     */
	/**************��ӡ����***************/
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg){
	    try {
	    	ctx.write(msg);
	    	
			 //�յ�����Ϣ�������� ByteBuf
			 ByteBuf in = (ByteBuf) msg;
			 //�����Ч��ѭ��ʵ���Ͽ��Լ�Ϊ
			 System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));			 
//			 while(in.isReadable()){
//			 System.out.println((char)in.readByte());
//			 System.out.flush();
//			 }            
        } finally {
            /**
             * ByteBuf��һ�����ü�������������������ʾ�ص���release()�������ͷš�
             * ���ס��������ְ�����ͷ����д��ݵ������������ü�������
             */
            // �����յ�������
            ReferenceCountUtil.release(msg);
            
        }
	}
	
//	  /**************Ӧ��ͻ�***************/
//	@Override
//	public void channelRead(ChannelHandlerContext ctx, Object msg) {
//		System.out.println("channelRead����������");
//		ByteBuf in = (ByteBuf) msg;
//		 //�����Ч��ѭ��ʵ���Ͽ��Լ�Ϊ
//		 System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));			 
//
//		try {
//			// ChannelHandlerContext �����ṩ����������ʹ���ܹ��������ָ����� I/O �¼��Ͳ�����
//			// �������ǵ����� write(Object) ���������ֵذѽ��ܵ�����Ϣд�롣
//			// ��ע�ⲻͬ�� DISCARD ���������ǲ�û���ͷŽ��ܵ�����Ϣ��
//			// ������Ϊ��д���ʱ�� Netty �Ѿ��������ͷ��ˡ�
//			ctx.write(msg); // (1)
//			// ctx.write(Object) ��������ʹ��Ϣд�뵽ͨ���ϣ��������������ڲ�������Ҫ���� ctx.flush()
//			// �������ѻ�����������ǿ�������
//			// ����������ø����� cxt.writeAndFlush(msg) �Դﵽͬ����Ŀ�ġ�
//			ctx.flush(); // (2)
//
//		} finally {
//			// û����������
//
//		}
//
//	}
	
	/**
	 * ��д���������Ϣ��ˢ��Զ������
	 * �����ڲ�����ɺ� �ر�����
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		// ͨ��ChannelHandlerContext �ķ������Ѵ��������Ϣ�����ݸ���һ��handler
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}
	
	
	 /**************ʱ�������***************/
//	@Override
//	
//	//1.channelActive() �������������ӱ���������׼������ͨ��ʱ�����á�
//	// ������ʱ��ֱ����ͻ��˷�����Ϣ����ʱ��û����ͨ��
//	//�����������������������һ������ǰʱ���32λ������Ϣ�Ĺ�������
//	
//	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//	System.out.println("channelActive����������");
//		
////		2.Ϊ�˷���һ���µ���Ϣ��������Ҫ����һ�����������Ϣ���µĻ��塣
////		��Ϊ������Ҫд��һ��32λ�����������������Ҫһ��������4���ֽڵ� ByteBuf��
////		ͨ�� ChannelHandlerContext.alloc() �õ�һ����ǰ��ByteBufAllocator��Ȼ�����һ���µĻ��塣
//		final ByteBuf time = ctx.alloc().buffer(4);
//		
//		time.writeInt((int)(System.currentTimeMillis()/1000l+2208988800l));
//		
////		3.������һ��������Ҫ��дһ�������õ���Ϣ�����ǵ�һ�ȣ�flip ���ģ��ѵ�����ʹ�� NIO ������Ϣʱ���ǵ��� java.nio.ByteBuffer.flip() ��
////		ByteBuf ֮����û�����������Ϊ������ָ�룬һ����Ӧ������һ����Ӧд������
////		������ ByteBuf ��д�����ݵ�ʱ��дָ��������ͻ����ӣ�ͬʱ��ָ�������û�б仯����ָ��������дָ�������ֱ��������Ϣ�Ŀ�ʼ�ͽ�����
//		
////		�Ƚ�������NIO ���岢û���ṩһ�ּ��ķ�ʽ���������Ϣ���ݵĿ�ʼ�ͽ�β����������� flip ������
////		�������ǵ��� flip ����������û�����ݻ��ߴ������ݱ�����ʱ���������������
////		������һ�����󲻻ᷢ���� Netty �ϣ���Ϊ���Ƕ��ڲ�ͬ�Ĳ��������в�ͬ��ָ�롣
////		��ᷢ��������ʹ�÷�����������̱�ø��ӵ����ף���Ϊ���Ѿ�ϰ��һ��û��ʹ�� flip �ķ�ʽ��
//		
////		����һ������Ҫע����� ChannelHandlerContext.write() (�� writeAndFlush() )�����᷵��һ�� ChannelFuture ����
////	    һ�� ChannelFuture ������һ����û�з����� I/O ������
////		����ζ���κ�һ������������������ϱ�ִ�У���Ϊ�� Netty �����еĲ��������첽�ġ�
////		�ٸ���������Ĵ���������Ϣ������֮ǰ���ܻ��ȹر����ӡ�
////		 	Channel ch = ...;
////		    ch.writeAndFlush(message);
////		    ch.close();
//		
////		�������Ҫ�� write() �������ص� ChannelFuture ��ɺ���� close() ������Ȼ������д�����Ѿ��������֪ͨ���ļ����ߡ�
////		��ע��,close() ����Ҳ���ܲ�������رգ���Ҳ�᷵��һ��ChannelFuture��
//		
//		final ChannelFuture f = ctx.writeAndFlush(time);
//		
////		��һ��д�����Ѿ���������֪ͨ�����ǣ����ֻ��Ҫ�򵥵��ڷ��ص� ChannelFuture ������һ��ChannelFutureListener��
////		�������ǹ�����һ�������� ChannelFutureListener �������ڲ������ʱ�ر� Channel��
////
////		���ߣ������ʹ�ü򵥵�Ԥ�������������:ֱ�ӹر�channel
////		f.addListener(ChannelFutureListener.CLOSE);
//		
//		f.addListener(new ChannelFutureListener() {
//			
//			@Override
//			public void operationComplete(ChannelFuture future) throws Exception {
//				// TODO Auto-generated method stub
//				assert f == future;
//				System.out.println("д������ɡ���������");
//				ctx.close();
//			}
//		});
//	}
	
	/************��POJO����ByteBuf--��ʼ*************/
	@Override
	
	//1.channelActive() �������������ӱ���������׼������ͨ��ʱ�����á������������������������һ������ǰʱ���32λ������Ϣ�Ĺ�������
	
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive����������");
		 ChannelFuture f = ctx.writeAndFlush(new UnixTime());		
		 f.addListener(ChannelFutureListener.CLOSE); //������Ϻ󣬹ر�channel����
	}
	/************��POJO����ByteBuf--����*************/

	/***
	 * ����������ڷ����쳣ʱ����
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		System.out.println("exceptionCaught����������");
        /**
         * exceptionCaught() �¼��������ǵ����� Throwable ����Żᱻ���ã����� Netty ���� IO
         * ������ߴ������ڴ����¼�ʱ�׳����쳣ʱ���ڴ󲿷�����£�������쳣Ӧ�ñ���¼���� ���Ұѹ����� channel
         * ���رյ���Ȼ����������Ĵ���ʽ����������ͬ�쳣��������в� ͬ��ʵ�֣�������������ڹر�����֮ǰ����һ�����������Ӧ��Ϣ��
         */
        // �����쳣�͹ر�
        cause.printStackTrace();
        ctx.close();
	}
}

//���⼸�д����ﻹ�м�����Ҫ�����顣
//��һ�� ͨ��ChannelPromise�������������ݱ�д����ͨ����Netty����ͨ������������ǳɹ�����ʧ�ܡ�
//�ڶ��� ���ǲ���Ҫ����cxt.flush()��
//��Ϊ�������Ѿ������������һ������void flush(ChannelHandlerContext cxt),������Լ�ʵ��flush�������ݿ������и������������
//ʣ�µ����һ����������TimeServerHandler֮ǰ��һ��ʱ�����������������˵�ChannelPipeline������һ���򵥵���ϰ��
/**
* ����˷����ͻ��˵���Ϣ����
* @author Administrator
*
*/
class POJOEncoder extends ChannelOutboundHandlerAdapter  {
  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
  	System.out.println("POJOEncoder write����������");
      UnixTime m = (UnixTime) msg;
      ByteBuf encoded = ctx.alloc().buffer(4);
      encoded.writeInt((int) m.value());
      ctx.write(encoded, promise); // (1)
  }
}

//��һ���򻯲����������ʹ��MessageToByteEncode:
class POJOEncoder2 extends MessageToByteEncoder<UnixTime> {
  @Override
  protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) {
  	System.out.println("TimeEncoder2 encode����������");
      out.writeInt((int) msg.value());
  }
}
