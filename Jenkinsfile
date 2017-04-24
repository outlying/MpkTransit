#!groovy​
node {

    def ignoreFailures = (env.BRANCH_NAME != 'master')

    stage('Prepare'){
        checkout scm
    }

    try {
        stage('Build'){
            sh "./gradlew clean build -PignoreFailures=" + ignoreFailures
        }
    } catch (error) {
        // Build failed
        throw new IllegalStateException(error, "Build failed")
    }
}