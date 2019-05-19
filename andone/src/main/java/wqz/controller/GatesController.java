package wqz.controller;


import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import wqz.domain.GateStatus;
import wqz.domain.Gates;
import wqz.domain.PageBean;
import wqz.repository.GateStatusRepository;
import wqz.repository.GatesRepository;
import wqz.socketservice.SocketMapDao;
import wqz.socketservice.SocketUtil;



@Controller
public class GatesController {
	
	@Autowired
	SocketUtil socketUtil;
	
	@Autowired
	SocketMapDao socketMapDao;
	
	@Autowired
	GateStatusRepository gateStatusRepository;
	
	@Autowired
	GatesRepository gatesRepository;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getGateStatusPage(@RequestParam(value="gateName")String name,@RequestParam(value = "page", defaultValue = "0") Integer page,
	        @RequestParam(value = "size", defaultValue = "10") Integer size,ModelMap modelMap) {
	    Sort sort = new Sort(Direction.DESC, "id");
	    Pageable pageable = new PageRequest(page, size, sort);
	    Page<GateStatus> pageResult = gateStatusRepository.findByName(name,pageable);
	    modelMap.addAttribute("pageBean", new PageBean<>(pageResult));
	    
	    return "list";
	}
	
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="gates",method=RequestMethod.GET)
	public String getGates(@RequestParam(value = "page", defaultValue = "0") Integer page,
	        @RequestParam(value = "size", defaultValue = "10") Integer size,ModelMap modelMap) {
	    Sort sort = new Sort(Direction.DESC, "id");
	    Pageable pageable = new PageRequest(page, size, sort);
	    Page<Gates> pageResult = gatesRepository.findAll(pageable);
	    modelMap.addAttribute("pageBean", new PageBean<>(pageResult));
	    System.out.println("gates controller");
		return "gates";
	}
	@RequestMapping(value="gateControl",method=RequestMethod.GET)
	public String gateControl(@RequestParam(value="gateName") String gateName,@RequestParam(value="ctr") String status){
		AsynchronousSocketChannel channel = socketMapDao.get(gateName);
		if(channel == null){
			System.out.println("ctr channel = null gatename = " + gateName);
			return "dates";
		}else{
			int ctr = Integer.parseInt(status.trim());
			String control = "";
			switch(ctr){
				case 0: control = "on";break;
				case 1: control = "off";break;
				case 2: control = "awake";break;
				case 3: control = "open";break;
				default: control = "error";
			}
			byte[] bytes = control.getBytes();		
			ByteBuffer writeBuffer = ByteBuffer.wrap(bytes);
			writeBuffer.flip();
			socketUtil.send(gateName, control);			
		}
		return "dates";
	}
}
