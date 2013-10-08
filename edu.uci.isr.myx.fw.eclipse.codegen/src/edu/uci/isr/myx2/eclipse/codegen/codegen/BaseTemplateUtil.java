package edu.uci.isr.myx2.eclipse.codegen.codegen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import edu.uci.isr.myx.fw.IMyxBrickItems;
import edu.uci.isr.myx.fw.IMyxDynamicBrick;
import edu.uci.isr.myx.fw.IMyxLifecycleProcessor;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.IMyxProvidedServiceProvider;
import edu.uci.isr.myx.fw.MyxLifecycleAdapter;
import edu.uci.isr.myx.fw.MyxRegistry;
import edu.uci.isr.myx.fw.MyxUtils;
import edu.uci.isr.myx2.eclipse.codegen.brick.CodegenBrick;
import edu.uci.isr.myx2.eclipse.codegen.brick.CodegenBrickInterface;
import edu.uci.isr.myx2.eclipse.codegen.brick.MethodContainer;
import edu.uci.isr.myx2.eclipse.codegen.util.TextUtil;
import edu.uci.isr.myx2.eclipse.extension.ConnectionTiming;
import edu.uci.isr.myx2.eclipse.extension.Direction;
import edu.uci.isr.myx2.eclipse.extension.IInterface;
import edu.uci.isr.myx2.eclipse.extension.TemplateType;

/**
 * The utility to generate an abstract myx base class. This class is supposed to
 * be used in "MyxBase.java.jet" template. Two files will be generated by myx
 * code generation application:
 * <ul>
 * <li>one is "Myxbase" class, the base class that is to encapsulate the
 * complexity of the myx framework,</li>
 * <li>and the other is "MyxComp" class, the class where the user is going to
 * implement the services.</li>
 * </ul>
 * This utility class is to produce "MyxBase" java file. See
 * {@link CompTemplateUtil}.
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class BaseTemplateUtil {

	/** the line separator string */
	static final String lineseparator = System.getProperty("line.separator");

	/**
	 * Returns java comment like string. Adds '*' after each line separator.
	 * 
	 * @param str
	 * @param tab
	 *            the number of tabs
	 * @return
	 */
	public static String getComment(String str, int tab) {
		StringBuilder tabStr = new StringBuilder();
		for (int i = 0; i < tab; i++) {
			tabStr.append('\t');
		}
		return str.replace(lineseparator, lineseparator + tabStr.toString() + " * ");
	}

	/**
	 * Returns a collection of fully qualified class names to be declared as
	 * "import".
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<String> getImports(CodegenBrick brick) {
		assert brick != null;

		Collection<String> javaImports = new HashSet<String>();

		// Myx.fw
		if (!brick.hasParentBrick()) {
			javaImports.add(IMyxBrickItems.class.getName());
			javaImports.add(IMyxDynamicBrick.class.getName());
			javaImports.add(IMyxLifecycleProcessor.class.getName());
			javaImports.add(IMyxProvidedServiceProvider.class.getName());
			if (shouldCreateMyxRegistry(brick)) {
				javaImports.add(MyxRegistry.class.getName());
			}

			// java.util
			//javaImports.add(Collection.class.getName());
			javaImports.add(List.class.getName());
			javaImports.add(ArrayList.class.getName());
		}
		javaImports.add(IMyxName.class.getName());
		javaImports.add(MyxLifecycleAdapter.class.getName());
		if (!brick.getAllInterfaces().isEmpty()) {
			javaImports.add(MyxUtils.class.getName());
		}
		// javaImports.add(ListenerList.class.getName());

		// ////////////
		// in
		javaImports.addAll(brick.getInterfaceFQClassNames( //
		        Direction.IN, //
		        TemplateType.DELEGATE, //
		        TemplateType.DELEGATE_WITH_GETTERS, //
		        TemplateType.IMPLEMENTED_IN_BRICK, //
		        TemplateType.DELEGATE_TO_MYX_REGISTRY));
		// in brickInterfaces of delegate or implemented type
		javaImports.addAll(brick.getVoidMethodArgsFQClassNames( //
		        Direction.IN, //
		        TemplateType.DELEGATE_TO_MYX_REGISTRY));

		// ////////////
		// out-single
		javaImports.addAll(brick.getInterfaceFQClassNames( //
		        Direction.OUT_SINGLE, //
		        TemplateType.DELEGATE, //
		        TemplateType.DELEGATE_WITH_GETTERS, //
		        TemplateType.IMPLEMENTED_IN_BRICK, //
		        TemplateType.NONE));
		javaImports.addAll(brick.getVoidMethodArgsFQClassNames( //
		        Direction.OUT_SINGLE, //
		        TemplateType.IMPLEMENTED_IN_BRICK));

		// ////////////
		// out-multi
		javaImports.addAll(brick.getInterfaceFQClassNames( //
		        Direction.OUT_MULTI, //
		        TemplateType.DELEGATE, //
		        TemplateType.DELEGATE_WITH_GETTERS, //
		        TemplateType.IMPLEMENTED_IN_BRICK, //
		        TemplateType.IMPLEMENTED_IN_DELEGATE,// only for out-multi
		        TemplateType.IMPLEMENTED_IN_DELEGATE_WITH_GETTERS, //
		        TemplateType.NONE));
		javaImports.addAll(brick.getVoidMethodArgsFQClassNames( //
		        Direction.OUT_MULTI, //
		        TemplateType.IMPLEMENTED_IN_DELEGATE, //
		        TemplateType.IMPLEMENTED_IN_DELEGATE_WITH_GETTERS, //
		        TemplateType.IMPLEMENTED_IN_BRICK));
		if (!BaseTemplateUtil.getOutMultiMyxVariables(brick).isEmpty()) {
			javaImports.add(ArrayList.class.getName());
			javaImports.add(Collection.class.getName());
		}
		if (!BaseTemplateUtil.getOutMultiMyxVariablesGetter(brick).isEmpty()) {
			javaImports.add(Collection.class.getName());
		}
		if (!BaseTemplateUtil.getProxyAdapters(brick).isEmpty()) {
			javaImports.add(Collection.class.getName());
		}

		// //////////////
		// extends parent brick
		if (brick.hasParentBrick()) {
			javaImports.add(brick.getFQParentClassName());
			javaImports.addAll(brick.getFQParentConstructorParams());
		}

		List<String> importsList = new ArrayList<String>(javaImports);
		Collections.sort(importsList);
		return importsList;

	}

	/**
	 * Returns the string expression of type of the given brickInterface
	 * variable
	 * 
	 * @param intf
	 * @return
	 */
	public static String getVariableType(IInterface intf) {
		if (intf.getFQJavaInterfaceName() == null) {
			return null;
		}
		if (intf.getDirection() == Direction.OUT_MULTI) {
			return "Collection< " + TextUtil.getClassPart(intf.getFQJavaInterfaceName()) + ">";
		}
		else {
			return TextUtil.getClassPart(intf.getFQJavaInterfaceName());
		}
	}

	/**
	 * Returns the string expression of type of the given brickInterface proxy
	 * variable
	 * 
	 * @param intf
	 * @return
	 */
	public static String getProxyVariableType(IInterface intf) {
		if (intf.getFQJavaInterfaceName() == null) {
			return null;
		}
		return TextUtil.getClassPart(intf.getFQJavaInterfaceName());
	}

	/**
	 * Returns the string expression of "implements" classes declaration.
	 * 
	 * @param brick
	 * @return
	 */
	public static String getImplementsString(CodegenBrick brick) {
		assert brick != null;

		if (brick.hasParentBrick() && brick.getImplementsClasses().isEmpty()) {

			// nothing to implement
			return "";
		}

		// "implements"
		StringBuilder impls = new StringBuilder();

		impls.append(" implements ");
		if (!brick.hasParentBrick()) {
			impls.append("IMyxDynamicBrick, IMyxLifecycleProcessor, IMyxProvidedServiceProvider, ");
		}
		for (String implClass : brick.getImplementsClasses()) {
			impls.append(TextUtil.getClassPart(implClass)).append(", ");
		}
		impls.delete(impls.lastIndexOf(", "), impls.length());
		return impls.toString();
	}

	public static String getExtendsString(CodegenBrick brick) {
		assert brick != null;

		if (!brick.hasParentBrick()) {
			// independent class
			return "";
		}

		// "extends"
		return " extends " + TextUtil.getClassPart(brick.getFQParentClassName());
	}

	/**
	 * The prefix of IMyx variable name for "in" interface
	 */
	public final static String IN_IMYX_NAME_PREFIX = "INTERFACE_NAME_IN_";

	/**
	 * Returns the "in" IMyx variable name of the given brickInterface.
	 * 
	 * @param intf
	 * @return
	 */
	public static String getInMyxName(IInterface intf) {
		assert intf != null;

		if (intf.getDirection() != Direction.IN) {
			throw new IllegalArgumentException("The direction of " + intf.getName() + " must be " + Direction.IN);
		}
		return IN_IMYX_NAME_PREFIX + TextUtil.upperHyphenate(intf.getName());
	}

	/**
	 * The prefix of IMyx variable name for "out" interface
	 */
	public final static String OUT_IMYX_NAME_PREFIX = "INTERFACE_NAME_OUT_";

	/**
	 * Returns the "out" IMyx variable name of the given brickInterface.
	 * 
	 * @param intf
	 * @return
	 */
	public static String getOutMyxName(IInterface intf) {
		assert intf != null;

		switch (intf.getDirection()) {
		case OUT_MULTI:
		case OUT_SINGLE:
			return OUT_IMYX_NAME_PREFIX + TextUtil.upperHyphenate(intf.getName());
		default:
			throw new IllegalArgumentException("The direction of " + intf.getName() + " must be either " + Direction.OUT_MULTI + " or " + Direction.OUT_SINGLE);
		}
	}

	/**
	 * Returns the collection of {@link CodegenBrickInterface}s that should be
	 * declared as "in" IMyx variables.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getInMyxVariables(CodegenBrick brick) {
		assert brick != null;

		// in-brickInterface variables.
		// generate variables only when template type is Delegate
		Collection<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : brick.getInInterfaces()) {
			if (isInMyxVariable(intf)) {
				intfs.add(intf);
			}
		}
		return intfs;

	}

	/**
	 * Returns the collection of variables that will be available in init()
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getInitAvailableVariables(CodegenBrick brick) {
		// brickInterfaces that will be assigned values before init() method is
		// called.
		// all the out brickInterfaces
		List<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : brick.getInterfaces( //
		        ConnectionTiming.CONNECT_BEFORE_INIT)) {
			if (intf.getDirection() == Direction.IN) {
				// in brickInterfaces must be assigned values before init() by
				// this class.
				continue;
			}
			if (BaseTemplateUtil.isMyxVariable(intf)) {
				intfs.add(intf);
			}
		}
		return intfs;
	}

	/**
	 * Returns the collection of getter methods that will be available in init()
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getInitAvailableGetters(CodegenBrick brick) {
		// brickInterfaces that will be assigned values before init() method is
		// called.
		// all the out brickInterfaces
		List<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : brick.getInterfaces( //
		        ConnectionTiming.CONNECT_BEFORE_INIT)) {
			if (intf.getDirection() == Direction.IN) {
				// in brickInterfaces must be assigned values before init() by
				// this class.
				continue;
			}
			if (BaseTemplateUtil.isMyxVariableGetter(intf)) {
				intfs.add(intf);
			}
		}
		return intfs;
	}

	/**
	 * Returns the collection of getter variables that must be assigned values.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getInitAssignVariables(CodegenBrick brick) {
		// brickInterfaces that must be assigned values before init() method
		// exits.
		// connectAfterInit (for the server)
		List<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : brick.getInterfaces( //
		        ConnectionTiming.CONNECT_BEFORE_BEGIN)) {
			if (intf.getDirection() != Direction.IN) {
				// only in brickInterfaces need to be assigned values.
				continue;
			}
			if (intf.getTemplateType() == TemplateType.DELEGATE) {
				intfs.add(intf);
			}
		}
		return intfs;
	}

	/**
	 * Returns the collection of getter methods that must be assigned values.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getInitAssignMethods(CodegenBrick brick) {
		// brickInterfaces that must be assigned values before init() method
		// exits.
		// connectAfterInit (for the server)
		List<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : brick.getInterfaces(//
		        ConnectionTiming.CONNECT_BEFORE_BEGIN)) {
			if (intf.getDirection() != Direction.IN) {
				// only in brickInterfaces need to be assigned values.
				continue;
			}
			if (intf.getTemplateType() == TemplateType.DELEGATE_WITH_GETTERS) {
				intfs.add(intf);
			}
		}
		return intfs;
	}

	/**
	 * Returns the collection of {@link CodegenBrickInterface}s that should be
	 * declared as "in" IMyx variables.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getInMyxVariablesGetter(CodegenBrick brick) {
		assert brick != null;

		// in-brickInterface variables.
		// generate variables only when template type is Delegate
		Collection<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : brick.getInInterfaces()) {
			if (isInMyxVariableGetter(intf)) {
				intfs.add(intf);
			}
		}
		return intfs;
	}

	public final static String GETTER_METHOD_PREFIX = "get";
	public final static String GETTER_METHOD_POSTFIX = "ServiceObject";

	/**
	 * Returns a getter method name
	 * 
	 * @param name
	 * @return
	 */
	public static String getGetterMethodName(String name) {
		assert name != null;

		return GETTER_METHOD_PREFIX + TextUtil.upperFirstChar(name) + GETTER_METHOD_POSTFIX;

	}

	public final static String SETTER_METHOD_PREFIX = "set";
	public final static String SETTER_METHOD_POSTFIX = "ServiceObject";

	/**
	 * Returns a setter method name
	 * 
	 * @param name
	 * @return
	 */
	public static String getSetterMethodName(String name) {
		assert name != null;

		return SETTER_METHOD_PREFIX + TextUtil.upperFirstChar(name) + SETTER_METHOD_POSTFIX;

	}

	/**
	 * Returns true if the given brickInterface should be declared as "in" IMyx
	 * variable.
	 * 
	 * @param intf
	 * @return
	 */
	public static boolean isInMyxVariable(IInterface intf) {
		assert intf != null;

		// needs to be "in" and have java interface
		if (intf.getDirection() != Direction.IN || intf.getFQJavaInterfaceName() == null) {
			return false;
		}

		if (intf.getTemplateType() == TemplateType.DELEGATE) {
			// "Delegate" has variables in the base class
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns true if the given brickInterface should be declared as "in" IMyx
	 * variable getter.
	 * 
	 * @param intf
	 * @return
	 */
	public static boolean isInMyxVariableGetter(IInterface intf) {
		assert intf != null;

		// needs to be "in" and have java interface
		if (intf.getDirection() != Direction.IN || intf.getFQJavaInterfaceName() == null) {
			return false;
		}

		if (intf.getTemplateType() == TemplateType.DELEGATE_WITH_GETTERS) {
			// "DelegateWitheGetters" has getters in the user class
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns true if the given brickInterface is implemented in the base class
	 * 
	 * @param intf
	 * @return
	 */
	public static boolean isImplementedInClass(IInterface intf) {
		assert intf != null;

		// needs to be "in" and have java interface
		if (intf.getFQJavaInterfaceName() == null || intf.getDirection() != Direction.IN) {
			return false;
		}

		return EnumSet.of(//
		        TemplateType.IMPLEMENTED_IN_BRICK, //
		        TemplateType.DELEGATE_TO_MYX_REGISTRY) //
		        .contains(intf.getTemplateType());
	}

	/**
	 * Returns the collection of "out multiple" {@link CodegenBrickInterface}s
	 * that should be declared as "out" IMyx variables.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getOutMultiMyxVariables(CodegenBrick brick) {
		assert brick != null;

		Collection<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : brick.getOutInterfaces()) {
			if (isOutMultiMyxVariable(intf)) {
				intfs.add(intf);
			}
		}
		return intfs;
	}

	/**
	 * Returns the collection of "out multiple" {@link CodegenBrickInterface}s
	 * that should be declared as "out" IMyx variables.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getOutMultiMyxVariablesGetter(CodegenBrick brick) {
		assert brick != null;

		Collection<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : brick.getOutInterfaces()) {
			if (isOutMultiMyxVariableGetter(intf)) {
				intfs.add(intf);
			}
		}
		return intfs;
	}

	/**
	 * Returns true if the given brickInterface is declared as a "out-mutli"
	 * variable
	 * 
	 * @param intf
	 * @return
	 */
	public static boolean isOutMultiMyxVariable(IInterface intf) {
		assert intf != null;
		if (intf.getFQJavaInterfaceName() == null //
		        || intf.getDirection() != Direction.OUT_MULTI) {
			return false;
		}

		if (EnumSet.of(//
		        TemplateType.IMPLEMENTED_IN_DELEGATE, //
		        TemplateType.DELEGATE) //
		        .contains(intf.getTemplateType())) {
			return true;
		}

		return false;
	}

	/**
	 * Returns true if the given brickInteface is declared as "out-multi" getter
	 * methods
	 * 
	 * @param intf
	 * @return
	 */
	public static boolean isOutMultiMyxVariableGetter(IInterface intf) {
		assert intf != null;
		if (intf.getFQJavaInterfaceName() != null //
		        && intf.getDirection() == Direction.OUT_MULTI) {
			if (EnumSet.of(//
			        TemplateType.IMPLEMENTED_IN_DELEGATE_WITH_GETTERS, //
			        TemplateType.DELEGATE_WITH_GETTERS) //
			        .contains(intf.getTemplateType())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the collection of "out single" {@link CodegenBrickInterface}s
	 * that should be declared as "out" IMyx variables.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getOutSingleMyxVariables(CodegenBrick brick) {
		assert brick != null;

		Collection<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : brick.getOutInterfaces()) {
			if (isOutSingleMyxVariable(intf)) {
				intfs.add(intf);
			}
		}
		return intfs;
	}

	/**
	 * Returns the collection of "out single" {@link CodegenBrickInterface}s
	 * that should be declared as "out" IMyx variables.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getOutSingleMyxVariablesGetter(CodegenBrick brick) {
		assert brick != null;

		Collection<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : brick.getOutInterfaces()) {
			if (isOutSingleMyxVariableGetter(intf)) {
				intfs.add(intf);
			}
		}
		return intfs;
	}

	/**
	 * Returns true if the given brickInterface is declared as a "out-single"
	 * variable
	 * 
	 * @param intf
	 * @return
	 */
	public static boolean isOutSingleMyxVariable(IInterface intf) {
		assert intf != null;
		if (intf.getFQJavaInterfaceName() == null //
		        || intf.getDirection() != Direction.OUT_SINGLE) {
			return false;
		}

		return EnumSet.of(//
		        TemplateType.IMPLEMENTED_IN_DELEGATE, //
		        TemplateType.DELEGATE) //
		        .contains(intf.getTemplateType());
	}

	/**
	 * Returns true if the given brickInterface is declared as "out-single"
	 * getter methods
	 * 
	 * @param intf
	 * @return
	 */
	public static boolean isOutSingleMyxVariableGetter(IInterface intf) {
		assert intf != null;

		if (intf.getFQJavaInterfaceName() == null //
		        || intf.getDirection() != Direction.OUT_SINGLE) {
			return false;
		}

		return EnumSet.of(//
		        TemplateType.IMPLEMENTED_IN_DELEGATE_WITH_GETTERS, //
		        TemplateType.DELEGATE_WITH_GETTERS) //
		        .contains(intf.getTemplateType());
	}

	/**
	 * Returns true if the given brickInterface is declared as a variable
	 * 
	 * @param intf
	 * @return
	 */
	public static boolean isMyxVariable(IInterface intf) {
		return isInMyxVariable(intf) || isOutMultiMyxVariable(intf) || isOutSingleMyxVariable(intf);
	}

	/**
	 * Returns true if the given brickInterface is declared as getter methods
	 * 
	 * @param intf
	 * @return
	 */
	public static boolean isMyxVariableGetter(IInterface intf) {
		return isInMyxVariableGetter(intf) || isOutMultiMyxVariableGetter(intf) || isOutSingleMyxVariableGetter(intf);
	}

	/**
	 * Returns the collection of {@link CodegenBrickInterface}s that should be
	 * declared as private "proxy" class
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<Collection<CodegenBrickInterface>> getProxyAdapters(CodegenBrick brick) {
		assert brick != null;

		Collection<CodegenBrickInterface> brickIntfs = brick.getInterfaces( //
		        Direction.OUT_MULTI);
		return CodegenBrick.getBrickInterfacesByInterface(brickIntfs, //
		        TemplateType.IMPLEMENTED_IN_DELEGATE, //
		        TemplateType.IMPLEMENTED_IN_DELEGATE_WITH_GETTERS);

	}

	/**
	 * Returns the collection of all of {@link CodegenBrickInterface}s that
	 * should be declared as variables.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getMyxVariables(CodegenBrick brick) {
		assert brick != null;

		Collection<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		intfs.addAll(getInMyxVariables(brick));
		intfs.addAll(getOutMultiMyxVariables(brick));
		intfs.addAll(getOutSingleMyxVariables(brick));
		return intfs;
	}

	/**
	 * Returns the collection of all of {@link CodegenBrickInterface}s that
	 * should be declared as variables getter.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getMyxVariablesGetter(CodegenBrick brick) {
		assert brick != null;

		Collection<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		intfs.addAll(getInMyxVariablesGetter(brick));
		intfs.addAll(getOutMultiMyxVariablesGetter(brick));
		intfs.addAll(getOutSingleMyxVariablesGetter(brick));
		return intfs;
	}

	/**
	 * Returns the collection of {@link CodegenBrickInterface}s that should be
	 * used in a private class "PreMyxLifecycleProcessor#init()".
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getPreMyxLifecycleInitVariables(CodegenBrick brick) {
		assert brick != null;

		Collection<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();

		for (CodegenBrickInterface intf : getMyxVariables(brick)) {
			if (intf.getConnectionTiming() == ConnectionTiming.CONNECT_BEFORE_INIT) {
				intfs.add(intf);
			}
		}
		return intfs;
	}
	
	/**
	 * Returns the collection of {@link CodegenBrickInterface}s that should be
	 * used in a private class "PreMyxLifecycleProcessor#init()".
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getPreMyxLifecycleInitVariablesGetter(CodegenBrick brick) {
		assert brick != null;

		Collection<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();

		for (CodegenBrickInterface intf : getMyxVariablesGetter(brick)) {
			if (intf.getConnectionTiming() == ConnectionTiming.CONNECT_BEFORE_INIT) {
				intfs.add(intf);
			}
		}
		return intfs;
	}

	/**
	 * Returns the collection of {@link CodegenBrickInterface}s that should be
	 * used in a private class "PreMyxLifecycleProcessor#begin()".
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getPreMyxLifecycleBeginVariables(CodegenBrick brick) {
		assert brick != null;

		Collection<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();

		for (CodegenBrickInterface intf : getMyxVariables(brick)) {
			switch (intf.getConnectionTiming()) {
			case CONNECT_BEFORE_BEGIN:
			case CONNECT_BEFORE_INIT:
				intfs.add(intf);
			case NONE:
			default:
			}
		}

		return intfs;
	}
	
	/**
	 * Returns the collection of {@link CodegenBrickInterface}s that should be
	 * used in a private class "PreMyxLifecycleProcessor#begin()".
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getPreMyxLifecycleBeginVariablesGetter(CodegenBrick brick) {
		assert brick != null;

		Collection<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();

		for (CodegenBrickInterface intf : getMyxVariablesGetter(brick)) {
			switch (intf.getConnectionTiming()) {
			case CONNECT_BEFORE_BEGIN:
			case CONNECT_BEFORE_INIT:
				intfs.add(intf);
			case NONE:
			default:
			}
		}

		return intfs;
	}

	/**
	 * //TODO: we don't need PostMyxLifecycle anymore Returns the collection of
	 * {@link CodegenBrickInterface}s that should be used in a private class
	 * "PostMyxLifecycleProcessor#init()".
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getPostMyxLifecycleInitVariables(CodegenBrick brick) {
		assert brick != null;

		Collection<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();

		for (CodegenBrickInterface intf : getMyxVariables(brick)) {
			if (intf.getConnectionTiming() == ConnectionTiming.CONNECT_BEFORE_BEGIN) {
				intfs.add(intf);
			}
		}
		return intfs;
	}

	/**
	 * The post fix of "proxy" class name
	 */
	public final static String PROXY_ADAPTER_POSTFIX = "MyxProxy";

	/**
	 * Returns the "proxy" class name of the given brickInterface
	 * 
	 * @param intf
	 * @return
	 */
	public static String getProxyAdapterName(IInterface intf) {
		assert intf != null;

		return TextUtil.getClassPart(intf.getFQJavaInterfaceName()) + PROXY_ADAPTER_POSTFIX;
	}

	/**
	 * Returns true if the given brickInterface should be declared as proxy
	 * variable
	 * 
	 * @param intf
	 * @return
	 */
	public static boolean isProxyVariable(IInterface intf) {
		assert intf != null;

		if (intf.getFQJavaInterfaceName() == null //
		        || intf.getDirection() != Direction.OUT_MULTI) {
			return false;
		}

		return intf.getTemplateType() == TemplateType.IMPLEMENTED_IN_DELEGATE;
	}

	/**
	 * Returns true if the given brickInterface should be declared as proxy
	 * getter methods
	 * 
	 * @param intf
	 * @return
	 */
	public static boolean isProxyVariableGetter(IInterface intf) {
		assert intf != null;

		if (intf.getFQJavaInterfaceName() == null //
		        || intf.getDirection() != Direction.OUT_MULTI) {
			return false;
		}

		return intf.getTemplateType() == TemplateType.IMPLEMENTED_IN_DELEGATE_WITH_GETTERS;
	}

	/**
	 * The post fix of a proxy variable name
	 */
	public final static String PROXY_VAR_POSTFIX = "MyxProxy";

	/**
	 * Returns the proxy variable name of the given brickInterface
	 * 
	 * @param intf
	 * @return
	 */
	public static String getProxyVariableName(IInterface intf) {
		assert intf != null;

		return intf.getName() + PROXY_VAR_POSTFIX;
	}

	/**
	 * Returns the collection of {@link CodegenBrickInterface}s declared as
	 * "proxy" variables.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getProxyVariables(CodegenBrick brick) {
		List<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : brick.getInterfaces( //
		        Direction.OUT_MULTI, //
		        TemplateType.IMPLEMENTED_IN_DELEGATE)) {
			if (intf.getFQJavaInterfaceName() != null) {
				intfs.add(intf);
			}
		}
		return intfs;
	}

	/**
	 * The prefix of "create proxy" method name
	 */
	public final static String CREATE_PROXY_METHOD_NAME_PREFIX = "create";

	/**
	 * The post fix of "create proxy" method name
	 */
	public final static String CREATE_PROXY_METHOD_NAME_POSTFIX = "MyxProxy";

	/**
	 * Returns the "create proxy" method name of the given brickInterface
	 * 
	 * @param intf
	 * @return
	 */
	public static String getCreateProxyMethodName(IInterface intf) {
		assert intf != null;

		return CREATE_PROXY_METHOD_NAME_PREFIX + TextUtil.upperFirstChar(intf.getName()) + CREATE_PROXY_METHOD_NAME_POSTFIX;
	}

	/**
	 * TODO: is this correct? Returns the collection of "out" brickInterfaces
	 * whose methods should be implemented in the class.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getImplMethodOut(CodegenBrick brick) {
		assert brick != null;

		List<CodegenBrickInterface> repSigs = new ArrayList<CodegenBrickInterface>();
		// out-brickInterfaces' implemented interfaces
		// (TemplateType.IMPLEMENTED_IN_BRICK)
		for (Collection<CodegenBrickInterface> intfs : CodegenBrick.getBrickInterfacesByInterface(brick.getOutInterfaces(), //
		        TemplateType.IMPLEMENTED_IN_BRICK)) {
			Iterator<CodegenBrickInterface> it = intfs.iterator();
			CodegenBrickInterface repSig = it.next();
			if (repSig.hasNonVoidMethods()) {
				repSigs.add(repSig);
			}
		}

		return repSigs;

	}

	/**
	 * Returns the collection of constructors
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<MethodContainer> getConstructors(CodegenBrick brick) {
		return brick.getParentConstructors();
	}

	/**
	 * Returns the collection of brickInterfaces that should be used in the
	 * getServiceObject() method.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getGetServiceObjectVariables(CodegenBrick brick) {
		assert brick != null;

		Collection<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();

		// in variables brickInterfaces or
		// in implementedInBrick brickInterfaces
		for (CodegenBrickInterface intf : brick.getInInterfaces()) {
			if (isInMyxVariable(intf) || isInMyxVariableGetter(intf) || EnumSet.of( //
			        TemplateType.IMPLEMENTED_IN_BRICK, //
			        TemplateType.DELEGATE_TO_MYX_REGISTRY).contains(intf.getTemplateType())) {
				intfs.add(intf);
			}
		}

		return intfs;
	}

	public static boolean shouldCreateMyxRegistry(CodegenBrick brick) {
		for (CodegenBrickInterface intf : brick.getInInterfaces()) {
			if (EnumSet.of( //
			        TemplateType.DELEGATE_TO_MYX_REGISTRY).contains(intf.getTemplateType())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the collection of representative in-brickInterfaces whose methods
	 * should be implemented in the class and delegated to the myx registry. If
	 * there are more than one brickInterface whose java interface is the same,
	 * the first found one was returned.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getImplMethodDelegateToMyxRegistry(CodegenBrick brick) {
		List<CodegenBrickInterface> repSigs = new ArrayList<CodegenBrickInterface>();
		// in-brickInterfaces' implemented interfaces (TemplateType.DELEGATE_TO_MYX_REGISTRY)
		for (Collection<CodegenBrickInterface> intfs : CodegenBrick.getBrickInterfacesByInterface(brick.getInInterfaces(),
		        TemplateType.DELEGATE_TO_MYX_REGISTRY)) {
			Iterator<CodegenBrickInterface> it = intfs.iterator();
			CodegenBrickInterface repSig = it.next();
			repSigs.add(repSig);
		}

		return repSigs;
	}

}
