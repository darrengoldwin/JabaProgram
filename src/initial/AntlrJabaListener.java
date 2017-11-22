package initial;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class AntlrJabaListener extends JabaBaseListener {

    @Override
    public void enterCompilationUnit(JabaParser.CompilationUnitContext ctx) {
        super.enterCompilationUnit(ctx);
    System.out.println("enterCompilationUnit");}
    
    @Override
    public void exitCompilationUnit(JabaParser.CompilationUnitContext ctx) {
        super.exitCompilationUnit(ctx);
    System.out.println("exitCompilationUnit");}

    @Override
    public void enterPackageDeclaration(JabaParser.PackageDeclarationContext ctx) {
        super.enterPackageDeclaration(ctx);
    System.out.println("enterPackageDeclaration");}

    @Override
    public void exitPackageDeclaration(JabaParser.PackageDeclarationContext ctx) {
        super.exitPackageDeclaration(ctx);
    System.out.println("exitPackageDeclaration");}

    @Override
    public void enterImportDeclaration(JabaParser.ImportDeclarationContext ctx) {
        super.enterImportDeclaration(ctx);
    System.out.println("enterImportDeclaration");}

    @Override
    public void exitImportDeclaration(JabaParser.ImportDeclarationContext ctx) {
        super.exitImportDeclaration(ctx);
    System.out.println("exitImportDeclaration");}

    @Override
    public void enterTypeDeclaration(JabaParser.TypeDeclarationContext ctx) {
        super.enterTypeDeclaration(ctx);
    System.out.println("enterTypeDeclaration");}

    @Override
    public void exitTypeDeclaration(JabaParser.TypeDeclarationContext ctx) {
        super.exitTypeDeclaration(ctx);
    System.out.println("exitTypeDeclaration");}

    @Override
    public void enterModifier(JabaParser.ModifierContext ctx) {
        super.enterModifier(ctx);
    System.out.println("enterModifier");}

    @Override
    public void exitModifier(JabaParser.ModifierContext ctx) {
        super.exitModifier(ctx);
    System.out.println("exitModifier");}

    @Override
    public void enterClassOrInterfaceModifier(JabaParser.ClassOrInterfaceModifierContext ctx) {
        super.enterClassOrInterfaceModifier(ctx);
    System.out.println("enterClassOrInterfaceModifier");}

    @Override
    public void exitClassOrInterfaceModifier(JabaParser.ClassOrInterfaceModifierContext ctx) {
        super.exitClassOrInterfaceModifier(ctx);
    System.out.println("exitClassOrInterfaceModifier");}

    @Override
    public void enterVariableModifier(JabaParser.VariableModifierContext ctx) {
        super.enterVariableModifier(ctx);
    System.out.println("enterVariableModifier");}

    @Override
    public void exitVariableModifier(JabaParser.VariableModifierContext ctx) {
        super.exitVariableModifier(ctx);
    System.out.println("exitVariableModifier");}

    @Override
    public void enterClassDeclaration(JabaParser.ClassDeclarationContext ctx) {
        super.enterClassDeclaration(ctx);
    System.out.println("enterClassDeclaration");}

    @Override
    public void exitClassDeclaration(JabaParser.ClassDeclarationContext ctx) {
        super.exitClassDeclaration(ctx);
    System.out.println("exitClassDeclaration");}

    @Override
    public void enterTypeParameters(JabaParser.TypeParametersContext ctx) {
        super.enterTypeParameters(ctx);
    System.out.println("enterTypeParameters");}

    @Override
    public void exitTypeParameters(JabaParser.TypeParametersContext ctx) {
        super.exitTypeParameters(ctx);
    System.out.println("exitTypeParameters");}

    @Override
    public void enterTypeParameter(JabaParser.TypeParameterContext ctx) {
        super.enterTypeParameter(ctx);
    System.out.println("enterTypeParameter");}

    @Override
    public void exitTypeParameter(JabaParser.TypeParameterContext ctx) {
        super.exitTypeParameter(ctx);
    System.out.println("exitTypeParameter");}

    @Override
    public void enterTypeBound(JabaParser.TypeBoundContext ctx) {
        super.enterTypeBound(ctx);
    System.out.println("enterTypeBound");}

    @Override
    public void exitTypeBound(JabaParser.TypeBoundContext ctx) {
        super.exitTypeBound(ctx);
    System.out.println("exitTypeBound");}

    @Override
    public void enterEnumDeclaration(JabaParser.EnumDeclarationContext ctx) {
        super.enterEnumDeclaration(ctx);
    System.out.println("enterEnumDeclaration");}

    @Override
    public void exitEnumDeclaration(JabaParser.EnumDeclarationContext ctx) {
        super.exitEnumDeclaration(ctx);
    System.out.println("exitEnumDeclaration");}

    @Override
    public void enterEnumConstants(JabaParser.EnumConstantsContext ctx) {
        super.enterEnumConstants(ctx);
    System.out.println("enterEnumConstants");}

    @Override
    public void exitEnumConstants(JabaParser.EnumConstantsContext ctx) {
        super.exitEnumConstants(ctx);
    System.out.println("exitEnumConstants");}

    @Override
    public void enterEnumConstant(JabaParser.EnumConstantContext ctx) {
        super.enterEnumConstant(ctx);
    System.out.println("enterEnumConstant");}

    @Override
    public void exitEnumConstant(JabaParser.EnumConstantContext ctx) {
        super.exitEnumConstant(ctx);
    System.out.println("exitEnumConstant");}

    @Override
    public void enterEnumBodyDeclarations(JabaParser.EnumBodyDeclarationsContext ctx) {
        super.enterEnumBodyDeclarations(ctx);
    System.out.println("enterEnumBodyDeclarations");}

    @Override
    public void exitEnumBodyDeclarations(JabaParser.EnumBodyDeclarationsContext ctx) {
        super.exitEnumBodyDeclarations(ctx);
    System.out.println("exitEnumBodyDeclarations");}

    @Override
    public void enterInterfaceDeclaration(JabaParser.InterfaceDeclarationContext ctx) {
        super.enterInterfaceDeclaration(ctx);
    System.out.println("enterInterfaceDeclaration");}

    @Override
    public void exitInterfaceDeclaration(JabaParser.InterfaceDeclarationContext ctx) {
        super.exitInterfaceDeclaration(ctx);
    System.out.println("exitInterfaceDeclaration");}

    @Override
    public void enterTypeList(JabaParser.TypeListContext ctx) {
        super.enterTypeList(ctx);
    System.out.println("enterTypeList");}

    @Override
    public void exitTypeList(JabaParser.TypeListContext ctx) {
        super.exitTypeList(ctx);
    System.out.println("exitTypeList");}

    @Override
    public void enterClassBody(JabaParser.ClassBodyContext ctx) {
        super.enterClassBody(ctx);
    System.out.println("enterClassBody");}

    @Override
    public void exitClassBody(JabaParser.ClassBodyContext ctx) {
        super.exitClassBody(ctx);
    System.out.println("exitClassBody");}

    @Override
    public void enterInterfaceBody(JabaParser.InterfaceBodyContext ctx) {
        super.enterInterfaceBody(ctx);
    System.out.println("enterInterfaceBody");}

    @Override public void exitInterfaceBody(JabaParser.InterfaceBodyContext ctx) { System.out.println("exitInterfaceBody");}
    
    @Override public void enterClassBodyDeclaration(JabaParser.ClassBodyDeclarationContext ctx) { System.out.println("enterClassBodyDeclaration");}
    
    @Override public void exitClassBodyDeclaration(JabaParser.ClassBodyDeclarationContext ctx) { System.out.println("exitClassBodyDeclaration");}
    
    @Override public void enterMemberDeclaration(JabaParser.MemberDeclarationContext ctx) { System.out.println("enterMemberDeclaration");}
    
    @Override public void exitMemberDeclaration(JabaParser.MemberDeclarationContext ctx) { System.out.println("exitMemberDeclaration");}
    
    @Override public void enterMethodDeclaration(JabaParser.MethodDeclarationContext ctx) { System.out.println("enterMethodDeclaration");}
    
    @Override public void exitMethodDeclaration(JabaParser.MethodDeclarationContext ctx) { System.out.println("exitMethodDeclaration");}
    
    @Override public void enterGenericMethodDeclaration(JabaParser.GenericMethodDeclarationContext ctx) { System.out.println("enterGenericMethodDeclaration");}
    
    @Override public void exitGenericMethodDeclaration(JabaParser.GenericMethodDeclarationContext ctx) { System.out.println("exitGenericMethodDeclaration");}
    
    @Override public void enterConstructorDeclaration(JabaParser.ConstructorDeclarationContext ctx) { System.out.println("enterConstructorDeclaration");}
    
    @Override public void exitConstructorDeclaration(JabaParser.ConstructorDeclarationContext ctx) { System.out.println("exitConstructorDeclaration");}
    
    @Override public void enterGenericConstructorDeclaration(JabaParser.GenericConstructorDeclarationContext ctx) { System.out.println("enterGenericConstructorDeclaration");}
    
    @Override public void exitGenericConstructorDeclaration(JabaParser.GenericConstructorDeclarationContext ctx) { System.out.println("exitGenericConstructorDeclaration");}
    
    @Override public void enterFieldDeclaration(JabaParser.FieldDeclarationContext ctx) { System.out.println("enterFieldDeclaration");}
    
    @Override public void exitFieldDeclaration(JabaParser.FieldDeclarationContext ctx) { System.out.println("exitFieldDeclaration");}
    
    @Override public void enterInterfaceBodyDeclaration(JabaParser.InterfaceBodyDeclarationContext ctx) { System.out.println("enterInterfaceBodyDeclaration");}
    
    @Override public void exitInterfaceBodyDeclaration(JabaParser.InterfaceBodyDeclarationContext ctx) { System.out.println("exitInterfaceBodyDeclaration");}
    
    @Override public void enterInterfaceMemberDeclaration(JabaParser.InterfaceMemberDeclarationContext ctx) { System.out.println("enterInterfaceMemberDeclaration");}
    
    @Override public void exitInterfaceMemberDeclaration(JabaParser.InterfaceMemberDeclarationContext ctx) { System.out.println("exitInterfaceMemberDeclaration");}
    
    @Override public void enterConstDeclaration(JabaParser.ConstDeclarationContext ctx) { System.out.println("enterConstDeclaration");}
    
    @Override public void exitConstDeclaration(JabaParser.ConstDeclarationContext ctx) { System.out.println("exitConstDeclaration");}
    
    @Override public void enterConstantDeclarator(JabaParser.ConstantDeclaratorContext ctx) { System.out.println("enterConstantDeclarator");}
    
    @Override public void exitConstantDeclarator(JabaParser.ConstantDeclaratorContext ctx) { System.out.println("exitConstantDeclarator");}
    
    @Override public void enterInterfaceMethodDeclaration(JabaParser.InterfaceMethodDeclarationContext ctx) { System.out.println("enterInterfaceMethodDeclaration");}
    
    @Override public void exitInterfaceMethodDeclaration(JabaParser.InterfaceMethodDeclarationContext ctx) { System.out.println("exitInterfaceMethodDeclaration");}
    
    @Override public void enterGenericInterfaceMethodDeclaration(JabaParser.GenericInterfaceMethodDeclarationContext ctx) { System.out.println("enterGenericInterfaceMethodDeclaration");}
    
    @Override public void exitGenericInterfaceMethodDeclaration(JabaParser.GenericInterfaceMethodDeclarationContext ctx) { System.out.println("exitGenericInterfaceMethodDeclaration");}
    
    @Override public void enterVariableDeclarators(JabaParser.VariableDeclaratorsContext ctx) { System.out.println("enterVariableDeclarators");}
    
    @Override public void exitVariableDeclarators(JabaParser.VariableDeclaratorsContext ctx) { System.out.println("exitVariableDeclarators");}
    
    @Override public void enterVariableDeclarator(JabaParser.VariableDeclaratorContext ctx) { System.out.println("enterVariableDeclarator");}
    
    @Override public void exitVariableDeclarator(JabaParser.VariableDeclaratorContext ctx) { System.out.println("exitVariableDeclarator");}
    
    @Override public void enterVariableDeclaratorId(JabaParser.VariableDeclaratorIdContext ctx) { System.out.println("enterVariableDeclaratorId");}
    
    @Override public void exitVariableDeclaratorId(JabaParser.VariableDeclaratorIdContext ctx) { System.out.println("exitVariableDeclaratorId");}
    
    @Override public void enterVariableInitializer(JabaParser.VariableInitializerContext ctx) { System.out.println("enterVariableInitializer");}
    
    @Override public void exitVariableInitializer(JabaParser.VariableInitializerContext ctx) { System.out.println("exitVariableInitializer");}
    
    @Override public void enterArrayInitializer(JabaParser.ArrayInitializerContext ctx) { System.out.println("enterArrayInitializer");}
    
    @Override public void exitArrayInitializer(JabaParser.ArrayInitializerContext ctx) { System.out.println("exitArrayInitializer");}
    
    @Override public void enterEnumConstantName(JabaParser.EnumConstantNameContext ctx) { System.out.println("enterEnumConstantName");}
    
    @Override public void exitEnumConstantName(JabaParser.EnumConstantNameContext ctx) { System.out.println("exitEnumConstantName");}
    
    @Override public void enterTypeType(JabaParser.TypeTypeContext ctx) { System.out.println("enterTypeType");}
    
    @Override public void exitTypeType(JabaParser.TypeTypeContext ctx) { System.out.println("exitTypeType");}
    
    @Override public void enterClassOrInterfaceType(JabaParser.ClassOrInterfaceTypeContext ctx) { System.out.println("enterClassOrInterfaceType");}
    
    @Override public void exitClassOrInterfaceType(JabaParser.ClassOrInterfaceTypeContext ctx) { System.out.println("exitClassOrInterfaceType");}
    
    @Override public void enterPrimitiveType(JabaParser.PrimitiveTypeContext ctx) { System.out.println("enterPrimitiveType");}
    
    @Override public void exitPrimitiveType(JabaParser.PrimitiveTypeContext ctx) { System.out.println("exitPrimitiveType");}
    
    @Override public void enterTypeArguments(JabaParser.TypeArgumentsContext ctx) { System.out.println("enterTypeArguments");}
    
    @Override public void exitTypeArguments(JabaParser.TypeArgumentsContext ctx) { System.out.println("exitTypeArguments");}
    
    @Override public void enterTypeArgument(JabaParser.TypeArgumentContext ctx) { System.out.println("enterTypeArgument");}
    
    @Override public void exitTypeArgument(JabaParser.TypeArgumentContext ctx) { System.out.println("exitTypeArgument");}
    
    @Override public void enterQualifiedNameList(JabaParser.QualifiedNameListContext ctx) { System.out.println("enterQualifiedNameList");}
    
    @Override public void exitQualifiedNameList(JabaParser.QualifiedNameListContext ctx) { System.out.println("exitQualifiedNameList");}
    
    @Override public void enterFormalParameters(JabaParser.FormalParametersContext ctx) { System.out.println("enterFormalParameters");}
    
    @Override public void exitFormalParameters(JabaParser.FormalParametersContext ctx) { System.out.println("exitFormalParameters");}
    
    @Override public void enterFormalParameterList(JabaParser.FormalParameterListContext ctx) { System.out.println("enterFormalParameterList");}
    
    @Override public void exitFormalParameterList(JabaParser.FormalParameterListContext ctx) { System.out.println("exitFormalParameterList");}
    
    @Override public void enterFormalParameter(JabaParser.FormalParameterContext ctx) { System.out.println("enterFormalParameter");}
    
    @Override public void exitFormalParameter(JabaParser.FormalParameterContext ctx) { System.out.println("exitFormalParameter");}
    
    @Override public void enterLastFormalParameter(JabaParser.LastFormalParameterContext ctx) { System.out.println("enterLastFormalParameter");}
    
    @Override public void exitLastFormalParameter(JabaParser.LastFormalParameterContext ctx) { System.out.println("exitLastFormalParameter");}
    
    @Override public void enterMethodBody(JabaParser.MethodBodyContext ctx) { System.out.println("enterMethodBody");}
    
    @Override public void exitMethodBody(JabaParser.MethodBodyContext ctx) { System.out.println("exitMethodBody");}
    
    @Override public void enterConstructorBody(JabaParser.ConstructorBodyContext ctx) { System.out.println("enterConstructorBody");}
    
    @Override public void exitConstructorBody(JabaParser.ConstructorBodyContext ctx) { System.out.println("exitConstructorBody");}
    
    @Override public void enterQualifiedName(JabaParser.QualifiedNameContext ctx) { System.out.println("enterQualifiedName");}
    
    @Override public void exitQualifiedName(JabaParser.QualifiedNameContext ctx) { System.out.println("exitQualifiedName");}
    
    @Override public void enterLiteral(JabaParser.LiteralContext ctx) { System.out.println("enterLiteral");}
    
    @Override public void exitLiteral(JabaParser.LiteralContext ctx) { System.out.println("exitLiteral");}
    
    @Override public void enterAnnotation(JabaParser.AnnotationContext ctx) { System.out.println("enterAnnotation");}
    
    @Override public void exitAnnotation(JabaParser.AnnotationContext ctx) { System.out.println("exitAnnotation");}
    
    @Override public void enterAnnotationName(JabaParser.AnnotationNameContext ctx) { System.out.println("enterAnnotationName");}
    
    @Override public void exitAnnotationName(JabaParser.AnnotationNameContext ctx) { System.out.println("exitAnnotationName");}
    
    @Override public void enterElementValuePairs(JabaParser.ElementValuePairsContext ctx) { System.out.println("enterElementValuePairs");}
    
    @Override public void exitElementValuePairs(JabaParser.ElementValuePairsContext ctx) { System.out.println("exitElementValuePairs");}
    
    @Override public void enterElementValuePair(JabaParser.ElementValuePairContext ctx) { System.out.println("enterElementValuePair");}
    
    @Override public void exitElementValuePair(JabaParser.ElementValuePairContext ctx) { System.out.println("exitElementValuePair");}
    
    @Override public void enterElementValue(JabaParser.ElementValueContext ctx) { System.out.println("enterElementValue");}
    
    @Override public void exitElementValue(JabaParser.ElementValueContext ctx) { System.out.println("exitElementValue");}
    
    @Override public void enterElementValueArrayInitializer(JabaParser.ElementValueArrayInitializerContext ctx) { System.out.println("enterElementValueArrayInitializer");}
    
    @Override public void exitElementValueArrayInitializer(JabaParser.ElementValueArrayInitializerContext ctx) { System.out.println("exitElementValueArrayInitializer");}
    
    @Override public void enterAnnotationTypeDeclaration(JabaParser.AnnotationTypeDeclarationContext ctx) { System.out.println("enterAnnotationTypeDeclaration");}
    
    @Override public void exitAnnotationTypeDeclaration(JabaParser.AnnotationTypeDeclarationContext ctx) { System.out.println("exitAnnotationTypeDeclaration");}
    
    @Override public void enterAnnotationTypeBody(JabaParser.AnnotationTypeBodyContext ctx) { System.out.println("enterAnnotationTypeBody");}
    
    @Override public void exitAnnotationTypeBody(JabaParser.AnnotationTypeBodyContext ctx) { System.out.println("enterPackageDeclaration");}
    
    @Override public void enterAnnotationTypeElementDeclaration(JabaParser.AnnotationTypeElementDeclarationContext ctx) { System.out.println("enterAnnotationTypeElementDeclaration");}
    
    @Override public void exitAnnotationTypeElementDeclaration(JabaParser.AnnotationTypeElementDeclarationContext ctx) { System.out.println("exitAnnotationTypeElementDeclaration");}
    
    @Override public void enterAnnotationTypeElementRest(JabaParser.AnnotationTypeElementRestContext ctx) { System.out.println("enterAnnotationTypeElementRest");}
    
    @Override public void exitAnnotationTypeElementRest(JabaParser.AnnotationTypeElementRestContext ctx) { System.out.println("exitAnnotationTypeElementRest");}
    
    @Override public void enterAnnotationMethodOrConstantRest(JabaParser.AnnotationMethodOrConstantRestContext ctx) { System.out.println("enterAnnotationMethodOrConstantRest");}
    
    @Override public void exitAnnotationMethodOrConstantRest(JabaParser.AnnotationMethodOrConstantRestContext ctx) { System.out.println("exitAnnotationMethodOrConstantRest");}
    
    @Override public void enterAnnotationMethodRest(JabaParser.AnnotationMethodRestContext ctx) { System.out.println("enterAnnotationMethodRest");}
    
    @Override public void exitAnnotationMethodRest(JabaParser.AnnotationMethodRestContext ctx) { System.out.println("exitAnnotationMethodRest");}
    
    @Override public void enterAnnotationConstantRest(JabaParser.AnnotationConstantRestContext ctx) { System.out.println("enterAnnotationConstantRest");}
    
    @Override public void exitAnnotationConstantRest(JabaParser.AnnotationConstantRestContext ctx) { System.out.println("exitAnnotationConstantRest");}
    
    @Override public void enterDefaultValue(JabaParser.DefaultValueContext ctx) { System.out.println("enterDefaultValue");}
    
    @Override public void exitDefaultValue(JabaParser.DefaultValueContext ctx) { System.out.println("exitDefaultValue");}
    
    @Override public void enterBlock(JabaParser.BlockContext ctx) { System.out.println("enterBlock");}
    
    @Override public void exitBlock(JabaParser.BlockContext ctx) { System.out.println("exitBlock");}
    
    @Override public void enterBlockStatement(JabaParser.BlockStatementContext ctx) { System.out.println("enterBlockStatement");}
    
    @Override public void exitBlockStatement(JabaParser.BlockStatementContext ctx) { System.out.println("exitBlockStatement");}
    
    @Override public void enterLocalVariableDeclarationStatement(JabaParser.LocalVariableDeclarationStatementContext ctx) { System.out.println("enterLocalVariableDeclarationStatement");}
    
    @Override public void exitLocalVariableDeclarationStatement(JabaParser.LocalVariableDeclarationStatementContext ctx) { System.out.println("exitLocalVariableDeclarationStatement");}
    
    @Override public void enterLocalVariableDeclaration(JabaParser.LocalVariableDeclarationContext ctx) { System.out.println("enterLocalVariableDeclaration");}
    
    @Override public void exitLocalVariableDeclaration(JabaParser.LocalVariableDeclarationContext ctx) { System.out.println("exitLocalVariableDeclaration");}
    
    @Override public void enterStatement(JabaParser.StatementContext ctx) { System.out.println("enterStatement");}
    
    @Override public void exitStatement(JabaParser.StatementContext ctx) { System.out.println("exitStatement");}
    
    @Override public void enterCatchClause(JabaParser.CatchClauseContext ctx) { System.out.println("enterCatchClause");}
    
    @Override public void exitCatchClause(JabaParser.CatchClauseContext ctx) { System.out.println("exitCatchClause");}
    
    @Override public void enterCatchType(JabaParser.CatchTypeContext ctx) { System.out.println("enterCatchType");}
    
    @Override public void exitCatchType(JabaParser.CatchTypeContext ctx) { System.out.println("exitCatchType");}
    
    @Override public void enterFinallyBlock(JabaParser.FinallyBlockContext ctx) { System.out.println("enterFinallyBlock");}
    
    @Override public void exitFinallyBlock(JabaParser.FinallyBlockContext ctx) { System.out.println("exitFinallyBlock");}
    
    @Override public void enterResourceSpecification(JabaParser.ResourceSpecificationContext ctx) { System.out.println("enterResourceSpecification");}
    
    @Override public void exitResourceSpecification(JabaParser.ResourceSpecificationContext ctx) { System.out.println("exitResourceSpecification");}
    
    @Override public void enterResources(JabaParser.ResourcesContext ctx) { System.out.println("enterResources");}
    
    @Override public void exitResources(JabaParser.ResourcesContext ctx) { System.out.println("exitResources");}
    
    @Override public void enterResource(JabaParser.ResourceContext ctx) { System.out.println("enterResource");}
    
    @Override public void exitResource(JabaParser.ResourceContext ctx) { System.out.println("exitResource");}
    
    @Override public void enterSwitchBlockStatementGroup(JabaParser.SwitchBlockStatementGroupContext ctx) { System.out.println("enterSwitchBlockStatementGroup");}
    
    @Override public void exitSwitchBlockStatementGroup(JabaParser.SwitchBlockStatementGroupContext ctx) { System.out.println("exitSwitchBlockStatementGroup");}
    
    @Override public void enterSwitchLabel(JabaParser.SwitchLabelContext ctx) { System.out.println("enterSwitchLabel");}
    
    @Override public void exitSwitchLabel(JabaParser.SwitchLabelContext ctx) { System.out.println("exitSwitchLabel");}
    
    @Override public void enterForControl(JabaParser.ForControlContext ctx) { System.out.println("enterForControl");}
    
    @Override public void exitForControl(JabaParser.ForControlContext ctx) { System.out.println("exitForControl");}
    
    @Override public void enterForInit(JabaParser.ForInitContext ctx) { System.out.println("enterForInit");}
    
    @Override public void exitForInit(JabaParser.ForInitContext ctx) { System.out.println("exitForInit");}
    
    @Override public void enterEnhancedForControl(JabaParser.EnhancedForControlContext ctx) { System.out.println("enterEnhancedForControl");}
    
    @Override public void exitEnhancedForControl(JabaParser.EnhancedForControlContext ctx) { System.out.println("exitEnhancedForControl");}
    
    @Override public void enterForUpdate(JabaParser.ForUpdateContext ctx) { System.out.println("enterForUpdate");}
    
    @Override public void exitForUpdate(JabaParser.ForUpdateContext ctx) { System.out.println("exitForUpdate");}
    
    @Override public void enterParExpression(JabaParser.ParExpressionContext ctx) { System.out.println("enterParExpression");}
    
    @Override public void exitParExpression(JabaParser.ParExpressionContext ctx) { System.out.println("exitParExpression");}
    
    @Override public void enterExpressionList(JabaParser.ExpressionListContext ctx) { System.out.println("enterExpressionList");}
    
    @Override public void exitExpressionList(JabaParser.ExpressionListContext ctx) { System.out.println("exitExpressionList");}
    
    @Override public void enterStatementExpression(JabaParser.StatementExpressionContext ctx) { System.out.println("enterStatementExpression");}
    
    @Override public void exitStatementExpression(JabaParser.StatementExpressionContext ctx) { System.out.println("exitStatementExpression");}
    
    @Override public void enterConstantExpression(JabaParser.ConstantExpressionContext ctx) { System.out.println("enterConstantExpression");}
    
    @Override public void exitConstantExpression(JabaParser.ConstantExpressionContext ctx) { System.out.println("exitConstantExpression");}
    
    @Override public void enterExpression(JabaParser.ExpressionContext ctx) { System.out.println("enterExpression");}
    
    @Override public void exitExpression(JabaParser.ExpressionContext ctx) { System.out.println("exitExpression");}
    
    @Override public void enterPrimary(JabaParser.PrimaryContext ctx) { System.out.println("enterPrimary");}
    
    @Override public void exitPrimary(JabaParser.PrimaryContext ctx) { System.out.println("exitPrimary");}
    
    @Override public void enterCreator(JabaParser.CreatorContext ctx) { System.out.println("enterCreator");}
    
    @Override public void exitCreator(JabaParser.CreatorContext ctx) { System.out.println("exitCreator");}
    
    @Override public void enterCreatedName(JabaParser.CreatedNameContext ctx) { System.out.println("enterCreatedName");}
    
    @Override public void exitCreatedName(JabaParser.CreatedNameContext ctx) { System.out.println("exitCreatedName");}
    
    @Override public void enterInnerCreator(JabaParser.InnerCreatorContext ctx) { System.out.println("enterInnerCreator");}
    
    @Override public void exitInnerCreator(JabaParser.InnerCreatorContext ctx) { System.out.println("exitInnerCreator");}
    
    @Override public void enterArrayCreatorRest(JabaParser.ArrayCreatorRestContext ctx) { System.out.println("enterArrayCreatorRest");}
    
    @Override public void exitArrayCreatorRest(JabaParser.ArrayCreatorRestContext ctx) { System.out.println("exitArrayCreatorRest");}
    
    @Override public void enterClassCreatorRest(JabaParser.ClassCreatorRestContext ctx) { System.out.println("enterClassCreatorRest");}
    
    @Override public void exitClassCreatorRest(JabaParser.ClassCreatorRestContext ctx) { System.out.println("exitClassCreatorRest");}
    
    @Override public void enterExplicitGenericInvocation(JabaParser.ExplicitGenericInvocationContext ctx) { System.out.println("enterExplicitGenericInvocation");}
    
    @Override public void exitExplicitGenericInvocation(JabaParser.ExplicitGenericInvocationContext ctx) { System.out.println("exitExplicitGenericInvocation");}
    
    @Override public void enterNonWildcardTypeArguments(JabaParser.NonWildcardTypeArgumentsContext ctx) { System.out.println("enterNonWildcardTypeArguments");}
    
    @Override public void exitNonWildcardTypeArguments(JabaParser.NonWildcardTypeArgumentsContext ctx) { System.out.println("exitNonWildcardTypeArguments");}
    
    @Override public void enterTypeArgumentsOrDiamond(JabaParser.TypeArgumentsOrDiamondContext ctx) { System.out.println("enterTypeArgumentsOrDiamond");}
    
    @Override public void exitTypeArgumentsOrDiamond(JabaParser.TypeArgumentsOrDiamondContext ctx) { System.out.println("exitTypeArgumentsOrDiamond");}
    
    @Override public void enterNonWildcardTypeArgumentsOrDiamond(JabaParser.NonWildcardTypeArgumentsOrDiamondContext ctx) { System.out.println("enterNonWildcardTypeArgumentsOrDiamond");}
    
    @Override public void exitNonWildcardTypeArgumentsOrDiamond(JabaParser.NonWildcardTypeArgumentsOrDiamondContext ctx) { System.out.println("exitNonWildcardTypeArgumentsOrDiamond");}
    
    @Override public void enterSuperSuffix(JabaParser.SuperSuffixContext ctx) { System.out.println("enterSuperSuffix");}
    
    @Override public void exitSuperSuffix(JabaParser.SuperSuffixContext ctx) { System.out.println("exitSuperSuffix");}
    
    @Override public void enterExplicitGenericInvocationSuffix(JabaParser.ExplicitGenericInvocationSuffixContext ctx) { System.out.println("enterExplicitGenericInvocationSuffix");}
    
    @Override public void exitExplicitGenericInvocationSuffix(JabaParser.ExplicitGenericInvocationSuffixContext ctx) { System.out.println("exitExplicitGenericInvocationSuffix");}
    
    @Override public void enterArguments(JabaParser.ArgumentsContext ctx) { System.out.println("enterArguments");}
    
    @Override public void exitArguments(JabaParser.ArgumentsContext ctx) { System.out.println("exitArguments");}

    
    @Override public void enterEveryRule(ParserRuleContext ctx) { }//System.out.println("enterEveryRule");}
    
    @Override public void exitEveryRule(ParserRuleContext ctx) { }//System.out.println("exitEveryRule");}
    
    @Override public void visitTerminal(TerminalNode node) { System.out.println("visitTerminal");}
    
    @Override public void visitErrorNode(ErrorNode node) { System.out.println("visitErrorNode");}

}
