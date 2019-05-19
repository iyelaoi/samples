package wqz.socketservice;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;



public class WriteHandler implements CompletionHandler<Integer, ByteBuffer> {
	private AsynchronousSocketChannel channel;
	public WriteHandler(AsynchronousSocketChannel channel){
		this.channel = channel;
	}
	
	@Override
	public void completed(Integer result, ByteBuffer buffer){
		// TODO Auto-generated method stub
		// buffer already send completed ?
		if(buffer.hasRemaining()) {
			channel.write(buffer, buffer, this);
		}else {
			ByteBuffer readBuffer = ByteBuffer.allocate(1024);
			channel.read(readBuffer, readBuffer, new ReadHandler(channel));
		}
	}
	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		// TODO Auto-generated method stub
		try {
			channel.close();
		}catch(IOException e) {
			
		}
	}	
}
