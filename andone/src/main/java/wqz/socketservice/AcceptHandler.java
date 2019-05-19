package wqz.socketservice;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServerHandler>{
	/**
	 * AsynchronousSocketChannel channel:�����յ��ͻ������Ӻ��Զ���װΪ�˶��󲢴��룬��Ϊһ���ͻ������ӵ�  channel
	 */
	@Override
	public void completed(AsynchronousSocketChannel channel, AsyncServerHandler serverHandler) {
		// TODO Auto-generated method stub
		Server.clientCount++;
		System.out.println("connected client count = " + Server.clientCount);
		
		//continue accept
		serverHandler.channel.accept(serverHandler,this);
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//channel.read(dst, attachment, handler);
		//����
		//ByteBuffer dst�����ջ������ȡchannel�е���ݰ�
		//A attachment�� �첽channelЯ��ĸ���֪ͨ�ص�ʱ��Ϊ����ʹ��
		//CompletionHandler<Integer,? super A> handler:����֪ͨ�ص�ҵ��
		channel.read(buffer, buffer, new ReadHandler(channel));
	}
	@Override
	public void failed(Throwable exc, AsyncServerHandler serverHandler) {
		// TODO Auto-generated method stub
		exc.printStackTrace();
		serverHandler.latch.countDown();
	}

}
