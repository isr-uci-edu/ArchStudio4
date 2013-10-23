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
package edu.uci.isr.xarch.activitydiagramsspecial;

import java.util.*;

import edu.uci.isr.xarch.*;

import org.w3c.dom.*;

import edu.uci.isr.xarch.IXArch;
import edu.uci.isr.xarch.IXArchContext;

/**
 * The context interface for the activitydiagramsspecial package.
 * This interface is used to create objects that are used
 * in the activitydiagramsspecial namespace.
 *
 * @author Automatically Generated by xArch apigen
 */
public interface IActivitydiagramsspecialContext extends IXArchContext{

	/**
	 * Create an IActivityDiagram object in this namespace.
	 * @return New IActivityDiagram object.
	 */
	public IActivityDiagram createActivityDiagram();

	/**
	 * Brings an IActivityDiagram object created in another
	 * context into this context.
	 * @param value Object to recontextualize.
	 * @return <code>value</code> object in this namespace.
	 */
	public IActivityDiagram recontextualizeActivityDiagram(IActivityDiagram value);

	/**
	 * Promote an object of type <code>edu.uci.isr.xarch.uml212superstructure.IActivityDiagram</code>
	 * to one of type <code>IActivityDiagram</code>.  xArch APIs
	 * are structured in such a way that a regular cast is not possible
	 * to change interface types, so casting must be done through these
	 * promotion functions.  If the <code>edu.uci.isr.xarch.uml212superstructure.IActivityDiagram</code>
	 * object wraps a DOM element that is the base type, then the 
	 * <code>xsi:type</code> of the base element is promoted.  Otherwise, 
	 * it is left unchanged.
	 *
	 * This function also emits an <CODE>XArchEvent</CODE> with type
	 * PROMOTE_TYPE.  The source for this events is the pre-promoted
	 * IXArchElement object (should no longer be used), and the
	 * target is the post-promotion object.  The target name is
	 * the name of the interface class that was the target of the
	 * promotion.
	 * 
	 * @param value Object to promote.
	 * @return Promoted object.
	 */
	public IActivityDiagram promoteToActivityDiagram(
	edu.uci.isr.xarch.uml212superstructure.IActivityDiagram value);

	/**
	 * Create an edu.uci.isr.xarch.uml212superstructure.IActor object in this namespace.
	 * @return New edu.uci.isr.xarch.uml212superstructure.IActor object.
	 */
	public edu.uci.isr.xarch.uml212superstructure.IActor createActor();

	/**
	 * Brings an edu.uci.isr.xarch.uml212superstructure.IActor object created in another
	 * context into this context.
	 * @param value Object to recontextualize.
	 * @return <code>value</code> object in this namespace.
	 */
	public edu.uci.isr.xarch.uml212superstructure.IActor recontextualizeActor(edu.uci.isr.xarch.uml212superstructure.IActor value);

	/**
	 * Create an INote object in this namespace.
	 * @return New INote object.
	 */
	public INote createNote();

	/**
	 * Brings an INote object created in another
	 * context into this context.
	 * @param value Object to recontextualize.
	 * @return <code>value</code> object in this namespace.
	 */
	public INote recontextualizeNote(INote value);

	/**
	 * Create an edu.uci.isr.xarch.instance.IDescription object in this namespace.
	 * @return New edu.uci.isr.xarch.instance.IDescription object.
	 */
	public edu.uci.isr.xarch.instance.IDescription createDescription();

	/**
	 * Brings an edu.uci.isr.xarch.instance.IDescription object created in another
	 * context into this context.
	 * @param value Object to recontextualize.
	 * @return <code>value</code> object in this namespace.
	 */
	public edu.uci.isr.xarch.instance.IDescription recontextualizeDescription(edu.uci.isr.xarch.instance.IDescription value);

	/**
	 * Create an IExternalReference object in this namespace.
	 * @return New IExternalReference object.
	 */
	public IExternalReference createExternalReference();

	/**
	 * Brings an IExternalReference object created in another
	 * context into this context.
	 * @param value Object to recontextualize.
	 * @return <code>value</code> object in this namespace.
	 */
	public IExternalReference recontextualizeExternalReference(IExternalReference value);

	/**
	 * Create an edu.uci.isr.xarch.instance.IXMLLink object in this namespace.
	 * @return New edu.uci.isr.xarch.instance.IXMLLink object.
	 */
	public edu.uci.isr.xarch.instance.IXMLLink createXMLLink();

	/**
	 * Brings an edu.uci.isr.xarch.instance.IXMLLink object created in another
	 * context into this context.
	 * @param value Object to recontextualize.
	 * @return <code>value</code> object in this namespace.
	 */
	public edu.uci.isr.xarch.instance.IXMLLink recontextualizeXMLLink(edu.uci.isr.xarch.instance.IXMLLink value);

	/**
	 * Create a top-level element of type <code>IActivityDiagram</code>.
	 * This function should be used in lieu of <code>createActivityDiagram</code>
	 * if the element is to be added as a sub-object of <code>IXArch</code>.
	 * @return new IActivityDiagram suitable for adding
	 * as a child of xArch.
	 */
	public IActivityDiagram createActivityDiagramElement();

	/**
	 * Gets the IActivityDiagram child from the given <code>IXArch</code>
	 * element.  If there are multiple matching children, this returns the first one.
	 * @param xArch <code>IXArch</code> object from which to get the child.
	 * @return <code>IActivityDiagram</code> that is the child
	 * of <code>xArch</code> or <code>null</code> if no such object exists.
	 */
	public IActivityDiagram getActivityDiagram(IXArch xArch);

	/**
	 * Gets all the IActivityDiagram children from the given 
	 * <code>IXArch</code> element.
	 * @param xArch <code>IXArch</code> object from which to get the children.
	 * @return Collection of <code>IActivityDiagram</code> that are 	
	 * the children of <code>xArch</code> or an empty collection if no such object exists.
	 */
	public Collection getAllActivityDiagrams(IXArch xArch);


	public final static XArchTypeMetadata TYPE_METADATA = new XArchTypeMetadata(
		XArchTypeMetadata.XARCH_CONTEXT,
		"activitydiagramsspecial", null, null,
		new XArchPropertyMetadata[]{},
		new XArchActionMetadata[]{
			new XArchActionMetadata(XArchActionMetadata.CREATE, null, IActivityDiagram.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.RECONTEXTUALIZE, IActivityDiagram.TYPE_METADATA, IActivityDiagram.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.PROMOTE, edu.uci.isr.xarch.uml212superstructure.IActivityDiagram.TYPE_METADATA, IActivityDiagram.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.CREATE, null, edu.uci.isr.xarch.uml212superstructure.IActor.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.RECONTEXTUALIZE, edu.uci.isr.xarch.uml212superstructure.IActor.TYPE_METADATA, edu.uci.isr.xarch.uml212superstructure.IActor.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.CREATE, null, INote.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.RECONTEXTUALIZE, INote.TYPE_METADATA, INote.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.CREATE, null, edu.uci.isr.xarch.instance.IDescription.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.RECONTEXTUALIZE, edu.uci.isr.xarch.instance.IDescription.TYPE_METADATA, edu.uci.isr.xarch.instance.IDescription.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.CREATE, null, IExternalReference.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.RECONTEXTUALIZE, IExternalReference.TYPE_METADATA, IExternalReference.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.CREATE, null, edu.uci.isr.xarch.instance.IXMLLink.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.RECONTEXTUALIZE, edu.uci.isr.xarch.instance.IXMLLink.TYPE_METADATA, edu.uci.isr.xarch.instance.IXMLLink.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.CREATE_ELEMENT, null, IActivityDiagram.TYPE_METADATA)});

}
