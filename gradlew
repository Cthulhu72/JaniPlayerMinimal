#!/usr/bin/env sh
##############################################################################
# Gradle start up script for UN*X
##############################################################################
APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options.
DEFAULT_JVM_OPTS=""

# Locate JAVA_HOME if set
if [ -n "$JAVA_HOME" ]; then
    JAVA_EXE="$JAVA_HOME/bin/java"
else
    JAVA_EXE="java"
fi

# Run Gradle
exec "$JAVA_EXE" $DEFAULT_JVM_OPTS -classpath "gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
