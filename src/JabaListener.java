// Generated from Jaba.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JabaParser}.
 */
public interface JabaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JabaParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(JabaParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(JabaParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPackageDeclaration(JabaParser.PackageDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPackageDeclaration(JabaParser.PackageDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterImportDeclaration(JabaParser.ImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitImportDeclaration(JabaParser.ImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclaration(JabaParser.TypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclaration(JabaParser.TypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#modifier}.
	 * @param ctx the parse tree
	 */
	void enterModifier(JabaParser.ModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#modifier}.
	 * @param ctx the parse tree
	 */
	void exitModifier(JabaParser.ModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#classOrInterfaceModifier}.
	 * @param ctx the parse tree
	 */
	void enterClassOrInterfaceModifier(JabaParser.ClassOrInterfaceModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#classOrInterfaceModifier}.
	 * @param ctx the parse tree
	 */
	void exitClassOrInterfaceModifier(JabaParser.ClassOrInterfaceModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void enterVariableModifier(JabaParser.VariableModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void exitVariableModifier(JabaParser.VariableModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(JabaParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(JabaParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameters(JabaParser.TypeParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameters(JabaParser.TypeParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameter(JabaParser.TypeParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameter(JabaParser.TypeParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#typeBound}.
	 * @param ctx the parse tree
	 */
	void enterTypeBound(JabaParser.TypeBoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#typeBound}.
	 * @param ctx the parse tree
	 */
	void exitTypeBound(JabaParser.TypeBoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#enumDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterEnumDeclaration(JabaParser.EnumDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#enumDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitEnumDeclaration(JabaParser.EnumDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#enumConstants}.
	 * @param ctx the parse tree
	 */
	void enterEnumConstants(JabaParser.EnumConstantsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#enumConstants}.
	 * @param ctx the parse tree
	 */
	void exitEnumConstants(JabaParser.EnumConstantsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#enumConstant}.
	 * @param ctx the parse tree
	 */
	void enterEnumConstant(JabaParser.EnumConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#enumConstant}.
	 * @param ctx the parse tree
	 */
	void exitEnumConstant(JabaParser.EnumConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#enumBodyDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterEnumBodyDeclarations(JabaParser.EnumBodyDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#enumBodyDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitEnumBodyDeclarations(JabaParser.EnumBodyDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceDeclaration(JabaParser.InterfaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceDeclaration(JabaParser.InterfaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#typeList}.
	 * @param ctx the parse tree
	 */
	void enterTypeList(JabaParser.TypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#typeList}.
	 * @param ctx the parse tree
	 */
	void exitTypeList(JabaParser.TypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(JabaParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(JabaParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBody(JabaParser.InterfaceBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBody(JabaParser.InterfaceBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassBodyDeclaration(JabaParser.ClassBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassBodyDeclaration(JabaParser.ClassBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclaration(JabaParser.MemberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclaration(JabaParser.MemberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(JabaParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(JabaParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#genericMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGenericMethodDeclaration(JabaParser.GenericMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#genericMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGenericMethodDeclaration(JabaParser.GenericMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclaration(JabaParser.ConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclaration(JabaParser.ConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#genericConstructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGenericConstructorDeclaration(JabaParser.GenericConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#genericConstructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGenericConstructorDeclaration(JabaParser.GenericConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclaration(JabaParser.FieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclaration(JabaParser.FieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#interfaceBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBodyDeclaration(JabaParser.InterfaceBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#interfaceBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBodyDeclaration(JabaParser.InterfaceBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#interfaceMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMemberDeclaration(JabaParser.InterfaceMemberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#interfaceMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMemberDeclaration(JabaParser.InterfaceMemberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#constDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstDeclaration(JabaParser.ConstDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#constDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstDeclaration(JabaParser.ConstDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#constantDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclarator(JabaParser.ConstantDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#constantDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclarator(JabaParser.ConstantDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#interfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMethodDeclaration(JabaParser.InterfaceMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#interfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMethodDeclaration(JabaParser.InterfaceMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#genericInterfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGenericInterfaceMethodDeclaration(JabaParser.GenericInterfaceMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#genericInterfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGenericInterfaceMethodDeclaration(JabaParser.GenericInterfaceMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#variableDeclarators}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarators(JabaParser.VariableDeclaratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#variableDeclarators}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarators(JabaParser.VariableDeclaratorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarator(JabaParser.VariableDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarator(JabaParser.VariableDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorId(JabaParser.VariableDeclaratorIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorId(JabaParser.VariableDeclaratorIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void enterVariableInitializer(JabaParser.VariableInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void exitVariableInitializer(JabaParser.VariableInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializer(JabaParser.ArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializer(JabaParser.ArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#enumConstantName}.
	 * @param ctx the parse tree
	 */
	void enterEnumConstantName(JabaParser.EnumConstantNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#enumConstantName}.
	 * @param ctx the parse tree
	 */
	void exitEnumConstantName(JabaParser.EnumConstantNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#typeType}.
	 * @param ctx the parse tree
	 */
	void enterTypeType(JabaParser.TypeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#typeType}.
	 * @param ctx the parse tree
	 */
	void exitTypeType(JabaParser.TypeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterClassOrInterfaceType(JabaParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitClassOrInterfaceType(JabaParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(JabaParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(JabaParser.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void enterTypeArguments(JabaParser.TypeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void exitTypeArguments(JabaParser.TypeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgument(JabaParser.TypeArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgument(JabaParser.TypeArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#qualifiedNameList}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedNameList(JabaParser.QualifiedNameListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#qualifiedNameList}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedNameList(JabaParser.QualifiedNameListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(JabaParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(JabaParser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterList(JabaParser.FormalParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterList(JabaParser.FormalParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(JabaParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(JabaParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#lastFormalParameter}.
	 * @param ctx the parse tree
	 */
	void enterLastFormalParameter(JabaParser.LastFormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#lastFormalParameter}.
	 * @param ctx the parse tree
	 */
	void exitLastFormalParameter(JabaParser.LastFormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBody(JabaParser.MethodBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBody(JabaParser.MethodBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#constructorBody}.
	 * @param ctx the parse tree
	 */
	void enterConstructorBody(JabaParser.ConstructorBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#constructorBody}.
	 * @param ctx the parse tree
	 */
	void exitConstructorBody(JabaParser.ConstructorBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedName(JabaParser.QualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedName(JabaParser.QualifiedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(JabaParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(JabaParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(JabaParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(JabaParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#annotationName}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationName(JabaParser.AnnotationNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#annotationName}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationName(JabaParser.AnnotationNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#elementValuePairs}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePairs(JabaParser.ElementValuePairsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#elementValuePairs}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePairs(JabaParser.ElementValuePairsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePair(JabaParser.ElementValuePairContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePair(JabaParser.ElementValuePairContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void enterElementValue(JabaParser.ElementValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void exitElementValue(JabaParser.ElementValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#elementValueArrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterElementValueArrayInitializer(JabaParser.ElementValueArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#elementValueArrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitElementValueArrayInitializer(JabaParser.ElementValueArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeDeclaration(JabaParser.AnnotationTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeDeclaration(JabaParser.AnnotationTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#annotationTypeBody}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeBody(JabaParser.AnnotationTypeBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#annotationTypeBody}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeBody(JabaParser.AnnotationTypeBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#annotationTypeElementDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeElementDeclaration(JabaParser.AnnotationTypeElementDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#annotationTypeElementDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeElementDeclaration(JabaParser.AnnotationTypeElementDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#annotationTypeElementRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeElementRest(JabaParser.AnnotationTypeElementRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#annotationTypeElementRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeElementRest(JabaParser.AnnotationTypeElementRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#annotationMethodOrConstantRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationMethodOrConstantRest(JabaParser.AnnotationMethodOrConstantRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#annotationMethodOrConstantRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationMethodOrConstantRest(JabaParser.AnnotationMethodOrConstantRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#annotationMethodRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationMethodRest(JabaParser.AnnotationMethodRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#annotationMethodRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationMethodRest(JabaParser.AnnotationMethodRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#annotationConstantRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationConstantRest(JabaParser.AnnotationConstantRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#annotationConstantRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationConstantRest(JabaParser.AnnotationConstantRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultValue(JabaParser.DefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultValue(JabaParser.DefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(JabaParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(JabaParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(JabaParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(JabaParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclarationStatement(JabaParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclarationStatement(JabaParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclaration(JabaParser.LocalVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclaration(JabaParser.LocalVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(JabaParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(JabaParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#catchClause}.
	 * @param ctx the parse tree
	 */
	void enterCatchClause(JabaParser.CatchClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#catchClause}.
	 * @param ctx the parse tree
	 */
	void exitCatchClause(JabaParser.CatchClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#catchType}.
	 * @param ctx the parse tree
	 */
	void enterCatchType(JabaParser.CatchTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#catchType}.
	 * @param ctx the parse tree
	 */
	void exitCatchType(JabaParser.CatchTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#finallyBlock}.
	 * @param ctx the parse tree
	 */
	void enterFinallyBlock(JabaParser.FinallyBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#finallyBlock}.
	 * @param ctx the parse tree
	 */
	void exitFinallyBlock(JabaParser.FinallyBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#resourceSpecification}.
	 * @param ctx the parse tree
	 */
	void enterResourceSpecification(JabaParser.ResourceSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#resourceSpecification}.
	 * @param ctx the parse tree
	 */
	void exitResourceSpecification(JabaParser.ResourceSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#resources}.
	 * @param ctx the parse tree
	 */
	void enterResources(JabaParser.ResourcesContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#resources}.
	 * @param ctx the parse tree
	 */
	void exitResources(JabaParser.ResourcesContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#resource}.
	 * @param ctx the parse tree
	 */
	void enterResource(JabaParser.ResourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#resource}.
	 * @param ctx the parse tree
	 */
	void exitResource(JabaParser.ResourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlockStatementGroup(JabaParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlockStatementGroup(JabaParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void enterSwitchLabel(JabaParser.SwitchLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void exitSwitchLabel(JabaParser.SwitchLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#forControl}.
	 * @param ctx the parse tree
	 */
	void enterForControl(JabaParser.ForControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#forControl}.
	 * @param ctx the parse tree
	 */
	void exitForControl(JabaParser.ForControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(JabaParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(JabaParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#enhancedForControl}.
	 * @param ctx the parse tree
	 */
	void enterEnhancedForControl(JabaParser.EnhancedForControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#enhancedForControl}.
	 * @param ctx the parse tree
	 */
	void exitEnhancedForControl(JabaParser.EnhancedForControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void enterForUpdate(JabaParser.ForUpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void exitForUpdate(JabaParser.ForUpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void enterParExpression(JabaParser.ParExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void exitParExpression(JabaParser.ParExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(JabaParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(JabaParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#statementExpression}.
	 * @param ctx the parse tree
	 */
	void enterStatementExpression(JabaParser.StatementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#statementExpression}.
	 * @param ctx the parse tree
	 */
	void exitStatementExpression(JabaParser.StatementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(JabaParser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(JabaParser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(JabaParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(JabaParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(JabaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(JabaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(JabaParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(JabaParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterCreator(JabaParser.CreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitCreator(JabaParser.CreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#createdName}.
	 * @param ctx the parse tree
	 */
	void enterCreatedName(JabaParser.CreatedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#createdName}.
	 * @param ctx the parse tree
	 */
	void exitCreatedName(JabaParser.CreatedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#innerCreator}.
	 * @param ctx the parse tree
	 */
	void enterInnerCreator(JabaParser.InnerCreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#innerCreator}.
	 * @param ctx the parse tree
	 */
	void exitInnerCreator(JabaParser.InnerCreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#arrayCreatorRest}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreatorRest(JabaParser.ArrayCreatorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#arrayCreatorRest}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreatorRest(JabaParser.ArrayCreatorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#classCreatorRest}.
	 * @param ctx the parse tree
	 */
	void enterClassCreatorRest(JabaParser.ClassCreatorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#classCreatorRest}.
	 * @param ctx the parse tree
	 */
	void exitClassCreatorRest(JabaParser.ClassCreatorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#explicitGenericInvocation}.
	 * @param ctx the parse tree
	 */
	void enterExplicitGenericInvocation(JabaParser.ExplicitGenericInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#explicitGenericInvocation}.
	 * @param ctx the parse tree
	 */
	void exitExplicitGenericInvocation(JabaParser.ExplicitGenericInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#nonWildcardTypeArguments}.
	 * @param ctx the parse tree
	 */
	void enterNonWildcardTypeArguments(JabaParser.NonWildcardTypeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#nonWildcardTypeArguments}.
	 * @param ctx the parse tree
	 */
	void exitNonWildcardTypeArguments(JabaParser.NonWildcardTypeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#typeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgumentsOrDiamond(JabaParser.TypeArgumentsOrDiamondContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#typeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgumentsOrDiamond(JabaParser.TypeArgumentsOrDiamondContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#nonWildcardTypeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void enterNonWildcardTypeArgumentsOrDiamond(JabaParser.NonWildcardTypeArgumentsOrDiamondContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#nonWildcardTypeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void exitNonWildcardTypeArgumentsOrDiamond(JabaParser.NonWildcardTypeArgumentsOrDiamondContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#superSuffix}.
	 * @param ctx the parse tree
	 */
	void enterSuperSuffix(JabaParser.SuperSuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#superSuffix}.
	 * @param ctx the parse tree
	 */
	void exitSuperSuffix(JabaParser.SuperSuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#explicitGenericInvocationSuffix}.
	 * @param ctx the parse tree
	 */
	void enterExplicitGenericInvocationSuffix(JabaParser.ExplicitGenericInvocationSuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#explicitGenericInvocationSuffix}.
	 * @param ctx the parse tree
	 */
	void exitExplicitGenericInvocationSuffix(JabaParser.ExplicitGenericInvocationSuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link JabaParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(JabaParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JabaParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(JabaParser.ArgumentsContext ctx);
}