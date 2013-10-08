package edu.uci.isr.myx2.eclipse.codegen.codegen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import edu.uci.isr.myx2.eclipse.codegen.brick.CodegenBrick;
import edu.uci.isr.myx2.eclipse.codegen.brick.CodegenBrickInterface;
import edu.uci.isr.myx2.eclipse.codegen.brick.MethodContainer;
import edu.uci.isr.myx2.eclipse.extension.ConnectionTiming;
import edu.uci.isr.myx2.eclipse.extension.Direction;
import edu.uci.isr.myx2.eclipse.extension.IInterface;
import edu.uci.isr.myx2.eclipse.extension.TemplateType;

/**
 * The utility to generate an myx user class. This class is supposed to be used
 * in "MyxComp.java.jet" template. Two files will be generated by myx code
 * generation application:
 * <ul>
 * <li>one is "Myxbase" class, the base class that is to encapsulate the
 * complexity of the myx framework,</li>
 * <li>and the other is "MyxComp" class, the class where the user is going to
 * implement the services.</li>
 * </ul>
 * This utility class is to produce "MyxComp" java file. See
 * {@link BaseTemplateUtil}.
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class CompTemplateUtil {

	/**
	 * Returns java comment like string. Adds '*' after each line separator.
	 * 
	 * @param str
	 * @param tab the number of tabs
	 * @return 
	 */
	public static String getComment(String str, int tab) {
		return BaseTemplateUtil.getComment(str, tab);
	}
	
	/**
	 * Returns a collection of fully qualified class names to be declared as
	 * "import".
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<String> getImports(CodegenBrick brick) {
		Collection<String> javaImports = new HashSet<String>();

		////////////////
		// in
		javaImports.addAll(brick.getInterfaceFQClassNames(Direction.IN, TemplateType.DELEGATE_WITH_GETTERS));
		// methods
		javaImports.addAll(brick.getVoidMethodArgsFQClassNames(Direction.IN, TemplateType.IMPLEMENTED_IN_BRICK));
		javaImports.addAll(brick.getNonVoidMethodArgsFQClassNames(Direction.IN, TemplateType.IMPLEMENTED_IN_BRICK));

		////////////////
		// out-single
		javaImports.addAll(brick.getInterfaceFQClassNames(Direction.OUT_SINGLE, 
				TemplateType.DELEGATE_WITH_GETTERS, 
				TemplateType.IMPLEMENTED_IN_DELEGATE_WITH_GETTERS, 
				TemplateType.NONE));
		//non-void methods
		javaImports.addAll(brick.getNonVoidMethodArgsFQClassNames(Direction.OUT_SINGLE, 
				TemplateType.IMPLEMENTED_IN_BRICK));

		////////////////
		// out-multi
		javaImports.addAll(brick.getInterfaceFQClassNames(Direction.OUT_MULTI, 
				TemplateType.DELEGATE_WITH_GETTERS,
				TemplateType.IMPLEMENTED_IN_BRICK,
				TemplateType.IMPLEMENTED_IN_DELEGATE,
		        TemplateType.IMPLEMENTED_IN_DELEGATE_WITH_GETTERS,// only for out-multi
		        TemplateType.NONE));
		// out-multi brickInterface variable needs Collection
		if (!CompTemplateUtil.getOutMultiMyxVariables(brick).isEmpty()) {
			javaImports.add("java.util.Collection");
			javaImports.add("java.util.ArrayList");
		}
		//non-void methods
		javaImports.addAll(brick.getNonVoidMethodArgsFQClassNames(Direction.OUT_MULTI,
				TemplateType.IMPLEMENTED_IN_BRICK, 
				TemplateType.IMPLEMENTED_IN_DELEGATE, 
				TemplateType.IMPLEMENTED_IN_DELEGATE_WITH_GETTERS)); 
		
		////////////////
		// constructors
		if(brick.hasParentBrick()) {
			javaImports.addAll(brick.getFQParentConstructorParams());
		}
		
		///////////////
		// base class
		javaImports.add(brick.getFQBaseClassName());
		
		return javaImports;
	}
	
	/**
	 * Returns the string expression of type of the given brickInterface variable
	 * @param intf
	 * @return
	 */
	public static String getVariableType(IInterface intf) {
		return BaseTemplateUtil.getVariableType(intf);
	}
	
	/**
	 * Returns the string expression of type of the given brickInterface proxy variable
	 * @param intf
	 * @return
	 */
	public static String getProxyVariableType(IInterface intf) {
		return BaseTemplateUtil.getProxyVariableType(intf);
	}

	/**
	 * Returns the collection of {@link CodegenBrickInterface}s that should be declared as "in"
	 * IMyx variables.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getInMyxVariables(CodegenBrick brick) {

		return BaseTemplateUtil.getInMyxVariablesGetter(brick);
	}

	/**
	 * Returns the collection of "out single" {@link CodegenBrickInterface}s that should be
	 * declared as "out" IMyx variables.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getOutSingleMyxVariables(CodegenBrick brick) {
		return BaseTemplateUtil.getOutSingleMyxVariablesGetter(brick);
	}

	/**
	 * Returns the collection of "out multiple" {@link CodegenBrickInterface}s that should be
	 * declared as "out" IMyx variables.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getOutMultiMyxVariables(CodegenBrick brick) {
		return BaseTemplateUtil.getOutMultiMyxVariablesGetter(brick);
	}

	/**
	 * Returns the collection of all of {@link CodegenBrickInterface}s that should be declared
	 * as variables.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getMyxVariables(CodegenBrick brick) {
		return BaseTemplateUtil.getMyxVariablesGetter(brick);
	}

	/**
	 * Returns a getter method name
	 * 
	 * @param name
	 * @return
	 */
	public static String getGetterMethodName(String name) {
		assert name != null;

		return BaseTemplateUtil.getGetterMethodName(name);

	}

	/**
	 * Returns a setter method name
	 * 
	 * @param name
	 * @return
	 */
	public static String getSetterMethodName(String name) {
		assert name != null;

		return BaseTemplateUtil.getSetterMethodName(name);

	}

	/**
	 * Returns true if the given brickInterface should be declared as a proxy variable
	 * 
	 * @param intf
	 * @return
	 */
	public static boolean isProxyVariable(IInterface intf) {
		if(intf.getFQJavaInterfaceName() == null || intf.getDirection() != Direction.OUT_MULTI) {
			return false;
		}

		return (intf.getTemplateType() == TemplateType.IMPLEMENTED_IN_DELEGATE_WITH_GETTERS);
	}
	
	/**
	 * Returns true if the given brickInterface should be declared as a proxy variable in the base class
	 * @param intf
	 * @return
	 */
	public static boolean isProxyVariableInBaseClass(IInterface intf) {
		return BaseTemplateUtil.isProxyVariable(intf);
	}

	
	/**
	 * Returns true if the given brickInterface is declared as a variable in this class
	 * @param intf
	 * @return
	 */
	public static boolean isMyxVariable(IInterface intf) {
		return BaseTemplateUtil.isMyxVariableGetter(intf);
	}
	
	/**
	 * Returns true if the given brickInterface is declared as a variable in the base class 
	 * @param intf
	 * @return
	 */
	public static boolean isMyxVariablInBaseClass(IInterface intf) {
		return BaseTemplateUtil.isMyxVariable(intf); 
	}
	
	/**
	 * Returns true if the given brickInterface is implemented in the base class
	 * @param intf
	 * @return
	 */
	public static boolean isImplementedInClass(IInterface intf) {
		
		return BaseTemplateUtil.isImplementedInClass(intf);
	}
	
	/**
	 * Returns the collection of {@link CodegenBrickInterface}s that must be assigned
	 * values in the constructor.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getConstructorVariables(CodegenBrick brick) {
		//brickInterfaces that must be assigned values in the constructor
		// "in" && "connect before init" except "implemented in brick"
		List<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : brick.getInterfaces(ConnectionTiming.CONNECT_BEFORE_INIT)) {
			if (intf.getDirection() != Direction.IN) {
				//only in brickInterfaces need to be assigned values.
				continue;
			}
			if ((intf.getTemplateType() == TemplateType.DELEGATE 
					|| intf.getTemplateType() == TemplateType.DELEGATE_WITH_GETTERS) 
					&& BaseTemplateUtil.isMyxVariableGetter(intf)) {
				// we hava variables for the brickInterface (delegate), but
				// not for the brickInterface (implementedInTemplateBrick)
				intfs.add(intf);
			}
		}
		return intfs;
	}

	/**
	 * Returns the collection of {@link CodegenBrickInterface}s available in init().
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getInitAvailableVariables(CodegenBrick brick) {
		// brickInterfaces that will be assigned values before init() method is called.
		// all the out brickInterfaces 
		List<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : brick.getInterfaces(ConnectionTiming.CONNECT_BEFORE_INIT)) {
			if (intf.getDirection() == Direction.IN) {
				//in brickInterfaces must be assigned values before init() by this class.
				continue;
			}
			if (BaseTemplateUtil.isMyxVariableGetter(intf) ||
					BaseTemplateUtil.isMyxVariable(intf)) {
				intfs.add(intf);
			}
		}
		return intfs;
	}

	/**
	 * Returns the collection of {@link CodegenBrickInterface}s that must be assigned
	 * values in init().
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getInitAssignVariables(CodegenBrick brick) {
		// brickInterfaces that must be assigned values before init() method exits.
		// connectAfterInit (for the server)
		List<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : brick.getInterfaces(ConnectionTiming.CONNECT_BEFORE_BEGIN)) {
			if (intf.getDirection() != Direction.IN) {
				//only in brickInterfaces need to be assigned values.
				continue;
			}
			if (intf.getTemplateType() == TemplateType.DELEGATE
					|| intf.getTemplateType() == TemplateType.DELEGATE_WITH_GETTERS) {
				intfs.add(intf);
			}
		}
		return intfs;
	}

	/**
	 * Returns the "proxy" class name of the given brickInterface
	 * 
	 * @param intf
	 * @return
	 */
	public static String getProxyAdapterName(IInterface intf) {
		return BaseTemplateUtil.getProxyAdapterName(intf);
	}

	/**
	 * Returns the collection of {@link CodegenBrickInterface}s declared as "proxy"
	 * variables.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getProxyVariables(CodegenBrick brick) {
		List<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : brick.getInterfaces(Direction.OUT_MULTI, 
				TemplateType.IMPLEMENTED_IN_DELEGATE_WITH_GETTERS)) {
			if (intf.getFQJavaInterfaceName() != null) {
				intfs.add(intf);
			}
		}
		return intfs;
	}

	/**
	 * Returns the proxy variable name of the given brickInterface.
	 * 
	 * @param intf
	 * @return
	 */
	public static String getProxyVariableName(IInterface intf) {
		return BaseTemplateUtil.getProxyVariableName(intf);
	}

	/**
	 * Returns the create***Proxy method name of the given brickInterface
	 * 
	 * @param intf
	 * @return
	 */
	public static String getCreateProxyMethodName(IInterface intf) {
		return BaseTemplateUtil.getCreateProxyMethodName(intf);
	}
	
	/**
	 * Returns the collection of {@link CodegenBrickInterface}s that must declare
	 * createProxy method
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getCreateProxyMethodVariables(CodegenBrick brick) {
		List<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		intfs.addAll(getProxyVariables(brick));
		intfs.addAll(BaseTemplateUtil.getProxyVariables(brick));
		return intfs;
	}

	/**
	 * Returns the collection of representative in-brickInterfaces whose methods
	 * should be implemented in the class. If there are more than one brickInterface
	 * whose java interface is the same, the first found one was returned.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getImplMethodIn(CodegenBrick brick) {
		List<CodegenBrickInterface> repSigs = new ArrayList<CodegenBrickInterface>();
		// in-brickInterfaces' implemented interfaces (TemplateType.IMPLEMENTED_IN_BRICK)
		for (Collection<CodegenBrickInterface> intfs : CodegenBrick.getBrickInterfacesByInterface(brick.getInInterfaces(), TemplateType.IMPLEMENTED_IN_BRICK)) {
			Iterator<CodegenBrickInterface> it = intfs.iterator();
			CodegenBrickInterface repSig = it.next();
			repSigs.add(repSig);
		}

		return repSigs;
	}

	/**
	 * Returns the collection of representative out-brickInterfaces whose methods
	 * should be implemented in the class. If there are more than one brickInterface
	 * whose java interface is the same, the first found one was returned.
	 * 
	 * @param brick
	 * @return
	 */
	public static Collection<CodegenBrickInterface> getImplMethodOut(CodegenBrick brick) {
		List<CodegenBrickInterface> repSigs = new ArrayList<CodegenBrickInterface>();
		// out-brickInterfaces' implemented interfaces (TemplateType.IMPLEMENTED_IN_BRICK)
		for (Collection<CodegenBrickInterface> intfs : CodegenBrick.getBrickInterfacesByInterface(brick.getOutInterfaces(), TemplateType.IMPLEMENTED_IN_BRICK)) {
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
	 * @param brick
	 * @return
	 */
	public static Collection<MethodContainer> getConstructors(CodegenBrick brick) {
		return BaseTemplateUtil.getConstructors(brick);
	}
}
