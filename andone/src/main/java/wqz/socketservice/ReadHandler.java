package wqz.socketservice;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import org.springframework.beans.factory.annotation.Autowired;

import wqz.domain.GateStatus;
import wqz.domain.Gates;
import wqz.repository.GateStatusRepository;
import wqz.repository.GatesRepository;
import wqz.service.DateConvert;
/**
 * �յ������ɺ���ô��෽��������ݴ���
 * @author 13607
 *
 */
public class ReadHandler implements CompletionHandler<Integer, ByteBuffer>{
	
	@Autowired
	GatesRepository gatesRepository;
	
	@Autowired
	GateStatusRepository gateStatusRepository;
	
	@Autowired
	DateConvert dateConvert;
	
	@Autowired
	SocketMapDao socketMapDao;
	private AsynchronousSocketChannel channel;//�ͻ������ӵ�channel
	public ReadHandler(AsynchronousSocketChannel channel) {
		this.channel = channel;
	}
	/**
	 * ByteBuffer attachment:�Ѿ�������ɵĻ�����
	 */
	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		// TODO Auto-generated method stub
		attachment.flip();
		
		byte[] message = new byte[attachment.remaining()];
		attachment.get(message);
		
		String expression = null;
		try {
			expression = new String(message,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Server receive : " + expression);
		msgDeel(expression);
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		channel.read(buffer, buffer, new ReadHandler(channel));

	}
	
	public void msgDeel(String expression){
		//save message to GateStatus
		String[] receiveMsg = expression.trim().split(",");
		String name = receiveMsg[0];
		String date = receiveMsg[1];
		String status = receiveMsg[2];
		if(!socketMapDao.hasChannel(name)){
			socketMapDao.add(name, channel);
		}
		GateStatus gateStatus = new GateStatus();
		gateStatus.setName(name);
		gateStatus.setTime(dateConvert.parse(date));
		gateStatus.setStatus(status);
		System.out.println("sava gateStatus to table");
		gateStatusRepository.save(gateStatus);
		
		//update message to Gates depand GateName
		Gates gates = gatesRepository.findByName(name);
		if(gates == null){//gates table hava not this gate
			gates = new Gates();
			gates.setName(name);
			try {
				gates.setAddress(channel.getRemoteAddress().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gates.setStatus(status.equals("on") ? 1: 0);
			System.out.println("sava gates to table");
			gatesRepository.save(gates);
		}else{
			
			try {
				gatesRepository.updateGates(name, channel.getRemoteAddress().toString(), status, gates.getStatus());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		// TODO Auto-generated method stub
		try {
			channel.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
