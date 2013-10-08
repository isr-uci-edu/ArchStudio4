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
package edu.uci.isr.xarch.versions;

import org.w3c.dom.*;

import edu.uci.isr.xarch.*;

import java.util.*;

/**
 * DOM-Based implementation of the INode interface.
 *
 * @author Automatically generated by xArch apigen.
 */
public class NodeImpl implements INode, DOMBased{
	
	public static final String XSD_TYPE_NSURI = VersionsConstants.NS_URI;
	public static final String XSD_TYPE_NAME = "Node";
	
	protected IXArch xArch;
	
	/** Tag name for ids in this object. */
	public static final String ID_ATTR_NAME = "id";
	/** Tag name for immutables in this object. */
	public static final String IMMUTABLE_ATTR_NAME = "immutable";
	/** Tag name for versionIDs in this object. */
	public static final String VERSION_I_D_ELT_NAME = "versionID";
	/** Tag name for parents in this object. */
	public static final String PARENT_ELT_NAME = "parent";

	
	protected Element elt;
	
	private static SequenceOrder seqOrd = new SequenceOrder(
		new QName[]{
			new QName(VersionsConstants.NS_URI, VERSION_I_D_ELT_NAME),
			new QName(VersionsConstants.NS_URI, PARENT_ELT_NAME)
		}
	);
	
	public NodeImpl(Element elt){
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
				NodeImpl cloneImpl = new NodeImpl(cloneElt);
				cloneImpl.setXArch(getXArch());
				return cloneImpl;
			}
			else if(depth == 1){
				Element cloneElt = (Element)elt.cloneNode(false);
				cloneElt = (Element)doc.importNode(cloneElt, true);
				NodeImpl cloneImpl = new NodeImpl(cloneElt);
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
				NodeImpl cloneImpl = new NodeImpl(cloneElt);
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
				if(!DOMUtils.hasXSIType(elt, "http://www.ics.uci.edu/pub/arch/xArch/versions.xsd", baseTypeName)){
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
		return INode.TYPE_METADATA;
	}

	public XArchInstanceMetadata getInstanceMetadata(){
		return new XArchInstanceMetadata(XArchUtils.getPackageTitle(elt.getNamespaceURI()));
	}
	/**
	 * Set the id attribute on this object.
	 * @param id attribute value.
	 */
	public void setId(String id){
		{
			String oldValue = getId();
			if(oldValue == null ? id == null : oldValue.equals(id))
				return;
			DOMUtils.removeAttribute(elt, VersionsConstants.NS_URI, ID_ATTR_NAME);
			IXArch _x = getXArch();
			if(_x != null){
				_x.fireXArchEvent(
					new XArchEvent(this, 
					XArchEvent.CLEAR_EVENT,
					XArchEvent.ATTRIBUTE_CHANGED,
					"id", oldValue,
					XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this), true)
				);
			}
		}
		DOMUtils.setAttribute(elt, VersionsConstants.NS_URI, ID_ATTR_NAME, id);
		IXArch _x = getXArch();
		if(_x != null){
			_x.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.SET_EVENT,
				XArchEvent.ATTRIBUTE_CHANGED,
				"id", id,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
		
	/**
	 * Removes the id attribute from this object.
	 */
	public void clearId(){
		String oldValue = getId();
		if(oldValue == null)
			return;
		DOMUtils.removeAttribute(elt, VersionsConstants.NS_URI, ID_ATTR_NAME);
		IXArch _x = getXArch();
		if(_x != null){
			_x.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.CLEAR_EVENT,
				XArchEvent.ATTRIBUTE_CHANGED,
				"id", oldValue,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	/**
	 * Gets the value of the id attribute on this object.
	 * @return id attribute's value or <code>null</code> if that
	 * attribute is not set.
	 */
	public String getId(){
		return DOMUtils.getAttributeValue(elt, VersionsConstants.NS_URI, ID_ATTR_NAME);
	}
	
	/**
	 * Determines if this object's id attribute has the
	 * given value.
	 * @param id value to test.
	 * @return <code>true</code> if the values match, <code>false</code> otherwise.
	 * Matching is done by string-matching.
	 */
	public boolean hasId(String id){
		return DOMUtils.objNullEq(getId(), id);
	}

	/**
	 * Set the immutable attribute on this object.
	 * @param immutable attribute value.
	 */
	public void setImmutable(String immutable){
		{
			String oldValue = getImmutable();
			if(oldValue == null ? immutable == null : oldValue.equals(immutable))
				return;
			DOMUtils.removeAttribute(elt, VersionsConstants.NS_URI, IMMUTABLE_ATTR_NAME);
			IXArch _x = getXArch();
			if(_x != null){
				_x.fireXArchEvent(
					new XArchEvent(this, 
					XArchEvent.CLEAR_EVENT,
					XArchEvent.ATTRIBUTE_CHANGED,
					"immutable", oldValue,
					XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this), true)
				);
			}
		}
		DOMUtils.setAttribute(elt, VersionsConstants.NS_URI, IMMUTABLE_ATTR_NAME, immutable);
		IXArch _x = getXArch();
		if(_x != null){
			_x.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.SET_EVENT,
				XArchEvent.ATTRIBUTE_CHANGED,
				"immutable", immutable,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
		
	/**
	 * Removes the immutable attribute from this object.
	 */
	public void clearImmutable(){
		String oldValue = getImmutable();
		if(oldValue == null)
			return;
		DOMUtils.removeAttribute(elt, VersionsConstants.NS_URI, IMMUTABLE_ATTR_NAME);
		IXArch _x = getXArch();
		if(_x != null){
			_x.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.CLEAR_EVENT,
				XArchEvent.ATTRIBUTE_CHANGED,
				"immutable", oldValue,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	/**
	 * Gets the value of the immutable attribute on this object.
	 * @return immutable attribute's value or <code>null</code> if that
	 * attribute is not set.
	 */
	public String getImmutable(){
		return DOMUtils.getAttributeValue(elt, VersionsConstants.NS_URI, IMMUTABLE_ATTR_NAME);
	}
	
	/**
	 * Determines if this object's immutable attribute has the
	 * given value.
	 * @param immutable value to test.
	 * @return <code>true</code> if the values match, <code>false</code> otherwise.
	 * Matching is done by string-matching.
	 */
	public boolean hasImmutable(String immutable){
		return DOMUtils.objNullEq(getImmutable(), immutable);
	}


	public void setVersionID(IVersionID value){
		if(!(value instanceof DOMBased)){
			throw new IllegalArgumentException("Cannot handle non-DOM-based xArch entities.");
		}
		{
			IVersionID oldElt = getVersionID();
			DOMUtils.removeChildren(elt, VersionsConstants.NS_URI, VERSION_I_D_ELT_NAME);
			
			IXArch context = getXArch();
			if(context != null){
				context.fireXArchEvent(
					new XArchEvent(this, 
					XArchEvent.CLEAR_EVENT,
					XArchEvent.ELEMENT_CHANGED,
					"versionID", oldElt,
					XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this), true)
				);
			}
		}
		Element newChildElt = (Element)(((DOMBased)value).getDOMNode());
		newChildElt = DOMUtils.cloneAndRename(newChildElt, VersionsConstants.NS_URI, VERSION_I_D_ELT_NAME);
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
				"versionID", value,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public void clearVersionID(){
		IVersionID oldElt = getVersionID();
		DOMUtils.removeChildren(elt, VersionsConstants.NS_URI, VERSION_I_D_ELT_NAME);
		
		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.CLEAR_EVENT,
				XArchEvent.ELEMENT_CHANGED,
				"versionID", oldElt,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
	
	public IVersionID getVersionID(){
		NodeList nl = DOMUtils.getChildren(elt, VersionsConstants.NS_URI, VERSION_I_D_ELT_NAME);
		if(nl.getLength() == 0){
			return null;
		}
		else{
			Element el = (Element)nl.item(0);
			IXArch de = getXArch();
			if(de != null){
				IXArchElement cachedXArchElt = de.getWrapper(el);
				if(cachedXArchElt != null){
					return (IVersionID)cachedXArchElt;
				}
			}
			
			Object o = makeDerivedWrapper(el, "VersionID");
			if(o != null){
				try{
					((edu.uci.isr.xarch.IXArchElement)o).setXArch(getXArch());
					if(de != null){
						de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)o));
					}
					return (IVersionID)o;
				}
				catch(Exception e){}
			}
			VersionIDImpl eltImpl = new VersionIDImpl(el);
			eltImpl.setXArch(getXArch());
			if(de != null){
				de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)eltImpl));
			}
			return eltImpl;
		}
	}
	
	public boolean hasVersionID(IVersionID value){
		IVersionID thisValue = getVersionID();
		IVersionID thatValue = value;
		
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

	public void addParent(edu.uci.isr.xarch.instance.IXMLLink newParent){
		if(!(newParent instanceof DOMBased)){
			throw new IllegalArgumentException("Cannot handle non-DOM-based xArch entities.");
		}
		Element newChildElt = (Element)(((DOMBased)newParent).getDOMNode());
		newChildElt = DOMUtils.cloneAndRename(newChildElt, VersionsConstants.NS_URI, PARENT_ELT_NAME);
		((DOMBased)newParent).setDOMNode(newChildElt);

		synchronized(DOMUtils.getDOMLock(elt)){
			elt.appendChild(newChildElt);
			DOMUtils.order(elt, getSequenceOrder());
		}

		IXArch context = getXArch();
		if(context != null){
			context.fireXArchEvent(
				new XArchEvent(this, 
				XArchEvent.ADD_EVENT,
				XArchEvent.ELEMENT_CHANGED,
				"parent", newParent,
				XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
			);
		}
	}
		
	public void addParents(Collection parents){
		for(Iterator en = parents.iterator(); en.hasNext(); ){
			edu.uci.isr.xarch.instance.IXMLLink elt = (edu.uci.isr.xarch.instance.IXMLLink)en.next();
			addParent(elt);
		}
	}		
		
	public void clearParents(){
		//DOMUtils.removeChildren(elt, VersionsConstants.NS_URI, PARENT_ELT_NAME);
		Collection coll = getAllParents();
		removeParents(coll);
	}
	
	public void removeParent(edu.uci.isr.xarch.instance.IXMLLink parentToRemove){
		if(!(parentToRemove instanceof DOMBased)){
			throw new IllegalArgumentException("Cannot handle non-DOM-based xArch entities.");
		}
		NodeList nl = DOMUtils.getChildren(elt, VersionsConstants.NS_URI, PARENT_ELT_NAME);
		for(int i = 0; i < nl.getLength(); i++){
			Node n = nl.item(i);
			if(n == ((DOMBased)parentToRemove).getDOMNode()){
				synchronized(DOMUtils.getDOMLock(elt)){
					elt.removeChild(n);
				}

				IXArch context = getXArch();
				if(context != null){
					context.fireXArchEvent(
						new XArchEvent(this, 
						XArchEvent.REMOVE_EVENT,
						XArchEvent.ELEMENT_CHANGED,
						"parent", parentToRemove,
						XArchUtils.getDefaultXArchImplementation().isContainedIn(xArch, this))
					);
				}

				return;
			}
		}
	}
	
	public void removeParents(Collection parents){
		for(Iterator en = parents.iterator(); en.hasNext(); ){
			edu.uci.isr.xarch.instance.IXMLLink elt = (edu.uci.isr.xarch.instance.IXMLLink)en.next();
			removeParent(elt);
		}
	}
	
	public Collection getAllParents(){
		NodeList nl = DOMUtils.getChildren(elt, VersionsConstants.NS_URI, PARENT_ELT_NAME);
		int nlLength = nl.getLength();
		ArrayList v = new ArrayList(nlLength);
		IXArch de = getXArch();
		for(int i = 0; i < nlLength; i++){
			Element el = (Element)nl.item(i);
			boolean found = false;
			if(de != null){
				IXArchElement cachedXArchElt = de.getWrapper(el);
				if(cachedXArchElt != null){
					v.add((edu.uci.isr.xarch.instance.IXMLLink)cachedXArchElt);
					found = true;
				}
			}
			if(!found){
				Object o = makeDerivedWrapper(el, "XMLLink");	
				if(o != null){
					try{
						((edu.uci.isr.xarch.IXArchElement)o).setXArch(getXArch());
						if(de != null){
							de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)o));
						}
						v.add((edu.uci.isr.xarch.instance.IXMLLink)o);
					}
					catch(Exception e){
						edu.uci.isr.xarch.instance.XMLLinkImpl eltImpl = new edu.uci.isr.xarch.instance.XMLLinkImpl((Element)nl.item(i));
						eltImpl.setXArch(getXArch());
						if(de != null){
							de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)eltImpl));
						}
						v.add(eltImpl);
					}
				}
				else{
					edu.uci.isr.xarch.instance.XMLLinkImpl eltImpl = new edu.uci.isr.xarch.instance.XMLLinkImpl((Element)nl.item(i));
					eltImpl.setXArch(getXArch());
					if(de != null){
						de.cacheWrapper(el, ((edu.uci.isr.xarch.IXArchElement)eltImpl));
					}
					v.add(eltImpl);
				}
			}
		}
		return v;
	}

	public boolean hasParent(edu.uci.isr.xarch.instance.IXMLLink parentToCheck){
		Collection c = getAllParents();
		
		for(Iterator en = c.iterator(); en.hasNext(); ){
			edu.uci.isr.xarch.instance.IXMLLink elt = (edu.uci.isr.xarch.instance.IXMLLink)en.next();
			if(elt.isEquivalent(parentToCheck)){
				return true;
			}
		}
		return false;
	}
	
	public Collection hasParents(Collection parentsToCheck){
		Vector v = new Vector();
		for(Iterator en = parentsToCheck.iterator(); en.hasNext(); ){
			edu.uci.isr.xarch.instance.IXMLLink elt = (edu.uci.isr.xarch.instance.IXMLLink)en.next();
			v.addElement(new Boolean(hasParent(elt)));
		}
		return v;
	}
		
	public boolean hasAllParents(Collection parentsToCheck){
		for(Iterator en = parentsToCheck.iterator(); en.hasNext(); ){
			edu.uci.isr.xarch.instance.IXMLLink elt = (edu.uci.isr.xarch.instance.IXMLLink)en.next();
			if(!hasParent(elt)){
				return false;
			}
		}
		return true;
	}
	public boolean isEqual(INode NodeToCheck){
		String thisId = getId();
		String thatId = NodeToCheck.getId();
		
		if((thisId == null) || (thatId == null)){
			throw new IllegalArgumentException("One of the arguments is missing an ID.");
		}
		return thisId.equals(thatId);
	}
	
	public boolean isEquivalent(INode c){
		return (getClass().equals(c.getClass())) &&
		hasImmutable(c.getImmutable()) &&
			hasVersionID(c.getVersionID()) &&
			hasAllParents(c.getAllParents()) &&
			c.hasAllParents(getAllParents()) ;
	}

}
