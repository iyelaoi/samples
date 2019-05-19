package wqz.socketservice;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocketUtil {
	
	@Autowired
	SocketMapDao socketMapDao;
	
	
	public void send(String gateName,String ctrMsg){
		AsynchronousSocketChannel channel = socketMapDao.get(gateName);
		if(channel == null){
			System.out.println("socketMap hava not this gate: " + gateName);
			return;
		}
		byte[] bytes = ctrMsg.getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		writeBuffer.put(bytes);
		writeBuffer.flip();
		System.out.println("send ctrMsg("+ ctrMsg+") to gate(" + gateName+")");
		channel.write(writeBuffer, writeBuffer, new WriteHandler(channel));
	}
	
	

}
