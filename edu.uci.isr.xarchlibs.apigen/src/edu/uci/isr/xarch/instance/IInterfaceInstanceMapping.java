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
package edu.uci.isr.xarch.instance;

import java.util.Collection;
import edu.uci.isr.xarch.XArchActionMetadata;
import edu.uci.isr.xarch.XArchTypeMetadata;
import edu.uci.isr.xarch.XArchPropertyMetadata;

/**
 * Interface for accessing objects of the
 * InterfaceInstanceMapping <code>xsi:type</code> in the
 * instance namespace.
 * 
 * @author Automatically generated by xArch apigen
 */
public interface IInterfaceInstanceMapping extends edu.uci.isr.xarch.IXArchElement{

	public final static XArchTypeMetadata TYPE_METADATA = new XArchTypeMetadata(
		XArchTypeMetadata.XARCH_ELEMENT,
		"instance", "InterfaceInstanceMapping", edu.uci.isr.xarch.IXArchElement.TYPE_METADATA,
		new XArchPropertyMetadata[]{
			XArchPropertyMetadata.createAttribute("id", "instance", "Identifier", null, null),
			XArchPropertyMetadata.createElement("description", "instance", "Description", 1, 1),
			XArchPropertyMetadata.createElement("outerInterfaceInstance", "instance", "XMLLink", 1, 1),
			XArchPropertyMetadata.createElement("innerInterfaceInstance", "instance", "XMLLink", 1, 1)},
		new XArchActionMetadata[]{});

	/**
	 * Set the id attribute on this InterfaceInstanceMapping.
	 * @param id id
	 * @exception FixedValueException if the attribute has a fixed value
	 * and the value passed is not the fixed value.
	*/
	public void setId(String id);

	/**
	 * Remove the id attribute from this InterfaceInstanceMapping.
	 */
	public void clearId();

	/**
	 * Get the id attribute from this InterfaceInstanceMapping.
	 * if the attribute has a fixed value, this function will
	 * return that fixed value, even if it is not actually present
	 * in the XML document.
	 * @return id on this InterfaceInstanceMapping
	 */
	public String getId();

	/**
	 * Determine if the id attribute on this InterfaceInstanceMapping
	 * has the given value.
	 * @param id Attribute value to compare
	 * @return <code>true</code> if they match; <code>false</code>
	 * otherwise.
	 */
	public boolean hasId(String id);


	/**
	 * Set the description for this InterfaceInstanceMapping.
	 * @param value new description
	 */
	public void setDescription(IDescription value);

	/**
	 * Clear the description from this InterfaceInstanceMapping.
	 */
	public void clearDescription();

	/**
	 * Get the description from this InterfaceInstanceMapping.
	 * @return description
	 */
	public IDescription getDescription();

	/**
	 * Determine if this InterfaceInstanceMapping has the given description
	 * @param descriptionToCheck description to compare
	 * @return <code>true</code> if the descriptions are equivalent,
	 * <code>false</code> otherwise
	 */
	public boolean hasDescription(IDescription descriptionToCheck);

	/**
	 * Set the outerInterfaceInstance for this InterfaceInstanceMapping.
	 * @param value new outerInterfaceInstance
	 */
	public void setOuterInterfaceInstance(IXMLLink value);

	/**
	 * Clear the outerInterfaceInstance from this InterfaceInstanceMapping.
	 */
	public void clearOuterInterfaceInstance();

	/**
	 * Get the outerInterfaceInstance from this InterfaceInstanceMapping.
	 * @return outerInterfaceInstance
	 */
	public IXMLLink getOuterInterfaceInstance();

	/**
	 * Determine if this InterfaceInstanceMapping has the given outerInterfaceInstance
	 * @param outerInterfaceInstanceToCheck outerInterfaceInstance to compare
	 * @return <code>true</code> if the outerInterfaceInstances are equivalent,
	 * <code>false</code> otherwise
	 */
	public boolean hasOuterInterfaceInstance(IXMLLink outerInterfaceInstanceToCheck);

	/**
	 * Set the innerInterfaceInstance for this InterfaceInstanceMapping.
	 * @param value new innerInterfaceInstance
	 */
	public void setInnerInterfaceInstance(IXMLLink value);

	/**
	 * Clear the innerInterfaceInstance from this InterfaceInstanceMapping.
	 */
	public void clearInnerInterfaceInstance();

	/**
	 * Get the innerInterfaceInstance from this InterfaceInstanceMapping.
	 * @return innerInterfaceInstance
	 */
	public IXMLLink getInnerInterfaceInstance();

	/**
	 * Determine if this InterfaceInstanceMapping has the given innerInterfaceInstance
	 * @param innerInterfaceInstanceToCheck innerInterfaceInstance to compare
	 * @return <code>true</code> if the innerInterfaceInstances are equivalent,
	 * <code>false</code> otherwise
	 */
	public boolean hasInnerInterfaceInstance(IXMLLink innerInterfaceInstanceToCheck);
	/**
	 * Determine if another InterfaceInstanceMapping has the same
	 * id as this one.
	 * @param InterfaceInstanceMappingToCheck InterfaceInstanceMapping to compare with this
	 * one.
	 */
	public boolean isEqual(IInterfaceInstanceMapping InterfaceInstanceMappingToCheck);
	/**
	 * Determine if another InterfaceInstanceMapping is equivalent to this one, ignoring
	 * ID's.
	 * @param InterfaceInstanceMappingToCheck InterfaceInstanceMapping to compare to this one.
	 * @return <code>true</code> if all the child elements of this
	 * InterfaceInstanceMapping are equivalent, <code>false</code> otherwise.
	 */
	public boolean isEquivalent(IInterfaceInstanceMapping InterfaceInstanceMappingToCheck);

}
