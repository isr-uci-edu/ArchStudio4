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
package edu.uci.isr.xarch.tracelink;

import java.util.Collection;
import edu.uci.isr.xarch.XArchActionMetadata;
import edu.uci.isr.xarch.XArchTypeMetadata;
import edu.uci.isr.xarch.XArchPropertyMetadata;

/**
 * Interface for accessing objects of the
 * ArchTraceLinks <code>xsi:type</code> in the
 * tracelink namespace.
 * 
 * @author Automatically generated by xArch apigen
 */
public interface IArchTraceLinks extends edu.uci.isr.xarch.IXArchElement{

	public final static XArchTypeMetadata TYPE_METADATA = new XArchTypeMetadata(
		XArchTypeMetadata.XARCH_ELEMENT,
		"tracelink", "ArchTraceLinks", edu.uci.isr.xarch.IXArchElement.TYPE_METADATA,
		new XArchPropertyMetadata[]{
			XArchPropertyMetadata.createElement("traceLink", "tracelink", "TraceLink", 0, XArchPropertyMetadata.UNBOUNDED)},
		new XArchActionMetadata[]{});

	/**
	 * Add a traceLink to this ArchTraceLinks.
	 * @param newTraceLink traceLink to add.
	 */
	public void addTraceLink(ITraceLink newTraceLink);

	/**
	 * Add a collection of traceLinks to this ArchTraceLinks.
	 * @param traceLinks traceLinks to add.
	 */
	public void addTraceLinks(Collection<ITraceLink> traceLinks);

	/**
	 * Remove all traceLinks from this ArchTraceLinks.
	 */
	public void clearTraceLinks();

	/**
	 * Remove the given traceLink from this ArchTraceLinks.
	 * Matching is done by the <code>isEquivalent(...)</code> function.
	 * @param traceLinkToRemove traceLink to remove.
	 */
	public void removeTraceLink(ITraceLink traceLinkToRemove);

	/**
	 * Remove all the given traceLinks from this ArchTraceLinks.
	 * Matching is done by the <code>isEquivalent(...)</code> function.
	 * @param traceLinks traceLink to remove.
	 */
	public void removeTraceLinks(Collection<ITraceLink> traceLinks);

	/**
	 * Get all the traceLinks from this ArchTraceLinks.
	 * @return all traceLinks in this ArchTraceLinks.
	 */
	public Collection<ITraceLink> getAllTraceLinks();

	/**
	 * Determine if this ArchTraceLinks contains a given traceLink.
	 * @return <code>true</code> if this ArchTraceLinks contains the given
	 * traceLinkToCheck, <code>false</code> otherwise.
	 */
	public boolean hasTraceLink(ITraceLink traceLinkToCheck);

	/**
	 * Determine if this ArchTraceLinks contains the given set of traceLinks.
	 * @param traceLinksToCheck traceLinks to check for.
	 * @return Collection of <code>java.lang.Boolean</code>.  If the i<sup>th</sup>
	 * element in <code>traceLinks</code> was found, then the i<sup>th</sup>
	 * element of the collection will be set to <code>true</code>, otherwise it
	 * will be set to <code>false</code>.  Matching is done with the
	 * <code>isEquivalent(...)</code> method.
	 */
	public Collection<Boolean> hasTraceLinks(Collection<ITraceLink> traceLinksToCheck);

	/**
	 * Determine if this ArchTraceLinks contains each element in the 
	 * given set of traceLinks.
	 * @param traceLinksToCheck traceLinks to check for.
	 * @return <code>true</code> if every element in
	 * <code>traceLinks</code> is found in this ArchTraceLinks,
	 * <code>false</code> otherwise.
	 */
	public boolean hasAllTraceLinks(Collection<ITraceLink> traceLinksToCheck);

	/**
	 * Determine if another ArchTraceLinks is equivalent to this one, ignoring
	 * ID's.
	 * @param ArchTraceLinksToCheck ArchTraceLinks to compare to this one.
	 * @return <code>true</code> if all the child elements of this
	 * ArchTraceLinks are equivalent, <code>false</code> otherwise.
	 */
	public boolean isEquivalent(IArchTraceLinks ArchTraceLinksToCheck);

}
