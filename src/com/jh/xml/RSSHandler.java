package com.jh.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSHandler  extends DefaultHandler{
	
	private RSSItem currentItem ;
	
	private RSSChannel channel = new RSSChannel();
	private List<RSSItem> ItemList = new ArrayList<RSSItem>(); 
	
	private boolean inItem;
	private boolean inChannel;
	private boolean inContent;
	
	
	private int count;
	
	private StringBuffer content;
	private String characters = new String();
	
	public RSSHandler() {
	
	}
	
	public RSSChannel getChannel() {
		return channel;
	}

	
	/**
	 * @return the itemList
	 */
	public List<RSSItem> getItemList() {
		return ItemList;
	}

	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();	
		content = new StringBuffer();
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		if("channel".equals(qName)){
			inChannel = true;
			//System.out.println("channel: " + qName);
		}
		if("item".equals(qName) ){
			//System.out.println(count++ + "");
			//System.out.println("item: " + localName);
			inItem = true;
			currentItem = new RSSItem();
			currentItem.setSource(channel);
		}
		if("content:encoded".equals(qName) && inItem){
			//currentItem.setContent(characters);
			inContent = true;
			//System.out.println(" item content:encoded " + characters);
		}
		if("description".equals(qName)){
			
		}
		if("title".equals(qName) && inItem ){
		}
		if("link".equals(qName)  && inItem ){
			
		}
		if("description".equals(qName) && inItem ){
		}
		
		super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if("channel".equals(qName)){
			inChannel = false;
			//System.out.println("channel channel " + characters);
		}
		if("item".equals(qName) && inItem){
			inItem = false;
			ItemList.add(currentItem);
			//System.out.println("item item " + characters);
		}
		if("title".equals(qName)){
			if(inChannel && !inItem){  // 
				channel.setTitle(characters.toString());
				System.out.println("channel title " + characters);
			}
			if(inItem){
				currentItem.setTitle(characters.toString());
				System.out.println("item title " + characters);
			}
		}
		if("description".equals(qName)){
			if(inChannel && !inItem){
				channel.setDescription(characters.toString());
				//System.out.println("channel setDescription " + characters);
			}
			if(inItem){
				currentItem.setDescription(characters.toString());
				//System.out.println("item setDescription " + characters);
			}
		}
		if("link".equals(qName)){
			if(inChannel && !inItem){
				channel.setLink(characters.toString());
				
				//System.out.println("channel setLink " + characters);
			}
			if(inItem){
				currentItem.setLink(characters.toString());
				//System.out.println("item setLink " + characters);
			}
		}
		if("guid".equals(qName) && inItem){
			currentItem.setGuid(characters.toString());
			
			//System.out.println(" item guid " + characters);
		}
		
		if("content:encoded".equals(qName) && inItem){
			//currentItem.setContent(characters);
			inContent = false;
			//System.out.println(" item content:encoded " + content);
			currentItem.setContent(content.toString());
		}
		if("pubDate".equals(qName) && inItem){
			currentItem.setPubDate(characters.toString());
			//System.out.println(" item pubDate " + characters);
		}
		
		super.endElement(uri, localName, qName);
	}

	
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		
		//new StringBuffer(new String (ch, start, length));
		
		characters = new String(ch, start, length);
		
		if(inContent){
			//characters.delete(0, characters.length());
			content.append(characters);
		}else{// 否则清空content 数据以装入下一个item的数据
			
			content.delete(0, content.length());
		}
		
		//System.out.println(characters);
	}
}
