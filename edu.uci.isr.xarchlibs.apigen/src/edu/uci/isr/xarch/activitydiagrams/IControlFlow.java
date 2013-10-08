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
package edu.uci.isr.xarch.activitydiagrams;

import java.util.Collection;
import edu.uci.isr.xarch.XArchActionMetadata;
import edu.uci.isr.xarch.XArchTypeMetadata;
import edu.uci.isr.xarch.XArchPropertyMetadata;

/**
 * Interface for accessing objects of the
 * ControlFlow <code>xsi:type</code> in the
 * activitydiagrams namespace.  Extends and
 * inherits the properties of the
 * ActivityEdge <code>xsi:type</code>.
 * 
 * @author xArch apigen
 */
public interface IControlFlow extends IActivityEdge, edu.uci.isr.xarch.IXArchElement{

	public final static XArchTypeMetadata TYPE_METADATA = new XArchTypeMetadata(
		XArchTypeMetadata.XARCH_ELEMENT,
		"activitydiagrams", "ControlFlow", IActivityEdge.TYPE_METADATA,
		new XArchPropertyMetadata[]{},
		new XArchActionMetadata[]{});
	/**
	 * Determine if another ControlFlow is equivalent to this one, ignoring
	 * ID's.
	 * @param ControlFlowToCheck ControlFlow to compare to this one.
	 * @return <code>true</code> if all the child elements of this
	 * ControlFlow are equivalent, <code>false</code> otherwise.
	 */
	public boolean isEquivalent(IControlFlow ControlFlowToCheck);

}
