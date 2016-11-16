package com.university.nn.kotlinbased.server.initializer

import com.university.nn.kotlinbased.server.config.AppContextConfig
import com.university.nn.kotlinbased.server.config.DispatcherConfig
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet
import javax.servlet.ServletContext
import javax.servlet.ServletException

open class WebAppInitializer : WebApplicationInitializer {
    @Throws(ServletException::class)
    override fun onStartup(servletContext: ServletContext) {
        val rootContext = AnnotationConfigWebApplicationContext()
        rootContext.register(
                AppContextConfig::class.java
//                ,WebSecurityConfig::class.java
        )

        servletContext.addListener(ContextLoaderListener(rootContext))

        val dispatcherContext = AnnotationConfigWebApplicationContext()
        dispatcherContext.register(DispatcherConfig::class.java)

        val dispatcher = servletContext.addServlet("dispatcher", DispatcherServlet(dispatcherContext))
        dispatcher.setLoadOnStartup(1)
        dispatcher.addMapping("/")
    }
}
