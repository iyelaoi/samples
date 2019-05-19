package wqz.socketservice;

import java.nio.channels.AsynchronousSocketChannel;

import org.springframework.stereotype.Component;

import wqz.domain.socketMap;

@Component
public class SocketMapDao {
	
	public AsynchronousSocketChannel get(String name){
		if(hasChannel(name)){
			return socketMap.socketChannelMap.get(name);
		}
		return null;
		
	}
	
	public boolean hasChannel(String name){
		return socketMap.socketChannelMap.containsKey(name);
	}
	public boolean add(String name,AsynchronousSocketChannel channel){
		if(socketMap.socketChannelMap.containsKey(name)){
			return false;
		}
		socketMap.socketChannelMap.put(name, channel);
		socketMap.channelCount = socketMap.socketChannelMap.size();
		return true;
	}
	public boolean delete(String name){
		if(socketMap.socketChannelMap.containsKey(name)){
			socketMap.socketChannelMap.remove(name);
			socketMap.channelCount = socketMap.socketChannelMap.size();
			return true;
		}
		return false;
		
	}

}
