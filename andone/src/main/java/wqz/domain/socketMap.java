package wqz.domain;

import java.nio.channels.AsynchronousSocketChannel;
import java.util.HashMap;

public class socketMap{
	public static HashMap<String, AsynchronousSocketChannel> socketChannelMap;
	public static int channelCount = socketChannelMap.size();
}
