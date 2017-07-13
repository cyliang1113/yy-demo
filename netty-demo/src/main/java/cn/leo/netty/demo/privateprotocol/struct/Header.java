package cn.leo.netty.demo.privateprotocol.struct;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息头
 * 
 * @author chenyouliang
 */
public class Header {
	/**
	 * 0xabef(2个字节) + 主版本号(1个字节) + 次版本号(1个字节)
	 * 
	 * 0xabef固定值, 协议类型
	 */
	private int crcCode = 0xabef0101;

	private int length;

	private long sessionID;

	private byte type;

	private byte priority;

	private Map<String, Object> attachment = new HashMap<String, Object>();

	public int getCrcCode() {
		return crcCode;
	}

	public void setCrcCode(int crcCode) {
		this.crcCode = crcCode;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public long getSessionID() {
		return sessionID;
	}

	public void setSessionID(long sessionID) {
		this.sessionID = sessionID;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte getPriority() {
		return priority;
	}

	public void setPriority(byte priority) {
		this.priority = priority;
	}

	public Map<String, Object> getAttachment() {
		return attachment;
	}

	public void setAttachment(Map<String, Object> attachment) {
		this.attachment = attachment;
	}

}
