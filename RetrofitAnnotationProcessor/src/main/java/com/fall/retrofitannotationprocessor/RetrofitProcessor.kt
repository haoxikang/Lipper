package com.fall.retrofitannotationprocessor

import com.fall.retrofitannotation.DefaultBuilder
import com.fall.retrofitannotation.RetrofitBuilder
import com.fall.retrofitannotation.RetrofitBuilderFactory
import com.fall.retrofitannotation.RetrofitService
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

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
        annotations.add(RetrofitBuilder::class.java.canonicalName)
        annotations.add(DefaultBuilder::class.java.canonicalName)
        return annotations
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun process(set: MutableSet<out TypeElement>, roundEvt: RoundEnvironment): Boolean {
        val generator = HttpGenerator(filer)
        set.flatMap { roundEvt.getElementsAnnotatedWith(it) }
                .filter {
                    if (it.getAnnotation(DefaultBuilder::class.java) != null) {
                        generator.defaultBuilderElement = it as TypeElement
                    }
                    if (it.getAnnotation(RetrofitBuilder::class.java) != null) {
                        generator.retrofitBuilderElements.add(it as TypeElement)
                    }
                    it.getAnnotation(RetrofitService::class.java) != null
                }
                .forEach {
                    generator.element = it as TypeElement
                    generator.generate()

                }


        return true
    }
}

