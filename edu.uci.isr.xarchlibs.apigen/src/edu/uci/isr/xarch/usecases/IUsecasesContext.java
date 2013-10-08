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
package edu.uci.isr.xarch.usecases;

import java.util.*;

import edu.uci.isr.xarch.*;

import org.w3c.dom.*;

import edu.uci.isr.xarch.IXArch;
import edu.uci.isr.xarch.IXArchContext;

/**
 * The context interface for the usecases package.
 * This interface is used to create objects that are used
 * in the usecases namespace.
 *
 * @author Automatically Generated by xArch apigen
 */
public interface IUsecasesContext extends IXArchContext{

	/**
	 * Create an IUseCase object in this namespace.
	 * @return New IUseCase object.
	 */
	public IUseCase createUseCase();

	/**
	 * Brings an IUseCase object created in another
	 * context into this context.
	 * @param value Object to recontextualize.
	 * @return <code>value</code> object in this namespace.
	 */
	public IUseCase recontextualizeUseCase(IUseCase value);

	/**
	 * Create an IActor object in this namespace.
	 * @return New IActor object.
	 */
	public IActor createActor();

	/**
	 * Brings an IActor object created in another
	 * context into this context.
	 * @param value Object to recontextualize.
	 * @return <code>value</code> object in this namespace.
	 */
	public IActor recontextualizeActor(IActor value);

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
	 * Create a top-level element of type <code>IUseCase</code>.
	 * This function should be used in lieu of <code>createUseCase</code>
	 * if the element is to be added as a sub-object of <code>IXArch</code>.
	 * @return new IUseCase suitable for adding
	 * as a child of xArch.
	 */
	public IUseCase createUseCaseElement();

	/**
	 * Gets the IUseCase child from the given <code>IXArch</code>
	 * element.  If there are multiple matching children, this returns the first one.
	 * @param xArch <code>IXArch</code> object from which to get the child.
	 * @return <code>IUseCase</code> that is the child
	 * of <code>xArch</code> or <code>null</code> if no such object exists.
	 */
	public IUseCase getUseCase(IXArch xArch);

	/**
	 * Gets all the IUseCase children from the given 
	 * <code>IXArch</code> element.
	 * @param xArch <code>IXArch</code> object from which to get the children.
	 * @return Collection of <code>IUseCase</code> that are 	
	 * the children of <code>xArch</code> or an empty collection if no such object exists.
	 */
	public Collection getAllUseCases(IXArch xArch);


	public final static XArchTypeMetadata TYPE_METADATA = new XArchTypeMetadata(
		XArchTypeMetadata.XARCH_CONTEXT,
		"usecases", null, null,
		new XArchPropertyMetadata[]{},
		new XArchActionMetadata[]{
			new XArchActionMetadata(XArchActionMetadata.CREATE, null, IUseCase.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.RECONTEXTUALIZE, IUseCase.TYPE_METADATA, IUseCase.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.CREATE, null, IActor.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.RECONTEXTUALIZE, IActor.TYPE_METADATA, IActor.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.CREATE, null, edu.uci.isr.xarch.instance.IDescription.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.RECONTEXTUALIZE, edu.uci.isr.xarch.instance.IDescription.TYPE_METADATA, edu.uci.isr.xarch.instance.IDescription.TYPE_METADATA),
			new XArchActionMetadata(XArchActionMetadata.CREATE_ELEMENT, null, IUseCase.TYPE_METADATA)});

}

