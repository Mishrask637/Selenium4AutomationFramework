import groovy.json.JsonSlurper
import java.nio.file.Paths

pipeline{

agent any

options{
    buildDiscarder(logRotator(numToKeepStr:'20'))
}

// triggers{cron(getCronPattern())}

parameters{
    choice name:'Env_Name',
    choices:['test','dev','stage','prod'],
    description:'Select The Environment'

    choice name:'Browser',
    choices:['chrome','MicrosoftEdge','firefox'],
    description:'Select Browser'
}

stages{
    stage('Build')
    {
        steps{
            script{
                try{
                    bat """
                    echo 'Entered Into Build Phase'
                    mvn clean
                    echo 'Build Success'
                    """
                    currentBuild.result = 'SUCCESS'
                }
                catch(Exception e)
                {
                    echo '**************** Build Error ************'
                    isBuildStatusFail=true
                    currentBuild.result='FAILURE'
                }
            }
        }
    }
    stage('Test')
        {
        steps{
            script{
                    try{
                        bat """
                        echo 'Entered Into Test Phase'
                        mvn test
                        echo 'Test Success'
                        """
                        currentBuild.result = 'SUCCESS'
                    }
                    catch(Exception e)
                    {
                        echo '**************** Test Error ************'+e
                        isBuildStatusFail=true
                        currentBuild.result='FAILURE'
                    }
                }
            }
        }
        stage('Report')
        {
            steps{
                script{
                cucumber '**/reports/json/*.json'
                try{
                emailext mimeType : 'text/html',
                body:"""Automation Test Result ${env.JOB_NAME} is ${currentBuild.result}.
                <br></br>
                <br>The Build status is ${currentBuild.result}.
                <br>Build URL:${BUILD_URL}cucumber-html-reports/overview-features.html.""",
                subject: "Automation Test Result for build no ${env.BUILD_NUMBER} ",
                to:"mishraskus637@gmail.com",
                attachmentsPattern:'reports/html/*.html'
                }
                catch(Exception e)
                {
                    echo 'Unable to send email '+e
                }
                }
            }
        }
    }

    post{
        always{
            echo 'This will always run'
        }
        success{
            echo 'This will run only if successful'
        }
        failure{
            echo 'This will run only if failed'
        }
        unstable{
            echo 'This will run only if the run was marked as unstable'
        }
        changed{
            echo 'This will run only if the status of the pipeline has changed'
            echo 'For example, if the pipeline was perviously failing but is now successful'
        }
    }
}

    def getCronPattern()
    {
        if(env.BRANCH_NAME=='master')
        {
            return '* * * * *'
        }
    }