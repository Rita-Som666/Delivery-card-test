[![Build status](https://ci.appveyor.com/api/projects/status/abaolvw99dl0gydq?svg=true)](https://ci.appveyor.com/project/Rita-Som666/delivery-card-test)

# Интеграция ReportPortal

1. Скачать контейнер reportportal с помощью команды

   ```curl -LO https://raw.githubusercontent.com/reportportal/reportportal/master/docker-compose.yml```

2. Поместить doker-compose в папку проекта

3. Запустить doker-compose командой

   ```docker-compose up```

4. Открыть ReportPortal на [локальном хосте](http://localhost:8080/)

5. Авторизоваться, используя логин и пароль из doker-compose, они лежат в переменной ```RP_INITIAL_ADMIN_PASSWORD```

   Скорее всего это будут логин *superadmin* и пароль *erebus*

6. Добавить в build.gralle

  ```
repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven { url "https://dl.bintray.com/epam/reportportal" }
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter:5.6.1'
    implementation 'com.epam.reportportal:agent-java-junit5:5.1.6'
    implementation 'com.epam.reportportal:logger-java-logback:5.1.4'
    implementation 'ch.qos.logback:logback-classic:1.4.6'
    implementation 'com.epam.reportportal:logger-java-log4j:5.0.2'
    compileOnly 'log4j:log4j:1.2.17'
    implementation 'org.apache.logging.log4j:log4j-api:2.13.3'
    // Остальные зависимости
}

test {
    useJUnitPlatform()
    testLogging.showStandardStreams = true
    systemProperty 'junit.jupiter.extensions.autodetection.enabled', true
    // Остальные настройки
}
```

7. Добавить в папку scr/test/resources файлы [log4j2.xml](https://github.com/Rita-Som666/Delivery-card-test/blob/RP/src/test/resources/log4j2.xml), [logback.xml](https://github.com/Rita-Som666/Delivery-card-test/blob/RP/src/test/resources/logback.xml) и [reportportal.properties](https://github.com/Rita-Som666/Delivery-card-test/blob/RP/src/test/resources/reportportal.properties), а так же создать папку src/test/resources/META-INF/services, в которую поместить файл [org.junit.jupiter.api.extension.Extension](https://github.com/Rita-Som666/Delivery-card-test/blob/RP/src/test/resources/META-INF/services/org.junit.jupiter.api.extension.Extension)

8. Добавить в папку src/test/java/<название пакета>/ папку *util*, после поместить в неё файлы [LoggingUtils.java](https://github.com/Rita-Som666/Delivery-card-test/blob/RP/src/test/java/ru/netology/util/LoggingUtils.java) и [ScreenShooterReportPortalExtension.java](ScreenShooterReportPortalExtension.java)

9. Добавить перед тестовым классом анотацию ```@ExtendWith({ScreenShooterReportPortalExtension.class})```

10. Заустить автотесты используя команду ```./gradlew test```


