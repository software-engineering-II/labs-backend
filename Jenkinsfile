pipeline{
    agent any

    environment {
        PORT = '8585'
    }

    stages{
        stage( 'Initialize' ){
            curl "https://api.GitHub.com/repos/ccgarciab/labs-backend/statuses/$GIT_COMMIT?access_token=296cffeda36eb085a3f9cb0679e464d858ce70b0" \
              -H "Content-Type: application/json" \
              -X POST \
              -d "{\"state\": \"pending\",\"context\": \"continuous-integration/jenkins\", \"description\": \"Jenkins\", \"target_url\": \"http://seii.eastus.cloudapp.azure.com/job/Lab4 backend grupo 5/$BUILD_NUMBER/console\"}"
            }
        stage( 'clean' ){
            steps{
                sh "mvn clean"
            }
        }
        stage( 'test' ){
            steps{
                sh "mvn test"
            }
        }
        stage( 'package' ){
            steps{
                sh "mvn package"
            }
        }
    }
    post{
        success {
            curl "https://api.GitHub.com/repos/ccgarciab/labs-backend/statuses/$GIT_COMMIT?access_token=296cffeda36eb085a3f9cb0679e464d858ce70b0" \
              -H "Content-Type: application/json" \
              -X POST \
              -d "{\"state\": \"success\",\"context\": \"continuous-integration/jenkins\", \"description\": \"Jenkins\", \"target_url\": \"http://seii.eastus.cloudapp.azure.com/job/Lab4 backend grupo 5/$BUILD_NUMBER/console\"}"
        }
        failure {
            curl "https://api.GitHub.com/repos/ccgarciab/labs-backend/statuses/$GIT_COMMIT?access_token=296cffeda36eb085a3f9cb0679e464d858ce70b0" \
              -H "Content-Type: application/json" \
              -X POST \
              -d "{\"state\": \"failure\",\"context\": \"continuous-integration/jenkins\", \"description\": \"Jenkins\", \"target_url\": \"http://seii.eastus.cloudapp.azure.com/job/Lab4 backend grupo 5/$BUILD_NUMBER/console\"}"
        }
        }
    }
