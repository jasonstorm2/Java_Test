package nettyLearn;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ���� ByteBuf�ĸ��ַ���
 * @author Administrator
 *
 */
public class ByteBufTest extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		/**********�±��Ǵ�0��ʼ��***********/
			ByteBuf buffer = (ByteBuf) msg;
			for (int i = 0; i < buffer.capacity(); i ++) {
			byte b = buffer.getByte(i);
			System.out.println((char) b);
			}
		/**********˳������������±� readerIndex ��writerIndex ***********/
			//�����±꽫buf��Ϊ���Σ��Ѷ����ɷ��������ɶ�����д
			//ע�⣬�ڵ���discardReadBytes()֮�󣬲��ܱ�֤��д�ֽڵ����ݡ�
			buffer.discardReadBytes(); //�����Ѷ����ֽڣ����տռ�
			
		/**********������ ***********/
			//�ж�buf�Ƿ�ɶ�
			while (buffer.isReadable()) {
			System.out.println(buffer.readByte());
			buffer.readBytes(buffer);
			}
		/**********д���� ***********/
			while (buffer.writableBytes() >= 4) {
			buffer.writeInt(new Random().nextInt());
			}		
		/**********����buffer���±� ***********/
			//��read�±��write�±����Ϊ0�������ù���ԭ��������
			buffer.clear();
		/**********����buffer���±� ***********/
			//��������
			//indexOf()
			//ByteBufProcessor
			//bytesBefore(byte)
			
		/**********�±�ı�ǣ���������һ������readerIndex ***********/
			buffer.resetReaderIndex();//�ѵ�ǰ��readerIndex�±�����Ϊ����ǵ��±�λ�� current readerIndex to   marked readerIndex 
			buffer.readerIndex(10);//���ñ�ǵ��±�λ��
			
	}

}
