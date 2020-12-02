pipeline{
    agent any

    environment {
        PORT = '8585'
    }

    stages{
        stage( 'initialize' ){
            steps{
                curl -XPOST -H "Authorization: 296cffeda36eb085a3f9cb0679e464d858ce70b0" https://api.github.com/repos/ccgarciab/labs-backend/statuses/$(git rev-parse HEAD) -d "{
                  \"state\": \"pending\",
                  \"target_url\": \"${BUILD_URL}\",
                  \"description\": \"The build is pending!\"
                }"
                }
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
        success{
        curl -XPOST -H "Authorization: 296cffeda36eb085a3f9cb0679e464d858ce70b0" https://api.github.com/repos/ccgarciab/labs-backend/statuses/$(git rev-parse HEAD) -d "{
          \"state\": \"success\",
          \"target_url\": \"${BUILD_URL}\",
          \"description\": \"The build has succeeded!\"
        }"
        }
        failure{
        curl -XPOST -H "Authorization: 296cffeda36eb085a3f9cb0679e464d858ce70b0" https://api.github.com/repos/ccgarciab/labs-backend/statuses/$(git rev-parse HEAD) -d "{
          \"state\": \"failure\",
          \"target_url\": \"${BUILD_URL}\",
          \"description\": \"The build has failed!\"
        }"
        }
    }
    }
