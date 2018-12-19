package nettyLearn;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.Date;
import java.util.List;

//�����������ǳ��򵥣����Һͷ���˵��Ǹ����ӵĴ���Ҳ���ࡣ
//Ȼ������������ʱ�����Ϊ�׳�IndexOutOfBoundsException���ܾ�������
//���¸��������ǻ�����Ϊʲô�ᷢ�����������

//ChannelHandlerAdapter��ChannelHandler��ʵ����
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
	/************�򵥵Ŀͻ���-��ʼ*************/
//    @Override
//	//�������Ǹ�����chanelRead()�¼��������� ÿ���ӿͻ����յ��µ�����ʱ�� ������������յ���Ϣʱ�����ã�
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
////    	��TCP/IP�У�NETTY��Ѷ��������ݷŵ�ByteBuf�����ݽṹ�С�
//        ByteBuf m = (ByteBuf) msg; // (1)
//        try {
//            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
//            System.out.println(new Date(currentTimeMillis));
//            ctx.close();
//        } finally {
//            m.release();
//        }
//    }
    /************�򵥵Ŀͻ���-����*************/
    
    
    
//	/************�����ݵĴ��䴦��1--��ʼ*************/
//	//һ�����շ��������ǿͻ��˻��Ƿ���ˣ���Ӧ�ðѽ��յ������������һ�����߶��������˼�����ܹ��ó����ҵ���߼�������������
//	//һ��32�ֽ������Ƿǳ�С��������,���ܻᱻ��ֵ���ͬ�����ݶ��ڣ����Ҳ�ֵĿ����Ի�����ͨ���������Ӷ�����
//	//��򵥵ķ����ǹ���һ���ڲ��Ŀɻ��۵Ļ��壬ֱ��4���ֽ�ȫ�����յ����ڲ����塣
//	//
//	private ByteBuf buf;
//
//	/**
//	 * �ڽ�ChannelHandler��ӵ�ʵ�������Ĳ�׼�������¼�֮����á�
//	 * Gets called after the ChannelHandler was added to the actual context and it's ready to handle events.
//	 * 
//	 * �ٿ�ʼ��������֮ǰ��ʵ����һ��4�ֽڵ�ByteBuf
//	 */
//	@Override
//	public void handlerAdded(ChannelHandlerContext ctx) {
//
////		ChannelHandler��2���������ڵļ���������handlerAdded()��handlerRemoved()��
////		�������������ʼ������ֻҪ�����ᱻ�����ܳ���ʱ��
//		buf = ctx.alloc().buffer(4); // (1)
//		System.out.println("TimeClientHandler handlerAdd����������");
//
//	}
//	
//	@Override
//	/**
//	 * ����Ϣ������ʱ������Ϣ����4�ֽڵ�ByteBuf�У�ֱ�����ByteBuf�ɶ��ĳ��ȴ���ָ��ֵ
//	 */
//	public void channelRead(ChannelHandlerContext ctx, Object msg) {
//	System.out.println("TimeClientHandler channelRead����������");
//		ByteBuf m = (ByteBuf) msg;
//		// ���н��յ����ݶ�Ӧ�ñ��ۻ���buf������
//		buf.writeBytes(m); // (2)
//		m.release();
//
//		// ������������buf�����Ƿ����㹻�����ݣ��������������4���ֽڣ�Ȼ����ʵ�ʵ�ҵ���߼���
//		// ����Netty���ظ�����channelRead()���и������ݵ���,ֱ��4���ֽڵ����ݱ�����
//		if (buf.readableBytes() >= 4) { // (3)
//
//			long currentTimeMillis = (buf.readInt() - 2208988800L) * 1000L;
//
//			System.out.println(new Date(currentTimeMillis));
//
//			ctx.close();
//		}
//	}
//
//	/**
//	 * ��ͨ��������򱻴�ʵ����������ɾ������ã������ٴ����¼���
//	 * Gets called after the ChannelHandler was removed from the actual context and it doesn't handle events anymore.
//	 * ���ݴ�����Ϻ��ͷ�֮ǰʵ������ByteBuf
//	 */
//	@Override
//	public void handlerRemoved(ChannelHandlerContext ctx) {
//
//		buf.release(); // (1)
//		buf = null;
//		System.out.println("TimeClientHandler handlerRemoved����������");
//	}
	/************�����ݵĴ��䴦��1--����*************/
	
	
	/************��POJO����ByteBuf--��ʼ*************/
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.out.println("TimeClientHandler channelRead����������");
		UnixTime m = (UnixTime)msg;
		System.out.println(m);
		ctx.close(); //�ر�channel����
	}

	/************��POJO����ByteBuf--����*************/
	
	
//	/************�����ݵĴ��䴦��2,3--��ʼ*************/
//    @Override
//	//�������Ǹ�����chanelRead()�¼��������� ÿ���ӿͻ����յ��µ�����ʱ�� ������������յ���Ϣʱ�����ã�
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
////    	��TCP/IP�У�NETTY��Ѷ��������ݷŵ�ByteBuf�����ݽṹ�С�
//        ByteBuf m = (ByteBuf) msg; // (1)
//        try {
//            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
//            System.out.println(new Date(currentTimeMillis));
//            ctx.close();
//        } finally {
//            m.release();
//        }
//    }
//    /************�����ݵĴ��䴦��2,3--����*************/


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	System.out.println("TimeClientHandler exceptionCaught����������");
        cause.printStackTrace();
        ctx.close();
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	// TODO Auto-generated method stub
    	ctx.write("lala");
    	ctx.flush();
    }
}

/************�����ݵĴ��䴦��2--��ʼ*************/
//���ܵ�һ����������Ѿ������Time�ͻ��˵������ˣ������޸ĺ�Ĵ���������������ô�ļ�࣬
//����һ������ɶ���ֶα���ɱ䳤�ȵ��ֶ���ɵĸ�Ϊ���ӵ�Э��ʱ�����ChannelHandler��ʵ�ֽ��ܿ�ر������ά����
//
//��������֪�ģ���������Ӷ��ChannelHandler��ChannelPipeline ,
//�������԰�һ����ChannelHandler��ֳɶ��ģ���Լ���Ӧ�õĸ��ӳ̶ȣ���������԰�TimeClientHandler��ֳ�2����������
//
//1.TimeDecoder�������ݲ�ֵ�����
//2.TimeClientHandlerԭʼ�汾��ʵ��

//	ByteToMessageDecoder��ChannelHandler��һ��ʵ���࣬�������ڴ������ݲ�ֵ������ϱ�úܼ򵥡�
class TimeDecoder extends ByteToMessageDecoder{
	@Override
//	ÿ���������ݽ��յ�ʱ��ByteToMessageDecoder�������decode()�����������ڲ����Ǹ��ۻ����塣
	protected void decode(ChannelHandlerContext arg0, ByteBuf in,
			List<Object> out) throws Exception {
		System.out.println("TimeDecoder decode����������");
//		Decode()�������Ծ������ۻ�������û���㹻����ʱ������out����������ݡ�
//		���и�������ݱ�������ByteToMessageDecoder����һ�ε���decode()������ֱ�������㹻��	
		if(in.readableBytes() < 4){
			System.out.println("byte�ĳ��ȣ�"+in.readableBytes());
			return;
		}
//		�����decode()������������һ������out���������ζ�Ž�����������Ϣ�ɹ���
//		ByteToMessageDecoder���ᶪ�����ۻ��������Ѿ������������ݡ�
//		��ǵ��㲻��Ҫ�Զ�����Ϣ����decode()��ByteToMessageDecoder����������decode()������ֱ��û����out����κ����ݡ�
		out.add(in.readBytes(4));		
	}
}
/************�����ݵĴ��䴦��2--����*************/

/************�����ݵĴ��䴦��3--��ʼ*************/
//�ȵڶ��ַ��������
//���⣬Netty�ṩ�˿��伴�õĽ�������ʹ���ܹ��ǳ����׵�ʵ�ִ����Э�飬�������������Բ���ά���ĵ�һ������ʵ�ָ��ա������������������:
//io.netty.example.factorial for a binary protocol, and
//io.netty.example.telnet for a text line-based protocol.

class TimeDecoder2 extends ReplayingDecoder<Void> {
    @Override
    protected void decode(
            ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
    	System.out.println("TimeDecoder2 decode����������");
        out.add(in.readBytes(4));
    }
}
/************�����ݵĴ��䴦��3--����*************/

/************��POJO����ByteBuf--��ʼ*************/
/**
 * �������������㹻�����ݣ����ض����������ͽ���
 * @author LiZhenhua
 *
 */
class POJODecoder extends ByteToMessageDecoder{
	@Override
	protected void decode(ChannelHandlerContext arg0, ByteBuf in,
			List<Object> out) throws Exception {
		System.out.println("TimeDecoder3 decode����������");
		System.out.println("byteBuf�ĳ��ȣ�"+in.readableBytes());
		if (in.readableBytes() < 4) {
			return;
		}
		out.add(new UnixTime(in.readUnsignedInt()));
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception { //�������δ�Ͽ����������ص����˴�����á�
		// TODO Auto-generated method stub
		System.out.println("���ӳ��쳣��");
	}
}
/************��POJO����ByteBuf--����*************/

