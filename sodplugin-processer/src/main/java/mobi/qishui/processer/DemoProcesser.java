package mobi.qishui.processer;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import mobi.qishui.anno.JsPlugin;

/**
 * Created by wangxiao on 2017/4/26.
 */

@AutoService(Processor.class)
public class DemoProcesser extends AbstractProcessor {
    private ProcessingEnvironment mEnv;
    private Elements mElementUtil;
    private Filer mFiler;
    private Types mTypeUtils;
    private String packageName;
    private static final ClassName LOG = ClassName.get("android.util", "Log");

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new HashSet<>();
        annotations.add(JsPlugin.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        this.mEnv = processingEnvironment;
        this.mElementUtil = processingEnvironment.getElementUtils();
        this.mFiler = processingEnvironment.getFiler();
        this.mTypeUtils = processingEnvironment.getTypeUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "一轮开始了");

        String className = "PluginManager";

        MethodSpec addPlugin = MethodSpec.methodBuilder("addPlugin")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String.class, "pluginName")
                .addParameter(String.class, "pluginClassName")
                .addParameter(boolean.class, "loadOnInit")
                .addStatement("$T.i(\"fuck\",pluginName+pluginClassName+loadOnInit)", LOG)
                .build();

        MethodSpec.Builder initBuilder = MethodSpec.methodBuilder("init")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class);

        if (roundEnvironment.getElementsAnnotatedWith(JsPlugin.class).size() == 0) {
            info(null, "直接退出");
            return true;
        }

        for (Element element : roundEnvironment.getElementsAnnotatedWith(JsPlugin.class)) {
            if (element.getKind() != ElementKind.CLASS) {
                error(element, "element is not class!");
                return false;
            }

            TypeElement typeElement = (TypeElement) element;
            JsPlugin annotation = typeElement.getAnnotation(JsPlugin.class);
            if (annotation == null) {
                error(element, "annotation error");
                return false;
            }

            if (packageName == null) {
                packageName = mElementUtil.getPackageOf(typeElement).getQualifiedName().toString();
                info(null, "包名" + packageName);
            }

            initBuilder.addStatement("addPlugin($S, $T.class.getName(), false)", annotation.name(), typeElement.asType());

        }

        MethodSpec methodSpec = initBuilder.build();
        TypeSpec typeSpec = TypeSpec.classBuilder(className).addMethod(addPlugin).addMethod(methodSpec).build();
        try {
            JavaFile.builder(packageName, typeSpec).build().writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void error(Element e, String msg) {
        mEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, msg, e);
    }

    public void info(Element e, String msg) {
        mEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, msg, e);
    }
}
