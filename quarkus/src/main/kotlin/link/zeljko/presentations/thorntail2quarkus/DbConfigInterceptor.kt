package link.zeljko.presentations.thorntail2quarkus

import io.smallrye.config.ConfigSourceInterceptorContext
import io.smallrye.config.ConfigValue

class DbConfigInterceptor : io.smallrye.config.ConfigSourceInterceptor {
    override fun getValue(context: ConfigSourceInterceptorContext, name: String): ConfigValue? {
        return when (name) {
            "quarkus.datasource.username" -> context.proceed("db.user")
            "quarkus.datasource.password" -> context.proceed("db.pass")
            "quarkus.datasource.jdbc.url" -> ConfigValue.builder()
                .withValue("jdbc:postgresql://${context.proceed("db.host").value}/${context.proceed("db.database").value}")
                .build()
            else -> context.proceed(name)
        }
    }
}