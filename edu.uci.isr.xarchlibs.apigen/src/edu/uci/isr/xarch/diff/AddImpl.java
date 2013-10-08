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
package edu.uci.isr.xarch.diff;

import org.w3c.dom.*;

import edu.uci.isr.xarch.*;

import java.util.*;

/**
 * DOM-Based implementation of the IAdd interface.
 *
 * @author Automatically generated by xArch apigen.
 */
public class AddImpl implements IAdd, DOMBased{
	
	public static final String XSD_TYPE_NSURI = DiffConstants.NS_URI;
	public static final String XSD_TYPE_NAME = "Add";
	
	protected IXArch xArch;
	
	/** Tag name for components in this object. */
	public static final String COMPONENT_ELT_NAME = "component";
	/** Tag name for connectors in this object. */
	public static final String CONNECTOR_ELT_NAME = "connector";
	/** Tag name for links in this object. */
	public static final String LINK_ELT_NAME = "link";
	/** Tag name for groups in this object. */
	public static final String GROUP_ELT_NAME = "group";
	/** Tag name for componentTypes in this object. */
	public static final String COMPONENT_TYPE_ELT_NAME = "componentType";
	/** Tag name for connectorTypes in this object. */
	public static final String CONNECTOR_TYPE_ELT_NAME = "connectorType";
	/** Tag name for interfaceTypes in this object. */
	public static final String INTERFACE_TYPE_ELT_NAME = "interfaceType";

	
	protected Element elt;
	
	private static SequenceOrder seqOrd = new SequenceOrder(
		new QName[]{
			new QName(DiffConstants.NS_URI, COMPONENT_ELT_NAME),
			new QName(DiffConstants.NS_URI, CONNECTOR_ELT_NAME),
			new QName(DiffConstants.NS_URI, LINK_ELT_NAME),
			new QName(DiffConstants.NS_URI, GROUP_ELT_NAME),
			new QName(DiffConstants.NS_URI, COMPONENT_TYPE_ELT_NAME),
			new QName(DiffConstants.NS_URI, CONNECTOR_TYPE_ELT_NAME),
			new QName(DiffConstants.NS_URI, INTERFACE_TYPE_ELT_NAME)
		}
	);
	
	public AddImpl(Element elt){
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
	
	protected static SequenceOrder getSequenceOrder(){
		return seqOrd;
	}
	
	public void setXArch(IXArch xArch){
		this.xArch = xArch;
	}
	
	public IXArch getXArch(){
		return this.xArch;
	}

	public IXArchElement cloneElement(int depth){
		synchronized(DOMUtils.getDOMLock(elt)){
			Document doc = elt.getOwnerDocument();
			if(depth == 0){
				Element cloneElt = (Element)elt.cloneNode(false);
				cloneElt = (Element)doc.importNode(cloneElt, true);
				AddImpl cloneImpl = new AddImpl(cloneElt);
				cloneImpl.setXArch(getXArch());
				return cloneImpl;
			}
			else if(depth == 1){
				Element cloneElt = (Element)elt.cloneNode(false);
				cloneElt = (Element)doc.importNode(cloneElt, true);
				AddImpl cloneImpl = new AddImpl(cloneElt);
				cloneImpl.setXArch(getXArch());
				
				NodeList nl = elt.getChildNodes();
				int size = nl.getLength();
				for(int i = 0; i < size; i++){
					Node n = nl.item(i);
					Node cloneNode = (Node)n.cloneNode(false);
					cloneNode = doc.importNode(cloneNode, true);
					cloneElt.appendChild(cloneNode);
				}
				return cloneImpl;
			}
			else /* depth = infinity */{
				Element cloneElt = (Element)elt.cloneNode(true);
				cloneElt = (Element)doc.importNode(cloneElt, true);
				AddImpl cloneImpl = new AddImpl(cloneElt);
				cloneImpl.setXArch(getXArch());
				return cloneImpl;
			}
		}
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
	 * For internal use only.
	 */
	private static Object makeDerivedWrapper(Element elt, String baseTypeName){
		synchronized(DOMUtils.getDOMLock(elt)){
			QName typeName = XArchUtils.getXSIType(elt);
			if(typeName == null){
				return null;
			}
			else{
				if(!DOMUtils.hasXSIType(elt, "http://www.ics.uci.edu/pub/arch/xArch/diff.xsd", baseTypeName)){
					try{
						String packageTitle = XArchUtils.getPackageTitle(typeName.getNamespaceURI());
						String packageName = XArchUtils.getPackageName(packageTitle);
						String implName = XArchUtils.getImplName(packageName, typeName.getName());
						Class c = Class.forName(implName);
						java.lang.reflect.Constructor con = c.getConstructor(new Class[]{Element.class});
						Object o = con.newInstance(new Object[]{elt});
						return o;
					}
					catch(Exception e){
						//Lots of bad things could happen, but this
						//is OK, because this is best-effort anyway.
					}
				}
				return null;
			}
		}
	}

	public XArchTypeMetadata getTypeMetadata(){
		return IAdd.TYPE_METADATA;
	}

	public XArchInstanceMetadata getInstanceMetadata(){
		return new XArchInstanceMetadata(XArchUtils.getPackageTitle(elt.getNamespaceURI()));
	}

	public void setComponent(edu.uci.isr.xarch.types.IComponent value){
		if(!(value instanceof DOMBased)){
			throw new IllegalArgumentException("Cannot handle non-DOM-based xArch entities.");
		}
		{
			edu.uci.isr.xarch.types.IComponent oldElt = getComponent();
			DOMUtils.removeChildren(elt, DiffConstants.NS_URI, COMPONENT_ELT_NAME);
			
			IXArch context = getXArch();
			if(context != null){
				context.fireXArchEvent(
					new XArchEvent(this, 
					XArchEvent.CLEAR_EVENT,
					XArchEvent.ELEMENT_CHANGED,
					"component", oldElt,
					XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this), true)
				);
			}
		}
		Element newChildElt = (Element)(((DOMBased)value).getDOMNode());
		newChildElt = DOMUtils.cloneAndRename(newChildElt, DiffConstants.NS_URI, COMPONENT_ELT_NAME);
		((DOMBased)value).setDOMNode(newChildElt);
		
		synchronized(DOMUtils.getDOMLock(elt)){
			elt.appendChild(newChildElt);
			DOMUtils.order(elt, getSequenceOrder());
		}
		
		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.SET_EVENT,
				XArchEvent.ELEMENT_CHANGED,
				"component", value,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public void clearComponent(){
		edu.uci.isr.xarch.types.IComponent oldElt = getComponent();
		DOMUtils.removeChildren(elt, DiffConstants.NS_URI, COMPONENT_ELT_NAME);
		
		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.CLEAR_EVENT,
				XArchEvent.ELEMENT_CHANGED,
				"component", oldElt,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public edu.uci.isr.xarch.types.IComponent getComponent(){
		NodeList nl = DOMUtils.getChildren(elt, DiffConstants.NS_URI, COMPONENT_ELT_NAME);
		if(nl.getLength() == 0){
			return null;
		}
		else{
			Element el = (Element)nl.item(0);
			IXArch de = getXArch();
			if(de != null){
				IXArchElement cachedXArchElt = de.getWrapper(el);
				if(cachedXArchElt != null){
					return (edu.uci.isr.xarch.types.IComponent)cachedXArchElt;
				}
			}
			
			Object o = makeDerivedWrapper(el, "Component");
			if(o != null){
				try{
					((edu.uci.isr.xarch.IXArchElement)o).setXArch(getXArch());
					if(de != null){
						de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)o));
					}
					return (edu.uci.isr.xarch.types.IComponent)o;
				}
				catch(Exception e){}
			}
			edu.uci.isr.xarch.types.ComponentImpl eltImpl = new edu.uci.isr.xarch.types.ComponentImpl(el);
			eltImpl.setXArch(getXArch());
			if(de != null){
				de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)eltImpl));
			}
			return eltImpl;
		}
	}
	
	public boolean hasComponent(edu.uci.isr.xarch.types.IComponent value){
		edu.uci.isr.xarch.types.IComponent thisValue = getComponent();
		edu.uci.isr.xarch.types.IComponent thatValue = value;
		
		if((thisValue == null) && (thatValue == null)){
			return true;
		}
		else if((thisValue == null) && (thatValue != null)){
			return false;
		}
		else if((thisValue != null) && (thatValue == null)){
			return false;
		}
		return thisValue.isEquivalent(thatValue);
	}


	public void setConnector(edu.uci.isr.xarch.types.IConnector value){
		if(!(value instanceof DOMBased)){
			throw new IllegalArgumentException("Cannot handle non-DOM-based xArch entities.");
		}
		{
			edu.uci.isr.xarch.types.IConnector oldElt = getConnector();
			DOMUtils.removeChildren(elt, DiffConstants.NS_URI, CONNECTOR_ELT_NAME);
			
			IXArch context = getXArch();
			if(context != null){
				context.fireXArchEvent(
					new XArchEvent(this, 
					XArchEvent.CLEAR_EVENT,
					XArchEvent.ELEMENT_CHANGED,
					"connector", oldElt,
					XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this), true)
				);
			}
		}
		Element newChildElt = (Element)(((DOMBased)value).getDOMNode());
		newChildElt = DOMUtils.cloneAndRename(newChildElt, DiffConstants.NS_URI, CONNECTOR_ELT_NAME);
		((DOMBased)value).setDOMNode(newChildElt);
		
		synchronized(DOMUtils.getDOMLock(elt)){
			elt.appendChild(newChildElt);
			DOMUtils.order(elt, getSequenceOrder());
		}
		
		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.SET_EVENT,
				XArchEvent.ELEMENT_CHANGED,
				"connector", value,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public void clearConnector(){
		edu.uci.isr.xarch.types.IConnector oldElt = getConnector();
		DOMUtils.removeChildren(elt, DiffConstants.NS_URI, CONNECTOR_ELT_NAME);
		
		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.CLEAR_EVENT,
				XArchEvent.ELEMENT_CHANGED,
				"connector", oldElt,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public edu.uci.isr.xarch.types.IConnector getConnector(){
		NodeList nl = DOMUtils.getChildren(elt, DiffConstants.NS_URI, CONNECTOR_ELT_NAME);
		if(nl.getLength() == 0){
			return null;
		}
		else{
			Element el = (Element)nl.item(0);
			IXArch de = getXArch();
			if(de != null){
				IXArchElement cachedXArchElt = de.getWrapper(el);
				if(cachedXArchElt != null){
					return (edu.uci.isr.xarch.types.IConnector)cachedXArchElt;
				}
			}
			
			Object o = makeDerivedWrapper(el, "Connector");
			if(o != null){
				try{
					((edu.uci.isr.xarch.IXArchElement)o).setXArch(getXArch());
					if(de != null){
						de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)o));
					}
					return (edu.uci.isr.xarch.types.IConnector)o;
				}
				catch(Exception e){}
			}
			edu.uci.isr.xarch.types.ConnectorImpl eltImpl = new edu.uci.isr.xarch.types.ConnectorImpl(el);
			eltImpl.setXArch(getXArch());
			if(de != null){
				de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)eltImpl));
			}
			return eltImpl;
		}
	}
	
	public boolean hasConnector(edu.uci.isr.xarch.types.IConnector value){
		edu.uci.isr.xarch.types.IConnector thisValue = getConnector();
		edu.uci.isr.xarch.types.IConnector thatValue = value;
		
		if((thisValue == null) && (thatValue == null)){
			return true;
		}
		else if((thisValue == null) && (thatValue != null)){
			return false;
		}
		else if((thisValue != null) && (thatValue == null)){
			return false;
		}
		return thisValue.isEquivalent(thatValue);
	}


	public void setLink(edu.uci.isr.xarch.types.ILink value){
		if(!(value instanceof DOMBased)){
			throw new IllegalArgumentException("Cannot handle non-DOM-based xArch entities.");
		}
		{
			edu.uci.isr.xarch.types.ILink oldElt = getLink();
			DOMUtils.removeChildren(elt, DiffConstants.NS_URI, LINK_ELT_NAME);
			
			IXArch context = getXArch();
			if(context != null){
				context.fireXArchEvent(
					new XArchEvent(this, 
					XArchEvent.CLEAR_EVENT,
					XArchEvent.ELEMENT_CHANGED,
					"link", oldElt,
					XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this), true)
				);
			}
		}
		Element newChildElt = (Element)(((DOMBased)value).getDOMNode());
		newChildElt = DOMUtils.cloneAndRename(newChildElt, DiffConstants.NS_URI, LINK_ELT_NAME);
		((DOMBased)value).setDOMNode(newChildElt);
		
		synchronized(DOMUtils.getDOMLock(elt)){
			elt.appendChild(newChildElt);
			DOMUtils.order(elt, getSequenceOrder());
		}
		
		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.SET_EVENT,
				XArchEvent.ELEMENT_CHANGED,
				"link", value,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public void clearLink(){
		edu.uci.isr.xarch.types.ILink oldElt = getLink();
		DOMUtils.removeChildren(elt, DiffConstants.NS_URI, LINK_ELT_NAME);
		
		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.CLEAR_EVENT,
				XArchEvent.ELEMENT_CHANGED,
				"link", oldElt,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public edu.uci.isr.xarch.types.ILink getLink(){
		NodeList nl = DOMUtils.getChildren(elt, DiffConstants.NS_URI, LINK_ELT_NAME);
		if(nl.getLength() == 0){
			return null;
		}
		else{
			Element el = (Element)nl.item(0);
			IXArch de = getXArch();
			if(de != null){
				IXArchElement cachedXArchElt = de.getWrapper(el);
				if(cachedXArchElt != null){
					return (edu.uci.isr.xarch.types.ILink)cachedXArchElt;
				}
			}
			
			Object o = makeDerivedWrapper(el, "Link");
			if(o != null){
				try{
					((edu.uci.isr.xarch.IXArchElement)o).setXArch(getXArch());
					if(de != null){
						de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)o));
					}
					return (edu.uci.isr.xarch.types.ILink)o;
				}
				catch(Exception e){}
			}
			edu.uci.isr.xarch.types.LinkImpl eltImpl = new edu.uci.isr.xarch.types.LinkImpl(el);
			eltImpl.setXArch(getXArch());
			if(de != null){
				de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)eltImpl));
			}
			return eltImpl;
		}
	}
	
	public boolean hasLink(edu.uci.isr.xarch.types.ILink value){
		edu.uci.isr.xarch.types.ILink thisValue = getLink();
		edu.uci.isr.xarch.types.ILink thatValue = value;
		
		if((thisValue == null) && (thatValue == null)){
			return true;
		}
		else if((thisValue == null) && (thatValue != null)){
			return false;
		}
		else if((thisValue != null) && (thatValue == null)){
			return false;
		}
		return thisValue.isEquivalent(thatValue);
	}


	public void setGroup(edu.uci.isr.xarch.instance.IGroup value){
		if(!(value instanceof DOMBased)){
			throw new IllegalArgumentException("Cannot handle non-DOM-based xArch entities.");
		}
		{
			edu.uci.isr.xarch.instance.IGroup oldElt = getGroup();
			DOMUtils.removeChildren(elt, DiffConstants.NS_URI, GROUP_ELT_NAME);
			
			IXArch context = getXArch();
			if(context != null){
				context.fireXArchEvent(
					new XArchEvent(this, 
					XArchEvent.CLEAR_EVENT,
					XArchEvent.ELEMENT_CHANGED,
					"group", oldElt,
					XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this), true)
				);
			}
		}
		Element newChildElt = (Element)(((DOMBased)value).getDOMNode());
		newChildElt = DOMUtils.cloneAndRename(newChildElt, DiffConstants.NS_URI, GROUP_ELT_NAME);
		((DOMBased)value).setDOMNode(newChildElt);
		
		synchronized(DOMUtils.getDOMLock(elt)){
			elt.appendChild(newChildElt);
			DOMUtils.order(elt, getSequenceOrder());
		}
		
		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.SET_EVENT,
				XArchEvent.ELEMENT_CHANGED,
				"group", value,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public void clearGroup(){
		edu.uci.isr.xarch.instance.IGroup oldElt = getGroup();
		DOMUtils.removeChildren(elt, DiffConstants.NS_URI, GROUP_ELT_NAME);
		
		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.CLEAR_EVENT,
				XArchEvent.ELEMENT_CHANGED,
				"group", oldElt,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public edu.uci.isr.xarch.instance.IGroup getGroup(){
		NodeList nl = DOMUtils.getChildren(elt, DiffConstants.NS_URI, GROUP_ELT_NAME);
		if(nl.getLength() == 0){
			return null;
		}
		else{
			Element el = (Element)nl.item(0);
			IXArch de = getXArch();
			if(de != null){
				IXArchElement cachedXArchElt = de.getWrapper(el);
				if(cachedXArchElt != null){
					return (edu.uci.isr.xarch.instance.IGroup)cachedXArchElt;
				}
			}
			
			Object o = makeDerivedWrapper(el, "Group");
			if(o != null){
				try{
					((edu.uci.isr.xarch.IXArchElement)o).setXArch(getXArch());
					if(de != null){
						de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)o));
					}
					return (edu.uci.isr.xarch.instance.IGroup)o;
				}
				catch(Exception e){}
			}
			edu.uci.isr.xarch.instance.GroupImpl eltImpl = new edu.uci.isr.xarch.instance.GroupImpl(el);
			eltImpl.setXArch(getXArch());
			if(de != null){
				de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)eltImpl));
			}
			return eltImpl;
		}
	}
	
	public boolean hasGroup(edu.uci.isr.xarch.instance.IGroup value){
		edu.uci.isr.xarch.instance.IGroup thisValue = getGroup();
		edu.uci.isr.xarch.instance.IGroup thatValue = value;
		
		if((thisValue == null) && (thatValue == null)){
			return true;
		}
		else if((thisValue == null) && (thatValue != null)){
			return false;
		}
		else if((thisValue != null) && (thatValue == null)){
			return false;
		}
		return thisValue.isEquivalent(thatValue);
	}


	public void setComponentType(edu.uci.isr.xarch.types.IComponentType value){
		if(!(value instanceof DOMBased)){
			throw new IllegalArgumentException("Cannot handle non-DOM-based xArch entities.");
		}
		{
			edu.uci.isr.xarch.types.IComponentType oldElt = getComponentType();
			DOMUtils.removeChildren(elt, DiffConstants.NS_URI, COMPONENT_TYPE_ELT_NAME);
			
			IXArch context = getXArch();
			if(context != null){
				context.fireXArchEvent(
					new XArchEvent(this, 
					XArchEvent.CLEAR_EVENT,
					XArchEvent.ELEMENT_CHANGED,
					"componentType", oldElt,
					XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this), true)
				);
			}
		}
		Element newChildElt = (Element)(((DOMBased)value).getDOMNode());
		newChildElt = DOMUtils.cloneAndRename(newChildElt, DiffConstants.NS_URI, COMPONENT_TYPE_ELT_NAME);
		((DOMBased)value).setDOMNode(newChildElt);
		
		synchronized(DOMUtils.getDOMLock(elt)){
			elt.appendChild(newChildElt);
			DOMUtils.order(elt, getSequenceOrder());
		}
		
		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.SET_EVENT,
				XArchEvent.ELEMENT_CHANGED,
				"componentType", value,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public void clearComponentType(){
		edu.uci.isr.xarch.types.IComponentType oldElt = getComponentType();
		DOMUtils.removeChildren(elt, DiffConstants.NS_URI, COMPONENT_TYPE_ELT_NAME);
		
		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.CLEAR_EVENT,
				XArchEvent.ELEMENT_CHANGED,
				"componentType", oldElt,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public edu.uci.isr.xarch.types.IComponentType getComponentType(){
		NodeList nl = DOMUtils.getChildren(elt, DiffConstants.NS_URI, COMPONENT_TYPE_ELT_NAME);
		if(nl.getLength() == 0){
			return null;
		}
		else{
			Element el = (Element)nl.item(0);
			IXArch de = getXArch();
			if(de != null){
				IXArchElement cachedXArchElt = de.getWrapper(el);
				if(cachedXArchElt != null){
					return (edu.uci.isr.xarch.types.IComponentType)cachedXArchElt;
				}
			}
			
			Object o = makeDerivedWrapper(el, "ComponentType");
			if(o != null){
				try{
					((edu.uci.isr.xarch.IXArchElement)o).setXArch(getXArch());
					if(de != null){
						de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)o));
					}
					return (edu.uci.isr.xarch.types.IComponentType)o;
				}
				catch(Exception e){}
			}
			edu.uci.isr.xarch.types.ComponentTypeImpl eltImpl = new edu.uci.isr.xarch.types.ComponentTypeImpl(el);
			eltImpl.setXArch(getXArch());
			if(de != null){
				de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)eltImpl));
			}
			return eltImpl;
		}
	}
	
	public boolean hasComponentType(edu.uci.isr.xarch.types.IComponentType value){
		edu.uci.isr.xarch.types.IComponentType thisValue = getComponentType();
		edu.uci.isr.xarch.types.IComponentType thatValue = value;
		
		if((thisValue == null) && (thatValue == null)){
			return true;
		}
		else if((thisValue == null) && (thatValue != null)){
			return false;
		}
		else if((thisValue != null) && (thatValue == null)){
			return false;
		}
		return thisValue.isEquivalent(thatValue);
	}


	public void setConnectorType(edu.uci.isr.xarch.types.IConnectorType value){
		if(!(value instanceof DOMBased)){
			throw new IllegalArgumentException("Cannot handle non-DOM-based xArch entities.");
		}
		{
			edu.uci.isr.xarch.types.IConnectorType oldElt = getConnectorType();
			DOMUtils.removeChildren(elt, DiffConstants.NS_URI, CONNECTOR_TYPE_ELT_NAME);
			
			IXArch context = getXArch();
			if(context != null){
				context.fireXArchEvent(
					new XArchEvent(this, 
					XArchEvent.CLEAR_EVENT,
					XArchEvent.ELEMENT_CHANGED,
					"connectorType", oldElt,
					XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this), true)
				);
			}
		}
		Element newChildElt = (Element)(((DOMBased)value).getDOMNode());
		newChildElt = DOMUtils.cloneAndRename(newChildElt, DiffConstants.NS_URI, CONNECTOR_TYPE_ELT_NAME);
		((DOMBased)value).setDOMNode(newChildElt);
		
		synchronized(DOMUtils.getDOMLock(elt)){
			elt.appendChild(newChildElt);
			DOMUtils.order(elt, getSequenceOrder());
		}
		
		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.SET_EVENT,
				XArchEvent.ELEMENT_CHANGED,
				"connectorType", value,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public void clearConnectorType(){
		edu.uci.isr.xarch.types.IConnectorType oldElt = getConnectorType();
		DOMUtils.removeChildren(elt, DiffConstants.NS_URI, CONNECTOR_TYPE_ELT_NAME);
		
		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.CLEAR_EVENT,
				XArchEvent.ELEMENT_CHANGED,
				"connectorType", oldElt,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public edu.uci.isr.xarch.types.IConnectorType getConnectorType(){
		NodeList nl = DOMUtils.getChildren(elt, DiffConstants.NS_URI, CONNECTOR_TYPE_ELT_NAME);
		if(nl.getLength() == 0){
			return null;
		}
		else{
			Element el = (Element)nl.item(0);
			IXArch de = getXArch();
			if(de != null){
				IXArchElement cachedXArchElt = de.getWrapper(el);
				if(cachedXArchElt != null){
					return (edu.uci.isr.xarch.types.IConnectorType)cachedXArchElt;
				}
			}
			
			Object o = makeDerivedWrapper(el, "ConnectorType");
			if(o != null){
				try{
					((edu.uci.isr.xarch.IXArchElement)o).setXArch(getXArch());
					if(de != null){
						de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)o));
					}
					return (edu.uci.isr.xarch.types.IConnectorType)o;
				}
				catch(Exception e){}
			}
			edu.uci.isr.xarch.types.ConnectorTypeImpl eltImpl = new edu.uci.isr.xarch.types.ConnectorTypeImpl(el);
			eltImpl.setXArch(getXArch());
			if(de != null){
				de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)eltImpl));
			}
			return eltImpl;
		}
	}
	
	public boolean hasConnectorType(edu.uci.isr.xarch.types.IConnectorType value){
		edu.uci.isr.xarch.types.IConnectorType thisValue = getConnectorType();
		edu.uci.isr.xarch.types.IConnectorType thatValue = value;
		
		if((thisValue == null) && (thatValue == null)){
			return true;
		}
		else if((thisValue == null) && (thatValue != null)){
			return false;
		}
		else if((thisValue != null) && (thatValue == null)){
			return false;
		}
		return thisValue.isEquivalent(thatValue);
	}


	public void setInterfaceType(edu.uci.isr.xarch.types.IInterfaceType value){
		if(!(value instanceof DOMBased)){
			throw new IllegalArgumentException("Cannot handle non-DOM-based xArch entities.");
		}
		{
			edu.uci.isr.xarch.types.IInterfaceType oldElt = getInterfaceType();
			DOMUtils.removeChildren(elt, DiffConstants.NS_URI, INTERFACE_TYPE_ELT_NAME);
			
			IXArch context = getXArch();
			if(context != null){
				context.fireXArchEvent(
					new XArchEvent(this, 
					XArchEvent.CLEAR_EVENT,
					XArchEvent.ELEMENT_CHANGED,
					"interfaceType", oldElt,
					XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this), true)
				);
			}
		}
		Element newChildElt = (Element)(((DOMBased)value).getDOMNode());
		newChildElt = DOMUtils.cloneAndRename(newChildElt, DiffConstants.NS_URI, INTERFACE_TYPE_ELT_NAME);
		((DOMBased)value).setDOMNode(newChildElt);
		
		synchronized(DOMUtils.getDOMLock(elt)){
			elt.appendChild(newChildElt);
			DOMUtils.order(elt, getSequenceOrder());
		}
		
		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.SET_EVENT,
				XArchEvent.ELEMENT_CHANGED,
				"interfaceType", value,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public void clearInterfaceType(){
		edu.uci.isr.xarch.types.IInterfaceType oldElt = getInterfaceType();
		DOMUtils.removeChildren(elt, DiffConstants.NS_URI, INTERFACE_TYPE_ELT_NAME);
		
		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.CLEAR_EVENT,
				XArchEvent.ELEMENT_CHANGED,
				"interfaceType", oldElt,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public edu.uci.isr.xarch.types.IInterfaceType getInterfaceType(){
		NodeList nl = DOMUtils.getChildren(elt, DiffConstants.NS_URI, INTERFACE_TYPE_ELT_NAME);
		if(nl.getLength() == 0){
			return null;
		}
		else{
			Element el = (Element)nl.item(0);
			IXArch de = getXArch();
			if(de != null){
				IXArchElement cachedXArchElt = de.getWrapper(el);
				if(cachedXArchElt != null){
					return (edu.uci.isr.xarch.types.IInterfaceType)cachedXArchElt;
				}
			}
			
			Object o = makeDerivedWrapper(el, "InterfaceType");
			if(o != null){
				try{
					((edu.uci.isr.xarch.IXArchElement)o).setXArch(getXArch());
					if(de != null){
						de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)o));
					}
					return (edu.uci.isr.xarch.types.IInterfaceType)o;
				}
				catch(Exception e){}
			}
			edu.uci.isr.xarch.types.InterfaceTypeImpl eltImpl = new edu.uci.isr.xarch.types.InterfaceTypeImpl(el);
			eltImpl.setXArch(getXArch());
			if(de != null){
				de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)eltImpl));
			}
			return eltImpl;
		}
	}
	
	public boolean hasInterfaceType(edu.uci.isr.xarch.types.IInterfaceType value){
		edu.uci.isr.xarch.types.IInterfaceType thisValue = getInterfaceType();
		edu.uci.isr.xarch.types.IInterfaceType thatValue = value;
		
		if((thisValue == null) && (thatValue == null)){
			return true;
		}
		else if((thisValue == null) && (thatValue != null)){
			return false;
		}
		else if((thisValue != null) && (thatValue == null)){
			return false;
		}
		return thisValue.isEquivalent(thatValue);
	}

	public boolean isEquivalent(IAdd c){
		return (getClass().equals(c.getClass())) &&
			hasComponent(c.getComponent()) &&
			hasConnector(c.getConnector()) &&
			hasLink(c.getLink()) &&
			hasGroup(c.getGroup()) &&
			hasComponentType(c.getComponentType()) &&
			hasConnectorType(c.getConnectorType()) &&
			hasInterfaceType(c.getInterfaceType()) ;
	}

}
