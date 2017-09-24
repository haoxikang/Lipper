package com.fall.retrofitannotationprocessor

import com.fall.retrofitannotation.RetrofitService
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec
import javax.annotation.processing.Filer
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import kotlin.reflect.KClass

/**
 * Created by qqq34 on 2017/9/22.
 */
class HttpGenerator(val filer: Filer) {
    lateinit var element: TypeElement
    lateinit var defaultBuilderElement: TypeElement
    fun generate() {
        val qualifiedName = element.qualifiedName.toString()   //被注解的类的绝对路径
        val packageName = qualifiedName.substringBeforeLast(".") //报名
        val className = "${element.simpleName}HttpMethods"                      //新生成的类名


        val retrofitService = element.getAnnotation(RetrofitService::class.java)  //被注解类的注解
        val baseUrl = retrofitService.baseUrl                                                             //注解中的 host地址

val file = FileSpec.builder(packageName,className)
        .addType()
       // .addProperty(PropertySpec.builder("service",RetrofitService::class).delegate(CodeBlock.of()))

    }
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