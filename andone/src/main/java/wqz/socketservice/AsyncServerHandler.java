package wqz.socketservice;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AsyncServerHandler implements Runnable{
	public CountDownLatch latch;
	public AsynchronousServerSocketChannel channel;
	public AsyncServerHandler(int port) {
		try {
			channel = AsynchronousServerSocketChannel.open();
			channel.bind(new InetSocketAddress(port));
			System.out.println("Server already run,port = " + port);
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		latch = new CountDownLatch(1);
		//socketserver start accept
		channel.accept(this,new AcceptHandler());
		try {
			latch.await(); //CountDownLatch await
		}catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
