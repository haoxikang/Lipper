package com.fall.retrofitannotationprocessor

import com.fall.retrofitannotation.DefaultBuilder
import com.fall.retrofitannotation.RetrofitBuilderFactory
import com.fall.retrofitannotation.RetrofitService
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

open class RetrofitProcessor : AbstractProcessor() {
    private lateinit var filer: Filer
    private lateinit var messager: Messager
    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        filer = processingEnv.filer
        messager = processingEnv.messager
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        val annotations = LinkedHashSet<String>()
        annotations.add(RetrofitService::class.java.canonicalName)
        annotations.add(RetrofitBuilderFactory::class.java.canonicalName)
        annotations.add(DefaultBuilder::class.java.canonicalName)
        return annotations
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun process(set: MutableSet<out TypeElement>, roundEvt: RoundEnvironment): Boolean {

            set.flatMap { roundEvt.getElementsAnnotatedWith(it) }
                    .forEach{
                       // val defaultBuilder = it.getAnnotation(DefaultBuilder::class.java)
                        messager.printMessage(Diagnostic.Kind.ERROR ,"${it.simpleName}")
                        messager.printMessage(Diagnostic.Kind.ERROR ,"${it.enclosingElement}")

                    }
//            val greeterClass = ClassName("com.fallllllll.retrofit", "Greeter")
//            val file = FileSpec.builder("com.fallllllll.retrofit", "HelloWorld")
//                    .addType(TypeSpec.classBuilder("Greeter")
//                            .primaryConstructor(FunSpec.constructorBuilder()
//                                    .addParameter("name", String::class)
//                                    .build())
//                            .addProperty(PropertySpec.builder("name", String::class)
//                                    .initializer("name")
//                                    .build())
//                            .addFunction(FunSpec.builder("greet")
//                                    .addStatement("println(%S)", "Hello, \$name")
//                                    .build())
//                            .build())
//                    .addFunction(FunSpec.builder("main")
//                            .addParameter("args", String::class, KModifier.VARARG)
//                            .addStatement("${greeterClass.simpleName()}(args[0]).greet()")
//                            .build())
//                    .build()
//            val sourceFile = filer.createResource(StandardLocation.SOURCE_OUTPUT,"com.fallllllll.retrofit","HelloWorld.kt")
//            val writer = sourceFile.openWriter()
//            writer.use {
//                writer.write(file.toString())
//                writer.flush()
//            }


        return true
    }
}

