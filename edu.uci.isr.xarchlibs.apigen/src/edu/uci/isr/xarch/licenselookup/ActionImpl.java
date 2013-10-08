/*
 * Copyright (c) 2000-2005 Regents of the University of California.
 * All rights reserved.
 *
 * This software was developed at the University of California, Irvine.
 *
 * Redistribution and use in source and binary forms are permitted
 * provided that the above copyright notice and this paragraph are
 * duplicated in all such forms and that any documentation,
 * advertising materials, and other materials related to such
 * distribution and use acknowledge that the software was developed
 * by the University of California, Irvine.  The name of the
 * University may not be used to endorse or promote products derived
 * from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED
 * WARRANTIES OF MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */
package edu.uci.isr.xarch.licenselookup;

import org.w3c.dom.*;

import edu.uci.isr.xarch.*;

/**
 * DOM-Based implementation of the IAction interface.
 *
 * @author Automatically generated by xArch apigen.
 */
public class ActionImpl implements IAction, DOMBased{
	
	public static final String XSD_TYPE_NSURI = LicenselookupConstants.NS_URI;
	public static final String XSD_TYPE_NAME = "Action";

	protected IXArch xArch;

	protected Element elt;
	
	public ActionImpl(Element elt){
		if(elt == null){
			throw new IllegalArgumentException("Element cannot be null.");
		}
		this.elt = elt;
	}
	
	public Node getDOMNode(){
		return elt;
	}
	
	public void setDOMNode(Node node){
		if(node.getNodeType() != Node.ELEMENT_NODE){
			throw new IllegalArgumentException("Base DOM node of this type must be an Element.");
		}
		elt = (Element)node;
	}

	//Override 'equals' to be DOM-based...	
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		if(!(o instanceof DOMBased)){
			return super.equals(o);
		}
		DOMBased db = (DOMBased)o;
		Node dbNode = db.getDOMNode();
		return dbNode.equals(getDOMNode());
	}

	//Override 'hashCode' to be based on the underlying node
	public int hashCode(){
		return getDOMNode().hashCode();
	}

	/**
	 * Set the xArch element that is the document element of 
	 * the document that is this object's context.
	 * @param xArch XArch document element for this element.
	 */
	public void setXArch(IXArch xArch){
		this.xArch = xArch;
	}

	/**
	 * Gets the xArch element that is the document element
	 * of the document that is this object's context.
	 * @return XArch document element for this element.
	 */
	public IXArch getXArch(){
		return this.xArch;
	}
	
	public IXArchElement cloneElement(int depth){
		synchronized(DOMUtils.getDOMLock(elt)){
			Document doc = elt.getOwnerDocument();
			if(depth == 0){
				Element cloneElt = (Element)elt.cloneNode(false);
				cloneElt = (Element)doc.importNode(cloneElt, true);
				ActionImpl cloneImpl = new ActionImpl(cloneElt);
				cloneImpl.setXArch(getXArch());
				IXArch de = getXArch();
				if(de != null){
					de.cacheWrapper(cloneElt, cloneImpl);
				}
				return cloneImpl;
			}
			else if(depth == 1){
				Element cloneElt = (Element)elt.cloneNode(false);
				cloneElt = (Element)doc.importNode(cloneElt, true);
				ActionImpl cloneImpl = new ActionImpl(cloneElt);
				cloneImpl.setXArch(getXArch());
				
				NodeList nl = elt.getChildNodes();
				int size = nl.getLength();
				for(int i = 0; i < size; i++){
					Node n = nl.item(i);
					Node cloneNode = (Node)n.cloneNode(false);
					cloneNode = doc.importNode(cloneNode, true);
					cloneElt.appendChild(cloneNode);
				}
				IXArch de = getXArch();
				if(de != null){
					de.cacheWrapper(cloneElt, cloneImpl);
				}
				return cloneImpl;
			}
			else /* depth = infinity */{
				Element cloneElt = (Element)elt.cloneNode(true);
				cloneElt = (Element)doc.importNode(cloneElt, true);
				ActionImpl cloneImpl = new ActionImpl(cloneElt);
				cloneImpl.setXArch(getXArch());
				IXArch de = getXArch();
				if(de != null){
					de.cacheWrapper(cloneElt, cloneImpl);
				}
				return cloneImpl;
			}
		}
	}

	public void setValue(String value){
		synchronized(DOMUtils.getDOMLock(elt)){
			{
				String oldValue = getValue();
				if(oldValue == null ? value == null : oldValue.equals(value))
					return;
				synchronized(DOMUtils.getDOMLock(elt)){
					//Get rid of any text nodes that are children of the element.
					elt.normalize();
				}
				NodeList nl = elt.getChildNodes();
				for(int i = (nl.getLength() - 1); i >= 0; i--){
					Node n = nl.item(i);
					if(n.getNodeType() == Node.TEXT_NODE){
						synchronized(DOMUtils.getDOMLock(elt)){
							elt.removeChild(n);
						}
		
						IXArch context = getXArch();
						if(context != null){
							context.fireXArchEvent(
								new XArchEvent(this, 
								XArchEvent.CLEAR_EVENT,
								XArchEvent.SIMPLE_TYPE_VALUE_CHANGED,
								"$SIMPLETYPEVALUE$", null,
								XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this), true)
							);
						}
					}
				}
			}
			elt.normalize();
			Document doc = elt.getOwnerDocument();
			Text txt = doc.createTextNode(value);
			elt.appendChild(txt);
		}

		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.SET_EVENT,
				XArchEvent.SIMPLE_TYPE_VALUE_CHANGED,
				"$SIMPLETYPEVALUE$", value,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public void clearValue(){
		synchronized(DOMUtils.getDOMLock(elt)){
			//Get rid of any text nodes that are children of the element.
			elt.normalize();
		}
		NodeList nl = elt.getChildNodes();
		for(int i = (nl.getLength() - 1); i >= 0; i--){
			Node n = nl.item(i);
			if(n.getNodeType() == Node.TEXT_NODE){
				synchronized(DOMUtils.getDOMLock(elt)){
					elt.removeChild(n);
				}

				IXArch context = getXArch();
				if(context != null){
					context.fireXArchEvent(
						new XArchEvent(this, 
						XArchEvent.CLEAR_EVENT,
						XArchEvent.SIMPLE_TYPE_VALUE_CHANGED,
						"$SIMPLETYPEVALUE$", null,
						XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
					);
				}
			}
		}
	}
	
	public String getValue(){
		synchronized(DOMUtils.getDOMLock(elt)){
			elt.normalize();
			NodeList nl = elt.getChildNodes();
			for(int i = (nl.getLength() - 1); i >= 0; i--){
				Node n = nl.item(i);
				if(n.getNodeType() == Node.TEXT_NODE){
					return n.getNodeValue();
				}
			}
			return null;
		}
	}
		
	public boolean hasValue(String value){
		String str1 = DOMUtils.normalizeString(getValue());
		String str2 = DOMUtils.normalizeString(value);
		return DOMUtils.objNullEq(str1, str2);
	}
	
	public boolean isEquivalent(IAction ActionToCheck){
		return ActionToCheck.hasValue(getValue());
	}
	
	public XArchTypeMetadata getTypeMetadata(){
		return IAction.TYPE_METADATA;
	}

	public XArchInstanceMetadata getInstanceMetadata(){
		return new XArchInstanceMetadata(XArchUtils.getPackageTitle(elt.getNamespaceURI()));
	}
	
	//public boolean equals(Object o){
	//	if(!(o instanceof IAction)){
	//		return false;
	//	}
	//	else return isEquivalent((IAction)o);
	//}
	
}
