package com.fall.retrofitannotationprocessor

import com.fall.retrofitannotation.RetrofitBuilder
import com.fall.retrofitannotation.RetrofitService
import com.squareup.kotlinpoet.*
import retrofit2.Retrofit
import javax.annotation.processing.Filer
import javax.lang.model.element.TypeElement
import javax.tools.StandardLocation

/**
 * Created by qqq34 on 2017/9/22.
 */
class HttpGenerator(private val filer: Filer) {
    lateinit var element: TypeElement
    var defaultBuilderElement: TypeElement? = null
    val retrofitBuilderElements: MutableList<TypeElement>   by lazy { ArrayList<TypeElement>() }
    fun generate() {

        val qualifiedName = element.qualifiedName.toString()
        val packageName = qualifiedName.substringBeforeLast(".")
        val className = "${element.simpleName}HttpMethods"
        val funName = "createRetrofit"
        val retrofitService = element.getAnnotation(RetrofitService::class.java)
        val baseUrl = retrofitService.baseUrl
        var retrofitBuilderElement: TypeElement? = null
        val builderElement: TypeElement

        retrofitBuilderElements.forEach {
            if (it.getAnnotation(RetrofitBuilder::class.java).servicePaths.contains(qualifiedName)) {
                retrofitBuilderElement = it
            }
        }


        if (retrofitBuilderElement == null && defaultBuilderElement == null) {
            throw NoSuchElementException("Missing retrofitBuilder")
        }
        if (retrofitBuilderElement != null) {
            builderElement = retrofitBuilderElement!!
        } else {
            builderElement = defaultBuilderElement!!
        }


        val file = FileSpec.builder(packageName, className)
                .addType(TypeSpec.classBuilder(className)
                        .addProperty(PropertySpec.builder("service", element.asClassName(), KModifier.PUBLIC)
                                .delegate("lazy{$funName().create(%T::class.java)}", element.asClassName())
                                .build())
                        .addFunction(FunSpec.builder(funName)
                                .addModifiers(KModifier.PRIVATE)
                                .addStatement("return %T().getBuilder().baseUrl(\"$baseUrl\").build()", builderElement.asClassName())
                                .returns(Retrofit::class)
                                .build())
                        .build())
                .build()
        generateFile(file, qualifiedName, className)
    }

    private fun generateFile(file: FileSpec, packageName: String, className: String) {
        val sourceFile = filer.createResource(StandardLocation.SOURCE_OUTPUT, packageName, "$className.kt")
        val writer = sourceFile.openWriter()
        writer.use {
            writer.write(file.toString())
            writer.flush()
        }
    }
}