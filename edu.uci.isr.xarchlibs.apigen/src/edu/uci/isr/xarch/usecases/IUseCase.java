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

import java.util.Collection;
import edu.uci.isr.xarch.XArchActionMetadata;
import edu.uci.isr.xarch.XArchTypeMetadata;
import edu.uci.isr.xarch.XArchPropertyMetadata;

/**
 * Interface for accessing objects of the
 * UseCase <code>xsi:type</code> in the
 * usecases namespace.
 * 
 * @author Automatically generated by xArch apigen
 */
public interface IUseCase extends edu.uci.isr.xarch.IXArchElement{

	public final static XArchTypeMetadata TYPE_METADATA = new XArchTypeMetadata(
		XArchTypeMetadata.XARCH_ELEMENT,
		"usecases", "UseCase", edu.uci.isr.xarch.IXArchElement.TYPE_METADATA,
		new XArchPropertyMetadata[]{
			XArchPropertyMetadata.createAttribute("id", "instance", "Identifier", null, null),
			XArchPropertyMetadata.createElement("actor", "usecases", "Actor", 0, XArchPropertyMetadata.UNBOUNDED)},
		new XArchActionMetadata[]{});

	/**
	 * Set the id attribute on this UseCase.
	 * @param id id
	 * @exception FixedValueException if the attribute has a fixed value
	 * and the value passed is not the fixed value.
	*/
	public void setId(String id);

	/**
	 * Remove the id attribute from this UseCase.
	 */
	public void clearId();

	/**
	 * Get the id attribute from this UseCase.
	 * if the attribute has a fixed value, this function will
	 * return that fixed value, even if it is not actually present
	 * in the XML document.
	 * @return id on this UseCase
	 */
	public String getId();

	/**
	 * Determine if the id attribute on this UseCase
	 * has the given value.
	 * @param id Attribute value to compare
	 * @return <code>true</code> if they match; <code>false</code>
	 * otherwise.
	 */
	public boolean hasId(String id);


	/**
	 * Add a actor to this UseCase.
	 * @param newActor actor to add.
	 */
	public void addActor(IActor newActor);

	/**
	 * Add a collection of actors to this UseCase.
	 * @param actors actors to add.
	 */
	public void addActors(Collection actors);

	/**
	 * Remove all actors from this UseCase.
	 */
	public void clearActors();

	/**
	 * Remove the given actor from this UseCase.
	 * Matching is done by the <code>isEquivalent(...)</code> function.
	 * @param actorToRemove actor to remove.
	 */
	public void removeActor(IActor actorToRemove);

	/**
	 * Remove all the given actors from this UseCase.
	 * Matching is done by the <code>isEquivalent(...)</code> function.
	 * @param actors actor to remove.
	 */
	public void removeActors(Collection actors);

	/**
	 * Get all the actors from this UseCase.
	 * @return all actors in this UseCase.
	 */
	public Collection getAllActors();

	/**
	 * Determine if this UseCase contains a given actor.
	 * @return <code>true</code> if this UseCase contains the given
	 * actorToCheck, <code>false</code> otherwise.
	 */
	public boolean hasActor(IActor actorToCheck);

	/**
	 * Determine if this UseCase contains the given set of actors.
	 * @param actorsToCheck actors to check for.
	 * @return Collection of <code>java.lang.Boolean</code>.  If the i<sup>th</sup>
	 * element in <code>actors</code> was found, then the i<sup>th</sup>
	 * element of the collection will be set to <code>true</code>, otherwise it
	 * will be set to <code>false</code>.  Matching is done with the
	 * <code>isEquivalent(...)</code> method.
	 */
	public Collection hasActors(Collection actorsToCheck);

	/**
	 * Determine if this UseCase contains each element in the 
	 * given set of actors.
	 * @param actorsToCheck actors to check for.
	 * @return <code>true</code> if every element in
	 * <code>actors</code> is found in this UseCase,
	 * <code>false</code> otherwise.
	 */
	public boolean hasAllActors(Collection actorsToCheck);

	/**
	 * Gets the actor from this UseCase with the given
	 * id.
	 * @param id ID to look for.
	 * @return actor with the given ID, or <code>null</code> if not found.
	 */
	public IActor getActor(String id);

	/**
	 * Gets the actors from this UseCase with the given
	 * ids.
	 * @param ids ID to look for.
	 * @return actors with the given IDs.  If an element with a given
	 * ID was not found, that ID is ignored.
	 */
	public Collection getActors(Collection ids);

	/**
	 * Determine if another UseCase has the same
	 * id as this one.
	 * @param UseCaseToCheck UseCase to compare with this
	 * one.
	 */
	public boolean isEqual(IUseCase UseCaseToCheck);
	/**
	 * Determine if another UseCase is equivalent to this one, ignoring
	 * ID's.
	 * @param UseCaseToCheck UseCase to compare to this one.
	 * @return <code>true</code> if all the child elements of this
	 * UseCase are equivalent, <code>false</code> otherwise.
	 */
	public boolean isEquivalent(IUseCase UseCaseToCheck);

}